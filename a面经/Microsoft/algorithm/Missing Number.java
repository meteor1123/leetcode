/*
	Missing Number

	Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

	For example,
	Given nums = [0, 1, 3] return 2.

	Note:
	Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?


*/

//Solution1: bits prefer
public class Solution {
	//这里的数和index是对应的， 除了missing number，将所有的数与其对应的index进行xor操作
    public int missingNumber(int[] nums) {
        int xor = 0;
        int i = 0;
        while (i < nums.length) {
            xor = xor ^ i ^ nums[i];
            i++;
        }
        // nums[0]=0 nums[1]=2  nums[2]=3    i=3
        // 形如 0 ，2 ， 3 = 0 ^ (0 ^ 0) ^ (1 ^ 2) ^ (2 ^ 3)   ^  3
        return xor ^ i;
    }
}

//Solution2:
public class Solution {
    public int missingNumber(int[] nums) {
        int sum = 0;
        for(int num: nums)
            sum += num;

        return (nums.length * (nums.length + 1) )/ 2 - sum; //等差数列-和=所缺数字
    }
}