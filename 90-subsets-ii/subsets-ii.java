import java.util.*;

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);  // Step 1: sort
        
        backtrack(0, nums, new ArrayList<>(), result);
        return result;
    }
    
    private void backtrack(int start, int[] nums, 
                           List<Integer> current, 
                           List<List<Integer>> result) {
        
        // Add current subset (every node is a valid subset)
        result.add(new ArrayList<>(current));
        
        for (int i = start; i < nums.length; i++) {
            
            // Skip duplicates at same recursion level
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            
            current.add(nums[i]);                 // choose
            backtrack(i + 1, nums, current, result); // explore
            current.remove(current.size() - 1);   // backtrack
        }
    }
}