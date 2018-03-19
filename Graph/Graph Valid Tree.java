/*
	Graph Valid Tree
	Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

	For example:

	Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

	Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

	Hint:

		Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
		According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
		Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
*/



/*
	http://www.geeksforgeeks.org/union-find/
	Find: Determine which subset a particular element is in. This can be used for determining if two elements are in the same subset.
	Union: Join two subsets into a single subset.

	Solution1 : Union Find, Union the point set
	1)	Initially, all slots of parent array are initialized to -1 (means there is only one item in every subset).
		0   1   2
		-1 -1  -1
	2)  Now process all edges one by one.

	    Edge 0-1: Find the subsets in which vertices 0 and 1 are. Since they are in different subsets, we take the union of them. 
	    For taking the union, either make node 0 as parent of node 1 or vice-versa.
	    0   1   2    <----- 1 is made parent of 0 (1 is now representative of subset {0, 1})
		1  -1  -1

		Edge 1-2: 1 is in subset 1 and 2 is in subset 2. So, take union.
		0   1   2    <----- 2 is made parent of 1 (2 is now representative of subset {0, 1, 2})
		1   2  -1

	3)  Edge 0-2: 0 is in subset 2 and 2 is also in subset 2. Hence, including this edge forms a cycle.
		How subset of 0 is same as 2?
		0->1->2 // 1 is parent of 0 and 2 is parent of 1
	
	// A utility function to find the subset of an element i
	int find(int parent[], int i){
	    if (parent[i] == -1)
	        return i;
	    return find(parent, parent[i]);
	}
 
	// A utility function to do union of two subsets 
	void Union(int parent[], int x, int y){
		int xset = find(parent, x);
	    int yset = find(parent, y);
	    parent[xset] = yset;
	}


*/

//Solution1: Union Find
public class Solution {
    public boolean validTree(int n, int[][] edges) {
        int[] root = new int[n];
        Arrays.fill(root, -1);
        for (int i = 0; i < edges.length; i++) {
            int x = find(root, edges[i][0]);
            int y = find(root, edges[i][1]);
            
            if (x == y) {
                return false;
            }
            
            //Union
            root[x] = y;
        }
        
        return edges.length == n - 1;
    }
    
    //Find
    public int find(int[] root, int i) {
        if (root[i] == -1) {
            return i;
        }
        root[i] = find(root, root[i]);
        return root[i];
    }

    public int find(int[] root, int i) {
        while (root[i] != -1)
            i = root[i];
        return i;
    }
}

//Solution2: BFS，跟dfs的区别在于用queue，并且遍历完node的所有邻接点才继续下一层
public class Solution {
	public boolean validTree(int n, int[][] edges) {
        if (n < 1) {
            return false;
        }
        List<Set<Integer>> graph = new ArrayList<>();
        //初始化每个点的set，构建邻接表
        for (int i = 0; i < n; i++) {
            graph.add(new HashSet<Integer>());
        }
        //根据边的关系构建邻接表
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        //标记每个顶点是否访问过
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (visited[node] == true) {
                return false;
            }
            visited[node] = true;
            
            for (int neighbor : graph.get(node)) {
                queue.offer(neighbor);
                graph.get(neighbor).remove(node);
            }
        }
        //最后要判断所有的点是否都访问过，没访问过 代表孤岛点，则该图没有完全连通，树的定义的 连通无环图！
        for (boolean connected : visited) {
            if (connected == false) {
                return false;
            }
        }
        
        return true;
        
    }
}


//Solution3 : DFS, 跟bfs的区别在于这里用stack，新进来的结点最先访问，因此会一直搜寻到最深，之后根据栈的性质再回朔，因此是深度遍历
public class Solution {
	// DFS, using stack
    private boolean validDFS(int n, int[][] edges) {
        // build the graph using adjacent list
        List<Set<Integer>> graph = new ArrayList<Set<Integer>>();
        for(int i = 0; i < n; i++)
            graph.add(new HashSet<Integer>());

        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // no cycle
        boolean[] visited = new boolean[n];
        Deque<Integer> stack = new ArrayDeque<Integer>();
        stack.push(0);
        while(!stack.isEmpty()) {
            int node = stack.pop();
            if(visited[node])
                return false;
            visited[node] = true;
            for(int neighbor : graph.get(node)) {
                stack.push(neighbor);
                graph.get(neighbor).remove(node);
            }
        }

        // fully connected
        for(boolean result : visited) {
            if(!result)
                return false;
        }

        return true;
    }
}


//Solution4: prefer
public class Solution {
    public boolean validTree(int n, int[][] edges) {
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<Integer>());
        }
        
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.offer(0);
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (visited[node] == true) {
                return false;
            }
            visited[node] = true;
            for (int neighbor : graph.get(node)) {
                graph.get(neighbor).remove(node);
                queue.offer(neighbor);
            }
        }
        
        for (boolean visit : visited) {
            if (visit == false) {
                return false;
            }
        }
        return true;
    }
}