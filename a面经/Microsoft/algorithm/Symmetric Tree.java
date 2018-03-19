/*
	Symmetric Tree 
	Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
	For example, this binary tree is symmetric:
		1
	   / \
	  2   2
	 / \ / \
	3  4 4  3

	But the following is not:
	    1
	   / \
	  2   2
	   \   \
	   3    3
	Note:
	Bonus points if you could solve it both recursively and iteratively.

*/
/*
	time complexity:O(n);
	space complexity:O(logn);
*/

public class Solution {

	//DFS
    public boolean isSymmetric(TreeNode root) {
    	if (root == null) {
    		return true;
    	}
    	return isSymmetric(root.left, root.right);
    }
    public boolean isSymmetric(TreeNode p, TreeNode q) {
    	if (p == null && q == null) {
    		return true;
    	}
    	if (p == null || q == null) {
    		return false;
    	}
    	return p.val == q.val && isSymmetric(p.left, q.right) && isSymmetric(p.right, q.left);
    }

    //Two Stack iterative
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
    
        Stack<TreeNode> L = new Stack<TreeNode>();
        Stack<TreeNode> R = new Stack<TreeNode>();
        L.push(root.left);
        R.push(root.right);
        while (!L.isEmpty() && !R.isEmpty()) {
            TreeNode lNode = L.pop();
            TreeNode rNode = R.pop();
            
            if (lNode == null && rNode == null) {
                continue;
            }
            if (lNode == null || rNode == null) {
                return false;
            }
            if (lNode.val != rNode.val) {
                return false;
            }
            L.push(lNode.left);
            R.push(rNode.right);
            L.push(lNode.right);
            R.push(rNode.left);
        }
        return true;
    }

    //One Stack iterative
     public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
    
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root.left);
        stack.push(root.right);
        while (!stack.isEmpty()) {
            TreeNode lNode = stack.pop();
            TreeNode rNode = stack.pop();
            
            if (lNode == null && rNode == null) {
                continue;
            }
            if (lNode == null || rNode == null) {
                return false;
            }
            if (lNode.val != rNode.val) {
                return false;
            }
            stack.push(lNode.left);
            stack.push(rNode.right);
            stack.push(lNode.right);
            stack.push(rNode.left);
        }
        return true;
    }
}