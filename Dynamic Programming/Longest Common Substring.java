/*
	Longest Common Substring
	given two strings, find the longest common substring.

	Return the length of it.

	Note
	The characters in substring should occur continiously in original string. This is different with subsequnce.

	Example
	Given A=“ABCD”, B=“CBCE”, return  2
*/

/*
	D[i][j] 定义为：两个string的前i个和前j个字符串，尾部连到最后的最长子串。
	D[i][j] = 
	1. i = 0 || j = 0 : 0
	2. s1.char[i - 1] = s2.char[j - 1] ? D[i-1][j-1] + 1 : 0;
*/

public class Solution {
	public int longestCommonSubstring(String A, String B) {
		int[][] dp = new int[A.length() + 1][B.length() + 1];
		int res = 0;
		for (int i = 0; i <= A.length(); i++) {
			for (int j = 0; j <= B.length(); j++) {
				if (A.charAt(i - 1) != B.charAt(j - 1)) {
					dp[i][j] = 0;//可以省略
				} else {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}
				res = Math.max(result, res[i][j]);
			}
		}
		return result;
	}
}