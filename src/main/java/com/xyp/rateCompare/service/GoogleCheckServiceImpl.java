package com.xyp.rateCompare.service;

import com.derbysoft.dplatform.google.protocol.service.GoogleHiltonCheckService;
import org.apache.commons.lang.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: ysy
 * Date: 15-1-23
 * Time: 下午5:15
 * To change this template use File | Settings | File Templates.
 */
public class GoogleCheckServiceImpl {

    public GoogleCheckServiceImpl() {
        createService();
    }

    GoogleHiltonCheckService hyattGoogleCheckService;

    GoogleHiltonCheckService trustGoogleCheckService;

    GoogleHiltonCheckService marriottGoogleCheckService;

    GoogleHiltonCheckService dhGoogleCheckService;

    GoogleHiltonCheckService helloworldGoogleCheckService;

    GoogleHiltonCheckService hiltonCheckService;

    GoogleHiltonCheckService dhGeneralGoogleCheckService;

    public GoogleHiltonCheckService getGoogleCheckServiceByAccount(String accountCode) {
        GoogleHiltonCheckService googleHiltonCheckService = null;
        if (StringUtils.isNotBlank(accountCode)) {
            if (accountCode.equals("HILTON")) {
                googleHiltonCheckService = hiltonCheckService;
            } else if (accountCode.startsWith("HYATT")) {
                googleHiltonCheckService = hyattGoogleCheckService;
            } else if (accountCode.startsWith("CARLSON")) {
                googleHiltonCheckService = dhGoogleCheckService;
            } else if (accountCode.startsWith("TRUST")) {
                googleHiltonCheckService = trustGoogleCheckService;
            } else if (accountCode.startsWith("SYNXIS")) {
                googleHiltonCheckService = dhGoogleCheckService;
            } else if (accountCode.startsWith("HELLOWORLD")) {
                googleHiltonCheckService = helloworldGoogleCheckService;
            } else if (accountCode.startsWith("MAR")) {
                googleHiltonCheckService = marriottGoogleCheckService;
            } else if (accountCode.startsWith("OMNI") ||
                    accountCode.startsWith("FAIRMONT") ||
                    accountCode.startsWith("2P-SANDMAN") ||
                    accountCode.startsWith("2PVENTURES") ||
                    accountCode.startsWith("KERZNER") ||
                    accountCode.startsWith("CAESARS")) {
                googleHiltonCheckService = dhGeneralGoogleCheckService;
            }
        }
        return googleHiltonCheckService;
    }

    public void setHyattGoogleCheckService(GoogleHiltonCheckService hyattGoogleCheckService) {
        this.hyattGoogleCheckService = hyattGoogleCheckService;
    }

    public void setTrustGoogleCheckService(GoogleHiltonCheckService trustGoogleCheckService) {
        this.trustGoogleCheckService = trustGoogleCheckService;
    }

    public void setMarriottGoogleCheckService(GoogleHiltonCheckService marriottGoogleCheckService) {
        this.marriottGoogleCheckService = marriottGoogleCheckService;
    }

    public void setDhGoogleCheckService(GoogleHiltonCheckService dhGoogleCheckService) {
        this.dhGoogleCheckService = dhGoogleCheckService;
    }

    public void setHelloworldGoogleCheckService(GoogleHiltonCheckService helloworldGoogleCheckService) {
        this.helloworldGoogleCheckService = helloworldGoogleCheckService;
    }

    public void setHiltonCheckService(GoogleHiltonCheckService hiltonCheckService) {
        this.hiltonCheckService = hiltonCheckService;
    }

    public void setDhGeneralGoogleCheckService(GoogleHiltonCheckService dhGeneralGoogleCheckService) {
        this.dhGeneralGoogleCheckService = dhGeneralGoogleCheckService;
    }

    private void createService() {
        setHyattGoogleCheckService(new GoogleQualityCheckServiceImpl("http://54.248.246.238/dplatform-google-hyatt/remoting/rateCheck"));
        setTrustGoogleCheckService(new GoogleQualityCheckServiceImpl("http://54.68.113.136/dplatform-google-trust/remoting/rateCheck"));
        setMarriottGoogleCheckService(new GoogleQualityCheckServiceImpl("http://54.68.57.142/dplatform-google-marriott/remoting/rateCheck"));
        setDhGoogleCheckService(new GoogleQualityCheckServiceImpl("http://54.248.216.168/dplatform-google-dh/remoting/rateCheck"));
        setHelloworldGoogleCheckService(new GoogleQualityCheckServiceImpl("http://54.68.55.234/dplatform-google-helloworld/remoting/rateCheck"));
        setHiltonCheckService(new GoogleQualityCheckServiceImpl("http://54.248.122.5/dplatform-google/remoting/rateCheck"));
        setDhGeneralGoogleCheckService(new GoogleQualityCheckServiceImpl("http://54.248.216.168/dplatform-google-dh/remoting/rateCheck"));
    }

}
