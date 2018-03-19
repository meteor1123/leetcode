/*
	Backpack
	Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?

	Have you met this question in a real interview? Yes
	Example
	If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can select [2, 3, 5], 
	so that the max size we can fill this backpack is 10. If the backpack size is 12.
	we can select [2, 3, 7] so that we can fulfill the backpack.

	You function should return the max size we can fill in the given backpack.

	Note
	You can not divide any item into small pieces.

	Challenge
	O(n x m) time and O(m) memory.

	O(n x m) memory is also acceptable if you do not know how to optimize memory.
 */


//Solution1: O(n*m) memory
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        // write your code here
        if (A == null || m < 1) {
            return 0;
        }
        int n = A.length;
        //dp[i][j] 表示前i个物品是否能组合成体积和为j的背包
        boolean[][] dp = new boolean[n + 1][m + 1];
        
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (j < A[i - 1]) {//假如体积和小于物品A[i - 1]则 这件物品有没有都不影响，取决于前i - 1件
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - A[i - 1]];
                }
            }
        }
        
        for (int i = m; i >= 0; i--) {
            if (dp[n][i]) {
                return i;
            }
        }
        return 0;
    }
}

/*
	考虑背包问题的核心——状态转移方程，如何优化此转移方程？原始方案中用到了二维矩阵来保存result，
	注意到result的第i行仅依赖于第i-1行的结果，那么 能否用一维数组来代替这种隐含的关系呢？
	我们在内循环j处递减即可。如此即可避免result[i][S]的值由本轮result[i][S-A[i] ]递推得到。
 */
//Solution2 : O(m) memory
public class Solution {
	public int backPack(int m, int[] A) {
	    if (A == null || m < 1) {
	        return 0;
	    }
	    int n = A.length;
	    boolean[] dp = new boolean[m + 1];
	    dp[0] = true;
	    for (int i = 0; i < n; i++) {
	        for (int j = m; j >= 0; j--) {
	            if (j >= A[i] && dp[j - A[i]]) {
	                dp[j] = true;
	            }
	        }
	    }
	    
	    for (int i = m; i >= 0; i--) {
	        if (dp[i]) {
	            return i;
	        }
	    }
	    return 0;
	}
}