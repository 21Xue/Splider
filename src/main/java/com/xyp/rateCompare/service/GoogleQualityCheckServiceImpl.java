package com.xyp.rateCompare.service;

import com.derbysoft.dplatform.google.protocol.dto.AvailRQCondition;
import com.derbysoft.dplatform.google.protocol.dto.transaction.Transaction;
import com.derbysoft.dplatform.google.protocol.service.GoogleHiltonCheckService;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

public class GoogleQualityCheckServiceImpl implements GoogleHiltonCheckService {

    String serviceUrl;

    GoogleHiltonCheckService googleHiltonCheckService;

    public GoogleQualityCheckServiceImpl() {

    }

    public GoogleQualityCheckServiceImpl(String serviceUrl) {
        setServiceUrl(serviceUrl);
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;

        HttpInvokerProxyFactoryBean factoryBean = new HttpInvokerProxyFactoryBean();
        factoryBean.setServiceUrl(serviceUrl);
        factoryBean.setServiceInterface(GoogleHiltonCheckService.class);
        factoryBean.afterPropertiesSet();
        googleHiltonCheckService = (GoogleHiltonCheckService) factoryBean.getObject();

    }

    public Transaction.Result getGoogleAdapterRate(AvailRQCondition availRQCondition) {
        return googleHiltonCheckService.getGoogleAdapterRate(availRQCondition);
    }

    public Transaction.Result getDStorageRate(AvailRQCondition availRQCondition) {
        return googleHiltonCheckService.getDStorageRate(availRQCondition);
    }

    public Transaction.Result getPluginRate(AvailRQCondition availRQCondition) {
        return googleHiltonCheckService.getPluginRate(availRQCondition);
    }

    public Transaction.Result getHteRate(AvailRQCondition availRQCondition) {
        return googleHiltonCheckService.getHteRate(availRQCondition);
    }
}
