package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {
	public int numIslands(char[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		int res = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '1') {
					bfs(grid, i, j);
					res++;
				}
			}
		}
		return res;
	}
	
	private void bfs(char[][] grid, int row, int column) {
		Queue<Element> q = new LinkedList<>();
		int m = grid.length;
		int n = grid[0].length;
		Element element = new Element(row, column);
		q.offer(element);
		grid[element.row][element.column] = '0';
		while (!q.isEmpty()) {
			Element cur = q.poll();
			if (cur.row + 1 < m && grid[cur.row + 1][cur.column] == '1') {
				q.offer(new Element(cur.row + 1, cur.column));
				grid[cur.row + 1][cur.column] = '0';
			}
			if (cur.column + 1 < n && grid[cur.row][cur.column + 1] == '1') {
				q.offer(new Element(cur.row, cur.column + 1));
				grid[cur.row][cur.column + 1] = '0';
			}
			if (cur.row > 0 && grid[cur.row - 1][cur.column] == '1') {
				q.offer(new Element(cur.row - 1, cur.column));
				grid[cur.row - 1][cur.column] = '0';
			}
			if (cur.column > 0 && grid[cur.row][cur.column - 1] == '1') {
				q.offer(new Element(cur.row, cur.column - 1));
				grid[cur.row][cur.column - 1] = '0';
			}
		}
	}
	
	class Element {
		int row;
		int column;
		public Element(int row, int column) {
			this.row = row;
			this.column = column;
		}
	}
	public static void main(String[] args) {
		NumberOfIslands test = new NumberOfIslands();
		char[][] grid = {
				{'1','1','0','0','0'},
				{'1','1','0','0','0'},
				{'0','0','1','0','0'},
				{'0','0','0','1','1'},
				{'0','1','0','0','1'}};
		int res = test.numIslands(grid);
		System.out.print(res);
	}
}
