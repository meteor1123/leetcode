/*
	A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
How many possible unique paths are there?
Tags:dp
*/

public class Solution {
    public int uniquePaths(int m, int n) {
    	if (m == 1 || n == 1)
    		return 1;
    	int[][] dp = new int[m][n];
    	for (int i = 0; i < m; i++) 
    		dp[i][0] = 1;
    	for (int j = 0; j < n; j++) 
    		dp[0][j] = 1;
    	dp[0][0] = 1;
    	for (int i = 1; i < m; i++) 
    		for (int j = 1; j < n; j++) 
    			dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
    	return dp[m - 1][n - 1];
    }
}


//one dimension
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j] + dp[j - 1];  //我们只需要保存上一层的信息就行，因此可以节省space，
                                            //这里的 dp[j] = dp[i][j] =  (dp[j] = dp[i - 1][j]) + (dp[j - 1] = dp[i][j - 1])
            }
        }
        return dp[n - 1];
    }