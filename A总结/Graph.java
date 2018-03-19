/*
	Graph 

	(1) A tree is an undirected graph in which any two vertices are connected by exactly one path.
	(2) Any connected graph who has n nodes with n-1 edges is a tree.
	(3) The degree of a vertex of a graph is the number of edges incident to the vertex.
	(4) A leaf is a vertex of degree 1. An internal vertex is a vertex of degree at least 2.
	(5) A path graph is a tree with two or more vertices that is not branched at all.
	(6) A tree is called a rooted tree if one vertex has been designated the root.
	(7) The height of a rooted tree is the number of edges on the longest downward path between root and a leaf.
*/

0. Basic Graph Alogirithm
		0.0 Detect Cycle In Directed Graph 
			//Detect the Cycle by using DFS
				public class Solution {
				    public boolean canFinish(int n, int[][] edges) {
				        int[][] graph = new int[n][n];
				        for (int i = 0; i < edges.length; i++) {
				            graph[edges[i][1]][edges[i][0]] = 1;
				        }
				        
				        //Visited array has 3 different number
				        //0: not visited
				        //1: visiting
				        //2: visited
				        int[] visited = new int[n];
				        for (int node = 0; node < n; node++) {
				            if (visited[node] == 0) {
				                if (dfs(node, graph, visited)) {
				                    continue;
				                } else {
				                    return false;
				                }
				            }
				        }
				        return true;
				    }
				public boolean dfs(int node, int[][] graph, int[] visited) {
				        visited[node] = 1;
				        for (int i = 0; i < graph.length; i++) {
				            if (grap[node][i] == 1) {
				                if (visited[i] == 1) {
				                    return false;
				                }
				                if (visited[i] == 0 && !dfs(i, graph, visited)) {
				                    return false;
				                }
				            }
				        }
				        visited[node] = 2;
				        return true;
				    }
				}
		0.1 Topological Sort
			/**
			 * Definition for Directed graph.
			 * class DirectedGraphNode {
			 *     int label;
			 *     ArrayList<DirectedGraphNode> neighbors;
			 *     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
			 * };
			 */
			public class Solution {   
			    public List<DirectedGraphNode> topSort(List<DirectedGraphNode> graph) {
			        List<DirectedGraphNode> res = new ArrayList<>();
			        HashMap<DirectedGraphNode, Integer> indegree = new HashMap<>();
			        
			        for (DirectedGraphNode node : graph) {
			            for (DirectedGraphNode neighbor : node.neighbors) {
			                if (indegree.containsKey(neighbor)) {
			                    indegree.put(neighbor, indegree.get(neighbor) + 1);
			                } else {
			                    indegree.put(neighbor, 1);
			                }
			            }
			        }
			        
			        Queue<DirectedGraphNode> queue = new LinkedList<DirectedGraphNode>();
			        for (DirectedGraphNode node : graph) {
			            if (!indegree.containsKey(node)) {
			                queue.offer(node);
			                res.add(node);
			            }
			        }
			        
			        while (!queue.isEmpty()) {
			            DirectedGraphNode node = queue.poll();
			            for (DirectedGraphNode neighbor : node.neighbors) {
			                indegree.put(neighbor, indegree.get(neighbor) - 1);
			                if (indegree.get(neighbor) == 0) {
			                    res.add(neighbor);
			                    queue.offer(neighbor);
			                }
			            }
			        }
			        return res;
			    }
			}
		0.2 Number Of Connected Components In An Undirected Graph
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



