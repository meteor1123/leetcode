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
	isMatch("ab", ".*") → true
	isMatch("aab", "c*a*b") → true
*/

public class Solution {
    //DP
    ///http://www.cnblogs.com/EdwardLiu/p/4021407.html DP 图
    public boolean isMatch(String s, String p) {
        if (s == null && p == null) {
            return true;
        }
        if (s.length() == 0 && p.length() == 0) {
            return true;
        }

        //dp[i][j] means the length of string s is i, and the length of string p is j,
        //they are equals or not, S(0...i - 1) ,P(0...j - 1)
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        //Why length() + 1? ,becaue need to initialize the situation of length equals 0,dp[0][0]

        dp[0][0] = true; //初始化1，都为0，自然匹配

        // 这里的字符串匹配必须完全匹配，不能多也不能少，要正好，a* 可以代表0-无限个a，自然也可以表示成0
        for (int j = 1; j <= p.length(); j++) {
            if (p.charAt(j - 1) == '*' && j > 1) {
                //'*' Matches zero or more of the preceding element!!
                dp[0][j] = dp[0][j - 2];
            }
        }
        
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

        if (p.length() == 1 || p.charAt(1) != '*') {
            if (s.isEmpty() || (p.charAt(0) != '.' && p.charAt(0) != s.charAt(0))) {
                return false;
            } else {
                return isMatch(s.substring(1), p.substring(1));
            }
        }

        //P.length() >=2
        while (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
            if (isMatch(s, p.substring(2))) {
                return true;
            }
            s = s.substring(1);
        }
        return isMatch(s, p.substring(2));
    }

    //DP2
    public boolean isMatch(String s, String p) {
        if (s == null && p == null) {
            return true;
        }
        if (s.length() == 0 && p.length() == 0) {
            return true;
        }
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int j = 1; j <= p.length(); j++) {
            if (p.charAt(j - 1) == '*' && j > 1) {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {

                if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.') {
                    dp[i + 1][j + 1] = dp[i][j];

                } else if (p.charAt(j) == '*' && j > 0) {
                    if (s.charAt(i) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                        dp[i + 1][j + 1] = dp[i][j + 1] || dp[i + 1][j] || dp[i + 1][j - 1];

                        /*
                            if p.charAt(j - 1) == s.charAt(i) or p.charAt(j - 1) == '.' :
                                dp[i + 1][j + 1] = dp[i + 1][j]   a* count as single a
                                dp[i + 1][j + 1] = dp[i][j + 1]   a* count as multiple a
                                dp[i + 1][j + 1] = dp[i + 1][j - 1]   a* count as empty
                        */
                    } else {
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

}