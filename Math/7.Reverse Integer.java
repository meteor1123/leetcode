/*
	Reverse Integer 
	Reverse digits of an integer.
	Example1: x = 123, return 321
	Example2: x = -123, return -321
*/

//Solution2: prefer
public class Solution {
    public int reverse(int x) {
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            if(res > Integer.MAX_VALUE || res < Integer.MIN_VALUE){
                return 0;
            } 
            x = x / 10;
        }
        return (int)res;
    }
}


