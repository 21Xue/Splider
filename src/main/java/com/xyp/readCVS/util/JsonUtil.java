package com.xyp.readCVS.util;

/**
 * Created by DT288 on 2016/7/19.
 */

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class JsonUtil {

    private static Gson gson = new Gson();

    public static String toJson(Object obj){
        String json = gson.toJson(obj);
        return json;
    }
    public static <T> T fromJson(String json, Class<T> classOfT){
        T t = gson.fromJson(json, classOfT);

        return t;
    }

    public static <T> T fromJson(String json, Type typeOfT){
        T t = gson.fromJson(json, typeOfT);
        return t;
    }

}

