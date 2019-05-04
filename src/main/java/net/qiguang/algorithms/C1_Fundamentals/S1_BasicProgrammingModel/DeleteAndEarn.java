package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import java.util.Arrays;

public class DeleteAndEarn {
    private static int deleteAndEarn(int[] nums) {
        int[] count = new int[10001];
        for (int x : nums) {
            count[x]++;
        }
        int avoid = 0;
        int using = 0;
        int prev = -1;

        for (int k = 0; k <= 10000; ++k)
            if (count[k] > 0) {
                int m = Math.max(avoid, using);
                if (k - 1 != prev) {
                    using = k * count[k] + m;
                    avoid = m;
                } else {
                    using = k * count[k] + avoid;
                    avoid = m;
                }
                prev = k;
            }
        return Math.max(avoid, using);
    }

    private static int deleteAndEarn2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int maxPossible = 10000;
        int[] points = new int[maxPossible + 1];
        for (int num : nums) {
            points[num] += num;
        }
        int[] F = Arrays.copyOf(points, maxPossible + 1);
        for (int i = 2; i <= maxPossible; i++) {
            F[i] = Math.max(F[i - 1], F[i - 2] + points[i]);
        }
        return F[maxPossible];
    }

    public static void main(String args[]) {
        //int[] input = {3, 4, 2};
        int[] input = {2, 2, 3, 3, 3, 4};
        int result1 = deleteAndEarn(input);
        System.out.println(result1);
        int result2 = deleteAndEarn2(input);
        System.out.println(result2);
    }
}
