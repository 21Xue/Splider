package com.xyp;

import java.util.*;

public class Subsets {

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();

        for (int index = 0; index < 2 << nums.length - 1; index++) {
            List<Integer> subSet = new ArrayList<>();
            String subString = Integer.toBinaryString(index);
            String newSubString=subString;
            if (subString.length() < nums.length) {
                for (int i = 0; i < nums.length - subString.length(); i++) {
                    newSubString = "0" + newSubString;
                }
            }
            for (int i = 0; i < newSubString.length(); i++) {
                if (newSubString.charAt(i) != '0') {
                    subSet.add(nums[i]);
                }
            }
            subsets.add(subSet);
        }


        return subsets;

    }

    public static class test {

        private int number;

        public test(int num) {
            this.number = num;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return true;
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> list = subsets(new int[]{1, 2, 3});

        System.out.print("asd");
    }


}
