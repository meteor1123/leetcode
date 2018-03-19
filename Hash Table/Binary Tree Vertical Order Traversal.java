/*
	Binary Tree Vertical Order Traversal
	Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

	If two nodes are in the same row and column, the order should be from left to right.

	Examples:
	Given binary tree [3,9,20,null,null,15,7],
	    3
	   / \
	  9  20
	    /  \
	   15   7
	return its vertical order traversal as:
	[
	  [9],
	  [3,15],
	  [20],
	  [7]
	]
	Given binary tree [3,9,20,4,5,2,7],
	    _3_
	   /   \
	  9    20
	 / \   / \
	4   5 2   7
	return its vertical order traversal as:
	[
	  [4],
	  [9],
	  [3,5,2],
	  [20],
	  [7]
	]
*/


/*
	Solution: 1. 将整棵树进行bfs遍历，每个结点设置一个权值，左边的孩子为父节点权值-1，右边的孩子为父节点权值+1，
				 比如3， 9， 20， 4， 5， 2， 7的BFS序列，那就是 3（0）， 9（-1）， 20（1）， 4（-2）， 5（0）， 2（0）， 7（2）
				 输出为	
				 	  （-2） [4],
					  （-1） [9],
					  （0）  [3,5,2],
					  （1）  [20],
					  （2）  [7]
*/
public class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
        Queue<TreeNode> queue = new LinkedList<>();
        HashMap<TreeNode, Integer> weight = new HashMap<TreeNode, Integer>();
        queue.offer(root);
        weight.put(root, 0);
        int min = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int w = weight.get(node);
            if (!map.containsKey(w)) {
                map.put(w, new ArrayList<>());
            }
            map.get(w).add(node.val);
            if (node.left != null) {
                queue.add(node.left);
                weight.put(node.left, w - 1);
            } 
            if (node.right != null) {
                queue.add(node.right);
                weight.put(node.right, w + 1);
            }
            min = Math.min(min, w);
        }
        while (map.containsKey(min)) {
            res.add(map.get(min++));
        }
        return res;
    }
}