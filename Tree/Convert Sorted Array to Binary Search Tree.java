/*
	Convert Sorted Array to Binary Search Tree
	Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
*/

//DFS, my solution
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }
    
    public TreeNode buildTree(int[] nums, int start, int end) {
        if (start > end)
            return null;
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildTree(nums, start, mid - 1);
        root.right = buildTree(nums, mid + 1, end);
        return root;
    }
}

//Iterative
class Solution {
	private class Node {
		TreeNode node;
		int left, right;
		public Node(TreeNode node, int left, int right) {
			this.node = node;
			this.left = left;
			this.right = right;
		}
	}
	public TreeNode sortedArrayToBST(int[] nums) {
		if (nums == null || nums.length == 0) return null;
		TreeNode root = new TreeNode(0);
		Stack<Node> stack = new Stack<>();
		Node node = new Node(root, 0, nums.length - 1);
		stack.push(node);

		while (!stack.isEmpty()) {
			Node cur = stack.pop();
			int mid = cur.left + (cur.right - cur.left) / 2;
			cur.node.val = nums[mid];		

			if (cur.left < mid) {
				cur.left = new TreeNode(0);
				stack.push(new Node(cur.left, cur.left, mid - 1));
			}

			if (cur.right > mid) {
				cur.right = new TreeNode(0);
				stack.push(new Node(cur.right, mid + 1, cur.right));
			}
		}
		return root;
	}
}