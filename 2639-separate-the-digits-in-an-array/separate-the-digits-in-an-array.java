import java.util.*;

class Solution {
    public int[] separateDigits(int[] nums) {
        List<Integer> list = new ArrayList<>();
        
        for (int num : nums) {
            // Extract digits into stack to maintain order
            Stack<Integer> stack = new Stack<>();
            
            if (num == 0) {
                stack.push(0);
            } else {
                while (num > 0) {
                    stack.push(num % 10);
                    num /= 10;
                }
            }
            
            while (!stack.isEmpty()) {
                list.add(stack.pop());
            }
        }
        
        // Convert list to array
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        
        return result;
    }
}