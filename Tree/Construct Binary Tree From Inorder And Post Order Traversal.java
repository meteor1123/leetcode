/*
	Construct Binary Tree from Inorder and Postorder Traversal 
	Given inorder and postorder traversal of a tree, construct the binary tree.

	Note:
	You may assume that duplicates do not exist in the tree.
*/



/*	
	这道题跟pre+in一样的方法做，只不过找左子树右子树的位置不同而已。 

 

          1       
         / \   
        2   3   
       / \ / \   
      4  5 6  7

对于上图的树来说，
        index: 0 1 2 3 4 5 6
     中序遍历为: 425 1 637
     后续遍历为: 452 673 1
为了清晰表示，我给节点上了颜色，红色是根节点，蓝色为左子树，绿色为右子树。
可以发现的规律是：
1. 中序遍历中根节点是左子树右子树的分割点。
2. 后续遍历的最后一个节点为根节点。

同样根据中序遍历找到根节点的位置，然后顺势计算出左子树串的长度。在后序遍历中分割出左子树串和右子树串，递归的建立左子树和右子树。
*/

public class Solution {
     public TreeNode buildTree(int[] inorder, int[] postorder) {
    	  int inLen = inorder.length;
    	  int postLen = postorder.length;
        return buildTree(postorder, 0, postLen - 1, inorder, 0, inLen - 1);
    }

    public TreeNode buildTree(int[] post, int postStart, int postEnd, int[] in, int inStart, int inEnd) {
    	if (inStart > inEnd || postStart > postEnd)
    		return null;

    	//后序遍历数组的最后一位肯定是根节点
    	int rootVal = post[postEnd];
    	//后序遍历数据的最后一位下标记录
    	int rootIndex = postEnd;
    	for (int i = inStart; i <= inEnd; i++) {
    		if (rootVal == in[i]) {
    			rootIndex = i;
    			break;
    		}
    	}
    	int len = rootIndex - inStart;
    	TreeNode root = new TreeNode(rootVal);
    	// inStart到rootIndex - 1是 左边子树在中序遍历数组的范围
    	// 
    	root.left = buildTree(post, postStart, postStart + len - 1, in, inStart, rootIndex - 1);
    	root.right = buildTree(post, postStart + len, postEnd - 1, in, rootIndex + 1, inEnd);
    	return root;	
    }

}