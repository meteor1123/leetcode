/*
	Longest Increasing Continuous subsequence Show result 
	Give you an integer array (index from 0 to n-1, where n is the size of this array)，find the longest increasing continuous subsequence in this array. (The definition of the longest increasing continuous subsequence here can be from right to left or from left to right)

	Have you met this question in a real interview? Yes
	Example
	For [5, 4, 2, 1, 3], the LICS is [5, 4, 2, 1], return 4.

	For [5, 1, 2, 3, 4], the LICS is [1, 2, 3, 4], return 4.

	Note
	O(n) time and O(1) extra space.
*/


//Solution1
public class Solution {
    /**
     * @param A an array of Integer
     * @return  an integer
     */
    public int longestIncreasingContinuousSubsequence(int[] A) {
        // Write your code here
        int res = 0;
        if (A == null || A.length == 0) {
            return res;
        }
        boolean fromLeft = true;
        int start = 0;
        res = 1;
        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i - 1]) {
                if (fromLeft == true) {
                    res = Math.max(res, i - start + 1);
                } else {
                    start = i - 1;
                    fromLeft = true;
                }
            } else if (A[i] < A[i - 1]) {
                if (fromLeft == false) {
                    res = Math.max(res, i - start + 1);
                } else {
                    start = i - 1;
                    fromLeft = false;
                }
            }
        }
        return res;
    }
}


//Solution2
public class Solution {
	public int longestIncreasingContinuousSubsequence(int[] A) {
        // Write your code here
        if(A == null || A.length == 0) {
            return 0;
        }
        if(A.length == 1) {// corn case;
            return 1;
        }
        int count = 1;
        int max = 0;
        for(int i = 1; i < A.length; i++) {
            if(A[i] > A[i - 1]){
                count++;
            } else {
                count = 1;
            }
            max = Math.max(max, count);
        }
        count = 1;
        for(int i = A.length - 1; i > 0; i--) {
            if(A[i] < A[i - 1]){
                count++;
            }else{
                count = 1;
            }
            max = Math.max(max, count);
        }
        return max;
    }
}
