/*
	Binary Tree Maximum Path Sum

	Given a binary tree, find the maximum path sum.

	The path may start and end at any node in the tree.

	For example:
	Given the below binary tree,

	   1
      / \
     2   3 == 6

     Tags: tree, dfs
*/


/*
    Solution:
    1. 和平常不同的是这里的路径不仅可以从根到某一个结点，而且路径可以从左子树某一个结点，然后到达右子树的结点，
    2. 就像题目中所说的可以起始和终结于任何结点。在这里树没有被看成有向图，而是被当成无向图来寻找路径。
    3. 因为这个路径的灵活性，我们需要对递归返回值进行一些调整，而不是通常的返回要求的结果。
    4. 在这里，函数的返回值定义为以自己为根的一条从根到子结点的最长路径（这里路径就不是当成无向图了，必须往单方向走）。
    5. 这个返回值是为了提供给它的父结点计算自身的最长路径用，而结点自身的最长路径（也就是可以从左到右那种）则只需计算然后更新即可。
    6. 这样一来，一个结点自身的最长路径就是它的左子树返回值（如果大于0的话），加上右子树的返回值（如果大于0的话），再加上自己的值。
    7. 而返回值则是自己的值加上左子树返回值，右子树返回值或者0（注意这里是“或者”，而不是“加上”，因为返回值只取一支的路径和）。
    8. 在过程中求得当前最长路径时比较一下是不是目前最长的，如果是则更新。算法的本质还是一次树的遍历，所以复杂度是O(n)。而空间上仍然是栈大小O(logn)。
*/



//Solution2
//maxValue 是要计算每一个结点的 左右路线和结点值的最大值， left right是为了求左边路线最大和右边路线最大，所以findMax就是遍历所有结点，返回最大的路线
public class Solution {
    int maxValue;
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        maxValue = Integer.MIN_VALUE;
        findmax(root);
        return maxValue;
    }
    
    public int findmax(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(0, findmax(root.left)); 
        int right = Math.max(0, findmax(root.right));
        maxValue = Math.max(maxValue, left + right + root.val);
        return Math.max(left, right) + node.val;
    }
}

public class Solution {
    //solution 1
    public int maxPathSum(TreeNode root) {
    	int[] max = new int[1];
    	max[0] = Integer.MIN_VALUE;
    	findmax(root, max);
    	//if we use array ,we can recursively update the max value
    	return max[0];
    }

    public int findmax(TreeNode root, int[] max) {
    	if (root == null)
    		return 0;
    	int left = findmax(root.left, max);
    	int right = findmax(root.right, max);
    	//the maximum value of current level
    	int current = Math.max(root.val, Math.max(root.val + left, root.val + right));

    	//the maximum value of whole level
    	max[0] = Math.max(max[0], Math.max(current, root.val + left + right));
    	return current;
    }
}   
