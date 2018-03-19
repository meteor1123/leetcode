/*
	Binary Tree Zigzag Level Order Traversal 
	Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

	For example:
	Given binary tree {3,9,20,#,#,15,7},

		3
	   / \
	  9  20
	    /  \
	   15   7
	return its zigzag level order traversal as:
	[
	  [3],
	  [20,9],
	  [15,7]
	]
*/

public class Solution {
	//DFS
	public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if (root == null)
			return res;
		dfs(root, res, 0);
		return res;
	}

	public void dfs(TreeNode root, ArrayList<ArrayList<Integer>> res, int depth) {
		if (root == null)
			return ;
		if (depth >= res.size())
			res.add(new ArrayList<Integer>());

		//根据层数判断是否需要reverse
		if (depth % 2 == 0) {
			res.get(depth).add(root.val);
		} else if (depth % 2 == 1){
			res.get(depth).add(0, root.val);
		}
		dfs(root.left, res, depth + 1);
		dfs(root.right, res, depth + 1);
	}

	//BFS 
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null)
			return res;
		boolean reverse = false;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		int curNum = 1;
		int nextNum = 0;
		List<Integer> item = new ArrayList<>();
		while(!queue.isEmpty()) {
			TreeNode cur = queue.poll();
			curNum--;
			item.add(cur.val);
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
				if (reverse) {			
					Collections.reverse(item);//关键 reverse为true时，用Collections的reverse转置
					reverse = false;
				} else {
					reverse = true;
				}
				res.add(item);
				item = new ArrayList<>();
			}
		}
		return res;
	}
}

