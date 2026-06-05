import java.util.*;

class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {

        int n = landStartTime.length;
        int m = waterStartTime.length;

        // Sort water rides by start time for binary search
        int[][] water = new int[m][2];
        for (int i = 0; i < m; i++) {
            water[i][0] = waterStartTime[i];
            water[i][1] = waterDuration[i];
        }
        Arrays.sort(water, (a, b) -> a[0] - b[0]);

        // Sort land rides by start time for binary search
        int[][] land = new int[n][2];
        for (int i = 0; i < n; i++) {
            land[i][0] = landStartTime[i];
            land[i][1] = landDuration[i];
        }
        Arrays.sort(land, (a, b) -> a[0] - b[0]);

        // Precompute suffix minimum of (startTime[j] + duration[j]) for water rides
        // where startTime[j] >= some value
        // Also suffix minimum of duration for water rides (for case when finishLand >= startWater)
        int[] waterSuffMinEnd = new int[m]; // suffix min of startTime + duration
        int[] waterSuffMinDur = new int[m]; // suffix min of duration
        waterSuffMinEnd[m - 1] = water[m - 1][0] + water[m - 1][1];
        waterSuffMinDur[m - 1] = water[m - 1][1];
        for (int i = m - 2; i >= 0; i--) {
            waterSuffMinEnd[i] = Math.min(waterSuffMinEnd[i + 1], water[i][0] + water[i][1]);
            waterSuffMinDur[i] = Math.min(waterSuffMinDur[i + 1], water[i][1]);
        }

        // Similarly for land rides
        int[] landSuffMinEnd = new int[n];
        int[] landSuffMinDur = new int[n];
        landSuffMinEnd[n - 1] = land[n - 1][0] + land[n - 1][1];
        landSuffMinDur[n - 1] = land[n - 1][1];
        for (int i = n - 2; i >= 0; i--) {
            landSuffMinEnd[i] = Math.min(landSuffMinEnd[i + 1], land[i][0] + land[i][1]);
            landSuffMinDur[i] = Math.min(landSuffMinDur[i + 1], land[i][1]);
        }

        int ans = Integer.MAX_VALUE;

        // Land -> Water
        for (int i = 0; i < n; i++) {
            int finishLand = land[i][0] + land[i][1];

            // Case 1: water rides starting >= finishLand => finish = finishLand + minDur
            int idx = lowerBound(water, finishLand);
            if (idx < m) {
                ans = Math.min(ans, finishLand + waterSuffMinDur[idx]);
            }

            // Case 2: water rides starting < finishLand => finish = startWater + duration
            // We need minimum (startWater + duration) among those with start < finishLand
            // but we also need start + duration >= finishLand to matter... actually no
            // finish = max(finishLand, startWater) + duration
            // if startWater < finishLand, finish = finishLand + duration (already covered above conceptually... no)
            // Wait: if startWater < finishLand, we start at finishLand, finish = finishLand + duration
            // if startWater >= finishLand, finish = startWater + duration
            // So for startWater < finishLand: best is finishLand + min(duration) among those
            if (idx > 0) {
                // Need min duration among water[0..idx-1]
                // Precompute prefix min duration
            }
        }

        // Simpler O(n+m) approach: just iterate smartly
        // Actually let me just do the clean binary search approach

        ans = Integer.MAX_VALUE;

        // Precompute prefix min duration for water and land
        int[] waterPrefMinDur = new int[m];
        waterPrefMinDur[0] = water[0][1];
        for (int i = 1; i < m; i++) {
            waterPrefMinDur[i] = Math.min(waterPrefMinDur[i - 1], water[i][1]);
        }

        int[] landPrefMinDur = new int[n];
        landPrefMinDur[0] = land[0][1];
        for (int i = 1; i < n; i++) {
            landPrefMinDur[i] = Math.min(landPrefMinDur[i - 1], land[i][1]);
        }

        // Land -> Water
        for (int i = 0; i < n; i++) {
            int finishLand = land[i][0] + land[i][1];

            // Water rides starting >= finishLand: finish = waterStart + waterDur
            int idx = lowerBound(water, finishLand);
            if (idx < m) {
                ans = Math.min(ans, waterSuffMinEnd[idx]);
            }

            // Water rides starting < finishLand: finish = finishLand + waterDur
            if (idx > 0) {
                ans = Math.min(ans, finishLand + waterPrefMinDur[idx - 1]);
            }
        }

        // Water -> Land
        for (int i = 0; i < m; i++) {
            int finishWater = water[i][0] + water[i][1];

            // Land rides starting >= finishWater: finish = landStart + landDur
            int idx = lowerBound(land, finishWater);
            if (idx < n) {
                ans = Math.min(ans, landSuffMinEnd[idx]);
            }

            // Land rides starting < finishWater: finish = finishWater + landDur
            if (idx > 0) {
                ans = Math.min(ans, finishWater + landPrefMinDur[idx - 1]);
            }
        }

        return ans;
    }

    // Find first index where ride[idx][0] >= target
    private int lowerBound(int[][] rides, int target) {
        int lo = 0, hi = rides.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (rides[mid][0] >= target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
}