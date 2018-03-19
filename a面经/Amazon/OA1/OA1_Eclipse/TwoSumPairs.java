package OA1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class TwoSumPairs {
	public static int TwoSumCount(int[] nums, int target) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		HashSet<Integer> set = new HashSet<>();
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (set.contains(target - nums[i])) {
				count++;
			} else {
				set.add(nums[i]);
			}
		}
		return count;
	}
//	public static int TwoSumCount(int[] nums, int target) {
//		if (nums == null || nums.length < 2) {
//			return 0;
//		}
//		Map<Integer, Integer> map = new HashMap<>();
//		int count = 0;
//		for (int i = 0; i < nums.length; i++) {
//			if (map.containsKey(target - nums[i])) {
//				count += map.get(target - nums[i]);
//			} 
//			if (!map.containsKey(nums[i])) {
//				map.put(nums[i], 1);
//			} else {
//				map.put(nums[i], map.get(nums[i]) + 1);
//			}
//		}
//		return count;
//	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {1,1,2,3,4,5,6};
		System.out.println(TwoSumCount(a, 5));
	}

}
