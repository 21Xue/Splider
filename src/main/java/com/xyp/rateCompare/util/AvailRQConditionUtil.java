package com.xyp.rateCompare.util;

import com.derbysoft.dplatform.google.protocol.dto.AvailRQCondition;
import com.xyp.rateCompare.model.CompareDataDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Created by DT288 on 2016/9/9.
 */
public class AvailRQConditionUtil {

    public static AvailRQCondition getAvailRQConditionByAccount(CompareDataDTO compareDataDTO, String accountCode) {
        AvailRQCondition availRQCondition = null;
        if (StringUtils.isNotBlank(accountCode)) {
            if (accountCode.equals("HILTON")) {
                availRQCondition = translateRequestToRQCondition(compareDataDTO, "google-hilton", "hiltonus", "HILTON");
            } else if (accountCode.equals("MAR")) {
                availRQCondition = translateRequestToRQCondition(compareDataDTO, "marriott-google", "marriott", "MAR");
            } else if (accountCode.startsWith("HYATT")) {
                availRQCondition = translateRequestToRQCondition(compareDataDTO, "hyatt-google", "hyatt", "HYATT");
            } else if (accountCode.startsWith("CARLSON")) {
                availRQCondition = translateRequestToRQCondition(compareDataDTO, "carlson-google", "carlson", "CARLSON");
            } else if (accountCode.startsWith("TRUST")) {
                availRQCondition = translateRequestToRQCondition(compareDataDTO, "trust-google", "trust", "TRUST");
            } else if (accountCode.startsWith("SYNXIS")) {
                availRQCondition = translateRequestToRQCondition(compareDataDTO, "dh-google", "synxis", "");
            } else if (accountCode.startsWith("HELLOWORLD")) {
                availRQCondition = translateRequestToRQCondition(compareDataDTO, "helloworld-google", "helloworld", "HELLOWORLD");
            } else if (accountCode.startsWith("OMNI")) {
                availRQCondition = translateRequestToRQCondition(compareDataDTO, "omni-google", "omni", "OMNI");
            } else if (accountCode.startsWith("FAIRMONT")) {
                availRQCondition = translateRequestToRQCondition(compareDataDTO, "dh-google", "fairmont", "FAIRMONT");
            } else if (accountCode.startsWith("2P-SANDMAN")) {
                availRQCondition = translateRequestToRQCondition(compareDataDTO, "dh-google", "2p", "SANDMAN");
            } else if (accountCode.startsWith("2PVENTURES")) {
                availRQCondition = translateRequestToRQCondition(compareDataDTO, "dh-google", "2pventures", "2P");
            } else if (accountCode.startsWith("KERZNER")) {
                availRQCondition = translateRequestToRQCondition(compareDataDTO, "dh-google", "kerzner", "KERZNER");
            } else if (accountCode.startsWith("CAESARS")) {
                availRQCondition = translateRequestToRQCondition(compareDataDTO, "dh-google", "caesars", "CAESARS");
            }
        }
        return availRQCondition;
    }

    public static AvailRQCondition translateRequestToRQCondition(CompareDataDTO compareDataDTO, String defaultChannel, String defaultAccount, String prefixHotelCode) {
        AvailRQCondition availRQCondition = new AvailRQCondition();
        String tempStr = compareDataDTO.getHotelCode();
        String hotelCode = (org.apache.commons.lang.StringUtils.isNotBlank(tempStr) ? (tempStr.indexOf("-") > -1 ? tempStr.trim() : prefixHotelCode + "-" + tempStr.trim()) : "");
        availRQCondition.setHotelCode(hotelCode);
        tempStr = compareDataDTO.getCheckIn();
        availRQCondition.setCheckInDate(org.apache.commons.lang.StringUtils.isNotBlank(tempStr) ? DateTimeUtils.parse(tempStr, "yyyy-MM-dd") : null);
        tempStr = compareDataDTO.getCheckOut();
        availRQCondition.setCheckOutDate(org.apache.commons.lang.StringUtils.isNotBlank(tempStr) ? DateTimeUtils.parse(tempStr, "yyyy-MM-dd") : null);
        tempStr = compareDataDTO.getRooms();
        availRQCondition.setRooms(org.apache.commons.lang.StringUtils.isNotBlank(tempStr) && tempStr.matches("[1-9][0-9]*") ? Integer.parseInt(tempStr) : Integer.valueOf(1));
        tempStr = compareDataDTO.getGuest();
        availRQCondition.setGuest(org.apache.commons.lang.StringUtils.isNotBlank(tempStr) && tempStr.matches("[1-9][0-9]*") ? Integer.parseInt(tempStr) : Integer.valueOf(2));
        tempStr = compareDataDTO.getChannelCode();
        availRQCondition.setChannelCode(org.apache.commons.lang.StringUtils.isNotBlank(tempStr) ? tempStr : defaultChannel);
        tempStr = compareDataDTO.getAccountCode();
        availRQCondition.setAccountCode(org.apache.commons.lang.StringUtils.isNotBlank(tempStr) ? tempStr : defaultAccount);
        return availRQCondition;
    }

}
