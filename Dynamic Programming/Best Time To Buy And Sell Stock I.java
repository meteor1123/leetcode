/*
	Best Time to Buy and Sell Stock 1
	1.Say you have an array for which the ith element is the price of a given stock on day i.
	2.If you were only permitted to complete at most one transaction
	  (ie, buy one and sell one share of the stock), 
	3;design an algorithm to find the maximum profit.

	Tags: Array, DP
*/

/*
	Solution1:

	1. 这道题只让做一次transaction，那么就需要找到价格最低的时候买，价格最高的时候卖（买价的日期早于卖价的日期）。
	   从而转化为在最便宜的时候买入，卖价与买价最高的卖出价最大时，就是我们要得到的结果。

	2. 因为我们需要买价日期早于卖价日期，所以不用担心后面有一天价格特别低，而之前有一天价格特别高而错过了（这样操作是错误的）。
	   所以，只许一次遍历数组，维护一个最小买价，和一个最大利润(保证了买在卖前面）即可。
*/
public class Solution {

    public int maxProfit(int[] prices) {
        if (prices.length <= 0)
            return 0;
    	int minBuyPrice = prices[0];
    	int maxProfit = 0;
    	//只需设置一个最小买入价，和一个最大利润差价就行
    	for (int i = 1; i < prices.length; i++) {
    		if (prices[i] < minBuyPrice) 
    			minBuyPrice = prices[i];
    		if (prices[i] - minBuyPrice > maxProfit)
    			maxProfit = prices[i] - minBuyPrice;
    	}
    	return maxProfit;
    }
    //Math solution
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int maxProfit = 0;
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(prices[i] - minPrice, maxProfit);
        }
        return maxProfit;
    }

    //DP
        /*
        思路是维护两个变量，
        global is the max profit so far一个是到目前为止最好的交易，
        and local is the max profit sell in today另一个是在今天卖出的最佳交易（也就是局部最优）。
        递推式是local[i+1] = max(local[i]+prices[i+1]-prices[i], prices[i+1]-prices[i]),
               global[i+1] = max(local[i+1],global[i])。这样一次扫描就可以得到结果，时间复杂度是O(n)。而空间只需要两个变量，即O(1)
    */
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0)
            return 0;
        int local = 0;
        int global = 0;
        for(int i = 0;i < prices.length - 1; i++) {
            local = Math.max(local + prices[i + 1] - prices[i], prices[i + 1] - prices[i]);
            global = Math.max(local, global);
        }
        return global;
    }
}