/*
	A + B Problem
	Write a function that add two numbers A and B. You should not use + or any arithmetic operators.

	Have you met this question in a real interview? Yes
	Example
	Given a=1 and b=2 return 3

	Note
	There is no need to read data from standard input stream. Both parameters are given in function aplusb, you job is to calculate the sum and return it.

	Challenge
	Of course you can just return a + b to get accepted. But Can you challenge not do it like that?

	Clarification
	Are a and b both 32-bit integers?

	Yes.
	Can I use bit operation?

	Sure you can.
 */

/*
	位运算实现整数加法本质就是用二进制进行运算。
	其主要用了两个基本表达式：
	x^y //执行加法，不考虑进位。
	(x&y)<<1 //进位操作
	令x=x^y ；y=(x&y)<<1 进行迭代，每迭代一次进位操作右面就多一位0，最多需要“加数二进制位长度”次迭代就没有进位了，此时x^y的值就是结果。

	我们来做个3位数的加法：
	101+011=1000 //正常加法
	位运算加法：
	（1） 101 ^ 011 = 110
	(101 & 011)<<1 = 010
	（2） 110 ^ 010 = 100
	(110 & 010)<<1 = 100
	（3） 100 ^ 100 = 000
	(100 & 100)<<1 = 1000
	此时进行相加操作就没有进位了，即000 ^ 1000=1000即是最后结果。
 */


class Solution {
    /*
     * param a: The first integer
     * param b: The second integer
     * return: The sum of a and b
     */
    public int aplusb(int a, int b) {
        // write your code here, try to do it without arithmetic operators.
        while (b != 0) {
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }
        return a;
    }
};

