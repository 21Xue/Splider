package com.xyp;

/**
 * Created by xyp on 19/1/8.
 */
public class PowerOfTwo {

    public static boolean isPowerOfTwo(int n) {

        if (n == 0 || n < 0) {
            return false;
        }
        boolean flag = true;
        while (n >> 1 != 0) {
            if (n % 2 != 0) {
                flag = false;
                break;
            }
            n = n >> 1;
        }

        return flag;
    }


    public static boolean isPowerOfTwo1(int n) {

        if (n == 0) {
            return false;
        }
        if (n < 0) {
            n = 0 - n;
        }

        int maxNumber = Integer.MAX_VALUE;
        boolean flag = true;
        flag = (maxNumber & n) == maxNumber;

        return flag;
    }

    public static void main(String[] args) {
        System.out.print(isPowerOfTwo1(16));

    }
}
