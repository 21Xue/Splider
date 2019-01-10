package com.xyp;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

/**
 * Created by xyp on 19/1/9.
 */
public class ThreeNumberSum {


    //1个偶数 3个偶数
    //0个奇数 2个奇数



    public static List<List<Integer>> threeSum(int[] nums) {

        Map<Integer, List<Integer>> threeSum = new HashMap<>();

        List<List<Integer>> threeSumList = new ArrayList<>();

        if (nums.length < 3) {
            return threeSumList;
        }

        for (int i = 0; i < nums.length; i++) {
            if (i < nums.length) {
                for (int j = i + 1; j < nums.length; j++) {
                    getThreeSum(threeSum, nums, i, j);
                }
            }
        }

        for (List<Integer> temp : threeSum.values()) {
            threeSumList.add(temp);
        }
        return threeSumList;

    }

    private static void getThreeSum(Map<Integer, List<Integer>> threeSums, int[] nums, int first, int second) {
        if (second < nums.length) {
            for (int i = second + 1; i < nums.length; i++) {
                if (nums[first] + nums[second] + nums[i] == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[i]);
                    Collections.sort(list);
                    threeSums.put(list.toString().hashCode(), list);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {0, 0};
        List<List<Integer>> test = threeSum(nums);
        System.out.printf("asd");
    }
}
