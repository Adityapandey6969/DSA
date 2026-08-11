class Solution {
    public int missingInteger(int[] nums) {
        int sum = nums[0];

        // Find the sum of the longest sequential prefix
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
            } else {
                break;
            }
        }

        // Find the smallest missing integer >= sum
        boolean[] seen = new boolean[1001];

        for (int num : nums) {
            if (num <= 1000) {
                seen[num] = true;
            }
        }

        while (sum <= 1000 && seen[sum]) {
            sum++;
        }

        return sum;
    }
}