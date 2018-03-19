/*
	Continuous Subarray Sum

	Given an integer array, find a continuous subarray where the sum of numbers is the biggest. 
    
    Your code should return the index of the first number and the index of the last number. (If their are duplicate answer, return anyone)

	Example
	Give [-3, 1, 3, -3, 4], return [1,4].
 */


public class Solution {
	public ArrayList<Integer> continuousSubarraySum(int[] A) {
        // Write your code here
        ArrayList<Integer> res = new ArrayList<>();
        if (A == null || A.length == 0) {
            return res;
        }
        int curSum = 0;
        int maxSum = Integer.MIN_VALUE;
        int start = 0;
        res.add(0);
        res.add(0);
        for (int i = 0; i < A.length; i++) {
            curSum += A[i];
            if (maxSum < curSum) {
                res.set(0, start);
                res.set(1, i);
                maxSum = curSum;
            }
            if (curSum < 0) {
                curSum = 0;
                start = i + 1;
            }
            
        }
        
        return res;
    }
}
