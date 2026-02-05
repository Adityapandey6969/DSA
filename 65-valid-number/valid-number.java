class Solution {
    public boolean isNumber(String s) {
        boolean seenDigit = false;
        boolean seenDot = false;
        boolean seenExp = false;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (Character.isDigit(c)) {
                seenDigit = true;
            } 
            else if (c == '+' || c == '-') {
                // sign is allowed only at start or just after e/E
                if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } 
            else if (c == '.') {
                // dot not allowed after exponent or if already seen
                if (seenDot || seenExp) return false;
                seenDot = true;
            } 
            else if (c == 'e' || c == 'E') {
                // exponent must appear once and after a digit
                if (seenExp || !seenDigit) return false;
                seenExp = true;
                seenDigit = false; // must have digits after e/E
            } 
            else {
                return false;
            }
        }
        
        return seenDigit;
    }
}
 