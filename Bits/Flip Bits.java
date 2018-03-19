/*
 	Flip Bits
	Determine the number of bits required to flip if you want to convert integer n to integer m.

	Example
	Given n = 31 (11111), m = 14 (01110), return 2.

	Note
	Both n and m are 32-bit integers.
 */
public class Solution {
    public static int bitSwapRequired(int a, int b) {
        // write your code here
        int c = a ^ b;
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if ((c & 1) != 0) {
                res++;
            }
            c = c >> 1;
        }
        return res;
    }
}