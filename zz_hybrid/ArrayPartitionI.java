package LeetCode;

/*Given an array of 2n integers, your task is to group these integers into n pairs of integer, 
 * say (a1, b1), (a2, b2), ..., (an, bn) 
 * which makes sum of min(ai, bi) for all i from 1 to n as large as possible.
 * Example 1:
 * Input: [1,4,3,2]
 * Output: 4
 * Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).
*/

public class ArrayPartitionI {
	public int arrayPairSum(int[] array) {
		int res = 0;
		permute(array, 0, res);
		return res;
	}
	private void permute(int[] array, int index, int res) {
		if (index == array.length - 1) {
			int sum = 0;
			for (int i = 0; i < array.length / 2; i++) {
				sum += Math.min(array[i], array[(array.length / 2) + i]);
			}
			res = Math.max(res, sum);		
		}
		for (int i = index; i < array.length; i++) {
			swap(array, index, i);
			permute(array, index + 1, res);
			swap(array, index, i);
		}
	}
	private void swap(int[] array, int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	public static void main(String[] args) {
		ArrayPartitionI test = new ArrayPartitionI();
		int[] array = {1,3,2,4};
		int res = test.arrayPairSum(array);
		System.out.println(res);
	}
}
