/*
	Recover Rotated Sorted Array
	Given a rotated sorted array, recover it to sorted array in-place.

	Example
	[4, 5, 1, 2, 3] -> [1, 2, 3, 4, 5]

	Challenge
	In-place, O(1) extra space and O(n) time.

	Clarification
	What is rotated array:

    For example, the orginal array is [1,2,3,4],
    The rotated array of it can be [1,2,3,4], [2,3,4,1], [3,4,1,2], [4,1,2,3]
*/
public class Solution {

	/*
		Solution:
			1. find the index of k which A[k] > A[k + 1]
			2. reverse the in front of k numbers
			3. reverse the at the back of A.length - k numbers
			4. reverse all the numers;
	*/
	public void recoverRotatedSortedArray(ArrayList<Integer> nums) {
	        // write your code
	        if (nums == null || nums.size() <= 1) {
	            return;
	        }
	        for (int i = 0; i < nums.size() - 1; i++) {
	            if (nums.get(i) > nums.get(i + 1)) {
	                reverse(nums, 0, i);// reverse in front of k number
	                reverse(nums, i + 1, nums.size() - 1);// reverse at the back of nums.length - k number
	                reverse(nums, 0, nums.size() - 1);//rever all the number
	                return;
	            }
	        }
	}
    
    private void reverse(ArrayList<Integer> nums, int start, int end) {
        for (int i = start, j = end ; i < j; i++, j--) {
            int temp = nums.get(i);
            nums.set(i, nums.get(j));
            nums.set(j, temp);
        }
    }
}