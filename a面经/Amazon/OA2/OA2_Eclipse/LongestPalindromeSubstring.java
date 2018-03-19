package OA2;
public class LongestPalindromeSubstring {
	public static String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        s = preProcess(s);
        int[] p = new int[s.length()];
        int mid = 0;
        int max = 0;
        for (int i = 1; i < s.length() - 1; i++) {
            p[i] = i < max ? Math.min(p[mid - (i - mid)], max - i) : 0; 
            while (s.charAt(i - p[i] - 1) == s.charAt(i + p[i] + 1)) {
                p[i]++;
            }
            if (i + p[i] > max) {
                mid = i;
                max = p[i];
            }
        }
        int maxLen = 0;
        mid = 0;
        for (int i = 0; i < s.length(); i++) {
            if (p[i] > maxLen) {
                mid = i;
                maxLen = p[i];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = mid - maxLen; i <= mid + maxLen; i++) {
            if (s.charAt(i) != '#') {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
    public static String preProcess(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append('~');
        for (int i = 0; i < s.length(); i++) {
            sb.append('#');
            sb.append(s.charAt(i));
        }
        sb.append("#$");
        return sb.toString();
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(longestPalindrome("aaabbbavvvvvvabb"));
	}
}

//public class Solution {
//    private int low, maxLen;
//    public String longestPalindrome(String s) {
//        int len = s.length();
//        if (len < 2) {
//            return s;
//        }
//        
//        for (int i = 0; i < s.length(); i++) {
//            extendPalindrome(s, i, i);
//            extendPalindrome(s, i, i + 1);
//        }
//        return s.substring(low, low + maxLen);
//    }
//    
//    public void extendPalindrome(String s, int i, int j) {
//        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
//            i--;
//            j++;
//        }
//        if (maxLen < j - i - 1) { 
//            low = i + 1;
//            maxLen = j - i - 1;
//        }
//    }
//}
