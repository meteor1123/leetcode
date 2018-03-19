/*
	Kth Smallest Element in a BST 
		Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

	Note: 
		You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

	Follow up:
		What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

	Hint:
		Try to utilize the property of a BST.
		What if you could modify the BST node's structure?
		The optimal runtime complexity is O(height of BST).
*/

public class Solution {
    //1. Binary Search
    public int kthSmallest(TreeNode root, int k) {
        int leftNum = countNodes(root.left);
        if (leftNum + 1 == k) {
            return root.val;
        } else if (leftNum + 1 > k) {
            return kthSmallest(root.left, k);
        } else {
            return kthSmallest(root.right, k - leftNum - 1);
        }
    }
    
    private int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    //2.In order recursive
        public int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        ArrayList<TreeNode> item = new ArrayList<>();
        dfs(root, k, item);
        return item.get(k - 1).val;
    }
    
    public void dfs(TreeNode root, int k, ArrayList<TreeNode> item) {
        if (root == null) {
            return;
        }
        if (item.size() == k) {
            return;
        }
        dfs(root.left, k, item);
        item.add(root);
        dfs(root.right, k, item);
    }

    //3. Iterative use stack to mock up the recursive
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        int count = 0;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                count++;
                if (count == k) {
                    return cur.val;
                }
                cur = cur.right;
            }
        }
        return 0;
    }
}

