/*
	Word Break II 
	Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

	Return all such possible sentences.

	For example, given
	s = "catsanddog",
	dict = ["cat", "cats", "and", "sand", "dog"].

	A solution is ["cats and dog", "cat sand dog"].
	Tags:DP, Backtracking
*/

public class Solution {
	//Solution1: Brute force, BackTracking
    public ArrayList<String> wordBreak(String s, Set<String> dict) {
        ArrayList<String> res = new ArrayList<String>();
        if (s == null || s.length() == 0 || dict.size() == 0 || dict == null || !isWordBreak(s, dict)) {
            return res;
        }
        wordBreakHelper(s, dict, 0, "", res);
        return res;
    }

    public void wordBreakHelper (String s, Set<String> dict, int start, String item, ArrayList<String> res) {
    	if (start == s.length()) {
    		res.add(item);
    		return;
    	}
    	StringBuilder sb = new StringBuilder();
    	for (int i = start; i < s.length(); i++) {
    		sb.append(s.charAt(i));
    		if (dict.contains(sb.toString())) {
    			String newItem = item.length() > 0 ? (item + " " + sb.toString()) : sb.toString();
    			wordBreakHelper(s, dict, i + 1, newItem, res);
    		}
    	}
    }
    //Word Break I's method, just to check the orginal String whether can be break or not
    public boolean isWordBreak(String s, Set<String> dict) {
        if (s == null || s.length() == 0 || dict.size() == 0 || dict == null) {
            return false;
        }
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                }
            }
        }
        return dp[s.length()];
    }




    /*
    	Solution II: Dynamic Programming
    */
}