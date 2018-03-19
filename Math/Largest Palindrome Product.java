/*
	479. Largest Palindrome Product

	Find the largest palindrome made from the product of two n-digit numbers.

	Since the result could be very large, you should return the largest palindrome mod 1337.

	Example:

	Input: 2

	Output: 987

	Explanation: 99 x 91 = 9009, 9009 % 1337 = 987

	Note:

	The range of n is [1,8].
*/

/*
	Solution: 
		1. 找出范围内product 因子的 upper bound，lower bound，maxNumber
		2. 从 maxNumber的前一半开始用string generate palindrom 去实验是否满足要求
		3. 
*/

class Solution {
    public int largestPalindrome(int n) {
        if (n == 1)
            return 9;
        int upperBound = (int) Math.pow(10, n) - 1;
        int lowerBound = upperBound / 10 + 1;
        long maxNumber = (long) upperBound * (long) upperBound;
        
        int half = (int) (maxNumber / (long) Math.pow(10, n));
        boolean found = false;
        long palindrom = 0;
        
        while (!found) {
            palindrom = createPalindrom(half);
            System.out.println(palindrom);
            for (long i = upperBound; i >= lowerBound; i--) {
                if (i * i < palindrom) // i * i已经是最大值了 如果连这都不满足 就马上退出
                    break;
                if (palindrom % i == 0) { //找到也马上退出，已然是最大值
                    found = true;
                    break;
                }
            }
            half--;
        }
        return (int)(palindrom % 1337);
    }
    
    public long createPalindrom(int num) {
        String s = num + new StringBuilder().append(num).reverse().toString();
        return Long.parseLong(s);
    }
}