/*
	There are n coins with different value in a line. 
	Two players take turns to take one or two coins from left side until there are no more coins left. 
	The player who take the coins with the most value wins.

	Could you please decide the first player will win or lose?
	Example
	Given values array A = [1,2,2], return true.

	Given A = [1,2,4], return false.


 */

/*
	state
		DP[i]表示从i到end能取到的最大value
	function
		当我们走到i时，有两种选择:
			取values[i]
			取values[i] + values[i+1]
		1. 我们取了values[i],对手的选择有 values[i+1]或者values[i+1] + values[i+2] 剩下的最大总value分别为DP[i+2]或DP[i+3], 
		   对手也是理性的所以要让我们得到最小value,所以 value1 = values[i] + min(DP[i+2], DP[i+3])
		2. 我们取了values[i]和values[i+1] 同理 value2 = values[i] + values[i+1] + min(DP[i+3], DP[i+4])
		3. 最后DP[i] = max(value1, value2)
 */

public class Solution {
    public boolean firstWillWin(int[] values) {
        int len = values.length;
        if (len <= 2) {
            return true;
        }
        int[] dp = new int[len];
        dp[len - 1] = values[len - 1];
        dp[len - 2] = values[len - 2] + values[len - 1];
        
        for (int i = len - 3; i >= 0; i--) {
            dp[i] = Math.max(values[i] - dp[i + 1], values[i] + values[i + 1] - dp[i + 2]);
        }
        return dp[0] > 0;
    }
}