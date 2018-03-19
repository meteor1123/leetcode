/*
	Maximum Binary Tree
	
	Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

	The root is the maximum number in the array.
	The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
	The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
	Construct the maximum tree by the given array and output the root node of this tree.

	Example 1:
		Input: [3,2,1,6,0,5]
		Output: return the tree root node representing the following tree:

		      6
		    /   \
		   3     5
		    \    / 
		     2  0   
		       \
		        1
		Note:
		The size of the given array will be in the range [1,1000].
*/

/*
	Solution:
			1. 理解题意， root是maximum的number in array， 左/右子树是被root divided之后左/右那一部分数组的值中 按照maximum number当root的规则去constructed，一样的规则

			2. 首先应该想到这里用的是 pre order, 先找root 之后再递归找左右子树
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// by myself
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;
        return buildTree(nums, 0, nums.length - 1);
    }
    
    public TreeNode buildTree(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int maxVal = Integer.MIN_VALUE;
        int maxIndex = 0;
        for (int i = start; i <= end; i++) {
            if (nums[i] > maxVal) {
                maxVal = nums[i];
                maxIndex = i;
            }
        }
        
        TreeNode root = new TreeNode(maxVal);
        root.left = buildTree(nums, start, maxIndex - 1);
        root.right = buildTree(nums, maxIndex + 1, end);
        return root;
    }
}