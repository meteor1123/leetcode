/*
	Unique Binary Search Trees II
	Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

	For example,
	Given n = 3, your program should return all 5 unique BST's shown below.

	   1         3     3      2      1
	    \       /     /      / \      \
	     3     2     1      1   3      2
	    /     /       \                 \
	   2     1         2                 3
*/

/* Solution :
	Start by noting that 1..n is the in-order traversal for any BST with nodes 1 to n. 
	So if I pick i-th node as my root, the left subtree will contain elements 1 to (i-1), 
	and the right subtree will contain elements (i+1) to n.
	 I use recursive calls to get back all possible trees for left and right subtrees 
	 and combine them in all possible ways with the root.
*/

/*
    Solution:
    http://www.cnblogs.com/EdwardLiu/p/3960079.html
*/
class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0)
            return new ArrayList();
        return helper(1, n);
    }
    
    public List<TreeNode> helper(int start, int end) {
        List<TreeNode> res = new ArrayList();
        if (start > end) {
            res.add(null);
            return res;
        }
        
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftList = helper(start, i - 1);
            List<TreeNode> rightList = helper(i + 1, end);
            for (TreeNode left : leftList) {
                for (TreeNode right : rightList) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }
}