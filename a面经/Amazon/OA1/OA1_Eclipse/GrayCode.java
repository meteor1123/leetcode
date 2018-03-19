package OA1;

public class GrayCode {
	public static int grayCodeCheck(byte num1, byte num2) {
		byte xor = (byte)(num1 ^ num2);
		int count = 0;
		while (xor != 0) {
			xor = (byte)(xor & (xor - 1));//Turn off the rightmost 1 - bit,y = x & (x - 1)
			count++;
		}
		if (count == 1) {
			return 1;
		} 
		return 0;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GrayCode test = new GrayCode();
		System.out.println(test.grayCodeCheck((byte)-128, (byte)0));
	}

}
