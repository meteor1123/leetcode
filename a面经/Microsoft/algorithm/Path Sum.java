/*
	Path Sum 
	Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

	For example:
	Given the below binary tree and sum = 22,

			  5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
    return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
*/

public class Solution {
   	public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        sum = sum - root.val;
        if (root.left == null && root.right == null)
            return sum == 0;
        else 
            return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }
}
 

//BFS
public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> pathSum = new LinkedList<>();
        queue.offer(root);
        pathSum.offer(root.val);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            int newSum = pathSum.poll();
            if (cur.left == null && cur.right == null) {
                if (newSum == sum) {
                    return true;
                }
            }
            if (cur.left != null) {
                queue.offer(cur.left);
                pathSum.offer(newSum + cur.left.val);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                pathSum.offer(newSum + cur.right.val);
            }
        }
        return false;
    }