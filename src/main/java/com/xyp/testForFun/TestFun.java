package com.xyp.testForFun;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DT288 on 2017/2/24.
 */
public class TestFun {
    public static void main(String[] args) {
        TestKey testKey1 = new TestKey("Key1");
        TestKey testKey2 = new TestKey("Key2");

        TestValue testValue1 = new TestValue("Value1");
        TestValue testValue2 = new TestValue("Value2");

        Map<TestKey, TestValue> testMap = new HashMap<TestKey, TestValue>();

        testMap.put(testKey1, testValue1);
        testMap.put(testKey2, testValue2);

        System.out.print("asdasd");
    }
}
