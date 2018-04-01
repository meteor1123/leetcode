package LeetCode;

import java.util.Arrays;
import java.util.Comparator;

/*
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 */
public class LargestNumber {
	public String largestNumber(int[] nums) {
		String[] sNums = new String[nums.length];
		for (int i = 0; i < nums.length; i++) {
			sNums[i] = Integer.toString(nums[i]);
		}
		Comparator<String> comp = new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				s1 = s1 + s2;
				s2 = s2 + s1;
				return s2.compareTo(s1);
			}
		};
		Arrays.sort(sNums, comp);
		if (sNums[0].charAt(0) == '0') return "0";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < sNums.length; i++) {
			sb.append(sNums[i]);
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		LargestNumber test = new LargestNumber();
		int[] nums = {0,0};
		String res = test.largestNumber(nums);
		System.out.println(res);
	}
}
