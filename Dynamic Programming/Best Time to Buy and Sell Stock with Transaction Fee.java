/*
	714. Best Time to Buy and Sell Stock with Transaction Fee
	Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee.

	You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)

	Return the maximum profit you can make.

	Example 1:
	Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
	Output: 8
	Explanation: The maximum profit can be achieved by:
	Buying at prices[0] = 1
	Selling at prices[3] = 8
	Buying at prices[4] = 4
	Selling at prices[5] = 9
	The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
	Note:

	0 < prices.length <= 50000.
	0 < prices[i] < 50000.
	0 <= fee < 50000.
*/

/*
	sold[i]表示第i天卖掉股票此时的最大利润，
	hold[i]表示第i天保留手里的股票此时的最大利润。

	那么我们来分析递推公式:
		1. 在第i天如果我们要卖掉手中的股票，那么此时我们的总利润应该是前一天手里有股票的利润，加上此时的卖出价格，减去交易费得到的利润总值，跟前一天卖出的利润相比，取其中较大值，如果前一天卖出的利润较大，那么我们就前一天卖了，不留到今天了。
		2. 在第i天如果我们不卖的利润，就是昨天股票卖了的利润然后今天再买入股票，得减去今天的价格，得到的值和昨天股票保留时的利润相比，取其中的较大值，如果昨天保留股票的利润大，那么我们就继续保留到今天，所以递推时可以得到：
		sold[i] = max(sold[i - 1], hold[i - 1] + prices[i] - fee);
		hold[i] = max(hold[i - 1], sold[i - 1] - prices[i]);
*/

// O(n) space.
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int[] sold = new int[prices.length];
        int[] hold = new int[prices.length];
        hold[0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            sold[i] = Math.max(sold[i - 1], hold[i - 1] + prices[i] - fee);
            hold[i] = Math.max(hold[i - 1], sold[i - 1] - prices[i]);
        }
        return sold[prices.length - 1];
    }
}

// prefer, improved version without extra space
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int sold = 0;
        int hold = -prices[0];
        
        for (int price : prices) {
            int temp = sold;
            sold = Math.max(sold, hold + price - fee);
            hold = Math.max(hold, temp - price);
        }
        return sold;
    }
}