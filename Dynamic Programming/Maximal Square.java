 /*
	Maximal Square
	Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.
	For example, given the following matrix:
		1 0 1 0 0
		1 0 1 1 1
		1 1 1 1 1
		1 0 0 1 0
	return 4

*/

/*
	Solution:
	https://leetcode.com/discuss/38489/easy-solution-with-detailed-explanations-8ms-time-and-space
*/

/*
	Well, this problem desires for the use of dynamic programming. They key to any DP problem is to come up with the state equation. 
	In this problem, we define the state to be the maximal size of the square that can be achieved at point (i, j), denoted as P[i][j]. Remember that we use size instead of square as the state (square = size^2).

    Now let's try to come up with the formula for P[i][j].

    First, it is obvious that for the topmost row (i = 0) and the leftmost column (j = 0), P[i][j] = matrix[i][j]. 
    This is easily understood. Let's suppose that the topmost row of matrix is like [1, 0, 0, 1]. 
    Then we can immediately know that the first and last point can be a square of size 1 while the two middle points cannot make any square, 
    giving a size of 0. Thus, P = [1, 0, 0, 1], which is the same as matrix. The case is similar for the leftmost column. 
    Till now, the boundary conditions of this DP problem are solved.

    Let's move to the more general case for P[i][j] in which i > 0 and j > 0. First of all, 
    let's see another simple case in which matrix[i][j] = 0. It is obvious that P[i][j] = 0 too. 
    Why? Well, since matrix[i][j] = 0, no square will contain matrix[i][j]. 
    According to our definition of P[i][j], P[i][j] is also 0.

    Now we are almost done. The only unsolved case is matrix[i][j] = 1. Let's see an example.

    Suppose matrix = [[0, 1], [1, 1]], it is obvious that P[0][0] = 0, P[0][1] = P[1][0] = 1, 
    what about P[1][1]? Well, to give a square of size larger than 1 in P[1][1], 
    all of its three neighbors (left, up, left-up) should be non-zero, right? 
    In this case, the left-up neighbor P[0][0] = 0, so P[1][1] can only be 1, 
    which means that it contains the square of itself.

    Now you are near the solution. In fact, P[i][j] = min(P[i - 1][j], P[i][j - 1], P[i - 1][j - 1]) + 1 in this case.

    Taking all these together, we have the following 

    state equations:

	P[0][j] = matrix[0][j] (topmost row);
	P[i][0] = matrix[i][0] (leftmost column);

	For i > 0 and j > 0: if matrix[i][j] = 0, P[i][j] = 0; 
		if matrix[i][j] = 1, P[i][j] = min(P[i - 1][j], P[i][j - 1], P[i - 1][j - 1]) + 1.
	Putting them into codes, and maintain a variable maxsize to record the maximum size of the square we have seen, 
	we have the following (unoptimized) solution.
*/
public class Solution {
    //Solution1: prefer
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        /*
            dp[i][j] 代表在以i, j这一格为右下角的正方形边长。
            如果这一格的值也是1，那这个正方形的边长就是他的上面，左边，和斜上的值的最小边长 +1。因为如果有一边短了缺了，都构成不了正方形。
        */
        int[][] dp = new int[m + 1][n + 1]; 
        
        int maxArea = dp[0][0];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
                
                maxArea = Math.max(maxArea, dp[i][j] * dp[i][j]);
            }
        }
        return maxArea;
    }

    /*
    	Solution2: only use one array
    */
    public int maximalSquare(char[][] matrix) {
    	if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int[] dp = new int[matrix[0].length + 1];
        int maxsize = 0;
        int last_topleft = 0;//use last_toplest to store the dp[i - 1][j - 1]
        for (int i = 1; i <= matrix.length; i++) {
        	for (int j = 1; j <= matrix[0].length; j++) {
        		int temp = dp[j];
        		if (matrix[i - 1][j - 1] == '0') {
        			dp[j] = 0;
        		} else {
        		    /*
        		        dp[j - 1] = dp[i][j - 1];
        		        dp[j] = dp[i - 1][j];
        		        last_top = dp[i - 1][j - 1]
        		    */
        			dp[j] = Math.min(Math.min(dp[j], dp[j - 1]), last_topleft) + 1;
        			maxsize = Math.max(maxsize, dp[j]);
        		}
        		last_topleft = temp;
        	}
        }
        return maxsize * maxsize;
    }
}