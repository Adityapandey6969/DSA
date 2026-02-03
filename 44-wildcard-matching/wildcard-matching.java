class Solution {
    public boolean isMatch(String s, String p) {
        int i = 0, j = 0;
        int starIdx = -1, match = 0;

        while (i < s.length()) {
            // Characters match or '?'
            if (j < p.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '?')) {
                i++;
                j++;
            }
            // '*' found, store position
            else if (j < p.length() && p.charAt(j) == '*') {
                starIdx = j;
                match = i;
                j++;
            }
            // Previous '*' can match more characters
            else if (starIdx != -1) {
                j = starIdx + 1;
                match++;
                i = match;
            }
            // No match possible
            else {
                return false;
            }
        }

        // Remaining characters in pattern must all be '*'
        while (j < p.length() && p.charAt(j) == '*') {
            j++;
        }

        return j == p.length();
    }
}
 