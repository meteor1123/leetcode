/*
	Path Sum II 
	Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

	For example:
	Given the below binary tree and sum = 22,

	          5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1

    return
	[
	   [5,4,11,2],
	   [5,8,4,5]
	]
*/

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList();
        if (root == null)
            return res;
        List<Integer> item = new ArrayList();
        helper(root, item, sum, res);
        return res;
    }
    
    public void helper(TreeNode root, List<Integer> item, int sum, List<List<Integer>> res) {
        if (root == null)
            return;
        item.add(root.val);
        if (root.left == null && root.right == null && root.val == sum) {
            res.add(new ArrayList(item));
        } else {
            helper(root.left, item, sum - root.val, res);
            helper(root.right, item, sum - root.val, res); 
        }
        
        item.remove(item.size() - 1);
    }
}