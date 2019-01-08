package com.xyp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xyp on 19/1/8.
 */
public class Permutations {

    public static List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> permuteLists = permute(nums, new ArrayList<>());
        return permuteLists;
    }

    public static List<List<Integer>> permute(int[] nums, List<Integer> permuteList) {
        List<List<Integer>> permuteLists = new ArrayList<>();

        for (Integer num : nums) {
            List<Integer> copypermuteList = new ArrayList<>(permuteList.size());
            copypermuteList.addAll(permuteList);
            copypermuteList.add(num);
            int[] newNumber = new int[nums.length - 1];
            int i = 0;
            for (Integer tempNum : nums) {
                if (tempNum != num) {
                    newNumber[i] = tempNum;
                    i++;
                }
            }
            if (newNumber.length != 0) {
                for (List<Integer> tempList : permute(newNumber, copypermuteList)) {
                    permuteLists.add(tempList);
                }
            } else {
                permuteLists.add(copypermuteList);
            }
        }
        return permuteLists;
    }

    public static void main(String[] args) {
        List<List<Integer>> nihao = permute(new int[]{1,2,3,4});
        System.out.print(nihao);
    }

}
