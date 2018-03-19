/*
	Longest Increasing Path in a Matrix
	Given an integer matrix, find the length of the longest increasing path.

	From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

	Example 1:

	nums = [
	  [9,9,4],
	  [6,6,8],
	  [2,1,1]
	]
	Return 4
	The longest increasing path is [1, 2, 6, 9].

	Example 2:

	nums = [
	  [3,4,5],
	  [3,2,6],
	  [2,2,1]
	]
	Return 4
	The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */

/*
    Key point: 用一个state数组来存储每一位上的最长路径数，set the larger number to 1， if find smaller near the larger one just add 1  to the smaller one
 */
public class Solution {
    int[] shift = {0, 1, 0, -1, 0};
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        
        int[][] state = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dfs(matrix, i, j, state));
            }
        }
        return res;
    }
    
    public int dfs(int[][] matrix, int x, int y, int[][] state) {
        if (state[x][y] > 0) {
            return state[x][y];
        }
        int max = 0;
        for (int i = 0; i < 4; i++) {
            int nearX = x + shift[i];
            int nearY = y + shift[i + 1];
            if (nearX >= 0 && nearX < matrix.length && nearY >= 0 && nearY < matrix[0].length && matrix[nearX][nearY] > matrix[x][y]) {
                max = Math.max(max, dfs(matrix, nearX, nearY, state));
            }
        }
        state[x][y] = 1 + max;
        return state[x][y];
    }
}