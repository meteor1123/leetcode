package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfAPhoneNumber {
	public List<String> combination(String digits) {
		String[] keyboard = {"0","1","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
		List<String> res = new ArrayList<>();
		if (digits == null || digits.length() == 0) return res;
		helper(digits, keyboard, 0, "", res);
		return res;
	}
	private void helper(String digits, String[] keyboard, int index, String cur, List<String> res) {
		if (index == digits.length()) {
			res.add(cur);
			return;
		}
		String letters = keyboard[digits.charAt(index) - '0'];
		for (int i = 0; i < letters.length(); i++) {
			helper(digits, keyboard, index + 1, cur + letters.charAt(i), res);
		}
	}
	public static void main(String[] args) {
		LetterCombinationsOfAPhoneNumber test = new LetterCombinationsOfAPhoneNumber();
		List<String> res = test.combination("23");
		System.out.println(res);
	}
}
