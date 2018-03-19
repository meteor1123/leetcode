/*
	Partition Array by Odd and Even

	Partition an integers array into odd number first and even number second.

	Have you met this question in a real interview? Yes
	Example
	Given [1, 2, 3, 4], return [1, 3, 2, 4]

	Challenge
	Do it in-place.
 */

//partition的是数组里的奇偶值，而不是数组下标
 public class Solution {
     public void partitionArray(int[] nums) {
         // write your code here;
         if (nums == null || nums.length == 0) {
             return;
         }
         int start = 0;
         int end = nums.length - 1;
         while (start < end) {
             while (start < end && nums[start] % 2 != 0) {
                 start++;
             }
             while (start < end && nums[end] % 2 == 0) {
                 end--;
             }
             if (start < end) {
                 int temp = nums[start];
                 nums[start] = nums[end];
                 nums[end] = temp;
                 start++;
                 end--;
             }
         }
     }
 }