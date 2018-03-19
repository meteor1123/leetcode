package OA1;

public class countPalindromeAmount {
	public int countPalindrome(String str) {
		int i, j , k, count = 0;
		for (i = 0; i < str.length(); i++) {
			k = i - 1;
			j = i + 1;
			while (k >= 0 && j < str.length() && str.charAt(j) == str.charAt(k)) {
				count++;
				k--;
				j++;
			}
			
			k = i; 
			j = i + 1;
			while (k >= 0 && j < str.length() &&str.charAt(j) == str.charAt(k)) {
				count++;
				k--;
				j++;
			}
		}
		return count;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		countPalindromeAmount test = new countPalindromeAmount();
		System.out.println(test.countPalindrome("aabbaa"));
	}

}
