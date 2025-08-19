class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;      // current streak of 1s
        int maxCount = 0;   // maximum streak found

        for (int num : nums) {
            if (num == 1) {
                count++;
                maxCount = Math.max(maxCount, count);
            } else {
                count = 0; // reset when 0 is encountered
            }
        }
        return maxCount;
    }
}
