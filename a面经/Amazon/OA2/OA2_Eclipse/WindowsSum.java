 package OA2;

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
	//Solution2
	public static int[] Solution(int[] array, int k) {
		if (array == null || array.length < k || k <= 0)	
			return null;
		int[] rvalue = new int[array.length - k + 1];
		for (int i = 0; i < k; i++)
			rvalue[0] += array[i];
		for (int i = 1; i < rvalue.length; i++) {
			rvalue[i] = rvalue[i-1] - array[i-1] + array[i+k-1];
		}
		return rvalue;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}