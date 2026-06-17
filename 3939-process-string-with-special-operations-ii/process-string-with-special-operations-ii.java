class Solution {
    public char processStr(String s, long k) {
        int n = s.length();
        long[] len = new long[n];
        
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            long prev = (i == 0) ? 0 : len[i - 1];
            
            if (ch >= 'a' && ch <= 'z') {
                len[i] = prev + 1;
            } else if (ch == '*') {
                len[i] = Math.max(0, prev - 1);
            } else if (ch == '#') {
                len[i] = prev * 2;
            } else { // ch == '%'
                len[i] = prev;
            }
        }
        
        if (n == 0 || k < 0 || k >= len[n - 1]) {
            return '.';
        }
        
        for (int i = n - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            long prev = (i == 0) ? 0 : len[i - 1];
            
            if (ch >= 'a' && ch <= 'z') {
                if (k == len[i] - 1) {
                    return ch;
                }
            } else if (ch == '*') {
                // k stays the same
            } else if (ch == '#') {
                if (k >= prev) {
                    k -= prev;
                }
            } else { // ch == '%'
                k = prev - 1 - k;
            }
        }
        
        return '.';
    }
}