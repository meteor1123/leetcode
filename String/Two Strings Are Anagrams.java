/*
	Two Strings Are Anagrams
	Write a method anagram(s,t) to decide if two strings are anagrams or not.

	Example
	Given s="abcd", t="dcab", return true

	Challenge
		O(n) time, O(1) extra space
*/

public class Solution {
	public boolean anagram(String s, String t) {
		if (s == null || t == null || s.length() != t.length()) {
			return false;
		}
		int[] sArr = new int[256];
		int[] tArr = new int[256];
		for (int i = 0; i < s.length(); i++) {
			sArr[s.charAt(i)]++;
			tArr[t.charAt(i)]++;
		}
		for (int i = 0; i < 256; i++) {
			if (sArr[i] != tArr[i]) {
				return false;
			}
		}
		return true;
	}
}

//Solution2 prefer
public class Solution {
    /**
     * @param s: The first string
     * @param b: The second string
     * @return true or false
     */
    public boolean anagram(String s, String t) {
        // write your code here
        if ((s == null && t != null) || (s != null && t == null) || (s.length() != t.length())) {
            return false;
        }
        int[] charArr = new int[256];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                charArr[s.charAt(i) - '0']++;
            }
            if (t.charAt(i) != ' ') {
                charArr[t.charAt(i) - '0']--;
            }         
        }
        for (int i = 0; i < charArr.length; i++) {
            if (charArr[i] != 0) {
                return false;
            }
        }
        return true;
    }
};
