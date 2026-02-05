class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                result[i] = 0;
            } else {
                int move = nums[i] % n;   // reduce extra cycles
                int newIndex = (i + move) % n;
                
                if (newIndex < 0) {
                    newIndex += n;       // handle negative wrap
                }
                
                result[i] = nums[newIndex];
            }
        }

        return result;
    }
}
 