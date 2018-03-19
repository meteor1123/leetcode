/*
	Cut Off Trees for Golf Event

	You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:

	0 represents the obstacle can't be reached.
	1 represents the ground can be walked through.
	The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.
	You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first. And after cutting, the original place has the tree will become a grass (value 1).

	You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. If you can't cut off all the trees, output -1 in that situation.

	You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.

	Example 1:
	Input: 
	[
	 [1,2,3],
	 [0,0,4],
	 [7,6,5]
	]
	Output: 6
	Example 2:
	Input: 
	[
	 [1,2,3],
	 [0,0,0],
	 [7,6,5]
	]
	Output: -1
	Example 3:
	Input: 
	[
	 [2,3,4],
	 [0,0,5],
	 [8,7,6]
	]
	Output: 6
	Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.
	Hint: size of the given matrix will not exceed 50x50.
*/

/*
	Solution: 
		1. 遍历l借助PriorityQueue 存储位置信息 -> int[3] {row, col, value}, 最小堆
		2. 依次从最小的节点开始做BFS，一旦找到最小的tree就加入队列， 计算每一层的step
*/

class Solution {
    static int[] shift = {0, -1, 0, 1, 0};
    public int cutOffTree(List<List<Integer>> forest) {
        if (forest == null || forest.size() == 0)
            return 0;
        int m = forest.size();
        int n = forest.get(0).size();
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (forest.get(i).get(j) > 1) {
                    pq.offer(new int[] {i, j, forest.get(i).get(j)});
                }
            }
        }
        
        int[] start = new int[2];
        int res = 0;
        while (!pq.isEmpty()) {
            int[] tree = pq.poll();
            
            int step = minStep(forest, start, tree, m, n);
            
            if (step < 0)
                return -1;
            res += step;
            
            start[0] = tree[0];
            start[1] = tree[1];
        }
        return res;
    }
    
    private int minStep(List<List<Integer>> forest, int[] start, int[] tree, int m, int n) {
        int step = 0;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList();
        queue.add(start);
        visited[start[0]][start[1]] = true;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                if (cur[0] == tree[0] && cur[1] == tree[1])
                    return step;
                
                for (int j = 0; j < shift.length - 1; j++) {
                    int x = cur[0] + shift[j];
                    int y = cur[1] + shift[j + 1];
                    if (x < 0 || y < 0 || x >= m || y >= n || forest.get(x).get(y) == 0 || visited[x][y])
                        continue;
                    queue.add(new int[]{x, y});
                    visited[x][y] = true;
                }
            }
            step++;
        }
        return -1;
    }
}