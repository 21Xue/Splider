package com.xyp.WaringSystem;

/**
 * Created by DT288 on 2016/9/9.
 */
public enum WaringType {

    cached_priceVSgoogle_adaper("Cache price diff with Google adaper"),

    cached_taxVSgoogle_adaper("Cache tax diff with google adaper"),

    fetched_priceVSwebDriver("Fetched price diff with WebDriver"),

    fetched_taxVSwebDriver("Fetched tax diff with webDriver"),

    Operation_is_Same("Operation is same"),

    google_adaperVShte("google adaper diff with hte"),

    google_adaperVSdstorage("google adaper diff with dstorage"),

    dstorageVShte("dstorage diff with hte"),

    fetched_priceVSOperation_Same("fetch price diff with Operation"),

    webDriverVSOperation_Same("WebDriver diff with Operation"),

    hteSameWithWebDriverOrFetchPrice("hteSameWithWebDriverOrFetchPrice");

    //    hte == webDriver || hte == fetchedPrice
    private String errorMessage;

    private Integer value;

    private WaringType(String type) {
        this.errorMessage = type;
    }

    public String getTypeString() {
        return this.errorMessage;
    }
}
