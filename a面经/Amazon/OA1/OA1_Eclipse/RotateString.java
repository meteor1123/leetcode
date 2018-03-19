package OA1;

public class RotateString {
//	public boolean isRotateWord(String s1, String s2) {
//		if (s1 == null && s2 == null) {
//			return true;
//		}
//		if (s1 == null || s2 == null || s1.length() != s2.length()) {
//			return false;
//		}
//		int len = s1.length();
//		for (int i = 0; i < s1.length(); i++) {
//			if (s1.substring(0, i).equals(s2.substring(len - i, len)) && 
//					s1.substring(i, len).equals(s2.substring(0, len - i))) {
//				return true;
//			}
//		}
//		return false;
//	}
	public int isRotateWord(String s1, String s2) {
		if (s1 == null && s2 == null) {
			return 1;
		}
		if (s1 == null || s2 == null || s1.length() != s2.length()) {
			return 0;
		}
		String pattern = s1 + s1;
		return pattern.indexOf(s2) != -1 ? 1 : 0;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RotateString test = new RotateString();
		System.out.println(test.isRotateWord("abcd", "cdab"));
	}

}
