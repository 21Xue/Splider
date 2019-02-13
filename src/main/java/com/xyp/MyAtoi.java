package com.xyp;

import javax.persistence.criteria.CriteriaBuilder;

public class MyAtoi {


    public static void main(String[] args) {
        String temp = "  -0012a42";
        System.out.print(myAtoi(temp));
    }

    public static int myAtoi(String str) {
        boolean negative = false;
        boolean haveflag = false;
        int i = 0;
        int len = str.length();
        long tempResult = 0;
        int result = 0;
        if (len > 0) {
            while (i < len) {
                char firstchar = str.charAt(i);
                if (firstchar < '0') {
                    if ((firstchar == '-' || firstchar == '+') && !haveflag) {
                        haveflag = true;
                        if (firstchar == '-') {
                            negative = true;
                        }
                    } else if (firstchar != ' ' || haveflag) {
                        return result;
                    }
                } else if (firstchar > '9') {
                    return result;
                } else {
                    if (negative) {
                        tempResult = tempResult * 10 - (firstchar - 48);
                        if (tempResult < Integer.MIN_VALUE) {
                            tempResult = Integer.MIN_VALUE;
                        }
                    } else {
                        tempResult = tempResult * 10 + (firstchar - 48);
                        if (tempResult > Integer.MAX_VALUE) {
                            tempResult = Integer.MAX_VALUE;
                        }
                    }
                    result = (int) tempResult;
                }
                i++;
            }
        }
        return result;
    }
}
