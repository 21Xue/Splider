package com.xyp.rateCompare.model;

import java.util.List;

/**
 * Created by DT288 on 2016/9/9.
 */
public class CompareResultDto {

    public CompareResultDto(List<GoogleRateCheckDto> d, List<GoogleRateCheckDto> h, List<GoogleRateCheckDto> a) {
        this.dstorage = d;
        this.hte = h;
        this.adapter = a;
    }


    public List<GoogleRateCheckDto> getDstorageList() {
        return dstorage;
    }

    public void setDstorageList(List<GoogleRateCheckDto> dstorageList) {
        this.dstorage = dstorageList;
    }

    public List<GoogleRateCheckDto> getHteList() {
        return hte;
    }

    public void setHteList(List<GoogleRateCheckDto> hteList) {
        this.hte = hteList;
    }

    public List<GoogleRateCheckDto> getAdapterList() {
        return adapter;
    }

    public void setAdapterList(List<GoogleRateCheckDto> adapterList) {
        this.adapter = adapterList;
    }

    List<GoogleRateCheckDto> dstorage;

    List<GoogleRateCheckDto> hte;

    List<GoogleRateCheckDto> adapter;

}
