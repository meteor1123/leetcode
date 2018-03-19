/*
	387. First Unique Character in a String

	Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

	Examples:

	s = "leetcode"
	return 0.

	s = "loveleetcode",
	return 2.
	Note: You may assume the string contain only lowercase letters.


	It takes O(n) and goes through the string twice:

	1. Get the frequency of each character.
	2. Get the first character that has a frequency of one.

	Actually the code below passes all the cases. We could change the size of the frequency array to 256 to store other kinds of characters. 
*/

class Solution {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0)
            return -1;
        int[] dict = new int [26];
        
        for (int i = 0; i < s.length(); i++) {
            dict[s.charAt(i) - 'a'] += 1;
        }
        
        for (int i = 0; i < s.length(); i++) {
            if (dict[s.charAt(i) - 'a'] == 1) 
                return i;
        }
        return -1;
    }
}