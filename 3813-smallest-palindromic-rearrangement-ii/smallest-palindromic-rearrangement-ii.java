class Solution {
    private static final long LIMIT = 1_000_001L;

    public String smallestPalindrome(String s, int k) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        int[] half = new int[26];
        String mid = "";

        for (int i = 0; i < 26; i++) {
            half[i] = cnt[i] / 2;
            if ((cnt[i] & 1) == 1) {
                mid = String.valueOf((char) ('a' + i));
            }
        }

        if (countWays(half) < k) {
            return "";
        }

        StringBuilder left = new StringBuilder();
        int len = s.length() / 2;

        for (int pos = 0; pos < len; pos++) {
            for (int c = 0; c < 26; c++) {
                if (half[c] == 0) continue;

                half[c]--;
                long ways = countWays(half);

                if (ways >= k) {
                    left.append((char) ('a' + c));
                    break;
                } else {
                    k -= ways;
                    half[c]++;
                }
            }
        }

        StringBuilder ans = new StringBuilder(left);
        ans.append(mid);
        ans.append(new StringBuilder(left).reverse());

        return ans.toString();
    }

    private long countWays(int[] cnt) {
        int total = 0;
        for (int x : cnt) total += x;

        long res = 1;

        for (int x : cnt) {
            if (x == 0) continue;
            res *= nCr(total, x);
            if (res >= LIMIT) return LIMIT;
            total -= x;
        }

        return res;
    }

    private long nCr(int n, int r) {
        r = Math.min(r, n - r);
        long res = 1;

        for (int i = 1; i <= r; i++) {
            res = res * (n - i + 1) / i;
            if (res >= LIMIT) return LIMIT;
        }

        return res;
    }
}