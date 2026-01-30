import java.util.*;

class Solution {
    public long minimumCost(String source, String target,
                            String[] original, String[] changed, int[] cost) {

        int n = source.length();
        long INF = (long) 1e18;

        // Group transformations by length
        Map<Integer, Map<String, Integer>> idByLen = new HashMap<>();
        Map<Integer, long[][]> distByLen = new HashMap<>();

        for (int i = 0; i < original.length; i++) {
            int len = original[i].length();
            idByLen.putIfAbsent(len, new HashMap<>());
        }

        // Assign IDs per length
        for (int i = 0; i < original.length; i++) {
            int len = original[i].length();
            Map<String, Integer> id = idByLen.get(len);
            id.putIfAbsent(original[i], id.size());
            id.putIfAbsent(changed[i], id.size());
        }

        // Build distance matrices
        for (int len : idByLen.keySet()) {
            int m = idByLen.get(len).size();
            long[][] dist = new long[m][m];
            for (int i = 0; i < m; i++) {
                Arrays.fill(dist[i], INF);
                dist[i][i] = 0;
            }
            distByLen.put(len, dist);
        }

        // Fill direct edges
        for (int i = 0; i < original.length; i++) {
            int len = original[i].length();
            Map<String, Integer> id = idByLen.get(len);
            long[][] dist = distByLen.get(len);
            int u = id.get(original[i]);
            int v = id.get(changed[i]);
            dist[u][v] = Math.min(dist[u][v], cost[i]);
        }

        // Floyd–Warshall per length
        for (int len : distByLen.keySet()) {
            long[][] dist = distByLen.get(len);
            int m = dist.length;
            for (int k = 0; k < m; k++)
                for (int i = 0; i < m; i++)
                    for (int j = 0; j < m; j++)
                        if (dist[i][k] + dist[k][j] < dist[i][j])
                            dist[i][j] = dist[i][k] + dist[k][j];
        }

        // DP
        long[] dp = new long[n + 1];
        Arrays.fill(dp, INF);
        dp[n] = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (source.charAt(i) == target.charAt(i)) {
                dp[i] = dp[i + 1];
            }

            for (int len : idByLen.keySet()) {
                if (i + len > n) continue;

                String s = source.substring(i, i + len);
                String t = target.substring(i, i + len);

                Map<String, Integer> id = idByLen.get(len);
                if (!id.containsKey(s) || !id.containsKey(t)) continue;

                long[][] dist = distByLen.get(len);
                long c = dist[id.get(s)][id.get(t)];
                if (c < INF && dp[i + len] < INF) {
                    dp[i] = Math.min(dp[i], c + dp[i + len]);
                }
            }
        }

        return dp[0] >= INF ? -1 : dp[0];
    }
}
 