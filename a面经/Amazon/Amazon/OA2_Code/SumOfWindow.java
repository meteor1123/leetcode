
public class SumOfWindow {
	public int[] Solution(int[] array, int k) {
		if (array == null || array.length < k || k <= 0)	return null;
		int[] rvalue = new int[array.length - k + 1];
		for (int i = 0; i < k; i++)
			rvalue[0] += array[i];
		for (int i = 1; i < rvalue.length; i++) {
			rvalue[i] = rvalue[i-1] - array[i-1] + array[i+k-1];
		}
		return rvalue;
	}
}
