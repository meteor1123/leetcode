package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> res = new ArrayList<>();
		int row = matrix.length;
		int column = matrix[0].length;
		spiral(matrix, 0, row, column, res);
		return res;
	}
	private void spiral(int[][] matrix, int offset, int row, int column, List<Integer> res) {
		if (row == 0 || column == 0) return;
		if (row == 1 && column == 1) {
			res.add(matrix[offset][offset]);
			return;
		}
		if (row == 1) {
			for (int i = 0; i < column; i++) {
				res.add(matrix[0 + offset][i + offset]);
			}
			return;
		} else if (column == 1) {
			for (int i = 0; i < row; i++) {
				res.add(matrix[i + offset][0 + offset]);
			}
			return;
		}
		
		for (int i = 0; i < column - 1; i++) {
			res.add(matrix[0 + offset][i + offset]);
		}
		for (int i = 0; i < row - 1; i++) {
			res.add(matrix[i + offset][column - 1 + offset]);
		}
		for (int i = column - 1; i > 0; i--) {
			res.add(matrix[row - 1 + offset][i + offset]);
		}
		for (int i = row - 1; i > 0; i--) {
			res.add(matrix[i + offset][0 + offset]);
		}
		spiral(matrix, offset + 1, row - 2, column - 2, res);
	}
	public static void main(String[] args) {
		SpiralMatrix test = new SpiralMatrix();
		int[][] matrix = {{1,2,3,4,5},{16,17,18,19,6},{15,24,25,20,7},{14,23,22,21,8},{13,12,11,10,9}};
		int[][] matrix2 = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
		List<Integer> res = test.spiralOrder(matrix2);
		System.out.println(res);
	}
}
