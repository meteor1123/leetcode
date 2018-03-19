/*
    Single Number III
    Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

    For example:

    Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

    Note:
    The order of the result is not important. So in the above example, [5, 3] is also correct.
    Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
*/

public class Solution {
    public int[] singleNumber(int[] nums) {
        // Pass 1 : 
        // Get the XOR of the two numbers we need to find
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        // Get its last set bit
        diff &= -diff;//这样可以得到两个single number 不相同的最靠近右边的位数 

        // Pass 2 :
        //
        //我们根据最右边这位数 去划分整个数组，一定会将两个single number分开
        int[] res = {0, 0}; // this array stores the two numbers we will return
        for (int num : nums) {
            if ((num & diff) == 0) {// the bit is not set
                res[0] ^= num;
            }
            else {// the bit is set
                res[1] ^= num;
            }
        }
        return res;
    }
}