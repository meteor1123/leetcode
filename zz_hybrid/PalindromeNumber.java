package LeetCode;

public class PalindromeNumber {
	public boolean isPalin(int x) {
		if (x < 0) return false;
		if (x >= 0 && x < 10) return true;
		int remain = 0;
		while (x > remain) {
			remain = remain * 10 + (x % 10);
			x = x / 10;
		}
		return remain == x || remain / 10 == x;
	}
	public static void main(String[] args) {
		PalindromeNumber test = new PalindromeNumber();
		boolean res = test.isPalin(12321);
		if (res) {
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
	}
}
