class Solution {
    public int minFlips(String s) {
        int n = s.length();
        String ss = s + s;

        int diff1 = 0, diff2 = 0;
        int res = Integer.MAX_VALUE;

        for (int i = 0; i < ss.length(); i++) {
            char c = ss.charAt(i);

            char alt1 = (i % 2 == 0) ? '0' : '1';
            char alt2 = (i % 2 == 0) ? '1' : '0';

            if (c != alt1) diff1++;
            if (c != alt2) diff2++;

            if (i >= n) {
                char prev = ss.charAt(i - n);

                char prevAlt1 = ((i - n) % 2 == 0) ? '0' : '1';
                char prevAlt2 = ((i - n) % 2 == 0) ? '1' : '0';

                if (prev != prevAlt1) diff1--;
                if (prev != prevAlt2) diff2--;
            }

            if (i >= n - 1) {
                res = Math.min(res, Math.min(diff1, diff2));
            }
        }

        return res;
    }
}