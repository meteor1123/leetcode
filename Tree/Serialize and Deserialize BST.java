/*
	449. Serialize and Deserialize BST

	Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

	Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.

	The encoded string should be as compact as possible.

	Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
*/

/*
	example:
		[2,1,3] serialize -> 2!1!#!#!3!#!#!  split("!) -> [2, 1, #, #, 3, #, #]

		再用queue去 建BST 遇到#就返回null
*/
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null)
            return "#!";
        String res = root.val + "!";
        res += serialize(root.left);
        res += serialize(root.right);
        return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        System.out.println(data);
        String[] strings = data.split("!");
        System.out.println(Arrays.toString(strings));
        Queue<String> queue = new LinkedList();
        for (String val : strings) {
            queue.add(val);
        }
        return reconFromPreOrder(queue);
    }
    
    public TreeNode reconFromPreOrder(Queue<String> queue) {
        String val = queue.poll();
        if (val.equals("#"))
            return null;
        TreeNode root = new TreeNode(Integer.valueOf(val));
        root.left = reconFromPreOrder(queue);
        root.right = reconFromPreOrder(queue);
        return root;
    }
}