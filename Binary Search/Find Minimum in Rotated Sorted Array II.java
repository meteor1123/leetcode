/*
	Find Minimum in Rotated Sorted Array II 
	Follow up for "Find Minimum in Rotated Sorted Array":
	What if duplicates are allowed?
	Would this affect the run-time complexity? How and why?

	Suppose a sorted array is rotated at some pivot unknown to you beforehand.

	(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

	Find the minimum element.

	The array may contain duplicates.

	Worst Case: O(n)
*/

//Solution1
public class Solution {
    public int findMin(int[] num) {
        int min = num[0];
        for (int i = 1; i < num.length; i++) {
            if(num[i] < min) {
                min = num[i];
            }
        }
        return min;
    }
}

//Solution2
public class Solution {
    public int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < nums[end]) {
                end = mid;
            } else if (nums[mid] > nums[end]) {
                start = mid;
            } else {
                end--;
            }
        }
        
        if (nums[start] <= nums[end]) {
            return nums[start];
        } else  {
            return nums[end];
        } 
    }
}