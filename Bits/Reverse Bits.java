/*
	Reverse Bits
	Reverse bits of a given 32 bits unsigned integer.

	For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192 (represented in binary as 00111001011110000010100101000000).

	Follow up:
	If this function is called many times, how would you optimize it?

	Related problem: Reverse Integer

	Credits:
	Special thanks to @ts for adding this problem and creating all test cases.
*/

public class Solution {
    // you need treat n as an unsigned value

    /*
    	右移: >> (正数左补0， 负数左补1) 1000 >>1  1100
    	左移: << (正负数皆右补0)        1000 >>>1 0100

    	无符号右移: >>> 无符号右移，正数左补0，负数左补-1
    	没有无符号左移！
    	为什么没有无符号左移？
		因为左移是在后面补0
		而右移是在前面边补1或0
		有无符号是取决于数的前面的第一位是0还是1
		所以右移是会产生到底补1还是0的问题。
		而左移始终是在右边补，不会产生符号问题。
		所以没有必要无符号左移<<<。
		无符号左移<<<和左移<<是一样的概念
    */
     public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res = res + (n & 1);//n & 1是每次只
            n = n >>> 1;//// CATCH: must do unsigned shift,无符号右移，n不断右移，再下一个循环中&1 取n最右边一位的数和res相加
            if (i < 31) {
                res = res << 1;// CATCH: for last digit, don't shift!
            }
        }
        return res;
    }
}



//Solution2 : Optimize,  called many times
/*
        How to optimize if this function is called multiple times? 
        We can divide an int into 4 bytes, and reverse each byte then combine into an int. 
        For each byte, we can use cache to improve performance.

*/
public class Solution {
// cache
    private final Map<Byte, Integer> cache = new HashMap<Byte, Integer>();
    public int reverseBits(int n) {
        byte[] bytes = new byte[4];
        for (int i = 0; i < 4; i++) {// convert int into 4 bytes
            bytes[i] = (byte)((n >>> 8*i) & 0xFF);
        }
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result += reverseByte(bytes[i]); // reverse per byte
            if (i < 3) {
                result <<= 8;
            }
        }
        return result;
    }

    private int reverseByte(byte b) {
        Integer value = cache.get(b); // first look up from cache
        if (value != null) {
            return value;
        }
        value = 0;
        // reverse by bit
        for (int i = 0; i < 8; i++) {
            value += ((b >>> i) & 1);
            if (i < 7) {
                value <<= 1;
            }
        }
        cache.put(b, value);
        return value;
    }
}