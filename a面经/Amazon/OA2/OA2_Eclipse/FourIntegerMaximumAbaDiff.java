package OA2;

import java.util.Arrays;

public class FourIntegerMaximumAbaDiff {
	public static int[] fourInteger(int A, int B, int C, int D) {
		int[] arr = new int[4];
		arr[0] = A;
		arr[1] = B;
		arr[2] = C;
		arr[3] = D;
		Arrays.sort(arr);
		swap(arr, 0, 1);
		swap(arr, 2, 3);
		swap(arr, 0, 3);
		return arr;
		
	}
	public static void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
