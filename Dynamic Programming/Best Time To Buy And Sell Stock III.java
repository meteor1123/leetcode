/*
	Best Time to Buy and Sell Stock III
	1. Say you have an array for which the ith element is the price of a given stock on day i.
	   Design an algorithm to find the maximum profit. 
	2. You may complete at most two transactions.

	Note:
	You may not engage in multiple transactions at the same time 
	(ie, you must sell the stock before you buy again).
*/


/*
	根据题目要求，最多进行两次买卖股票，而且手中不能有2只股票，就是不能连续两次买入操作。

	1. 所以，两次交易必须是分布在2个区间内，也就是动作为：买入卖出，买入卖出。
	2. 进而，我们可以划分为2个区间[0,i]和[i,len-1]，i可以取0~len-1。
	3. 那么两次买卖的最大利润为：在两个区间的最大利益和的最大利润。
	4. 一次划分的最大利益为：Profit[i] = MaxProfit(区间[0,i]) + MaxProfit(区间[i,len-1]);
	5. 最终的最大利润为：MaxProfit(Profit[0], Profit[1], Profit[2], ... , Profit[len-1])。
*/


//Solution1:  prefer
public class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int m = prices.length;
        int[] left = new int[m];
        int[] right = new int[m];
        
        //维护一个最小买入价
        int minBuy = prices[0];
        for (int i = 1; i < m; i++) {
            left[i] = Math.max(left[i - 1], prices[i] - minBuy);
            minBuy = Math.min(minBuy, prices[i]);
        }
        
        //维护一个最大卖价
        int maxSell = prices[m - 1];
        for (int i = m - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], maxSell - prices[i]);
            maxSell = Math.max(maxSell, prices[i]);
        }

        // 算最大profit
        int profit = 0;
        for (int i = 0; i < m - 1; i++) {
            profit = Math.max(profit, left[i] + right[i]);
        }
        return profit;
    }
}

//Solution2:
public class Solution {
    public int maxProfit(int[] prices) { 
		if (prices.length <= 1)
			return 0;
		int len = prices.length;
		int[] left = new int[len];
		int[] right = new int[len];
		
		//两次一维动态规划，分别求出 从左和从右边开始的一次买入卖出最大差价
		
		//left[i]表示 从0-i 的最大利润
		int minBuyPrice = prices[0];
		for (int i = 1; i < len; i++) {
		    minBuyPrice = prices[i] < minBuyPrice ? prices[i] : minBuyPrice;
			left[i] = prices[i] - minBuyPrice > left[i - 1] ? prices[i] - minBuyPrice : left[i - 1];
		
		}
        //right[i]表示从 i到length - 1的最大利润
		int maxSellPrice = prices[len - 1];
		for (int i = len - 2; i > 0; i--) {
		    maxSellPrice = prices[i] > maxSellPrice ? prices[i] : maxSellPrice;
			 right[i] = maxSellPrice - prices[i] > right[i + 1] ? maxSellPrice - prices[i] : right[i + 1];
			
		}

		int maxProfit = 0;
		for (int i = 0; i < len; i++) 
			maxProfit = left[i] + right[i] > maxProfit ? left[i] + right[i] : maxProfit;
		return maxProfit;	
	}
}

//Solution3: space:O(1)
public int maxProfit(int[] prices) {
    int oneBuy = Integer.MIN_VALUE;
    int oneBuyOneSell = 0;
    int twoBuy = Integer.MIN_VALUE;
    int twoBuyTwoSell = 0;
    for(int i = 0; i < prices.length; i++){
        oneBuy = Math.max(oneBuy, -prices[i]);//we set prices to negative, so the calculation of profit will be convenient
        oneBuyOneSell = Math.max(oneBuyOneSell, prices[i] + oneBuy);
        twoBuy = Math.max(twoBuy, oneBuyOneSell - prices[i]);//we can buy the second only after first is sold
        twoBuyTwoSell = Math.max(twoBuyTwoSell, twoBuy + prices[i]);
    }

    return Math.max(oneBuyOneSell, twoBuyTwoSell);
}