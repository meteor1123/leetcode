/*
	Integer to Roman
	Given an integer, convert it to a roman numeral.
	Input is guaranteed to be within the range from 1 to 3999.
	Tags: Math , String
*/

/*
        1    = I
        4    = IV
        5    = V
        9    = IX
        10   = X
        40   = XL
        50   = L
        90   = XC
        100  = C
        400  = CD
        500  = D
        900  = CM
        1000 = M
*/

//Best
public class Solution {
    //We notice that, once we meet 4 or 40 or 400, they are difficult to solve,
    //like IIII is not roman four, so we need to convert to IV,
    //like VIIII is not roman nine, so we need to convert to IX
    //注意到，每逢4就不好处理，比如IIII不可行，需要转换成IV。9也不好处理VIIII所以要转成IX，以此类推
    //40 90 400 900 也应该预先初始化，方便遍历
    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbol = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        
        StringBuilder sb = new StringBuilder();
        
        int index = 0;
        while (num > 0) {
            while (num >= values[index]) {
                num -= values[index];
                sb.append(symbol[index]);
            }
            index++;
        }
        return sb.toString();
    }
}
