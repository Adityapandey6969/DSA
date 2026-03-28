class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] s = new char[n];
        char cur = 'a';
        
        // Step 1: Build the string from LCP matrix
        for (int i = 0; i < n; i++) {
            if (s[i] != 0) continue; // already assigned
            if (cur > 'z') return ""; // more than 26 distinct characters needed
            s[i] = cur;
            for (int j = i + 1; j < n; j++) {
                if (lcp[i][j] > 0) {
                    if (s[j] != 0 && s[j] != cur) return "";
                    s[j] = cur;
                }
            }
            cur++;
        }
        
        // Step 2: Validate the LCP matrix against the constructed string
        // We check from bottom-right to top-left using DP relationship:
        // lcp[i][j] = (s[i] == s[j]) ? (lcp[i+1][j+1] + 1) : 0
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int expected;
                if (s[i] != s[j]) {
                    expected = 0;
                } else {
                    if (i + 1 < n && j + 1 < n) {
                        expected = lcp[i + 1][j + 1] + 1;
                    } else {
                        expected = 1;
                    }
                }
                if (lcp[i][j] != expected) return "";
            }
        }
        
        return new String(s);
    }
}