package com.xyp.readCVS;

import com.xyp.readCVS.core.ReadCVSService;
import com.xyp.readCVS.model.PriceAccuracyCVS;
import com.xyp.readCVS.util.CSVtoJSONUtil;
import com.xyp.readCVS.util.JsonUtil;
import com.xyp.spider.bean.LinkTypeData;
import com.xyp.spider.core.ExtractService;
import com.xyp.spider.rule.Rule;
import org.junit.Test;

import java.util.List;

/**
 * Created by DT288 on 2016/9/8.
 */
public class test {

    @Test
    public void ReadCVS() {
        try {
            ReadCVSService r = new ReadCVSService("E:\\Code\\Splider\\test.csv");
            String s = CSVtoJSONUtil.CSVtoJSON(r.readLine());
            PriceAccuracyCVS data = JsonUtil.fromJson(s, PriceAccuracyCVS.class);
            System.out.printf(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
