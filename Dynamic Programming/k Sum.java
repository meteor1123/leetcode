/*
	k Sum
	Given n distinct positive integers, integer k (k <= n) and a number target.

	Find k numbers where sum is target. Calculate how many solutions there are?

	Have you met this question in a real interview? Yes
	Example
	Given [1,2,3,4], k = 2, target = 5.

	There are 2 solutions: [1,4] and [2,3].

	Return 2.

	Tags: Dynamic Programming
 */

//Solution1: O(m*n*k)
public class Solution {
    /**
     * @param A: an integer array.
     * @param k: a positive integer (k <= length(A))
     * @param target: a integer
     * @return an integer
     */
    public int kSum(int A[], int k, int target) {
        // write your code here
        if (target < 0) {
            return 0;
        }
        int len = A.length;
        int[][][] dp = new int[len + 1][k + 1][target + 1];
        dp[0][0][0] = 1;
        
        for (int i = 0; i <= len; i++) {
            for (int j = 0; j <= k; j++) {
                for (int t = 0; t <= target; t++) {
                    if (j == 0 && t == 0) {
                        dp[i][j][t] = 1;
                    } else if (!(i == 0 || j == 0 || t == 0)) {
                        dp[i][j][t] = dp[i - 1][j][t];
                        if (t - A[i - 1] >= 0) {
                            dp[i][j][t] += dp[i - 1][j - 1][t - A[i - 1]];
                        }
                    }
                }
            }
        }
        return dp[len][k][target];
    }
}

//Solution2: O(m*n)
public class Solution {
    public int kSum(int A[], int k, int target) {
        if (target < 0) {
            return 0;
        }
        int len = A.length;
        int[][] dp = new int[k + 1][target + 1];
        
        dp[0][0] = 1;
        for (int i = 1; i <= len; i++) {
            for (int t = target; t > 0; t--) {
                for (int j = 1; j <= k; j++) {
                    if (t - A[i - 1] >= 0) {
                        dp[j][t] += dp[j - 1][t - A[i - 1]];
                    }
                }
            }
        }
        return dp[k][target];
    }
}