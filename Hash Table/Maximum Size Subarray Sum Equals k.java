/*
	Maximum Size Subarray Sum Equals k 
	Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

	Example 1:
	Given nums = [1, -1, 5, -2, 3], k = 3,
	return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)

	Example 2:
	Given nums = [-2, -1, 2, 1], k = 1,
	return 2. (because the subarray [-1, 2] sums to 1 and is the longest)

	Follow Up:
	Can you do it in O(n) time?
 */


//when k == 0 -> Subarray Sum Lintcode
public class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int maxLen = 0;
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum == k) {
                maxLen = i + 1;
            } else if (map.containsKey(sum - k)) {
                maxLen = Math.max(maxLen, i - map.get(sum - k));
            }
            
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return maxLen;
    }
}


