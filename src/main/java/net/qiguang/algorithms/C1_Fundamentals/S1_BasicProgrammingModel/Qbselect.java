package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

public class Qbselect {

    static int MAX = 10001;
    static int MIN = -200000000;
    static int n;
    static int[][] arr = new int[5][MAX];

    // get bit (of number status) at row   |  row : 0 -> 3
    static int getbit(int row, int status) {
        return (status >> row) & 1;
    }

    static int ok(int status1, int status2) {
        int[] bit = new int[5];
        int[] elsebit = new int[5];
        for (int row = 0; row < 4; row++) {
            bit[row] = getbit(row, status1);
            elsebit[row] = getbit(row, status2);
            if ((bit[row] & elsebit[row]) == 1) {
                return 0;
            }
        }
        return 1;
    }

    static int value(int status, int col) { // col : 0 -> 2
        int[] bit = new int[5];
        int sum = 0;
        for (int row = 0; row < 4; row++) {
            bit[row] = getbit(row, status);
            if (bit[row] == 1) {
                sum += arr[row][col];
            }
        }
        return sum;
    }

    static int bitmask() {
        int[][] fv = new int[8][n + 1];
        int[][] fr = new int[8][8];
        int[] statuses = {0, 1, 2, 4, 5, 8, 9, 10};
        int res = MIN;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                fr[i][j] = ok(statuses[i], statuses[j]); // fr : 0 -> 8
            }
        }
        for (int col = 0; col < n; col++) {
            for (int i = 0; i < 8; i++) {
                int t = MIN;
                for (int j = 0; j < 8; j++) {
                    if (fr[i][j] == 1 && fv[j][col] > t) {
                        t = fv[j][col];
                    }
                }
                fv[i][col + 1] = t + value(statuses[i], col);
            }
        }
        for (int i = 0; i < 8; i++) {
            res = Math.max(res, fv[i][n]);
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

