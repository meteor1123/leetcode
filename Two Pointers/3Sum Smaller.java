/*
	3Sum Smaller
	Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

	For example, given nums = [-2, 0, 1, 3], and target = 2.

	Return 2. Because there are two triplets which sums are less than 2:

	[-2, 0, 1]
	[-2, 0, 3]

	Could you solve it in O(n2) runtime?
	Tags: Array, Two Pointers
*/

//Solution1:
public class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int len = nums.length;
        int count = 0;
        for (int left = 0; left < len - 2; left++) {
            int mid = left + 1;
            int right = len - 1;
            while (mid < right) {
                int sum = nums[left] + nums[mid] + nums[right];
                if (sum < target) {
                    count += right - mid;
                    mid++;
                } else {
                    right--;
                }
            }
        }
        return count;
    }
}




//Solution2:
public class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        if (nums.length < 3 || nums == null) {
            return 0;
        }
        Arrays.sort(nums);
        int count = 0;
        for (int left = 0; left <= nums.length - 3 ; left++) {
            for (int mid = left + 1; mid <= nums.length - 2; mid++) {
                 int right = nums.length - 1;
                 while (mid < right) {
                    int sum = nums[left] + nums[mid] + nums[right];
                    if (sum >= target) {
                        right--;
                    } else {
                        count++;
                        right--;
                    }
                 }
            }
           
        }
        return count;
    }
}