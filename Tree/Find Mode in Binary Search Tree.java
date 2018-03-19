/*
	501. Find Mode in Binary Search Tree

	Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.

	Assume a BST is defined as follows:

	The left subtree of a node contains only nodes with keys less than or equal to the node's key.
	The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
	Both the left and right subtrees must also be binary search trees.
	For example:
	Given BST [1,null,2,2],
	   1
	    \
	     2
	    /
	   2
	return [2].

	Note: If a tree has more than one mode, you can return them in any order.

	Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
*/


// time: O(n), space: O(n) trival solution
class Solution {
    int max = Integer.MIN_VALUE;
    public int[] findMode(TreeNode root) {
        if (root == null)
            return new int[]{};
        HashMap<Integer, Integer> map = new HashMap();
        preorder(root, map);
        
        List<Integer> list = new ArrayList();
        
        for (int key : map.keySet()) {
            if (map.get(key) == max) {
                list.add(key);
            }
        }
        
        int[] res = new int[list.size()];
 
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        
        return res;
    }
    
    public void preorder(TreeNode root, HashMap<Integer, Integer> map) {
        if (root == null)
            return;
        preorder(root.left, map);
        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
        max = Math.max(map.get(root.val), max);
        preorder(root.right, map);
    }
}

// time: O(n) space: O(1), 第一遍记录mode的数量，第二遍赋值mode
class Solution {
    private int curVal;
    private int curCount = 0;
    private int maxCount = 0;
    private int modeCount = 0;
    private int[] modes;
    
    public int[] findMode(TreeNode root) {
        inorder(root);
        modes = new int[modeCount];
        curCount = 0;
        modeCount = 0;
        inorder(root);
        return modes;
    }
    
    private void handleValue(int val) {
        if (val != curVal) {
            curVal = val;
            curCount = 0;
        }
        curCount++;
        if (curCount > maxCount) {
            maxCount = curCount;
            modeCount = 1;
        } else if (curCount == maxCount) {
            if (modes != null)
                modes[modeCount] = curVal;
            modeCount++;
        }
    }
    
    private void inorder(TreeNode root) {
        if (root == null)
            return;
        inorder(root.left);
        handleValue(root.val);
        inorder(root.right);
    }
}