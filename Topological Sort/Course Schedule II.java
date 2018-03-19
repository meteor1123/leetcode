/*
	Course Schedule II 
	There are a total of n courses you have to take, labeled from 0 to n - 1.

	Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

	Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

	There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

	For example:
	2, [[1,0]]
	There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]

	4, [[1,0],[2,0],[3,1],[3,2]]
	There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].
*/



/*
	Solution: in this problem, we can not use int[][] to represent the graph matrix,since that will be memory exceed
			  instead of that, we use boolean[][] to represent the graph matrix!
*/

//Solution1: prefer. toplogical sort(bfs)
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        Map<Integer, List<Integer>> graph = new HashMap();
        int[] indegree = new int[numCourses];
        
        // init graph
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList());
        }
        
        for (int[] course : prerequisites) {
            int pre = course[1];
            int cur = course[0];
            // if (!graph.containsKey(pre)) {
            //     graph.put(i, new ArrayList());   -- 必须要在上面建图，如果这样初始化会与null pointer error
            // }
            graph.get(pre).add(cur);
            indegree[cur]++;
        }
        
        Queue<Integer> queue = new ArrayDeque();
        
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int count = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            res[count++] = course;
            for (int nextCourse : graph.get(course)) {
                if (--indegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }
        return count == numCourses ? res : new int[0]; // must compare the count exactly equals course number otherwise invalid
    }
}


//Solution2
public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        boolean[][] matrix = new boolean[numCourses][numCourses];
        int[] indegree = new int[numCourses];
        
        for (int i = 0; i < prerequisites.length; i++) {
            int cur = prerequisites[i][0];
            int pre = prerequisites[i][1];
            if (matrix[pre][cur] == false) {
                indegree[cur]++;
                matrix[pre][cur] = true;
            }
            
        }
        int count = 0;
        Queue<Integer> queue = new LinkedList();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int course = queue.poll();
            res[count] = course;
            count++;
            for (int i = 0; i < numCourses; i++) {
                if (matrix[course][i] != false) {
                    if (--indegree[i] == 0) {
                        queue.offer(i);
                    }
                }
            }
        }
        return count == numCourses ? res : new int[0];
    }
}


//Solution3: dfs
public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList(numCourses);
        for (int i = 0; i < numCourses; i++) 
            adj.add(i, new ArrayList());
        for (int i = 0; i < prerequisites.length; i++)
            adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
        boolean[] visited = new boolean[numCourses];
        Stack<Integer> stack = new Stack();
        for (int i = 0; i < numCourses; i++) {
            if (!topologicalSort(adj, i, stack, visited, new boolean[numCourses])) 
                return new int[0];
        }
        int i = 0;
        int[] result = new int[numCourses];
        while (!stack.isEmpty()) {
            result[i++] = stack.pop();
        }
        return result;
    }

    private boolean topologicalSort(List<List<Integer>> adj, int v, Stack<Integer> stack, boolean[] visited, boolean[] isLoop) {
        if (visited[v]) 
            return true;
        if (isLoop[v]) r
            return false;
        isLoop[v] = true;
        for (Integer u : adj.get(v)) {
            if (!topologicalSort(adj, u, stack, visited, isLoop)) 
                return false;
        }
        visited[v] = true;
        stack.push(v);
        return true;
    }
}

// Solution4: 
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        Map<Integer, List<Integer>> graph = new HashMap();
        Map<Integer, Integer> indegree = new HashMap();
        
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList());
        }
        
        for (int[] course : prerequisites) {
            int pre = course[1];
            int cur = course[0];
            graph.get(pre).add(cur);
            indegree.put(cur, indegree.getOrDefault(cur, 0) + 1);
        }
        
        Queue<Integer> queue = new ArrayDeque();
        
        for (int i = 0; i < numCourses; i++) {
            if (!indegree.containsKey(i)) {
                queue.offer(i);
            }
        }
        
        int count = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            res[count++] = course;
            for (int nextCourse : graph.get(course)) {
                indegree.put(nextCourse, indegree.get(nextCourse) - 1);
                if (indegree.get(nextCourse) == 0) {
                    queue.offer(nextCourse);
                }
            }
        }
        return count == numCourses ? res : new int[0];
    }
}