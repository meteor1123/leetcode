/*
	Excel Sheet Column Number
	Related to question Excel Sheet Column Title

	Given a column title as appear in an Excel sheet, return its corresponding column number.

	For example:
		A -> 1
	    B -> 2
	    C -> 3
	    ...
	    Z -> 26
	    AA -> 27
	    AB -> 28 
*/

public class Solution {
	/*
		char to int --> 
			1.直接和int进行运算
			2. c - '0' ,比如'9' - '0'
		int to char --> 
			1.(char)
			2. + 'a' 就是对应的1 ： a 
	*/
	//1
    public int titleToNumber(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            res = 26 * res + c - 64;
        }
        return res;
    }
}

class Solution {
    public int titleToNumber(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            res = res * 26 + (c - 'A' + 1);
        }
        return res;
    }
}