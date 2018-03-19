/*
	Maximum Subarray III
	Given an array of integers and a number k, find k non-overlapping subarrays which have the largest sum.

	The number in each subarray should be contiguous.

	Return the largest sum.

	Have you met this question in a real interview? Yes
	Example
	Given [-1,4,-2,3,-2,3], k=2, return 8

	Note
	The subarray should contain at least one number
 */

/*
	算是非常难的题目了，我们之前在Maximum Subarray II中的做法如果要generalize到k的解法，需要额外扫和空间，而且代码不优雅，这里我们的解法也是DP，逻辑上可能会有点绕，不过最后的解法会十分的clean，核心代码只有两行。
	跟之前一样这里我们维护两个东西，
	localMax[i][j] : 为进行了i次partition前j个数字的最大subarray，并且最后一个subarray以A[j - 1](A为输入的数组)结尾；
	globalMax[i][j]: 为进行了i次partition前j个数字的最大subarray(最后一个subarray不一定需要以j - 1结尾)。类比之前的DP方程，我们推出新的DP方程：
	
	globalMax[i][j] = max(globalMax[i][j - 1], localMax[i][j]);
	localMax[i][j] = max(globalMax[i - 1][k] + sumFromTo(A[k + 1], A[j])) 其中 0< k < j;
	第一眼看上去对于第二个DP方程我们需要每次构建localMax[i][j]的时候把后面都扫一遍，这样总的复杂度会达到O(n^2)，但是我们有方法把这一步优化到O(n)。
	设想如下例子：
	globalMax[i - 1]: 3, 5, -1, 8, 7
	A[]:              1, 2, 6, -2, 0
	我们可以看到当算完A[2] max(globalMax[i - 1][k] + sumFromTo(A[k + 1], A[j])) = 11。
	当我们算到A[3]的时候，如果我们不考虑新加的globalMax[i - 1][2] + A[3]的组合, 
	之前的所有组合（globalMax[i - 1][0] + sumFromTo(A[1], A[2]), globalMax[i - 1][1] + sumFromTo(A[2], A[2])）都只需要再加一个A[3]就是新的globalMax[i - 1][k] + sumFromTo(A[k + 1], A[j])的值，
	所以之前的最大的值，到新的还是原来所组合的最大值，只需要和新加的比一下就可以了，所以这个值我们从最左边向右边扫一遍一直维护是可行的，
	并且只需要维护一个变量localMax而不是数组。时间复杂度O(k * n)，空间复杂度O(k * n)，空间应该可以维护一个滚动数组来优化到n不过这道题的逻辑比较复杂，
	为了维护逻辑的清晰性，我们不优化空间。代码如下：
 */


//Solution1:BEST
public class Solution {
    /**
     * @param nums: A list of integers
     * @param k: An integer denote to find k non-overlapping subarrays
     * @return: An integer denote the sum of max k non-overlapping subarrays
     */
    public int maxSubArray(int[] nums, int k) {
        // write your code here
        if (nums == null) {
            return 0;
        }
        int len = nums.length;
        if (len < k) {
            return 0;
        }
        //k partitions of array with length len
        int[][] globalMax = new int[k + 1][len + 1];
        
        for (int i = 1; i <= k; i++) {
            int localMax = Integer.MIN_VALUE;
            
            for (int j = i - 1; j < len; j++) {
                localMax = Math.max(localMax, globalMax[i - 1][j]) + nums[j];
                if (j == i - 1) {
                    globalMax[i][j + 1] = localMax;
                } else {
                    globalMax[i][j + 1] = Math.max(globalMax[i][j], localMax);
                }
            }
        }
        return globalMax[k][len];
    }

}


/*
	Using sums[i][j] to denote the maximum total sum of choosing i subarrays from the first j numbers.

	We can update by sums[i][j] = max(sums[i - 1][t] + maxSubarraySum(nums[t+1...j])), which means using the first t numbers to choose i - 1 subarrays,
	 and plus the maximum sum from remaining numbers(nums[t]...nums[j-1]). We want to try all possible split point, so t ranges from nums[i-1] to nums[j-1].

	In the most inner loop, it will try to examine the max sum from the subarray nums[t] to nums[j-1], where t goes from j-1 down to i-1. 
	We can compute for each t the maximum sum. However, if we scan from right to left instead of left to right, we only needs to update the maximum value incrementally. For example, t's range is [1..5], so at first, the max sum is pick from [5], then it's picked from [4...5], ..., finally picked from [1...5]. By scanning from right to left, we are able to include the new number into computing on the fly.
 */

//Solution2:
public class Solution {
  public int maxSubArray(ArrayList<Integer> nums, int k) {
    if (nums == null || nums.size() < k) {
      return 0;
    }
    int len = nums.size();
    int[][] sums = new int[k + 1][len + 1];
    for (int i = 1; i <= k; i++) {
      for (int j = i; j <= len; j++) { // at least need one number in each subarray
        sums[i][j] = Integer.MIN_VALUE;
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int t = j - 1; t >= i - 1; t--) {
          sum = Math.max(nums.get(t), sum + nums.get(t));
          max = Math.max(max, sum);
          sums[i][j] = Math.max(sums[i][j], sums[i - 1][t] + max);
        }
      }
    }
    return sums[k][len];
  }
}
