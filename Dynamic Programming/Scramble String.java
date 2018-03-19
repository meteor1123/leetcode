/*
	Scramble String 
	Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

	Below is one possible representation of s1 = "great":

		great
	   /    \
	  gr    eat
	 / \    /  \
	g   r  e   at
	           / \
	          a   t

	To scramble the string, we may choose any non-leaf node and swap its two children.
	For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
		rgeat
	   /    \
	  rg    eat
	 / \    /  \
	r   g  e   at
	           / \
	          a   t
	We say that "rgeat" is a scrambled string of "great".
	Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
		rgtae
	   /    \
	  rg    tae
	 / \    /  \
	r   g  ta  e
	       / \
	      t   a
	We say that "rgtae" is a scrambled string of "great".
	Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
*/


/*
	The main idea is:

separate s1 into two parts, namely --s11--, --------s12--------
separate s2 into two parts, namely --s21--, --------s22--------, 
and test the corresponding part (s11 and s21 && s12 and s22) with isScramble.

separate s2 into two parts, namely --------s23--------, --s24--, 
and test the corresponding part (s11 and s24 && s12 and s23) with isScramble.

Note that before testing each sub-part with isScramble, 
anagram is used first to test if the corresponding parts are anagrams. If not, skip directly.
*/
public class Solution {
	//Recursive
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        int[] count = new int[256];
        for (int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i) - '0']++;
            count[s2.charAt(i) - '0']--;
        }
        for (int i = 0; i < s1.length(); i++) {
            if (count[s1.charAt(i) - '0'] != 0) {
                return false;
            }
        }
        for (int i = 1; i < s1.length(); i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) &&
                isScramble(s1.substring(i), s2.substring(i))) {
                    return true;
                }
            if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i)) &&
                isScramble(s1.substring(i), s2.substring(0, s2.length() - i))) {
                    return true;
                }
        }
        return false;
    }
}
    //Dynamic Programming
    /*
		参考了一下Code Ganker的思路
		这其实是一道三维动态规划的题目，我们提出维护量res[i][j][n]，其中i是s1的起始字符，j是s2的起始字符，而n是当前的字符串长度，
			res[i][j][len]表示的是以i和j分别为s1和s2起点的长度为len的字符串是不是互为scramble。

		有了维护量我们接下来看看递推式，也就是怎么根据历史信息来得到res[i][j][len]。判断这个是不是满足，
		其实我们首先是把当前s1[i...i+len-1]字符串劈一刀分成两部分，然后分两种情况：
		  第一种是左边和s2[j...j+len-1]左边部分是不是scramble，以及右边和s2[j...j+len-1]右边部分是不是scramble；
		  第二种情况是左边和s2[j...j+len-1]右边部分是不是scramble，以及右边和s2[j...j+len-1]左边部分是不是scramble。

		如果以上两种情况有一种成立，说明s1[i...i+len-1]和s2[j...j+len-1]是scramble的。
		而对于判断这些左右部分是不是scramble我们是有历史信息的，因为长度小于n的所有情况我们都在前面求解过了（也就是长度是最外层循环）。

		上面说的是劈一刀的情况，对于s1[i...i+len-1]我们有len-1种劈法，在这些劈法中只要有一种成立，那么两个串就是scramble的。
		总结起来递推式是res[i][j][len] = || (res[i][j][k]&&res[i+k][j+k][len-k] || res[i][j+len-k][k]&&res[i+k][j][len-k]) 
		对于所有1<=k<len，也就是对于所有len-1种劈法的结果求或运算。因为信息都是计算过的，
		对于每种劈法只需要常量操作即可完成，因此求解递推式是需要O(len)（因为len-1种劈法）。
		如此总时间复杂度因为是三维动态规划，需要三层循环，加上每一步需要线行时间求解递推式，所以是O(n^4)。
		虽然已经比较高了，但是至少不是指数量级的，动态规划还是有很大有事的，空间复杂度是O(n^3)。代码如下：
    */
public class Solution {
    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        if (s1.length() == 0) {
            return true;
        }
        boolean[][][] check = new boolean[s1.length()][s2.length()][s1.length() + 1];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                check[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }
        for (int len = 2; len <= s1.length(); len++) {
            for (int i = 0; i + len - 1 < s1.length(); i++) {
                for (int j = 0; j + len - 1 < s2.length(); j++) {
                    for (int k = 1; k < len; k++) {
                        check[i][j][len] |= check[i][j][k] && check[i + k][j + k][len - k] ||
                                            check[i][j + len - k][k] && check[i + k][j][len - k];
                    }
                }
            }
        }
        return check[0][0][s1.length()];
    }
}