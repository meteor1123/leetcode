/*
	Rotate Array 
	Rotate an array of n elements to the right by k steps.

	For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

	Note:
	Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.

	[show hint]

	Credits:
	Special thanks to @Freezen for adding this problem and creating all test cases.
*/


 /* 
        Solution for 后K: reverse the array,k mean the mostright k elements
                  1. reverse 前面 length - k个数 ，in front of length - k numbers
                  2. reverse 后面 k 个数           at the back of k numbers
                  3. reverse 所有的数，这样就可以得到在倒数位置K逆转的数组
                  
        Solution for 前K: reverse the array,k mean the mostleft k elements
                  1. reverse 前面k个数
                  2. reverse 后面 length - k 个数
                  3. reverse 所有数, 这样就可以得到在正数位置K逆转的数组
    */
   
   
public class Solution {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i++, j--);
        }
    }
}