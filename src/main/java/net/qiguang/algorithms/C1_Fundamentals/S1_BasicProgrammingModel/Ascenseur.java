package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;
import java.util.Scanner;

class Ascenseur {

    private static final int BASE = 100;
    private static IntervalTree[][] tree = new IntervalTree[BASE][BASE];

    // main
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < BASE; i++) {
            for (int j = 0; j < BASE; j++) {
                tree[i][j] = new IntervalTree();
            }
        }
        int N = sc.nextInt();
        int X = sc.nextInt();
        long[] a = new long[N];
        for (int i = N - 1; i > -1; i--) {
            a[i] = sc.nextLong();
        }
        long res, cnt;
        for (int k = 1; k <= Math.min(N, BASE - 1); k++) {
            for (int r = 0; r < k; r++) {
                long[] tmp = new long[(N - r - 1) / k + 1];
                cnt = 1;
                int id = 0;
                for (int i = r; i < N; i += k) {
                    tmp[id++] = a[i] + cnt * X;
                    cnt += 2;
                }
                tree[k][r].build(tmp);
            }
        }

        int Q = sc.nextInt();
        while (Q-- > 0) {
            int R = sc.nextInt();
            int L = sc.nextInt();
            int K = sc.nextInt();
            R = N - R;
            L = N - L;
            res = 0;
            if (K >= BASE) {
                cnt = 1;
                for (int i = L; i <= R; i += K) {
                    res = Math.max(res, a[i] + cnt * X);
                    cnt += 2;
                }
            }
            else {
                int r = L % K;
                L = (L - r) / K;
                R = (R - r) / K;
                res = tree[K][r].query(L, R);
                res -= (long)2 * L * X;
            }
            System.out.println(res);
        }
    }


    private static class IntervalTree {
        long[] it;
        int n;

        private void build(int id, int l, int r, long[] a) {
            if (l > r) {
                return;
            }
            if (l == r) {
                it[id] = a[l];
                return;
            }
            int m = (l + r) / 2;
            build(id * 2, l, m, a);
            build(id * 2 + 1, m + 1, r, a);
            it[id] = Math.max(it[id * 2], it[id * 2 + 1]);
        }

        public void build(long[] a) {
            n = a.length;
            it = new long[4 * n + 1];
            build(1, 0, n - 1, a);
        }

        private long query(int id, int l, int r, int lo, int hi) {
            if (lo > r || hi < l) {
                return 0;
            }
            if (lo <= l && r <= hi) {
                return it[id];
            }
            int m = (l + r) / 2;
            return Math.max(query(id * 2, l, m, lo, hi), query(id * 2 + 1, m + 1, r, lo, hi));
        }

        public long query(int l, int r) {
            return query(1, 0, n - 1, l, r);
        }
    }
}
/*
 stdin  copy
5 2
1 3 5 12 13
3
1 5 3
1 3 1
2 5 4
  stdout  copy
15
11
15
* */