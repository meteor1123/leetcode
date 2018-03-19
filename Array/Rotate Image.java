/*
	Rotate Image
	You are given an n x n 2D matrix representing an image.
	Rotate the image by 90 degrees (clockwise).

	Follow up:
	Could you do this in-place?
	Tags: Array
*/
/*
    The idea was firstly transpose the matrix and then flip it symmetrically. For instance,

    1  2  3
    4  5  6
    7  8  9
    after transpose, it will be swap(matrix[i][j], matrix[j][i])

    1  4  7
    2  5  8
    3  6  9
    Then flip the matrix horizontally. (swap(matrix[i][j], matrix[i][matrix.length-1-j])

    7  4  1
    8  5  2
    9  6  3
*/
public class Solution {
    public void rotate(int[][] matrix) {
    	if (matrix == null || matrix.length == 0) {
    		return ;
    	}
    	int m = matrix.length;
    	int n = matrix[0].length;
    	for (int i = 0; i < m; i++) {
    		for (int j = i; j < n; j++) {
    			int temp = matrix[i][j];
    			matrix[i][j] = matrix[j][i];
    			matrix[j][i] = temp;
    		}
    	}

    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n / 2; j++) {
    			int temp = matrix[i][j];
    			matrix[i][j] = matrix[i][n - j - 1];
    			matrix[i][n - j - 1] = temp;
    		}
    	}
    }
}
