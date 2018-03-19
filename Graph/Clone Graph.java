/*
	Clone Graph 

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

/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { 
 *          label = x; 
 *          neighbors = new ArrayList<UndirectedGraphNode>();
 * };
 */
public class Solution {

	//BFS
    /*
        已错点分析，记住每一个点都要新建一个copynode，
    */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        //new a hashmap to store the old graph node and copy graph node, avoid the duplicate enqueue
        HashMap<UndirectedGraphNode, UndirectedGraphNode> hm = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        //clone graph's head node
        UndirectedGraphNode newhead = new UndirectedGraphNode(node.label);
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        hm.put(node, newhead);
        queue.offer(node);
        while (!queue.isEmpty()) {
            UndirectedGraphNode curNode = queue.poll();
            //put all the neighbor into the queue
            for (UndirectedGraphNode oldneighbor : curNode.neighbors) {
            	//use hashmap to avoid the previous graph point repeat go into the queue
                if (!hm.containsKey(oldneighbor)) {//这句话就可以排除重复访问
                    queue.add(oldneighbor);
                    UndirectedGraphNode newNeighbor = new UndirectedGraphNode(oldneighbor.label);
                    //use hash to mapping the  relation between new and old graph node
                    hm.put(oldneighbor, newNeighbor);
                }
                //put one of the neighbor node to the newNode
                hm.get(curNode).neighbors.add(hm.get(oldneighbor));
            }
        }
        return newhead;
    }

    //DFS
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        HashMap<UndirectedGraphNode, UndirectedGraphNode> hm = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        UndirectedGraphNode head = new UndirectedGraphNode(node.label);
        hm.put(node, head);
        
        dfs(hm, node);
        return head;
    }
    
    public void dfs(HashMap<UndirectedGraphNode, UndirectedGraphNode> hm, UndirectedGraphNode node) {
        if (node == null) {
            return;
        }
        
        for (UndirectedGraphNode oldneighbor : node.neighbors) {
            if (!hm.containsKey(oldneighbor)) {
                UndirectedGraphNode newneighbor = new UndirectedGraphNode(oldneighbor.label);
                hm.put(oldneighbor, newneighbor);
                dfs(hm, oldneighbor);//dfs
            }
            hm.get(node).neighbors.add(hm.get(oldneighbor));
        }
    }
}