package com.xyp.rateCompare.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;
import java.util.List;


/**
 * <p>Java class for T_room complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="T_room">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="trp" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="rt" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="rp" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="rm" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="rc" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="pa" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="hs" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="url" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="servicerule" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="taxrule" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="additionalrule" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RoomRate")
public class TRoomRate {
    private static final long serialVersionUID = 1L;
    @XmlAttribute(required = true, name = "TotalBaseRate")
    protected BigDecimal totalBaseRate;
    @XmlAttribute(name = "TotalTax")
    protected BigDecimal totalTax;
    @XmlAttribute(name = "TotalOtherFees")
    protected BigDecimal totalOtherFees;

    @XmlAttribute(name = "DailyBaseRate")
    protected List<BigDecimal> dailyBaseRate;

    @XmlAttribute(required = true, name = "RoomTypeName")
    protected String roomTypeName;
    @XmlAttribute(required = true, name = "RatePlanName")
    protected String ratePlanName;
    @XmlAttribute(required = true, name = "RoomTypeCode")
    protected String roomTypeCode;
    @XmlAttribute(required = true, name = "RatePlanCode")
    protected String ratePlanCode;
    @XmlAttribute(required = true, name = "Currency")
    protected String currency;
    @XmlAttribute(required = true, name = "URL")
    protected String url;

    @XmlAttribute(name = "Payment")
    protected String payment;

    @XmlAttribute(name = "FreeMeal")
    protected String freeMeal;

    @XmlAttribute(name = "FreeInternet")
    protected String freeInternet;

    @XmlAttribute(name = "CreditCardGuarantee")
    protected Boolean creditCardGuarantee;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TRoomRate tRoomRate = (TRoomRate) o;

        if (creditCardGuarantee != null ? !creditCardGuarantee.equals(tRoomRate.creditCardGuarantee) : tRoomRate.creditCardGuarantee != null)
            return false;
        if (currency != null ? !currency.equals(tRoomRate.currency) : tRoomRate.currency != null) return false;
        if (dailyBaseRate != null ? !dailyBaseRate.equals(tRoomRate.dailyBaseRate) : tRoomRate.dailyBaseRate != null)
            return false;
        if (freeMeal != null ? !freeMeal.equals(tRoomRate.freeMeal) : tRoomRate.freeMeal != null) return false;
        if (payment != null ? !payment.equals(tRoomRate.payment) : tRoomRate.payment != null) return false;
        if (ratePlanCode != null ? !ratePlanCode.equals(tRoomRate.ratePlanCode) : tRoomRate.ratePlanCode != null)
            return false;
        if (ratePlanName != null ? !ratePlanName.equals(tRoomRate.ratePlanName) : tRoomRate.ratePlanName != null)
            return false;
        if (roomTypeCode != null ? !roomTypeCode.equals(tRoomRate.roomTypeCode) : tRoomRate.roomTypeCode != null)
            return false;
        if (roomTypeName != null ? !roomTypeName.equals(tRoomRate.roomTypeName) : tRoomRate.roomTypeName != null)
            return false;
        if (totalBaseRate != null ? !totalBaseRate.equals(tRoomRate.totalBaseRate) : tRoomRate.totalBaseRate != null)
            return false;
        if (totalOtherFees != null ? !totalOtherFees.equals(tRoomRate.totalOtherFees) : tRoomRate.totalOtherFees != null)
            return false;
        if (totalTax != null ? !totalTax.equals(tRoomRate.totalTax) : tRoomRate.totalTax != null) return false;
        if (url != null ? !url.equals(tRoomRate.url) : tRoomRate.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = totalBaseRate != null ? totalBaseRate.hashCode() : 0;
        result = 31 * result + (totalTax != null ? totalTax.hashCode() : 0);
        result = 31 * result + (totalOtherFees != null ? totalOtherFees.hashCode() : 0);
        result = 31 * result + (dailyBaseRate != null ? dailyBaseRate.hashCode() : 0);
        result = 31 * result + (roomTypeName != null ? roomTypeName.hashCode() : 0);
        result = 31 * result + (ratePlanName != null ? ratePlanName.hashCode() : 0);
        result = 31 * result + (roomTypeCode != null ? roomTypeCode.hashCode() : 0);
        result = 31 * result + (ratePlanCode != null ? ratePlanCode.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (payment != null ? payment.hashCode() : 0);
        result = 31 * result + (freeMeal != null ? freeMeal.hashCode() : 0);
        result = 31 * result + (creditCardGuarantee != null ? creditCardGuarantee.hashCode() : 0);
        return result;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public BigDecimal getTotalBaseRate() {
        return totalBaseRate;
    }

    public void setTotalBaseRate(BigDecimal totalBaseRate) {
        this.totalBaseRate = totalBaseRate;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getTotalOtherFees() {
        return totalOtherFees;
    }

    public void setTotalOtherFees(BigDecimal totalOtherFees) {
        this.totalOtherFees = totalOtherFees;
    }

    public List<BigDecimal> getDailyBaseRate() {
        return dailyBaseRate;
    }

    public void setDailyBaseRate(List<BigDecimal> dailyBaseRate) {
        this.dailyBaseRate = dailyBaseRate;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public String getRatePlanName() {
        return ratePlanName;
    }

    public void setRatePlanName(String ratePlanName) {
        this.ratePlanName = ratePlanName;
    }

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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFreeMeal() {
        return freeMeal;
    }

    public void setFreeMeal(String freeMeal) {
        this.freeMeal = freeMeal;
    }

    public Boolean getCreditCardGuarantee() {
        return creditCardGuarantee;
    }

    public void setCreditCardGuarantee(Boolean creditCardGuarantee) {
        this.creditCardGuarantee = creditCardGuarantee;
    }

    public String getFreeInternet() {
        return freeInternet;
    }

    public void setFreeInternet(String freeInternet) {
        this.freeInternet = freeInternet;
    }
}
