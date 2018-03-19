/*
	Basic Calculator II
	Implement a basic calculator to evaluate a simple expression string.

	The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

	You may assume that the given expression is always valid.

	Some examples:
		"3+2*2" = 7
		" 3/2 " = 1
		" 3+5 / 2 " = 5
*/

/*
    Solution: 算法逻辑
             1.运用stack， 遍历String上的所有character，如果遇到数字字符um = num * 10 + s.charAt(i) - '0'，为什么要这样，当遇见多位的数时，需要这样处理
             2.接下来如果遇到以下两种情况就对栈进行进栈操作
                1）  如果该字符非数字，且该字符不为空，根据sign，我们注意这个时候的sign是前次运算的sign，根据上次的sign来决定对前个num是采用何种操作。
                     之后将sign赋值为当前字符，num设为0
                2)   如果i遍历到字符串尾部。
            3. 将stack的所有项相加，就是ressult
*/

public class Solution {
    public int calculate(String s) {
        int len = s.length();
        if (s == null || len == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<Integer>();
        int num = 0;
        char sign = '+';
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if ((!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ') || i == len - 1) {
                if (sign == '-') {
                    stack.push(-num);
                }
                if (sign == '+') {
                    stack.push(num);
                }
                if (sign == '*') {
                    stack.push(stack.pop() * num);
                }
                if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                sign = s.charAt(i);
                num = 0;
            }
        }
        int res = 0;
        for (int i : stack) {
            res += i;
        }
        return res;
    }
}