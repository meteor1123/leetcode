package OA2;

public class GreatesCommonDivisor {
	public static int Solution(int[] nums) {
		if (nums == null || nums.length == 1) {
			return 0;
		}
		int res = nums[0];
		for (int i = 1; i < nums.length; i++) {
			res = gcd(res, nums[i]);
		}
		return res;
	}
	
	private static int gcd(int num1, int num2) {
		if (num1 == 0 || num2 == 0) {
			return 0;
		}
		while (num1 != 0 && num2 != 0) {
			if (num2 > num1) {
				int tmp = num1;
				num1 = num2;
				num2 = tmp;
			}
			int temp = num1 % num2;
			num1 = num2;
			num2 = temp;
		}
		return num1 + num2;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {1,24};
		System.out.println(Solution(nums));
	}

}
