/*
	Remove Duplicates from Sorted Array II 
	Follow up for "Remove Duplicates":
	What if duplicates are allowed at most twice?

	For example,
	Given sorted array A = [1,1,1,2,2,3],

	Your function should return length = 5, and A is now [1,1,2,2,3].
	Tags: Array, Two Pointers
*/


// prefer
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null)
            return 0;
        if (nums.length <= 2)
            return nums.length;
        int count = 2;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[count - 2])
                nums[count++] = nums[i];
        }
        return count;
    }
}

public class Solution {
    //1 StefanPochmann
    public int removeDuplicates(int[] A) {
        int index = 0;
        for (int num : nums) {
            if (index < 2 || num != nums[index - 2]) {
                nums[index++] = num;
            }
        }
        return index;
    }
}

// at most K duplicates
public class Solution {
    //1 StefanPochmann
    public int removeDuplicates(int[] A) {
        int index = 0;
        for (int num : nums) {
            if (index < k || num > nums[index - k]) {
                nums[index++] = num;
            }
        }
        return index;
    }
}