package com.xyp;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by xyp on 19/1/14.
 */
public class ReverseString {

    public static String reverseString(String s) {
        char[] charlist = s.toCharArray();

        StringBuilder sb = new StringBuilder();

        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }


        return sb.toString();
    }

    public static String reverseWords(String s) {
        int left = 0;
        int right = 0;

        char[] c = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 32 || i == s.length() - 1) {
                right = i - 1;
                if (i == s.length() - 1) {
                    right = i;
                }
                while (left < right) {
                    char temp = c[left];
                    c[left] = c[right];
                    c[right] = temp;
                    left++;
                    right--;
                }

                left = i + 1;
            }
        }

        return new String(c);
    }

    //采取左右一半。只需要O(n/2)
    public static String reverseString1(String s) {
        char[] c = s.toCharArray();

        int left = 0;
        int right = c.length - 1;

        while (left < right) {
            char temp = c[left];
            c[left] = c[right];
            c[right] = temp;
            left++;
            right--;
        }

        return new String(c);
    }

    public static void main(String[] args) {
        reverseWords("Let's take LeetCode contest");
        System.out.print("ads");
    }
}
