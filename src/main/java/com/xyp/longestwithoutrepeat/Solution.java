package com.xyp.longestwithoutrepeat;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by xyp on 18/12/11.
 */
public class Solution {


    public static int lengthOfLongestSubstring(String s) {

        if (s.isEmpty()) {
            return 0;
        }
        int num = 0;
        BlockingQueue<Character> norepeatList = new ArrayBlockingQueue<Character>(10);
        for (Character c : s.toCharArray()) {
            if (!norepeatList.contains(c)) {
                norepeatList.add(c);
            } else {
                if (norepeatList.size() > num) {
                    num = norepeatList.size();
                }
                while (c != norepeatList.poll()) {
                }
                norepeatList.add(c);
            }

        }
        if (norepeatList.size() > num) {
            num = norepeatList.size();
        }
        return num;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.print(lengthOfLongestSubstring(s));
    }

}
