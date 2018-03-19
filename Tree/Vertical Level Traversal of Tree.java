/*
	Vertical Level Traversal of Tree
	Print arbitrary binary tree by vertical levels / columns from left to right


	example tree

	      a
	     / \
	    b   c
	   / \   \
	  d   g   z. 
	   \     /
	    e   i
	       /
	      q
	     /
	    x
	   / 
	  x1
	/
	x2


	sample output

	x2
	d x1
	b e x. 
	a g q
	c i

*/
//no result
//http://siyang2notleetcode.blogspot.com/2015/02/vertical-level-traversal-of-tree.html
public void verticalLevelTraversalofTree(TreeNode root){
	if (root == null) {
		return ;
	}
	//map's key is column, we assume the root column is zero, the left node will minus 1 ,and the right node will plus 1
	HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
	//use a queue to do bfs
	LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
	//use a HashMap to store the TreeNode and the according cloumn value
	HashMap<TreeNode, Integer> weight = new HashMap<TreeNode, Integer>();

	queue.add(root);
	weight.put(root, 0);
	int min = 0;
	while (!queue.isEmpty()) {
		TreeNode node = queue.poll();
		int w = weight.get(node);

		//if map doesn't has this column value, just create a list ,and put into map
		if (!map.containsKey(w)) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			list.add(node.val);
			map.put(w, list);
		} else {
			// ArrayList<Integer> list = map.get(w);
			// list.add(node.val);
			map.put(w, map.get(w).add(node.val));
		}

		//enqueue
		if (node.left != null) {
			queue.add(node.left);
			//put the node to weight hashmap
			weight.put(node.left, w - 1);
		}
		if (node.right != null) {
			queue.add(node.right);
			weight.put(node.right, w + 1);
		}
		//update min ,min means the minimum column value, which is the left most node
		min = Math.min(min, w);
	}
	while (map.containsKey(min)) {
		for (int val : map.get(min)) {
			System.output.print(val + " ");
		}
		System.output.println("");
		min++;
	}
}



//Has return
public List<List<Integer>> verticalLevelTraversalofTree(TreeNode root){
	List<List<Integer>> res = new ArrayList<>();
	//map's key is column, we assume the root column is zero, the left node will minus 1 ,and the right node will plus 1
	HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
	//use a queue to do bfs
	LinkedList<TreeNode> queue = new LinkedList<>();
	//use a HashMap to store the TreeNode and the according cloumn value
	HashMap<TreeNode, Integer> weight = new HashMap<>();
	if (root == null) {
		return res;
	}
	queue.add(root);
	weight.put(root, 0);
	int min = 0;
	while (!queue.isEmpty()) {
		TreeNode node = queue.poll();
		int w = weight.get(node);
		//if map doesn't has this column value, just create a list ,and put into map
		if (!map.containsKey(w)) {
			ArrayList<Integer> list = new ArrayList<>();
			list.add(node.val);
			map.put(w, list);
		} else {
			ArrayList<Integer> list = map.get(w);
			list.add(node.val);
			map.put(w, list);
		}
		//enqueue
		if (node.left != null) {
			queue.add(node.left);
			//put the node to weight hashmap
			weight.put(node.left, w - 1);
		}
		if (node.right != null) {
			queue.add(node.right);
			weight.put(node.right, w + 1);
		}
		//update min ,min means the minimum column value, which is the left most node
		min = Math.min(min, w);
	}
	//generate res
	while(map.containsKey(min)) {
		res.add(map.get(min++));
	}
	return res;
}





