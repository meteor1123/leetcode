package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
	public List<List<Integer>> generate(int n) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (n == 0) return res;
		
		// firstRow contains only 1
		res.add(new ArrayList<>());
		res.get(0).add(1);
		
		for (int i = 1; i < n; i++) {
			List<Integer> curRow = new ArrayList<>();
			List<Integer> prevRow = res.get(i - 1);
			curRow.add(1);
			
			for (int j = 1; j < prevRow.size(); j++) {
				curRow.add(prevRow.get(j - 1) + prevRow.get(j));
			}
			curRow.add(1);
			res.add(curRow);
		}
		return res;
	}
	public static void main(String[] args) {
		PascalTriangle test = new PascalTriangle();
		List<List<Integer>> res = test.generate(6);
		System.out.println(res);
	}
}
