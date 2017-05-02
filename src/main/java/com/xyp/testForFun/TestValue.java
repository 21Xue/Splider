package com.xyp.testForFun;

/**
 * Created by DT288 on 2017/2/24.
 */
public class TestValue {

    private String testPrint;

    public TestValue(String message) {
        this.testPrint = message;
    }

    public void print() {
        System.out.print(testPrint);
    }
}
