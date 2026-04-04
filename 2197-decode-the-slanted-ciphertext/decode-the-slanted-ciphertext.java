class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        if (encodedText.isEmpty()) {
            return "";
        }

        int len = encodedText.length();
        int cols = len / rows;

        StringBuilder sb = new StringBuilder();

        // Traverse each diagonal starting from top row
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                int index = row * cols + (col + row);
                if (index < len) {
                    sb.append(encodedText.charAt(index));
                }
            }
        }

        // Trim trailing whitespace (common padding in slanted encodings)
        int end = sb.length() - 1;
        while (end >= 0 && sb.charAt(end) == ' ') {
            end--;
        }

        return sb.substring(0, end + 1);
    }
}