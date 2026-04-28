import java.util.*;

class Solution {
    public int minOperations(int[][] grid, int x) {
        int m = grid.length;
        int n = grid[0].length;
        int size = m * n;
        
        int[] arr = new int[size];
        int idx = 0;
        
        // Flatten grid and check feasibility
        int remainder = grid[0][0] % x;
        for (int[] row : grid) {
            for (int val : row) {
                if (val % x != remainder) {
                    return -1;
                }
                arr[idx++] = val;
            }
        }
        
        // Sort to find median
        Arrays.sort(arr);
        int median = arr[size / 2];
        
        // Calculate total operations
        int operations = 0;
        for (int val : arr) {
            operations += Math.abs(val - median) / x;
        }
        
        return operations;
    }
}