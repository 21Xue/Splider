package com.xyp.zigzag;

/**
 * Created by xyp on 19/1/15.
 */
public class GenerateMatrix {

    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];

        int index = 1;

        int maxy = matrix.length - 1;
        int maxx = matrix[0].length - 1;
        int x = 0;
        int y = 0;

        while (index <= n * n) {

            for (int i = x; i <= maxx; i++) {
                matrix[y][i] = index;
                index++;
            }
            y++;

            for (int i = y; i <= maxy; i++) {
                matrix[i][maxx] = index;
                index++;
            }
            maxx--;


            for (int i = maxx; i >= x; i--) {
                matrix[maxy][i] = index;
                index++;
            }
            maxy--;

            for (int i = maxy; i >= y; i--) {
                matrix[i][x] = index;
                index++;
            }
            x++;
        }

        return matrix;

    }

    public static void main(String[] args) {

        int n = 5;

//        int[][] matrix = new int[n][n];

        int[][] matrix = generateMatrix(3);
        System.out.print("asd");


    }

}
