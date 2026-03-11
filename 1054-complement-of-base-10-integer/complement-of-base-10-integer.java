class Solution {
    public int bitwiseComplement(int n) {
        if (n == 0) return 1;

        int mask = 0;
        int temp = n;

        // create mask like 111... of same bit length
        while (temp > 0) {
            mask = (mask << 1) | 1;
            temp >>= 1;
        }

        return mask ^ n;
    }
}