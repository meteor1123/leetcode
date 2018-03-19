/*
	772. Basic Calculator III

	Implement a basic calculator to evaluate a simple expression string.

	The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

	The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.

	You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].

	Some examples:

	"1 + 1" = 2
	" 6-4 / 2 " = 4
	"2*(5+5*2)/3+(6/2+8)" = 21
	"(2+6* 3+5- (3*14/7+2)*5)+3"=-12
*/


/*
	o1 == 1 means +; o1 == -1 means - ;
	o2 == 1 means *; o2 == -1 means /.
	By default we have l1 = 0, o1 = 1, and l2 = 1, o2 = 1.
*/
class Solution {
    public int calculate(String s) {
        int l1 = 0, o1 = 1;
        int l2 = 1, o2 = 1;
        
        Stack<Integer> stack = new Stack();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (Character.isDigit(c)) {
                int num = c - '0';
                
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + (s.charAt(++i) - '0');
                }
                
                l2 = (o2 == 1 ? l2 * num : l2 / num);
            } else if (c == '(') {
            		// First preserve current calculation status
                stack.push(l1);
                stack.push(o1);
                stack.push(l2);
                stack.push(o2);
                
                // Then reset it for next calculation
                l1 = 0; o1 = 1;
                l2 = 1; o2 = 1;
            } else if (c == ')') {
            	  // First preserve the result of current calculation
                int num = l1 + o1 * l2;
                
                // Then restore previous calculation status
                o2 = stack.pop(); l2 = stack.pop();
                o1 = stack.pop(); l1 = stack.pop();
                
                // Previous calculation status is now in effect
                l2 = (o2 == 1 ? l2 * num : l2 / num);
            } else if (c == '*' || c == '/') {
                o2 = (c == '*' ? 1 : -1);  
            } else if (c == '+' || c == '-') {
                l1 = l1 + o1 * l2;
                o1 = (c == '+' ? 1 : -1);
                
                l2 = 1; o2 = 1;
            }
        }
        return (l1 + o1 * l2);
    }
}