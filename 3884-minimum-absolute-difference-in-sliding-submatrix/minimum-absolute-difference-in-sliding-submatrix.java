class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] ans = new int[m - k + 1][n - k + 1];
        
        for (int i = 0; i <= m - k; i++) {
            for (int j = 0; j <= n - k; j++) {
                int[] elements = new int[k * k];
                int idx = 0;
                for (int di = 0; di < k; di++) {
                    for (int dj = 0; dj < k; dj++) {
                        elements[idx++] = grid[i + di][j + dj];
                    }
                }
                
                Arrays.sort(elements);
                
                int minDiff = Integer.MAX_VALUE;
                for (int t = 1; t < elements.length; t++) {
                    // Skip duplicates — only consider DISTINCT values
                    if (elements[t] != elements[t - 1]) {
                        minDiff = Math.min(minDiff, elements[t] - elements[t - 1]);
                    }
                }
                
                // If all elements are the same or k=1, answer is 0
                ans[i][j] = (minDiff == Integer.MAX_VALUE) ? 0 : minDiff;
            }
        }
        
        return ans;
    }
}