package com.xyp.zigzag;

public class RemoveDuplicates {

	public static int removeDuplicates(int[] nums) {

		int nowNum = nums[0];

		int nowIndex = 1;

		for (int i = 1; i < nums.length; i++) {
			if (nowNum != nums[i]) {
				nowNum = nums[i];
				nums[nowIndex] = nums[i];
				nowIndex++;
			}

		}

		return nowIndex;
	}

	public static void main(String[] args) {
		int[] nums = { 4,4,4,4,4 };
		int length = removeDuplicates(nums);
		System.out.println("asd");
	}

}
