package com.xyp.webDriver.model;

/**
 * Created by DT288 on 2016/9/8.
 */
public class WebDriverFromHilton {

    public WebDriverFromHilton(String p, String ta, String to, String charge) {
        this.price = p;
        this.tax = ta;
        this.total = to;
        this.charge = charge;
    }

    private String price;

    private String tax;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    private String total;

    private String charge;
}
