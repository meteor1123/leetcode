/*
	437. Path Sum III

	You are given a binary tree in which each node contains an integer value.

	Find the number of paths that sum to a given value.

	The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

	The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

	Example:

	root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

	      10
	     /  \
	    5   -3
	   / \    \
	  3   2   11
	 / \   \
	3  -2   1

	Return 3. The paths that sum to 8 are:

	1.  5 -> 3
	2.  5 -> 2 -> 1
	3. -3 -> 11
*/


// Space: O(n) due to recursion.
// Time: O(n^2) in worst case (no branching); O(nlogn) in best case (balanced tree).
class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null)
            return 0;
        return pathSum(root.left, sum) + helper(root, sum) + pathSum(root.right, sum);
    }
    
    public int helper(TreeNode root, int sum) {
        if (root == null)
            return 0;
        return (root.val == sum ? 1 : 0) + helper(root.left, sum - root.val) + helper(root.right, sum - root.val);
    }
}

// time: O(n)
class Solution {
    public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSum = new HashMap();
        preSum.put(0, 1);
        return helper(root, 0, sum, preSum);
    }
    
    public int helper(TreeNode root, int curSum, int target, HashMap<Integer, Integer> preSum) {
        if (root == null)
            return 0;
        curSum += root.val;
        int res = preSum.getOrDefault(curSum - target, 0);
        preSum.put(curSum, preSum.getOrDefault(curSum, 0) + 1);
        res += helper(root.left, curSum, target, preSum) + helper(root.right, curSum, target, preSum);
        preSum.put(curSum, preSum.get(curSum) - 1);
        return res;
    }
 }