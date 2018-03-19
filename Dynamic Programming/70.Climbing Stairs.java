/*
	Climbing Stairs
	You are climbing a stair case. It takes n steps to reach to the top.
	Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
	Tag:Dynamic Programming
*/


/*
	Solution:

		假设梯子有n层，那么如何爬到第n层呢，因为每次只能趴爬1或2步，那么爬到第n层的方法要么是从第n-1层一步上来的，要不就是从n-2层2步上来的，所以递推公式非常容易的就得出了：dp[n] = dp[n-1] + dp[n-2]
    	如果梯子有1层或者2层，dp[1] = 1, dp[2] = 2，如果梯子有0层，自然dp[0] = 0
*/


/*
	这道题目是求跑楼梯的可行解法数量。每一步可以爬一格或者两个楼梯，可以发现，递推式是f(n)=f(n-1)+f(n-2)，
	也就是等于前一格的可行数量加上前两格的可行数量。熟悉的朋友可能发现了，这个递归式正是斐波那契数列的定义. 
	这里base case 是f(1)=1, f(2)=2. Fibonacci是典型的动态规划问题，用Recursion和Iteration都可以
*/

//Solution1
public int climbStairs(int n) {
	//create dp array,the length equal n + 1
	int[] dp = new dp[n + 1];

	//corner case
	if (n == 0 || n == 1 || n == 2)
		return n;

	//init dp[]
	dp[0] = 0;
	dp[1] = 1;
	dp[2] = 2;

	for (int i = 3; i <= n; i++) {
		dp[i] = dp[i - 1] + dp[i - 2];
	}
	return dp[n];
}

//Solution2
public class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}

//HashTable
    public int climbStairs(int n) {
        Hashtable<Integer, Integer> table = new Hashtable<Integer, Integer>();
        table.put(2, 2);
        table.put(1, 1);
        table.put(0, 0);
        if (n < 0) return 0;
        return climb(n, table);
    }
    
    public int climb(int n, Hashtable<Integer, Integer> table) {
        if (table.containsKey(n)) return table.get(n);
        else {
            table.put(n, climb(n-1, table) + climb(n-2, table));
            return table.get(n);
        }
    }

    //Recursive
    public int climbStairs(int n) {
        if (n <= 0)
        	return 0;
        if (n == 1)
        	return 1;
        if (n == 2)
        	return 2;
        return climbStairs(n-1) + climbStairs(n-2);
    }