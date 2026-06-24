class Solution {
    static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        int sz = 2 * m;

        long[][] T = new long[sz][sz];

        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                T[i][m + j] = 1;
            }
            for (int j = 0; j < i; j++) {
                T[m + i][j] = 1;
            }
        }

        long[] init = new long[sz];

        for (int i = 0; i < m; i++) {
            init[i] = 1;      // up block
            init[m + i] = 1;  // down block
        }

        long[][] P = power(T, n - 1);

        long[] res = multiply(P, init);

        long ans = 0;
        for (long x : res) {
            ans = (ans + x) % MOD;
        }

        return (int) ans;
    }

    private long[] multiply(long[][] a, long[] v) {
        int n = a.length;
        long[] res = new long[n];

        for (int i = 0; i < n; i++) {
            long cur = 0;
            for (int j = 0; j < n; j++) {
                cur = (cur + a[i][j] * v[j]) % MOD;
            }
            res[i] = cur;
        }
        return res;
    }

    private long[][] power(long[][] a, long e) {
        int n = a.length;
        long[][] res = new long[n][n];

        for (int i = 0; i < n; i++) {
            res[i][i] = 1;
        }

        while (e > 0) {
            if ((e & 1) == 1) {
                res = multiply(res, a);
            }
            a = multiply(a, a);
            e >>= 1;
        }

        return res;
    }

    private long[][] multiply(long[][] a, long[][] b) {
        int n = a.length;
        long[][] res = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (a[i][k] == 0) continue;

                long val = a[i][k];

                for (int j = 0; j < n; j++) {
                    if (b[k][j] == 0) continue;

                    res[i][j] = (res[i][j] + val * b[k][j]) % MOD;
                }
            }
        }
        return res;
    }
}