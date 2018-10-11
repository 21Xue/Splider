package com.xyp.twosum;

/**
 * Created by xyp on 18/10/11.
 */
public class Solution {

    public int[] twoSum(int[] nums, int target) {
        int[] test = {};
        if (nums.length == 0) {
            return test;
        }


        int middle = nums.length / 2;

        int left = 0;

        int right = nums.length - 1;

        while (middle == left || middle == right) {
            if (nums[middle] < target) {
                left = middle;
            } else {
                right = middle;
            }
            middle = (right - left) / 2;
        }


    }

}
