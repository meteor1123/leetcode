package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangleII {
	public List<Integer> getList(int n) {
		List<Integer> curRow = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			curRow.add(0, 1);
			for (int j = 1; j < i; j++) {
				curRow.set(j, curRow.get(j) + curRow.get(j + 1));
			}
		}
		
	}
	public static void main(String[] args) {
		PascalTriangleII test = new PascalTriangleII();
		List<Integer> res = test.getList(5);
		System.out.println(res);
	}
}
