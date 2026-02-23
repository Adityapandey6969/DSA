import java.util.*;

class Solution {
    
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return build(1, n);
    }
    
    private List<TreeNode> build(int start, int end) {
        List<TreeNode> trees = new ArrayList<>();
        
        if (start > end) {
            trees.add(null);
            return trees;
        }
        
        for (int i = start; i <= end; i++) {
            
            // Generate all left and right subtrees
            List<TreeNode> leftTrees = build(start, i - 1);
            List<TreeNode> rightTrees = build(i + 1, end);
            
            // Combine them
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    trees.add(root);
                }
            }
        }
        
        return trees;
    }
}

