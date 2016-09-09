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

/**
 * Created by DT288 on 2016/9/8.
 */
public class Fun {


    private static final String testURL = "http://us.linkcenter.derbysoftca.com/dplatform-linkcenter/booking.htm?hotelCode=HILTON-RDUII&providerHotelCode=RDUII&checkInDate=2016-09-18&checkOutDate=2016-09-19&identifier=google-hilton&price=191.82&roomTypeCode=NEXJEX&ratePlanCode=SDT2G2&currency=USD&language=en&userCountry=US&testClick=true&sitetype=verification&additionalInfo=2&partnerId=hilton";

    private static final String toolURL = "E:\\Code\\chromedriver.exe";

    @Test
    public void test() {
        webDriver(testURL);
        System.out.print("asd");
    }

    public WebDriverFromHilton webDriver(String url) {
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
                if (!StringUtils.isEmpty(priceString)) {
                    priceMap.add(new MinPriceElement(trElement, Double.valueOf(priceString)));
                }
            }
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


        String price = truePrice.findElements(By.className("price")).get(1).getText();
        String tax = truePrice.findElements(By.className("price")).get(2).getText();
        String total = truePrice.findElements(By.className("price")).get(3).getText();

        for (String win : driver.getWindowHandles()) {
            WebDriver close = driver.switchTo().window(win);
            close.close();
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
            ReadCVSService r = new ReadCVSService("E:\\Code\\Splider\\test.csv");
            String s = CSVtoJSONUtil.CSVtoJSON(r.readLine());
            PriceAccuracyCVS data = JsonUtil.fromJson(s, PriceAccuracyCVS.class);
            List<WebDriverFromHilton> list = new ArrayList<WebDriverFromHilton>();
            CompareService cs = new CompareService();
            List<FunDTO> funList = new ArrayList<FunDTO>();
            for (PriceAccuracyCVS.PriceAccuracyDTO dto : data.getResult()) {
                CompareResultDto compareResultDto = cs.getDStorageRate(dto);
                WebDriverFromHilton webDriverFromHilton = webDriver(dto.getUrl());
                funList.add(new FunDTO(dto, compareResultDto, webDriverFromHilton));
            }

            Map<String, List<WaringType>> errorMap = new HashMap<String, List<WaringType>>();
            for (FunDTO f : funList) {
                List<WaringType> waringTypeList = new ArrayList<WaringType>();
                String hotelCode = f.getPriceAccuracyDTO().getHotel();


                double cachePrice = Double.valueOf(f.getPriceAccuracyDTO().getCached_price());

                double googleAdaper = f.getCompareResultDto().getAdapterList().get(0).getBaseRate().doubleValue();

                double fetchedPrice = Double.valueOf(f.getPriceAccuracyDTO().getFetched_price());

                double webDriver = Double.valueOf(f.getWebDriverFromHilton().getPrice());

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
            }

            System.out.printf(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
