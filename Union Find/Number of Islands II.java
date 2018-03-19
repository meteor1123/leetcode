/*
	Number of Islands II
	A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

	Example:

	Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
	Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

		0 0 0
		0 0 0
		0 0 0
		Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

		1 0 0
		0 0 0   Number of islands = 1
		0 0 0
		Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

		1 1 0
		0 0 0   Number of islands = 1
		0 0 0
		Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

		1 1 0
		0 0 1   Number of islands = 2
		0 0 0
		Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

		1 1 0
		0 0 1   Number of islands = 3
		0 1 0
	We return the result as an array: [1, 1, 2, 3]
*/


//Solution1 
public class Solution {
	//using to check the near point
	int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        if (m <= 0 || n <= 0) {
            return res;
        }
        
        int count = 0;// number of islands
        int[] roots = new int[m * n]; // one island = one tree
        Arrays.fill(roots, -1);
        
        for (int[] p : positions) {
            int root = n * p[0] + p[1];//index the 2D matrix
            roots[root] = root;// add new island
            count++;
            
            for (int[] dir : dirs) {
                int x = p[0] + dir[0];
                int y = p[1] + dir[1];
                int idNear = n * x + y;
                if (x < 0 || y < 0 || x >= m || y >= n || roots[idNear] == -1) {
                    continue;
                }
                int rootIdNear = find(roots, idNear);
                if (root != rootIdNear) {// if neighbor is in another island
                    roots[rootIdNear] = root; // union two islands
                    count--;
                }
            }
            res.add(count);
        }
        return res;
    }
    
    public int find(int[] roots, int id) {
        while (id != roots[id]) {
            id = roots[id];
        }
        return id;
    }
}

//Solution2: better name prefer
public class Solution {
    public final int[] shift = {0, 1, 0 , -1, 0};
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        int[] roots = new int[m * n];
        Arrays.fill(roots, -1);
        int count = 0;
        for (int[] p : positions) {
            int landRootId = p[0] * n + p[1];
            roots[landRootId] = landRootId;
            count++;
            for (int i = 0; i < 4; i++) {
                int row = p[0] + shift[i];
                int col = p[1] + shift[i + 1];
                int nearPos = row * n + col;
                //roots[nearPos] == -1意味着这个位置不是1而是0，所以跳出，因为之前的岛的root肯定不为1
                if (row < 0 || col < 0 || row > m - 1 || col > n - 1 || roots[nearPos] == -1) {
                    continue;
                }
                //如果是个岛，查这个岛的root
                int nearPosRootId = find(roots, nearPos);
                //如果这个点的root和之前的landRootId 不相等，意味着原来不连通的两部分岛，现在联通为一体，所以count--
                if (nearPosRootId != landRootId) {
                    roots[nearPosRootId] = landRootId;
                    count--;
                }
            }
            res.add(count);
        }
        return res;
    }
    
    public int find(int[] root, int id) {
        if (root[id] == id) {
            return id;
        }
        root[id] = find(root, root[id]);
        return root[id];
    }
}