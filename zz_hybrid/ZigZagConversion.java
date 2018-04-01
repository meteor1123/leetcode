package LeetCode;

/*The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR"
*/

public class ZigZagConversion {
	public String convert(String s, int n) {
		StringBuffer[] sb = new StringBuffer[n];
		char[] ch = s.toCharArray();
		for (int i = 0; i < sb.length; i++) sb[i] = new StringBuffer();
		int index = 0;
		while (index < s.length()) {
			for (int i = 0; i < n && index < s.length(); i++) {
				sb[i].append(ch[index]);
				index++;
			}
			for (int i = n - 2; i >= 1 && index < s.length(); i--) {
				sb[i].append(ch[index]);
				index++;
			}
		}
		for (int i = 1; i < sb.length; i++) {
			sb[0].append(sb[i]);
		}
		return sb[0].toString();
	}
	public static void main(String[] args) {
		ZigZagConversion test = new ZigZagConversion();
		String s = "abcdef";
		String res = test.convert(s, 3);
		System.out.println(res);
	}
}
