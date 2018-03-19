fr/*
	Two Sum III - Data structure design
	Design and implement a TwoSum class. It should support the following operations: add and find.

	add - Add the number to an internal data structure.
	find - Find if there exists any pair of numbers which sum is equal to the value.

	For example,
	add(1); add(3); add(5);
	find(4) -> true
	find(7) -> false

	Tags: HashTable, Design
*/

/*
    注意题目要求！two sum! two sum! two sum!!! 只需要计算两个数字的sum，
    因此这里可以使用hashtable，key就是加入的number，value是加入的number的次数，注意到我们只是two sum，因此不管同一个数字加多少次
    我们都只保存最多两次的value.
    find：
        1) 遍历key值，看看有没有key == value - key, 
        2) 如果有的话, 假如key 和value - key 不等，则只需要value - key出现一次既可以满足找到。
        3) 假如key 和 value - key 相等，则需要key出现两次！
*/
public class TwoSum {
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    // Add the number to an internal data structure.
	public void add(int number) {
	    map.put(number, map.get(number) == null ? 1 : 2);
	}

    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
	    for (int key : map.keySet()) {
	        int res = value - key;
	        Integer occur = map.get(res);
	        if (occur != null && (occur == 2 || (occur == 1 && key != res))) {
	            return true;
	        }
	    }
	    return false;
	}
}


// Your TwoSum object will be instantiated and called as such:
// TwoSum twoSum = new TwoSum();
// twoSum.add(number);
// twoSum.find(value);

// Your TwoSum object will be instantiated and called as such:
// TwoSum twoSum = new TwoSum();
// twoSum.add(number);
// twoSum.find(value);