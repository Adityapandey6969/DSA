class Solution {
    public int maxDepth(TreeNode root) {
        
        // Base case: empty tree
        if (root == null) {
            return 0;
        }
        
        // Recursively find depth of left and right subtree
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        
        // Return max depth including current node
        return 1 + Math.max(leftDepth, rightDepth);
    }
}

