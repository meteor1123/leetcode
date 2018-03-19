/*
	Set Matrix Zeroes 
	Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

	Follow up:
		Did you use extra space?
		A straight forward solution using O(mn) space is probably a bad idea.
		A simple improvement uses O(m + n) space, but still not the best solution.
		Could you devise a constant space solution?
	Tags: Array
*/


/*
	Solution: How to reduce the extra space to constant?
			  We can use the first row and first col to store the state of whether should set to zero.
			  if matrix[i][j] = 0,we set the matrix[i][0] and matrix[0][j] = 0, 

			  step1: check the first row and col whether has zero and set true
			  step2: check every matrix value, if has zero, set matrix[i][0] == 0 and matrix[0][j] == 0
			  step3: set the matrix to zer0,base on the first row and col
			  step4: check the hasZero state and reset the first row and first col
	running time: O(n*m)
	extra space: constant	
*/

public class Solution {
	public void setZeroes(int[][] matrix) {
		int rowLength = matrix.length;
		int colLength = matrix[0].length;
		if (rowLength == 0 || colLength == 0)
			return ;
		boolean hasZeroFirstRow = false, hasZeroFirstCol = false;

		//check the first row  whether has zero
		for (int i = 0; i < colLength; i++) {
			if (matrix[0][i] == 0) {
				hasZeroFirstRow = true;
				break;
			}
		}

		//check the first col whether has zero
		for (int i = 0; i < rowLength; i++) {
			if (matrix[i][0] == 0) {
				hasZeroFirstCol = true;
				break;
			}
		}

		//check every matrix value, if has zero, set matrix[i][0] == 0 and matrix[0][j] == 0
		for (int i = 1; i < rowLength; i++) {
			for (int j = 1; j < colLength; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}

		//set the matrix to zere,base on the first row and col
		for (int i = 1; i < rowLength; i++) {
			for (int j = 1; j < colLength; j++) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0)
					matrix[i][j] = 0;
			}
		}

		//check the hasZero state and reset the first row
		if (hasZeroFirstRow) {
			for (int i = 0; i < colLength; i++)
				matrix[0][i] = 0;
		}

		//check the hasZero state and reset the first col
		if (hasZeroFirstCol) {
			for (int i = 0; i < rowLength; i++)
				matrix[i][0] = 0;
		}
	}
}