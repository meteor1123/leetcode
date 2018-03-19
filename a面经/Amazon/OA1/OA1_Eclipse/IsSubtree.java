package OA1;

public class IsSubtree {
	public boolean isSubtree(TreeNode T1, TreeNode T2) {
        // write your code here
        if (T2 == null) {
            return true;
        }
        if (T1 == null) {
            return false;
        }
        return isSameTree(T1, T2) || isSubtree(T1.left, T2) || isSubtree(T1.right, T2);
        
    }
    public boolean isSameTree(TreeNode T1, TreeNode T2) {
        if (T1 == null && T2 == null) {
            return true;
        }
        if (T1 == null || T2 == null) {
            return false;
        }
        if (T1.val != T2.val) {
            return false;
        }
        return isSameTree(T1.left, T2.left) && isSameTree(T1.right, T2.right);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
class TreeNode {
     public int val;
     public TreeNode left, right;
     public TreeNode(int val) {
         this.val = val;
         this.left = this.right = null;
     }
}
