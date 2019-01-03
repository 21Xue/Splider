package com.xyp.zigzag;

/**
 * Created by xyp on 19/1/2.
 */
public class Solution {


// 可以通过这张图得到第一行和最后一行两个字符之间间隔了 2 * (nRows - 1) 个字符。
// 其他的行每两个字符相隔 2 * (nRows - 1) - 2 * i，2 * i（ i 为 0-base 的行号）。
// 另外还需要注意的就是 nRows 为 1 时，直接返回给出的字符串就行了。

    public static String convert(String s, int numRows) {
        if (numRows == 1) return s;

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    ret.append(s.charAt(j + cycleLen - i));
            }
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        String temp = "1234567890ABCDEFGHIJK";

        convert(temp, 4);

    }

}
