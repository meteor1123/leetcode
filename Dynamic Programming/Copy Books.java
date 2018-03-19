/*
	Copy Books 
	Given an array A of integer with size of n( means n books and number of pages of each book) and k people to copy the book. You must distribute the continuous id books to one people to copy. (You can give book A[1],A[2] to one people, but you cannot give book A[1], A[3] to one people, because book A[1] and A[3] is not continuous.) Each person have can copy one page per minute. Return the number of smallest minutes need to copy all the books.

	Have you met this question in a real interview? Yes
	Example
	Given array A = [3,2,4], k = 2.

	Return 5( First person spends 5 minutes to copy book 1 and book 2 and second person spends 4 minutes to copy book 3. )

	Challenge
	Could you do this in O(n*k) time ?
 */

/*
	Given array A = [3,2,4], k = 2.
	Return 5( First person spends 5 minutes to copy book 1 and book 2 and second person spends 4 minutes to copy book 3. )
	很明显地用dp来做。dp[i][j] 代表前i本书由j个工作者完成需要的最小时间。状态转移方程如下：
	dp[i+1][j+1] = Min(Max(dp[i][k], A[k]+...+A[j+1])),  where k from i to j+1
	意思就是每加入一个新worker的时候，从后往前累加他需要copy的book，每次更新总体所需要的时间；而总体需要的时间是之前所有worker分配方案中的最优时间和新worker所需时间两者中的较大值。

 */

public class Solution {
    /**
     * @param pages: an array of integers
     * @param k: an integer
     * @return: an integer
     */
    public int copyBooks(int[] pages, int k) {
        // write your code here
        int[][] dp = new int[k + 1][pages.length + 1];
        
        int sum = 0;
        int max = 0;
        //初始化dp，dp[i][j] = 前j本书由i个人完成所需的最小时间
        for (int j = 1; j <= pages.length; j++) {
            sum += pages[j - 1];
            dp[1][j] = sum;
            max = Math.max(max, pages[j - 1]);
        }
        
        //人数比书的页数多，意味着所需的用时等于耗时最多的那一页
        if (k >= pages.length) {
            return max;
        }
        
        for (int i = 2; i <= k; i++) {
            for (int j = pages.length - 1; j >= i - 1; j--) {
                int cur = pages[j];
                int min = Math.max(pages[j], dp[i - 1][j]);
                dp[i][j + 1] = min;
                for (int l = j - 1; l >= i - 1; l--) {
                    cur += pages[l];
                    int curMin = Math.max(cur, dp[i - 1][l]);
                    if (curMin < min) {
                        dp[i][j + 1] = curMin;
                        min = curMin;
                    }
                }
            }
        }
        return dp[k][pages.length];
    }
}