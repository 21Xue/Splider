package com.xyp.testForFun;

/**
 * Created by DT288 on 2017/2/24.
 */
public class TestKey {

    private String flagMessage;

    public TestKey(String message) {
        this.flagMessage = message;
    }

    public String getFlagMessage() {
        return flagMessage;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }
}
