/*
	Lowest Common Ancestor of a Binary Search Tree
	Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

	According to the definition of LCA on Wikipedia: 
	“The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants 
	(where we allow a node to be a descendant of itself).”
*/

public class Solution {
    // iterative1
    // public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    //     while((root.val - p.val) * (root.val - q.val) > 0) {
    //         root = p.val < root.val ? root.left : root.right;
    //     }
    //     return root;
    // }

    //iterative2 prefer
    // public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    //     while (true) {
    //         if (root.val > p.val && root.val > q.val) {
    //             root = root.left;
    //         } else if (root.val < p.val && root.val < q.val) {
    //             root = root.right;
    //         } else {
    //             return root;
    //         }
    //     }
    // }
    
    // //recursive1 prefer
    // public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    //     if (root.val > p.val && root.val > q.val) {
    //         return lowestCommonAncestor(root.left, p, q);
    //     } else if (root.val < p.val && root.val < q.val) {
    //         return lowestCommonAncestor(root.right, p, q);
    //     } else {
    //         return root;
    //     }
    // }

    //recursive2 same as binary tree
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode leftCh = lowestCommonAncestor(root.left, p, q);
        TreeNode rightCh = lowestCommonAncestor(root.right, p, q);
        if (leftCh != null && rightCh != null) {
            return root;
        }
        return leftCh != null ? leftCh : rightCh;
    }
}