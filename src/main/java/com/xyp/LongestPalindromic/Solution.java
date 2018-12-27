package com.xyp.LongestPalindromic;


import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xyp on 18/12/25.
 */
public class Solution {

    public static void main(String[] args) {
        String s = "";

        long time1 = System.currentTimeMillis();

        System.out.println(longestPalindrome(s));

        System.out.println(System.currentTimeMillis() - time1);
    }


    public static String longestPalindrome1(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    public static String longestPalindrome(String s) {
        if (s.isEmpty() || s.length() == 1) {
            return s;
        }

        Map<Character, List<Integer>> charMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);

            if (charMap.get(temp) != null) {
                charMap.get(temp).add(i);
            } else {
                List<Integer> intList = new ArrayList<>();
                intList.add(i);
                charMap.put(temp, intList);
            }
        }
        String temp = "";
        for (Character c : charMap.keySet()) {
            List<Integer> tempList = charMap.get(c);
            for (int i = tempList.size(); i > 0; i--) {

                if (tempList.get(i - 1) > temp.length()) {

                    for (int j = 0; j < i; j++) {

                        int length = tempList.get(i - 1) - tempList.get(j) + 1;

                        StringBuilder sb = new StringBuilder();
                        String subStringLeft = s.substring(tempList.get(j), tempList.get(j) + length / 2);
                        sb.append(subStringLeft);
                        if (length % 2 != 0) {
                            sb.append(s.charAt(tempList.get(j) + length / 2));
                        }
                        sb.append(new StringBuffer(subStringLeft).reverse());
                        if (s.contains(sb.toString()) && sb.toString().length() > temp.length()) {
                            temp = sb.toString();
                        }
                    }

                }
            }
        }
        return temp;
    }


}
