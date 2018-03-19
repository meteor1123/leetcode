/*
	670. Maximum Swap

	Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.

	Example 1:
	Input: 2736
	Output: 7236
	Explanation: Swap the number 2 and the number 7.
	Example 2:
	Input: 9973
	Output: 9973
	Explanation: No swap.
	Note:
	The given number is in the range [0, 108]
*/

/*
	Solution:
		1. 建buckets，存每一个数字出现在数组中的最后的数字
		2. 从低位开始遍历num，如果从后找到一个大的数字的位置比i大，我们就知道找到了一个maximum swap

		建了十个桶，分别代表数字0到9，每个桶存该数字出现的最后一个位置，也就是低位。
		这样我们从开头开始遍历数字上的每位上的数字，然后从大桶开始遍历，
		如果该大桶的数字对应的位置大于当前数字的位置，说明低位的数字要大于当前高位上的数字，那么直接交换这两个数字返回即可
*/

class Solution {
    public int maximumSwap(int num) {
        char[] nums = String.valueOf(num).toCharArray();
        
        int[] buckets = new int[10];
        
        for (int i = 0; i < nums.length; i++) {
            buckets[nums[i] - '0'] = i;
        }
        
        for (int i = 0; i < nums.length; i++) {
            for (int d = 9; d > nums[i] - '0'; d--) {
                if (buckets[d] > i) {
                    char temp = nums[i];
                    nums[i] = nums[buckets[d]];
                    nums[buckets[d]] = temp;
                    return Integer.valueOf(new String(nums));
                }
            }
        }
        return num;
    }
}