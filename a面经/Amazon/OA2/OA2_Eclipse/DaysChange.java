package OA2;

import java.util.Arrays;

public class DaysChange {
	public static int[] Solution(int[] days, int n) {
		if (days == null || n <= 0) {
			return days;
		}
		int len = days.length;
		int[] res = new int[len + 2];
		res[0] = 0;
		res[len + 1] = 0;
		int preDay = res[0];
		for (int i = 1; i <= len; i++) {
			res[i] = days[i - 1];
		}
		for (int i = 0; i < n; i++) {
			for (int j = 1; j <= len; j++) {
				int temp = res[j];
				res[j] = preDay ^ res[j + 1];
				preDay = temp;
			}
		}
		return Arrays.copyOfRange(res, 1, len + 1);
	}
//    public static int[] state(int[] A, int n) {
//        for (int i = 0; i < n; ++i) {
//        	int pre = 0;
//            for (int j = 0; j < A.length; ++j) { 
//            	if (j == A.length - 1) {
//            		A[j] = pre ^ 0;
//            	} else {
//                    int cur = A[j];
//                    A[j] = A[j + 1] ^ pre;
//                    pre = cur;
//                }
//            }
//        }
//        return A;
//    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {1, 0, 0, 0, 0, 1, 0, 0};
		int[] res = Solution(nums, 1);
		for (int i : res) {
			System.out.print(i);
		}
	}

}
