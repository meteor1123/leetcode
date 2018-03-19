/*
	Bulb Switcher II

	There is a room with n lights which are turned on initially and 4 buttons on the wall. After performing exactly m unknown operations towards buttons, you need to return how many different kinds of status of the n lights could be.

	Suppose n lights are labeled as number [1, 2, 3 ..., n], function of these 4 buttons are given below:

	Flip all the lights.
	Flip lights with even numbers.
	Flip lights with odd numbers.
	Flip lights with (3k + 1) numbers, k = 0, 1, 2, ...
	Example 1:
	Input: n = 1, m = 1.
	Output: 2
	Explanation: Status can be: [on], [off]
	Example 2:
	Input: n = 2, m = 1.
	Output: 3
	Explanation: Status can be: [on, off], [off, on], [off, off]
	Example 3:
	Input: n = 3, m = 1.
	Output: 4
	Explanation: Status can be: [off, on, off], [on, off, on], [off, off, off], [off, on, on].
	Note: n and m both fit in range [0, 1000].
*/


/*
	Solution:

	我们只需要考虑当 n<=2 and m < 3 的特殊情形。因为当 n >2 and m >=3, 结果肯定是 8.
		
		The four buttons:

		1. Flip all the lights.
		2. Flip lights with even numbers.
		3. Flip lights with odd numbers.
		4. Flip lights with (3k + 1) numbers, k = 0, 1, 2, ...
		如果我们使用了 button 1 和 2, 其效果等同于使用 button 3 。
		类似的..

		1 + 2 --> 3, 
		1 + 3 --> 2, 
		2 + 3 --> 1
		所以，只有 8 种情形。

		All_on, 1, 2, 3, 4, 1+4, 2+4, 3+4

		并且当 n>2 and m>=3 时，我们就能够获得所有的情形。
*/

class Solution {
    public int flipLights(int n, int m) {
        if (m == 0) return 1;
        if (n == 1) return 2;
        if (n == 2) return m == 1 ? 3 : 4;
        if (m == 1) return 4;
        if (m == 2) return 7; // 两个操作以后 没有4这个情况 所以是7个
        return 8;
    }
}