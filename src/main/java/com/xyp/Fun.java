package com.xyp;

import com.xyp.WaringSystem.WaringType;
import com.xyp.rateCompare.model.CompareResultDto;
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
            ReadCVSService r = new ReadCVSService("E:\\Code\\Splider\\price_accuracy_validation_report_Hilton_2016-09-18_to_2016-09-18.csv");
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
            ReadCVSService r = new ReadCVSService("E:\\Code\\Splider\\price_accuracy_validation_report_Hilton_2016-09-21_to_2016-09-21.csv");
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

                    double cachePrice = Double.valueOf(f.getPriceAccuracyDTO().getCached_price().replace(",", "").replace("*", ""));

                    double googleAdaper = f.getCompareResultDto().getAdapterList().getBaseRate().doubleValue();

                    double fetchedPrice = Double.valueOf(f.getPriceAccuracyDTO().getFetched_price().replace(",", "").replace("*", ""));

                    double webDriver = Double.valueOf(f.getWebDriverFromHilton().getPrice().replace(",", "").replace("*", ""));

                    double hte = f.getCompareResultDto().getHteList().getBaseRate().doubleValue();

                    double dstorage = f.getCompareResultDto().getDstorageList().getBaseRate().doubleValue();

                    if (fetchedPrice != webDriver) {
                        waringTypeList.add(WaringType.fetched_priceVSwebDriver);
                    }

                    if (cachePrice != googleAdaper) {
                        waringTypeList.add(WaringType.cached_priceVSgoogle_adaper);
                    }


                    if (hte == dstorage && hte == googleAdaper && googleAdaper == dstorage) {
                        waringTypeList.add(WaringType.Operation_is_Same);
                        if (hte != fetchedPrice) {
                            waringTypeList.add(WaringType.fetched_priceVSOperation_Same);
                        }
                        if (hte != webDriver) {
                            waringTypeList.add(WaringType.webDriverVSOperation_Same);
                        }
                    }


                    if (googleAdaper != hte) {
                        waringTypeList.add(WaringType.google_adaperVShte);
                        if (hte == webDriver || hte == fetchedPrice) {
                            waringTypeList.add(WaringType.hteSameWithWebDriverOrFetchPrice);
                        }
                    }
                    if (googleAdaper != dstorage) {
                        waringTypeList.add(WaringType.google_adaperVSdstorage);
                    }
                    if (dstorage != hte) {
                        waringTypeList.add(WaringType.dstorageVShte);
                    }


                    errorMap.put(hotelCode, waringTypeList);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(JsonUtil.toJson(f));
                }
            }

//            for (List<WaringType> waringTypeList : errorMap.values()) {
//                if(waringTypeList)
//            }
            System.out.println(JsonUtil.toJson(errorMap));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
