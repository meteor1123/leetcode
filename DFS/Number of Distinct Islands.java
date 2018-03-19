/*
	Number of Distinct Islands

	Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

	Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.

	Example 1:
	11000
	11000
	00011
	00011
	Given the above grid map, return 1.
	Example 2:
	11011
	10000
	00001
	11011
	Given the above grid map, return 3.

	Notice that:
	11
	1
	and
	 1
	11
	are considered different island shapes, because we do not consider reflection / rotation.
	Note: The length of each dimension in the given grid does not exceed 50.
*/


// idea: 以最左上角的点为base point， 下面遍历到的同一个connect component的点都要 （x - baseX, y - baseY)， 将遍历过的点加入hashset中去重

class Solution {
    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        StringBuilder sb = new StringBuilder();
        HashSet<String> set = new HashSet();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    dfs(grid, visited, sb, i, j, m, n, i, j);
                    set.add(sb.toString());
                    sb = new StringBuilder();
                }
            }
        }
        return set.size();
    }
    
    public void dfs(int[][] grid, boolean[][] visited, StringBuilder sb, int i, int j, int m, int n, int baseX, int baseY) {
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || grid[i][j] != 1) {
            return;
        }
        sb.append(i - baseX).append('-').append(j - baseY); 
        visited[i][j] = true;
        
        dfs(grid, visited, sb, i + 1, j, m, n, x, y);
        dfs(grid, visited, sb, i - 1, j, m, n, x, y);
        dfs(grid, visited, sb, i, j + 1, m, n, x, y);
        dfs(grid, visited, sb, i, j - 1, m, n, x, y);
    }
}