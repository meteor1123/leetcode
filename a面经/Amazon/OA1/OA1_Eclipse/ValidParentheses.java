package OA1;

import java.util.Stack;

public class ValidParentheses {
	public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        char[] charArr = s.toCharArray();
        for (char c : charArr) {
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            } else if (c == '}' || c == ']' || c == ')' ) {
                if (stack.isEmpty()) {
                    return false;
                } 
                char temp = stack.pop();
                switch(c) {
                    case ')' :
                        if (temp != '(') 
                            return false;
                        break;
                    case ']' :
                        if (temp != '[')
                            return false;
                        break;
                    case '}' :
                        if (temp != '{')
                            return false;
                        break;
                } 
            }
        }
        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
