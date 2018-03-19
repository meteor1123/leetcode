/*
	Binary Tree Right Side View 
	Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

	For example:
	Given the following binary tree,

	   1            <---
	 /   \
	2     3         <---
	 \     \
	  5     4       <---
	You should return [1, 3, 4].

*/


//BFS 
public ArrayList<Integer> rightSideView(TreeNode root) {
    ArrayList<Integer> res = new ArrayList<Integer>();
    if (root == null) {
        return res;
    }
    LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
    int curNum = 1;
    int nextNum = 0;
    queue.offer(root);
    while (!queue.isEmpty()) {
        TreeNode curNode = queue.poll();
        curNum--;
        if (curNum == 0) {
            res.add(curNode.val);
        }
        if (curNode.left != null) {
            queue.offer(curNode.left);
            nextNum++;
        }
        if (curNode.right != null) {
            queue.offer(curNode.right);
            nextNum++;
        }
        
        if (curNum == 0) {
            curNum = nextNum;
            nextNum = 0;
        }
    }
    return res;
}

//DFS 
public ArrayList<Integer> rightSideView(TreeNode root) {
    ArrayList<Integer> res = new ArrayList<Integer>();
    if (root == null) {
        return res;
    }
    res.add(root.val);
    int level = 1;
    helper(res, root, level);
    return res;
}

public void helper(ArrayList<Integer> res, TreeNode root, int curLevel) {
    if (root == null) {
        return;
    }

    if (curLevel > res.size()) {
        res.add(root.val);
    }
    
    helper(res, root.right, curLevel + 1);
    helper(res, root.left, curLevel + 1 );
}

