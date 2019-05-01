package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import java.io.*;
import java.util.*;
import java.io.BufferedWriter;


public class TheLongestCommonSubsequence {

    // Complete the longestCommonSubsequence function below.
    static int[] longestCommonSubsequence(int[] a, int[] b) {
        int m = a.length, n = b.length;
        int[][] opt = new int[m+1][n+1];
        for (int i = m-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                if (a[i] == b[j]) {
                    opt[i][j] = opt[i+1][j+1] + 1;
                }
                else {
                    opt[i][j] = Math.max(opt[i+1][j], opt[i][j+1]);
                }
            }
        }

        // Recover LCS itself.
        int[] lcs = new int[opt[0][0]];
        int i = 0, j = 0;
        int count = 0;
        while (i < m && j < n) {
            if (a[i] == b[j] && a[i] != 0) {
                lcs[count++] = a[i];
                i++;
                j++;
            }
            else if (opt[i+1][j] >= opt[i][j+1]) i++;
            else                                 j++;
        }
        return lcs;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        File file = new File("src\\main\\java\\net\\qiguang\\algorithms\\C1_Fundamentals\\S1_BasicProgrammingModel\\TheLongestCommonSubsequence.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        int[] b = new int[m];

        String[] bItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            int bItem = Integer.parseInt(bItems[i]);
            b[i] = bItem;
        }

        int[] result = longestCommonSubsequence(a, b);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
