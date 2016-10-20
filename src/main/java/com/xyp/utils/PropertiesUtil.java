package com.xyp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by DT288 on 2016/7/19.
 */
public class PropertiesUtil {

    public static String getNewPath() {
        return NEW_PATH;
    }

    public static String getOldPath() {
        return OLD_PATH;
    }

    private static String OLD_PATH;

    private static String NEW_PATH;


    static {
        Properties prop = new Properties();
        InputStream in = com.derbysoft.dplatform.google.protocol.util.PropertiesUtil.class.getResourceAsStream("/filePath.properties");
        try {
            prop.load(in);
            OLD_PATH = prop.getProperty("old_path").trim();

            NEW_PATH = prop.getProperty("new_path").trim();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
