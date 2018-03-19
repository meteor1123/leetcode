/*	
	Find Bottom Left Tree Value
	
	Given a binary tree, find the leftmost value in the last row of the tree.

	Example 1:
	Input:

	    2
	   / \
	  1   3

	Output:
	1
	Example 2: 
	Input:

	        1
	       / \
	      2   3
	     /   / \
	    4   5   6
	       /
	      7

	Output:
	7
	Note: You may assume the tree (i.e., the given root node) is not NULL.
*/


// BFS prefer, concise, enqueu from right, then the last element will be the most left in BFS
class Solution {
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.right != null)
                queue.offer(root.right);
            if (root.left != null)
                queue.offer(root.left);
        }
        return root.val;
    }
}

//DFS Prefer: 
// 1. pre order 
// 2.设一个全局变量h， 如果dfs的时候找到一个节点depth比现在遍历过的树的高度还高，那当前节点就是最大depth的第一个。

class Solution {
    int res = 0, h = 0;
    public int findBottomLeftValue(TreeNode root) {
        helper(root, 1);
        return res;
    }
    
    public void helper(TreeNode root, int depth) {
        if (h < depth) {
            res = root.val;
            h = depth;
        }
        if (root.left != null) {
            helper(root.left, depth + 1);
        }
        if (root.right != null) {
            helper(root.right, depth + 1);
        }
    }
}


// myself, return the mostLeft only when curNum == 0 && nextNum != 0, then the element in front of the queue will be the most left one
class Solution {
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        int curNum = 1;
        int nextNum = 0;
        int mostLeft = root.val;
        
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
       
            if (curNum == 0 && nextNum != 0) {
                mostLeft = queue.peek().val;
                curNum = nextNum;
                nextNum = 0;
            }
        }
        return mostLeft;
    }
}