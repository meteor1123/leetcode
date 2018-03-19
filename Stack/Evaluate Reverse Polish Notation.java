/*
	Evaluate Reverse Polish Notation 
	Evaluate the value of an arithmetic expression in Reverse Polish Notation.

	Valid operators are +, -, *, /. Each operand may be an integer or another expression.

	Some examples:
	  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
	  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
*/


public class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>();
        String operator = "+-*/";
        for (String s : tokens) {
            if (!operator.contains(s)) {
                stack.push(Integer.valueOf(s));
                continue;
            }
            int a = stack.pop();
            int b = stack.pop();
            if (s.equals("+")) {
                stack.push(b + a);
            } else if (s.equals("-")) {
                stack.push(b - a);
            } else if (s.equals("*")) {
                stack.push(b * a);
            } else if (s.equals("/")) {
                stack.push(b / a);
            }
        }
        return stack.pop();
    }
}