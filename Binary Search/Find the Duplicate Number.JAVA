/*
	Find the Duplicate Number
	Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

	Note:
	You must not modify the array (assume the array is read only).
	You must use only constant, O(1) extra space.
	Your runtime complexity should be less than O(n2).
	There is only one duplicate number in the array, but it could be repeated more than once.
*/
/*
	At first the search space is numbers between 1 to n. Each time I select a number mid (which is the one in the middle) 
	and count all the numbers equal to or less than mid. Then if the count is more than mid, the search space will be [1 mid] otherwise [mid+1 n]. 
	I do this until search space is only one number.
	Let's say n=10 and I select mid=5. Then I count all the numbers in the array which are less than equal mid. 
	If the there are more than 5 numbers that are less than 5, then by Pigeonhole Principle (https://en.wikipedia.org/wiki/Pigeonhole_principle) 
	one of them has occurred more than once. So I shrink the search space from [1 10] to [1 5].
	 Otherwise the duplicate number is in the second half so for the next step the search space would be [6 10].
*/

/*
	Solution:
	Let count be the number of elements in the range 1 .. mid, as in your solution.

	If count > mid, then there are more than mid elements in the range 1 .. mid and thus that range contains a duplicate.

	If count <= mid, then there are n+1-count elements in the range mid+1 .. n. 
	That is, at least n+1-mid elements in a range of size n-mid. Thus this range must contain a duplicate.
*/

/*
    Solution: 不用管数组里的实际数字，用 n + 1 个数分布在1-n这个区间，直接用区间序号来计算
*/

//Solution1: O(nlogn)
public class Solution {
    public int findDuplicate(int[] nums) {
        int start = 1;
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            int count = noGreater(nums, mid);
            if (count <= mid) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;//我们注意到 因为 start 最后会等于end，所以返回start或end都行
    }
    
    public int noGreater(int[] nums, int target) {
        int count = 0;
        for (int i : nums) {
            if (i <= target) {
                count++;
            }
        }
        return count;
    }
}

//Solution2: time O(n) space O(1)
//用linkedlist cycle II 的算法
class Solution {
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1)
            return -1;
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        fast = 0;
        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }
}



