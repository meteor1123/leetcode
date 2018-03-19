/*
	647. Palindromic Substrings

	Given a string, your task is to count how many palindromic substrings in this string.

	The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

	Example 1:
	Input: "abc"
	Output: 3
	Explanation: Three palindromic strings: "a", "b", "c".
	Example 2:
	Input: "aaa"
	Output: 6
	Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
	Note:
	The input string length won't exceed 1000.
*/

//中心回测法，中心点可以是一个char 也可以是两个char
class Solution {
    int count = 0;
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0)
            return 0;
        for (int i = 0; i < s.length(); i++) {
            extendPalindrome(s, i, i); // odd length;
            extendPalindrome(s, i, i + 1);
        }
        
        return count;
    }
    
    private void extendPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
    }
}