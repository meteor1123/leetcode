/*
	Insert Node in a Binary Search Tree
	Given a binary search tree and a new tree node, insert the node into the tree. You should keep the tree still be a valid binary search tree.

	Have you met this question in a real interview? Yes
	Example
	Given binary search tree as follow, after Insert node 6, the tree should be:

	  2             2
	 / \           / \
	1   4   -->   1   4
	   /             / \ 
	  3             3   6
	Challenge
	Can you do it without recursion?
 */

//Recursive
public class Solution {
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        // write your code here
        if (root == null) {
            return node;
        }
        if (root.val < node.val) {
           root.right = insertNode(root.right, node);
        }
        if (root.val > node.val) {
            root.left = insertNode(root.left, node);
        }
        
        return root;
    }
}

//Iterative
public class Solution {
	public TreeNode insertNode(TreeNode root, TreeNode node) {
	    if (root == null) {
	        return node;
	    }
	    TreeNode cur = root;
	    TreeNode pre = null;
	    while (cur != null) {
	        pre = cur;
	        if (node.val > cur.val) {
	            cur = cur.right;
	        } else if (node.val < cur.val) {
	            cur = cur.left;
	        } 
	    }
	    
	    if (pre.val < node.val) {
	        pre.right = node;
	    } else {
	        pre.left = node;
	    }
	    return root;
	}
}

