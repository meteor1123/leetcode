/*
	Palindrome Permutation
	Given a string, determine if a permutation of the string could form a palindrome.

	For example,
	"code" -> False, "aab" -> True, "carerac" -> True.

	Hint:

	Consider the palindromes of odd vs even length. What difference do you notice?
	Count the frequency of each character.
	If each character occurs even number of times, then it must be a palindrome. How about character which occurs odd number of times?
*/

//Best
public class Solution {
    public boolean canPermutePalindrome(String s) {
        HashSet<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                set.remove(c);
            } else {
                set.add(c);
            }
        }
        return set.size() <= 1;
    }
}

public class Solution {
    public boolean canPermutePalindrome(String s) {
        int[] letter = new int[256];
        int count = 0;
        for (char c : s.toCharArray()) {
            if (letter[c] == 0) {
                letter[c]++;
            } else {
                letter[c]--;
            }
        }
        for (int i : letter) {
            count += i;
        }
        return count > 1 ? false : true;
    }
}