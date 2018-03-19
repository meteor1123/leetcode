/*
	Binary Tree Preorder Traversal 
*/

public class Solution {
    //recursive
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorder(res, root);
        return res;
    }
    public void preorder(List<Integer> res, TreeNode root) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        preorder(res, root.left);
        preorder(res, root.right);
    }

    //Iterative stack1
    public List<Integer> preorderTraversal(TreeNode root) {
    	ArrayList<Integer> res = new ArrayList<Integer>();
    	if (root == null)
    		return res;
    	LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
    	stack.push(root);
    	while (!stack.isEmpty()) {
    		TreeNode cur = stack.pop();
    		res.add(cur.val);
    		if (cur.right != null)
    			stack.push(cur.right);
    		if (cur.left != null)
    			stack.push(cur.left);
    	}
        return res;
    }

    //Iterative stack2
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (root == null)
            return res;
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);//只要有左边的叶子节点就一直遍历入栈
                res.add(root.val);// 跟inorder遍历的唯一区别在于， inorder中res.add(root.val)放到  遍历完最left的时候才add， 正好如何 
                root = root.left;
            } else {
                root = stack.pop();//左边的遍历完了再出栈给root，将右节点的值 //加入结果
                root = root.right;
            }
        }
        return res;
    }

    //Morris  O(1) space
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> res = new ArrayList<>();
        TreeNode pre = null;
        while (root != null) {
            if (root.left == null) {
                res.add(root.val);
                root = root.right;
            } else {//假如当前节点的left 不为空，则将left设为前驱结点pre，因为这里是根据inorder遍历
                pre = root.left;//前驱结点设为left child
                while (pre.right != null && pre.right != root) {
                    pre = pre.right;//不停的往下遍历 ，跟inorder一样
                }
                //假如pre.right 为空，叶子节点，记录root的信息，方便回溯
                if (pre.right == null) {
                    pre.right = root;
                    res.add(root.val);//关键步骤，在这里将当前的root结点输出到结果，先将root输出，再遍历下面的叶子节点输出，这是前序遍历
                    root = root.left;
                }  else {
                    pre.right = null;
                    root = root.right;
                }
            } 
        }
        return res;
    }
}