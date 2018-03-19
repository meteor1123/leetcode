public class Solution {
	public static int[][] solution (int[][] maze) {
		int m = maze.length;
		int n = msze[0].length;
		boolean[][] visited = new boolean[][];
		int[][] sol = new int[m][n];
		Queue<Queue<Position>> queue = new Queue<Queue<Position>>();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (maze[i][j] == 1) {
					Queue<Position> gate = Queue<Position>();
					gate.add(new Position(i, j));
					queue.add(gate);
					sol[i][j] = 0;
					visited[i][j] = true;
				}
			}
		}

		while (!queue.isEmpty()) {
			Queue<Position> gate = queue.poll();
			Position p = gate.poll();
			int i = p.x;
			int j = p.y;
			//to left
			if (isValid(maze, i, j - 1, visited)) {
				gate.add(new Position(i, j - 1));
				sol[i][j - 1] = sol[i][j] + 1;
				visited[i][j - 1] = true;

			}
			//to right
			if (isValid(maze, i, j + 1, visited)) {
				gate.add(new Position(i, j + 1));
				sol[i][j + 1] = sol[i][j] + 1;
				visited[i][j + 1] = true;
			}
			//to top
			if (isValid(maze, i - 1, j, visited)) {
				gate.add(new Position(i - 1, j));
				sol[i - 1][j] = sol[i][j] +1;
				visited[i - 1][j] = true;
			}
			//to bottom
			if (isValid(maze, i + 1, j, visited)) {
				gate.add(new Position(i + 1, j));
				sol[i +1][j] = sol[i][j] + 1;
				visited[i + 1][j] = true;
			}
			if (!gate.isEmpty()) {
				queue.add(gate);
			}
		}
		return sol;
	}


	public static class Position {
		public int x;
		public int y;
		public Position (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public boolean isValid(int[][] maze, int i , int j, boolean[][] visited) {
		int m = maze.length;
		int n = maze[0].length;
		if (i < 0 || i >= m || j < 0 || j >= n || maze[i][j] = -1 || visited[i][j]) {
			return false;
		}
		return true;
	}
}