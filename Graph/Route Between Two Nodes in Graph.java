/*
	Route Between Two Nodes in Graph

	Given a directed graph, design an algorithm to find out whether there is a route between two nodes.

	Have you met this question in a real interview? Yes
	Example
	Given graph:

	A----->B----->C
	 \     |
	  \    |
	   \   |
	    \  v
	     ->D----->E
	for s = B and t = E, return true

	for s = D and t = C, return false
 */

	/**
	 * Definition for Directed graph.
	 * class DirectedGraphNode {
	 *     int label;
	 *     ArrayList<DirectedGraphNode> neighbors;
	 *     DirectedGraphNode(int x) {
	 *         label = x;
	 *         neighbors = new ArrayList<DirectedGraphNode>();
	 *     }
	 * };  
	*/

//BFS
public class Solution {
  /**
     * @param graph: A list of Directed graph node
     * @param s: the starting Directed graph node
     * @param t: the terminal Directed graph node
     * @return: a boolean value
     */
    public boolean hasRoute(ArrayList<DirectedGraphNode> graph, 
                            DirectedGraphNode s, DirectedGraphNode t) {
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        queue.offer(s);
        while (!queue.isEmpty()) {
            DirectedGraphNode cur = queue.poll();
            if (cur == t) {
                return true;
            }
            for (DirectedGraphNode neighbor : cur.neighbors) {
                queue.offer(neighbor);
            }
        }
        return false;
    }
}


//DFS only difference is change from queue to stack
public class Solution {
   /**
     * @param graph: A list of Directed graph node
     * @param s: the starting Directed graph node
     * @param t: the terminal Directed graph node
     * @return: a boolean value
     */
    public boolean hasRoute(ArrayList<DirectedGraphNode> graph, 
                            DirectedGraphNode s, DirectedGraphNode t) {
        Stack<DirectedGraphNode> stack = new Stack<>();
        stack.push(s);
        while (!stack.isEmpty()) {
            DirectedGraphNode cur = stack.pop();
            if (cur == t) {
                return true;
            }
            for (DirectedGraphNode neighbor : cur.neighbors) {
                stack.push(neighbor);
            }
        }
        return false;
    }
}