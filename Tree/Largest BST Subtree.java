/*
	Largest BST Subtree

	Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

	Note:
	A subtree must include all of its descendants.
	Here's an example:
	    10
	    / \
	   5  15
	  / \   \ 
	 1   8   7
	The Largest BST Subtree in this case is the highlighted one. 
	The return value is the subtree's size, which is 3.
	Hint:

	You can recursively use algorithm similar to 98. Validate Binary Search Tree at each node of the tree, which will result in O(nlogn) time complexity.

	Follow up:
	Can you figure out ways to solve it with O(n) time complexity?
 */

// Solution 1 prefer
public class Solution {
    class Node {
        int size;
        int lower;
        int upper;
        
        Node(int size, int lower, int upper) {
            this.size = size;
            this.lower = lower;
            this.upper = upper;
        }
    }
    int max = 0;
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        traverse(root);
        return max;
    }
    private Node traverse(TreeNode root) {
        if (root == null) {
            return new Node(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        Node left = traverse(root.left);
        Node right = traverse(root.right);
        if (left.size == -1 || right.size == -1 || root.val <= left.upper || root.val >= right.lower) {
            return new Node(-1, 0, 0);
        }
        int size = left.size + 1 + right.size;
        max = Math.max(size, max);
        return new Node(size, Math.min(left.lower, root.val), Math.max(right.upper, root.val));
    }
}

// Solution 2
public int largestBSTSubtree(TreeNode root) {
    if (root == null) 
        return 0;
    if (root.left == null && root.right == null) 
        return 1;
    if (isValid(root, null, null)) 
        return countNode(root);
    return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
}

public boolean isValid(TreeNode root, Integer min, Integer max) {
    if (root == null) 
        return true;
    if (min != null && min >= root.val) 
        return false;
    if (max != null && max <= root.val) 
        return false;
    return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
}

public int countNode(TreeNode root) {
    if (root == null) 
        return 0;
    if (root.left == null && root.right == null) 
        return 1;
    return 1 + countNode(root.left) + countNode(root.right);
}