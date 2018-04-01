package LeetCode;

import java.util.Arrays;

public class Closest3Sum {
	public int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int res = 0;
		int gMin = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
        	int start = i + 1;
        	int end = nums.length - 1;
        	while (start < end) {
        		int sum = nums[i] + nums[start] + nums[end];
        		if (sum > target) {
        			end--;
        		} else if (sum < target) {
        			start++;
        		} else {
        			return sum;
        		}
        		if (Math.abs(target - sum) < gMin) {
        			gMin = Math.abs(target - sum);
        			res = sum;
        		}
        	}
        }
        return res;
	}
}
