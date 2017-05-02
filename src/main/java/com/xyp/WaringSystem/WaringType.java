package com.xyp.WaringSystem;

/**
 * Created by DT288 on 2016/9/9.
 */
public enum WaringType {

    TimeaPast("TimeaPast"),


    Google_error("Google_error"),

    fetched_priceVSwebDriver("Fetched price diff with WebDriver"),

    hteNoPrice("hteNoPrice"),

    DstorgeNoPrice("DstorgeNoPrice"),

    GoogleAdapterNoPrice("GoogleAdapterNoPrice"),

    CacheLackPrice("CacheLackPrice"),

    TaxDifferent("TaxDifferent"),

    HotelDeletePrice("HotelDeletePrice"),

    HotelChangePrice("HotelChangePrice"),

    hteDiffWithWeb("hteDiffWithWeb"),

    CacheNoPrice("CacheNoPrice"),

    GetPriceDelay("GetPriceDelay"),

    AlreadyRight("AlreadyRight");

    private String errorMessage;

    private Integer value;

    private WaringType(String type) {
        this.errorMessage = type;
    }

    public String getTypeString() {
        return this.errorMessage;
    }
}
