/*
	653. Two Sum IV - Input is a BST

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


// Time Complexity: O(n), Space Complexity: O(n).
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        HashSet<Integer> set = new HashSet();
        return helper(root, set, k);
    }
    
    public boolean helper(TreeNode root, HashSet<Integer> set, int k) {
        if (root == null)
            return false;

        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val); // don't put this above the HashSet check, avoid the case [1], 2
        return helper(root.left, set, k) || helper(root.right, set, k);
    }
}

// Time: O(n), Space: ave O(logn) worst(n)
class Solution {
  public boolean findTarget(TreeNode root, int k) {
      return dfs(root, root, k);
  }
  
  public boolean dfs(TreeNode root, TreeNode cur, int k) {
      if (cur == null)
          return false;
      return search(root, cur, k - cur.val) || dfs(root, cur.left, k) || dfs(root, cur.right, k);
  }
  
  public boolean search(TreeNode root, TreeNode cur, int value) {
      if (root == null)
          return false;
      return (root.val == value) && (root != cur)
          || (root.val < value) && search(root.right, cur, value)
          || (root.val > value) && search(root.left, cur, value);
  }
}