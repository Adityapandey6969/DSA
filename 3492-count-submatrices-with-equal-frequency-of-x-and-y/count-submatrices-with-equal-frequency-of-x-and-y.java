class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] xCol = new int[n];
        int[] yCol = new int[n];
        int ans = 0;

        for (int i = 0; i < m; i++) {
            int x = 0, y = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'X') xCol[j]++;
                else if (grid[i][j] == 'Y') yCol[j]++;

                x += xCol[j];
                y += yCol[j];

                if (x == y && x > 0) ans++;
            }
        }

        return ans;
    }
}