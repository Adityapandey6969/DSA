class Solution {
    public boolean hasAllCodes(String s, int k) {
        
        int n = s.length();
        if (n < k) return false;
        
        int total = 1 << k;   // 2^k possible codes
        boolean[] seen = new boolean[total];
        int count = 0;
        
        int num = 0;
        int mask = total - 1;  // keeps last k bits
        
        for (int i = 0; i < n; i++) {
            
            // shift left and add current bit
            num = ((num << 1) & mask) | (s.charAt(i) - '0');
            
            // start checking once window size reaches k
            if (i >= k - 1) {
                if (!seen[num]) {
                    seen[num] = true;
                    count++;
                    
                    // early stopping
                    if (count == total) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
}

