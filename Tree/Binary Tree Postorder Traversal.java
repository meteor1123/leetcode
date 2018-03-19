/*
	Binary Tree Postorder Traversal 
	Given a binary tree, return the postorder traversal of its nodes' values.
	For example:
	Given binary tree {1,#,2,3},

		1
	    \
	     2
	    /
	   3
	return [3,2,1].
*/

public class Solution {
	//Recursive 1
    public List<Integer> postorderTraversal(TreeNode root) {
    	ArrayList<Integer> res = new ArrayList<Integer>();
    	if (root != null) {
    		res.addAll(root.left);
    		res.addAll(root.right);
    		res.add(root.val);
    	}
    	return res;
    }

    //Recursive 2
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        dfs(root, res);
        return res;
    }
    public void dfs(TreeNode root, ArrayList<Integer> res) {
        if (root == null)
            return ;
        dfs(root.left, res);
        dfs(root.right, res);
        res.add(root.val);
    }

    //Iterative1
    //等于preorder 将root = root.left 变成 root = root.right ,最后再将结果 reverse
    //将 preOrder 逆序
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                res.add(root.val);
                root = root.right;
            } else {
                root = stack.pop();
                root = root.left;
            }
        }
        Collections.reverse(res);
        return res;
    }

    //Iterative2
    /*
		后序遍历的情况就复杂多了。我们需要维护当前遍历的cur指针和前一个遍历的pre指针来追溯当前的情况
		（注意这里是遍历的指针，并不是真正按后序访问顺序的结点）。具体分为几种情况：
		1）如果pre的左孩子或者右孩子是cur，那么说明遍历在往下走，按访问顺序继续，即如果有左孩子，则是左孩子进栈，
		   否则如果有右孩子，则是右孩子进栈，如果左右孩子都没有，则说明该结点是叶子，可以直接访问并把结点出栈了。
		2）如果反过来，cur的左孩子是pre，则说明已经在回溯往上走了，但是我们知道后序遍历要左右孩子走完才可以访问自己，
		   所以这里如果有右孩子还需要把右孩子进栈，否则说明已经到自己了，可以访问并且出栈了。
		3）如果cur的右孩子是pre，那么说明左右孩子都访问结束了，可以轮到自己了，访问并且出栈即可。
		   算法时间复杂度也是O(n)，空间复杂度是栈的大小O(logn)。

		下面在弹栈的时候需要分情况一下：
		1）如果当前栈顶元素的右结点存在并且还没访问过（也就是右结点不等于上一个访问结点），那么就把当前结点移到右结点继续循环；
		2）如果栈顶元素右结点是空或者已经访问过，那么说明栈顶元素的左右子树都访问完毕，应该访问自己继续回溯了。
    */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            //往左探底
            if (root != null) {
                stack.push(root);
                root = root.left;
            } 
            else {
                TreeNode peekNode = stack.peek();
                //一直往右探底
                if (peekNode.right != null && peekNode.right != pre) 
                    root = peekNode.right;
                else {
                    //已经探底，加入这个值
                    stack.pop();
                    res.add(peekNode.val);
                    pre = peekNode;
                }
                
            }
        }
        return res;
    }
}