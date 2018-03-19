/*
	Number of Distinct Islands II

	Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

	Count the number of distinct islands. An island is considered to be the same as another if they have the same shape, or have the same shape after rotation (90, 180, or 270 degrees only) or reflection (left/right direction or up/down direction).

	Example 1:
	11000
	10000
	00001
	00011
	Given the above grid map, return 1. 

	Notice that:
	11
	1
	and
	 1
	11
	are considered same island shapes. Because if we make a 180 degrees clockwise rotation on the first island, then two islands will have the same shapes.
	Example 2:
	11100
	10001
	01001
	01110
	Given the above grid map, return 2.

	Here are the two distinct islands:
	111
	1
	and
	1
	1

	Notice that:
	111
	1
	and
	1
	111
	are considered same island shapes. Because if we flip the first array in the up/down direction, then they have the same shapes.
	Note: The length of each dimension in the given grid does not exceed 50.
*/



class Solution {
    int[][] grid;
    boolean[][] visited;
    List<Integer> shape;    
    public int numDistinctIslands2(int[][] grid) {
        this.grid = grid;
        int m = grid.length;
        int n = grid[0].length;
        visited = new boolean[m][n];
        Set<String> shapes = new HashSet();
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                shape = new ArrayList();
                explore(i, j);
                if (!shape.isEmpty()) {
                    shapes.add(canonical(shape));
                }
            }
        }
        
        return shapes.size();
    }
    
    public void explore(int i, int j) {
        if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1 && !visited[i][j]) {
            visited[i][j] = true;
            shape.add(i * grid[0].length + j);
            explore(i + 1, j);
            explore(i - 1, j);
            explore(i, j + 1);
            explore(i, j - 1);
        }
    }
    
    public String canonical(List<Integer> shape) {
        String res = "";
        int lift = grid.length;
        int[] out = new int [shape.size()];
        int[] xs = new int [shape.size()];
        int[] ys = new int [shape.size()];
        
        for (int i = 0; i < 8; i++) {
            int k = 0;
            for (int z : shape) {
                int x = z / grid[0].length;
                int y = z % grid[0].length;
                
                //x y, x -y, -x y, -x -y
                //y x, y -x, -y x, -y -x
                xs[k] = i <= 1? x : i <= 3 ? -x : i <= 5 ? y : -y;
                ys[k++] = i <= 3 ? (i % 2 == 0 ? y : -y) : (i % 2 == 0 ? x : -x);
            }
            
            int mx = xs[0], my = ys[0];
            for (int x : xs) mx = Math.min(mx, x);
            for (int y : ys) my = Math.min(my, y);
            
            for (int j = 0; j < shape.size(); j++) 
                out[j] = (xs[j] - mx) * lift + (ys[j] - my);
            Arrays.sort(out);
            String candidate = Arrays.toString(out);
            if (res.compareTo(candidate) < 0)
                res = candidate;
        }
        return res;
    }
}