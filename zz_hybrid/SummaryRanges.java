package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {
	public List<String> summaryRanges(int[] nums) {
		List<String> res = new ArrayList<String>();
		for (int i = 0; i < nums.length; i++) {
			int temp = nums[i];
			while (i + 1 < nums.length && nums[i + 1] - nums[i] == 1) {
				i++;
			}
			if (temp == nums[i]) {
				res.add(nums[i] + "");
			} else {
				res.add(temp + "->" +nums[i]);
			}
		}
		return res;
	}
	public static void main(String[] args) {
		SummaryRanges test = new SummaryRanges();
		int[] nums = {0,2,3,4,6,8,9};
		List<String> res = test.summaryRanges(nums);
		System.out.println(res);
	}
}
