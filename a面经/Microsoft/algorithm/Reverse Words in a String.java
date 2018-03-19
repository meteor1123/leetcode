/*	
	Given an input string, reverse the string word by word.

	For example,
	Given s = "the sky is blue",
	return "blue is sky the".
*/


/*
	solution:
		step1: 检查输入s是否为空
		step2: 使用split按照空格 分隔生成字符数组res[],check该字符数组是否为空
		step3: 构造一个ArrayList,存放res[i]如果非空。
		step4: 用Collections.reverse方法逆置arraylist 
		step5: 构造string ans，将list里的字符串按照顺序加入
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
    //Solution Chapter 9
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        String[] strArr = s.split(" ");
        if (strArr.length == 0){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = strArr.length - 1; i >= 0; i--) {
            if (!strArr[i].equals("")){
                sb.append(strArr[i]).append(" ");
            }
        }
        //remove the last blank space(空格)
        return sb.substring(0, sb.length() - 1);
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







