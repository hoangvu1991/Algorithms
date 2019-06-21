package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

public class Qbselect {

    static int MAX = 10001;
    static int MIN = -200000000;
    static int n;
    static int[][] arr = new int[5][MAX];

    // get bit (of number status) at row   |  row : 0 -> 3
    static boolean getbit(int status, int row) {
        //return (status >> row) & 1;
        return (status & (1 << row)) != 0;
    }

    static boolean ok(int status1, int status2) {
        boolean[] bit = new boolean[4];
        boolean[] elsebit = new boolean[4];
        for (int row = 0; row < 4; row++) {
            bit[row] = getbit(status1, row);
            elsebit[row] = getbit(status2, row);
            if (bit[row] && elsebit[row]) {
                return false;
            }
        }
        return true;
    }

    static int value(int status, int col) { // col : 0 -> 2
        boolean[] bit = new boolean[4];
        int sum = 0;
        for (int row = 0; row < 4; row++) {
            bit[row] = getbit(status, row);
            if (bit[row]) {
                sum += arr[row][col];
            }
        }
        return sum;
    }

    static int bitmask() {
        int[][] fvalues = new int[8][n + 1];
        boolean[][] checkStatuses = new boolean[8][8];
        int[] statuses = {0, 1, 2, 4, 5, 8, 9, 10};
        int res = MIN;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                checkStatuses[i][j] = ok(statuses[i], statuses[j]); // fr : 0 -> 8
            }
        }
        for (int col = 0; col < n; col++) {
            for (int i = 0; i < 8; i++) {
                int t = MIN;
                for (int j = 0; j < 8; j++) {
                    if (checkStatuses[i][j] && fvalues[j][col] > t) {
                        t = fvalues[j][col];
                    }
                }
                fvalues[i][col + 1] = t + value(statuses[i], col);
            }
        }
        for (int i = 0; i < 8; i++) {
            res = Math.max(res, fvalues[i][n]);
        }
        return res;
    }

    public static void main(String[] args) {
        n = 3;
        arr = new int[][]{
                {-1, 9, 3},
                {-4, 5, -6},
                {7, 8, 9},
                {9, 7, 2}};
        System.out.printf("%d", bitmask());
    }
}

