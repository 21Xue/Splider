package com.xyp.readCVS.core;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by DT288 on 2016/9/8.
 */
public class ReadCVSService {

    // CSV文件编码
    public static final String ENCODE = "UTF-8";

    private FileInputStream fis = null;
    private InputStreamReader isw = null;
    private BufferedReader br = null;


    public ReadCVSService(String filename) throws Exception {
        fis = new FileInputStream(filename);
        isw = new InputStreamReader(fis, ENCODE);
        br = new BufferedReader(isw);
    }

    // ==========以下是公开方法=============================

    /**
     * 从CSV文件流中读取一个CSV行。
     *
     * @throws Exception
     */
    public String readLine() throws Exception {

        StringBuilder sb = new StringBuilder();
        String line = "";
        while ((line = br.readLine()) != null) {
            if (sb.length() > 0) {
                sb.append("\r\n");
            }
            sb.append(line);
        }
        return sb.toString();
    }
}
