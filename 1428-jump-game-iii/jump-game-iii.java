class Solution {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n];
        return dfs(arr, start, visited);
    }

    private boolean dfs(int[] arr, int idx, boolean[] visited) {
        if (idx < 0 || idx >= arr.length || visited[idx]) {
            return false;
        }

        if (arr[idx] == 0) {
            return true;
        }

        visited[idx] = true;

        return dfs(arr, idx + arr[idx], visited) ||
               dfs(arr, idx - arr[idx], visited);
    }
}