package com.xyp;

import com.xyp.rateCompare.model.CompareResultDto;
import com.xyp.readCVS.model.PriceAccuracyCVS;
import com.xyp.webDriver.model.WebDriverFromHilton;

/**
 * Created by DT288 on 2016/9/9.
 */


public class FunDTO {

    public FunDTO(PriceAccuracyCVS.PriceAccuracyDTO p, CompareResultDto c, WebDriverFromHilton w) {
        this.priceAccuracyDTO = p;
        this.compareResultDto = c;
        this.webDriverFromHilton = w;
    }

    private PriceAccuracyCVS.PriceAccuracyDTO priceAccuracyDTO;

    private CompareResultDto compareResultDto;

    public WebDriverFromHilton getWebDriverFromHilton() {
        return webDriverFromHilton;
    }

    public void setWebDriverFromHilton(WebDriverFromHilton webDriverFromHilton) {
        this.webDriverFromHilton = webDriverFromHilton;
    }

    public PriceAccuracyCVS.PriceAccuracyDTO getPriceAccuracyDTO() {
        return priceAccuracyDTO;
    }

    public void setPriceAccuracyDTO(PriceAccuracyCVS.PriceAccuracyDTO priceAccuracyDTO) {
        this.priceAccuracyDTO = priceAccuracyDTO;
    }

    public CompareResultDto getCompareResultDto() {
        return compareResultDto;
    }

    public void setCompareResultDto(CompareResultDto compareResultDto) {
        this.compareResultDto = compareResultDto;
    }

    private WebDriverFromHilton webDriverFromHilton;
}

