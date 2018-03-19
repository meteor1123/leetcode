/*
	Additive Number

	Additive number is a string whose digits can form additive sequence.

	A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.

	For example:
	"112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.

	1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
	"199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
	1 + 99 = 100, 99 + 100 = 199
	Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

	Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.

	Follow up:
	How would you handle overflow for very large input integers?
*/

public class Solution {
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long a = parse(num.substring(0, i));
                long b = parse(num.substring(i, j));
                if (a == -1 || b == -1) {
                    continue;
                }
                if (dfs(num.substring(j), a, b)) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean dfs(String s, long a, long b) {
        if (s.length() == 0) {
            return true;
        }
        for (int i = 1; i <= s.length(); i++) {
            long c = parse(s.substring(0, i));
            if (c == -1) {
                return false;
            }
            if (c - a == b && dfs(s.substring(i), b, c)) {
                return true;
            }
        }
        return false;
    }
    
    public long parse(String s) {
        if (!s.equals("0") && s.startsWith("0")) {
            return -1;
        }
        long res = Long.valueOf(s);
        return res;
    }
}