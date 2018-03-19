/*
	Paint House II
	There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

	The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

	Note:
	All costs are positive integers.

	Follow up:
	Could you solve it in O(nk) runtime?
*/


/*
	方法是用DP做，维护一个n*m的数组res，res[i][j]表示刷到第i个房子时，用第j种颜料的话的最低cost。
	因为对于每一种颜色，都要逐一查看上一个房子里除这个颜色外的所有颜色花费，选出最低，时间O(m^2*n)，空间O(n*m)
*/

//Solution1
public class Solution {
    public int minCostII(int[][] costs) {
    	//res[i][j] means 前i个house都paint了且第i个house paint成 第j种color的最小cost
		//res[i][j] means in front of i houses were painted,and the i house was painted to j color,the minimum cost
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        if (costs.length == 1 && costs[0].length == 1) {
            return costs[0][0];
        }
        int m = costs.length;
        int n = costs[0].length;
        int[][] dp = new int[m + 1][n];
  
        //initialize the minimum cost to MAX_VALUE
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (k != j) {
                    	//in the i - 1 house, we try all the colors k, and the color k do not equals j, to find the minimum cost
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + costs[i - 1][j]);
                    }
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.min(res, dp[m][i]);
        }
        return res;
    }
}


/*
    Follow up自然是改进，使用O(m)的空间和O(nm)的时间。
    提高空间很简单，可以注意到只需要上个房子的结果，因此可以只用一维数组。
    提高时间的方法是这样的：
        在计算n-1个房子的所有颜色cost时，就维护记录cost最小的前两种颜色。
        这样针对第n个房子的每个颜色，不用观察n-1的m-1个颜色，而是只要基于n-1时cost最小的那种颜色计算新的cost就行。
        如果是同一种颜色，就基于cost第二小的颜色。
*/
//Solution2, O(n) space
public class Solution {
    public int minCostII(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        int m = costs.length;
        int n = costs[0].length;
        int min1 = 0, min2 = 0;
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            int t1 = min1;
            int t2 = min2;
            min1 = Integer.MAX_VALUE;
            min2 = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                dp[j] = (dp[j] == t1 ? t2 : t1) + costs[i][j];
                if (min1 <= dp[j]) {
                    min2 = Math.min(dp[j], min2);
                } else {
                    min2 = min1;
                    min1 = dp[j];
                }
            }
        }
        
        return min1;
    }
}

//Solution3, O(1) space, best
public class Solution {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0)
            return 0;
    
        int n = costs.length;
        int k = costs[0].length;
        
        int min1 = -1;
        int min2 = -1;
        
        for (int i = 0; i < n; i++) {
            int last1 = min1;
            int last2 = min2;
            min1 = -1;
            min2 = -1;
            
            for (int j = 0; j < k; j++) {
                if (j != last1) {
                    costs[i][j] += last1 < 0 ? 0 : costs[i - 1][last1];
                } else {
                    costs[i][j] += last2 < 0 ? 0 : costs[i - 1][last2];
                }
                
                if (min1 < 0 || costs[i][j] < costs[i][min1]) {
                    min2 = min1;
                    min1 = j;
                } else if (min2 < 0 || costs[i][j] < costs[i][min2]) {
                    min2 = j;
                }
            }
        }
        return costs[n - 1][min1];
    }
}