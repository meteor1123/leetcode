/*
	Range Sum Query 2D - Immutable
	Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

	Range Sum Query 2D
	The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

	Example:
		Given matrix = [
		  [3, 0, 1, 4, 2],
		  [5, 6, 3, 2, 1],
		  [1, 2, 0, 1, 5],
		  [4, 1, 0, 1, 7],
		  [1, 0, 3, 0, 5]
		]

		sumRegion(2, 1, 4, 3) -> 8
		sumRegion(1, 1, 2, 2) -> 11
		sumRegion(1, 2, 2, 4) -> 12

	Note:
		You may assume that the matrix does not change.
		There are many calls to sumRegion function.
		You may assume that row1 ≤ row2 and col1 ≤ col2.
*/

//Best Solution1
/*
	Complexity analysis:
	The pre-computation takes O(mn) time. 
	It uses O(mn) space to store the cumulative region sum. 
	Each sumRegion query takes O(1) time.
*/
public class NumMatrix {
	private int[][] dp;
	public NumMatrix(int[][] matrix) {
	    if (matrix.length == 0 || matrix[0].length == 0) return;
	    dp = new int[matrix.length + 1][matrix[0].length + 1];
	    for (int i = 0; i < matrix.length; i++) {
	        for (int j = 0; j < matrix[0].length; j++) {
	            dp[i + 1][j + 1] = dp[i + 1][j] + dp[i][j + 1] + matrix[i][j] - dp[i][j];
	        }
	    }
	}
	//（row1,col1） 是矩形左上角的顶点， (row2, col2)是矩形右下角的顶点
	public int sumRegion(int row1, int col1, int row2, int col2) {
	    return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
	}

	/*
		比如  1 2 3
			 4 5 6   求5,9 之间矩形的和就等于 = 1为左上顶点9为右下顶点的这个大矩形 -  （1 2 3） - （1 4 7） + （1） =  （5 6 8 9）
			 7 8 9 
	*/
}


//Solution3
public class NumMatrix {
    int[][] dp;
    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
    }
}


//Solution2
/*
	Complexity analysis:
	The pre-computation takes O(mn) time. 
	It uses O(mn) space to store the cumulative sum of all rows. 
	The sumRegion query takes O(m) time.
*/
public class NumMatrix {
    private int[][] dp;
    public NumMatrix(int[][] matrix) {
        dp = matrix;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] += dp[i][j - 1];
            }
        }
    }
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int res = 0;
        for (int i = row1; i <= row2; i++) {
           res += dp[i][col2];
           if (col1 != 0) {
              res -= dp[i][col1 - 1];
           }
        }
        return res;
    }
}






// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.sumRegion(1, 2, 3, 4);