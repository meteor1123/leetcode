/*
	Longest Palindromic Substring
	Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
*/

//http://www.cnblogs.com/EdwardLiu/p/3792493.html
//http://www.programcreek.com/2013/12/leetcode-solution-of-longest-palindromic-substring-java/


//Best manacher's Algorithm   O(n) O(n)
//https://www.felix021.com/blog/read.php?2040
//http://articles.leetcode.com/2011/11/longest-palindromic-substring-part-ii.html
public class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        s = preProcess(s);
        int[] p = new int[s.length()];
        int mid = 0;
        int max = 0;
        for (int i = 1; i < s.length() - 1; i++) {
            p[i] = i < max ? Math.min(p[mid - (i - mid)], max - i) : 0; 
            while (s.charAt(i - p[i] - 1) == s.charAt(i + p[i] + 1)) {
                p[i]++;
            }
            if (i + p[i] > max) {
                mid = i;
                max = p[i];
            }
        }
        int maxLen = 0;
        mid = 0;
        for (int i = 0; i < s.length(); i++) {
            if (p[i] > maxLen) {
                mid = i;
                maxLen = p[i];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = mid - maxLen; i <= mid + maxLen; i++) {
            if (s.charAt(i) != '#') {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
    public String preProcess(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append('~');
        for (int i = 0; i < s.length(); i++) {
            sb.append('#');
            sb.append(s.charAt(i));
        }
        sb.append("#$");
        return sb.toString();
    }
}


//Solution1 Best, time: O(n*n), O(1) space
/*
    本题用到中心回探法，什么是中心回探？
    基本思路是对于每个子串的中心
        1)可以是一个字符
        2)或者是两个字符的间隙，
        3)比如串abc,中心可以是a,b,c,或者是ab的间隙，bc的间隙）往两边同时进行扫描，直到不是回文串为止。
        4)假设字符串的长度为n,那么中心的个数为2*n-1(字符作为中心有n个，间隙有n-1个）。
        5)对于每个中心往两边扫描的复杂度为O(n),所以时间复杂度为O((2*n-1)*n)=O(n^2),空间复杂度为O(1)
    
    extendPalindrome方法的作用是从i往0回前探， j往s.length() - 1探，遇到不palind时，跳出循环计算max Length
    注意跳出循环的时候 j 和 i都处于失配的位置，因此应该 len = j - i + 1 - 2 = j - i - 1
*/
public class Solution {
    private int left, maxLen;
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        
        for (int i = 0; i < s.length(); i++) {
            extendPalindrome(s, i, i);
            extendPalindrome(s, i, i + 1);
        }
        return s.substring(left, left + maxLen);
    }
    public void extendPalindrome(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        if (maxLen < j - i - 1) { 
            left = i + 1;
            maxLen = j - i - 1;
        }
    }
}

/*
    Solution2
    Let s be the input string, i and j are two indices of the string.

    Define a 2-dimension array "table" and 
    let table[i][j] denote whether substring from i to j is palindrome.

    Start condition:
    table[i][i] == true;
    table[i][i+1] == true  => s.charAt(i) == s.charAt(i+1) 


    Changing condition:
    table[i][j] == true => table[i+1][j-1] == true && s.charAt(i) == s.charAt(j)
*/

/*
    dp[i][j] 表示substring(i, j + 1) 是否为palindrome ，----> 0....i....j....s.length() - 1
    因此我们可以很明显的看出来，i从末尾开始往前递进， j从i往后开始递进， 到最后就是 0 = i .....j = s.length() - 1 这个区间段是否
*/
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        //isPalin[i][j], i and j are two indices of the string, 
        //denote whether substring from i to j is palindrome;
        //isPalin[i][i] is always palindrome, since s.charAt(i) == s.charAt(i)
        boolean[][] isPalind = new boolean[s.length()][s.length()];
        String res = "";
        int maxLen = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                // j - i <= 2 --> aba or aa 肯定palindrome
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || isPalind[i + 1][j - 1])) {
                    isPalind[i][j] = true;
                    if (maxLen < j - i + 1) {
                        maxLen = j - i + 1;
                        res = s.substring(i, j + 1);
                    }
                }
            }
        }
        return res;
    }
}
    
//Solution3: Brute Force by myself
public class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        
        int maxLen = 0;
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (isPalind(s, i, j)) {
                    if (j - i + 1 > maxLen) {
                        maxLen = j - i + 1;
                        left = i;
                        right = j;
                    }
                }
            }
        }
        return s.substring(left, right + 1);
    }
    
    public boolean isPalind(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}



