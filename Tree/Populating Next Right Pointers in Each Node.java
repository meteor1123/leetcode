/*
	Populating Next Right Pointers in Each Node

	Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }

    Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

	Initially, all next pointers are set to NULL.

	Note:

		You may only use constant extra space.
		You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
	For example,
	Given the following perfect binary tree,

		 1
       /  \
      2    3
     / \  / \
    4  5  6  7
 After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL
*/


/*
    this tree is perfect tree
    Property: A binary tree with all leaf nodes at the same depth. 
              All internal nodes have degree 2.
    Solution:
                1. check the left tree, if not null assign the root.left.next pointer
                2. check the right tree, if not null, check the next pointer is null or not
                    and assign the root.right.next;


*/
  perfect binary tree ！
  perfect binary tree ！
  perfect binary tree ！
  重要的事情说三遍
public class Solution {
	//dfs
    public void connect(TreeLinkNode root) {
        if (root == null) {
        	return ;
        }
        //left isn't empty, so let left node point to the right node/
        if (root.left != null) {
        	root.left.next = root.right;
        }

        //right isn't empty,  we hava two case
        /*

        	case 1: Node has right, but the next point is nnull,like point 3
        			             1  -> NULL
						       /  \
						      2 -> 3 -> NULL
						     / \  / \
						    4->5->6->7 -> NULL
                            
			case 2: Node has left, but the next point is not null, like point 2
						         1 -> NULL
						       /  \
						      2 -> 3 -> NULL
						     / \  / \
						    4->5->6->7 -> NULL,

        */
        if (root.right != null && root.next != null) {
            root.right.next = root.next.left;
        }
        connect(root.left);
        connect(root.right);
    }

    //BFS
    /*
    	How to use BFS to traverse and make connection?
    	Tha key point is next pointer! Every time we check the node has whether or not next
    	cause next pointer point to the node in the same level,
    	if has we make cur = cur.next;
    	if hasn't we make cur = root.left;

    */
    public void connect(TreeLinkNode root) {
    	if (root == null) {
    		return;
    	}
    	TreeLinkNode cur = root;
    	while (cur != null && cur.left != null) {
            //traversal by level ,
            //在cur位置的时候 就要把cur的左右孩子的next指针设好，所以到叶子节点就跳出循环
    		cur.left.next = cur.right;
    		if (cur.next != null) {
    			cur.right.next = cur.next.left;
    			cur = cur.next;
    		} else {
    			root = root.left;
    			cur = root;
    		}
    	}

    }


    // //we can also use Populating Next Right Pointers in each node II Solution
    // public void connect(TreeLinkNode root) {
    //     //tempChild 是每层的头结点（dummy node） tempChild.next是每层最左边的结点
    //     TreeLinkNode tempChild = new TreeLinkNode(0);
    //     while (root != null) {
    //         TreeLinkNode curChild = tempChild;//用curChild 来遍历root下的left和right child 并设置next
    //         while (root != null) {
    //             if (root.left != null) {
    //                 curChild.next = root.left;
    //                 curChild = curChild.next;
    //             }
    //             if (root.right != null) {
    //                 curChild.next = root.right;
    //                 curChild = curChild.next;
    //             }
    //             //root的左右儿子都设置完后，root向右前进.
    //             root = root.next;
    //         }
    //         //遍历完一层的root，也就是设置完root这一层的结点的儿子们的next指针后， 将tempChild 赋值给root，因为tempChild是root下面那一层的最左边的孩子
    //         root = tempChild.next;
    //         tempChild.next = null;
    //     }
    // }
}