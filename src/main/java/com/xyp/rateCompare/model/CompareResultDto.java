package com.xyp.rateCompare.model;

import java.util.List;

/**
 * Created by DT288 on 2016/9/9.
 */
public class CompareResultDto {

    public CompareResultDto(GoogleRateCheckDto d, GoogleRateCheckDto h, GoogleRateCheckDto a) {
        this.dstorage = d;
        this.hte = h;
        this.adapter = a;
    }


    public GoogleRateCheckDto getDstorageList() {
        return dstorage;
    }

    public void setDstorageList(GoogleRateCheckDto dstorageList) {
        this.dstorage = dstorageList;
    }

    public GoogleRateCheckDto getHteList() {
        return hte;
    }

    public void setHteList(GoogleRateCheckDto hteList) {
        this.hte = hteList;
    }

    public GoogleRateCheckDto getAdapterList() {
        return adapter;
    }

    public void setAdapterList(GoogleRateCheckDto adapterList) {
        this.adapter = adapterList;
    }

    GoogleRateCheckDto dstorage;

    GoogleRateCheckDto hte;

    GoogleRateCheckDto adapter;

}
