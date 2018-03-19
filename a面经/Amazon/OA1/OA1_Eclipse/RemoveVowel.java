package OA1;

public class RemoveVowel {
	public static String removeVowels(String s, String v) {
		if (s == null || s.length() == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (v.indexOf(s.charAt(i)) != -1) {
				continue;
			} else {
				sb.append(s.charAt(i));
			}
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RemoveVowel test = new RemoveVowel();
		System.out.println(test.removeVowels("aeiouAEIOU", ""));
	}

}
