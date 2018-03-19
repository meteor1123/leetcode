/*
	Submatrix Sum
	Given an integer matrix, find a submatrix where the sum of numbers is zero. Your code should return the coordinate of the left-up and right-down number.

	Example
	Given matrix

	[
	  [1 ,5 ,7],
	  [3 ,7 ,-8],
	  [4 ,-8 ,9],
	]
	return [(1,1), (2,2)]

	Challenge
	O(n3) time.
 */



public class Solution {
    /**
     * @param matrix an integer matrix
     * @return the coordinate of the left-up and right-down number
     */
    public int[][] submatrixSum(int[][] matrix) {
        // Write your code here
        if (matrix == null && matrix.length == 0 && matrix[0].length == 0) {
            return new int[][]{};
        }
        
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        
        int[][] sum = new int[numRows + 1][numCols + 1];
        
        //preprocess to get sum of all submatrix[(0, 0), (i, j)]
        
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                sum[i + 1][j + 1] = matrix[i][j] + sum[i + 1][j] + sum[i][j + 1] - sum[i][j];
            }
        }
        
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numRows; i++) {
            for (int j = i + 1; j <= numRows; j++) {
                map.clear();
                map.put(0, 0);
                for (int k = 1; k <= numCols; k++) {
                    int diff = sum[j][k] - sum[i][k];
                    if (map.containsKey(diff)) {
                        return new int[][]{{i, map.get(diff)}, {j - 1, k - 1}};
                    }
                    map.put(diff, k);
                }
            }
        }
        return null;
    }
}


