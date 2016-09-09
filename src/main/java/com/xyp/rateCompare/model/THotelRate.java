package com.xyp.rateCompare.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HotelRate", propOrder = {
        "roomRate"
})

public class THotelRate {
//    private static final long serialVersionUID = 1L;
    @XmlElement(name = "RoomRate")
    protected List<TRoomRate> roomRate = new ArrayList();

    @XmlAttribute(required = true, name = "HotelCode")
    protected String hotelCode;

    @XmlAttribute(name = "HotelName")
    protected String hotelName;

    @XmlAttribute(required = true, name = "URL")
    protected String url;

    @XmlAttribute(name = "Address")
    protected String address;

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    @XmlAttribute(name = "Error")
    protected Integer error;

    @XmlAttribute(name = "ErrorMessage")
    protected String errorMessage;

    transient private String chainCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        THotelRate hotelRate = (THotelRate) o;

        if (address != null ? !address.equals(hotelRate.address) : hotelRate.address != null) return false;
        if (hotelCode != null ? !hotelCode.equals(hotelRate.hotelCode) : hotelRate.hotelCode != null) return false;
        if (hotelName != null ? !hotelName.equals(hotelRate.hotelName) : hotelRate.hotelName != null) return false;
        if (roomRate != null ? !roomRate.equals(hotelRate.roomRate) : hotelRate.roomRate != null) return false;
        if (url != null ? !url.equals(hotelRate.url) : hotelRate.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roomRate != null ? roomRate.hashCode() : 0;
        result = 31 * result + (hotelCode != null ? hotelCode.hashCode() : 0);
        result = 31 * result + (hotelName != null ? hotelName.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<TRoomRate> getRoomRate() {
        return roomRate;
    }

    public void setRoomRate(List<TRoomRate> roomRate) {
        this.roomRate = roomRate;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getChainCode() {
        return chainCode;
    }

    public void setChainCode(String chainCode) {
        this.chainCode = chainCode;
    }
}
