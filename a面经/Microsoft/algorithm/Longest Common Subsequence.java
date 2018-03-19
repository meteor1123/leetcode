/*
	Longest Common Subsequence
	Given two strings, find the longest comment subsequence (LCS).

	Your code should return the length of LCS.

	Example
	For "ABCD" and "EDCA", the LCS is "A" (or D or C), return 1

	For "ABCD" and "EACB", the LCS is "AC", return 2
*/

/*
	Clarification
What's the definition of Longest Common Subsequence?

*(Note that a subsequence is different from a substring, for the terms of the former need not be consecutive terms of the original sequence.) It is a classic computer science problem, the basis of file comparison programs such as diff, and has applications in bioinformatics.
1. dp[i][j] 定义为s1, s2的前i,j个字符串的最长common subsequence.

2. d[[i][j] 当char i == char j， 可以有三种选择，D[i - 1][j - 1] + 1，D[i ][j - 1], D[i - 1][j] ，取最大的

当char i != char j, D[i ][j - 1], D[i - 1][j] 里取一个大的（因为最后一个不相同，所以有可能s1的最后一个字符会出现在s2的前部分里，反之亦然。
*/


public class Solution {
    /**
     * @param A, B: Two strings.
     * @return: The length of longest common subsequence of A and B.
     */
    public int longestCommonSubsequence(String A, String B) {
        // write your code here
        int[][] dp = new int[A.length() + 1][B.length() + 1];
        for (int i = 1; i <= A.length(); i++) {
            for (int j = 1; j <= B.length(); j ++) {
                if(A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j], Math.max(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        return dp[A.length()][B.length()];
    }
}




