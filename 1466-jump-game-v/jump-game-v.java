import java.util.*;

class Solution {
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        Integer[] memo = new Integer[n];
        int max = 1;

        for (int i = 0; i < n; i++) {
            max = Math.max(max, dfs(arr, d, i, memo));
        }

        return max;
    }

    private int dfs(int[] arr, int d, int i, Integer[] memo) {
        if (memo[i] != null) return memo[i];

        int n = arr.length;
        int best = 1; // at least the current index

        // Check left
        for (int step = 1; step <= d && i - step >= 0; step++) {
            if (arr[i - step] >= arr[i]) break;
            best = Math.max(best, 1 + dfs(arr, d, i - step, memo));
        }

        // Check right
        for (int step = 1; step <= d && i + step < n; step++) {
            if (arr[i + step] >= arr[i]) break;
            best = Math.max(best, 1 + dfs(arr, d, i + step, memo));
        }

        memo[i] = best;
        return best;
    }
}