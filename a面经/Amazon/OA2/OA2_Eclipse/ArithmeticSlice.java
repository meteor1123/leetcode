package OA2;

import java.util.Arrays;

public class ArithmeticSlice {
	public static int Solution(int[] nums) {
		if (nums == null || nums.length < 3) {
			return 0;
		}
		int count = 0;
		int gap = nums[1] - nums[0];
		int len = 2;
		for (int i = 1; i < nums.length - 1; i++) {
			if (nums[i + 1] - nums[i] == gap) {
				len++;
			} else {
				gap = nums[i + 1] - nums[i];
				if (len >= 3) {
					count += (len - 1) * (len - 2) / 2;
				}
				if (count > 1000000000) {
					return -1;
				}
				len = 2;
			}
		}
		if (len >= 3) {
			count += (len - 1) * (len - 2) / 2;
		}
		return count > 1000000000 ? -1 : count;
	}
	
	//Solution2
	public static int getLAS(int[] A) {
		if (A.length < 3) {
			return 0;
		}
		int res = 0; 
		int diff = Integer.MIN_VALUE;
		int count = 0;
		int start = 0;
		for (int i = 1; i < A.length; i++) {
			int currDiff = A[i] - A[i - 1];
			if (diff == currDiff) {
				count += i - start - 1 > 0 ? i - start - 1 : 0;
			} else {
				start = i - 1;
				diff = currDiff;
				res += count;
				count = 0;
			}
		}
		res += count;
		return res;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {-1, 1, 3, 3, 3, 2, 1, 0, -1};
		System.out.println(getLAS(arr));
	}

}
