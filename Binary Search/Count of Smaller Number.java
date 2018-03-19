/*  
	Count of Smaller Number Show result 
	Give you an integer array (index from 0 to n-1, where n is the size of this array, value from 0 to 10000) and an query list. For each query, give you an integer, return the number of element in the array that are smaller than the given integer.

	Have you met this question in a real interview? Yes
	Example
	For array [1,2,7,8,5], and queries [1,8,5], return [0,4,2]

	Note
	We suggest you finish problem Segment Tree Build and Segment Tree Query II first.

	Challenge
	Could you use three ways to do it.

	Just loop
	Sort and binary search
	Build Segment Tree and Search.
 */

//Solution1: Binary Search
public class Solution {
   /**
     * @param A: An integer array
     * @return: The number of element in the array that 
     *          are smaller that the given integer
     */
    public ArrayList<Integer> countOfSmallerNumber(int[] A, int[] queries) {
        // write your code here
        ArrayList<Integer> res = new ArrayList<>();
        Arrays.sort(A);
        for (int q : queries) {
            res.add(binarySearch(A, q));
        }
        return res;
    }
    
    public int binarySearch(int[] A, int val) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int start = 0;
        int end = A.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] < val) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (A[end] < val) {
            return end + 1;
        } else if (A[start] < val) {
            return start + 1;
        } else {
            return start;
        }
    }
}

//Solution2: Segment Tree
//public class Solution {
   /**
     * @param A: An integer array
     * @return: The number of element in the array that 
     *          are smaller that the given integer
     */
    public ArrayList<Integer> countOfSmallerNumber(int[] A, int[] queries) {
        // write your code here
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (A == null || queries == null || queries.length == 0) {
            return res;
        }
        Arrays.sort(A);
        SegmentTreeNode root = buildTree(A, 0, A.length - 1);
        for (int val : queries) {
            res.add(query(A, root, val));
        }
        return res;
    }
    
    public SegmentTreeNode buildTree(int[] A, int start, int end) {
        if (start > end) {
            return null;
        }
        SegmentTreeNode root = new SegmentTreeNode(start, end);
        if (start == end) {
            root.val = A[start];
        } else {
            int mid = start + (end - start) / 2;
            root.left = buildTree(A, start, mid);
            root.right = buildTree(A, mid + 1, end);
            root.val = root.left == null ? root.right.val : Math.max(root.left.val, root.right == null ? 0 : root.right.val);
        }
        return root;
    }
    public int query(int[] A, SegmentTreeNode root, int val) {
        if (root == null || A[root.start] > val) {
            return 0;
        }
        if (root.val < val) {
            return root.end - root.start + 1;
        }
        return query(A, root.left, val) + query(A, root.right, val);
    }
    class SegmentTreeNode {
        int start;
        int end;
        int val;
        SegmentTreeNode left;
        SegmentTreeNode right;
        public SegmentTreeNode() {
            
        }
        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
        }
        public SegmentTreeNode(int start, int end, int val) {
            this.start = start;
            this.end = end;
            this.val = val;
        }
    }
}
