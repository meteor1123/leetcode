/*
	Segment Tree Query
	For an integer array (index from 0 to n-1, where n is the size of this array), 
	in the corresponding SegmentTree, each node stores an extra attribute max to denote the maximum number in the interval of the array (index from start to end).

	Design a query method with three parameters root, start and end, 
	find the maximum number in the interval [start, end] by the given root of segment tree.

	Example
		For array [1, 4, 2, 3], the corresponding Segment Tree is:

		                  [0, 3, max=4]
		                 /             \
		          [0,1,max=4]        [2,3,max=3]
		          /         \        /         \
		   [0,0,max=1] [1,1,max=4] [2,2,max=2], [3,3,max=3]
		query(root, 1, 1), return 4

		query(root, 1, 2), return 4

		query(root, 2, 3), return 3

		query(root, 0, 2), return 4
*/

public class Solution {
	public int query(SegmentTreeNode root, int start, int end) {
		if (start == root.start && end == root.end) {
            return root.max;
        }

        int mid = (root.start + root.end) / 2;
        int leftmax = Integer.MIN_VALUE;
        int rightmax = Integer.MIN_VALUE；

        if (start <= mid) {
        	// if (mid < end) {
            //     leftmax = query(root.left, start, mid);
            // } else {
            //     leftmax = query(root.left, start, end);
            // }
        	leftmax = query(root.left, start, Math.min(mid, end));
        }

        if (mid < end) {
        	// if (start <= mid) {
            //     rightmax = query(root.right, mid + 1, end);
            // } else {
            //     rightmax = query(root.right, start, end);
            // }
        	rightmax = query(root.right, Math.max(mid + 1, start), end);
        }
        return Math.max(leftmax, rightmax);
	}
}

