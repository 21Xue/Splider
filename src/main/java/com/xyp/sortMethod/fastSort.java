package com.xyp.sortMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class fastSort {

    public static void main(String[] args) {

        List<Integer> numbers = createNumber(10, 100);

        List<Integer> numbers1 = fun(numbers, 0, 9);

        System.out.print("asd");
    }

    private static List<Integer> fun(List<Integer> needSortList, int left, int right) {
        int base = needSortList.get(left);
        int baseIndex = left;
        int baseright = right;

        int pointLeft = left + 1;
        int pointRight = right;
        while (pointLeft != pointRight) {
            while (pointLeft < pointRight && needSortList.get(pointLeft) < base) {
                pointLeft++;
            }
            while (pointLeft < pointRight && needSortList.get(pointRight) > base) {
                pointRight--;
            }
            swap(needSortList, pointLeft, pointRight);
        }
        swap(needSortList, baseIndex, pointLeft - 1);
        if (baseIndex < pointLeft - 1) {
            fun(needSortList, baseIndex, pointLeft - 1);
        }
        if (pointRight < baseright) {
            fun(needSortList, pointRight, baseright);
        }

        return needSortList;
    }

    private static void swap(List<Integer> needSortList, int left, int right) {
        int temp = needSortList.get(left);
        needSortList.set(left, needSortList.get(right));
        needSortList.set(right, temp);
    }

    private static List<Integer> createNumber(int length, int seed) {
        List<Integer> numberList = new ArrayList<>(length);

        Random r = new Random(seed);

        for (int i = 0; i < length; i++) {
            numberList.add(r.nextInt(seed));
        }
        return numberList;
    }
}
