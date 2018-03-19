/*
	Maximum Depth of Binary Tree 
	Given a binary tree, find its maximum depth.

	The maximum depth is the number of nodes along the longest path 
	from the root node down to the farthest leaf node.
*/

public class Solution {
    //DFS
    public int maxDepth(TreeNode root){
    	if (root == null)
    		return 0;
    	
    	int leftMaxDepth = maxDepth(root.left);
    	int rightMaxDepth = maxDepth(root.right);

    	return Math.max(leftMaxDepth, rightMaxDepth) + 1;
    }
    //BFS 
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        int level = 0;
        int nextNum = 0;
        int curNum = 1;
        queue.offer(root); //queue.offer = stack.push, queue.poll = stack.pop
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            curNum--;
            if (cur.left != null) {
                queue.offer(cur.left);
                nextNum++;
            }
            
            if (cur.right != null) {
                queue.offer(cur.right);
                nextNum++;
            }
            
            if (curNum == 0) {
                curNum = nextNum;
                nextNum = 0;
                level++;
            }
        }
        return level;
    }
}