import java.util.HashMap;
import java.util.Map;

public class Solution {
	public static int TwoSumCount(int[] nums, int target) {
		if (nums == null || nums.length < 2)	return 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(target - nums[i]))
				count += map.get(target - nums[i]);
			if (!map.containsKey(nums[i]))
				map.put(nums[i], 1);
			else map.put(nums[i], map.get(nums[i]) + 1);
		}
		return count;
	}
	
	public static void main(String[] args) {
		int rvalue = TwoSumCount(new int[] {1, 1, 2, 3, 4}, 5);
		System.out.println(rvalue);
		return;
	}
}
