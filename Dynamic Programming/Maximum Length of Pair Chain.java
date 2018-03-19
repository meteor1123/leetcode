/*
	646. Maximum Length of Pair Chain

	You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.

	Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.

	Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.

	Example 1:
	Input: [[1,2], [2,3], [3,4]]
	Output: 2
	Explanation: The longest chain is [1,2] -> [3,4]
	Note:
	The number of given pairs will be in the range [1, 1000].
*/

// Earliest Deadline first algorithm (greedy) time: O(nlogn), 
class Solution {
    public int findLongestChain(int[][] pairs) {
        if (pairs == null || pairs.length == 0)
            return 0;
        Arrays.sort(pairs, (a, b) -> (a[1] - b[1]));
        int res = 1;
        int preEnd = pairs[0][1];
        for (int i = 1; i < pairs.length; i++) {
            if (pairs[i][0] > preEnd) {
                res++;
                preEnd = pairs[i][1];
            }
        }
        return res;
    }
}

// time: O(n*n), dp solution, use longest increasing subsequence method.
class Solution {
    public int findLongestChain(int[][] pairs) {
        if (pairs == null || pairs.length == 0)
            return 0;
        Arrays.sort(pairs, (a, b) -> {
           if (a[0] == b[0]) {
               return a[1] - b[1];
           } else {
               return a[0] - b[0];
           }
        });
        int n = pairs.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[i][0] > pairs[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[n - 1];
    }
}