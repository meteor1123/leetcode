/*
	Perfect Squares
	Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

	For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
*/
/*
	动态规划（Dynamic Programming）

	时间复杂度：O(n * log n)

	初始化，令dp[y * y] = 1，其中y * y <= n

	状态转移方程：

	dp[x + y * y] = min(dp[x + y * y], dp[x] + 1);
    
    dp[i] = dp[i - j * j] + 1;
*/
public class Solution {
	//Solution1
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j*j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j*j] + 1);
            }
        }
        return dp[n];
    }

}

public class Solution {
    //Solution1
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j<= Math.sqrt(i); j++) {
                dp[i] = Math.min(dp[i], dp[i - j*j] + 1);
            }
        }
        return dp[n];
    }

}