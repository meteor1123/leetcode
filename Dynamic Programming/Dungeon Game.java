/*
	Dungeon Game

	The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

	The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

	Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

	In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.


	Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

	For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.

	Notes:

	The knight's health has no upper bound.
	Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
*/
/*
	有mxn个格子，皇后在右下角，勇士在左上角，现在勇士要去救皇后，每步可以往下或往右走，每个格子里的数字表示勇士到该处时要消耗或增加的魔力，（
	负数表示消耗，正数表示增加），不论何时只要勇士的魔力小于等于0，勇士立即死掉。问勇士初始至少要有多少魔力？

	思路：动态规划

    用一个二维数组ans[][]表示到每个格子时，勇士到每一步时至少需要的魔力，如ans[i][j]表示勇士在[i, j]处至少需要ans[i][j]魔力才能到达[m, n]救出皇后。

    技巧：从[m, n]往回遍历到[1, 1]

    动规方程：如果当前位置魔力正且大于等于右边/下边需要的魔力，则该处不需要额外的魔力，否则，勇士到达该处时需有一定的魔力来满足该处和右边/下边需要的魔力。
*/

//Solution 1
public class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
    	if (dungeon == null || dungeon.length == 0)
    		return 0;
    	int row = dungeon.length;
    	int col = dungeon[0].length;
    	//dp[i][j] 代表 在i, j处 至少需要dp[i][j]个生命 才能到达终点
    	int[][] dp = new int[row][col];
    	dp[row - 1][col - 1] = Math.max(0, 0 - dungeon[row - 1][col - 1]);

    	//初始化最后一列
    	for (int i = row - 2; i >= 0; i--)
    		dp[i][col - 1] = Math.max(dp[i + 1][col - 1] - dungeon[i][col - 1], 0);//这里为什么用Max，因为越大代表剩余的生命越多
    	//初始化最后一行
    	for (int i = col - 2; i >= 0; i--)
    		dp[row - 1][i] = Math.max(dp[row - 1][i + 1]) - dungeon[row - 1][i], 0);//这里为什么用Max，因为越大代表剩余的生命越多
		
		//开始动态规划
    	for (int i = row - 2; i >= 0; i--) {
    		for (int j = col - 2; j >= 0; j--) {
    			dp[i][j] = Math.max(Math.min(dp[i][j + 1], dp[i + 1][j]) - dungeon[i][j], 0);//这里为什么用Max，因为越大代表剩余的生命越多
    		}
    	} 
    	return dp[0][0] + 1;
    }
}


//Solution2
public class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0) {
            return 0;
        }
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = Math.max(1, 1 - dungeon[m - 1][n - 1]);
        for (int i = m - 2; i >= 0; i--) {
            dp[i][n - 1] = Math.max(dp[i + 1][n - 1] - dungeon[i][n - 1], 1);
        }
        for (int i = n - 2; i >= 0; i--) {
            dp[m - 1][i] = Math.max(dp[m - 1][i + 1] - dungeon[m - 1][i], 1);
        }
        
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = Math.max(Math.min(dp[i][j + 1], dp[i + 1][j]) - dungeon[i][j], 1);
            }
        }
        return dp[0][0];
    }
}