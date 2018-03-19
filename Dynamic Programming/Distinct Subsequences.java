/*
	Distinct Subsequences
	Given a string S and a string T, count the number of distinct subsequences of T in S.

	A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

	Here is an example:
	S = "rabbbit", T = "rabbit"

	Return 3.

	Show Tags:DP, String
*/

/*
	Solution: 
	 //题意：只可以用删除字符的方法从第一个字符串变换到第二个字符串，求出一共有多少种变换方法。
	 (注意是S变换成T，并且变换后S是完全 匹配T)
    //     r a b b b i t（S.length 列     （T.length, i）行
    //   1 1 1 1 1 1 1 1
    // r 0 1 1 1 1 1 1 1
    // a 0 0 1 1 1 1 1 1
    // b 0 0 0 1 2 3 3 3
    // b 0 0 0 0 1 3 3 3
    // i 0 0 0 0 0 0 3 3
    // t 0 0 0 0 0 0 0 3  
// 从这个表可以看出，无论T的字符与S的字符是否匹配，dp[i][j] = dp[i][j - 1].
   就是说，假设S已经匹配了j - 1个字符，得到匹配个数为dp[i][j - 1].
   现在无论S[j]是不是和T[i]匹配，匹配的个数至少是dp[i][j - 1]。
*/

/*
    At first, we set the dynamic programming array dp[i][j],
    dp[i][j] means ,the String T start from 0 to index i position ,
                    and the String S start from 0 to index j position.
                    the matched subsequence amount.

*/
public class Solution {

    //time complexity: O(m * n)
    // two-dimension dp 
    public int numDistinct(String  , String T) {
        if (S == null || T == null || S.length() < T.length()) {
            return 0;
        }
        int[][] dp = new int[S.length() + 1][T.length() + 1];
        for (int i = 0; i <= S.length(); i++)//T is empty
            dp[i][0] = 1;
        for (int i = 1; i <= S.length(); i++) {
            for (int j = 1; j <= T.length(); j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {//means this character can be retained or drop
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];//so the convert amount equals retain the character plus do not retain this character.
                } else {
                    dp[i][j] = dp[i - 1][j];//no matter the char i and char j equals or not, at least has dp[i - 1][j]
                }

                // dp[i][j] = dp[i - 1][j];  简化版本
                // if (s.charAt(i - 1) == t.charAt(j - 1)) {
                //     dp[i][j] += dp[i - 1][j - 1];
                // } 
            }
        }
     
        return dp[S.rlength()][T.length()];
    }

    //one -dimension dp
    public int numDistinct(String S, String T) {
        // write your code here
        if (S == null || T == null || S.length() < T.length()) {
            return 0;
        }
        int[] dp = new int[T.length() + 1];
        dp[0] = 1;
        for (int i = 1; i <= S.length(); i++) {
            for (int j = T.length(); j > 0; j--) {
                if (S.charAt(i - 1) == T.charAt(j - 1)) {
                    dp[j] = dp[j - 1] + dp[j];
                }
            }
        }
        return dp[T.length()];
    }