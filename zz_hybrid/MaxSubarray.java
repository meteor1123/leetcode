package LeetCode;

public class MaxSubarray {
	public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        int gMax = 0;
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] >= 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
            gMax = Math.max(gMax, dp[i]);
        }
        return gMax;
    }
	public static void main(String[] args) {
		MaxSubarray test = new MaxSubarray();
		int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
		int res = test.maxSubArray(nums);
		System.out.println(res);
	}
}
