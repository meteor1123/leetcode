package LeetCode;

public class ReverseWordsInAString {
	public String reverseWords(String s) {
	}
    private void reverse(char[] ch, int left, int right) {
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
    	ReverseWordsInAString test = new ReverseWordsInAString();
    	String res = test.reverseWords("   sky is  blue");
    	System.out.println(res);
    }
}
