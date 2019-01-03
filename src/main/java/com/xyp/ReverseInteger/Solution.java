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

        Map<Integer, Integer> numberMap = new HashMap<>();
        int divide = 10;
        while (x % divide != 0) {
            int temp = x % divide;
            x = x - temp;
            numberMap.put(divide, temp);
            divide = divide * 10;
        }

        List<Integer> bitList = Arrays.asList(numberMap.keySet().toArray(new Integer[]{}));
        Collections.sort(bitList);
        int sum = 0;
        for (int i = 0; i < bitList.size(); i++) {
            int first = bitList.get(bitList.size() - i - 1) / 10;
            int second = numberMap.get(bitList.get(i)) / (bitList.get(i) / 10);

            sum = sum + first * second;
        }
        return sum;
    }

    public static void main(String[] args) {
        reverse(54321);
    }
}
