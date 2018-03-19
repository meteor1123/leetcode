/*
	Palindrome Number
	Determine whether an integer is a palindrome. Do this without extra space.
	click to show spoilers.

	Some hints:
	Could negative integers be palindromes? (ie, -1)

	If you are thinking of converting the integer to string, note the restriction of using extra space.

	You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case?

	There is a more generic way of solving this problem.
	Tags: Math
*/
public class solution {
	public boolean isPalindrome(int x) {
		if (x < 0)
			return false;
		int div = 1;
		while (x / div >= 10) 
			div = div * 10;// x / div得到首位数字 
		while (x != 0) {
			int left = x / div; //除以div，得到首位数字
			int right = x % 10; //% 10得到最后一位数字。
			if (left != right) 
				return false;
			x = (x % div) / 10; // x % div 得到去掉头的数字，除以10得到去掉尾的数字

			div = div / 100; //因为x已经减少2位，所以div也要减少2位就是/100
		}
		return true;
	}
}

//Solution2
public class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int y = x;
        int res = 0;
        while (y != 0) {
            res = res * 10 + y % 10;
            y = y / 10;
        }
        return x == res;
    }
}