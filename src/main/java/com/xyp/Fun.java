package com.xyp;

import com.xyp.WaringSystem.WaringType;
import com.xyp.rateCompare.model.CompareDataDTO;
import com.xyp.rateCompare.model.CompareResultDto;
import com.xyp.rateCompare.service.CompareService;
import com.xyp.readCVS.core.ReadCVSService;
import com.xyp.readCVS.model.PriceAccuracyCVS;
import com.xyp.readCVS.model.WebDriverFromHilton;
import com.xyp.readCVS.util.CSVtoJSONUtil;
import com.xyp.readCVS.util.JsonUtil;
import com.xyp.spider.bean.LinkTypeData;
import com.xyp.spider.core.ExtractService;
import com.xyp.spider.rule.Rule;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
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


    public static void main(String[] args) {
        String priceString = "C1,23.00";
        Matcher m = p.matcher(priceString);
        priceString = m.replaceAll("").replace(",", "");
    }

    @Test
    public void test() {
//        webDriver(testURL);
        System.out.print("asd");
    }

    public WebDriverFromHilton webDriver(String url) throws Exception {
        System.setProperty("webdriver.chrome.driver", toolURL);
        ChromeDriver driver = new ChromeDriver();
        driver.get(url);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> elements = driver.findElements(By.className("roomRateTable"));
        List<WebElement> needElement = new ArrayList<WebElement>();
        for (WebElement element : elements) {
            if (element.findElements(By.className("ratePackageFeaturedRates")).size() == 0) {
                needElement.add(element);
            }
        }
        List<MinPriceElement> priceMap = new ArrayList<MinPriceElement>();

        for (WebElement element : needElement) {
            List<WebElement> list = element.findElements(By.tagName("tr"));
            for (WebElement trElement : list) {

                String priceString = trElement.findElement(By.className("priceamount")).getText().replace("$", "").replace("*", "");
                Matcher m = p.matcher(priceString);
                priceString = m.replaceAll("").replace(",", "");
                String[] priceGroup = priceString.split("(\\s)");
                if (priceGroup.length > 1) {
                    priceString = priceGroup[1];
                }

                if (!StringUtils.isEmpty(priceString)) {
                    priceMap.add(new MinPriceElement(trElement, Double.valueOf(priceString)));
                }
            }
        }

        if (priceMap.size() == 0) {
            for (String win : driver.getWindowHandles()) {
                WebDriver close = driver.switchTo().window(win);
                close.close();
            }
//            driver.close();

            return new WebDriverFromHilton("0", "0", "0");
        }

        MinPriceElement min = Collections.min(priceMap, new Comparator<MinPriceElement>() {
            public int compare(MinPriceElement o1, MinPriceElement o2) {
                double p1 = o1.getPrice();
                double p2 = o2.getPrice();
                if (p1 < p2) {
                    return -1;
                }
                return 1;
            }
        });

        WebElement mine = min.getWebElement();

        mine.findElement(By.tagName("a")).click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String currentWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();

        Iterator<String> it = handles.iterator();
        WebDriver window = null;
        while (it.hasNext()) {
            String now = it.next();
            if (currentWindow.equals(now)) {
                continue;
            }
            window = driver.switchTo().window(now);
            System.out.println("title,url = " + window.getTitle() + "," + window.getCurrentUrl());
        }
        WebElement truePrice = window.findElement(By.tagName("tbody"));


        String price = "0";
        String tax = "0";
        String total = "0";
        if (truePrice.findElements(By.className("price")).size() > 3) {
            price = truePrice.findElements(By.className("price")).get(1).getText();
            tax = truePrice.findElements(By.className("price")).get(2).getText();
            total = truePrice.findElements(By.className("price")).get(3).getText();
        } else {
            price = truePrice.findElements(By.className("price")).get(1).getText();
            total = truePrice.findElements(By.className("price")).get(2).getText();
        }

        for (String win : driver.getWindowHandles()) {
            WebDriver close = driver.switchTo().window(win);
            close.close();
//            driver.close();
        }
        return new WebDriverFromHilton(price, tax, total);

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
            ReadCVSService r = new ReadCVSService("E:\\Code\\Splider\\price_accuracy_validation_report_Hilton_2016-09-11_to_2016-09-11.csv");
            String s = CSVtoJSONUtil.CSVtoJSON(r.readLine());
            PriceAccuracyCVS data = JsonUtil.fromJson(s, PriceAccuracyCVS.class);
            List<WebDriverFromHilton> list = new ArrayList<WebDriverFromHilton>();
            CompareService cs = new CompareService();
            List<FunDTO> funList = new ArrayList<FunDTO>();
            for (PriceAccuracyCVS.PriceAccuracyDTO dto : data.getResult()) {
                try {


                    CompareResultDto compareResultDto = cs.getDStorageRate(dto);
                    WebDriverFromHilton webDriverFromHilton = webDriver(dto.getUrl());
                    funList.add(new FunDTO(dto, compareResultDto, webDriverFromHilton));
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.print(JsonUtil.toJson(dto));
                }
            }

            Map<String, List<WaringType>> errorMap = new HashMap<String, List<WaringType>>();
            for (FunDTO f : funList) {
                try {
                    List<WaringType> waringTypeList = new ArrayList<WaringType>();
                    String hotelCode = f.getPriceAccuracyDTO().getHotel();

                    double cachePrice = Double.valueOf(f.getPriceAccuracyDTO().getCached_price().replace(",", "").replace("*", ""));

                    double googleAdaper = f.getCompareResultDto().getAdapterList().get(0).getBaseRate().doubleValue();

                    double fetchedPrice = Double.valueOf(f.getPriceAccuracyDTO().getFetched_price().replace(",", "").replace("*", ""));

                    double webDriver = Double.valueOf(f.getWebDriverFromHilton().getPrice().replace(",", "").replace("*", ""));

                    double hte = f.getCompareResultDto().getHteList().get(0).getBaseRate().doubleValue();

                    double dstorage = f.getCompareResultDto().getDstorageList().get(0).getBaseRate().doubleValue();

                    if (cachePrice != googleAdaper) {
                        waringTypeList.add(WaringType.cached_priceVSgoogle_adaper);
                    }
                    if (fetchedPrice != webDriver) {
                        waringTypeList.add(WaringType.fetched_priceVSwebDriver);
                    }
                    if (hte == dstorage && hte == googleAdaper && googleAdaper == dstorage) {
                        waringTypeList.add(WaringType.Operation_is_Same);
                    }
                    if (googleAdaper != hte) {
                        waringTypeList.add(WaringType.google_adaperVShte);
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
                    System.out.print(JsonUtil.toJson(f));
                }
            }

            System.out.printf(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
