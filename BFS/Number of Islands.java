/*
	Number of Islands
	Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

	Example 1:
	11110
	11010
	11000
	00000
	Answer: 1


	Example 2:
	11000
	11000
	00100
	00011
	Answer: 3
*/

/*
    Time: O(m * n)
    Space: O(1)
	Solution: once we find the value equals '1' we make all of the adjacent point to '2',
	

*/
//DFS
public class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    ans++;
                    dfs(grid, i, j);
                }
            }
        }
        return ans;
    }
    
    public void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') {
            return ;
        } 
        grid[i][j] = '2';
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }
}


//BFS
public class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        boolean[][] visited = new boolean[m][n];
        final int[] shift = new int[] {0, 1, 0, -1, 0};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});
                    while (!queue.isEmpty()) {
                        int size = queue.size();
                        for (int q = 0; q < size; q++) {
                            int[] point = queue.poll();
                            for (int k = 0; k < 4; k++) {
                                int nextRow = point[0] + shift[k];
                                int nextCol = point[1] + shift[k + 1];
                                if (nextRow >= 0 && nextCol >= 0 && nextRow < m && nextCol < n && grid[nextRow][nextCol] == '1' && !visited[nextRow][nextCol]) {
                                    queue.offer(new int[]{nextRow, nextCol});
                                    grid[nextRow][nextCol] = '2';
                                    visited[nextRow][nextCol] = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return count;
    }
}
