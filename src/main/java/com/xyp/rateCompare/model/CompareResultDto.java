package com.xyp.rateCompare.model;

import java.util.List;

/**
 * Created by DT288 on 2016/9/9.
 */
public class CompareResultDto {

    public CompareResultDto(List<GoogleRateCheckDto> d, List<GoogleRateCheckDto> h, List<GoogleRateCheckDto> a) {
        this.dstorageList = d;
        this.hteList = h;
        this.adapterList = a;
    }


    public List<GoogleRateCheckDto> getDstorageList() {
        return dstorageList;
    }

    public void setDstorageList(List<GoogleRateCheckDto> dstorageList) {
        this.dstorageList = dstorageList;
    }

    public List<GoogleRateCheckDto> getHteList() {
        return hteList;
    }

    public void setHteList(List<GoogleRateCheckDto> hteList) {
        this.hteList = hteList;
    }

    public List<GoogleRateCheckDto> getAdapterList() {
        return adapterList;
    }

    public void setAdapterList(List<GoogleRateCheckDto> adapterList) {
        this.adapterList = adapterList;
    }

    List<GoogleRateCheckDto> dstorageList;

    List<GoogleRateCheckDto> hteList;

    List<GoogleRateCheckDto> adapterList;

}
