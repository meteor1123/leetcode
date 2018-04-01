package LeetCode;

public class MedianOfTwoSortedArrays {
	public double findMedianSortedArrays(int[] a1, int[] a2) {
		int m = a1.length; 
		int n = a2.length;
		if ((m + n) % 2 == 0) {
			int medianLeft = findKth(a1, 0, a2, 0, (m + n)/2);
			int medianRight = findKth(a1, 0, a2, 0, (m + n)/2 + 1);
			return (medianLeft + medianRight)/2.0;
		} else {
			return findKth(a1, 0, a2, 0, (m + n)/2 + 1);
		}
		
	}
	private int findKth(int[] a, int aLeft, int[] b, int bLeft, int k) {
		if (aLeft >= a.length) return b[bLeft + k - 1];
		if (bLeft >= b.length) return a[aLeft + k - 1];
		if (k == 1) return Math.min(a[aLeft], b[bLeft]);
		
		int aHalfk = aLeft + k/2 - 1 < a.length? a[aLeft + k/2 - 1] : Integer.MAX_VALUE;
		int bHalfk = bLeft + k/2 - 1 < b.length? b[bLeft + k/2 - 1] : Integer.MAX_VALUE;
		if (aHalfk > bHalfk) {
			return findKth(a, aLeft, b, bLeft + k/2, k - k/2);
		} else {
			return findKth(a, aLeft + k/2, b, bLeft, k - k/2);
		}
	}
	public static void main(String[] args) {
		MedianOfTwoSortedArrays test = new MedianOfTwoSortedArrays();
		int[] a1 = {1,3};
		int[] a2 = {2};
		double res = test.findMedianSortedArrays(a1, a2);
		System.out.println(res);
	}
}
