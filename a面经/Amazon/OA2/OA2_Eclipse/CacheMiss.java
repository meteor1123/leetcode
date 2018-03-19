package OA2;

import java.util.LinkedList;
import java.util.List;

public class CacheMiss {
	public static int Solution(int[] nums, int size) {
		if (nums == null) {
			return 0;
		}
		List<Integer> cache = new LinkedList<Integer>();
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (cache.contains(nums[i])) {
				cache.remove(new Integer(nums[i]));
			} else {
				count++;
				if (size == cache.size()) {
					cache.remove(0);
				}
			}
			cache.add(nums[i]);
		}
		return count;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {1, 2, 3, 4, 5, 2, 3, 1};
		System.out.println(Solution(nums, 4));
	}

}
