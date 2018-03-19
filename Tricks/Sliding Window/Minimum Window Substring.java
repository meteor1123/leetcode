/*
	Minimum Window Substring
	Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

	For example,
	S = "ADOBECODEBANC"
	T = "ABC"
	Minimum window is "BANC".

	Note:
	If there is no such window in S that covers all characters in T, return the emtpy string "".

	If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
*/

/*
    这道题也是用滑动窗口的思想，思想跟 Substring with Concatenation of All Words是一样的，
    同样是利用HashMap来存Dict，然后来遍历整个母串。
    因为这里是要求最短的包含子串的字符串，所以中间是可以允许有非子串字符的，

    当遇见非子串字符而count又没到子串长度时，可以继续走。
    当count达到子串长度，说明之前遍历的这些有符合条件的串，
    
    用一个pre指针帮忙找，pre指针帮忙找第一个在HashMap中存过的，并且找到后给计数加1后的总计数是大于0的，
    判断是否为全局最小长度，如果是，更新返回字符串res，更新最小长度，如果不是，继续找。

这道题的代码也参考了code ganker的。 
*/


//Solution1:
public class Solution {
    public String minWindow(String s, String t) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int[] m = new int[256];
        for (char c : t.toCharArray()) {
            m[c]++;
        }
        int start = 0, end = 0;
        int count = t.length();
        int minStart = 0, minLen = Integer.MAX_VALUE;
        while (end < s.length()) {
            if (m[s.charAt(end)]-- > 0) {
                count--;
            }
            end++;
            while (count == 0) {
                if (end - start < minLen) {
                    minStart = start;
                    minLen = end - start;
                }
                if (++m[s.charAt(start)] > 0) {
                    count++;
                }
                start++;
            }
        }
        if (minLen != Integer.MAX_VALUE) {
            return s.substring(minStart, minStart + minLen);
        }
        return "";
    }
}

//Solution2:
public class Solution {
	public String minWindow(String S, String T) {
        String res = "";
        if (S == null || S.length() == 0 || T == null || T.length() == 0)
            return res;
        HashMap<Character, Integer> dict = new HashMap<Character, Integer>();
        for (int i = 0; i < T.length(); i++) {
            if (!dict.containsKey(T.charAt(i)))
                dict.put(T.charAt(i), 1);
            else 
                dict.put(T.charAt(i), dict.get(T.charAt(i)) + 1);
        }
        
        //count record the matched characterS of String T
        int count = 0;
        //left record the left position of the slider window
        int left = 0;
        //record the minimum length of matched window
        int minLen = S.length() + 1;
        //the start position of matched window
        int minStart = 0;
        
        //traverse the String S ,begin from the right window position
        for (int right = 0; right < S.length(); right ++) {

            //if the traverse character -- S.charAt(right) is matched with the hashmap(dict)
            if (dict.containsKey(S.charAt(right))) {

                //minus the amount of the matched character in the dict
                dict.put(S.charAt(right), dict.get(S.charAt(right)) - 1);

                //after minus, the amount of the matched character is still >= 0, means this is a right matched character
                if (dict.get(S.charAt(right)) >= 0)
                    count++;

                //if count == T.length(), means we find a matched window, so we need to check the length whether is min or not
                while (count == T.length()) {
                    if (right - left + 1 < minLen) {
                        minLen = right - left + 1;
                        minStart = left;
                    }
                    
                    //We need to move the left side of the window
                    //if the character of left position is contains in the dict, we need to plus 1 with the value,
                    if (dict.containsKey(S.charAt(left))) {
                        dict.put(S.charAt(left), dict.get(S.charAt(left)) + 1);
                        //if the dict value of left charAt is > 0 ,means cause we move the left side of the window so produce a mismatched
                        if (dict.get(S.charAt(left)) > 0)
                            count--;
                    }
                    left++; //keep move the left position unless the count is  != T.length();
                }
            }
        }
        if (minLen > S.length())
            return "";
        return S.substring(minStart, minStart + minLen);
    }
}


//Solution3
public class Solution {
	public String minWindow(String s, String t) {
        String res = "";
        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return res;
        }
        HashMap<Character, Integer> dict = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            if (!dict.containsKey(t.charAt(i))) {
                dict.put(t.charAt(i), 1);
            } else {
                dict.put(t.charAt(i), dict.get(t.charAt(i)) + 1);
            }
        }
        
        int count = 0, start = 0, end = 0, minStart = 0;
        int minLen = s.length() + 1;
        while (end < s.length()) {
            char c = s.charAt(end);
            if (dict.containsKey(c)) {
                dict.put(c, dict.get(c) - 1);
                if (dict.get(c) >= 0) {
                    count++;
                }
                while (count == t.length()) {
                    if (end - start + 1 < minLen) {
                        minLen = end - start + 1;
                        minStart = start;
                    }
                    char leftChar = s.charAt(start);
                    if (dict.containsKey(leftChar)) {
                        dict.put(leftChar, dict.get(leftChar) + 1);
                        if (dict.get(leftChar) > 0) {
                            count--;
                        }
                    }
                    start++;
                }
            }
            end++;
        }
        if (minLen > s.length()) {
            return "";
        } else {
            return s.substring(minStart, minStart + minLen);        
        }
    }
}