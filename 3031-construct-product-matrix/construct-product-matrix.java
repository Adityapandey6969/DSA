class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int MOD = 12345;
        
        int[][] result = new int[n][m];
        
        // Calculate prefix products
        long[] prefix = new long[n * m];
        prefix[0] = 1;
        for (int i = 1; i < n * m; i++) {
            int prevRow = (i - 1) / m;
            int prevCol = (i - 1) % m;
            prefix[i] = (prefix[i - 1] * (grid[prevRow][prevCol] % MOD)) % MOD;
        }
        
        // Calculate suffix products
        long[] suffix = new long[n * m];
        suffix[n * m - 1] = 1;
        for (int i = n * m - 2; i >= 0; i--) {
            int nextRow = (i + 1) / m;
            int nextCol = (i + 1) % m;
            suffix[i] = (suffix[i + 1] * (grid[nextRow][nextCol] % MOD)) % MOD;
        }
        
        // Calculate result
        for (int i = 0; i < n * m; i++) {
            int row = i / m;
            int col = i % m;
            result[row][col] = (int) ((prefix[i] * suffix[i]) % MOD);
        }
        
        return result;
    }
}