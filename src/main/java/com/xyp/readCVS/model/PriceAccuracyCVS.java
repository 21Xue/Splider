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
        private String partner;
        private String hotel;
        private String check_in_date;
        private String length_of_stay;
        private String fetched_price;
        private String fetched_tax;
        private String fetched_currency;
        private String fetched_time;
        private String cached_price;
        private String cached_tax;
        private String cached_currency;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPartner() {
            return partner;
        }

        public void setPartner(String partner) {
            this.partner = partner;
        }

        public String getHotel() {
            return hotel;
        }

        public void setHotel(String hotel) {
            this.hotel = hotel;
        }

        public String getCheck_in_date() {
            return check_in_date;
        }

        public void setCheck_in_date(String check_in_date) {
            this.check_in_date = check_in_date;
        }

        public String getLength_of_stay() {
            return length_of_stay;
        }

        public void setLength_of_stay(String length_of_stay) {
            this.length_of_stay = length_of_stay;
        }

        public String getFetched_price() {
            return fetched_price;
        }

        public void setFetched_price(String fetched_price) {
            this.fetched_price = fetched_price;
        }

        public String getFetched_tax() {
            return fetched_tax;
        }

        public void setFetched_tax(String fetched_tax) {
            this.fetched_tax = fetched_tax;
        }

        public String getFetched_currency() {
            return fetched_currency;
        }

        public void setFetched_currency(String fetched_currency) {
            this.fetched_currency = fetched_currency;
        }

        public String getFetched_time() {
            return fetched_time;
        }

        public void setFetched_time(String fetched_time) {
            this.fetched_time = fetched_time;
        }

        public String getCached_price() {
            return cached_price;
        }

        public void setCached_price(String cached_price) {
            this.cached_price = cached_price;
        }

        public String getCached_tax() {
            return cached_tax;
        }

        public void setCached_tax(String cached_tax) {
            this.cached_tax = cached_tax;
        }

        public String getCached_currency() {
            return cached_currency;
        }

        public void setCached_currency(String cached_currency) {
            this.cached_currency = cached_currency;
        }

        public String getCached_time() {
            return cached_time;
        }

        public void setCached_time(String cached_time) {
            this.cached_time = cached_time;
        }

        private String cached_time;
        private String url;
    }

}
