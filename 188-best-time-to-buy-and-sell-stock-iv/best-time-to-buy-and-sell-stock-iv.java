class Solution {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k == 0) {
            return 0;
        }
        
        int n = prices.length;
        
        // If k >= n/2, it's equivalent to unlimited transactions
        if (k >= n / 2) {
            int maxProfit = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    maxProfit += prices[i] - prices[i - 1];
                }
            }
            return maxProfit;
        }
        
        // dp[i][j][0] = max profit on day i with at most j transactions, no stock
        // dp[i][j][1] = max profit on day i with at most j transactions, holding stock
        int[][][] dp = new int[n][k + 1][2];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                if (i == 0) {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -prices[i];
                } else {
                    if (j == 0) {
                        dp[i][j][0] = 0;
                        dp[i][j][1] = Math.max(dp[i - 1][j][1], -prices[i]);
                    } else {
                        dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                        dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
                    }
                }
            }
        }
        
        return dp[n - 1][k][0];
    }
}