/*
	Closest Binary Search Tree Value

	Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

	Note:
	Given target value is a floating point.
	You are guaranteed to have only one unique value in the BST that is closest to the target.
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

 public class Solution {
 	//Recursive
    public int closestValue(TreeNode root, double target) {
        TreeNode kid = target < root.val ? root.left : root.right;
        if (kid == null) {
            return root.val;
        }
        int b = closestValue(kid, target);
        return Math.abs(root.val - target ) < Math.abs(b - target) ? root.val : b;
    }
}

//Iterative
public class Solution {
	public int closestValue(TreeNode root, double target) {
        int closet = root.val;
        while (root != null) {
            if (Math.abs(closet - target) >= Math.abs(root.val - target)) {
                closet = root.val;
            }
            root = target < root.val ? root.left : root.right;
        }
        return closet;
    }
}