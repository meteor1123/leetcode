/*
	Binary Tree Upside Down
	Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

	For example:
	Given a binary tree {1,2,3,4,5},
	    1
	   / \
	  2   3
	 / \
	4   5
	return the root of the binary tree [4,5,2,#,#,3,1].
	   4
	  / \
	 5   2
	    / \
	   3   1  
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
	public TreeNode upsideDownBinaryTree(TreeNode root) {
		if (root == null || root.left == null && root.right == null) {
			return root;
		}

		//每次返回的newRoot 
		TreeNode newRoot = upsideDownBinaryTree(root.left);
		//每次迭代的将当前root的right结点 赋值给下面left孩子的 right节点，
		//将root赋值给下面left孩子的left结点，然后设置root.left , root.right
		root.left.left = root.right;//该节点的父亲节点的右节点为该节点的左孩子
		root.left.right = root;//该节点的父亲节点为该节点的右孩子
		root.left = null;//父节点的左右孩子设空
		root.right = null;//父节点的左右孩子设空
		//依然返回root结点
		return newRoot;
	}
}

//iterative
public class Solution {
    public TreeNode UpsideDownBinaryTree(TreeNode root) {
        TreeNode cur = root;
        TreeNode pre = null;
        TreeNode next = null;
        TreeNode temp = null;

        while (cur != null) {
            next = cur.left;
            cur.left = temp;
            temp = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }
}