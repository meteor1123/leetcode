package OA1;

import java.util.ArrayList;
import java.util.List;
public class WindowsSum {
	public static List<Integer> getSum(int[] A, int k) {
		List<Integer> res = new ArrayList<>();
		if (A == null || A.length == 0 || k <= 0) {
			return res;
		}
		int count = 0;
		for (int i = 0; i < A.length; i++) {
			count++;
			if (count >= k) {
				int sum = 0;
				for (int j = i; j >= i - k + 1; j--) {
					sum += A[j];
				}
				res.add(sum);
			}
		}
		return res;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
