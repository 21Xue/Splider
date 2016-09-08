package com.xyp;

import com.xyp.readCVS.core.ReadCVSService;
import com.xyp.readCVS.model.PriceAccuracyCVS;
import com.xyp.readCVS.model.WebDriverFromHilton;
import com.xyp.readCVS.util.CSVtoJSONUtil;
import com.xyp.readCVS.util.JsonUtil;
import com.xyp.spider.bean.LinkTypeData;
import com.xyp.spider.core.ExtractService;
import com.xyp.spider.rule.Rule;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
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
                String priceString = trElement.findElement(By.className("priceamount")).getText().replace("$", "");
                priceMap.add(new MinPriceElement(trElement, Double.valueOf(priceString)));
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

//    @Test
//    public void heheh() {
//        try {
//            ReadCVSService r = new ReadCVSService("E:\\Code\\Splider\\test.csv");
//            String s = CSVtoJSONUtil.CSVtoJSON(r.readLine());
//            PriceAccuracyCVS data = JsonUtil.fromJson(s, PriceAccuracyCVS.class);
//            for (PriceAccuracyCVS.PriceAccuracyDTO dto : data.getResult()) {
//                Rule rule = new Rule(dto.getUrl(), "roomRateTable", Rule.CLASS, Rule.GET);
//                Elements firstElement = ExtractService.getFristHtml(rule);
//                List<Element> needElement = new ArrayList<Element>();
//                for (Element e : firstElement) {
//                    if (ExtractService.getElement(e, "ratePackageFeaturedRates").size() == 0) {
//                        needElement.add(e);
//                    }
//                }
//                Rule rule2 = new Rule("https://secure3.hilton.com/en_US/dt/reservation/book.htm?execution=e1s1&amp;_eventId=rateSummaryDetailsPopup&amp;srpId=LDTPL1&amp;roomCode=NK1S&amp;index=0&amp;internalDeepLinking=true", "hehe", Rule.CLASS, Rule.GET);
//                ExtractService.getFristHtml(rule2);
//
//
////                https://secure3.hilton.com/en_US/dt/reservation/book.htm?execution=e1s1&amp;_eventId=rateSummaryDetailsPopup&amp;srpId=LDTPL1&amp;roomCode=NK1S&amp;index=0&amp;internalDeepLinking=true
//
////                roomRateTable
//
////                not ratePackageFeaturedRates
//            }
//
//            System.out.printf(s);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
