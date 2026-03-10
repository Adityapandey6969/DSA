class Solution {
    public int numSteps(String s) {
        int steps = 0;
        int carry = 0;
        
        // Work from rightmost bit to leftmost (excluding the first bit)
        for (int i = s.length() - 1; i > 0; i--) {
            int digit = (s.charAt(i) - '0' + carry) % 2;
            
            if (digit == 0) {
                // Even number - divide by 2
                steps++;
            } else {
                // Odd number - add 1 then divide by 2
                steps += 2;
                carry = 1;
            }
        }
        
        // Handle the first bit with carry
        // If we have a carry at the end, we need one more step
        if (carry == 1) {
            steps++;
        }
        
        return steps;
    }
}