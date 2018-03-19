/*
	673. Number of Longest Increasing Subsequence

	Given an unsorted array of integers, find the number of longest increasing subsequence.

	Example 1:
	Input: [1,3,5,4,7]
	Output: 2
	Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
	Example 2:
	Input: [2,2,2,2,2]
	Output: 5
	Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
	Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int
*/


/*
	 Solution:
	 The idea is to use two arrays len[n] and cnt[n] to record the maximum length of Increasing Subsequence and the coresponding number of these sequence which ends with nums[i], respectively. That is:

	len[i]: the length of the Longest Increasing Subsequence which ends with nums[i].
	count[i]: the number of the Longest Increasing Subsequence which ends with nums[i].

	Then, the result is the sum of each cnt[i] while its corresponding len[i] is the maximum length.
*/
class Solution {
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int n = nums.length;
        int res = 0;
        int maxLen = 0;
        
        int[] len = new int[n];
        int[] count = new int[n];
        
        for (int i = 0; i < n; i++) {
            len[i] = count[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (len[i] == len[j] + 1)
                        count[i] += count[j];
                    else if (len[i] < len[j] + 1) {
                        len[i] = len[j] + 1;
                        count[i] = count[j];
                    }
                }
            }
            if (maxLen == len[i])
                res += count[i];
            else if (maxLen < len[i]) {
                maxLen = len[i];
                res = count[i];
            }
        }
        return res;
    }
}