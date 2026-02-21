class Solution {
    public String minWindow(String s, String t) {
        
        if (s.length() < t.length()) return "";
        
        int[] need = new int[128];
        int[] window = new int[128];
        
        // Store frequency of characters in t
        for (char c : t.toCharArray()) {
            need[c]++;
        }
        
        int left = 0, right = 0;
        int required = t.length();
        int formed = 0;
        
        int minLen = Integer.MAX_VALUE;
        int start = 0;
        
        while (right < s.length()) {
            char c = s.charAt(right);
            
            window[c]++;
            
            if (need[c] > 0 && window[c] <= need[c]) {
                formed++;
            }
            
            // Try to shrink window
            while (formed == required) {
                
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }
                
                char leftChar = s.charAt(left);
                window[leftChar]--;
                
                if (need[leftChar] > 0 && window[leftChar] < need[leftChar]) {
                    formed--;
                }
                
                left++;
            }
            
            right++;
        }
        
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}