package OA2;

public class RotateMatrix {
	public static int[][]  Solution(int[][] matrix, int flag) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return matrix;
		}
		int[][] res = replace(matrix);
		invert(res, flag);
		return res;
	}
	
	public static int[][] replace(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		int[][] res = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				res[i][j] = matrix[j][i];
			}
		}
		return res;
	}
	
	public static void invert(int[][] matrix, int flag) {
		int m = matrix.length;
		int n = matrix[0].length;
		if (flag == 1) {
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n / 2; j++) {
					int temp = matrix[i][j];
					matrix[i][j] =  matrix[i][n - j - 1];
					matrix[i][n - j - 1] = temp;
				}
			}
		} else {
			for (int i = 0; i < m / 2; i++) {
				for (int j = 0; j < n; j++) {
					int temp  = matrix[i][j];
					matrix[i][j] = matrix[m - i - 1][j];
					matrix[m - i - 1][j] = temp;
				}
			}
		}
	}
//	public static int[][] Solution(int[][] matrix, int flag) {
//		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)	return matrix;
//		//int m = matrix.length, n = matrix[0].length;
//		int[][] rvalue;
//		rvalue = transpose(matrix);
//		flip(rvalue, flag);
//		return rvalue;
//	}
//	
//	private static int[][] transpose(int[][] matrix) {
//		int m = matrix.length, n = matrix[0].length;
//		int[][] rvalue = new int[n][m];
//		for (int i = 0; i < n; i++)
//			for (int j = 0; j < m; j++)
//				rvalue[i][j] = matrix[j][i];
//		return rvalue;
//	}
//	
//	private static void flip(int[][] matrix, int flag) {
//		int m = matrix.length, n = matrix[0].length;
//		if (flag == 1) {	//clock wise
//			for (int i = 0; i < m; i++)
//				for (int j = 0; j < n / 2; j++) {
//					matrix[i][j] ^= matrix[i][n-j-1];
//					matrix[i][n-j-1] ^= matrix[i][j];
//					matrix[i][j] ^= matrix[i][n-j-1];
//				}
//		}
//		else {
//			for (int i = 0; i < m / 2; i++)
//				for (int j = 0; j < n; j++) {
//					matrix[i][j] ^= matrix[m-i-1][j];
//					matrix[m-i-1][j] ^= matrix[i][j];
//					matrix[i][j] ^= matrix[m-i-1][j];
//				}
//		}
//	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8},{9, 10, 11, 12},{13, 14, 15, 16}};
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		int[][] res = Solution(matrix, 1);
		
		System.out.println();
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[0].length; j++) {
				System.out.print(res[i][j] + " ");
			}
			System.out.println();
		}
	}

}
