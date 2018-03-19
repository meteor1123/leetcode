/*
	Bitwise AND of Numbers Range 
	Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

	For example, given the range [5, 7], you should return 4.

	题目大意：
	给定范围[m, n]，其中 0 <= m <= n <= 2147483647，返回范围内所有整数的按位与，包括边界。

	例如，给定范围[5, 7], 你应该返回4。
	 bin     dec
	 101       5
	 110       6
	 111       7
	-------------
	 100       4

*/

/*
	bitwise ：按位与
	由数据范围0 <= m <= n <= 2147483647可知，时间复杂度O（n）及以上的解法是不可接受的。
	因此可以判断此题为数学题。


	101　　110　　111
	5, 6, 7相与后的结果为100，仔细观察我们可以得出，最后的数是该数字范围内所有的数的左边共同1的部分，
	如果上面那个例子不太明显，我们再来看一个范围[26, 30]，它们的二进制如下：

	11010　　11011　　11100　　11101　　11110
	发现了规律后，我们只要写代码找到左边公共1的部分即可，我们可以从建立一个32位都是1的mask
	，然后每次向左移一位，比较m和n是否相同，不同再继续左移一位，直至相同，然后把m和mask相与就是最终结果，代码如下：
*/

public class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        if(m == 0){
            return 0;
        }
        int i = 0;
        while(m != n){
            m >>= 1;
            n >>= 1;
            i++;
        }
        return m << i;
    }
}