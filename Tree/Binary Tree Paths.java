/*
	Binary Tree Paths 
	Given a binary tree, return all root-to-leaf paths.

	For example, given the following binary tree:
		   1
		 /   \
		2     3
		 \
		  5
	All root-to-leaf paths are:
	["1->2->5", "1->3"]
*/

class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList();
        dfs(root, res, "");
        return res;
    }
    
    public void dfs(TreeNode root, List<String> res, String item) {
        if (root == null)
           return;
        if (root.left == null && root.right == null) {
            res.add(item + root.val);
            return;
        }
        dfs(root.left, res, item + root.val + "->");
        dfs(root.right, res, item + root.val + "->");
        
    }
}