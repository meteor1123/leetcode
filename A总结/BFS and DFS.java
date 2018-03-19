/*
	BFS && DFS
*/


1. Word Problem
		1.1 Word Break





2. BFS && DFS Path, Matrix Problem
		1.1 Shortest Distance From All Buildings
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
			Solution: 
						1. 设两个数组 
									reach 用来记录这个坐标已经被多少个坐标值为1进行了多少次bfs
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
		1.2 Surrounded Regions
		/*
			Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

			A region is captured by flipping all 'O's into 'X's in that surrounded region.

			For example,
			X X X X
			X O O X
			X X O X
			X O X X
			After running your function, the board should be:

			X X X X
			X X X X
			X X X X
			X O X X
		*/
		public class Solution {
		    public void solve(char[][] board) {
		        int[] shift = {0, 1, 0, -1, 0};
		        if (board == null || board.length == 0) {
		            return;
		        }
		        int m = board.length;
		        int n = board[0].length;
		        for (int i = 0; i < n; i++) {
		            fill(board, 0, i, shift);
		            fill(board, m - 1, i, shift);
		        }
		        
		        for (int i = 0; i < m; i++) {
		            fill(board, i, 0, shift);
		            fill(board, i, n - 1, shift);
		        }
		        
		        for (int i = 0; i < m; i++) {
		            for (int j = 0; j < n; j++) {
		                if (board[i][j] == 'O') {
		                    board[i][j] = 'X';
		                } else if (board[i][j] == '*') {
		                    board[i][j] = 'O';
		                }
		            }
		        }
		    }
		    public void fill(char[][] board, int i, int j, int[] shift) {
		        if (board[i][j] == 'X') {
		            return;
		        }
		        board[i][j] = '*';
		        Queue<Integer> queue = new LinkedList<>();
		        int m = board.length;
		        int n = board[0].length;
		        int index = i * n + j;
		        queue.offer(index);
		        while (!queue.isEmpty()) {
		            int code = queue.poll();
		            int x = code / n;
		            int y = code % n;
		            for (int k = 0; k < 4; k++) {
		                int nextRow = x + shift[k];
		                int nextCol = y + shift[k + 1];
		                if (nextRow >= 0 && nextCol >= 0 && nextRow < m && nextCol < n && board[nextRow][nextCol] == 'O') {
		                    board[nextRow][nextCol] = '*';
		                    queue.offer(nextRow * n + nextCol);
		                }
		            }
		        }
		    }
		}
		1.4 Number Of Islands
			/*
				Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
				An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
				You may assume all four edges of the grid are all surrounded by water.
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
			//Solution1:
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
			//Solution2: dfs
			public class Solution {
				 public int numIslands(char[][] grid) {
			        if (grid == null || grid.length == 0) {
			            return 0;
			        }
			        int m = grid.length;
			        int n = grid[0].length;
			        int count = 0;
			        boolean[][] visited = new boolean[m][n];
			        for (int i = 0; i < m; i++) {
			            for (int j = 0; j < n; j++) {
			                if (grid[i][j] == '1') {
			                    dfs(grid, visited, i, j);
			                    count++;
			                }
			            }
			        }
			        return count;
			    }	    
			    public void dfs(char[][] grid, boolean[][] visited, int row, int col) {
			        if (row < 0 || col < 0 || row > grid.length - 1 || col > grid[0].length - 1 || visited[row][col] || grid[row][col] != '1') {
			            return ;
			        }
			        grid[row][col] = '2';
			        visited[row][col] = true;
			        dfs(grid, visited, row + 1, col);
			        dfs(grid, visited, row - 1, col);
			        dfs(grid, visited, row, col + 1);
			        dfs(grid, visited, row, col - 1);
			    }				
			}
		1.5 Walls And Gates
			/*
				You are given a m x n 2D grid initialized with these three possible values.
				-1 - A wall or an obstacle.
				0 - A gate.
				INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
				Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

				For example, given the 2D grid:
				INF  -1  0  INF
				INF INF INF  -1
				INF  -1 INF  -1
				  0  -1 INF INF
				After running your function, the 2D grid should be:
				  3  -1   0   1
				  2   2   1  -1
				  1  -1   2  -1
				  0  -1   3   4
			*/
			//Key Point: 先把所有的门（0）点进queue， 对0的四周进行bfs，只有INF点才算距离，并进栈
			public class Solution {
			    public final int[] shift = {0, 1, 0, -1, 0};
			    public void wallsAndGates(int[][] rooms) {
			        if (rooms == null || rooms.length == 0) {
			            return;
			        }
			        int m = rooms.length;
			        int n = rooms[0].length;
			        Queue<Integer> queue = new LinkedList<>();
			        for (int i = 0; i < m; i++) {
			            for (int j = 0; j < n; j++) {
			                if (rooms[i][j] == 0) {
			                    queue.offer(i * n + j);
			                }
			            }
			        }
			        
			        while (!queue.isEmpty()) {
			            int index = queue.poll();
			            int x = index / n;
			            int y = index % n;
			            int level = rooms[x][y];
			            for (int i = 0; i < 4; i++) {
			                int row = x + shift[i];
			                int col = y + shift[i + 1];
			                if (row < 0 || col < 0 || row > m - 1 || col > n - 1 || rooms[row][col] != Integer.MAX_VALUE) {
			                    continue;
			                }
			                rooms[row][col] = level + 1;
			                queue.offer(row * n + col);
			            }
			        }
			    }
			}
		1.5 Find Islands And Lakes
		/*
			湖的定义就是 group of 0s entirely surrounded by a single island。也就是说两个不同island包围的0不算一个湖，
			比如下面这个只有1个湖，就是两个连续0哪个是湖，边上那个被4个1包围的0不算湖

			Example1: // 1 1 1 0 1 1 
					  // 1 0 0 1 0 1
					  // 1 1 1 1 1 0 

			Example2:
					  // 1 1 1 1 1 0
					  // 1 0 1 0 0 1       i)  islands =>  2,  1是岛
					  // 1 0 1 0 0 1       ii) lakes =>  1，   0是湖
					  // 1 1 0 1 1 1

			// write a library/class that provides two functions / methods
			// i) count the number of 'islands' that the matrix has. 
			// ii) count the number of 'lakes' that the matrix has i.e. connected clump of zeros that is entirely surrounded by a single island.
		*/
		public class Solution {
			public static int findIslands(int[][] grid) {
				if (grid == null || grid.length == 0) {
					return 0;
				}
				int m = grid.length;
				int n = grid[0].length;
				int count = 0;
				boolean[][] visited = new boolean[m][n];
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						if (grid[i][j] == 1) {
							count++;
							dfs1(grid, visited, i, j, count + 1);
						}
					}
				}
				return count;
			}	
			public static int findLakes(int[][] grid) {
				findIslands(grid);
				int m = grid.length;
				int n = grid[0].length;
				int count = 0;
				for (int i = 1; i < m - 1; i++) {
					for (int j = 1; j < n - 1; j++) {
						if (grid[i][j] == 0) {
							int[] key = new int[1];
							key[0] = -1;
							if (dfs2(grid, i, j, key)) {
								count++;
							}
						}
					}
				}
				
				for (int i = 1; i < m - 1; i++) {
					for (int j = 1; j < n - 1; j++) {
						if (grid[i][j] == -1) {
							grid[i][j] = 0;
						}
					}
				}
				reset(grid);
				
				return count;	
			}
			
			public static void reset(int[][] grid) {
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						grid[i][j] = grid[i][j] == 0 ? 0 : 1;
					}
				}
			}
			
			public static boolean dfs2(int[][] grid, int i, int j, int[] key) {
				//假如是大于0，就是岛的点，
				if (grid[i][j] > 0) {
					//第一次碰上岛的点时
					if (key[0] == -1) {
						key[0] = grid[i][j];//取得岛的编号并返回true
						return true;
					} else if (key[0] == grid[i][j]) {
						//如果岛的编号和记录的编号相同则返回true
						return true;
					} else {
						//编号不同，返回false
						return false;
					}
				}
				
				if (grid[i][j] == -1) {
					//-1就是遇到邻接的湖
					return true;
				}
				grid[i][j] = -1;//每次进来就将0赋值为-1，
				if (i == 0 || j == 0 || i == grid.length - 1 || j == grid[0].length - 1) {//corn case
					return false;
				}
				return dfs2(grid, i - 1, j, key) && dfs2(grid, i + 1, j, key) && dfs2(grid, i, j + 1, key) &&  dfs2(grid, i, j - 1, key);
			}
			
			public static void dfs1(int[][] grid, boolean[][] visited, int i, int j, int index) {
				if (i < 0 || j < 0 || i > grid.length - 1 || j > grid[0].length - 1 || visited[i][j] || grid[i][j] != 1) {
					return;
				}
				grid[i][j] = index;
				visited[i][j] = true;
				dfs1(grid, visited, i + 1, j, index);
				dfs1(grid, visited, i - 1, j, index);
				dfs1(grid, visited, i, j + 1, index);
				dfs1(grid, visited, i, j - 1, index);
			}
		}

		1.6 Smallest Rectangle Enclosing Black Pixels
			/*
				An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.
				For example, given the following image:
				[
				  "0010",
				  "0110",
				  "0100"
				]
				and x = 0, y = 2,
				Return 6.
			*/
			public class Solution {
				private int minX = Integer.MAX_VALUE, 
                 			minY = Integer.MAX_VALUE, 
                  			maxX = 0, 
                  			maxY = 0;
			    public int minArea(char[][] image, int x, int y) {
			        if(image == null || image.length == 0 || image[0].length == 0) {
			        	return 0;
			        }
			        dfs(image, x, y);
			        return(maxX - minX + 1) * (maxY - minY + 1);
			    }
			    private void dfs(char[][] image, int x, int y){
			        int m = image.length, n = image[0].length;
			        if(x < 0 || y < 0 || x >= m || y >= n || image[x][y] == '0') {
			        	return;
			        }
			        image[x][y] = '0';
			        minX = Math.min(minX, x);
			        maxX = Math.max(maxX, x);
			        minY = Math.min(minY, y);
			        maxY = Math.max(maxY, y);
			        dfs(image, x + 1, y);
			        dfs(image, x - 1, y);
			        dfs(image, x, y - 1);
			        dfs(image, x, y + 1);
			    }
			}
			
		1.7 Longest Increasing Path In A Matrix
			/*
				Given an integer matrix, find the length of the longest increasing path.

				From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

				Example 1:

				nums = [
				  [9,9,4],
				  [6,6,8],
				  [2,1,1]
				]
				Return 4
				The longest increasing path is [1, 2, 6, 9].

				Example 2:

				nums = [
				  [3,4,5],
				  [3,2,6],
				  [2,2,1]
				]
				Return 4
				The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
			 */

			public class Solution {
			    int[] shift = {0, 1, 0, -1, 0};
			    public int longestIncreasingPath(int[][] matrix) {
			        if (matrix == null || matrix.length == 0) {
			            return 0;
			        }
			        int m = matrix.length;
			        int n = matrix[0].length;
			        
			        int[][] state = new int[m][n];
			        int res = 0;
			        for (int i = 0; i < m; i++) {
			            for (int j = 0; j < n; j++) {
			                res = Math.max(res, dfs(matrix, i, j, state));
			            }
			        }
			        return res;
			    }
			    
			    public int dfs(int[][] matrix, int x, int y, int[][] state) {
			        if (state[x][y] > 0) {
			            return state[x][y];
			        }
			        int max = 0;
			        for (int i = 0; i < 4; i++) {
			            int nearX = x + shift[i];
			            int nearY = y + shift[i + 1];
			            if (nearX >= 0 && nearX < matrix.length && nearY >= 0 && nearY < matrix[0].length && matrix[nearX][nearY] > matrix[x][y]) {
			                max = Math.max(max, dfs(matrix, nearX, nearY, state));
			            }
			        }
			        state[x][y] = 1 + max;
			        return state[x][y];
			    }
			}