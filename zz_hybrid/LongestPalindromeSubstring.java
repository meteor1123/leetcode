package LeetCode;

public class LongestPalindromeSubstring {
	public String longestPalindrome(String s) {
		int start = 0;
		int end = 0;
		for (int i = 0; i < s.length(); i++) {
			int l1 = findPalin(s, i, i);
			int l2 = findPalin(s, i, i + 1);
			int length = Math.max(l1, l2);
			if (length > end - start) {
				start = i - (length - 1) / 2;
				end = i + length / 2;
			}
		}
		return s.substring(start, end + 1);
	}
	private int findPalin(String s, int a, int b) {
		int left = a;
		int right = b;
		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			left--;
			right++;
		}
		return right - left - 1;
	}
	public static void main(String[] args) {
		LongestPalindromeSubstring test = new LongestPalindromeSubstring();
		String res = test.longestPalindrome("abcdcdc");
		System.out.println(res);
	}
}
