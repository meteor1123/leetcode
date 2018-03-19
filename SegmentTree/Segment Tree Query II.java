/*
	Segment Tree Query II

	Medium Segment Tree Query II

	28% Accepted
	For an array, we can build a SegmentTree for it, each node stores an extra attribute count to denote the number of elements in the the array which value is between interval start and end. (The array may not fully filled by elements)

	Design a query method with three parameters root, start and end, find the number of elements in the in array's interval [start, end] by the given root of value SegmentTree.

	Example
		For array [0, 2, 3], the corresponding value Segment Tree is:

		                     [0, 3, count=3]
		                     /             \
		          [0,1,count=1]             [2,3,count=2]
		          /         \               /            \
		   [0,0,count=1] [1,1,count=0] [2,2,count=1], [3,3,count=1]
           
		query(1, 1), return 0

		query(1, 2), return 1

		query(2, 3), return 2

		query(0, 2), return 2

	Note
		It is much easier to understand this problem if you finished Segment Tree Buildand Segment Tree Query first.
*/
/*
    对于一个数组，我们可以对其建立一棵线段树, 每个结点存储一个额外的值count来代表这个结点所指代的数组区间内的元素个数. (数组中并不一定每个位置上都有元素)
    实现一个query的方法，该方法接受三个参数root, start和end, 分别代表线段树的根节点和需要查询的区间，找到数组中在区间[start, end]内的元素个数。
*/
/**
 * Definition of SegmentTreeNode:
 * public class SegmentTreeNode {
 *     public int start, end, count;
 *     public SegmentTreeNode left, right;
 *     public SegmentTreeNode(int start, int end, int count) {
 *         this.start = start;
 *         this.end = end;
 *         this.count = count;
 *         this.left = this.right = null;
 *     }
 * }
 */

/*
    本题为值型线段树的Query
    如果查询区间 == 节点表示区间 => 直接返回root.count
    如果查询区间被节点表示的左/右区间包含 => 递归搜索左/右区间
    如果查询区间和结点表示的区间相交不相等 => 分裂递归搜索左/右区间
    典型的divide & conquer
    最后返回 leftCount + rightCount
*/
public class Solution {
    /**
     *@param root, start, end: The root of segment tree and 
     *                         an segment / interval
     *@return: The count number in the interval [start, end]
     */
    public class Solution {
        public int query(SegmentTreeNode root, int start, int end) {
            if (start > end || root == null) {
                return 0;
            }
            if (start <= root.start && root.end <= end) {
                return root.count;
            }
            int mid = (root.start + root.end) / 2;
            int leftsum = 0, rightsum = 0;

            //left side
            if (start <= mid && root.left != null) {
                leftsum = query(root.left, start, Math.min(mid, end));
            }
            //right side
            if (mid < end && root.right != null) {
                rightsum = query(root.right, Math.max(start, mid + 1), end);
            }
            return leftsum + rightsum;
        }
    }
}