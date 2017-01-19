package com.xyp;

import com.xyp.WaringSystem.WaringType;
import com.xyp.rateCompare.model.CompareResultDto;
import com.xyp.rateCompare.model.GoogleRateCheckDto;
import com.xyp.rateCompare.service.CompareService;
import com.xyp.readCVS.core.ReadCVSService;
import com.xyp.readCVS.model.PriceAccuracyCVS;
import com.xyp.webDriver.model.WebDriverFromHilton;
import com.xyp.readCVS.util.CSVtoJSONUtil;
import com.xyp.readCVS.util.JsonUtil;
import com.xyp.webDriver.service.WebDriverService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DT288 on 2016/9/8.
 */
public class Fun {

    private static Pattern p = Pattern.compile("[^.0-9\\s]");

    private static final String testURL = "http://us.linkcenter.derbysoftca.com/dplatform-linkcenter/booking.htm?hotelCode=HILTON-RDUII&providerHotelCode=RDUII&checkInDate=2016-09-18&checkOutDate=2016-09-19&identifier=google-hilton&price=191.82&roomTypeCode=NEXJEX&ratePlanCode=SDT2G2&currency=USD&language=en&userCountry=US&testClick=true&sitetype=verification&additionalInfo=2&partnerId=hilton";

    private static final String toolURL = "E:\\Code\\chromedriver.exe";


