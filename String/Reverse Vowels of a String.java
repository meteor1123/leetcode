/*
	Reverse Vowels of a String
	Write a function that takes a string as input and reverse only the vowels of a string.

	Example 1:
	Given s = "hello", return "holle".

	Example 2:
	Given s = "leetcode", return "leotcede".
 */
/*
	key point:
	(1)String vowels = "aeiouAEIOU";
	(2)char to String: Char + "";
	(3)use String.contains(String);
 */
public class Solution {
    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        String vowels = "aeiouAEIOU";
        char[] words = s.toCharArray();
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            char c1 = words[start];
            char c2 = words[end];
            if (vowels.contains(c1 + "") && vowels.contains(c2 + "")) {
                char temp = c1;
                words[start] = c2;
                words[end] = temp;
                start++;
                end--;
            } else if (vowels.contains(c1 + "")) {
                end--;
            } else if (vowels.contains(c2 + "")) {
                start++;
            } else {
                start++;
                end--;
            }
        }
        return new String(words);
    }
}