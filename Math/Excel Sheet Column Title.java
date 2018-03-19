(/*
	Excel Sheet Column Title 
	Given a positive integer, return its corresponding column title as appear in an Excel sheet.

	For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
*/

/*
	巧用 % ，'A' 的ASCAII码为65，’Z‘为90
	假设n 为26，也就是Z，则进来时 n--  = 25, 'A' + 25 % 26 = 65 + 25 = 90

	假设n 为28，则进来时 n-- = 27, 'A' + 27 % 26 = 65 + 1 = 66 = B, n再除以26 = 1 > 0
	进行下一次循环，n-- = 26, 'A' + 0 = 'A', 
	insert(0, ) 把A插入到B的前面
*/


public class Solution {
    public String convertToTitle(int n) {
        StringBuilder res = new StringBuilder();
        while (n > 0) {
        	n--;
        	res.insert(0, (char)('A' + n % 26));
        	n = n / 26;
        }
        return res.toString();
    }
}

public class Solution {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            n--;
            char c = (char)(n % 26 + 'A');
            sb.insert(0, c);
            n = n / 26;
        }
        return sb.toString();
    }
}