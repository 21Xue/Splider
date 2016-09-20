package com.xyp.webDriver.model;

import org.openqa.selenium.WebElement;

/**
 * Created by DT288 on 2016/9/20.
 */


public class MinPriceElement {

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

