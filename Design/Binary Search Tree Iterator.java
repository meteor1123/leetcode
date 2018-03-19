/*
    Binary Search Tree Iterator
    Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
    Calling next() will return the next smallest number in the BST.
    Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
*/

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


/*
    Solution:
        We notice that, the inorder sequence of a BST is ascending array,
        and we can use a queue to store the arrary, once we use next() method, 
        we can use remove the head of queue, that is the smallest number in the array.
*/

//Solution0: prefer
public class BSTIterator {
    Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        stack = new Stack();
        pushAll(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode tmpNode = stack.pop();
        pushAll(tmpNode.right);
        return tmpNode.val;
    }
    
    public void pushAll(TreeNode root) {
        while(root != null) {
            stack.push(root);
            root = root.left;
        }
    }
}


//Solution2 by myself
public class BSTIterator {
    Queue<TreeNode> queue;
    public BSTIterator(TreeNode root) {
        queue = new LinkedList();
        this.inOrder(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        if (hasNext()) {
            return queue.pop().val;
        }
        return -1;
    }
    
    public void inOrder(TreeNode root) {
        if (root == null) {
            return ;
        }
        inOrder(root.left);
        queue.offer(root);
        inOrder(root.right);
    }
}


//Solution1
public class BSTIterator {
    private Queue<Integer> queueOfInOrder = new LinkedList<Integer>();
    public BSTIterator(TreeNode root) {
        InOrder(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if (queueOfInOrder.isEmpty())
            return false;
        return true;
    }

    /** @return the next smallest number */
    public int next() {
        Integer headOfQueue = queueOfInOrder.poll();
        return headOfQueue;
    }
    
    private void InOrder(TreeNode root) {
        if (root != null) {
            InOrder(root.left);
            queueOfInOrder.add(root.val);
            InOrder(root.right);
        }
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */