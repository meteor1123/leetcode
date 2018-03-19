/*
	ZigZag Conversion
	The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
	
	P   A   H   N
	A P L S I I G
	Y   I   R
	And then read line by line: "PAHNAPLSIIGYIR"
	Write the code that will take a string and make this conversion given a number of rows:

	string convert(string text, int nRows);
	convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
*/

/*
	PAYPALISHIRING是输入
    
    输出
	P   A   H   N
	A P L S I I G
	Y   I   R     
	输出是按照上面这个zigzag排列之后每行相加的字符串 PAHNAPLSIIGYIR

    2 * numRows - 2 就是PAYP, ALIS, HIRI 这样的格式分组 组成一个V字形
*/


//Prefer, time O(n), space O(n)
class Solution {
    public String convert(String s, int numRows) {
        if (numRows <= 1)
            return s;

        StringBuilder[] sb = new StringBuilder[numRows];
        for (int i = 0; i < sb.length; i++)
            sb[i] = new StringBuilder();
        
        for (int i = 0; i < s.length(); i++) {
            int index =  i % (2 * numRows - 2);
            index = index < numRows ? index : 2 * numRows - 2 - index;
            sb[index].append(s.charAt(i));
        }
        
        for (int i = 1; i < sb.length; i++)
            sb[0].append(sb[i]);
        return sb[0].toString();
    }
}


public class Solution {
    public String convert(String s, int numRows) {
        char[] c = s.toCharArray();
        int len = c.length;
        StringBuffer[] sb = new StringBuffer[numRows];
        for (int i = 0; i < sb.length; i++) {
            sb[i] = new StringBuffer();
        }
        
        int i = 0;
        while (i < len) {
        	//这个for循环是用来储存满列的数
            for (int index = 0; index < numRows && i < len; index++) {
                sb[index].append(c[i++]);
            }
            //这个for循环是用来储存不满列的数
            for (int index = numRows - 2; index >= 1 && i < len; index--) {
                sb[index].append(c[i++]);
            }
        }
        
        for (int index = 1; index < sb.length; index++) {
            sb[0].append(sb[index]);//拼接所有行
        }
        return sb[0].toString();
    }
}