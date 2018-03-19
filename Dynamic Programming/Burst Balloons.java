/*
	Burst Balloons

	Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. 
    You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. 
    Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

	Find the maximum coins you can collect by bursting the balloons wisely.

	Note: 
	(1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
	(2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

	Example:

	Given [3, 1, 5, 8]

	Return 167

	    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
	   coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167

*/
//Solution1: DP
public class Solution {
    public int maxCoins(int[] iNums) {
        int[] nums = new int[iNums.length + 2];
        int n = 1;
        for (int x : iNums) {
            if (x > 0) {
                nums[n++] = x;
            }
        }
        nums[0] = nums[n++] = 1;


        int[][] dp = new int[n][n];
        for (int k = 2; k < n; ++k)
            for (int left = 0; left < n - k; ++left) {
                int right = left + k;
                for (int i = left + 1; i < right; ++i)
                    dp[left][right] = Math.max(dp[left][right], nums[left] * nums[i] * nums[right] + dp[left][i] + dp[i][right]);
            }
        return dp[0][n - 1];
    }
}

//Solution2: Divide and Conquer 记忆化搜索
public class Solution {
    public int maxCoins(int[] iNums) {
        int[] nums = new int[iNums.length + 2];
        int n = 1;
        for (int x : iNums) {
            if (x > 0) {
                nums[n++] = x;
            }
        }
        nums[0] = nums[n++] = 1;
        int[][] memo = new int[n][n];
        return burst(memo, nums, 0, n - 1);
    }
    public int burst(int[][] memo, int[] nums, int left, int right) {
        if (left + 1 == right) {
            return 0;
        }
        if (memo[left][right] > 0) {
            return memo[left][right];
        }
        int ans = 0;
        for (int i = left + 1; i < right; i++) {
            ans = Math.max(ans, nums[left] * nums[i] * nums[right] + burst(memo, nums, left, i) + burst(memo, nums, i, right));
        }
        memo[left][right] = ans;
        return ans;
    }
}

//Solution3 记忆化搜索:
/*
    给定n个气球。每次你可以打破一个，打破第i个，那么你会获得nums[left] * nums[i] * nums[right]个积分。 （nums[-1] = nums[n] = 1）求你可以获得的最大积分数
    我们可以想象：最后的剩下一个气球为i的时候，可以获得的分数为：nums[-1]*nums[i]*nums[n].

    那么介于i,j之间的x，有： dp[i][j] = max(dp[i][j], dp[i][x – 1] + nums[i – 1] * nums[x] * nums[j + 1] + dp[x + 1][j]);

    这里，为了方便代码书写，我在首尾插入了两个1，所以答案是 dp[1][n] (n为原来的长度)
 */

public class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        for (int i = 0; i < n; i++) {
            arr[i + 1] = nums[i];
        }
        arr[0] = arr[n + 1] = 1;
        int[][] dp = new int[n + 2][n + 2];
        return DP(1, n, arr, dp);
    }
    
    public int DP(int i, int j, int[] nums, int[][] dp) {
        if (dp[i][j] > 0) {
            return dp[i][j];
        }
        for (int x = i; x <= j; x++) {
            dp[i][j] = Math.max(dp[i][j], DP(i, x - 1, nums, dp) + nums[i - 1] * nums[x] * nums[j + 1] + DP(x + 1, j, nums, dp));
        }
        return dp[i][j];
    }
}