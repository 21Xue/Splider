package com.xyp.rateCompare.model;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: ysy
 * Date: 14-7-25
 * Time: 上午11:33
 * To change this template use File | Settings | File Templates.
 */
public class GoogleRateCheckDto implements Comparable<GoogleRateCheckDto> {

    String roomTypeCode;
    String ratePlanCode;
    BigDecimal baseRate;
    BigDecimal otherRate;
    BigDecimal totalRate;
    String currencyCode;

    public String getRoomTypeCode() {
        return roomTypeCode;
    }

    public void setRoomTypeCode(String roomTypeCode) {
        this.roomTypeCode = roomTypeCode;
    }

    public String getRatePlanCode() {
        return ratePlanCode;
    }

    public void setRatePlanCode(String ratePlanCode) {
        this.ratePlanCode = ratePlanCode;
    }

    public BigDecimal getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(BigDecimal baseRate) {
        this.baseRate = baseRate;
    }

    public BigDecimal getOtherRate() {
        return otherRate;
    }

    public void setOtherRate(BigDecimal otherRate) {
        this.otherRate = otherRate;
    }

    public BigDecimal getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(BigDecimal totalRate) {
        this.totalRate = totalRate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GoogleRateCheckDto)) return false;

        GoogleRateCheckDto that = (GoogleRateCheckDto) o;

        if (!baseRate.equals(that.baseRate)) return false;
        if (!currencyCode.equals(that.currencyCode)) return false;
        if (!otherRate.equals(that.otherRate)) return false;
        if (!ratePlanCode.equals(that.ratePlanCode)) return false;
        if (!roomTypeCode.equals(that.roomTypeCode)) return false;
        if (!totalRate.equals(that.totalRate)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roomTypeCode.hashCode();
        result = 31 * result + ratePlanCode.hashCode();
        result = 31 * result + baseRate.hashCode();
        result = 31 * result + otherRate.hashCode();
        result = 31 * result + totalRate.hashCode();
        result = 31 * result + currencyCode.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "GoogleRateCheckDto{" +
                "roomTypeCode='" + roomTypeCode + '\'' +
                ", ratePlanCode='" + ratePlanCode + '\'' +
                ", baseRate=" + baseRate +
                ", otherRate=" + otherRate +
                ", totalRate=" + totalRate +
                ", currencyCode='" + currencyCode + '\'' +
                '}';
    }

    
    public int compareTo(GoogleRateCheckDto o) {
        int result = this.getRoomTypeCode().compareTo(o.getRoomTypeCode());
        if (result == 0) {
            result = this.getRatePlanCode().compareTo(o.getRatePlanCode());
            if (result == 0) {
                result = this.getTotalRate().compareTo(o.getTotalRate());
                if (result == 0) {
                    result = this.getBaseRate().compareTo(o.getBaseRate());
                }
            }
        }
        return result;
    }


}
