package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

public class Qbselect {

    static int MAX = 10001;
    static int MIN = -200000000;
    static int n;
    static int[][] a = new int[5][MAX];

    // get bit (of number status) at (row - 1)   |  row : 1 -> 4
    static int getbit(int row, int status) {
        return (status >> (row - 1)) & 1;
    }

    static int ok(int status1, int status2) {
        int[] bit = new int[5];
        int[] elsebit = new int[5];
        for (int row = 1; row <= 4; row++) {
            bit[row] = getbit(row, status1);
            elsebit[row] = getbit(row, status2);
            if ((bit[row] & elsebit[row]) == 1) {
                return 0;
            }
        }
        return 1;
    }

    static int value(int status, int col) {
        int[] bit = new int[5];
        int sum = 0;
        for (int row = 1; row <= 4; row++) {
            bit[row] = getbit(row, status);
            if (bit[row] == 1) {
                sum += a[row - 1][col - 1];
            }
        }
        return sum;
    }

    static int bitmask() {
        int[][] f = new int[16][MAX];
        int[][] fr = new int[9][9];
        int[] statuses = {0, 1, 2, 4, 5, 8, 9, 10};
        int res = MIN;
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                fr[i][j] = ok(statuses[i - 1], statuses[j - 1]);
            }
        }
        for (int col = 1; col <= n; col++) {
            for (int i = 1; i <= 8; i++) {
                int t = MIN;
                for (int j = 1; j <= 8; j++) {
                    if (fr[i][j] == 1 && f[j][col - 1] > t) {
                        t = f[j][col - 1];
                    }
                }
                f[i][col] = t + value(statuses[i - 1], col);
            }
        }
        for (int i = 1; i <= 8; i++) {
            res = Math.max(res, f[i][n]);
        }
        return res;
    }

    public static void main(String[] args) {
        n = 3;
        a = new int[][]{
                {-1, 9, 3},
                {-4, 5, -6},
                {7, 8, 9},
                {9, 7, 2}};
        System.out.printf("%d", bitmask());
    }
}

