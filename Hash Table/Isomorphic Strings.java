/*
	Isomorphic Strings 
	Given two strings s and t, determine if they are isomorphic.

	Two strings are isomorphic if the characters in s can be replaced to get t.

	All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

	For example,
	Given "egg", "add", return true.

	Given "foo", "bar", return false.

	Given "paper", "title", return true.

	Note:
	You may assume both s and t have the same length.
	Tags: HashTable
*/


//Solution1: Two Hash Table
class Solution {
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> map = new HashMap();
        HashSet<Character> set = new HashSet();
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            
            if (map.get(c1) == null) {
                if (set.contains(c2)) {
                    return false;
                }
                set.add(c2);
                map.put(c1, c2);
            } else if (map.get(c1) != c2) {
                return false;
            } 
        }
        return true;
    }
}

//Solution2: use map.containsValue method
public class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if (!map.containsKey(c1)) {
                if (map.containsValue(c2)){
                    return false;
                }
                map.put(c1, c2);
            } else if (map.get(c1) != c2) {
                    return false;
            }
        }
        return true;
    }
}

//Solution3 : Best
public class Solution {
    public boolean isIsomorphic(String s, String t) {
        int[] map1 = new int[256];
        int[] map2 = new int[256];
        for (int i = 0; i < s.length(); i++) {
            if (map1[s.charAt(i)] != map2[t.charAt(i)]) {
                return false;
            } else {
                map1[s.charAt(i)] = map2[t.charAt(i)] = i + 1;
            }
        }
        return true;
    }
}