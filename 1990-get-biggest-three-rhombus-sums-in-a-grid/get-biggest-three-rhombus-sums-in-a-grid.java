class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder());
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // size 0 rhombus (single cell)
                set.add(grid[i][j]);
                
                // size s rhombus
                for (int s = 1; s <= Math.min(Math.min(i, m - 1 - i), Math.min(j, n - 1 - j)); s++) {
                    int sum = 0;
                    // top to right
                    for (int k = 0; k < s; k++) {
                        sum += grid[i - s + k][j + k];
                    }
                    // right to bottom
                    for (int k = 0; k < s; k++) {
                        sum += grid[i + k][j + s - k];
                    }
                    // bottom to left
                    for (int k = 0; k < s; k++) {
                        sum += grid[i + s - k][j - k];
                    }
                    // left to top
                    for (int k = 0; k < s; k++) {
                        sum += grid[i - k][j - s + k];
                    }
                    set.add(sum);
                }
                
                if (set.size() > 3) {
                    // keep only top 3
                    while (set.size() > 3) {
                        set.pollLast();
                    }
                }
            }
        }
        
        int[] result = new int[set.size()];
        int idx = 0;
        for (int val : set) {
            result[idx++] = val;
        }
        return result;
    }
}