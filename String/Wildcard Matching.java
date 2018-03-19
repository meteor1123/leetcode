/*
	Wildcard Matching 
		Implement wildcard pattern matching with support for '?' and '*'.
		'?' Matches any single character.
	'*' Matches any sequence of characters (including the empty sequence).

	The matching should cover the entire input string (not partial).

	The function prototype should be:
	bool isMatch(const char *s, const char *p)

	Some examples:
	isMatch("aa","a") → false
	isMatch("aa","aa") → true
	isMatch("aaa","aa") → false
	isMatch("aa", "*") → true
	isMatch("aa", "a*") → true
	isMatch("ab", "?*") → true
	isMatch("aab", "c*a*b") → false
	Tags:Dynamic Programming,BackTracking,Greedy,string
*/

/*
	在这里'?'相当于那边的'.'，而'*'相当于那边的'.*'，因为这里'*'就可以代替任何字符串，不需要看前面的字符，所以处理起来更加简单。
*/
 
//如果p[i]！='*'，dp[i][j] == true 当 dp[i-1][j-1]==true &&（p[i]==s[j]||p[i]='?'）
//如果p[i]=='*'，dp[i][j]== true 当 其中一个m使得 dp[i-1][m]==true，where 0 <= m < j.
class Solution {    
	public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= p.length(); i++) {
            if (p.charAt(i - 1) == '*') {
                 dp[0][i] = dp[0][i - 1];
            }
        }
	    //dp[i][j - 1] 为true，自然多出的*也可以匹配i
		//dp[i - 1][j] 为true，*肯定也能匹配多出的i
		///dp[i - 1][j - 1] 为true，自然*和i也匹配
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
            	if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1] || dp[i - 1][j - 1];
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}

//Greedy solution
/*
	  
        //主要是*的匹配问题。p每遇到一个*，就保留住当前*的坐标和s的坐标，然后s从前往后扫描，如果不成功，则s++,重新扫描。
        //1.*s==*p or *p == ? ：匹配，s++ p++。
        //2. p=='*'：也是一个匹配，但可能有0个或者多个字符用于匹配，
        	 所以将'*'所在位置保存(star)，并且将s的位置也保存下来(ss)。
        //3. 如果不匹配，查看之前是否有'*'。没有则返回false，有则对ss的下一个位置和start之后的重新匹配。


*/
class Solution {
    public boolean isMatch(String str, String pattern) {
        int s = 0;
        int p = 0;
        int starMatch = 0;
        int starIndex = -1;
        while (s < str.length()){
            // advancing both pointers
            if (p < pattern.length()  && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))){
                s++;
                p++;
            } else if (p < pattern.length() && pattern.charAt(p) == '*'){// * found, only advancing pattern pointer
                starIndex = p;
                starMatch = s;
                p++;
            } else if (starIndex != -1){ // last pattern pointer was *, advancing string pointer
                p = starIndex + 1;
                starMatch++;
                s = starMatch;
                
            //current pattern pointer is not star, last patter pointer was not *
            } else {//characters do not match
                return false;
            }
           
            
           
        }

        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*')
            p++;

        return p == pattern.length();
    }
}

