package com.xyp.ReverseInteger;

import java.util.*;

/**
 * Created by xyp on 19/1/3.
 */
public class Solution {

    public static int reverse(int x) {
        boolean isnegative = false;
        if (x < 0) {
            isnegative = true;
            x = 0 - x;
        }

        String stringx = String.valueOf(x);
        StringBuilder sb = new StringBuilder(stringx);
        String newx = sb.reverse().toString();

        try {
            int newnumber = Integer.valueOf(newx);
            if (isnegative) {
                newnumber = 0 - newnumber;
            }
            return newnumber;
        } catch (Exception e) {
            return 0;
        }
    }

    public int reverse2(int x) {
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x = x / 10;
        }
        return (int) res == res ? (int) res : 0;
    }

    public static void main(String[] args) {
        System.out.print(reverse(1147483419));
    }
}
