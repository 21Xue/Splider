package com.xyp.rateCompare.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "hotelRate"
})
@XmlRootElement(name = "HotelRateList")
public class THotelRateListing {
    private static final long serialVersionUID = 1L;
    @XmlElement(name = "HotelRate")
    protected List<THotelRate> hotelRate = new ArrayList();

    @XmlAttribute(required = true, name = "CheckInDate")
    protected String checkInDate;
    @XmlAttribute(required = true, name = "CheckOutDate")
    protected String checkOutDate;
    @XmlAttribute(required = true, name = "Rooms")
    protected Integer rooms;
    @XmlAttribute(required = true, name = "Guests")
    protected Integer guests;
    @XmlAttribute(required = false, name = "Children")
    protected Integer children;

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        THotelRateListing that = (THotelRateListing) o;

        if (checkInDate != null ? !checkInDate.equals(that.checkInDate) : that.checkInDate != null) return false;
        if (checkOutDate != null ? !checkOutDate.equals(that.checkOutDate) : that.checkOutDate != null) return false;
        if (guests != null ? !guests.equals(that.guests) : that.guests != null) return false;
        if (hotelRate != null ? !hotelRate.equals(that.hotelRate) : that.hotelRate != null) return false;
        if (rooms != null ? !rooms.equals(that.rooms) : that.rooms != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hotelRate != null ? hotelRate.hashCode() : 0;
        result = 31 * result + (checkInDate != null ? checkInDate.hashCode() : 0);
        result = 31 * result + (checkOutDate != null ? checkOutDate.hashCode() : 0);
        result = 31 * result + (rooms != null ? rooms.hashCode() : 0);
        result = 31 * result + (guests != null ? guests.hashCode() : 0);
        return result;
    }

    public List<THotelRate> getHotelRate() {
        return hotelRate;
    }

    public void setHotelRate(List<THotelRate> hotelRate) {
        this.hotelRate = hotelRate;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Integer getGuests() {
        return guests;
    }

    public void setGuests(Integer guests) {
        this.guests = guests;
    }
}
