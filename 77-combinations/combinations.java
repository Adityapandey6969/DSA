import java.util.*;

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, n, k, new ArrayList<>(), result);
        return result;
    }
    
    private void backtrack(int start, int n, int k, 
                           List<Integer> current, 
                           List<List<Integer>> result) {
        
        // Base case: if combination size == k
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        // Pruning optimization
        for (int i = start; i <= n - (k - current.size()) + 1; i++) {
            
            current.add(i);                 // choose
            backtrack(i + 1, n, k, current, result); // explore
            current.remove(current.size() - 1); // un-choose (backtrack)
        }
    }
}