/*
	Maximum Gap
		Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
	Try to solve it in linear time/space.
	Return 0 if the array contains less than 2 elements.
	You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
*/

/*
	官方版（桶排序）：

	假设有N个元素min到max。那么最大差值不会小于ceiling[(max - min) / (N - 1)], B是max value， A是min value， N是nums.length
	令bucket（桶）的大小len = ceiling[(max - min) / (N - 1)]
	对于数组中的任意整数K，很容易通过算式loc = (K - min) / len找出其桶的位置，然后维护每一个桶的最大值和最小值
	由于同一个桶内的元素之间的差值至多为len - 1，因此最终答案不会从同一个桶中选择。
	对于每一个非空的桶p，找出下一个非空的桶q，则q.min - p.max可能就是备选答案。返回所有这些可能值中的最大值。

	这里A是min，B是max，桶有num.length - 1个。min, max不参与放入桶中，除了min和max之外还有N-2个数字和N-1个桶，所以一定有一个空桶。
	因为有空桶的存在所以要用一个previous变量来代表上一个非空的桶的max。previous初始化为min，这样min就考虑了虽然min不在桶中。
	还要记得考虑max，所以最后遍历了桶之后还要再比一次max

	本算法O(n)时间和空间

	还要注意math.floor,不能用，要用math.ceil比如：2/2.667 = 0.7499062617172854, 期望是：1，但是floor会给0，ceil才能给1.
*/


/*
	1. 找到数组的min 和max 值，因为在一个有n个元素的数组，最小值为min，最大值为max，则两数最大的差值不会小于 ceiling[(max - min) / (n - 1)]
	2. 根据桶排序的原理，我们令bucket桶的大小 size = ceiling[(max - min) / (n - 1)],
	3. 对于数组中的任意数k， 可以通过 (k - min)/ size, 求出其属于的桶index，然后维护每个桶的最大值和最小值
	4. 则max 的差值 一定是前面桶的max 和相邻的后面的非空桶的最小值的差 , max = post.min - pre.max;
*/
public class Solution {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        //get the max and min value of the array
        int min = nums[0];
        int max = nums[0];
        
        for (int i : nums) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }
        //the minimum possible gap, ceiling of the integer division
        int gapSize = (int)Math.ceil((double)(max - min)/(nums.length - 1));
        int[] bucketsMIN = new int[nums.length - 1];
        int[] bucketsMAX = new int[nums.length - 1];
        
        Arrays.fill(bucketsMIN, Integer.MAX_VALUE);
        Arrays.fill(bucketsMAX, Integer.MIN_VALUE);
        
        //put numbers into buckets
        for (int val : nums) {
            if (val == min || val == max) {
                continue;
            }
            int index = (val - min) / gapSize; //index of the right position in the buckets
            bucketsMIN[index] = Math.min(val, bucketsMIN[index]);
            bucketsMAX[index] = Math.max(val, bucketsMAX[index]);
        }
        
        //Scan the buckets for the max gap
        int maxGap = Integer.MIN_VALUE;
        int previous = min;
        
        for (int i = 0; i < nums.length - 1; i++) {
            if (bucketsMIN[i] == Integer.MAX_VALUE && bucketsMAX[i] == Integer.MIN_VALUE) {
                //empty bucket
                continue;
            }
            // min value minus the previous value is the current gap
            maxGap = Math.max(maxGap, bucketsMIN[i] - previous);
            // update previous bucket value
            previous = bucketsMAX[i];
        }
        
        maxGap = Math.max(maxGap, max - previous);//Update the final max value gap
        return maxGap;
    }
}
