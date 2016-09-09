package com.xyp.rateCompare.util;

import com.derbysoft.dplatform.google.protocol.dto.AvailRQCondition;
import com.derbysoft.dplatform.google.protocol.dto.transaction.RoomPriceDataType;
import com.derbysoft.dplatform.google.protocol.dto.transaction.Transaction;
import com.xyp.rateCompare.model.GoogleRateCheckDto;
import com.xyp.rateCompare.model.THotelRate;
import com.xyp.rateCompare.model.THotelRateListing;
import com.xyp.rateCompare.model.TRoomRate;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * User: xiechunhao
 * Date: 12-9-19
 */
public class GoogleRateCheckUtil {


    public static List<GoogleRateCheckDto> getRoomRateFromGoogleApi(Transaction.Result googleResult){
        List<GoogleRateCheckDto> googleRateCheckDtos = null;
        if(googleResult != null){
            List<RoomPriceDataType> roomBundles =  googleResult.getRoomBundle();
            if(roomBundles != null && !roomBundles.isEmpty()){
                googleRateCheckDtos = new ArrayList<GoogleRateCheckDto>(roomBundles.size());
                GoogleRateCheckDto googleRateCheckDto = null;
                for(RoomPriceDataType roomPriceDataType:roomBundles){
                    googleRateCheckDto = new GoogleRateCheckDto();
                    googleRateCheckDto.setRoomTypeCode(roomPriceDataType.getCustom3());
                    googleRateCheckDto.setRatePlanCode(roomPriceDataType.getCustom4());
                    googleRateCheckDto.setTotalRate(BigDecimal.valueOf(
                            Double.valueOf(roomPriceDataType.getCustom2())).setScale(2,BigDecimal.ROUND_CEILING));
                    googleRateCheckDto.setBaseRate(roomPriceDataType.getBaserate().getValue());
                    googleRateCheckDto.setOtherRate(roomPriceDataType.getOtherFees().getValue().add(roomPriceDataType.getTax().getValue()));
                    googleRateCheckDto.setCurrencyCode(roomPriceDataType.getBaserate().getCurrency());
                    googleRateCheckDtos.add(googleRateCheckDto);
                }
            }else{
                googleRateCheckDtos = new ArrayList<GoogleRateCheckDto>(1);
                GoogleRateCheckDto googleRateCheckDto = null;
                String custom2 = googleResult.getCustom2();
                if(StringUtils.isNotBlank(custom2)){
                    googleRateCheckDto = new GoogleRateCheckDto();
                    googleRateCheckDto.setRoomTypeCode(googleResult.getCustom3());
                    googleRateCheckDto.setRatePlanCode(googleResult.getCustom4());
                    googleRateCheckDto.setTotalRate(BigDecimal.valueOf(Double.valueOf(custom2)).setScale(2, BigDecimal.ROUND_CEILING));
                    googleRateCheckDto.setBaseRate(googleResult.getBaserate().getValue());
                    googleRateCheckDto.setOtherRate(googleResult.getOtherFees().getValue().add(googleResult.getTax().getValue()));
                    googleRateCheckDto.setCurrencyCode(googleResult.getBaserate().getCurrency());
                    googleRateCheckDtos.add(googleRateCheckDto);
                }
            }
        }
        return googleRateCheckDtos == null ? new ArrayList<GoogleRateCheckDto>():googleRateCheckDtos;
    }


    public static List<GoogleRateCheckDto> getRoomRateFromSearchFeed(THotelRateListing hotelRateListing){
        List<GoogleRateCheckDto> googleRateCheckDtos = null;
        if(hotelRateListing != null ){
            List<THotelRate> thotelRates = hotelRateListing.getHotelRate();
            if(thotelRates != null && !thotelRates.isEmpty()){
                THotelRate ThotelRate = thotelRates.get(0);
                List<TRoomRate> tRoomRateList = ThotelRate.getRoomRate();
                if(tRoomRateList != null && !tRoomRateList.isEmpty()){
                    googleRateCheckDtos = new ArrayList<GoogleRateCheckDto>(tRoomRateList.size());
                    for(TRoomRate tRoomRate : tRoomRateList){
                        GoogleRateCheckDto googleRateCheckDto = new GoogleRateCheckDto();
                        googleRateCheckDto.setRoomTypeCode(tRoomRate.getRoomTypeCode());
                        googleRateCheckDto.setRatePlanCode(tRoomRate.getRatePlanCode());
                        googleRateCheckDto.setTotalRate(tRoomRate.getTotalBaseRate().add(tRoomRate.getTotalOtherFees()).add(tRoomRate.getTotalTax()));
                        googleRateCheckDto.setBaseRate(tRoomRate.getTotalBaseRate());
                        googleRateCheckDto.setOtherRate(tRoomRate.getTotalOtherFees().add(tRoomRate.getTotalTax()));
                        googleRateCheckDto.setCurrencyCode(tRoomRate.getCurrency());
                        googleRateCheckDtos.add(googleRateCheckDto);
                    }
                }
            }
        }
        return googleRateCheckDtos == null ? new ArrayList<GoogleRateCheckDto>():googleRateCheckDtos;
    }

    public static List<GoogleRateCheckDto> getAdapterFromGoogleApi(Transaction.Result googleResult){
        List<GoogleRateCheckDto> googleRateCheckDtos = null;
        if(googleResult != null){
            googleRateCheckDtos = new ArrayList<GoogleRateCheckDto>(1);
            GoogleRateCheckDto googleRateCheckDto = new GoogleRateCheckDto();
            googleRateCheckDto.setRoomTypeCode(googleResult.getCustom3());
            googleRateCheckDto.setRatePlanCode(googleResult.getCustom4());
            googleRateCheckDto.setTotalRate(googleResult.getBaserate().getValue().add(googleResult.getTax().getValue()));
            googleRateCheckDto.setBaseRate(googleResult.getBaserate().getValue());
            googleRateCheckDto.setOtherRate(googleResult.getTax().getValue());
            googleRateCheckDto.setCurrencyCode(googleResult.getBaserate().getCurrency());
            googleRateCheckDtos.add(googleRateCheckDto);
        }
        return googleRateCheckDtos == null ? new ArrayList<GoogleRateCheckDto>():googleRateCheckDtos;
    }



    public static THotelRateListing getSearchFeedHTERate(AvailRQCondition availRQCondition){
        THotelRateListing tHotelRateListing = null;
        if(availRQCondition != null){
            String channelCode = availRQCondition.getChannelCode();
            String hotelCode = availRQCondition.getHotelCode();
            StringBuilder requestURL = new StringBuilder();
            requestURL.append("http://54.189.101.169/dplatform-searchfeed/hotelRate.action?enableSmartcache=0");
            if(StringUtils.isNotBlank(hotelCode) && StringUtils.isNotBlank(channelCode)){
                requestURL.append("&s=").append(channelCode).append("&hotelid=").append(hotelCode)
                        .append("&d1=").append(availRQCondition.getCheckInDate()).append("&d2=").append(availRQCondition.getCheckOutDate());
//                String thotelRateListing = HttpInvoker.doGet(requestURL.toString(),null,"UTF-8",false);
//                String thotelRateListing=HttpIn
//                tHotelRateListing = XmlMarshaller.unmarshall(THotelRateListing.class, thotelRateListing);
            }
        }
        return tHotelRateListing;
    }





}