/*
	Strobogrammatic Number II
		A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

	Find all strobogrammatic numbers that are of length = n.

	For example,
	Given n = 2, return ["11","69","88","96"].

	Hint:

	Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.
	Tags: Math, Recursive
*/

public class Solution {
    public List<String> findStrobogrammatic(int n) {
        return helper(n, n);
    }
    List<String> helper(int n, int m) {
        if (n == 0) {
            // ArrayList<String> tempRes = new ArrayList<String>();
            // tempRes.add("");
            return new ArrayList<String>(Arrays.asList(""));
        }
        if (n == 1) {
            // ArrayList<String> tempRes = new ArrayList<String>();
            // tempRes.add("0");
            // tempRes.add("1");
            // tempRes.add("8");
            return new ArrayList<String>(Arrays.asList("0", "1", "8"));//asList 的只能读，不能改
        }
        List<String> list = helper(n - 2, m);//why n - 2? since every time we add two number, one from head,the other from end
        List<String> res = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            // n == m, 就是最外层的时候，不能在最外围加上0， 会导致出现010这样的无效数字
            if (n != m) {
                res.add("0" + s + "0");
            }
            res.add("1" + s + "1");
            res.add("6" + s + "9");
            res.add("8" + s + "8");
            res.add("9" + s + "6");
        }
        return res;
    }
}