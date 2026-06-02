import java.util.*;

class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {
        
        int n = landStartTime.length;
        int m = waterStartTime.length;
        
        int ans = Integer.MAX_VALUE;
        
        // Try all land -> water combinations
        for (int i = 0; i < n; i++) {
            int landEnd = landStartTime[i] + landDuration[i];
            
            for (int j = 0; j < m; j++) {
                int waterBegin = Math.max(landEnd, waterStartTime[j]);
                int finish = waterBegin + waterDuration[j];
                ans = Math.min(ans, finish);
            }
        }
        
        // Try all water -> land combinations
        for (int i = 0; i < m; i++) {
            int waterEnd = waterStartTime[i] + waterDuration[i];
            
            for (int j = 0; j < n; j++) {
                int landBegin = Math.max(waterEnd, landStartTime[j]);
                int finish = landBegin + landDuration[j];
                ans = Math.min(ans, finish);
            }
        }
        
        return ans;
    }
}