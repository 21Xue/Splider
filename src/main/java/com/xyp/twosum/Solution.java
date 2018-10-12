package com.xyp.twosum;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xyp on 18/10/11.
 */
public class Solution {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numberMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            numberMap.put(nums[i], i);
        }


        for (int i = 0; i < nums.length; i++) {
            if (numberMap.get((target - nums[i])) != null && i != numberMap.get(target - nums[i])) {
                return new int[]{i, numberMap.get((target - nums[i]))};
            }
        }


        return nums;
    }

}
