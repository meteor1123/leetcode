/*
	Happy Number
	Write an algorithm to determine if a number is "happy".

	A happy number is a number defined by the following process: Starting with any positive integer, 
	replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 
	(where it will stay), or it loops endlessly in a cycle which does not include 1. 
	Those numbers for which this process ends in 1 are happy numbers.
	Example: 19 is a happy number

	12 + 92 = 82
	82 + 22 = 68
	62 + 82 = 100
	12 + 02 + 02 = 1
	Tags: HashSet, Math
*/


/*
    The idea is to use one hash set to record sum of every digit square of every number occurred. 
    Once the current sum cannot be added to set, return false; once the current sum equals 1, return true;
*/


/*
    1. 题意是给一个数，求每一位数的平方的和，再将这个新数循环继续求平方和，当最后等于1时即是Happy Number
    2. 用HashSet存储每一个新生成的n，因为一旦遇到重复的n则意味着这个是个无限循环，无法再生成1，所以返回false，这就是hashset的妙用
*/
public class Solution {

    //Solution1 best prefer
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        int squareSum = 0;
        int remain = 0;
        while (set.add(n)) {
            squareSum = 0;
            while (n > 0) {
                remain = n % 10;
                squareSum += remain * remain;
                n = n / 10;
            }
            if (squareSum == 1) {
                return true;
            } else {
                n = squareSum;
            }
        }
        return false;
    }

//Solution3
public class Solution {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = n;
        do {
            slow = digitSquareSum(slow);
            fast = digitSquareSum(fast);
            fast = digitSquareSum(fast);
        } while (slow != fast);
        if (slow == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    public int digitSquareSum(int n) {
        int sum = 0;
        int temp;
        while (n > 0) {
            temp = n % 10;
            sum += temp * temp;
            n /= 10;
        }
        return sum;
    }
    
}