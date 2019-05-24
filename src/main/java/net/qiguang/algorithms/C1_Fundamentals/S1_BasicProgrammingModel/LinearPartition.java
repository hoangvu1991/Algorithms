package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LinearPartition {
    static int INF = Integer.MAX_VALUE;

    static int[][] partition(int[] values, int k) {
        int n = values.length;
        int[][] M = new int[n][k];
        int[][] D = new int[n - 1][k - 1];

        // initialize
        M[0][0] = values[0];
        // compute prefix sums
        for (int i = 1; i < n; i++) {
            M[i][0] = values[i] + M[i - 1][0];
        }
        // initialize boundary condition
        for (int i = 1; i < k; i++) {
            M[0][i] = values[0];
        }

        // fill the rest
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < k; j++) {
                int current_min = -1;
                int minx = INF;
                for (int x = 0; x < i; x++) {
                    int s = Math.max(M[x][j - 1], M[i][0] - M[x][0]);
                    if (current_min < 0 || s < current_min) {
                        current_min = s;
                        minx = x;
                    }
                }
                M[i][j] = current_min;
                D[i - 1][j - 1] = minx;
            }
        }
        return D;
    }

    static List<List<Integer>> reconstructPartition(int[] values, int[][] D, int k) {
        List<List<Integer>> result = new ArrayList();
        int n = D.length;
        k = k - 2;
        while (k >= 0) {
            List<Integer> inner = new ArrayList();
            for (int i = D[n - 1][k] + 1; i < n + 1; i++) {
                inner.add(values[i]);
            }
            result.add(inner);
            n = D[n - 1][k];
            k--;
        }

        List<Integer> inner = new ArrayList();
        for (int i = 0; i < n + 1; i++) {
            inner.add(values[i]);
        }
        result.add(inner);
        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        int[] values0 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int k = 3;
        int[][] D = partition(values0, k);
        System.out.println(reconstructPartition(values0, D, k));
    /*    k = 2;
        D = partition(values0, k);
        System.out.println(reconstructPartition(values0, D, k));
        k = 4;
        D = partition(values0, k);
        System.out.println(reconstructPartition(values0, D, k));

        int[] values1 = {1, 1, 1, 1, 1, 1, 1, 1, 1};
        k = 3;
        D = partition(values1, k);
        System.out.println(reconstructPartition(values1, D, k));
        k = 2;
        D = partition(values1, k);
        System.out.println(reconstructPartition(values1, D, k));

        int[] values2 = {1, 1, 1, 1, 1, 2, 2, 2, 2};
        k = 3;
        D = partition(values2, k);
        System.out.println(reconstructPartition(values2, D, k));
        k = 2;
        D = partition(values2, k);
        System.out.println(reconstructPartition(values2, D, k));*/
    }
}