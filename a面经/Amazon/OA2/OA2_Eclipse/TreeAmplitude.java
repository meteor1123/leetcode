package OA2;
public class TreeAmplitude {
	public int Solution(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return dfs(root, root.val, root.val);
	}
	public int dfs(TreeNode root, int min, int max) {
		if (root == null) {
			return max - min;
		}
		if (root.val < min) {
			min = root.val;
		}
		if (root.val > max) {
			max = root.val;
		}
		return Math.max(dfs(root.left, min, max), dfs(root.right, min, max));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
//	private static int MAX = 0;
//	public static int getAmp(TreeNode root) {
//		if (root == null) {
//			return 0;
//		}
//		dfs2(root, root.val, root.val);
//		return MAX;
//	}
//	public static void dfs2(TreeNode root, int min, int max) {
//		if (root == null) {
//			return;
//		}
//		min = Math.min(min, root.val);
//		max = Math.max(max, root.val);
//		
//		if (root.left == null && root.right == null) {
//			MAX = Math.max(MAX, max - min);
//		}
//		dfs2(root.left, min, max);
//		dfs2(root.right, min, max);
//	}

}
