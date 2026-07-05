import java.util.*;

class Solution {
    private static final int MAX = 1_000_000;
    private static final List<Integer>[] factors = new ArrayList[MAX + 1];

    static {
        for (int i = 0; i <= MAX; i++) {
            factors[i] = new ArrayList<>();
        }
        for (int i = 2; i <= MAX; i++) {
            if (factors[i].isEmpty()) {
                for (int j = i; j <= MAX; j += i) {
                    factors[j].add(i);
                }
            }
        }
    }

    public int minJumps(int[] nums) {
        int n = nums.length;

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int p : factors[nums[i]]) {
                map.computeIfAbsent(p, k -> new ArrayList<>()).add(i);
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        boolean[] vis = new boolean[n];
        vis[0] = true;
        q.offer(0);

        int steps = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int i = q.poll();

                if (i == n - 1) return steps;

                if (i > 0 && !vis[i - 1]) {
                    vis[i - 1] = true;
                    q.offer(i - 1);
                }

                if (i + 1 < n && !vis[i + 1]) {
                    vis[i + 1] = true;
                    q.offer(i + 1);
                }

                if (nums[i] > 1 && factors[nums[i]].size() == 1
                        && factors[nums[i]].get(0) == nums[i]) {

                    List<Integer> list = map.get(nums[i]);
                    if (list != null) {
                        for (int idx : list) {
                            if (!vis[idx]) {
                                vis[idx] = true;
                                q.offer(idx);
                            }
                        }
                        map.remove(nums[i]); // use teleport only once
                    }
                }
            }

            steps++;
        }

        return -1;
    }
}