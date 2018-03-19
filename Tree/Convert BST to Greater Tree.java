/*
	538. Convert BST to Greater Tree

	Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

	Example:

	Input: The root of a Binary Search Tree like this:
	              5
	            /   \
	           2     13

	Output: The root of a Greater Tree like this:
	             18
	            /   \
	          20     13
*/


// useing global variable
class Solution {
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if (root == null)
            return null;
        convert(root);
        return root;
    }
    
    public void convert(TreeNode root) {
        if (root == null)
            return;
        convert(root.right);
        root.val += sum;
        sum = root.val;
        convert(root.left);
    }
}


// doesn't use global variable
class Solution {
	public TreeNode convertBST(TreeNode root) {
      if (root == null)
          return null;
      dfs(root, 0);
      return root;
  }
    
  private int dfs(TreeNode root, int sum) {
      if (root == null)
          return sum;
      int rightSum = dfs(root.right, sum);
      root.val += rightSum;
      return dfs(root.left, root.val);
  }
}