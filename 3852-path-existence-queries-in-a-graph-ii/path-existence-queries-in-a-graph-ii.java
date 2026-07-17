import java.util.*;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[] ans = new int[queries.length];
        int[] indexMap = new int[n];
        int[] sortedNums = new int[n];

        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));

        for (int i = 0; i < n; i++) {
            sortedNums[i] = arr[i][0];
            indexMap[arr[i][1]] = i;
        }

        int maxLevel = 32 - Integer.numberOfLeadingZeros(n) + 1;
        int[][] jump = new int[n][maxLevel];

        int right = 0;
        for (int i = 0; i < n; i++) {
            while (right + 1 < n && sortedNums[right + 1] - sortedNums[i] <= maxDiff)
                right++;
            jump[i][0] = right;
        }

        for (int k = 1; k < maxLevel; k++) {
            for (int i = 0; i < n; i++) {
                jump[i][k] = jump[jump[i][k - 1]][k - 1];
            }
        }

        for (int i = 0; i < queries.length; i++) {
            int u = indexMap[queries[i][0]];
            int v = indexMap[queries[i][1]];
            int l = Math.min(u, v);
            int r = Math.max(u, v);
            int res = solve(jump, l, r, maxLevel - 1);
            ans[i] = (res == Integer.MAX_VALUE) ? -1 : res;
        }

        return ans;
    }

    private int solve(int[][] jump, int l, int r, int level) {
        if (l == r) return 0;
        if (jump[l][0] >= r) return 1;
        if (jump[l][level] < r) return Integer.MAX_VALUE;

        int j;
        for (j = level; j >= 0; j--) {
            if (jump[l][j] < r) break;
        }

        return (1 << j) + solve(jump, jump[l][j], r, j);
    }
}