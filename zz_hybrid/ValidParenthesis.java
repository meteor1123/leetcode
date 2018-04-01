package LeetCode;

import java.util.Deque;
import java.util.LinkedList;

public class ValidParenthesis {
	public boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<>();
        char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '(') {
                stack.offerFirst(')');
            } else if (ch[i] == '[') {
                stack.offerFirst(']');
            } else if (ch[i] == '{') {
                stack.offerFirst('}');
            } else if (stack.isEmpty() || stack.pollFirst() != ch[i]) {
                return false;
            }
        }
        return stack.isEmpty();
    }
	public static void main(String[] args) {
		ValidParenthesis test = new ValidParenthesis();
		String s = "()";
		boolean res = test.isValid(s);
		if (res) System.out.print("yea");
		if (!res) System.out.println("no");
	}
}
