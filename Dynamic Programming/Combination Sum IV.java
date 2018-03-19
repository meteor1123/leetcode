/*
	377. Combination Sum IV

	Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

	Example:

	nums = [1, 2, 3]
	target = 4

	The possible combination ways are:
	(1, 1, 1, 1)
	(1, 1, 2)
	(1, 2, 1)
	(1, 3)
	(2, 1, 1)
	(2, 2)
	(3, 1)

	Note that different sequences are counted as different combinations.

	Therefore the output is 7.
*/


/*
	Follow up:
		What if negative numbers are allowed in the given array?
		How does it change the problem?
		What limitation we need to add to the question to allow negative numbers?

	if there are negative numbers in the array, we must add a requirement that each number is only used one time, or either positive number or negative number should be used only one time, 
	otherwise there would be infinite possible combinations.
	For example, we are given:
		{1, -1}, target = 1,
		it’s obvious to see as long as we choose n 1s and (n-1) -1s, it always sums up to 1, n can be any value >= 1.
*/

//Solution0: bottom-up DP, time: O(n * k), space : O(k), prefer
class Solution {
  public int combinationSum4(int[] nums, int target) {
      int[] dp = new int[target + 1];
      dp[0] = 1;
      for (int i = 1; i < dp.length; i++) {
          for (int num : nums) {
              if (i - num >= 0)
                  dp[i] += dp[i - num];
          }
      }
      return dp[target];
  }
}

//Solution1: recursive, time: O(2^n)
class Solution {
  public int combinationSum4(int[] nums, int target) {
      if (target == 0)
          return 1;
      int res = 0;
      for (int i = 0; i < nums.length; i++) {
          if (target >= nums[i])
              res += combinationSum4(nums, target - nums[i]);
      }
      return res;
  }
}

//Solution2: Memoization recursive
class Solution {
  private int[] dp;
  
  public int combinationSum4(int[] nums, int target) {
      dp = new int[target + 1];
      Arrays.fill(dp, -1);
      dp[0] = 1;
      return helper(nums, target);
  }
  
  private int helper(int[] nums, int target) {
      if (dp[target] != -1)
          return dp[target];
      int res = 0;
      for (int i = 0; i < nums.length; i++) {
          if (target >= nums[i])
              res += helper(nums, target - nums[i]);
      }
      dp[target] = res;
      return res;
  }
}

//Solution3: Memoization recursive HashMap
class Solution {
  public int combinationSum4(int[] nums, int target) {
      if (nums.length == 0)
          return 0;
      HashMap<Integer, Integer> map = new HashMap();
      return helper(nums, target, map);
  }
  
  private int helper(int[] nums, int target, HashMap<Integer, Integer> map) {
      if (target == 0)
          return 1;
      if (target < 0)
          return 0;
      if (map.containsKey(target))
          return map.get(target);
      int res = 0;
      for (int i = 0; i < nums.length; i++) {
          if (target >= nums[i])
              res += helper(nums, target - nums[i], map);
      }
      map.put(target, res);
      return res;
  }
}