/*
	Multiply Strings
	Given two numbers represented as strings, return multiplication of the numbers as a string.
	Note: The numbers can be arbitrarily large and are non-negative.
	Tags: Math, String
*/


    /*
        直接乘会溢出，所以每次都要两个single digit相乘，最大81，不会溢出。
        比如385 * 97, 就是个位=5 * 7，十位=8 * 7 + 5 * 9 ，百位=3 * 7 + 8 * 9 …
        可以每一位用一个Int表示，存在一个int[]里面。
        这个数组最大长度是num1.len + num2.len，比如99 * 99，最大不会超过10000，所以4位就够了。
        这种个位在后面的，不好做（10的0次方，可惜对应位的数组index不是0而是n-1），
        所以干脆先把string reverse了代码就清晰好多。
        最后结果前面的0要清掉。
    */

 /*
 	step1: 逆置字符串为StringBuilder 方便后面插入删除
 	step2: 新建新数数组，长度为2字符串之长
 	step3: A[i] * B[j] 存入C[i + j];
 	step4: 从C[]读出数字，放到res中
 	step5: 判断corner case，首位为0删除，因为前几位可能填不满，因为不进位。比如99 * 99 4位数，但是10 * 10 两位数
 */
public class Solution {
	public String multiply(String num1, String num2) { 
 		num1 = new StringBuilder(num1).reverse().toString();
 		num2 = new StringBuilder(num2).reverse().toString();

 		int[] newNum = new int[num1.length() + num2.length()];
 		for (int i = 0; i < num1.length(); i++) {
 			int a = num1.charAt(i) - '0';
 			for (int j = 0; j < num2.length(); j++) {
 				int b = num2.charAt(j) - '0';
 				newNum[i + j] += a * b;
 			}
 		}
 		StringBuilder res = new StringBuilder();

 		for (int i = 0; i < newNum.length; i++) {
 			int digit = newNum[i] % 10;
 			int carry = newNum[i] / 10;
 			res.insert(0, digit);
 			if (i < newNum.length - 1)
 				newNum[i + 1] += carry; 
 		}

 		while (res.length() > 0 && res.charAt(0) == '0')
 			res.deleteCharAt(0);
 		return res.length() == 0 ? "0" : res.toString();
 	} 
}