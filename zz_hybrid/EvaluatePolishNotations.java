package LeetCode;

import java.util.Deque;
import java.util.LinkedList;

public class EvaluatePolishNotations {
	public int evalRPN(String[] tokens) {
		Deque<String> d = new LinkedList<>();
		Integer cur = 0;
		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i].equals("+")) {
				cur = Integer.parseInt(d.pop()) + Integer.parseInt(d.pop());
				d.push(cur.toString());
			} else if (tokens[i].equals("-")) {
				int b = Integer.parseInt(d.pop());
				int a = Integer.parseInt(d.pop());
				cur = a - b;
				d.push(cur.toString());
			} else if (tokens[i].equals("*")) {
				cur = Integer.parseInt(d.pop()) * Integer.parseInt(d.pop());
				d.push(cur.toString());
			} else if (tokens[i].equals("/")) {
				int b = Integer.parseInt(d.pop());
				int a = Integer.parseInt(d.pop());
				cur = a / b;
				d.push(cur.toString());
			} else {
				d.push(tokens[i]);
			}
		}
		return Integer.parseInt(d.pop());
	}
	public static void main(String[] args) {
		EvaluatePolishNotations test = new EvaluatePolishNotations();
		String[] tokens = {"4","13","5","/","+"};
		int res = test.evalRPN(tokens);
		System.out.println(res);
	}
}
