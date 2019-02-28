package com.xyp.zigzag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xyp on 19/1/8.
 */
public class Permutations {

    private static void permuteHelper(int[] nums, int i, List<List<Integer>> list) {
        if (i == nums.length - 1) {
            List<Integer> subList = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                subList.add(nums[j]);
            }
            list.add(subList);
        } else {
            for (int j = i; j < nums.length; j++) {
                swap(nums, i, j);
                permuteHelper(nums, i + 1, list);
                swap(nums, i, j);
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        permuteHelper(nums, 0, res);
        return res;
    }
//    public static List<List<Integer>> permute(int[] nums) {
//
//        List<List<Integer>> permuteLists = permute(nums, new ArrayList<>());
//        return permuteLists;
//    }
//
//    public static List<List<Integer>> permute(int[] nums, List<Integer> permuteList) {
//        List<List<Integer>> permuteLists = new ArrayList<>();
//
//        for (Integer num : nums) {
//            List<Integer> copypermuteList = new ArrayList<>(permuteList.size());
//            copypermuteList.addAll(permuteList);
//            copypermuteList.add(num);
//            int[] newNumber = new int[nums.length - 1];
//            int i = 0;
//            for (Integer tempNum : nums) {
//                if (tempNum != num) {
//                    newNumber[i] = tempNum;
//                    i++;
//                }
//            }
//            if (newNumber.length != 0) {
//                for (List<Integer> tempList : permute(newNumber, copypermuteList)) {
//                    permuteLists.add(tempList);
//                }
//            } else {
//                permuteLists.add(copypermuteList);
//            }
//        }
//        return permuteLists;
//    }

    public static void main(String[] args) {
        List<List<Integer>> nihao = permute(new int[]{1, 2, 3, 4});
        System.out.print(nihao);
    }

}
