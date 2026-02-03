class Solution {
    public String multiply(String num1, String num2) {
        // If either number is "0", result is "0"
        if (num1.equals("0") || num2.equals("0")) return "0";

        int n = num1.length();
        int m = num2.length();
        int[] result = new int[n + m];

        // Multiply each digit (like manual multiplication)
        for (int i = n - 1; i >= 0; i--) {
            int d1 = num1.charAt(i) - '0';
            for (int j = m - 1; j >= 0; j--) {
                int d2 = num2.charAt(j) - '0';
                int sum = d1 * d2 + result[i + j + 1];
                result[i + j + 1] = sum % 10;
                result[i + j] += sum / 10;
            }
        }

        // Build the result string (skip leading zeros)
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while (idx < result.length && result[idx] == 0) idx++;

        while (idx < result.length) {
            sb.append(result[idx++]);
        }

        return sb.toString();
    }
}

