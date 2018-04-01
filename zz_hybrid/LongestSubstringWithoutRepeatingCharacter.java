package LeetCode;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacter {
	public int lengthOfLongestSubstring(String s) {
		Set<Character> exist = new HashSet<>();
		int slow = 0;
		int fast = 0;
		int gMax = 0;
		while (slow < s.length() && fast < s.length()) {
			if (!exist.contains(s.charAt(fast))) {
				exist.add(s.charAt(fast));
				gMax = Math.max(gMax, fast - slow + 1);
				fast++;
			} else {
				exist.remove(s.charAt(slow));
				slow++;
			}
		}
		return gMax;
	}
	public static void main(String[] args) {
		LongestSubstringWithoutRepeatingCharacter test = new LongestSubstringWithoutRepeatingCharacter();
		int res = test.lengthOfLongestSubstring("qwer");
		System.out.println(res);
	}
}
