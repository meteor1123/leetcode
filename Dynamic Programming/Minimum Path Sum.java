/* 
	Minimum Path Sum
	Given a m x n grid filled with non-negative numbers, 
	find a path from top left to bottom right which minimizes the sum of all numbers along its path.
	Note: You can only move either down or right at any point in time.
*/

//Solution1:
public class Solution {
	//two dimension dp
	 public int minPathSum(int[][] grid) {
	 	int m = grid.length;
	 	int n = grid[0].length;

	 	int[][] dp = new int[m][n];
	 	dp[0][0] = grid[0][0];

	 	//注意不要加错
	 	//initialze the first column
	 	for (int i = 1; i < m; i++) 
	 		dp[i][0] = grid[i][0] + dp[i - 1][0];
	 	//initialize the first row
	 	for (int j = 1; j < n; j++)
	 		dp[0][j] = grid[0][j] + dp[0][j - 1];

	 	for (int i = 1; i < m; i++) {
	 		for (int j = 1; j < n; j++) {
	 			dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
	 		}
	 	}
	 	return dp[m - 1][n - 1];
	 }
}

//Solution2
public class Solution {
	 //one dimension dp
	public int minPathSum(int[][] grid) {
	    if(grid == null || grid.length==0 || grid[0].length==0)
	        return 0;
	    int[] res = new int[grid[0].length];
	    res[0] = grid[0][0];
	    for(int i = 1; i < grid[0].length; i++) {
	        res[i] = res[i - 1] + grid[0][i];
	    }
	    for(int i = 1;i < grid.length; i++) {
	        for(int j = 0; j < grid[0].length; j++) {
	            if(j == 0)
	                res[j] += grid[i][j];
	            else
	                res[j] = Math.min(res[j - 1], res[j]) + grid[i][j];
	        }
	    }
	    return res[grid[0].length - 1];
	}


//Solution3: prefer
public class Solution {
	//Prefer one dimension
	public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[1] = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[j + 1] = Math.min(dp[j + 1], dp[j]) + grid[i][j];
            }
        }
        return dp[n];
    }
}