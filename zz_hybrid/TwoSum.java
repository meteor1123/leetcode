package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
	public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return null;
    }
	public static void main(String[] args) {
		TwoSum test = new TwoSum();
		int[] array = {2,2,3,43,54,75,34,23,2,1,6,7,8,9,44,5,67,23};
		int target = 25;
		int[] res = test.twoSum(array, target);
		for (int n : res) {
			System.out.println(n);
		}
	}
}
