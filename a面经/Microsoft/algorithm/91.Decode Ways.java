/*
	Decode Ways 
	A message containing letters from A-Z is being encoded to numbers using the following mapping:
	'A' -> 1
	'B' -> 2
	...
	'Z' -> 26

	Given an encoded message containing digits, determine the total number of ways to decode it.

	For example,
	Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
	The number of ways decoding "12" is 2.
	Tags:Dp, String
*/

/*
    题意解析：给你一串数字，解码成英文字母。
    类似爬楼梯问题，但要加很多限制条件。
    定义数组dp，dp[i]意味着：字符串s[0..i]可以有dp[i]种解码方法。
    回想爬楼梯问题一样，number[i] = number[i-1] + number[i-2]
    但不同的是本题有多种限制：
    第一： s[i-1]不能是0，如果s[i-1]是0的话，number[i]就只能等于number[i-2]
    第二，s[i-2,i-1]中的第一个字符不能是0，而且Integer.parseInt(s.substring(i-2,i))获得的整数必须在0到26之间。

    1010，生成的number数组为：[1,1,1,1,1]
    10000，生成的number数组为：[1,1,1,0,0,0,0,0,0]
*/

public class Solution {
    //DP
	public int numDecodings(String s) {
         if (s.length() == 0 || s == null || s == "0")
            return 0;
         int[] dp = new int[s.length() + 1];
         dp[0] = 1;
         //dp[i] mean the string has how many decode amount of s.substring(0, i) == 前i个字符串
        if (isValid(s.substring(0, 1))){
            dp[1] = 1;
        }
        for (int i = 2; i <= s.length(); i++) {
            //the substring from (i - 1, i) is valid, we add to the dp[i]
            if (isValid(s.substring(i - 1, i))) {
                dp[i] += dp[i - 1];
            }
            //and we also need to check the (i - 2, i), if is valid,we add to the dp[i]
            if (isValid(s.substring(i - 2, i))) {
                dp[i] += dp[i - 2];
            }
         }
         return dp[s.length()];
     }
     
     public boolean isValid(String s) {
        if (s.charAt(0) == '0') {
            return false;
        }
        int code = Integer.parseInt(s);
        return code >= 1 && code <= 26;
     }
}

//Recursive
public class Solution {
    int num;
    public int numDecodings(String s) {
        if (s.length() == 0) {
            return 0;
        }
        num = 0;
        dfs(s);
        return num;
    }
    
    public void dfs(String s){
        if (s.length() == 0) {
            num++;
        }
        for(int i = 0; i <= 1 && i < s.length(); i++){
            if (isValid(s.substring(0, i + 1))) {
                dfs(s.substring(i + 1));
            }
        }
    }
    
    public boolean isValid(String s){
        if (s.charAt(0) == '0') {
            return false;
        }
        int code = Integer.parseInt(s);
        return code >= 1 && code <= 26;
    }
}