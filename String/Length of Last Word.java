/*
	Length of Last Word
	Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

	If the last word does not exist, return 0.

	Note: A word is defined as a character sequence consists of non-space characters only.

	For example, 
	Given s = "Hello World",
	return 5.
*/

public class Solution {
    public int lengthOfLastWord(String s) {
        if(s.isEmpty())
            return 0;
        String[] strArr = s.split(" ");
        if(strArr.length == 0 )
            return 0;
 		return strArr[strArr.length-1].length();
    }
}

//No API
public class Solution {
    public int lengthOfLastWord(String s) {
        int i = s.length() - 1;
        int count = 0;
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }
        while (i >= 0 && s.charAt(i) != ' ') {
            count++;
            i--;
        }
        return count;
    }
}