    @Test
    public void test() {
        try {
            ReadCVSService r = new ReadCVSService("E:\\Code\\Splider\\price_accuracy_validation_report_Hilton_2016-09-23_to_2016-09-23.csv");
            String s = CSVtoJSONUtil.CSVtoJSON(r.readLine());
            PriceAccuracyCVS data = JsonUtil.fromJson(s, PriceAccuracyCVS.class);
            List<WebDriverFromHilton> list = new ArrayList<WebDriverFromHilton>();
            List<FunDTO> funList = new ArrayList<FunDTO>();
            for (PriceAccuracyCVS.PriceAccuracyDTO dto : data.getResult()) {
                try {
                    WebDriverFromHilton webDriverFromHilton = WebDriverService.webDriver(dto.getUrl());
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(JsonUtil.toJson(dto));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("asd");
    }


    private class MinPriceElement {

        public MinPriceElement(WebElement e, double p) {
            this.webElement = e;
            this.price = p;
        }

        public WebElement getWebElement() {
            return webElement;
        }

        public void setWebElement(WebElement webElement) {
            this.webElement = webElement;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        private WebElement webElement;

        private double price;
    }

    @Test
    public void heheh() {
        try {
            ReadCVSService r = new ReadCVSService("E:\\Code\\Splider\\price_accuracy_validation_report_Hilton_2017-01-17_to_2017-01-17.csv");
            String s = CSVtoJSONUtil.CSVtoJSON(r.readLine());
            PriceAccuracyCVS data = JsonUtil.fromJson(s, PriceAccuracyCVS.class);
            CompareService cs = new CompareService();
            List<FunDTO> funList = new ArrayList<FunDTO>();
            for (PriceAccuracyCVS.PriceAccuracyDTO dto : data.getResult()) {
                try {
                    CompareResultDto compareResultDto = cs.getDStorageRate(dto);
                    WebDriverFromHilton webDriverFromHilton = WebDriverService.webDriver(dto.getUrl());
                    funList.add(new FunDTO(dto, compareResultDto, webDriverFromHilton));
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(JsonUtil.toJson(dto));
                }
            }

            Map<String, List<WaringType>> errorMap = new HashMap<String, List<WaringType>>();
            for (FunDTO f : funList) {
                try {
                    List<WaringType> waringTypeList = new ArrayList<WaringType>();


                    String hotelCode = f.getPriceAccuracyDTO().getHotel();
                    if (Math.abs(Double.valueOf(f.getPriceAccuracyDTO().getFetched_price()) - Double.valueOf(f.getPriceAccuracyDTO().getCached_price())) < 1) {
                        if (!f.getPriceAccuracyDTO().getFetched_tax().equals(f.getPriceAccuracyDTO().getCached_tax())) {
                            waringTypeList.add(WaringType.TaxDifferent);
                            errorMap.put(hotelCode, waringTypeList);
                            continue;
                        }
                    }

                    double fetchedPrice = Double.valueOf(f.getPriceAccuracyDTO().getFetched_price().replace(",", "").replace("*", ""));
                    double webDriver = Double.valueOf(f.getWebDriverFromHilton().getPrice().replace(",", "").replace("*", ""));

                    boolean fetchWeb = false;
                    if (fetchedPrice != webDriver) {
                        waringTypeList.add(WaringType.fetched_priceVSwebDriver);
                    } else {
                        fetchWeb = true;
                    }

                    if (webDriver == 0) {
                        waringTypeList.add(WaringType.HotelDeletePrice);
                    }

                    double cachePrice = Double.valueOf(f.getPriceAccuracyDTO().getCached_price().replace(",", "").replace("*", ""));

                    double googleAdaper = f.getCompareResultDto().getAdapterList().get(0).getBaseRate().doubleValue();
                    double hte = f.getCompareResultDto().getHteList().get(0).getBaseRate().doubleValue();
                    double dstorage = f.getCompareResultDto().getDstorageList().get(0).getBaseRate().doubleValue();

                    if (Math.abs(hte - dstorage) < 1 && Math.abs(hte - googleAdaper) < 1 && Math.abs(googleAdaper - dstorage) < 1) {
                        if (hte != fetchedPrice) {
                            boolean hteFindFetch = false;
                            for (GoogleRateCheckDto hteDto : f.getCompareResultDto().getHteList()) {
                                if (hteDto.getBaseRate().doubleValue() == fetchedPrice) {
                                    hteFindFetch = true;
                                }
                            }
                            boolean dstorageFindFetch = false;
                            for (GoogleRateCheckDto dsDto : f.getCompareResultDto().getDstorageList()) {
                                if (dsDto.getBaseRate().doubleValue() == fetchedPrice) {
                                    dstorageFindFetch = true;
                                }
                            }

                            if (hteFindFetch && !dstorageFindFetch) {
                                waringTypeList.add(WaringType.CacheLackPrice);
                            }

                            if ((hte - fetchedPrice) > 1 && (webDriver == fetchedPrice)) {
                                waringTypeList.add(WaringType.hteDiffWithWeb);
                            }


                        } else {
                            waringTypeList.add(WaringType.AlreadyRight);
                        }

                    }

                    boolean isHteHaveWebPrice = false;
                    for (GoogleRateCheckDto hteDto : f.getCompareResultDto().getHteList()) {
                        if (hteDto.getBaseRate().doubleValue() == fetchedPrice) {
                            isHteHaveWebPrice = true;
                        }
                    }

                    boolean isdstorageHaveWebPrice = false;
                    for (GoogleRateCheckDto dsDto : f.getCompareResultDto().getDstorageList()) {
                        if (dsDto.getBaseRate().doubleValue() == fetchedPrice) {
                            isdstorageHaveWebPrice = true;
                        }
                    }

                    if (Math.abs(hte - dstorage) < 1 && Math.abs(hte - googleAdaper) > 1) {
                        waringTypeList.add(WaringType.GetPriceDelay);
                    }

                    if (isHteHaveWebPrice && !isdstorageHaveWebPrice) {
                        waringTypeList.add(WaringType.CacheNoPrice);
                    }

                    if (!isHteHaveWebPrice && !isdstorageHaveWebPrice) {
                        waringTypeList.add(WaringType.hteDiffWithWeb);
                    }

                    if (isHteHaveWebPrice && isdstorageHaveWebPrice) {
                        waringTypeList.add(WaringType.HotelChangePrice);
                    }

                    errorMap.put(hotelCode, waringTypeList);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(JsonUtil.toJson(f));
                }

            }
            System.out.println(JsonUtil.toJson(errorMap));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
