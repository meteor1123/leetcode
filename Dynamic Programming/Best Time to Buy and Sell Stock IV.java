/*
	Best Time to Buy and Sell Stock IV
	Say you have an array for which the ith element is the price of a given stock on day i.

	Design an algorithm to find the maximum profit. You may complete at most k transactions.

	Note:
	You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
	Tags: DP
*/

public class Solution {
	 /*
    	dp[i, j] represents the max profit up until prices[j] using at most i transactions.

  		dp[i, j] = max(dp[i, j-1], prices[j] - prices[k] + dp[i-1, k])       { k in range of [0, j-1] }
           		 = max(dp[i, j-1], prices[j] + max(dp[i-1, k] - prices[k]))
 		dp[0, j] = 0; 0 transactions makes 0 profit
  		dp[i, 0] = 0; if there is only one price data point you can't make any transaction.
 	*/
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n <= 1)
            return 0;
    
        //if k >= n/2, then you can make maximum number of transactions.
        if (k >=  n/2) {
            int maxPro = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1])
                    maxPro += prices[i] - prices[i - 1];
            }
            return maxPro;
        }
      
        int[][] dp = new int[k + 1][n];
        for (int i = 1; i <= k; i++) {
            int localMax = dp[i - 1][0] - prices[0];
            for (int j = 1; j < n; j++) {
                //localMax means the maximum profit of just doing at most i-1 transactions, 
                //using at most first j-1 prices, and buying the stock at price[j]  this is used for the next loop. 
                dp[i][j] = Math.max(dp[i][j - 1],  prices[j] + localMax);
                localMax = Math.max(localMax, dp[i - 1][j] - prices[j]);
            }
        }
        return dp[k][n - 1];
    }
}

//Solution2
public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (k >= len / 2) {
            return quickSolve(prices);
        }
        int[][] dp = new int[k + 1][len];
        for (int i = 1; i <= k; i++) {
            int tempMax = -prices[0];
            for (int j = 1; j < len; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + tempMax);
                //tmpMax means the maximum profit of just doing at most i-1 transactions, 
                //using at most first j-1 prices, and buying the stock at price[j]  this is used for the next loop.
                tempMax = Math.max(tempMax, dp[i - 1][j - 1] - prices[j]);
            }
        }
        return dp[k][len - 1];
    }
    
    private int quickSolve(int[] prices) {
        int len = prices.length;
        int profit = 0;
        for (int i = 1; i < len; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }
}

/*
	The basic idea is to create two tables. hold and unhold.

	hold[i][j] means the maximum profit with at most j transaction for 0 to i-th day. 
	hold means you have a stock in your hand.

	unhold[i][j] means the maximum profit with at most j transaction for 0 to i-th day. 
	unhold means you don't have a stock in your hand.

	The equation is
		hold[i][j] = Math.max(unhold[i-1][j]-prices[i],hold[i-1][j]);
	unhold[i][j] = Math.max(hold[i-1][j-1]+prices[i],unhold[i-1][j]);

	when you sell your stock this is a transaction but when you buy a stock, 
	it is not considered as a full transaction. so this is why the two equation look a little different.

	And we have to initiate hold table when k = 0.

	When the situation is you can not buy a new stock at the same day when you sell it. For example you can only buy a new stock after one day you sell it. The same idea. Another situation is when you have to pay a transaction fee for each transaction, just make a modification when you sell it, So just change the unhold equation a little.
*/
public class Solution {
    //hold[i][k]  ith day k transaction have stock and maximum profit
    //unhold[i][k] ith day k transaction do not have stock at hand and maximum profit
    public int maxProfit(int k, int[] prices) {
        if(k > prices.length / 2) 
        	return maxP(prices);
        int[][] hold = new int[prices.length][k + 1];
        int[][] unhold = new int[prices.length][k + 1];
        hold[0][0] = -prices[0];
        for(int i = 1; i < prices.length; i++) 
        	hold[i][0] = Math.max(hold[i-1][0], -prices[i]);
        for(int j = 1; j <= k;j++) 
        	hold[0][j] = -prices[0];
        for(int i = 1; i < prices.length; i++){
            for(int j = 1; j <= k;j++){
                hold[i][j] = Math.max(unhold[i-1][j] - prices[i], hold[i-1][j]);
                unhold[i][j] = Math.max(hold[i-1][j-1] + prices[i], unhold[i-1][j]);
            }
        }
        return Math.max(hold[prices.length - 1][k], unhold[prices.length - 1][k]);
    }
    public int maxP(int[] prices){
        int res = 0;
        for(int i = 0; i < prices.length;i++){
            if(i > 0 && prices[i] > prices[i-1]){
                res += prices[i] - prices[i-1];
            }
        }
        return res;
    }
}
