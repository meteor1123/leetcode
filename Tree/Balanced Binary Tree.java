/*
	Balanced Binary Tree 
	Given a binary tree, determine if it is height-balanced.

	For this problem, a height-balanced binary tree is defined as a binary tree in which 
	the depth of the two subtrees of every node never differ by more than 1.
*/

public class Solution {
	//solution 2 更容易理解
	public boolean isBalanced(TreeNode root) {
		//递归终止条件
		// 1.root为空返回true
		if (root == null)
			return true;
		// 2.左右子树的最大深度相减不超过1
		if (Math.abs(maxDepth(root.left) - maxDepth(root.right)) > 1)
			return false;

		return isBalanced(root.left) && isBalanced(root.right);
	}

	public int maxDepth(TreeNode root) {
		if (root == null)
			return 0;
		int leftMaxDepth = maxDepth(root.left);
		int rightMaxDepth = maxDepth(root.right);

		return Math.max(leftMaxDepth, rightMaxDepth) + 1;

	}
}

// my solution prefer:
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        return Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }
    
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}

