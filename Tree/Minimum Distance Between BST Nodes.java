/*
	783. Minimum Distance Between BST Nodes

	Given a Binary Search Tree (BST) with the root node root, return the minimum difference between the values of any two different nodes in the tree.

	Example :

	Input: root = [4,2,6,1,3,null,null]
	Output: 1
	Explanation:
	Note that root is a TreeNode object, not an array.

	The given tree [4,2,6,1,3,null,null] is represented by the following diagram:

	          4
	        /   \
	      2      6
	     / \    
	    1   3  

	while the minimum difference in this tree is 1, it occurs between node 1 and node 2, also between node 3 and node 2.
	Note:

	The size of the BST will be between 2 and 100.
	The BST is always valid, each node's value is an integer, and each node's value is different.
*/



// trival solution, O(n) space
class Solution {
    public int minDiffInBST(TreeNode root) {
        if (root == null)
            return 0;
        List<Integer> item = new ArrayList();
        helper(root, item);
        
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < item.size(); i++) {
            res = Math.min(res, Math.abs(item.get(i) - item.get(i - 1)));
        }
        return res;
    }
    
    public void helper(TreeNode root, List<Integer> res) {
        if (root == null)
            return ;
        
        helper(root.left, res);
        res.add(root.val);
        helper(root.right, res);
    }
}


// Prefer, O(1) space, user Global variable and Integer pre to check whether has pre value
class Solution {
    Integer res = Integer.MAX_VALUE;
    Integer pre = null;
    public int minDiffInBST(TreeNode root) {
        if (root.left != null)
            minDiffInBST(root.left);
        if (pre != null)
            res = Math.min(root.val - pre, res);
        pre = root.val;
        if (root.right != null)
            minDiffInBST(root.right);
        return res;
    }
}