1. Topological Sort
		1.1 Course Schedule
			/*
				There are a total of n courses you have to take, labeled from 0 to n - 1.
				Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
				Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

				For example:
				2, [[1,0]]
				There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

				2, [[1,0],[0,1]]
				There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
				Note:
				The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
			*/
			//bfs + topological sort, 
			/*
				0. Construct the graph, the key is start point, the value if the end point
				1. The indegree array is key point,
				2. Let the node which the indegree equals zero offer to the queue,
				3. and substract 1 to the neighbour indegree of the node, if after remove the indegree of neighbour equals 0, we offer this node to the queue.
			*/
			public class Solution {
			    public boolean canFinish(int numCourses, int[][] prerequisites) {
			        HashMap<Integer, List<Integer>> graph = new HashMap<>();
			        int[] indegree = new int[numCourses];
			        int count = 0;
			        for (int i = 0; i < numCourses; i++) {
			            graph.put(i, new ArrayList<Integer>());
			        }
			        for (int[] course : prerequisites) {
			            graph.get(course[1]).add(course[0]);
			            indegree[course[0]]++;
			        }
			        Queue<Integer> queue = new LinkedList<>();
			        for (int i = 0; i < indegree.length; i++) {
			            if (indegree[i] == 0) {
			                queue.offer(i);
			            }
			        }
			        while (!queue.isEmpty()) {
			            int preCourse = queue.poll();
			            for (int curCourse : graph.get(preCourse)) {
			                if (--indegree[curCourse] == 0) {
			                    queue.offer(curCourse);
			                }
			            }
			            count++;
			        }
			        return count == numCourses;
			    }


			//Detect the Cycle by using DFS
			public class Solution {
			    public boolean canFinish(int numCourses, int[][] prerequisites) {
			        int[][] graph = new int[numCourses][numCourses];
			        for (int i = 0; i < prerequisites.length; i++) {
			            graph[prerequisites[i][1]][prerequisites[i][0]] = 1;
			        }
			        
			        //Visited array has 3 different number
			        //0: not visited
			        //1: visiting
			        //2: visited
			        int[] visited = new int[numCourses];
			        for (int node = 0; node < numCourses; node++) {
			            if (visited[node] == 0) {
			                if (dfs(node, graph, visited)) {
			                    continue;
			                } else {
			                    return false;
			                }
			            }
			        }
			        return true;
			    }
			    public boolean dfs(int node, int[][] graph, int[] visited) {
			        visited[node] = 1;
			        for (int i = 0; i < graph.length; i++) {
			            if (grap[node][i] == 1) {
			                if (visited[i] == 1) {
			                    return false;
			                }
			                if (visited[i] == 0 && !dfs(i, graph, visited)) {
			                    return false;
			                }
			            }
			        }
			        visited[node] = 2;
			        return true;
			    }
			}
		1.2 Course Schedule II
			//BFS
			public class Solution {
			    public int[] findOrder(int numCourses, int[][] prerequisites) {
			        int[] res = new int[numCourses];
			        int count = 0;
			        HashMap<Integer, List<Integer>> graph = new HashMap<>();
			        int[] indegree = new int[numCourses];
			        for (int i = 0; i < numCourses; i++) {
			            graph.put(i, new ArrayList<Integer>());
			        }
			        for (int[] course : prerequisites) {
			            graph.get(course[1]).add(course[0]);
			            indegree[course[0]]++;
			        }
			        Queue<Integer> queue = new LinkedList<>();
			        for (int i = 0; i < indegree.length; i++) {
			            if (indegree[i] == 0) {
			                queue.offer(i);
			            }
			        }
			        while (!queue.isEmpty()) {
			            int course = queue.poll();
			            res[count++] = course;
			            for (int i : graph.get(course)) {
			                if (--indegree[i] == 0) {
			                    queue.offer(i);
			                }
			            }
			        }
			        return count == numCourses ? res : new int[0];
			    }
			}

			//DFS
			public class Solution {
			    public int[] findOrder(int numCourses, int[][] prerequisites) {
			        List<List<Integer>> adj = new ArrayList<>(numCourses);
			        for (int i = 0; i < numCourses; i++) adj.add(i, new ArrayList<>());
			        for (int i = 0; i < prerequisites.length; i++) adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
			        boolean[] visited = new boolean[numCourses];
			        Stack<Integer> stack = new Stack<>();
			        for (int i = 0; i < numCourses; i++) {
			            if (!topologicalSort(adj, i, stack, visited, new boolean[numCourses])) return new int[0];
			        }
			        int i = 0;
			        int[] result = new int[numCourses];
			        while (!stack.isEmpty()) {
			            result[i++] = stack.pop();
			        }
			        return result;
			    }

			    private boolean topologicalSort(List<List<Integer>> adj, int v, Stack<Integer> stack, boolean[] visited, boolean[] isLoop) {
			        if (visited[v]) return true;
			        if (isLoop[v]) return false;
			        isLoop[v] = true;
			        for (Integer u : adj.get(v)) {
			            if (!topologicalSort(adj, u, stack, visited, isLoop)) return false;
			        }
			        visited[v] = true;
			        stack.push(v);
			        return true;
			    }
			}
		1.3 Alien Dictionary
			/*
				There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

				For example,
				Given the following words in dictionary,

				[
				  "wrt",
				  "wrf",
				  "er",
				  "ett",
				  "rftt"
				]
				The correct order is: "wertf".

				Note:
				You may assume all letters are in lowercase.
				If the order is invalid, return an empty string.
				There may be multiple valid order of letters, return any one of them is fine.
			*/
			/*
				1. 以每一个字符为顶点，构建有向图
				2. 注意wrt ，并不就一定意味着 w < r < t, 比如一个英文单词 bad,并不意味着b < a，同一个字符不能比较顺序，
				   能比较顺序的只有wrt和wrf中的 t和f: t < f ，也就是两个单词里的第一个字符能比较顺序，比如ett和rftt -> e < r
			*/
			public class Solution {
			    public String alienOrder(String[] words) {
			        //构建图
			        HashMap<Character, HashSet<Character>> graph = new HashMap<>();
			        for (int i = 0; i < words.length; i++) {
			            for (int j = 0; j < words[i].length(); j++) {
			                if (!graph.containsKey(words[i].charAt(j))) {
			                    graph.put(words[i].charAt(j), new HashSet<Character>());
			                }
			            }
			            if (i > 0) {
			                getOrder(words[i - 1], words[i], graph);//构建前后两个word中的 char的顺序
			            }
			        }
			        return topologicalSort(graph);
			    }
			    public String topologicalSort(HashMap<Character, HashSet<Character>> graph) {
			        StringBuilder sb = new StringBuilder();
			        HashMap<Character, Integer> indegree = new HashMap<>();
			        Queue<Character> queue = new LinkedList<>();
			        //构建入度hash
			        for (char c : graph.keySet()) {
			           for (char v : graph.get(c)) {
			               if (indegree.containsKey(v)) {
			                   indegree.put(v, indegree.get(v) + 1);
			               } else {
			                   indegree.put(v, 1);
			               }
			           }
			        }
			        //将入度为0的点进队列
			        for (char c : graph.keySet()) {
			            if (!indegree.containsKey(c)) {
			                queue.offer(c);
			            }
			        }
			        //用Queue，BFS遍历所有入度为0的点，
			        while (!queue.isEmpty()) {
			            char c = queue.poll();
			            sb.append(c);
			            for (char neighbor : graph.get(c)) {
			                indegree.put(neighbor, indegree.get(neighbor) - 1);
			                if (indegree.get(neighbor) == 0) {
			                    queue.offer(neighbor);
			                }
			            }
			        }
			        //遍历完了还有点入度为0，意味着发现了环，返回空字符，不是一个正确的顺序
			        for (int v : indegree.values()) {
			            if (v != 0) {
			                return "";
			            }
			        }
			        return sb.toString();
			        
			    }
			    //两个不同的单词只能比较第一个的不同char的顺序，比如 abc和aca
			    public void getOrder(String s, String t, HashMap<Character, HashSet<Character>> graph) {
			        for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
			            char c1 = s.charAt(i);
			            char c2 = t.charAt(i);
			            if (c1 != c2) {
			                if (!graph.get(c1).contains(c2)) {
			                    graph.get(c1).add(c2);
			                }
			                break;
			            }
			        }
			    }
			}


