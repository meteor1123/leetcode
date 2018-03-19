/*
	404. Sum of Left Leaves

	Find the sum of all left leaves in a given binary tree.

	Example:

	    3
	   / \
	  9  20
	    /  \
	   15   7

	There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
*/

// My Solution
class Solution {
    // bfs
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null)
            return 0;
        Queue<TreeNode> queue = new ArrayDeque();
        queue.offer(root);
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                    if (node.left.left == null && node.left.right == null)
                        res += node.left.val;
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return res;
    }
}

class Solution {
	// dfs
    int res = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null)
            return 0;
        dfs(root);
        return res; 
    }
    
    public void dfs(TreeNode root) {
        if (root == null)
            return;
        dfs(root.left);
        if (root.left != null && root.left.left == null && root.left.right == null) {
            res += root.left.val; // 1. don't return in this step, still need to recursive compute right children
            											// 2. we actually add the value of left leaf in the parent level;
        }
        dfs(root.right);
    }
}