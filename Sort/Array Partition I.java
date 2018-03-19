/*
    561. Array Partition I

    Given an array of 2n integers, your task is to group these integers into n pairs of integer, say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.

    Example 1:
    Input: [1,4,3,2]

    Output: 4
    Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).
    Note:
    n is a positive integer, which is in the range of [1, 10000].
    All the integers in the array will be in the range of [-10000, 10000].
*/



//Solution1: 
class Solution {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length - 1; i += 2) {
            res += nums[i];
        }
        return res;
    }
}


//Solution2: 时间换空间 time: O(n), space O(20001) => O(1)
/*
    1. Sort 然后每两个数加第一个数
    2. 注意到n在 [1, 10000], integers的范围是[-10000, 10000];
        我们可以建一个 length 为20001的数组，
            shift 10000 用[0, 20000] -> [-10000, 10000];
            遍历这个数组将出现的数字和频率++，
            再次遍历从0开始加奇数次出现的数字
*/
class Solution {
    public int arrayPairSum(int[] nums) {
        int[] exist = new int[20001];
        for (int i = 0; i < nums.length; i++)
            exist[nums[i] + 10000]++;
        
        int sum = 0;
        boolean odd = true;
        for (int i = 0; i < exist.length; i++) {
            while (exist[i] > 0) {
                if (odd)
                    sum += i - 10000;
                odd = !odd;
                exist[i]--;
            }
        }
        return sum;
    }
}