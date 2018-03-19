/*
	Remove Node In Binary Search Tree

	Given a root of Binary Search Tree with unique value for each node. Remove the node with given value. If there is no such a node with given value in the binary search tree, do nothing. You should keep the tree still a binary search tree after removal.

	Example
	Given binary search tree:

	    5
	   / \
	  3   6
	 / \
	2   4
	Remove 3, you can either return:

	    5
	   / \
	  2   6
	   \
	    4
	or

	    5
	   / \
	  4   6
	 /
	2
 */


/*
	case1: 要删除的结点只有left child -> 将left child 接到父节点
	case2: 要删除的结点只有right child -> 将right child 接到父节点
	case3: 要删除的结点有两个孩子
			(1) find the minimum node in the right children of root
			(2) change the root to the minimum node in right children
			(3) 将这个最小的右孩子结点连接原先的左右孩子结点。
 */
public class Solution {
    /**
     * @param root: The root of the binary search tree.
     * @param value: Remove the node with given value.
     * @return: The root of the binary search tree after removal.
     */
    public TreeNode removeNode(TreeNode root, int value) {
        // write your code here
        if (root == null) {
            return root;
        }
        if (value < root.val) {
            root.left = removeNode(root.left, value);
        } else if (value > root.val) {
            root.right = removeNode(root.right, value);
        } else {
        	//case1:left child == null
            if (root.left == null) {
                return root.right;
            //case2: right child == null
            } else if (root.right == null) {
                return root.left;
                
            //case3: the delete node has two children
            } else {
                TreeNode cur = root;
                root = findMin(root.right);
                root.right = deleteMin(cur.right);
                root.left = cur.left;
            }
        }
        return root;
    }
    
    //找root结点下的最小点
    public TreeNode findMin(TreeNode root) {
        if (root.left == null) {
            return root;
        }
        return findMin(root.left);
    }
    //删除root结点下的最小结点，只需要简单的将root.left = root.right
    public TreeNode deleteMin(TreeNode root) {
        if (root.left == null) {
            return root.right;
        }
        root.left = deleteMin(root.left);
        return root;
    }

}

