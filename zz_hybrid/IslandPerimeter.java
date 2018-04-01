package LeetCode;

public class IslandPerimeter {
	public int islandPerimeter(int[][] grid) {
		if (grid == null || grid.length == 0) return 0;
		int count = 0;
		int total = 0;
		int row = grid.length;
		int column = grid[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (grid[i][j] == 1) {
					total++;
					if (i > 0 && grid[i - 1][j] == 1) {
						count++;
					}
					if (i + 1 < row && grid[i + 1][j] == 1) {
						count++;
					}
					if (j > 0 && grid[i][j - 1] == 1) {
						count++;
					} 
					if (j + 1 < column && grid[i][j + 1] == 1) {
						count++;
					}
				}
			}
		}
		return total * 4 - count;
	}
	public static void main(String[] args) {
		IslandPerimeter test = new IslandPerimeter();
		int[][] grid = {
				{0,1,0,0},
				{1,1,1,0},
				{0,1,0,0},
				{1,1,0,0}};
		int res = test.islandPerimeter(grid);
		System.out.print(res);
	}
}
