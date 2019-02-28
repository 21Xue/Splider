package com.xyp.zigzag;

/**
 * Created by xyp on 19/1/14.
 */
public class CanWinNim {
    public static boolean canWinNim(int n) {


        boolean flag = n % 4 == 0 ? true : false;


        return !flag;

    }

    public static void main(String[] args) {
        System.out.print(canWinNim(5));
    }
}
