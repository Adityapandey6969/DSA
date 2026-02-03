import java.util.*;

class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> numbers = new ArrayList<>();
        int[] fact = new int[n + 1];
        fact[0] = 1;

        // factorials
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i;
            numbers.add(i);
        }

        k--; // convert to 0-based index
        StringBuilder sb = new StringBuilder();

        for (int i = n; i >= 1; i--) {
            int idx = k / fact[i - 1];
            sb.append(numbers.get(idx));
            numbers.remove(idx);
            k %= fact[i - 1];
        }

        return sb.toString();
    }
}

