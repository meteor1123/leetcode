package OA2;

public class MinPathSum {
	public int Solution(TreeNode root) {
		if (root == null) {
			return 0;
		}
		if (root.right != null && root.left == null) {
			return Solution(root.right) + root.val;
		}
		if (root.left != null && root.right == null) {
			return Solution(root.left) + root.val;
		}
	
		return Math.min(Solution(root.left), Solution(root.right)) + root.val;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
