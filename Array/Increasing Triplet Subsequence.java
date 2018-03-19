/*
	Increasing Triplet Subsequence

	Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

	Formally the function should:
	Return true if there exists i, j, k 
	such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
	Your algorithm should run in O(n) time complexity and O(1) space complexity.

	Examples:
	Given [1, 2, 3, 4, 5],
	return true.

	Given [5, 4, 3, 2, 1],
	return false.
 */

// best, time O(n)
class Solution {
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3)
            return false;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > max)
                return true;
            else if (num > min)
                max = nums[i];
            else
                min = nums[i];
        }
        return false;
    }
}

//Solution2: solution from Longest Increasing Subsequence, time: O(nlogn), space: O(3)
class Solution {
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3)
            return false;
        int[] res = new int[3];
        int len = 0;
        for (int num : nums) {
            int index = Arrays.binarySearch(res, 0, len, num);
            if (index < 0) {
                index = -(index + 1);
            }
            res[index] = num;
            if (index == len) {
                len++;
            }
            if (len == 3)
                return true;
        }
        return false;
    }
}