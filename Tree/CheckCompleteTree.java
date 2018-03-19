public class Solution {
	public boolean checkCompleteTree(TreeNode root) {
		if (root == null) {
			return false;
		}
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		int level = 0;
		int curNum = 1;
		int nextNum = 0;
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode cur = queue.poll();
			curNum--;
			if (root.left != null) {
				queue.offer(root.left);
				nextNum++;
			}
			if (root.right != null) {
				queue.offer(root.right);
				nextNum++;
			}
			if (curNum == 0) {
				level++;
				if (nextNum != Math.pow(2, level)) {
					return false;
				}
				curNum = nextNum;
				nextNum = 0;
			}
		}
		return true;
	}
}