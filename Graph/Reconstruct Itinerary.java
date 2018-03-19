/*
	 Reconstruct Itinerary

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
            while (flights.containsKey(stack.peek()) &&!flights.get(stack.peek()).isEmpty()) {
                stack.push(flights.get(stack.peek()).poll());
            }
            res.add(0, stack.pop());
        }
        return res;
    }

//Solution2
public class Solution {
    Map<String, PriorityQueue<String>> flights;
    List<String> res;
 
    public List<String> findItinerary(String[][] tickets) {
        flights = new HashMap<>();
        res = new LinkedList<>();
        for (String[] ticket : tickets) {
            flights.putIfAbsent(ticket[0], new PriorityQueue<>());
            flights.get(ticket[0]).add(ticket[1]);
        }
        dfs("JFK");
        return res;
    }
 
    public void dfs(String departure) {
        PriorityQueue<String> arrivals = flights.get(departure);
        while (arrivals != null && !arrivals.isEmpty())
            dfs(arrivals.poll());
        res.addFirst(departure);
    }
}