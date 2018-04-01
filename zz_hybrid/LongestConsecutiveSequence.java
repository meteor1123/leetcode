package LeetCode;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
	public int longestConsecutive(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		Set<Integer> set = new HashSet<Integer>();
		int res = 0;
		for (int n : nums) {
			set.add(n);
		}
		for (int i = 0; i < nums.length; i++) {
			int length = 1;
			if (!set.contains(nums[i] - 1)) {
				int cur = nums[i];
				while (set.contains(cur + 1)) {
					cur += 1;
					length += 1;
				}
			}
			res = Math.max(res, length);
		}
		return res;
	}
	public static void main(String[] args) {
		LongestConsecutiveSequence test = new LongestConsecutiveSequence();
		int[] nums = {400,100,3,1,4,2};
		int res = test.longestConsecutive(nums);
		System.out.println(res);
	}
}
