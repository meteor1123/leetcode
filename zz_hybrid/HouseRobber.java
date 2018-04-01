package LeetCode;

public class HouseRobber {
	public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        // dp[0] = nums[0].
        // dp[1] = max(nums[0], nums[1]);
        // dp[i] represents the current robbed money including i;
        // dp[i] = max(dp[i - 1], dp[i - 2] + nums[i]);
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }
	public static void main(String[] args) {
		HouseRobber test = new HouseRobber();
		int[] nums = {2,3,1,0,4,6,3};
		int res = test.rob(nums);
		System.out.println(res);
	}
}
