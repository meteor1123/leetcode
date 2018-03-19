/*
	371. Sum of Two Integers

	Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

	Example:
	Given a = 1 and b = 2, return 3.

	Credits:
	Special thanks to @fujiaozhu for adding this problem and creating all test cases.
*/


// Iterative
class Solution {
	public int getSum(int a, int b) {
		if (a == 0) 
			return b;
		if (b == 0) 
			return a;

		while (b != 0) {
			int carry = a & b;
			a = a ^ b;
			b = carry << 1;
		}
		
		return a;
	}
}


//Follow up: Recursive
class Solution {
    public int getSum(int a, int b) {
        if (b == 0) //没有进位的时候完成运算
            return a;
        int sum = a ^ b; //完成第一步加法的运算
        int carry = (a & b) << 1; //完成第二步进位并且左移运算
        return getSum(sum, carry);
    }
}




//Follow up: getSubtract
public int getSubtract(int a, int b) {
	while (b != 0) {
		int borrow = (~a) & b;
		a = a ^ b;
		b = borrow << 1;
	}
	
	return a;
}

// Recursive
public int getSubtract(int a, int b) {
	return (b == 0) ? a : getSubtract(a ^ b, (~a & b) << 1);
}

// Get negative number
public int negate(int x) {
	return ~x + 1;
}