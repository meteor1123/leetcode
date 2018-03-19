/*
	Different Ways to Add Parentheses 
	Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.

	Example 1
		Input: "2-1-1".

		((2-1)-1) = 0
		(2-(1-1)) = 2
		Output: [0, 2]
	Example 2
		Input: "2*3-4*5"

		(2*(3-(4*5))) = -34
		((2*3)-(4*5)) = -14
		((2*(3-4))*5) = -10
		(2*((3-4)*5)) = -10
		(((2*3)-4)*5) = 10
		Output: [-34, -14, -10, -10, 10]
*/


public class Solution {
	public List<Integer> diffWaysToCompute(String input) {
		List<Integer> res = new LinkedList<Integer>();
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*') {
				String part1 = input.substring(0, i);
				String part2 = input.substring(i + 1);
				List<Integer> part1Res = diffWaysToCompute(part1);
				List<Integer> part2Res = diffWaysToCompute(part2);
				for (Integer p1 : part1Res) {
					for (Integer p2 : part2Res) {
						int c = 0;
						switch (input.charAt(i)) {
							case '+' :
								c = p1 + p2;
								break;
							case '-' :
								c = p1 - p2;
								break;
							case '*' :
								c = p1 * p2;
								break;
						}
						res.add(c);
					}
				}
			}
		}
		if (res.size() == 0) {
			res.add(Integer.valueOf(input));
		}
		return res;
	}
}