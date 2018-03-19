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
	//双向链表 in place, time : o(n)
	public void convertToDoubleLL(TreeNode root) {
		if (root == null) {
			return;
		}
		//recursive find the leftmost node
		convertToDoubleLL(root.left);
		//the left is like previous point
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
	//单向链表 in place, time :O(n)
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



//Solution by 北航小涵
/*
	1. 拿出根节点。  
	2. 递归处理left， 找到左边最后一个节点，并且连接root到这个节点上。
	3. 递归处理right， 找到右边最前面的节点，并且连接root到这个节点上。
	4. return。

	//get the root node
	//recursive find the left children, and find the leftmost node, and connect to the it's parent root
	//recursive find the right children, and find the right most leading node, and connect to the parent
	最后还需要处理一下头连接到尾巴的双向循环，补上就行
*/
public class SolutionConvert {
    public void solve(TreeNode root) {
        if (root == null) {
            return;
        }
        convertToDoubleLinkedList(root);           
    }
    
    public void convertToDoubleLinkedList(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            TreeNode left = root.left;
            convertToDoubleLinkedList(left); 
            while (left.right != null) {
                left = left.right;
            }
            left.right = root;
            root.left = left;
        }
        if (root.right != null) {
            TreeNode right = root.right;
            convertToDoubleLinkedList(right);
            while (right.left != null) {
                right = right.left;
            }
            right.left = root;
            root.right = right;
        }
    }
    
    public void test(String[] args) {
        TreeNode a = new TreeNode("10");
        TreeNode b = new TreeNode("12");
        TreeNode c = new TreeNode("15");
        TreeNode d = new TreeNode("25");
        TreeNode e = new TreeNode("30");
        TreeNode f = new TreeNode("36");
        a.left = b;.
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        solve(a);
        while (a.left != null) {
            a = a.left;
        }
        while (a != null) {
            System.out.print(a.val + " ");
            a = a.right;
        }      
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
