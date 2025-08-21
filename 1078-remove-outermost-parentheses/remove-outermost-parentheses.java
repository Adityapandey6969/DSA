class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int balance = 0;  // keeps track of open vs close

        for (char c : s.toCharArray()) {
            if (c == '(') {
                if (balance > 0) {
                    result.append(c);  // not the outermost '('
                }
                balance++;
            } else { // c == ')'
                balance--;
                if (balance > 0) {
                    result.append(c);  // not the outermost ')'
                }
            }
        }

        return result.toString();
    }
}
