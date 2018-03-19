/*
	Shortest Palindrome
	Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

	For example:
	Given "aacecaaa", return "aaacecaaa".
	Given "abcd", return "dcbabcd".
	Tags: String
*/

/*

	1. 记原始字符串为s，s的反转字符串为rev_s。
	2. 构造字符串l = s + '#' + rev_s，其中'#'为s中不会出现的字符，添加'#'是为了处理输入为空字符串的情形。

	3. 对字符串l执行KMP算法，可以得到“部分匹配数组”p（也称“失败函数”）

	4. 我们只关心p数组的最后一个值p[l.length*()-1]，因为它表明了rev_s与s相互匹配的最大前后缀长度。

	5. 最后只需要将rev_s的前k个字符与原始串s拼接即可，其中k是s的长度len(s)与p[-1]之差。
*/
/*
	We can construct the following string and run KMP algorithm on it: 
	(s) + (some symbol not present in s) + (reversed string)
	1. After running KMP on that string as result we get a vector p with values of a prefix function for each character 
	   (for definition of a prefix function see KMP algorithm description). 

	2. We are only interested in the last value because it shows us the largest suffix of the reversed string 
	   that matches the prefix of the original string.

	3. So basically all we left to do is to add the first k characters of 
	   the reversed string to the original string

	4. where k is a difference between original string size and the prefix function
	   for the last character of a constructed string.
*/


/*
	1. 这里要用到kmp算法的next数组，next数组可以求出一个字符串最大匹配的前后缀长度
	2. 求一个字符串的palindrome，最简单的方法就是 combineStr = s + reverse(s) + , 比如 abcd + dcba, aabb + bbaa 
	3. 这里的问题在于如何取到最短的palindrome
	4. 因此我们可以使用next数组求出combineStr的最长匹配前后缀，
	   将这个匹配的最长前后缀截取丢掉，使用其余的部分reverse(s) + s,
	  （注意要将s的最长匹配前后缀丢掉！之后reverse，再加上s，便是shortest palindrome!)
*/
//Solution1
public class Solution {
	public String shortestPalindrome(String s) {
        if (s.length() <=1 ) {
            return s;
        }
        String cur = s + " " + new StringBuilder(s).reverse().toString();
        int[] next = new int[cur.length()];
        for (int i = 1; i < cur.length(); i++) {
            int curIndex = next[i - 1];
            while (curIndex > 0 && cur.charAt(curIndex) != cur.charAt(i)) {
                curIndex = next[curIndex - 1];
            }
            if (cur.charAt(curIndex) == cur.charAt(i)) {
                next[i] = curIndex + 1;
            }
        }
        return new StringBuilder(s.substring(next[cur.length() -1])).reverse().toString() + s;
    }
}


//传统的 next数组求法，注意这里的next[i] 并不是在这个charAt(i)上的最大匹配长度，而是i之前的子串的最长匹配
//因此在最后的时候，需要将next[i] + 1
//Solution2
public class Solution {
    public String shortestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }
        String cur = s + " " + new StringBuilder(s).reverse().toString();
        int len = cur.length();
        int[] next = new int[len];
        int k = -1;
        int j = 0;
        next[0] = -1;
        while (j < len - 1) {
            if (k == -1 || cur.charAt(k) == cur.charAt(j)) {
                next[++j] = ++k;
            } else {
                k = next[k];
            }
        }
        
        return new StringBuilder(s.substring(next[cur.length() - 1] + 1)).reverse().toString() + s;
    }
}


//Solution3: recursive
public class Solution {
    public String shortestPalindrome(String s) {
        int j = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == s.charAt(j)) {
                j += 1;  //从尾一直比较到头
            }
        }
        if (j == s.length()) {
            return s;
        }
        String suffix = s.substring(j);
        return new StringBuffer(suffix).reverse().toString() + shortestPalindrome(s.substring(0, j)) + suffix;
    }
}

//Solution4: iterative
class Solution {
    public String shortestPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        int end = j;
        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                i = 0;
                end--;
                j = end; // end 是最后一个匹配的palindrome 字符， end + 1 开始需要copy reverse
            }
        }
        return new StringBuilder(s.substring(end + 1)).reverse().toString() + s;
    }
}