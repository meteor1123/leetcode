/*
	Valid Anagram
	Given two strings s and t, write a function to determine if t is an anagram of s.

	For example,
	s = "anagram", t = "nagaram", return true.
	s = "rat", t = "car", return false.
*/


/*
    Follow up:
        What if the inputs contain unicode characters? How would you adapt your solution to such case?

    Answer:
        Use a hash table instead of a fixed size counter. Imagine allocating a large size array to fit the entire range of unicode characters, 
        which could go up to more than 1 million. A hash table is a more generic solution and could adapt to any range of characters.
*/
public class Solution {

	//O(n) time complexity, O(1) space
	public boolean isAnagram(String s, String t) {
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++; 
        }
        
        for (int i = 0; i < t.length(); i++) {
            arr[t.charAt(i) - 'a']--;
        }
        
        for (int i : arr) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    //sort O(nlogn) time, O(n) space
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        Arrays.sort(sArr);
        Arrays.sort(tArr);
        
        for (int i = 0; i < sArr.length; i++) {
            if (sArr[i] != tArr[i]) {
                return false;
            }
        }
        return true;
    }
}