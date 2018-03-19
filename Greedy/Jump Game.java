/*
	Jump Game
	Given an array of non-negative integers, you are initially positioned at the first index of the array.

	Each element in the array represents your maximum jump length at that position.

	Determine if you are able to reach the last index.

	For example:
	A = [2,3,1,1,4], return true.
	A = [3,2,1,0,4], return false.
*/

/*

*/
public class Solution {

    //Solution by never
    public boolean canJump(int[] A) {
        if (A == null || A.length == 0) {
            return false;
        }
        int global = 0;
        for (int i = 0; i <= global && i < A.length; i++) {
            global = Math.max(A[i] + i, global);
        }
        return global >= A.length - 1 ? true : false;
    }
	//DP

    //dp[i] means  this index can jump to the end point or not.
    public boolean canJump(int[] A) {
    	if (A == null || A.length == 0) {
    		return false;
    	}
        int[] dp = new int[A.length];
    	dp[0] = true;
    	for (int i = 1; i < A.length; i++) {
    		for (int j = 0; j < i; j++) {
    			if (dp[j] && A[j] + j >= i) {
    				dp[i] = true;
    				break;
    			}
    		}
    	}
    	return dp[A.length - 1];
    }

    //Greedy from chapter 9
    public boolean canJump(int[] A) {
    	if (A == null || A.length == 0) {
    		return false;
    	}
    	int farthest = A[0];
    	for (int i = 1; i < A.length; i++) {
    		//i <= farthest 代表 在farthest能走的最远距离之内，找一个A[i] ，并且A[i] + i
    		//还大于farthest的话 就更新最远能走的距离
    		if (i <= farthest && A[i] + i >= farthest) {
    			farthest = A[i] + i;
    		}
    	}
    	return farthest >= A.length - 1;
    }

    //Greedy from xiaowanzi
    public boolean canJump(int[] A) {
        int maxCover = 0;
        for (int start = 0; start < A.length && start <= maxCover; start++) {
            if (A[start] + start > maxCover) {
                maxCover = A[start] + start;
            }
            if (maxCover >= A.length - 1) {
                return true;
            }
        }
        return false;
    }
}