package com.xyp;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xyp on 19/1/15.
 */
public class SpiralOrder {


    public static List<Integer> spiralOrder(int[][] matrix) {

        if (matrix.length == 0) {
            return new ArrayList<>();
        }

        int maxy = matrix.length - 1;
        int maxx = matrix[0].length - 1;

        int x = 0;
        int y = 0;

        List<Integer> integerList = new ArrayList<>();

        while (true) {

            for (int i = x; i <= maxx; i++) {
                integerList.add(matrix[y][i]);
            }
            y++;

            if (x > maxx || y > maxy) {
                break;
            }

            for (int i = y; i <= maxy; i++) {
                integerList.add(matrix[i][maxx]);
            }
            maxx--;

            if (x > maxx || y > maxy) {
                break;
            }

            for (int i = maxx; i >= x; i--) {
                integerList.add(matrix[maxy][i]);
            }
            maxy--;

            if (x > maxx || y > maxy) {
                break;
            }

            for (int i = maxy; i >= y; i--) {
                integerList.add(matrix[i][x]);
            }
            x++;

            if (x > maxx || y > maxy) {
                break;
            }
        }

        return integerList;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};

//        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

//        int[][] matrix = {{1, 2, 3}};

//        int[][] matrix = {};

        List<Integer> list = spiralOrder(matrix);

        System.out.print("asd");
    }
}
