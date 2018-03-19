/*
	Minimum Depth of Binary Tree
	Given a binary tree, find its minimum depth.
	The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
*/

/*
	Solution:
	这道题因为是判断最小深度，所以必须增加一个叶子的判断（因为如果一个节点如果只有左子树或者右子树，我们不能取它左右子树中小的作为深度，
	因为那样会是0，我们只有在叶子节点才能判断深度，而在求最大深度的时候，因为一定会取大的那个，所以不会有这个问题
*/
public class Solution {

    //solution2: 一个节点如果只有左或者右子树的话 不能取小的那个作为深度，这样depth会为0；
    public int minDepth(TreeNode root) {
    	if (root == null)
    		return 0; 
    	int minLeft = minDepth(root.left);
    	int minRight = minDepth(root.right);
    	//如果有个节点只有一边孩子时，不能返回0，要返回另外一半边的depth。 
    	if (minLeft == 0 || minRight == 0)
    		return minLeft > minRight ? minLeft + 1 : minRight + 1;
    	return Math.min(minLeft, minRight) + 1;
    }

    //solution3:
    /*
    	这道题因为是判断最小深度，所以必须增加一个叶子的判断（因为如果一个节点如果只有左子树或者右子树，
    	我们不能取它左右子树中小的作为深度，因为那样会是0，我们只有在叶子节点才能判断深度，而在求最大深度的时候，
    	因为一定会取大的那个，所以不会有这个问题
    */

    //DFS3
    public int minDepth(TreeNode root) {
    	if (root == null)
    		return 0;
    	if (root.left == null)
    		return minDepth(root.right) + 1;
    	if (root.right == null)
    		return minDepth(root.left) + 1;
    	return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }


    //BFS
    public int minDepth(TreeNode root) {
    	if (root == null)
    		return 0;
    	LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
    	queue.add(root);
    	int curNum = 1;
    	int nextNum = 0;
    	int depth = 1;
    	while (!queue.isEmpty()) {
    		TreeNode cur = queue.poll();
    		curNum--;
    		if (cur.left == null && cur.right == null)
    			return depth;
    		if (cur.left != null) {
    			queue.add(cur.left);
    			nextNum++;
    		}
    		if (cur.right != null) {
    			queue.add(cur.right);
    			nextNum++;
    		}
    		if (curNum == 0) {
    			curNum = nextNum;
    			nextNum = 0;
    			depth++;
    		}
    	}
    	return 0;
    }
}