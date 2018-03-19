/*
	Word Break
	Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

	For example, given
	s = "leetcode",
	dict = ["leet", "code"].

	Return true because "leetcode" can be segmented as "leet code".
	Tags: DP
*/
/* 
    time: O(n^2)
    space: O(n)
    dp[i]是表示到字符串s的第i个元素为止能不能用字典中的词来表示，我们需要一个长度为n的布尔数组来存储信息。
    然后假设我们现在拥有dp[0,...,i-1]的结果，我们来获得dp[i]的表达式。
    思路是对于每个以i为结尾的子串，看看他是不是在字典里面以及他之前的元素对应的dp[j]是不是true，如果都成立，那么dp[i]为true，
*/
public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
    	if (s == null || s.length() == 0 || dict.size() == 0 || dict == null) {
    		return null;
    	} 
    	int[] dp = new int[s.length() + 1];
    	dp[0] = true;
    	for (int i = 1; i <= s.length(); i++) {
    		for (int j = 0; j <= i; j++) {
                //dp[j] == true,which means the substring s from 0 to j - 1 can be represent by the dict's element
                // and if the dict also has the word from substring j to i, which mean dp[i] is also can be represented by dict
    			if (dp[j] && dict.contains(s.substring(j, i))) {
    				dp[i] = true;
    			}
     		}
    	}
    	return dp[s.length()];
    }
}
