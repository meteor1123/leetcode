/*
	697. Degree of an Array

	Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.

	Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.

	Example 1:
	Input: [1, 2, 2, 3, 1]
	Output: 2
	Explanation: 
	The input array has a degree of 2 because both elements 1 and 2 appear twice.
	Of the subarrays that have the same degree:
	[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
	The shortest length is 2. So return 2.
	Example 2:
	Input: [1,2,2,3,1,4,2]
	Output: 6
	Note:

	nums.length will be between 1 and 50,000.
	nums[i] will be an integer between 0 and 49,999.
*/


/*
	 Solution: 
	 		1. 建三个hashmap, left right记录每个number出现最左或者最右的位置，count记录每个number出现的次数
	 		2. right - left 就是包含多有candidate number的最短距离
	 		3. 重新遍历 array, 假如当前num是degree， 就从left right 中拿位置相减.
*/

class Solution {
    public int findShortestSubArray(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        HashMap<Integer, Integer> left = new HashMap();
        HashMap<Integer, Integer> right = new HashMap();
        HashMap<Integer, Integer> count = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (left.get(num) == null)
                left.put(num, i);
            right.put(num, i);
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        
        int res = nums.length;
        int degree = Collections.max(count.values());
        
        for (int num : count.keySet()) {
            if (count.get(num) == degree) {
                res = Math.min(res, right.get(num) - left.get(num) + 1);
            }
        }
        return res;
    }
}