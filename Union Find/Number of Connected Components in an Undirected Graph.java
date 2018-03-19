/*
	Number of Connected Components in an Undirected Graph
	Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

	Example 1:
	     0          3
	     |          |
	     1 --- 2    4
	Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

	Example 2:
	     0           4
	     |           |
	     1 --- 2 --- 3
	Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.

	Note:
	You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
*/

public class Solution {
    public int countComponents(int n, int[][] edges) {
        int[] root = new int[n];
        // Arrays.fill(root, -1);
        for (int i = 0; i < n; i++) {
            root[i] = i;
        }
        for (int i = 0; i < edges.length; i++) {
            int x = find(root, edges[i][0]);
            int rootY = find(root, edges[i][1]);
            if (x != rootY) {
                root[x] = rootY;
            }
        }
        int count = 0;
        for (int i = 0; i < root.length; i++) {
            if (root[i] == i) {
                count++;
            }
        }
        return count;
    }
    
    public int find(int[] root, int i) {
        if (root[i] == i) {
            return i;
        }
        root[i] = find(root, root[i]); //Path compression
        return root[i];
    }
}

public class Solution {
    public int findCircleNum(int[][] M) {
        int n = M.length;
        int count = 0;
        int[] root = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (M[i][j] == 1) {
                    int x = find(root, i);
                    int y = find(root, j);
                    if (x != y) {
                        root[x] = y;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (root[i] == i) {
                count++;
            }
        }
        return count;
    }
    
    public int find(int[] root, int i) {
        if (root[i] == i) {
            return i;
        }
        root[i] = find(root, root[i]);
        return root[i];
    }
}
