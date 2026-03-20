class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        
        while (left < right) {
            // Area = width × min height
            int width = right - left;
            int minHeight = Math.min(height[left], height[right]);
            maxArea = Math.max(maxArea, width * minHeight);
            
            // Move the shorter side inward
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        return maxArea;
    }
}