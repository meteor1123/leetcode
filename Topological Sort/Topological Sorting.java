/*
	Topological Sorting
	Given an directed graph, a topological order of the graph nodes is defined as follow:

	For each directed edge A-->B in graph, A must before B in the order list.
	The first node in the order can be any node in the graph with no nodes direct to it.
	Find any topological order for the given graph.
	Note
	You can assume that there is at least one topological order in the graph.

	Example
	For graph as follow: 



	The topological order can be:

	[0, 1, 2, 3, 4, 5]

	or

	[0, 2, 3, 1, 5, 4]

	or

	....

	Challenge
	Can you do it in both BFS and DFS?
*/

/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 * };
 */

/*
	Solution:
	method1:  Record the pre nodes of every node, then find out a node without pre node in each iteration and delete this node from unvisited set, add this node to result
	http://www.cnblogs.com/EdwardLiu/p/4431722.html
	在图论中，由一个有向无环图的顶点组成的序列，当且仅当满足下列条件时，称为该图的一个拓扑排序（英语：Topological sorting）。
	（1）每个顶点出现且只出现一次；
	（2）若A在序列中排在B的前面，则在图中不存在从B到A的路径。
		 也可以定义为：拓扑排序是对有向无环图的顶点的一种排序，它使得如果存在一条从顶点A到顶点B的路径，那么在排序中B出现在A的后面。
*/

public class Solution {
	//注意在这题中，如果node 有neighbor就意味着 node -> neighbor   node指向neighbor！
	public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
		ArrayList<DirectedGraphNode> result = new ArrayList<DirectedGraphNode>();
		//用hashmap开确定一个点的邻接点数， key是定点，value是入度！
		HashMap<DirectedGraphNode, Integer> map = new HashMap();

		//遍历每个node的邻接点,构建每个点的入度
		for (DirectedGraphNode node : graph) {
			for (DirectedGraphNode neighbor : node.neighbors) {
				if (map.containsKey(neighbor)) {
					map.put(neighbor, map.get(neighbor) + 1);
				} else {
					map.put(neighbor, 1);
				}
			}
		}
		Queue<DirectedGraphNode> queue = new LinkedList<DirectedGraphNode>();
		for (DirectedGraphNode node : graph) {
			if (!map.containsKey(node)) {
				queue.offer(node);
				result.add(node);
			}
		}
		while (!queue.isEmpty()) {
			DirectedGraphNode node = queue.poll();
			for (DirectedGraphNode n : node.neighbors) {
				map.put(n, map.get(n) - 1);
				if (map.get(n) == 0) {
					result.add(n);
					queue.offer(n);
				}
			}
		}
		return result;
	}
}


