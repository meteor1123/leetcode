/*
	Count Univalue Subtrees

	Given a binary tree, count the number of uni-value subtrees.

	A Uni-value subtree means all nodes of the subtree have the same value.

	For example:
	Given binary tree,
	              5
	             / \
	            1   5
	           / \   \
	          5   5   5
	return 4.

*/

/*
    1. 就是对一个树找所有的子树，并且子树中所有的value都相同, 对于例子中 三个叶节点5都算, 另外一个是root的右儿子
    2. 并不需要和root的value相等
 */

//Solution1
class Solution {
    int res = 0;
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null)
            return 0;
        helper(root);
        return res;
    }
    
    public boolean helper(TreeNode root) {
        if (root == null)
            return true;
        // use post order traverse, compare left and right children, and then compare whether same with the parent.
        boolean left = helper(root.left);
        boolean right = helper(root.right);
        
        if (left && right) {
            if (root.left != null && root.val != root.left.val) {
                return false;
            }
            if (root.right != null && root.val != root.right.val) {
                return false;
            }
            res++;
            return true;
        }
        return false;
    }
}


//Solution2
public class Solution {
    int count = 0;
    public int countUnivalSubtrees(TreeNode root) {
        all(root, 0);
        return count;
    }
    public boolean all(TreeNode root, int val) {
        if (root == null)
            return true;
        if (!all(root.left, root.val) | !all(root.right, root.val))// 要是 || 或，计算第一个的结果如果是正确的话，就不会计算后面的，就会漏算后面的计数，这就是short-circuit
                                                                   // | 或 无论前面的结果如何，都会将所有表达式运行
            return false;
        count++;
        return root.val == val;
    }
}