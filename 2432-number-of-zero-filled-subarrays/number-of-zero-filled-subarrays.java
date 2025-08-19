class Solution {
    public long zeroFilledSubarray(int[] nums) {
        long count = 0;  // total subarrays
        long streak = 0; // current consecutive zeros

        for (int num : nums) {
            if (num == 0) {
                streak++;
                count += streak; // add subarrays ending here
            } else {
                streak = 0; // reset streak
            }
        }
        return count;
    }
}
