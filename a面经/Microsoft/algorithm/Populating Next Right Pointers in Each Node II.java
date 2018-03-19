/*
	Populating Next Right Pointers in Each Node II 
	Follow up for problem "Populating Next Right Pointers in Each Node".

	What if the given tree could be any binary tree? Would your previous solution still work?

	Note:

	You may only use constant extra space.
	For example,
	Given the following binary tree,
				1
		       /  \
		      2    3
		     / \    \
		    4   5    7
    After calling your function, the tree should look like:
    	1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
*/
public class Solution {

	//based on level order traversal
	public void connect(TreeLinkNode root) {
		TreeLinkNode head = root; //The left most node in the lower level
		TreeLinkNode pre = null; //The previous node in the lower level
		TreeLinkNode cur = null; //The current node in the upper level
		while (head != null) {
			//move to next level
			cur = head;
			head = null;
			pre = null;
			while (cur != null) { //iterate on the current level
				//left child
				if (cur.left != null) {
					if (pre != null) {
						pre.next = cur.left;
					} else {
						head = cur.left;
					}
					pre = cur.left;
				}
				//right child
				if (cur.right != null) {
					if (pre != null) {
						pre.next = cur.right;
					} else {
						head = cur.right;
					}
					pre = cur.right;
				}
				//move to next node
				cur = cur.next;
			}
		}
	}



	public void connect(TreeLinkNode root) {
	    //tempChild 是每层的头结点（dummy node） tempChild.next是每层最左边的结点
        TreeLinkNode tempChild = new TreeLinkNode(0);
        while (root != null) {
            TreeLinkNode curChild = tempChild;//用curChild 来遍历root下的left和right child 并设置next
            while (root != null) {
                if (root.left != null) {
                    curChild.next = root.left;
                    curChild = curChild.next;
                }
                if (root.right != null) {
                    curChild.next = root.right;
                    curChild = curChild.next;
                }
                //root的左右儿子都设置完后，root向右前进.
                root = root.next;
            }
            //遍历完一层的root，也就是设置完root这一层的结点的儿子们的next指针后， 将tempChild 赋值给root，因为tempChild是root下面那一层的最左边的孩子
            root = tempChild.next;
            tempChild.next = null;
        }
    }
}


public class Solution {
	public void connect(TreeLinkNode root) {
		TreeLinkNode head = null; //The left most node in the lower level
		TreeLinkNode pre = null; //The previous node in the lower level
		TreeLinkNode cur = root; //The current node in the upper level
		while (cur != null) {
			while (cur != null) { //iterate on the current level
				//left child
				if (cur.left != null) {
					if (pre != null) {
						pre.next = cur.left;
					} else {
						head = cur.left;
					}
					pre = cur.left;
				}
				//right child
				if (cur.right != null) {
					if (pre != null) {
						pre.next = cur.right;
					} else {
						head = cur.right;
					}
					pre = cur.right;
				}
				//move to next node
				cur = cur.next;
			}
            //move to next level
			cur = head;
			head = null;
			pre = null;
		}
	}
}