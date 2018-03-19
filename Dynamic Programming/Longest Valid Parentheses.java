/*
	Longest Valid Parentheses 
	Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

	For "(()", the longest valid parentheses substring is "()", which has length = 2.

	Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
	Tags: DP, String, Stack
*/

/*
    Solution:
    1. 新建一个stack，用以保存括号char的序号，而不是保存内容，遍历String上的所有char， 遇到'('一律将index进栈.
    2. 遇到')'，一律不进栈，此时判断stack是否为空如果为空
       1） 为空说明之前已经将'('匹配完出栈，已经没有'('可以进行匹配，所以需要将start的位置 赋值为i+ 1，跳过这个')'
       2） 如果栈非空， pop 一个'('和这个')'进行匹配， 匹配完成后判断栈是否为空，
            2.1）如果空  maxLen = Math.max(maxLen, i - start + 1) 
            2.2）如果栈非空  maxLen = Math.max(maxLen, i - stack.pop())
*/

public class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;  
        }
        Stack<Integer> stack = new Stack<Integer>();
        int start = -1;
        int maxLen = 0;
        //use a stack to store the unvalid parentheses
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                ////if stack is empty, it means that we already found a complete valid combo and pop to the stack
                // or since we meet a right parenthesesm so we need to update the last index. to store the unmatched  position
                if (stack.isEmpty()) {
                    start = i;
                } else {
                    stack.pop();
                    maxLen = stack.isEmpty() ? Math.max(maxLen, i - start) : Math.max(maxLen, i - stack.peek());
                }
            }
        }
        return maxLen;
    }
}