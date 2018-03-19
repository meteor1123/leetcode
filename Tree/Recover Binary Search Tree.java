/*
	Recover Binary Search Tree 
	Two elements of a binary search tree (BST) are swapped by mistake.

	Recover the tree without changing its structure.

	Note:
	A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
	confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
*/



/*
	https://leetcode.com/discuss/13034/no-fancy-algorithm-just-simple-and-powerful-order-traversal
*/


/*
	Let's start by writing the in order traversal:

    private void traverse (TreeNode root) {
       if (root == null)
          return;
       traverse(root.left);
       // Do some business
       traverse(root.right);
    }
    So when we need to print the node values in order, we insert System.out.println(root.val) in the place of "Do some business".

    What is the business we are doing here? We need to find the first and second elements that are not in order right?

    How do we find these two elements? For example, we have the following tree that is printed as in order traversal:

    6, 3, 4, 5, 2

    We compare each node with its next one and we can find out that 6 is the first element to swap because 6 > 3 and 2 is the second element to swap because 2 < 5.

    Really, what we are comparing is the current node and its previous node in the "in order traversal".

    Let us define three variables, firstElement, secondElement, and prevElement. Now we just need to build the "do some business" logic as finding the two elements. 
    See the code below:
*/
public class Solution {

    /*
        since a inorder array of BST is a ascending sequence
        if two node are in wrong way, must be like  1 2 3 4 5 6 --> 1 5 3 4 2 6
        pre > root and 
    */
    private TreeNode firstElement = null;
    private TreeNode secondElement = null;
    private TreeNode preElement = new TreeNode(Integer.MIN_VALUE);
    public void recoverTree(TreeNode root) {
        if (root == null) {
            return ;
        }
        traverse(root);
        int temp = firstElement.val;
        firstElement.val = secondElement.val;
        secondElement.val = temp;
    }
    public void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        //inorder traverse
        traverse(root.left);
        // when firstElement is null , which means if we find the pre element large than root.val ,it must be the first lost elment.
        if (firstElement == null && preElement.val > root.val) {
            firstElement = preElement;
        }
        // If first element is found, assign the second element to the root (refer to 2 in the example above)
        if (firstElement != null && preElement.val > root.val) {
            secondElement = root;
        }
        preElement = root;
        traverse(root.right);
    }
}