package com.xyp.database.service;

import com.xyp.rateCompare.util.DateTimeUtils;
import com.xyp.readCVS.core.ReadCVSService;
import com.xyp.readCVS.model.PriceAccuracyCVS;
import com.xyp.readCVS.util.CSVtoJSONUtil;
import com.xyp.readCVS.util.JsonUtil;
import com.xyp.utils.PropertiesUtil;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by DT288 on 2016/10/13.
 */

public class DataServices {

    public JdbcTemplate getJt() {
        return jt;
    }

    public void setJt(JdbcTemplate jt) {
        this.jt = jt;
    }

    public static JdbcTemplate jt;

    public boolean updateCustomer(String sql, Object[] params) {
        try {
            jt.update(sql, params);
        } catch (DataAccessException e) {
            return false;
        }
        return true;
    }

    public void readGoogleCSVFile() {
        File file = new File(PropertiesUtil.getNewPath());
        File[] tempList = file.listFiles();
        String sql = "";
        for (File temp : tempList) {
            PriceAccuracyCVS data = null;
            try {
                ReadCVSService r = new ReadCVSService(temp);
                String s = CSVtoJSONUtil.CSVtoJSON(r.readLine());
                data = JsonUtil.fromJson(s, PriceAccuracyCVS.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (PriceAccuracyCVS.PriceAccuracyDTO priceAccuracyDTO : data.getResult()) {
                try {
                    sql = new StringBuffer("insert into google_file (partner, hotel, checkin,  los, fetchprice, fetchtax, fetchcurrency,fetchtime,cacheprice,cachetax,cachecurrency,cachetime,url,filename)")
                            .append(" values('").append(priceAccuracyDTO.getPartner()).append("',")
                            .append("'").append(priceAccuracyDTO.getHotel()).append("',")
                            .append("'").append(DateTimeUtils.formatDate(DateTimeUtils.parse(priceAccuracyDTO.getCheck_in_date()))).append("',")
                            .append("'").append(priceAccuracyDTO.getLength_of_stay()).append("',")
                            .append("'").append(priceAccuracyDTO.getFetched_price()).append("',")
                            .append("'").append(priceAccuracyDTO.getFetched_tax()).append("',")
                            .append("'").append(priceAccuracyDTO.getFetched_currency()).append("',")
                            .append("'").append(DateTimeUtils.formatDateTime(DateTimeUtils.parse(priceAccuracyDTO.getFetched_time(), DateTimeUtils.FULL_DATE_TIME_FORMAT2), DateTimeUtils.FULL_DATE_TIME_FORMAT)).append("',")
                            .append("'").append(priceAccuracyDTO.getCached_price()).append("',")
                            .append("'").append(priceAccuracyDTO.getCached_tax()).append("',")
                            .append("'").append(priceAccuracyDTO.getCached_currency()).append("',")
                            .append("'").append(DateTimeUtils.formatDateTime(DateTimeUtils.parse(priceAccuracyDTO.getCached_time(), DateTimeUtils.FULL_DATE_TIME_FORMAT2), DateTimeUtils.FULL_DATE_TIME_FORMAT)).append("',")
                            .append("'").append(priceAccuracyDTO.getUrl()).append("',")
                            .append("'").append(temp.getName()).append("'")
                            .append(" ) ")
                            .toString();
                    jt.execute(sql.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            moveFile(temp);
            temp.delete();
        }
    }

    public void moveFile(File newFile) {
        try {
            int byteSum = 0;
            int byteRead = 0;
            if (newFile.exists()) { //文件不存在时
                InputStream inStream = new FileInputStream(newFile); //读入原文件
                FileOutputStream fs = new FileOutputStream(PropertiesUtil.getOldPath() + "//" + newFile.getName());
                byte[] buffer = new byte[1024];
                int length;
                while ((byteRead = inStream.read(buffer)) != -1) {
                    byteSum += byteRead; //字节数 文件大小
                    fs.write(buffer, 0, byteRead);
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错:" + newFile.getPath());
            e.printStackTrace();
        }

    }

}
