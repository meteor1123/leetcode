/*
	One Edit Distance
	Given two strings S and T, determine if they are both one edit distance apart.
	Notice: Must be one apart distance,not same!
	Tags: String
*/


/*
	这道题的关键在于根据s 和 t的长度开始着手
	we let s.length() = m, t.length() = n;
	1. If |m - n| > 1, impossible to have one edit distance
	2. If  m == n, set a count to record the different char, check count == 1 
	3. If |m - n| == 1, which mean if has one distance apart, that is append or delete operation.
		if m == n - 1 find the first different position i , 
			if  s.substring(i, m)  ==  t.substring(i + 1, n),return true
			else return false;
*/
/*
    这题其实超级简单
    分三步 s.length() == t.length(), 只要比较char 计算count不同就行
          |s.length() - t.length()| == 1， 找到不同的位数，从这个位开始比较后面的字符串是否相同，长的那个要从i + 1 开始是相当于删除操作

*/
public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int m = s.length();
        int n = t.length();
        
        if (m > n) {
            return isOneEditDistance(t, s);
        }
        
        if (m == n) {
            int count = 0;
            for (int i = 0; i < m; i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    count++;
                }
            }
            return count == 1;
        } 
        
        if (m == n - 1) {
            for (int i = 0; i < m; i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    return s.substring(i).equals(t.substring(i + 1)); 
                }
            }
            return true;
        }
        
        return false;
    }
}