/*
	Counting Bits
	Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

	Example:
	For num = 5 you should return [0,1,1,2,1,2].

	Follow up:

	It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
	Space complexity should be O(n).
	Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
	Hint:

	You should make use of what you have produced already.
	Divide the numbers in ranges like [2-3], [4-7], [8-15] and so on. And try to generate new range from previous.
	Or does the odd/even status of the number help you in calculating the number of 1s?

 */

/*
	Explaination.
	Take number X for example, 10011001.
	Divide it in 2 parts:
	<1>the last digit ( 1 or 0, which is " i&1 ", equivalent to " i%2 " )
	<2>the other digits ( the number of 1, which is " f[i >> 1] ", equivalent to " f[i/2] " )
*/
public class Solution {
    public int[] countBits(int num) {
        int[] dp = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            dp[i] = dp[i >> 1] + (i & 1);
        }
        return dp;
    }
}