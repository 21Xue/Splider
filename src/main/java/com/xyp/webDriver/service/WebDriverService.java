package com.xyp.webDriver.service;

import com.xyp.Fun;
import com.xyp.webDriver.model.MinPriceElement;
import com.xyp.webDriver.model.WebDriverFromHilton;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DT288 on 2016/9/20.
 */
public class WebDriverService {

    private static Pattern p = Pattern.compile("[^.0-9\\s]");

    private static final String toolURL = "E:\\Code\\chromedriver.exe";

    static {
        System.setProperty("webdriver.chrome.driver", toolURL);
    }


    private static void createMinPriceElement(List<WebElement> list, List<MinPriceElement> priceMap) {
        for (WebElement trElement : list) {
            String priceString = trElement.findElement(By.className("priceamount")).getText().replace("$", "").replace("*", "");
            Matcher m = p.matcher(priceString);
            priceString = m.replaceAll("").replace(",", "");
            String[] priceGroup = priceString.split("(\\s)");
            if (priceGroup.length > 1) {
                Double tempMinGroup = Double.MAX_VALUE;
                for (String tempPrice : priceGroup) {
                    if (!StringUtils.isEmpty(tempPrice)) {
                        if (Double.valueOf(tempPrice) < tempMinGroup) {
//                            System.out.print("This is group min");
                            tempMinGroup = Double.valueOf(tempPrice);
                        }
                    }
                }
                priceString = String.valueOf(tempMinGroup);
            }

            if (!StringUtils.isEmpty(priceString)) {
                priceMap.add(new MinPriceElement(trElement, Double.valueOf(priceString)));
            }
        }
    }

    public static WebDriverFromHilton webDriver(String url) throws Exception {
        ChromeDriver driver = new ChromeDriver();
        driver.get(url);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> elements = driver.findElements(By.tagName("tr"));
//        List<WebElement> needElement = new ArrayList<WebElement>();
        List<WebElement> calculationList = new ArrayList<WebElement>();

        for (WebElement element : elements) {
            try {
                String tempString = element.findElement(By.tagName("a")).getText();
                if (!StringUtils.isEmpty(tempString)) {
                    if (!tempString.contains("HHONORS")) {
                        calculationList.add(element);
                    }
                }
            } catch (Exception e) {
                System.out.print("no a tag in tr");
            }
        }

        List<MinPriceElement> priceMap = new ArrayList<MinPriceElement>();
        createMinPriceElement(calculationList, priceMap);


        if (priceMap.size() == 0) {
            for (String win : driver.getWindowHandles()) {
                WebDriver close = driver.switchTo().window(win);
                close.close();
            }
            driver.quit();
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

        List<WebElement> truePriceList = truePrice.findElements(By.tagName("tr"));

        int size = truePriceList.size();
        if (size > 3) {
            price = truePriceList.get(size - 3).findElement(By.className("price")).getText();
            tax = truePriceList.get(size - 2).findElement(By.className("price")).getText();
            total = truePriceList.get(size - 1).findElement(By.className("price")).getText();
        } else {
            price = truePriceList.get(size - 2).findElement(By.className("price")).getText();
            total = truePriceList.get(size - 1).findElement(By.className("price")).getText();
        }
//        if (truePrice.findElements(By.className("price")).size() > 3) {
//            price = truePrice.findElements(By.className("price")).get(1).getText();
//            tax = truePrice.findElements(By.className("price")).get(2).getText();
//            total = truePrice.findElements(By.className("price")).get(3).getText();
//        } else {
//            price = truePrice.findElements(By.className("price")).get(1).getText();
//            total = truePrice.findElements(By.className("price")).get(2).getText();
//        }

        for (String win : driver.getWindowHandles()) {
            WebDriver close = driver.switchTo().window(win);
            close.close();
        }
        driver.quit();
        return new WebDriverFromHilton(price, tax, total);
    }
}
