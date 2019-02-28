package com.xyp.zigzag;

/**
 * Created by xyp on 19/1/10.
 */
public class shell {
    public static void sort(Comparable[] a) { // 将a[]按升序排列
        int N = a.length;
        int h = 1;
        while (h < N / 3)
            h = 3 * h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...


        while (h >= 1) {
            for (int i = h; i < N; i++) { // 将a[i]插入到a[i-h], a[i-2*h], a[i-3*h]... 之中
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
                    exch(a, j, j - h);
            }
            h = h / 3;
        }
    }

    private static void exch(Comparable[] a, int i, int j) {

    }

    private static boolean less(Comparable a, Comparable b) {
        return true;

    }


}

