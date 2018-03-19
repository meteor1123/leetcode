/*
	Interval Minimum Number

	Given an integer array (index from 0 to n-1, where n is the size of this array), and an query list. Each query has two integers [start, end]. For each query, calculate the minimum number between index start and end in the given array, return the result list.

	Have you met this question in a real interview? Yes
	Example
	For array [1,2,7,8,5], and queries [(1,2),(0,4),(2,4)], return [2,1,5]

	Note
	We suggest you finish problem Segment Tree Build, Segment Tree Query and Segment Tree Modify first.

	Challenge
	O(logN) time for each query
 */


/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */
public class Solution {
    /**
     *@param A, queries: Given an integer array and an query list
     *@return: The result list
     */
    public ArrayList<Integer> intervalMinNumber(int[] A, ArrayList<Interval> queries) {
        // write your code here
        ArrayList<Integer> res = new ArrayList<>();
        if (A == null || A.length == 0 || queries == null || queries.size() == 0) {
            return res;
        }
        MinTreeNode root = buildTree(A, 0, A.length - 1);
        for (Interval interval : queries) {
            res.add(getVal(root, interval.start, interval.end));
        }
        return res;
    }
    
    public int getVal(MinTreeNode root, int from, int to) {
        if (root == null || root.end < from || root.start > to) {
            return Integer.MAX_VALUE;
        }
        if (root.start == root.end || (from <= root.start && to >= root.end)) {
            return root.min;
        }
        return Math.min(getVal(root.left, from, to), getVal(root.right, from, to));
    }
    
    public MinTreeNode buildTree(int[] A, int from, int to) {
        if (from > to) {
            return null;
        }
        if (from == to) {
            return new MinTreeNode(from, to, A[from]);
        }
        MinTreeNode root = new MinTreeNode(from, to);
        root.left = buildTree(A, from, (from + to) / 2);
        root.right = buildTree(A, (from + to) / 2 + 1, to);
        if (root.left == null) {
            root.min = root.right.min;
        } else if (root.right == null) {
            root.min = root.left.min;
        } else {
            root.min = Math.min(root.left.min, root.right.min);
        }
        return root;
    }
    
    class MinTreeNode {
        int start;
        int end;
        int min;
        MinTreeNode left;
        MinTreeNode right;
        public MinTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        public MinTreeNode(int start, int end, int min) {
            this(start, end);
            this.min = min;
        }
    }
}