class Solution {
    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) return true;
        if (s1.length() != s2.length()) return false;

        int n = s1.length();
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        for (int c : count) {
            if (c != 0) return false;
        }

        Boolean[][][] memo = new Boolean[n][n][n + 1];
        return dfs(s1, s2, 0, 0, n, memo);
    }

    private boolean dfs(String s1, String s2, int i, int j, int len, Boolean[][][] memo) {
        if (memo[i][j][len] != null) return memo[i][j][len];

        if (s1.substring(i, i + len).equals(s2.substring(j, j + len))) {
            return memo[i][j][len] = true;
        }

        int[] count = new int[26];
        for (int k = 0; k < len; k++) {
            count[s1.charAt(i + k) - 'a']++;
            count[s2.charAt(j + k) - 'a']--;
        }
        for (int c : count) {
            if (c != 0) return memo[i][j][len] = false;
        }

        for (int k = 1; k < len; k++) {
            if ((dfs(s1, s2, i, j, k, memo) &&
                 dfs(s1, s2, i + k, j + k, len - k, memo)) ||
                (dfs(s1, s2, i, j + len - k, k, memo) &&
                 dfs(s1, s2, i + k, j, len - k, memo))) {
                return memo[i][j][len] = true;
            }
        }

        return memo[i][j][len] = false;
    }
}