2. Tree
		2.1 Graph Valid Tree
			/*
				Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
				For example:

				Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
				Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

				Hint:
					Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
					According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
					Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
			*/
			//Solution1: BFS
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
			//Solution2: Union-Find
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
			    public class Solution {
			    	public boolean validTree(int n, int[][] edges) {
				        int[] parent = new int[n];
				        Arrays.fill(parent, -1);
				        for (int[] edge : edges) {
				            int x = find(parent, edge[0]);
				            int y = find(parent, edge[1]);
				        
				            if (x == y) {
				                return false;
				            }
				            parent[x] = y;
				        }
				        return edges.length == n - 1;
				    }
				    
				    public int find(int[] parent, int i) {
				        if (parent[i] == -1) {
				            return i;
				        } else {
				            return find(parent, parent[i]);
				        }
				    }
			    }
		2.2 Minimum Height Trees
			/*
				For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

				Format
				The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).

				You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

				Example 1:
					Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

					        0
					        |
					        1
					       / \
					      2   3
					return [1]

				Example 2:
					Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
					     0  1  2
					      \ | /
					        3
					        |
					        4
					        |
					        5
					return [3, 4]
				Note:

					(1) According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. 
				    In other words, any connected graph without simple cycles is a tree.”

					(2) The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
			*/
			/*
				Solution: 核心思想，从叶子结点开始找中间的结点
						  1. 用邻接矩阵保存graph信息
						  2. 从所有的叶子节点开始遍历，从叶子节点的邻接结点中删除该叶子结点，并判断邻接结点的size是否等于1 如果等于1就是已经成为叶子结点，放入新的叶子节点list
						  3. 下一层从新的叶子结点开始执行2，当剩下的结点说 <=2 时，跳出while，返回结果
			*/
			public class Solution {
			    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
			        if (n <= 1) {
			            return new ArrayList<Integer>(Arrays.asList(0));
			        }
			        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
			        for (int i = 0; i < n; i++) {
			            graph.put(i, new HashSet<Integer>());
			        }
			        for (int[] edge : edges) {
			            graph.get(edge[0]).add(edge[1]);
			            graph.get(edge[1]).add(edge[0]);
			        }
			        List<Integer> leaves = new ArrayList<>();
			        for (int i = 0; i < n; i++) {
			            if (graph.get(i).size() == 1) {
			                leaves.add(i);
			            }
			        }
			        while (n > 2) {
			            n = n - leaves.size();
			            List<Integer> newLeaves = new ArrayList<>();
			            for (int leave : leaves) {
			                for (int nearNode : graph.get(leave)) {
			                    graph.get(nearNode).remove(leave);
			                    if (graph.get(nearNode).size() == 1) {
			                        newLeaves.add(nearNode);
			                    }
			                }
			                leaves = newLeaves;
			            }
			        }
			        return leaves;
			    }
			}
	


