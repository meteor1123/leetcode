/*
	Course Schedule
	There are a total of n courses you have to take, labeled from 0 to n - 1.

	Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

	Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

	For example:
	2, [[1,0]]
	There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

	2, [[1,0],[0,1]]
	There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
	Tags: Breadth-first Search Graph Topological Sort
*/

public class Solution {
	//BFS topological without using hashmap
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[][] matrix = new int[numCourses][numCourses];
        //caculate every vertices indegree
        int[] indegree = new int[numCourses];
        
        
        //Constructing a Adjacency matrices
        //and base on the matrix, set the indegree of every vertices
        for (int i = 0; i < prerequisites.length; i++) {
            //pre是起点，cur是终点
            // pre(1) -> cur(0)  (cur, pre) = (1, 0)  0是1的前序课程
            int pre = prerequisites[i][1];
            int cur = prerequisites[i][0];
            if (matrix[pre][cur] == 0) {
                indegree[cur]++;//cur的入度++
                matrix[pre][cur] = 1; //matrix这里设为1 只是表明存在从pre-cur的一条边，而不是计数！
            } 
        }
        //compute how many vertex has been visited,
        int count = 0;
        Queue<Integer> queue = new LinkedList();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            for (int i = 0; i < numCourses; i++) {
                if (matrix[course][i] != 0) {
                    if (--indegree[i] == 0) {
                        queue.offer(i);
                    }
                }
            }
        }

        //since we just offer every vertex once into queue, if count != numCourses, means has cycle
        return count == numCourses;
    }

	//DFS : find cycle by using dfs
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //construct graph matrix, this is a adjecnt matrix of directed graph
        // first index is outgoing vertex, second index is incoming vertex
        // if (prerequisites == null || prerequisites.length == 0) {
        //     return true;
        // }
        int[][] directedGraph = new int[numCourses][numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            directedGraph[ prerequisites[i][1] ][ prerequisites[i][0] ] = 1; 
        }
        //visited array has 3 different number,
        //0 : not visited
        //1 : visiting
        //2 : visited
        int[] visited = new int[numCourses];
        for (int node = 0; node < numCourses; node++) {
            if (visited[node] == 0) {
                if (dfs(node, directedGraph, visited)) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }
    public boolean dfs(int node, int[][] directedGraph, int[] visited) {
        visited[node] = 1;
        for (int otherNode = 0; otherNode < directedGraph.length; otherNode++) {
            if (directedGraph[node][otherNode] == 1) {
                if (visited[otherNode] == 1) {
                    return false;//find cycle
                }
                if (visited[otherNode] == 0 && !dfs(otherNode, directedGraph, visited)) {
                    return false;
                }
            }
        }
        visited[node] = 2;
        return true;
    }


    //BFS topological sort by using HashMap and Queue
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		HashMap<Integer, ArrayList<Integer>> map = HashMap<Integer, ArrayList<Integer>>();
		int[] indegree = new int[numCourses];
		Queue<Integer> queue = new LinkedList<Integer>();
		int count = numCourses;
		for (int i = 0; i < numCourses; i++) {
			map.put(i, new ArrayList<Integer>());
		}
		for (int i = 0; i < prerequisites.length; i++) {
			map.get(prerequisites[i][0]).add(prerequisites[i][1]);
			indegree[prerequisites[i][1]]++;
		}
		for (int i = 0; i < numCourses; i++) {
			if (indegree[i] == 0) {
				queue.offer(i);
			}
		}
		while (!queue.isEmpty()) {
			int current = queue.poll();
			for (int i : map.get(current)) {
				if (--indegree[i] == 0) {
					queue.offer(i);
				}
			}
			count--;
		}
		return count == 0;
	}
}