class Solution {
    public int divide(int dividend, int divisor) {
        // Overflow case
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Determine sign
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // Use long-like behavior via negatives to avoid overflow
        int a = dividend;
        int b = divisor;

        a = (a > 0) ? -a : a;
        b = (b > 0) ? -b : b;

        int result = 0;

        while (a <= b) {
            int temp = b;
            int multiple = 1;

            while (temp >= Integer.MIN_VALUE / 2 && a <= temp + temp) {
                temp += temp;
                multiple += multiple;
            }

            a -= temp;
            result += multiple;
        }

        return negative ? -result : result;
    }
}
 