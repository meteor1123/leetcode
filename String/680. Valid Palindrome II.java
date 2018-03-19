/*
	680. Valid Palindrome II

	Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

	Example 1:
	Input: "aba"
	Output: True
	Example 2:
	Input: "abca"
	Output: True
	Explanation: You could delete the character 'c'.
	Note:
	The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
*/


class Solution {
    public boolean validPalindrome(String s) {
        if (s == null || s.length() == 0)
            return false;
        char[] chars = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        
        while (i < j) {
            if (chars[i] != chars[j])
                return isPalindrome(chars, i + 1, j) || isPalindrome(chars, i, j - 1);
            i++;
            j--;
        }
        return true;
    }
    
    public boolean isPalindrome(char[] chars, int i, int j) {
        while (i < j) {
            if (chars[i] != chars[j])
                return false;
            i++;
            j--;
        }
        return true;
    }
}