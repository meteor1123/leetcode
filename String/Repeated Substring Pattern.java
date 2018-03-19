/*
	459. Repeated Substring Pattern

	Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. 
	You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

	Example 1:
	Input: "abab"

	Output: True

	Explanation: It's the substring "ab" twice.
	Example 2:
	Input: "aba"

	Output: False
	Example 3:
	Input: "abcabcabcabc"

	Output: True

	Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
*/


/*
	1： 遍历 i 从len/2 -> 1, 把所有长度的s.substring(0, i) 累加到字符的长度，看看和原来的字符串是否相等
	2. 只判断能够 被len 整除的i的长度，其他略过 
*/
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        for (int i = len / 2; i >= 1; i--) {
            if (len % i == 0) {
                int m = len / i;
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < m; j++) {
                    sb.append(s.substring(0, i));
                }
                if (sb.toString().equals(s))
                    return true;
            }
        }
        return false;
    }

    //Just return if any of the substring will not match. No need to create the whole string.
    public boolean repeatedSubstringPattern(String str) {
        int len = str.length();
    	for(int i = len / 2 ; i >= 1; i--) {
    		if(len % i == 0) {
    			int m = len/i;
    			String subS = str.substring(0,i);
    			int j;
    			for(j = 1; j < m;j++) {
    				if(!subS.equals(str.substring(j * i, i + j * i))) break;
    			}
    			if(j == m)
    			    return true;
    		}
    	}
    	return false;
    }
}


class Solution {
	public boolean repeatedSubstringPattern(string str) {
        int len = str.length(), i = 0, j = 1;
        int[] p = new int[len];
        while (j < len)
            if (str.charAt(i) == str.charAt(j))
                p[j++] = ++i;
            else {
                if (i == 0) 
                    p[j++] = 0;
                else
                    i = p[i - 1];
            }
        return p[len - 1] > 0 && len % (len - p[len - 1]) == 0;
    }
}
		