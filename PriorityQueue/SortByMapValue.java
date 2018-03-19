/*
	Get the key By value order
*/

public static List<String> sortByMapValue(Map<String, Integer> map) {
		PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(map.size(), new Comparator<Map.Entry<String, Integer>>(){
			public int compare(Map.Entry<String, Integer> m1, Map.Entry<String, Integer> m2) {
				if (m1.getValue() > m2.getValue()) {
					return 1;
				} else if (m1.getValue() < m2.getValue()) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(entry);

        }
		List<String> res = new ArrayList<>();
		while (!pq.isEmpty()) {
			res.add(pq.poll().getKey());
		}
		return res;
	}