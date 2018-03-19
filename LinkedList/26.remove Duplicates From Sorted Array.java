/*
	Remove Duplicates from Sorted Array 
	Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
	Do not allocate extra space for another array, you must do this in place with constant memory.

	For example,	
		Given input array A = [1,1,2],
	Your function should return length = 2, and A is now [1,2].
*/
// Solution1: count 不重复的number数量
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            //只要nums[i]和nums[i - 1]不同，就可以将A[i]赋值给nums[count],count记录着数组里不同长度number的个数
            if (nums[i] != nums[i - 1]) {
                nums[count++] = nums[i];
            }
        }
        return count;
    }
}

// Solution2: count 出现重复number的数量
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == nums[i]) {
                count++;
            } else {
                nums[i - count] = nums[i];
            }
        }
        
        return nums.length - count;
    }
}

