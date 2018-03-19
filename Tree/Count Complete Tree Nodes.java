/*
	Count Complete Tree Nodes 
	Given a complete binary tree, count the number of nodes.

	Definition of a complete binary tree from Wikipedia:
	In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. 
    It can have between 1 and 2h nodes inclusive at the last level h.
	Tags: Tree, BinarySearch
*/

/*
	Solution1:
	https://leetcode.com/discuss/38930/concise-java-solutions-o-log-n-2
	1. The height of a tree can be found by just going left. Let a single node tree have height 0. 
	   Find the height h of the whole tree.If the whole tree is empty, i.e., has height -1, there are 0 nodes.

	2. Otherwise check whether the height of the right subtree is just one less than that of the whole tree, 
	   meaning left and right subtree have the same height.
	   1) If yes, then the last node on the last tree row is in the right subtree and the left subtree is a full tree of height h-1. 
	      So we take the 2^h-1 nodes of the left subtree plus the 1 root node plus recursively the number of nodes in the right subtree.
	   2) If no, then the last node on the last tree row is in the left subtree and the right subtree is a full tree of height h-2. 
	      So we take the 2^(h-1)-1 nodes of the right subtree plus the 1 root node plus recursively the number of nodes in the left subtree.

	Solution2:
	The key point:

	1. If left subtree is a full tree, then right subtree must be a complete tree, and Lheight == Rheight
	2. If left subtree is a complete tree, then right subtree must be a full tree, and Lheight - Rhight == 1
    

*/

/*
    http://stackoverflow.com/questions/2603692/what-is-the-difference-between-tree-depth-and-height
    the difference between height and depth

    1) The depth of a node is the number of edges from the node to the tree's root node.
       A root node will have a depth of 0;
    2) The height of a node is the number of edges on the longest path from the node to leaf,
       A leaf node will have a height of 0;
*/
public class Solution {
    //Easy to understand solution prefer
    public int countNodes(TreeNode root) {
        if (root == null)
            return 0;
        int rootHeight = height(root);
        if (height(root.right) == rootHeight - 1) {
            return (1 << (rootHeight - 1)) - 1 + countNodes(root.right) + 1;// 2^(h - 1)(左子树) - 1 + count(root.right) + 1(root);
            /*
                     1
                   /   \
                  2     3
                 / \   / 
                4   5 6
            */
        } else {
            /*
                     1
                   /  \
                  2    3
                 / 
                4   
            */
            return (1 << (rootHeight - 2)) - 1 + countNodes(root.left) + 1;// 2^(h - 2)（右子树） - 1 + cout(root.left) + 1(root);
        }
    }
    
    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + height(root.left);
    }
}

// prefer
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null)
            return 0;
        int height = height(root);
        int rightHeight = height(root.right);
        if (rightHeight == height - 1) {
            return (1 << (height - 1)) - 1 + countNodes(root.right) + 1;
        }
        return (1 << (height - 2)) - 1 + countNodes(root.left) + 1;
    }
    
    public int height(TreeNode root) {
        if (root == null)
            return 0;
        return height(root.left) + 1;
    }
}