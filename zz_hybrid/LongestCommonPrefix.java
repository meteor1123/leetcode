package LeetCode;

public class LongestCommonPrefix {
	public String longestCommonPrefix(String[] strs) {
		if (strs == null || strs.length == 0) {
			return null;
		}
		return merge(strs, 0, strs.length - 1);
	}
	private String merge(String[] strs, int left, int right) {
		if (left >= right) return strs[left];
		int mid = left + (right - left)/2;
		String leftCommon = merge(strs, left, mid);
		String rightCommon = merge(strs, mid + 1, right);
		return findCommon(leftCommon, rightCommon);
	}
	private String findCommon(String a, String b) {
		int len = Math.min(a.length(), b.length());;
		for (int i = 0; i < len; i++) {
			if (a.charAt(i) != b.charAt(i)) {
				return a.substring(0, i);
			}
		}
		return a.substring(0, len);
	}
	public static void main(String[] args) {
		LongestCommonPrefix test = new LongestCommonPrefix();
		String[] input = {"abcd","abcde","ab"};
		String res = test.longestCommonPrefix(input);
		System.out.println(res);
	}
}
