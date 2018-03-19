/*
	Construct Binary Tree from Preorder and Inorder Traversal 
	Given preorder and inorder traversal of a tree, construct the binary tree.

	Note:
	You may assume that duplicates do not exist in the tree.
*/

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


/*
	Solution:
		 1       
	    / \   
	   2   3   
	  / \ / \   
	 4  5 6  7
	 对于上图的树来说，
        index: 0 1 2 3 4 5 6
     先序遍历为: 1 2 4 5 3 6 7 
     中序遍历为: 4 2 5 1 6 3 7
	。
	可以发现的规律是：
	1. 先序遍历的从左数第一个为整棵树的根节点。
	2. 中序遍历中根节点是左子树右子树的分割点。

	再看这个树的左子树：
	     先序遍历为: 2 4 5 
	     中序遍历为: 4 2 5
	依然可以套用上面发现的规律。

	右子树：
	     先序遍历为: 3 6 7 
	     中序遍历为: 6 3 7
	也是可以套用上面的规律的。

	所以这道题可以用递归的方法解决。
	具体解决方法是：
	1.通过先序遍历找到第一个点作为根节点，在中序遍历中找到根节点并记录index。
	2.因为中序遍历中根节点左边为左子树，所以可以记录左子树的长度并在先序遍历中依据这个长度找到左子树的区间，用同样方法可以找到右子树的区间。
	3.递归的建立好左子树和右子树就好。
*/
public class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
    	int preLength = preorder.length;
    	int inLength = inorder.length;

    	return buildTree(preorder, 0, preLength - 1, inorder, 0, inLength - 1);
    }

    public TreeNode buildTree(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
    	if (preStart > preEnd)
    		return null;
				TreeNode root = new TreeNode(pre[preStart]);
        int rootIndex = 0;
        for (int i = 0; i < in.length; i++) {
            if (in[i] == root.val) {
                rootIndex = i;
                break;
            }
        }

		  	int len = rootIndex - inStart;
		  	TreeNode root = new TreeNode(rootVal);
		  	root.left = buildTree(pre, preStart + 1, preStart + len, in, inStart, rootIndex - 1);
		  	root.right = buildTree(pre, preStart + len + 1, preEnd, in, rootIndex + 1, inEnd);
		  	return root;
    }
}