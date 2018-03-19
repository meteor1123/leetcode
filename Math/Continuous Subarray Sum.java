/*
	523. Continuous Subarray Sum

	Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.

	Example 1:
	Input: [23, 2, 4, 6, 7],  k=6
	Output: True
	Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
	Example 2:
	Input: [23, 2, 6, 4, 7],  k=6
	Output: True
	Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
	Note:
	The length of the array won't exceed 10,000.
	You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
*/


假设: 
	a[i]+a[i+1]+...+a[j]=ak+q;

如果存在一个n 
	n>j 且 a[i]+a[i+1]+...+a[j]+...+a[n]=bk+q;

那么 
a[j+1]+...+a[n]=(b−a)k

因此利用这一结果，可以从序列第一个元素开始遍历，不断累加上当前的元素，并求出当前和除以k后的余数，用一个映射记录该余数出现时的下标，
如果同一个余数出现了两次，并且两次出现的下标之差大于1，那么就表示在这两个坐标之间的元素之和是k的倍数，因此就可以返回true，否则最后返回false。 

需要注意的两个地方： 
1. k可能取0，所以只有当k不为0时才对当前的和求余，同时针对于nums = [0, 0], k = 0的情况，需要添加一个初始映射(0, -1)来确保结果的正确。 
2. 下标之差至少为2才算是正确的情况，因为题目要求子序列长度至少为2，以上面的例子就是n至少等于j+2。 


class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap();
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) {
                sum = sum % k;
            }
            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1)
                    return true;
            } else {
                map.put(sum, i);
            }
        }
        return false;
    }
}