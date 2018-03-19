/*
	 Lowest Common Ancestor
	 Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.


	Example
	        4

	    /     \

	  3         7

	          /     \

	        5         6

	For 3 and 5, the LCA is 4.

	For 5 and 6, the LCA is 7.

	For 6 and 7, the LCA is 7.
*/

public class Solution {
	//Solution1: recursive
	public TreeNode lowestCommonAncester(TreeNode root, TreeNode A, TreeNode B) {
		if (root == null) {
			return root;
		}
		//if at least one matched, no need to continue
		//that is the LCA for this root
		if (root == A || root == B) {
			return root;
		}
		//recursive find the lowestCommonAncester in left tree, and right tree
		TreeNode leftCh = lowestCommonAncester(root.left, A, B);
		TreeNode rightCh = lowestCommonAncester(root.right, A, B);
		//if leftCh and rightCh are not null,which means they are in separate branch
		if (leftCh != null && rightCh != null) {
			return root; //
		}
		//either one node is on one branch,
		//or none was found in any of the branches
		return leftCh == null ? rightCh : leftCh;
	}

	//Solution2: iterative
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (true) {
            if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else {
                return root;
            }
        }
    }
}