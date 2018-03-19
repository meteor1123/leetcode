/*
	Power Of Three
	Given an integer, write a function to determine if it is a power of three.

	Follow up:
	Could you do it without using any loop / recursion?
 */
//问一个数是不是三的幂
//Iterative
public class Solution {
	public boolean isPowerOfThree(int n) {
        if (n > 1) {
            while (n % 3 == 0) {
                n = n / 3;
            }
        }
        return n == 1;
   }
}
//Recursive 
public boolean isPowerOfThree(int n) {
	return n > 0 && (n == 1 || (n % 3 == 0 && isPowerOfThree(n/3)));
}
//Math,Best		 
public boolean isPowerOfThree(int n) {
    return (Math.log10(n) / Math.log10(3)) % 1 == 0;
}