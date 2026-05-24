class Solution {
    public int maximumJumps(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[n];
        
        // Initialize dp with -1 (unreachable)
        for (int i = 0; i < n; i++) {
            dp[i] = -1;
        }
        
        dp[0] = 0; // start at index 0 with 0 jumps
        
        for (int i = 0; i < n; i++) {
            if (dp[i] == -1) continue; // skip unreachable
            
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(nums[j] - nums[i]) <= target) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
        }
        
        return dp[n - 1];
    }
}