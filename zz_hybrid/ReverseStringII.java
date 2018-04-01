package LeetCode;

public class ReverseStringII {
	public String reverseStr(String s, int k) {
		char[] ch = s.toCharArray();
		for (int i = 0; i < ch.length; i += 2 * k) {
			reverse(ch, i, Math.min(i + k - 1, ch.length - 1));
		}
		return new String(ch);
	}
	public void reverse(char[] ch, int left, int right) {
		if (left > right) {
			return;
		}
		while (left <= right) {
			swap(ch, left++, right--);
		}
	}
	private void swap(char[] ch, int a, int b) {
		char temp = ch[a];
		ch[a] = ch[b];
		ch[b] = temp;
	}
	public static void main(String[] args) {
		ReverseStringII test = new ReverseStringII();
		String output = test.reverseStr("abcdefg", 2);
		System.out.println(output);
	}
}
