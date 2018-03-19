/*
	398. Random Pick Index

	Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

	Note:
	The array size can be very large. Solution that uses too much extra space will not pass the judge.

	Example:

	int[] nums = new int[] {1,2,3,3,3};
	Solution solution = new Solution(nums);

	// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
	solution.pick(3);

	// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
	solution.pick(1);
*/


/*
	Reservoir sampling 是一个随机采样算法，简单来说就是从 n 个 items 中随机选择 k 个items，并且每个 item 被选择的概率应该都一样。这个算法的优点在于时空复杂度都不高，其中时间复杂度为 O(n), 空间复杂度为 O(1)。下面介绍该算法的过程，并且以 leetcode 上的两道题目为例讲解。

	假设现在要从 n 个数里面随机选择一个数，那么通过 Reservoir sampling 选择的流程如下

	记最终选择的数为 result
	遍历数组，对于数组第 i 个数，以 1/i 的概率将其赋给result（i从1开始，所以第一个数肯定会赋给result）
	遍历完数组后得到的 result 即为产生的随机数
	假设现在有数组 [1, 2, 3], 随机产生一个数，那么按照上面的流程有

	遍历第一个数时，result = 1
	遍历第二个数时，result = 2 的概率为 1/2, 即 result = 1 的概率也是 1/2
	遍历第三个数时，result = 3 的概率为 1/3, result = 1 的概率为 (1 - 1/3) * 1/2 = 1/3, 同理 result = 2 的概率也是 1/3
	上面的 (1 - 1/3) * 1/2 指的是这一次没有选择第三个数且之前 result 的值为1的概率，通过数学归纳法可以很容易的证明遍历完整个数组后每个数被选择的概率是 1/n (n 为数组的长度)

	而假如要从 n 个数里面随机选择 k 个数时，Reservoir sampling 的过程类似上面的

	选择前 k 个数作为 result
	从第 k+1 个数开始遍历数组，对于数组第 k+i（i=1,2,…..）个数，以 kk+i 的概率选择这个数加入result并替换掉 result 中的任意一个数
	遍历完数组后得到的 result 即为产生的 k 个随机样本
	下面通过数学归纳法证明通过上面的算法过程最终每个数被选择的概率为 k/n
	1) i=1 时，选择第 k+1 个数的概率为 kk+1，而在 result 中 k 个数里面的一个(记为 x) 能够保留下来的概率为是 x 原来在 result中且这一次没有被替换的概率，而这一次没有被替换掉又可分为两种情况，一种是根本没有选择到第 k+i 个数，一种是选择了第 k+i 个数，但是替换 k 个数中的一个时没有替换掉 x。公式表示为

	p(x上一次在result中)∗p(x没有被替换掉)=1∗（kk+1∗(1−1k)+(1−kk+1)）=kk+1
	即每个数被选择的概率为 kk+1
	2) 因此当 i=m 时，每个数被选择的概率为 k/(k+m)
	3) 则当 i=m+1 时，选择第 k+m+1 个数的概率为 kk+m+1, 在 result 中 k 个数里面的一个(记为 x) 能够保留下来的概率为:

	p(x上一次在result中)∗p(x没有被替换掉)=kk+m∗（kk+m+1∗(1−1k)+(1−kk+m+1)）=kk+m+1
	从上可知，遍历到第 i 个数的时候，前 k+i 每个数被选择的概率为 k/(k+i),则遍历完 n 个数后，每个数被选择的概率为 k/n
*/
class Solution {
    int[] nums;
    Random random;
    public Solution(int[] nums) {
        this.nums = nums;
        this.random = new Random();
    }
    
    public int pick(int target) {
        int res = -1;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target)
                continue;
            // 跟0比较是比较容易设一个随机抽样值
            if (random.nextInt(++count) == 0)
                res = i;
        }
        return res;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */