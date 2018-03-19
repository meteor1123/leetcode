/*
	Plus One
	Given a non-negative number represented as an array of digits, plus one to the number.
	The digits are stored such that the most significant digit is at the head of the list.
	Tags: Math, Array
*/

public class Solution {
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9) { //加1的时候，如果该位为9，则设为0，往前进位，进位到不能进位的时候，就返回结果
                digits[i] = 0;
            } else {
                digits[i]++;
                return digits;
            }
        }
        int[] res = new int[digits.length + 1];
        //这种情况是比如 9999 + 1, 会成为10000，这时候直接创建一个新数组，设[0] = 1,直接返回
        res[0] = 1;
        return res;
    }
}