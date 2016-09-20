package com.xyp.rateCompare.service;

import com.derbysoft.dplatform.google.protocol.dto.AvailRQCondition;
import com.derbysoft.dplatform.google.protocol.dto.transaction.Transaction;
import com.derbysoft.dplatform.google.protocol.service.GoogleHiltonCheckService;
import com.xyp.rateCompare.model.CompareDataDTO;
import com.xyp.rateCompare.model.CompareResultDto;
import com.xyp.rateCompare.model.GoogleRateCheckDto;
import com.xyp.rateCompare.model.THotelRateListing;
import com.xyp.rateCompare.util.AvailRQConditionUtil;
import com.xyp.rateCompare.util.DateTimeUtils;
import com.xyp.rateCompare.util.GoogleRateCheckUtil;
import com.xyp.readCVS.model.PriceAccuracyCVS;
import com.xyp.readCVS.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by DT288 on 2016/9/9.
 */


public class CompareService {

    GoogleCheckServiceImpl googleRateCheckService = new GoogleCheckServiceImpl();

    private final String DISABLED_HTE_RATE_ACCOUNT = "TRUST,HELLOWORLD";

    private static final SimpleDateFormat dataformat = new SimpleDateFormat("yyyy-MM-dd");

    private CompareDataDTO translateToCompareDataDto(PriceAccuracyCVS.PriceAccuracyDTO pricedto) {
        CompareDataDTO compareDataDTO = new CompareDataDTO();

        compareDataDTO.setHotelCode(pricedto.getHotel());
        compareDataDTO.setCheckIn(pricedto.getCheck_in_date());
        try {
            compareDataDTO.setCheckOut(getCheckData(pricedto.getCheck_in_date(), pricedto.getLength_of_stay()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        compareDataDTO.setRooms("1");
        compareDataDTO.setGuest("2");

        return compareDataDTO;

    }

    private String getCheckData(String checkin, String stayDay) throws Exception {
        Date date = dataformat.parse(checkin);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, Integer.valueOf(stayDay));
        return dataformat.format(cal.getTime());
    }

    public CompareResultDto getDStorageRate(PriceAccuracyCVS.PriceAccuracyDTO dto) throws Exception {
        String accountCode = "HILTON";
        AvailRQCondition availRQCondition = AvailRQConditionUtil.getAvailRQConditionByAccount(translateToCompareDataDto(dto), accountCode);
        if (availRQCondition == null) {
            return null;
        }
        Map<String, Object> result = new HashMap<String, Object>();
        GoogleHiltonCheckService googleHiltonCheckService = googleRateCheckService.getGoogleCheckServiceByAccount(accountCode);
        Transaction.Result dstorageRate = googleHiltonCheckService.getDStorageRate(availRQCondition);
        Transaction.Result hteRate = null;
        List<GoogleRateCheckDto> hteList = null;
        if (DISABLED_HTE_RATE_ACCOUNT.indexOf(accountCode) == -1) {
            hteRate = googleHiltonCheckService.getHteRate(availRQCondition);
            result.put("hteJSON", JsonUtil.toJson(hteRate));
            hteList = GoogleRateCheckUtil.getRoomRateFromGoogleApi(hteRate);
        } else {
            THotelRateListing tHotelRateListing = GoogleRateCheckUtil.getSearchFeedHTERate(availRQCondition);
            result.put("hteJSON", JsonUtil.toJson(tHotelRateListing));
            hteList = GoogleRateCheckUtil.getRoomRateFromSearchFeed(tHotelRateListing);
        }
        Transaction.Result adapterRate = googleHiltonCheckService.getGoogleAdapterRate(availRQCondition);
        List<GoogleRateCheckDto> dstorageList = GoogleRateCheckUtil.getRoomRateFromGoogleApi(dstorageRate);

        GoogleRateCheckDto dstorage = Collections.min(dstorageList, new Comparator<GoogleRateCheckDto>() {
            public int compare(GoogleRateCheckDto o1, GoogleRateCheckDto o2) {
                double b1 = o1.getBaseRate().doubleValue();
                double b2 = o2.getBaseRate().doubleValue();
                if (b1 < b2) {
                    return -1;
                }
                return 1;
            }
        });
        GoogleRateCheckDto hte = Collections.min(hteList, new Comparator<GoogleRateCheckDto>() {
            public int compare(GoogleRateCheckDto o1, GoogleRateCheckDto o2) {
                double b1 = o1.getBaseRate().doubleValue();
                double b2 = o2.getBaseRate().doubleValue();
                if (b1 < b2) {
                    return -1;
                }
                return 1;
            }
        });
        List<GoogleRateCheckDto> adapterList = GoogleRateCheckUtil.getAdapterFromGoogleApi(adapterRate);

        GoogleRateCheckDto adapter = Collections.min(adapterList, new Comparator<GoogleRateCheckDto>() {
            public int compare(GoogleRateCheckDto o1, GoogleRateCheckDto o2) {
                double b1 = o1.getBaseRate().doubleValue();
                double b2 = o2.getBaseRate().doubleValue();
                if (b1 < b2) {
                    return -1;
                }
                return 1;
            }
        });

        CompareResultDto compareResultDto = new CompareResultDto(dstorage, hte, adapter);

        return compareResultDto;
    }
}
