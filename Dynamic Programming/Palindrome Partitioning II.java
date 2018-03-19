/*
	Palindrome Partitioning II 
	Given a string s, partition s such that every substring of the partition is a palindrome.

	Return the minimum cuts needed for a palindrome partitioning of s.

	For example, given s = "aab",
	Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
*/

/*

	首先设置dp变量 cuts[len+1]。cuts[i]表示从第i位置到第len位置（包含，即[i, len])的切割数（第len位置为空）。
	初始时，是len-i。比如给的例子aab，cuts[0] = 3, 就是最坏情况每一个字符都得切割：a|a|b|' '。
 	cuts[1] = 2, 即从i=1位置开始，a|b|' '。
 	cuts[2] = 1 b|' '。cuts[3]=0,即第len位置，为空字符，不需要切割。

 

 上面的这个cuts数组是用来帮助算最小cuts的。
	Step1 设置dp变量 cuts[len + 1].cuts[i]，表示从第i到s.length位置的切割数. 初始化时，每个cuts[i] = len - i; 因为这样才是最坏情况，每个字符都要切割。
	Step2 maxtrixs[i][j] 表示字符串[i,j]从第i个位置（包含）到第j个位置(包含)是否是回文。
	Step3 判断是否是回文的方法：
    	step 1. 假如是回文 matrix[i+1][j-1] 是回文，而且s.charAt(i) == s.charAt(j);
    	step 2. i == j ,则为同一个字符
    	step 3. j = i+1 (i,j相邻) 而且s.charAt(i) == s.charAt(j)
	Step4 
    	当字符串[i,j]是回文后，说明从第i个位置到字符串第len位置的最小cut数可以被更新了，
    	那么就是从j+1位置开始到第len位置的最小cut数加上[i,j] | [j+1,len - 1]中间的这一cut。
    	即，Math.min(cuts[i], cuts[j+1]+1) 最后返回cuts[0]-1。把多余加的那个对于第len位置的切割去掉，即为最终结果。
*/

//Solution1: space: O(m * n)
public class Solution {
	public int minCut(String s) {
		if (s == null || s.length() == 0)
			return 0;
		int min = 0;
		int len = s.length();
		//isPalindrome[i][j] means from the substring position i to position j, is palindrome or not
		boolean[][] isPalindrome = new boolean[len][len];
		//cuts[i] mean ths substring from i to lenghth ,the minimum cuts
		int minCuts[] = new int[len + 1];

		//initialize the cuts[i]
		
		//cut[i] means  from i position to the length of String s include how many cuts, that can make minimum palindrome partition
		for (int i = 0; i < len; i++)
			minCuts[i] = len - i;//in the worst case, every character need to cut, 

		//we need to update the state from len - 1 ~ len  to 0 ~ len
		for (int i = len - 1; i >= 0; --i) {
			//j is the right side of the i ,     (i ~ j), (j + 1 ~ len - 1)
			for (int j = i; j < len; ++j) {
				if ((s.charAt(i) == s.charAt(j) && (j - i) <= 1) || 
					(s.charAt(i) == s.charAt(j) && isPalindrome[i + 1][j - 1])) {
					isPalindrome[i][j] = true;
					//  [i,j] | [j+1,len - 1]  因为i-j已经是回文不需要切割，因此最小的从i 到 length 的切割数应该是
					//       中间这里的一割，再加上j + 1 到len - 1的最小切割。
					minCuts[i] = Math.min(minCuts[i], minCuts[j + 1] + 1);
					//                 [i,j] | [j+1,len - 1]
				}
			}
		}
		min = minCuts[0] - 1;//把多余加的那个对于第len位置的切割去掉，即为最终结果
		return min;
	}
}



//Solution2: space: O(n)
public class Solution {
    public int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[n] = -1;// number of cuts for the first k characters
        for (int i = n - 1; i>= 0; i--) {
            for (int a = i, b = i; a >= 0 && b < n && s.charAt(a) == s.charAt(b); a--, b++) {
                dp[a] = Math.min(dp[a], 1 + dp[b + 1]);// even length palindrome
            }
            for (int a = i, b = i + 1; a >= 0 && b < n && s.charAt(a) == s.charAt(b); a--, b++) {
                dp[a] = Math.min(dp[a], 1 + dp[b + 1]);//odd length palindrome

            }
        }
        return dp[0];
    }
}