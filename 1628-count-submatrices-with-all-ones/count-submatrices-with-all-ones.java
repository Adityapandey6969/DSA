class Solution {
    public int numSubmat(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] height = new int[m][n];
        
        // Step 1: Build histogram heights
        for (int j = 0; j < n; j++) {
            height[0][j] = mat[0][j];
            for (int i = 1; i < m; i++) {
                if (mat[i][j] == 1) {
                    height[i][j] = height[i - 1][j] + 1;
                } else {
                    height[i][j] = 0;
                }
            }
        }
        
        int result = 0;
        
        // Step 2: For each row, count submatrices ending there
        for (int i = 0; i < m; i++) {
            result += countFromHistogram(height[i]);
        }
        
        return result;
    }
    
    // Count rectangles in a histogram row
    private int countFromHistogram(int[] h) {
        int n = h.length;
        int[] sum = new int[n];
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        
        for (int j = 0; j < n; j++) {
            while (!stack.isEmpty() && h[stack.peek()] >= h[j]) {
                stack.pop();
            }
            
            if (!stack.isEmpty()) {
                int prev = stack.peek();
                sum[j] = sum[prev] + h[j] * (j - prev);
            } else {
                sum[j] = h[j] * (j + 1);
            }
            
            res += sum[j];
            stack.push(j);
        }
        
        return res;
    }
}
