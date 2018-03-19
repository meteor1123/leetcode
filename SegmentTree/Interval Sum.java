/*
	Interval Sum

	Given an integer array (index from 0 to n-1, where n is the size of this array), and an query list. Each query has two integers [start, end]. For each query, calculate the sum number between index start and end in the given array, return the result list.

	Have you met this question in a real interview? Yes
	Example
	For array [1,2,7,8,5], and queries [(0,4),(1,2),(2,4)], return [23,9,20]

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
    int[] nums;
    int[] BIT;
    int n;
    
    public ArrayList<Long> intervalSum(int[] A, ArrayList<Interval> queries) {
        // write your code here
        ArrayList<Long> res = new ArrayList<Long>();
        this.nums = A;
        n = A.length;
        BIT = new int[n + 1];
        for (int i = 0; i < n; i++) {
            init(i, nums[i]);
        }
        
        for (Interval interval : queries) {
            res.add(sumRange(interval.start, interval.end));
        }
        return res;
    }
    
    public void init(int i, int val) {
        i++;
        while (i <= n) {
            BIT[i] += val;
            i += (i & -i);
        }
    }
    
    public long getSum(int i) {
        long sum = 0;
        i++;
        while (i > 0) {
            sum += BIT[i];
            i -= (i & -i);
        }
        return sum;
    }
    
    public long sumRange(int i, int j) {
        return getSum(j) - getSum(i - 1);
    }
}
