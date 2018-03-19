/*
	Longest Common Prefix
	Write a function to find the longest common prefix string amongst an array of strings.
    Time complexity: O(m*n) 
*/


public class Solution {
    //Solution1 by myself
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLen = Integer.MAX_VALUE;
        //Find the minimum  string length in the string array
        for (String s : strs) {
            if (s.length() < minLen) {
                minLen = s.length();//update the minimum len
            }
        }
        //use strs[0] to check the every character is match or not
        String str = strs[0];

        //iterative the string[0],and compare the char with others Strings which in the array
        //Once found is not compared,return the substring,because this is the longest common prefix.
        for (int i = 0; i < minLen; i++) {
            char c = str.charAt(i);
            for (String s : strs) {
                if (s.charAt(i) != c) {
                    return str.substring(0, i);
                }
            }
        }
        return strs[0].substring(0, minLen);
    }

    //Solution2 best
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String pre = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(pre) != 0) {
                pre = pre.substring(0, pre.length() - 1);
            }
        }
        return pre;
    }

    //Solution3
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";

        for (int i = 0; i < strs[0].length() ; i++){
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j ++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);             
            }
        }
    return strs[0];
}
}