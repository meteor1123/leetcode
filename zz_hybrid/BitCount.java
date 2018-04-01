package LeetCode;

public class BitCount {
	public int hammingWeight(int n) {
		int count = 0;
		for (int i = 0; i < 32; i++) {
			if ((n & 1) == 0) {
				count++;
			}
			n >>= 1;
		}
		return count;
	}
	public static void main(String[] args) {
		BitCount test = new BitCount();
		int re = test.hammingWeight(214748364);
		System.out.println(re);
	}
}
