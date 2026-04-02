class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;
        int NEG = Integer.MIN_VALUE / 4;

        int[][][] dp = new int[m][n][3];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = NEG;
                }
            }
        }

        int val = coins[0][0];

        if (val >= 0) {
            dp[0][0][0] = val;
        } else {
            dp[0][0][0] = val;
            dp[0][0][1] = 0;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;

                val = coins[i][j];

                for (int k = 0; k < 3; k++) {
                    if (i > 0 && dp[i - 1][j][k] != NEG) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j][k] + val);
                    }
                    if (j > 0 && dp[i][j - 1][k] != NEG) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i][j - 1][k] + val);
                    }

                    if (val < 0 && k > 0) {
                        if (i > 0 && dp[i - 1][j][k - 1] != NEG) {
                            dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j][k - 1]);
                        }
                        if (j > 0 && dp[i][j - 1][k - 1] != NEG) {
                            dp[i][j][k] = Math.max(dp[i][j][k], dp[i][j - 1][k - 1]);
                        }
                    }
                }
            }
        }

        return Math.max(dp[m - 1][n - 1][0],
               Math.max(dp[m - 1][n - 1][1], dp[m - 1][n - 1][2]));
    }
}