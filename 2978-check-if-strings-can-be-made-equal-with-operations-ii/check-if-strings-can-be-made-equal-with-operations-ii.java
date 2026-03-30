class Solution {
    public boolean checkStrings(String s1, String s2) {
        // Characters at even indices can be rearranged among even indices
        // Characters at odd indices can be rearranged among odd indices
        // So we just need to check if the frequency of characters at even indices
        // and odd indices are the same for both strings.
        
        int[][] freq = new int[2][26];
        
        for (int i = 0; i < s1.length(); i++) {
            freq[i % 2][s1.charAt(i) - 'a']++;
            freq[i % 2][s2.charAt(i) - 'a']--;
        }
        
        for (int p = 0; p < 2; p++) {
            for (int c = 0; c < 26; c++) {
                if (freq[p][c] != 0) return false;
            }
        }
        
        return true;
    }
}