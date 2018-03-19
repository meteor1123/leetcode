/*	
	Given an input string, reverse the string word by word.

	For example,
	Given s = "the sky is blue",
	return "blue is sky the".
*/

/*
        Solution from chapter 9 :
                1. split the original string s by " "(blank space), and generate a new String[] array
                2. new a StringBuilder, and append the string[i] + " " from end to start(reverse)
                3. check the length of stringbuild whether is zero
                    if yes return ""
                    if no return substring(0, size() - 1), since we need to remove the last blank space;
*/
public class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0)
            return s;
        
        String[] words = s.split(" ");
        if (words.length == 0)
            return "";
        
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            if (!words[i].equals(""))
                sb.append(words[i]).append(" ");
        }
        return sb.toString().trim();
    }
}

//No using split(), O(n) time, O(1) space, prefer
public class Solution {
    public String reverseWords(String s) {
        StringBuilder res = new StringBuilder();
        for (int start = s.length() - 1; start >= 0; start--) {
            if (s.charAt(start) == ' ') {
                continue;
            }
            int end = start;
            while (start >= 0 && s.charAt(start) != ' ') {
                start--;
            }
            res.append(s.substring(start + 1, end + 1)).append(' ');
        }
        return res.toString().trim();
    }
}