2. Graph Problem
		3.1 Clone Graph
			/*
				Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.

				OJ's undirected graph serialization:
				Nodes are labeled uniquely.

				We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
				As an example, consider the serialized graph {0,1,2#1,2#2,2}.

				The graph has a total of three nodes, and therefore contains three parts as separated by #.

				First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
				Second node is labeled as 1. Connect node 1 to node 2.
				Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
				Visually, the graph looks like the following:

				       1
				      / \
				     /   \
				    0 --- 2
				         / \
				         \_/
			*/
			//Soltuion1: BFS
			/**
			 * Definition for undirected graph.
			 * class UndirectedGraphNode {
			 *     int label;
			 *     List<UndirectedGraphNode> neighbors;
			 *     UndirectedGraphNode(int x) { 
			 *       label = x; 
			 *       neighbors = new ArrayList<UndirectedGraphNode>(); 
			 *     }
			 * };
			 */
			public class Solution {
			    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
			        if (node == null) {
			            return null;
			        }
			        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
			        UndirectedGraphNode newRoot = new UndirectedGraphNode(node.label);
			        Queue<UndirectedGraphNode> queue = new LinkedList<>();
			        map.put(node, newRoot);
			        queue.offer(node);
			        while (!queue.isEmpty()) {
			            UndirectedGraphNode curNode = queue.poll();
			            for (UndirectedGraphNode oldNeighbor : curNode.neighbors) {
			                if (!map.containsKey(oldNeighbor)) {
			                    queue.offer(oldNeighbor);
			                    UndirectedGraphNode newNeighbor = new UndirectedGraphNode(oldNeighbor.label);
			                    map.put(oldNeighbor, newNeighbor);
			                }
			                map.get(curNode).neighbors.add(map.get(oldNeighbor));
			            }
			        }
			        return newRoot;
			    }
			}
			//Solution2: DFS
			public class Solution {
			    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
			        if (node == null) {
			            return null;
			        }
			        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
			        UndirectedGraphNode newRoot = new UndirectedGraphNode(node.label);
			        map.put(node, newRoot);
			        dfs(map, node);
			        return newRoot;
			    }
			    
			    public void dfs(HashMap<UndirectedGraphNode, UndirectedGraphNode> map, UndirectedGraphNode node) {
			        if (node == null) {
			            return;
			        }
			        for (UndirectedGraphNode oldNeighbor : node.neighbors) {
			            if (!map.containsKey(oldNeighbor)) {
			                UndirectedGraphNode newNeighbor = new UndirectedGraphNode(oldNeighbor.label);
			                map.put(oldNeighbor, newNeighbor);
			                dfs(map, oldNeighbor);
			            }
			            map.get(node).neighbors.add(map.get(oldNeighbor));
			        }
			    }
			}

3. Others
		3.1 Reconstruct Itinerary
			/*
				 Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

				 Note:
				 If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
				 All airports are represented by three capital letters (IATA code).
				 You may assume all tickets form at least one valid itinerary.
				 Example 1:
				 tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
				 Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
				 Example 2:
				 tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
				 Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
				 Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
			 */

			//Solution1
			public List<String> findItinerary(String[][] tickets) {
		        Map<String, PriorityQueue<String>> flights = new HashMap<>();
		        for (String[] ticket : tickets) {
		            if (!flights.containsKey(ticket[0])) {
		                flights.put(ticket[0], new PriorityQueue<String>());
		            }
		            flights.get(ticket[0]).add(ticket[1]);
		        }
		        List<String> res = new LinkedList<>();
		        Stack<String> stack = new Stack<>();
		        stack.push("JFK");
		        while (!stack.isEmpty()) {
		            while (flights.containsKey(stack.peek()) && !flights.get(stack.peek()).isEmpty()) {
		                stack.push(flights.get(stack.peek()).poll());
		            }
		            res.add(0, stack.pop());
		        }
		        return res;
		    }


