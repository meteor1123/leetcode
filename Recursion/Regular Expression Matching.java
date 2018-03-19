/*
	Regular Expression Matching 
	Implement regular expression matching with support for '.' and '*'.

	'.' Matches any single character.
	'*' Matches zero or more of the preceding element.

	The matching should cover the entire input string (not partial).

	The function prototype should be:
	bool isMatch(const char *s, const char *p)
    
	Some examples:
	isMatch("aa","a") → false
	isMatch("aa","aa") → true
	isMatch("aaa","aa") → false
	isMatch("aa", "a*") → true
	isMatch("aa", ".*") → true
	isMatch("ab", ".*") → true  //".*" means we can have zero or more of the '.'
	isMatch("aab", "c*a*b") → true
*/

public class Solution {

    //DP
    ///http://www.cnblogs.com/EdwardLiu/p/4021407.html DP 图
    public boolean isMatch(String s, String p) {
        //dp[i][j] means the length of string s is i, and the length of string p is j,
        //they are equals or not, S(0...i - 1) ,P(0...j - 1)
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        //Why length() + 1? ,becaue need to initialize the situation of length equals 0,dp[0][0]

        dp[0][0] = true; //初始化1，都为0，自然匹配

        //initialize the situation of p's length equals 0,all the value is false;
        //默认就是false了 可以省略
        // for (int i = 1; i <= s.length(); i++) {
        //     dp[i][0] = false;
        // }

        // 这里的字符串匹配必须完全匹配，不能多也不能少，要正好，a* 可以代表0-无限个a，自然也可以表示成0
        for (int j = 1; j <= p.length(); j++) {
            if (p.charAt(j - 1) == '*' && j > 1) {
                //'*' Matches zero or more of the preceding element!!
                dp[0][j] = dp[0][j - 2];
            } 
            // else {
            //     dp[0][j] = false;
            // }
        }

        //dp[i][j-2] __a vs ab*:  depends on a vs a ,该情况下a为i, a为j - 2,只要匹配，后面的b* 可以等于0，所以能匹配
        //                       '*' 和前面的字符都不取，合并为空
        //dp[i][j-1] abb vs ab*:  depends on ab vs ab , 
        //                       '*' 和前面的字符，合二为一
        //dp[i-1][j] a  b  b  b  vs    a   b   *  :  depends on ab vs ab*，该情况下只要i - 1 和 i - 2的值相等
        //             i-2 i-1            j-2  j-1
        //                       '*' 取前面的字符1次， 因为 i - 1 和 j 已经匹配的话， i必须要和p的 一个字符再匹配，
        //                           所以*这时添上+1前面的相同字符
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*' && j > 1) {
                    if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1] || dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[s.length()][p.length()];
    }

        /*
        (1)p[j+1]不是'*'。情况比较简单，只要判断当前s的i和p的j上的字符是否一样（如果有p在j上的字符是'.',也是相同），
           如果不同，返回false，否则，递归下一层i+1，j+1; 
        (2)p[j+1]是'*'。那么此时看从s[i]开始的子串，假设s[i],s[i+1],...s[i+k]都等于p[j]那么意味着这些都有可能是合适的匹配，
           那么递归对于剩下的(i,j+2),(i+1,j+2),...,(i+k,j+2)都要尝试（j+2是因为跳过当前和下一个'*'字符）。 

                   i  i+1
         S:  b  a  a  a  a  a  a  a  a  b  c


         p:  b  a  a  *  a  b  c
                   j j+1
    */
    //Recursive2 prefer
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        //check the p.length() == 1 ,or the charAt(1) != '*', easy to do just recursive find the substring(1)
        if (p.length() == 1 || p.charAt(1) != '*') {
            if (s.isEmpty() || (p.charAt(0) != '.' && p.charAt(0) != s.charAt(0))) {
                return false;
            } else {
                return isMatch(s.substring(1), p.substring(1));
            }
        }
        //P.length() >=2 and p.charAt(1) must be equals = '*', notice the last if statement
        while (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
            if (isMatch(s, p.substring(2))) {
                return true;
            }
            s = s.substring(1);
        }
        return isMatch(s, p.substring(2));
    }
}