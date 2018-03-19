/*
	Shortest Distance from All Buildings
	You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You are given a 2D grid of values 0, 1 or 2, where:

	Each 0 marks an empty land which you can pass by freely.
	Each 1 marks a building which you cannot pass through.
	Each 2 marks an obstacle which you cannot pass through.
	The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

	For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):

	1 - 0 - 2 - 0 - 1
	|   |   |   |   |
	0 - 0 - 0 - 0 - 0
	|   |   |   |   |
	0 - 0 - 1 - 0 - 0
	The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.

	Note:
	There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
*/

/*
	Solution: 1. 设两个数组 reach 用来记录这个坐标已经被多少个坐标值为1进行了多少次bfs
						  dist 用来记录这个坐标对已经遍历过的坐标值为1的点的距离和，因为bfs 所以一定是这个点到所有1点的最近距离

			  2. 对所有坐标值为1的点进行bfs，记录所有0点对这个点的距离.
			  3. 遍历所有坐标值为0的点的reach 和dist值，找reach等于所有1的数量，并且dist的值

*/
public class Solution {
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        final int[] shift = new int[]{0, 1, 0, -1, 0};
        int m = grid.length;
        int n = grid[0].length;
        int[][] dist = new int[m][n];
        int[][] reach = new int[m][n];
        int buildNum = 0;//记录1的个数

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    buildNum++;//发现1 ，数量++
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});//从这个building开始做bfs
                    boolean[][] visited = new boolean[m][n];//记录遍历过的点避免重复
                    int level = 1; //building附近的点level为1，再外层再+1.
                    while (!queue.isEmpty()) {
                        int size = queue.size();
                        //只遍历
                        for (int q = 0; q < size; q++) {
                            int[] point = queue.poll();
                            for (int k = 0; k < 4; k++) {
                                int nextRow = point[0] + shift[k];
                                int nextCol = point[1] + shift[k + 1];
                                if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n && !visited[nextRow][nextCol] && grid[nextRow][nextCol] == 0) {
                                    queue.offer(new int[]{nextRow, nextCol});
                                    dist[nextRow][nextCol] += level;//累加对所有点的距离
                                    reach[nextRow][nextCol]++;//grid[nextRow][nextCol] 这个点目前遇到多少1
                                    visited[nextRow][nextCol] = true;
                                }
                            }
                        }
                        level++;
                    }
                }
            }
        }
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && reach[i][j] == buildNum) {
                    minDist = Math.min(minDist, dist[i][j]);
                }
            }
        }
        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }
}