/*
	Number of 1 Bits 
	Write a function that takes an unsigned integer and returns the number of ’1' 
	bits it has (also known as the Hamming weight).

	For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, 
	so the function should return 3.

*/


public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            
            // 1 << i 是 i左移，不停左移 这样能够逐个比对数n的每一位的1，如果要是不等于0，则该位上
            if ((n & (1 << i)) != 0)
                count ++;
        }
        return count;
    }
}