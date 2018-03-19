/*
 	Reverse Words in a String II
 	跟Reverse Words in a String很类似，但是这里要求in-place，也就是说不需要开辟额外空间。

	[分析]
	该题在LeetCode中假设开头和结尾没有空格，而且单词之间只有一个空格。但其实不需要这些假设也是可以的，就是代码会比较复杂。
	思路就是两步走，第一步就是将整个字符串翻转。然后从头逐步扫描，将每个遇到单词再翻转过来。

	[注意事项]
	1）如果是Java，应该跟面试官指出String是immutable，所以需要用char array来做。
	2）follow-up问题：k-step reverse。也就是在第二部翻转的时候，把k个单词看作一个长单词，进行翻转。
*/
public class Solution {

	/*
		I am good student: step1 : reverse all the array -> tneduts doog ma I
						   step2 : reverse the single word when meet the  " "
								    student doog ma I
								    student good ma I

	*/
	public void reverseWords(char[] s) {
		reverse(s, 0, s.length - 1);
		//i is the every single word start position
		//when j meet ' ', means a single word already found ,so need to reverse i to j,
		//and j + 1 is the next single word's position, so assign to i
		for (int i = 0, j = 0; j < s.length; j++) {
			//once find ' ', means fin the end of previous single word, so reverse
			if (s[j] == ' ') {
				reverse(s, i, j - 1);
				i = j + 1;
			}
			//go to the end word
			if (j == s.length - 1) {
				reverse(s, i, j);
			}
		}
	}

	public void reverse(char[] c, int start, int end) {
		for (int i = start, j = end; i < j; i++, j--) {
			char temp = c[i];
			c[i] = c[j];
			c[j] = temp;
		}
	}
}


//my solution, prefer
public class Solution {
    public void reverseWords(char[] s) {
        if (s == null || s.length <= 1) {
            return;
        }
        reverse(s, 0, s.length - 1);
        int start = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == ' ') {
                reverse(s, start, i - 1);
                start = i + 1;
            }
        }
        reverse(s, start, s.length - 1);
    }
    
    public void reverse(char[] arr, int i, int j) {
        while (i < j) {
            swap(arr, i++, j--);
        }
    }
    
    public void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}


//Solution3:
public class Solution {
	public class Solution {
	    public void reverseWords(char[] s) {
	        reverse(s, 0, s.length - 1);
	        for (int i = 0, j = 0; j < s.length; j++) {
	            if (s[j] == ' ') {
	                reverse(s, i, j - 1);
	                i = j + 1;
	            }
	            if (j == s.length - 1) {
	                reverse(s, i, j);
	            }
	        }
	    }
	    public void reverse(char[] s, int start, int end) {
	        while (start < end) {
	            swap(s, start++, end--);
	        }
	    }
	    public void swap(char[] s, int i, int j) {
	        char temp = s[i];
	        s[i] = s[j];
	        s[j] = temp;
	    }
	}
}