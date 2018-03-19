/*
	3Sum Closest
	Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
	For example, given array S = {-1 2 1 -4}, and target = 1.
    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
*/

public class Solution {
    // 先对数组排个序。枚举第一个数，然后设两个指针，在第一个数的后半段开始王中间收缩，if sum > target则右指针往左移， if sum < target则左指针往右移。排序O(nlogn) + 查找O(n^2) = O(n^2)
    
    // 这道题也是3sum的变体，这里找到的不仅使3sum==target，同时如果没有找到==target的3sum要返回最接近target的值。
    // 于是，这就需要在使用二分查找法时遍历数组的时候，维护一个最接近target值min，这样当完全遍历完数组扔没找到与target相等的3sum时，可以返回维护的这个min值。
    // 这里要注意，判断closest的方法是采取target-sum的绝对值与min相比，很容易理解，无论这个closest是在target左还是右，离target最近的就是最closest的。
    public int threeSumClosest(int[] num, int target) {
        if(num == null || num.length < 3)
            return 0;
        int res = num[0] + num[1] + num[2];

        Arrays.sort(num);
        for (int i = 0; i < num.length - 2; i++) {
            int left = i + 1;
            int right = num.length - 1;
            while (left < right) {
                int sum = num[i] + num[left] + num[right];
                if (target > sum)
                    left++;
                else if (target < sum)
                    right--;
                else
                    return target;
                if (Math.abs(target - res) > Math.abs(target - sum)) {
                    res = sum;
                }
            }
        }
        return res;
    }
}