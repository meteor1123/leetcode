/*
	Basic Calculator
	Implement a basic calculator to evaluate a simple expression string.

	The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

	You may assume that the given expression is always valid.

	Some examples:
	"1 + 1" = 2
	" 2-1 + 2 " = 3
	"(1+(4+5+2)-3)+(6+8)" = 23
	Tags: stack, math
*/

/*
	Solution:https://leetcode.com/discuss/39553/iterative-java-solution-with-stack
	Simple iterative solution by identifying characters one by one. One important thing is that the input is valid, which means the parentheses are always paired and in order. Only 5 possible input we need to pay attention:

	digit: it should be one digit from the current number
	'+': number is over, we can add the previous number and start a new number
	'-': same as above
	'(': push the previous result and the sign into the stack, set result to 0, just calculate the new result within the parenthesis.
	')': pop out the top two numbers from stack, first one is the sign before this pair of parenthesis, second is the temporary result before this pair of parenthesis. We add them together.
	Finally if there is only one number, from the above solution, we haven't add the number to the result, so we do a check see if the number is zero.
*/



//Solution1
/*
    Principle:
        (Sign before '+'/'-') = (This context sign);
        (Sign after '+'/'-') = (This context sign) * (1 or -1);
    Algorithm:
        Start from +1 sign and scan s from left to right;
        if c == digit: This number = Last digit * 10 + This digit;
        if c == '+': Add num to result before this sign; This sign = Last context sign * 1; clear num;
        if c == '-': Add num to result before this sign; This sign = Last context sign * -1; clear num;
        if c == '(': Push this context sign to stack;
        if c == ')': Pop this context and we come back to last context;
        Add the last num. This is because we only add number after '+' / '-'.
*/
// Stack 只用来存sign
public class Solution {
    public int calculate(String s) {
        if (s == null) {
            return 0;
        }
        int res = 0;
        int sign = 1;
        int num = 0;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(sign);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else if (c == '+' || c == '-') {
                res += sign * num;
                sign = stack.peek() * (c == '+' ? 1 : -1);
                num = 0;
            } else if (c == '(') {
                stack.push(sign);
            } else if (c == ')') {
                stack.pop();
            }
        }
        res += sign * num;
        return res;
    }
}
//Solution2
public class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int res = 0;
        int number = 0;
        int sign = 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = 10 * number + (int)(c - '0');
            } else if (c == '+') {
                res += sign * number;
                number = 0;
                sign = 1;
            } else if (c == '-') {
                res += sign * number;
                number = 0;
                sign = -1;
            } else if (c == '(') {
            	//we push the result first, then sign;
                stack.push(res);
                stack.push(sign);
                //reset the sign and result for the value in the parenthesis
                sign = 1;
                res = 0;
            } else if (c == ')') {
                res += sign * number;
                number = 0;
                res *= stack.pop();//stack.pop() is the sign before the parenthesis
                res += stack.pop();//stack.pop() now is the result calculated before the parenthesis
            }
        }
        if (number != 0) {
            res += sign * number;
        }
        return res;
    }
}