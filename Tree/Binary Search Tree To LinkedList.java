package round2;

import java.util.LinkedList;

public class BST2LinkedList {
	private TreeNode last = null;
	public TreeNode bst2list(TreeNode root) {
		if (root == null) {
			return root;
		}
		convertToDoubleLL(root);
		return root;
	}

	//Convert to DoubleLinkedList
	public void convertToDoubleLL(TreeNode root) {
		if (root == null) {
			return;
		}
		//recursive find the leftmost node
		convertToDoubleLL(root.left);
		//the left is like previous listnode
		root.left = last;//so we let the root left point to the previous node
						 //notice that, we use a global variable last to record the previous node
						 //in inorder sequence.
		//if the last node isn't null,
		//we set the last node's right pointer ro root,just like next pointer
		if (last != null) {
			last.right = root;
		}
		//and we update the last node to the current root
		last = root;
		//recursive traverse the right children.
		convertToDoubleLL(root.right);
	}
	
	//Covert to LinkedList
	public void convertToLinkedList(TreeNode root) {
		if (root == null) {
			return;
		}
		convertToLinkedList(root.left);
		root.left = null;
		if (last != null) {
			last.right = root;
		}
		last = root;
		convertToLinkedList(root.right);
	}	
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) {
		val = x;
	}
}
