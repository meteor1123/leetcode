/*
	Best Time to Buy and Sell Stock II

	1. Say you have an array for which the ith element is the price of a given stock on day i.
	   Design an algorithm to find the maximum profit. 
	2. You may complete as many transactions as you like 
	  (ie, buy one and sell one share of the stock multiple times). 
	3. However, you may not engage in multiple transactions at the same time 
	  (ie, you must sell the stock before you buy again).
*/


// 只要前一天的卖价比后一天的卖价低就直接加上差
public class Solution {
    public int maxProfit(int[] prices) {
		if (prices.length <= 1)
			return 0;
		int maxProfit = 0;
		for (int i = 0; i < prices.length - 1; i++) {
			if (prices[i + 1] - prices[i] > 0)
				maxProfit += prices[i + 1] - prices[i];
		}
		return maxProfit;
	}
}