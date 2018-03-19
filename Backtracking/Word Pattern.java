/*
	Word Pattern
	Given a pattern and a string str, find if str follows the same pattern.

	Examples:
	pattern = "abba", str = "dog cat cat dog" should return true.
	pattern = "abba", str = "dog cat cat fish" should return false.
	pattern = "aaaa", str = "dog cat cat dog" should return false.
	pattern = "abba", str = "dog dog dog dog" should return false.
	Notes:
	patterncontains only lowercase alphabetical letters, and str contains words separated by a single space. Each word in str contains only lowercase alphabetical letters.
	Both pattern and str do not have leading or trailing spaces.
	Each letter in pattern must map to a word with length that is at least 1
*/

public class Solution {


    //Solution2 prefer
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (pattern.length() != words.length) {
            return false;
        }
        HashMap<Character, String> map = new HashMap<>();
        for (int i = 0;i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];
            if (!map.containsKey(c)) {
                if (map.containsValue(word)) {
                    return false;
                }
                map.put(c, word); 
            } else {
                if (!map.get(c).equals(word)) {
                    return false;
                }
            } 
        }
        return true;
    }

    //Solution3 use hashset by myself
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (pattern.length() != words.length) {
            return false;
        }
        HashMap<Character, String> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];
            if (!map.containsKey(c)) {
                if (!set.contains(word)) {
                    map.put(c, word); 
                    set.add(word);
                } else {
                    return false;
                }
            } else {
                if (!map.get(c).equals(word)) {
                    return false;
                }
            } 
        }
        return true;
    }
}