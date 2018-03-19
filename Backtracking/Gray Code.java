/*
	Gray Code 
	The gray code is a binary numeral system where two successive values differ in only one bit.

	Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

	For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

	00 - 0
	01 - 1
	11 - 3
	10 - 2

	Note:
	For a given n, a gray code sequence is not uniquely defined.

	For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

	For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.

	Tags: Backtracking
*/


/*
    解这道题的关键在于，当n时的 gray code == n - 1时的 gray code 从后往前遍历 + 1 << n, 以及原先的n - 1 的code 不需要改变，即可
*/
public class Solution {
    //iterative
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        
        for (int i = 0; i < n; i++) {
            int inc = 1 << i; //左移的位数
            for (int j = res.size() - 1; j >= 0; j--) {
                res.add(res.get(j) + inc); //左移的位数加上原先的res从后往前，就是新的一半的 gray code
            }
        }
        return res;
    }

    //Recursive
    public List<Integer> grayCode(int n) {
        if (n == 0) {
            List<Integer> res = new ArrayList<>();
            res.add(0);
            return res;
        }
        List<Integer> res = grayCode(n - 1);
        int inc = 1 << n - 1;
        for (int i = res.size() - 1; i >= 0; i--) {
            res.add(res.get(i) + inc);
        }
        return res;
        
    }
}