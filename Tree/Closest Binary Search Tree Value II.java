/*
	Closest Binary Search Tree Value II

	Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

	Note:
	Given target value is a floating point.
	You may assume k is always valid, that is: k ≤ total nodes.
	You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
	Follow up:
	Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

	Hint:

	Consider implement these two helper functions:
	getPredecessor(N), which returns the next smaller node to N.
	getSuccessor(N), which returns the next larger node to N.
	Try to assume that each node has a parent pointer, it makes the problem much easier.
	Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
	You would need two stacks to track the path in finding predecessor and successor node separately.

	Tags: Tree, Stack
*/


/*
	Solution:
    	中序遍历结果是将树中元素从小到大排列，逆式的中序遍历即先遍历右子树再访问根节点最后遍历左子树会得到树中元素从大到小排列的结果，
    	因此可通过中序遍历获取最接近target节点的predecessors，通过逆中序遍历获取最接近target节点的successors,
    	然后merge perdecessors 和 successors 获取最接近target节点的 k个节点值。 注意到在中序遍历时遇到比target 大的节点即停止，
    	因为由BST的性质可知后面的元素均会比target 大，即所有target的predecessors均已找到，同理逆中序遍历时遇到不大于 target的节点即可停止递归。 
*/

//Solution1 O(n + k)
public class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();
        Stack<Integer> pre = new Stack<>();
        Stack<Integer> post = new Stack<>();
        
        inorder(root, target, false, pre);
        inorder(root, target, true, post);

        while (k-- > 0) {
            if (pre.isEmpty()) {
                res.add(post.pop());
            } else if (post.isEmpty()) {
                res.add(pre.pop());
            } else if (Math.abs(pre.peek() - target) < Math.abs(post.peek() - target)) {
                res.add(pre.pop());
            } else {
                res.add(post.pop());
            }
        }
        return res;
    }

    public void inorder(TreeNode root, double target, boolean reverse, Stack<Integer> stack) {
        if (root == null) {
            return;
        }
        inorder(reverse ? root.right : root.left, target, reverse, stack);
        // early terminate, no need to traverse the whole tree
        if ((reverse && root.val <= target) || (!reverse && root.val > target)) {
            return;
        }
        // track the value of current node
        stack.push(root.val);
        inorder(reverse ? root.left : root.right, target, reverse, stack);
    }

}

//O(nlogk)
public class Solution {
	//用最小堆，首先用任意的遍历算法去遍历树，再将最小堆的堆顶依次poll加入res list
	public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();
        Queue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            //最小堆
            public int compare(Integer arg0, Integer arg1) {
                if (Math.abs(arg0 - target) > Math.abs(arg1 - target)) {
                    return 1;
                } else if (Math.abs(arg0 - target) < Math.abs(arg1 - target)) {
                    return - 1;
                } else {
                    return 0;
                }
            }
        });
        inorder(root, heap);
        for (int i = 0; i < k; i++) {
            res.add(heap.poll());
        }
        return res;
    }
    public void inorder(TreeNode root, Queue<Integer> heap) {
        if (root == null) {
            return;
        }
        heap.offer(root.val);
        inorder(root.left, heap);
        inorder(root.right, heap);
    }
}