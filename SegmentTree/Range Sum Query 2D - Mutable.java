/*
	Range Sum Query 2D - Mutable
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
	update(3, 2, 2)
	sumRegion(2, 1, 4, 3) -> 10
	Note:
		The matrix is only modifiable by the update function.
		You may assume the number of calls to update and sumRegion function is distributed evenly.
		You may assume that row1 ≤ row2 and col1 ≤ col2.
	Tags: Segment Tree Binary Indexed Tree
*/

//Solution1
public class NumMatrix {
    private int[][] colSums;
    private int[][] matrix;

    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        this.matrix = matrix;
        
        int m = matrix.length;
        int n = matrix[0].length;
        colSums = new int[m + 1][n];
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < n; j++) {
                colSums[i][j] = colSums[i - 1][j] + matrix[i - 1][j];
            }
        }
    }

    public void update(int row, int col, int val) {
        for (int i = row + 1; i < colSums.length; i++) {
            colSums[i][col] =  colSums[i][col] - matrix[row][col] + val;
        }
        matrix[row][col] = val;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int res = 0;
        for (int j = col1; j <= col2; j++) {
            res += colSums[row2 + 1][j] - colSums[row1][j];
        }
        return res;
    }
}

//Solution2
public class NumMatrix {
    int m, n;
    int[][] nums;
    int[][] BIT;
    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        m = matrix.length;
        n = matrix[0].length;
        nums = new int[m][n];
        BIT = new int[m + 1][n + 1];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                init(i, j, matrix[i][j]);
                nums[i][j] = matrix[i][j];
            }
        }
    }

    public void init(int i, int j, int val) {
        i++;
        j++;
        while (i <= m) {
            int k = j;
            while (k <= n) {
                BIT[i][k] += val;
                k += k & (-k);
            }
            i += i & (-i);
        }
    }
    public void update(int i, int j, int val) {
        int diff = val - nums[i][j];
        nums[i][j] = val;
        init(i, j, diff);
    }
    
    public int getSum(int i, int j) {
        int sum = 0;
        i++;
        j++;
        while (i > 0) {
            int k = j;
            while (k > 0) {
                sum += BIT[i][k];
                k -= k & (-k);
            }
            i -= i & (-i);
        }
        return sum;
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return getSum(row2, col2) - getSum(row1 - 1, col2) - getSum(row2, col1 - 1) + getSum(row1 - 1, col1 - 1);
    }
}


// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.update(1, 1, 10);
// numMatrix.sumRegion(1, 2, 3, 4);