package OA2;

import java.util.LinkedList;
import java.util.Queue;

public class FindPathIn2Dmatrix {
//	private final static int[] dx = {1, -1, 0, 0};
//	private final static int[] dy = {0, 0, 1, -1};
//	public static int Solution(int[][] matrix) {
//		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
//			return 0;
//		}
//		if (matrix[0][0] == 9) {
//			return 1;
//		}
//		int m = matrix.length;
//		int n = matrix[0].length;
//		Queue<int[]> queue = new LinkedList<int[]>();
//		queue.offer(new int[]{0, 0});
//		matrix[0][0] = 1;
//		while (!queue.isEmpty()) {
//			int[] cur = queue.poll();
//			for (int i = 0; i < 4; i++) {
//				int[] next = {cur[0] + dx[i], cur[1] + dy[i]};
//				if (next[0] >= 0 && next[0] < m && next[1] >= 0 && next[1] < n) {
//					if (matrix[next[0]][next[1]] == 9) {
//						return 1;
//					} else if (matrix[next[0]][next[1]] == 0) {
//						queue.offer(next);
//						matrix[next[0]][next[1]] = 1;
//					}
//				}
//			}
//		}
//		return 0;
//	}
	//DFS
	public static boolean findPath(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return false;
		}
		if (matrix[0][0] == 1) {
			return false;
		}
		return dfs(matrix, 0, 0);
	}
	public static boolean dfs(int[][] matrix, int x, int y) {
		if (x < 0 || x > matrix.length - 1 || y < 0 || y > matrix[0].length - 1 || matrix[x][y] == 1) { 
			return false;
		}
		if (matrix[x][y] == 9) {
			return true;
		}
		matrix[x][y] = 1;
		return dfs(matrix, x + 1, y) || dfs(matrix, x - 1, y) || dfs(matrix, x, y + 1) || dfs(matrix, x, y - 1);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] matrix = {{0, 1, 1, 1}, {0, 1, 1, 1}, {0, 1, 0, 0}, {0, 0, 0, 9}};
		System.out.println(findPath(matrix));
	}

}
