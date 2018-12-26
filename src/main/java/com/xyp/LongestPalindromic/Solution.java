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
        String s = "ccc";
        System.out.print(longestPalindrome(s));
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
            for (int i = 0; i < tempList.size(); i++) {

                for (int j = i; j < tempList.size(); j++) {

                    if (!(j - 1 < 0)) {
                        int length = tempList.get(j) - tempList.get(j - 1) + 1;
                        StringBuilder sb = new StringBuilder();
                        if (length % 2 == 0) {
                            String subStringLeft = s.substring(tempList.get(j - 1), tempList.get(j - 1) + length / 2);
                            sb.append(subStringLeft);
                            sb.append(new StringBuffer(subStringLeft).reverse());
                        } else {
                            String subStringLeft = s.substring(tempList.get(j - 1), tempList.get(j - 1) + length / 2);
                            sb.append(subStringLeft);
                            sb.append(s.charAt(tempList.get(j - 1) + 1));
                            sb.append(new StringBuffer(subStringLeft).reverse());
                        }
                        if (s.contains(sb.toString()) && sb.toString().length() > temp.length()) {
                            temp = sb.toString();
                        }
                    } else {
                        if (temp.length() == 0) {
                            temp = String.valueOf(s.charAt(tempList.get(j)));
                        }
                    }

                    
                }


            }
        }
        return temp;
    }


}
