import java.util.*;

class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        int m = restrictions.length;
        
        int[][] arr = new int[m + 1][2];
        arr[0][0] = 1;
        arr[0][1] = 0;
        
        for (int i = 0; i < m; i++) {
            arr[i + 1][0] = restrictions[i][0];
            arr[i + 1][1] = restrictions[i][1];
        }
        
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        
        // Forward pass
        for (int i = 1; i <= m; i++) {
            arr[i][1] = Math.min(arr[i][1], arr[i - 1][1] + (arr[i][0] - arr[i - 1][0]));
        }
        
        // Backward pass
        for (int i = m - 1; i >= 0; i--) {
            arr[i][1] = Math.min(arr[i][1], arr[i + 1][1] + (arr[i + 1][0] - arr[i][0]));
        }
        
        int ans = 0;
        
        // Maximum possible height between each pair of restricted buildings
        for (int i = 1; i <= m; i++) {
            int x1 = arr[i - 1][0], h1 = arr[i - 1][1];
            int x2 = arr[i][0], h2 = arr[i][1];
            int dist = x2 - x1;
            
            ans = Math.max(ans, (h1 + h2 + dist) / 2);
        }
        
        // After the last restriction, heights can keep increasing by 1
        ans = Math.max(ans, arr[m][1] + (n - arr[m][0]));
        
        return ans;
    }
}