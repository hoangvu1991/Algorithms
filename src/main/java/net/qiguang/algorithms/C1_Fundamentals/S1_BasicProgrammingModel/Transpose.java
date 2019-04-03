package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

/******************************************************************************
 *  Compilation:  javac Transpose.java
 *  Execution:    java Transpose n
 *
 *  Transpose an n-by-n matrix in-place, without creating a second
 *  2D array.
 *
 *  Submitted by Christian Rubio.
 *
 ******************************************************************************/


public class Transpose {

    public static void main(String[] args) {

        // create n-by-n matrix
        int n = Integer.parseInt(args.length == 0 ? "5" : args[0]);
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = n*i + j;
            }
        }

        // print out initial matrix
        System.out.println("Before");
        System.out.println("------");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%4d", a[i][j]);
            }
            System.out.println();
        }

        // transpose in-place
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int temp = a[i][j];
                a[i][j] = a[j][i];
                a[j][i] = temp;
            }
        }

        // print out transposed matrix
        System.out.println();
        System.out.println("After");
        System.out.println("------");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%4d", a[i][j]);
            }
            System.out.println();
        }

    }
}