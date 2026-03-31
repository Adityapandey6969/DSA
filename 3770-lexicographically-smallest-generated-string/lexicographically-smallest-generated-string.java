class Solution {
    public String generateString(String pattern, String str) {
        int n = pattern.length();
        int m = str.length();
        int total = n + m - 1;
        
        char[] forced = new char[total];
        
        // Apply T constraints
        for (int i = 0; i < n; i++) {
            if (pattern.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    char c = str.charAt(j);
                    if (forced[i + j] != 0 && forced[i + j] != c) return "";
                    forced[i + j] = c;
                }
            }
        }
        
        char[] word = new char[total];
        boolean[] isForced = new boolean[total];
        for (int i = 0; i < total; i++) {
            if (forced[i] != 0) {
                word[i] = forced[i];
                isForced[i] = true;
            } else {
                word[i] = 'a';
                isForced[i] = false;
            }
        }
        
        // Handle F constraints iteratively
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int i = 0; i < n; i++) {
                if (pattern.charAt(i) == 'F') {
                    boolean matches = true;
                    for (int j = 0; j < m; j++) {
                        if (word[i + j] != str.charAt(j)) {
                            matches = false;
                            break;
                        }
                    }
                    if (matches) {
                        // Find rightmost unforced position in window [i, i+m-1]
                        int pos = -1;
                        for (int j = m - 1; j >= 0; j--) {
                            if (!isForced[i + j]) {
                                pos = j;
                                break;
                            }
                        }
                        if (pos == -1) return "";
                        
                        int absPos = i + pos;
                        // Set to smallest char != str.charAt(pos)
                        char target = (str.charAt(pos) == 'a') ? 'b' : 'a';
                        word[absPos] = target;
                        isForced[absPos] = true;
                        
                        // Verify no T constraint conflict
                        for (int k = Math.max(0, absPos - m + 1); k <= Math.min(n - 1, absPos); k++) {
                            if (pattern.charAt(k) == 'T') {
                                if (str.charAt(absPos - k) != target) return "";
                            }
                        }
                        changed = true;
                    }
                }
            }
        }
        
        return new String(word);
    }
}