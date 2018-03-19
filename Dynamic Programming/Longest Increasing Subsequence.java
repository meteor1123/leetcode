/*
	Longest Increasing Subsequence

	Given an unsorted array of integers, find the length of longest increasing subsequence.

	For example,
	Given [10, 9, 2, 5, 3, 7, 101, 18],
	The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

	Your algorithm should run in O(n2) complexity.

	Follow up: Could you improve it to O(n log n) time complexity?
*/

//O(n*n)
public class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                } 
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}



//O(n),binarySearch and Dp, prefer
/*
    The idea is that as you iterate the sequence, you keep track of the minimum value a subsequence of given length might end with, 
    for all so far possible subsequence lengths. So dp[i] is the minimum value a subsequence of length i+1 might end with. Having this info,
    for each new number we iterate to, we can determine the longest subsequence where it can be appended using binary search. 
*/
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] res = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(res, 0, len, num);
            if (i < 0) {
                i = -(i + 1); //index of the search key, if it is contained in the array; otherwise, (-(insertion point) – 1).
            }
            res[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }

    private int binarySearch(int[] nums, int start, int end, int target) {
        while (start < end) {
            int mid = start + (end - start)/2;
            if (nums[mid] >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}