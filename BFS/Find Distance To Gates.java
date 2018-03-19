package com.alg1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class findDistanceToGates {
	private int row;
	private int col;
	private enum BOLCK {G, W, O};
	public int[][] findGates(String[][] maze) {
		if (maze == null || maze.length == 0 || maze[0].length == 0) {
			return null ;
		}
		int m = maze.length;
		int n = maze[0].length;
		
		boolean[][] visited = new boolean[m][n];
//		Arrays.fill(visited, false);
		int[][] sol = new int[m][n];
//		Arrays.fill(sol, 0);
		Queue<Queue<Position>> queue = new LinkedList<>();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (maze[i][j].equals("G")) {
					LinkedList<Position> tempList = new LinkedList<>();
					tempList.add(new Position(i, j));
	 				queue.add(tempList); 
	 				sol[i][j] = 0;
	 				visited[i][j] = true;
				}
				
			}
		}
		boolean[][] visited1 = Arrays.copyOf(visited,visited.length);
//		while (!queue.isEmpty()) {
			Queue<Position> bfs = queue.poll();
//			boolean[][] visited = Arrays.copyOf(visited1,visited1.length);
			while (!bfs.isEmpty()) {
				Position p = bfs.poll();
				//bfs : up
				if (isValid(maze, p.x - 1, p.y, visited)) {
					bfs.offer(new Position(p.x - 1, p.y));
					if (sol[p.x - 1][p.y] == 0) {
						sol[p.x - 1][p.y] = sol[p.x][p.y] + 1;
					} else {
						sol[p.x - 1][p.y] = Math.min(sol[p.x][p.y] + 1, sol[p.x - 1][p.y]);
					}
					
					visited[p.x - 1][p.y] = true;
				}
				//down
				if (isValid(maze, p.x + 1, p.y, visited)) {
					bfs.offer(new Position(p.x + 1, p.y));
					if (sol[p.x + 1][p.y] == 0) {
						sol[p.x + 1][p.y] = sol[p.x][p.y] + 1;
					} else {
						sol[p.x + 1][p.y] = Math.min(sol[p.x][p.y] + 1, sol[p.x + 1][p.y]);
					}
					//sol[p.x + 1][p.y] = sol[p.x][p.y] + 1;
					visited[p.x + 1][p.y] = true;
				}
				//left
				if (isValid(maze, p.x, p.y - 1, visited)) {
					bfs.offer(new Position(p.x, p.y - 1));
					if (sol[p.x][p.y - 1] == 0) {
						sol[p.x][p.y - 1] = sol[p.x][p.y] + 1;
					} else {
						sol[p.x][p.y - 1] = Math.min(sol[p.x][p.y] + 1, sol[p.x][p.y - 1]);
					}
					//sol[p.x][p.y - 1] = sol[p.x][p.y] + 1;
					visited[p.x][p.y - 1] = true;
				}
				//right
				if (isValid(maze, p.x, p.y + 1, visited)) {
					bfs.offer(new Position(p.x, p.y + 1));
					if (sol[p.x][p.y + 1] == 0) {
						sol[p.x][p.y + 1] = sol[p.x][p.y] + 1;
					} else {
						sol[p.x][p.y + 1] = Math.min(sol[p.x][p.y] + 1, sol[p.x][p.y + 1]);
					}
					//sol[p.x][p.y + 1] = sol[p.x][p.y] + 1;
					visited[p.x][p.y + 1] = true;
				}	
			}	
			
			bfs = queue.poll();
			while (!bfs.isEmpty()) {
				Position p = bfs.poll();
				//bfs : up
				if (isValid(maze, p.x - 1, p.y, visited1)) {
					bfs.offer(new Position(p.x - 1, p.y));
					if (sol[p.x - 1][p.y] == 0) {
						sol[p.x - 1][p.y] = sol[p.x][p.y] + 1;
					} else {
						sol[p.x - 1][p.y] = Math.min(sol[p.x][p.y] + 1, sol[p.x - 1][p.y]);
					}
					
					visited1[p.x - 1][p.y] = true;
				}
				//down
				if (isValid(maze, p.x + 1, p.y, visited1)) {
					bfs.offer(new Position(p.x + 1, p.y));
					if (sol[p.x + 1][p.y] == 0) {
						sol[p.x + 1][p.y] = sol[p.x][p.y] + 1;
					} else {
						sol[p.x + 1][p.y] = Math.min(sol[p.x][p.y] + 1, sol[p.x + 1][p.y]);
					}
					//sol[p.x + 1][p.y] = sol[p.x][p.y] + 1;
					visited1[p.x + 1][p.y] = true;
				}
				//left
				if (isValid(maze, p.x, p.y - 1, visited1)) {
					bfs.offer(new Position(p.x, p.y - 1));
					if (sol[p.x][p.y - 1] == 0) {
						sol[p.x][p.y - 1] = sol[p.x][p.y] + 1;
					} else {
						sol[p.x][p.y - 1] = Math.min(sol[p.x][p.y] + 1, sol[p.x][p.y - 1]);
					}
					//sol[p.x][p.y - 1] = sol[p.x][p.y] + 1;
					visited1[p.x][p.y - 1] = true;
				}
				//right
				if (isValid(maze, p.x, p.y + 1, visited1)) {
					bfs.offer(new Position(p.x, p.y + 1));
					if (sol[p.x][p.y + 1] == 0) {
						sol[p.x][p.y + 1] = sol[p.x][p.y] + 1;
					} else {
						sol[p.x][p.y + 1] = Math.min(sol[p.x][p.y] + 1, sol[p.x][p.y + 1]);
					}
					//sol[p.x][p.y + 1] = sol[p.x][p.y] + 1;
					visited1[p.x][p.y + 1] = true;
				}	
			}
//		}
		
		return sol;
	}
	public boolean isValid(String[][] maze, int i, int j, boolean[][] visited) {
		int m = maze.length;
		int n = maze[0].length;
		if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || maze[i][j] == "W") {
			return false;
		}
		return true;
	}
	
	public class Position {
		public int x;
		public int y;
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		findDistanceToGates test = new findDistanceToGates();
		String[][] maze = {{"G","W","G",""},
					  	   {"","","","W"},
					  	   {"","W","","W"},
					  	   {"","W","",""}};
		int[][] sol = test.findGates(maze);
		for (int i = 0; i < sol.length; i++) {
			for (int j = 0; j < sol[0].length; j++) {
				System.out.print(sol[i][j]);
				if (j == sol[0].length - 1) {
					System.out.println("");
				}
			}
		}
	}

}
