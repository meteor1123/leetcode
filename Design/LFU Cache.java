/*
	LFU Cache

	Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.

	get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
	put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.

	Follow up:
	Could you do both operations in O(1) time complexity?

	Example:

	LFUCache cache = new LFUCache( 2 capacity );

	cache.put(1, 1);
	cache.put(2, 2);
	cache.get(1);       // returns 1
	cache.put(3, 3);    // evicts key 2
	cache.get(2);       // returns -1 (not found)
	cache.get(3);       // returns 3.
	cache.put(4, 4);    // evicts key 1.
	cache.get(1);       // returns -1 (not found)
	cache.get(3);       // returns 3
	cache.get(4);       // returns 4
*/

class LFUCache {
    HashMap<Integer, Integer> values;
    HashMap<Integer, Integer> counts;
    HashMap<Integer, LinkedHashSet<Integer>> lists; // 按照使用的频率 以插入的顺序像linkedlist 一样放HashSet
    int capacity;
    int min = -1;
    public LFUCache(int capacity) {
        values = new HashMap();
        counts = new HashMap();
        lists = new HashMap();
        this.capacity = capacity;
        lists.put(1, new LinkedHashSet<>());
    }
    
    public int get(int key) {
        if (!values.containsKey(key))
            return -1;
        int count = counts.get(key);
        counts.put(key, count + 1);
        lists.get(count).remove(key);
        if (count == min && lists.get(count).size() == 0)
            min++;
        if (!lists.containsKey(count + 1))
            lists.put(count + 1, new LinkedHashSet());
        lists.get(count + 1).add(key);
        return values.get(key);
    }
    
    public void put(int key, int value) {
        if (capacity <= 0)
            return;
        if (values.containsKey(key)) {
            values.put(key, value);
            get(key);
            return;
        }
        
        if (values.size() >= capacity) {
            int evict = lists.get(min).iterator().next();
            lists.get(min).remove(evict);
            values.remove(evict);
        }
        values.put(key, value);
        counts.put(key, 1);
        min = 1;
        lists.get(1).add(key);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */