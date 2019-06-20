package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

public class Qbselect {

    static int MAX = 10001;
    static int MIN = -200000000;
    static int n;
    static int[][] a = new int[5][MAX];
    static int[][] f = new int[16][MAX];
    static int[][] fr = new int[9][9];

    static int getbit(int k, int x) {
        return (x >> (k - 1)) & 1;
    }

    static int ok(int x, int y) {
        int[] bit = new int[5];
        int[] elsebit = new int[5];
        for (int v = 1; v <= 4; v++) {
            bit[v] = getbit(v, x);
        }
        for (int v = 1; v <= 4; v++) {
            elsebit[v] = getbit(v, y);
        }
        for (int v = 1; v <= 4; v++) {
            if ((bit[v] & elsebit[v]) == 1) return 0;
        }
        return 1;
    }

    static int value(int x, int y) {
        int[] bit = new int[5];
        int sum = 0;
        for (int v = 1; v <= 4; v++) {
            bit[v] = getbit(v, x);
        }
        for (int v = 1; v <= 4; v++) {
            if (bit[v] == 1) sum += a[v - 1][y - 1];
        }
        return sum;
    }

    static int bitmask() {
        int[] d = {0, 1, 2, 4, 5, 8, 9, 10};
        int res = MIN;
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                fr[i][j] = ok(d[i - 1], d[j - 1]);
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 8; j++) {
                int t = MIN;
                for (int k = 1; k <= 8; k++) {
                    if (fr[j][k] == 1 && f[k][i - 1] > t) {
                        t = f[k][i - 1];
                    }
                }
                f[j][i] = t + value(d[j - 1], i);
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

