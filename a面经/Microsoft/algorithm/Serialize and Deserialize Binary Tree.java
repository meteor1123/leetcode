/*
	Serialize and Deserialize Binary Tree
	Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

	Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

	For example, you may serialize the following tree
*/



/*
        1
       / \
      2   3
         / \
        4   5
     Serialize：   用queue level order变成 "1,2,3,#,#,4,5,#,#,#,#,"   #代表该node为空，点号分割每一个node，记得要把最后一个点号删掉-> 1,2,3,#,#,4,5,#,#,#,#
                   这里用for循环，限制为每一层的size，控制每一层的String生成
     deserialize:  设置一个String[], 按照","号分割取得TreeNode的值

*/

//Solution1 queue iterative
public class Codec {
	public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur != null) {
                    queue.offer(cur.left);
                    queue.offer(cur.right);
                }
                String val = cur == null ? "#" : String.valueOf(cur.val);
                sb.append(val);
                sb.append(",");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
	// Decodes your encoded data to tree.
     public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] val = data.split(",");
        TreeNode root = new TreeNode(Integer.valueOf(val[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        //i控制string数组里的值，queue控制遍历的TreeNode，val[i] ,val[i + 1] 确保是 cur的left和right节点值，则有 
        for (int i = 1; i < val.length; i += 2) {
            TreeNode cur = queue.poll();
            if (!"#".equals(val[i])) {
                TreeNode left = new TreeNode(Integer.valueOf(val[i]));
                cur.left = left;
                queue.offer(left);
            }
            if (i + 1 < data.length() && !"#".equals(val[i + 1])) {
                TreeNode right = new TreeNode(Integer.valueOf(val[i + 1]));
                cur.right = right;
                queue.offer(right);
            }
        }
        return root;
    }
}




//Solution2: Recursive Method, DFS, preorder
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }
    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("#").append(" ");
        } else {
            sb.append(node.val).append(" ");
            buildString(node.left, sb);
            buildString(node.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }
        String[] strs = data.split(" ");
        Queue<String> queue = new LinkedList<>();
        queue.addAll(Arrays.asList(strs));
        return buildTree(queue);
    }
    
    public TreeNode buildTree(Queue<String> queue) {
        String val = queue.poll();
        if (val.equals("#")) {
            return null;
        } else {
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = buildTree(queue);
            node.right = buildTree(queue);
            return node;
        }
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));