/*
	Binary Tree Inorder Traversal 
	Given a binary tree, return the inorder traversal of its nodes' values.

	For example:
	Given binary tree {1,#,2,3},
		 1
	      \
	       2
	      /
	     3 
   return [1,3,2].
*/


//左 中 右， left -> val -> right
public class Solution {
	//Recursive version 1 recommend!
	public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (root == null)
            return res;
        dfs(root, res);
        return res;
    }
    public void dfs(TreeNode root, ArrayList<Integer> res) {
        if (root == null)
            return ;
        dfs(root.left, res);
        res.add(root.val);
        dfs(root.right, res);
    }

    //Iterative version 1 recommend!
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }


    //Iterative version 2
    // public List<Integer> inorderTraversal(TreeNode root) {
    // 	ArrayList<Integer> res = new ArrayList<Integer>();
    // 	if (root == null)
    // 		return res;
    // 	LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
    // 	TreeNode cur = root;

    // 	while (cur != null || !stack.isEmpty()) {
    // 		while (cur != null) {
    // 			stack.push(cur);
    // 			cur = cur.next;
    // 		}
    // 		cur = stack.pop();
    // 		res.add(cur.val);
    // 		cur = cur.right;
    // 	}
    // }

    //Morris traversal

    /*
        为什么要用Morris
        Morris Traversal方法可以做到这两点，与前两种方法的不同在于该方法只需要O(1)空间，而且同样可以在O(n)时间内完成。
        要使用O(1)空间进行遍历，最大的难点在于，遍历到子节点的时候怎样重新返回到父节点（假设节点中没有指向父节点的p指针），
        由于不能用栈作为辅助空间。为了解决这个问题，Morris方法用到了线索二叉树（threaded binary tree）的概念。
        在Morris方法中不需要为每个节点额外分配指针指向其前驱（predecessor）和后继节点（successor），
        只需要利用叶子节点中的左右空指针指向某种顺序遍历下的前驱节点或后继节点就可以了。
    */
    /* Solution:
        例子图片在这里：http://www.cnblogs.com/AnnieKim/archive/2013/06/15/MorrisTraversal.html
        1. 如果当前节点的左孩子为空，则输出当前节点并将其右孩子作为当前节点。
        2. 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。
            a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。
            b) 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空（恢复树的形状）。输出当前节点。当前节点更新为当前节点的右孩子。
        3. 重复以上1、2直到当前节点为空。
    */
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root == null) return new ArrayList<Integer>();
        List<Integer> res = new ArrayList<Integer>();
        TreeNode pre = null;
        while(root != null){
            if(root.left == null){
                res.add(root.val);
                root = root.right;
            } else{
                pre = root.left;
                while(pre.right != null && pre.right != root){
                    pre = pre.right;
                }
                if(pre.right == null){
                    pre.right = root;
                    root = root.left;
                }else{
                    pre.right = null;
                    res.add(root.val);
                    root = root.right;//当pre遍历输出过后，才回溯将root遍历输出
                }
            }
        }
        return res;
    }
}