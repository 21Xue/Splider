package com.xyp.readCVS.model;

import java.util.List;

/**
 * Created by DT288 on 2016/9/8.
 */
public class PriceAccuracyCVS {

    public List<PriceAccuracyDTO> getResult() {
        return result;
    }

    public void setResult(List<PriceAccuracyDTO> result) {
        this.result = result;
    }

    List<PriceAccuracyDTO> result;

    public class PriceAccuracyDTO {

        private String hotel_id;

        private String check_in;

        private String nights;

        private String scraped_price;

        private String scraped_tax;

        private String scraped_currency;

        private String scraped_total_price;

        private String feed_price;

        private String feed_tax;

        private String feed_currency;

        private String total_price;

        private String url;

        public String getHotel_id() {
            return hotel_id;
        }

        public void setHotel_id(String hotel_id) {
            this.hotel_id = hotel_id;
        }

        public String getCheck_in() {
            return check_in;
        }

        public void setCheck_in(String check_in) {
            this.check_in = check_in;
        }

        public String getNights() {
            return nights;
        }

        public void setNights(String nights) {
            this.nights = nights;
        }

        public String getScraped_price() {
            return scraped_price;
        }

        public void setScraped_price(String scraped_price) {
            this.scraped_price = scraped_price;
        }

        public String getScraped_tax() {
            return scraped_tax;
        }

        public void setScraped_tax(String scraped_tax) {
            this.scraped_tax = scraped_tax;
        }

        public String getScraped_currency() {
            return scraped_currency;
        }

        public void setScraped_currency(String scraped_currency) {
            this.scraped_currency = scraped_currency;
        }

        public String getScraped_total_price() {
            return scraped_total_price;
        }

        public void setScraped_total_price(String scraped_total_price) {
            this.scraped_total_price = scraped_total_price;
        }

        public String getFeed_price() {
            return feed_price;
        }

        public void setFeed_price(String feed_price) {
            this.feed_price = feed_price;
        }

        public String getFeed_tax() {
            return feed_tax;
        }

        public void setFeed_tax(String feed_tax) {
            this.feed_tax = feed_tax;
        }

        public String getFeed_currency() {
            return feed_currency;
        }

        public void setFeed_currency(String feed_currency) {
            this.feed_currency = feed_currency;
        }

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

}
