/*
	Inorder Successor in BST
	Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

	Note: If the given node has no in-order successor in the tree, return null.

	Tags: Tree
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
    //Solution1:
    // public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    //     if (root == null) {
    //         return null;
    //     }
        
    //     ArrayList<TreeNode> res = new ArrayList<>();
    //     inorder(root, res);
    //     for (int i = 0; i < res.size(); i++) {
    //         if (res.get(i) == p && i + 1 < res.size()) {
    //             return res.get(i + 1);
    //         }
    //     }
    //     return null;
    // }
    
    // public void inorder(TreeNode root, ArrayList<TreeNode> res) {
    //     if (root == null) {
    //         return;
    //     }
    //     inorder(root.left, res);
    //     res.add(root);
    //     inorder(root.right, res);
    // }
    
    //Solution2
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }

        if (root.val <= p.val) {
            return inorderSuccessor(root.right, p);
        } else {//返回root 右孩子中的最左边的结点也就是最小的大于root的节点，不存在就返回root
            TreeNode left = inorderSuccessor(root.left, p);
            return (left != null) ? left : root;
        }
    }

    //predecessor
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        if (root.val >= p.val) {
            return inorderSuccessor(root.left, p);
        } else {//返回root 左孩子中的最右边的结点也就是最大的小于root的节点，不存在就返回root
            TreeNode right = inorderSuccessor(root.right, p);
            return (right != null) ? right : root;
        }
    }

}