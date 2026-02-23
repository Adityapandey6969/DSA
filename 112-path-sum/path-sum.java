class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        
        // Base case: empty tree
        if (root == null) {
            return false;
        }
        
        // If it's a leaf node, check if remaining sum equals node value
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        
        // Subtract current node value from target
        int remainingSum = targetSum - root.val;
        
        // Recursively check left and right subtree
        return hasPathSum(root.left, remainingSum) ||
               hasPathSum(root.right, remainingSum);
    }
}

