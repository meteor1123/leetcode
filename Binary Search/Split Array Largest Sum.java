/*
	410. Split Array Largest Sum

	Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

	Note:
	If n is the length of array, assume the following constraints are satisfied:

	1 ≤ n ≤ 1000
	1 ≤ m ≤ min(50, n)
	Examples:

	Input:
	nums = [7,2,5,10,8]
	m = 2

	Output:
	18

	Explanation:
	There are four ways to split nums into two subarrays.
	The best way is to split it into [7,2,5] and [10,8],
	where the largest sum among the two subarrays is only 18.
*/

// time: O(log(sumOfArray - max) * n)
class Solution {
    public int splitArray(int[] nums, int m) {
        long sum = 0; //最小划分，整个数组为一个sum
        int singleMax = 0; //最大划分，每个数都为单独的一个sum
        for (int num : nums) {
            sum += num;
            singleMax = Math.max(singleMax, num);
        }
        return (int)binarySearch(nums, m, singleMax, sum);
    }
    
    // use binary search 
    private long binarySearch(int[] nums, int m, long low, long high) {
        long mid = 0;
        while (low < high) {
            mid = low + (high - low) / 2;
            //如果当前的mid 满足，则尝试更小的sum， 
            if (isValid(nums, m, mid)) {
                high = mid;
            } else {
                // 如果不满足，则尝试大的sum
                low = mid + 1;
            }
        }
        return high;
    }
    
    public boolean isValid(int[] nums, int m, long max) {
        int sum = 0;
        int count = 1;
        for (int num : nums) {
            sum += num;
            if (sum > max) {
                sum = num; // 因为sum已经大于max，所以当前加入的num应该为下一个划分
                count++;
                if (count > m)
                    return false;
            }
        }
        return true;
    }
}