/*
	Palindrome Pairs
	Given a list of unique words. Find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

	Example 1:
	Given words = ["bat", "tab", "cat"]
	Return [[0, 1], [1, 0]]
	The palindromes are ["battab", "tabbat"]
	Example 2:
	Given words = ["abcd", "dcba", "lls", "s", "sssll"]
	Return [[0, 1], [1, 0], [3, 2], [2, 4]]
	The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 */


//Solution0 prefer
public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>(); 
        if (words == null || words.length < 2) {
            return res;
        }
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }
        for (int i = 0; i<words.length; i++) {
            for (int j=0; j <= words[i].length(); j++) { // notice it should be "j <= words[i].length()"
                String str1 = words[i].substring(0, j);
                String str2 = words[i].substring(j);
                if (isPalindrome(str1)) {
                    String str2Reverse = new StringBuilder(str2).reverse().toString();
                    if (map.containsKey(str2Reverse) && map.get(str2Reverse) != i) {
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(map.get(str2Reverse));
                        list.add(i);
                        res.add(list);
                    }
                }
                if (isPalindrome(str2)) {
                    String str1Reverse = new StringBuilder(str1).reverse().toString();
                    // check "str.length() != 0" to avoid duplicates
                    if (map.containsKey(str1Reverse) && map.get(str1Reverse) != i && str2.length()!=0) { 
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(i);
                        list.add(map.get(str1Reverse));
                        res.add(list);
                    }
                }
            }
        }
        return res;
    }

//Solution1
public class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if (words == null || words.length == 0) {
            return res;
        }
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }
        for (int i = 0; i < words.length; i++) {
            int l = 0, r = 0;
            while (l <= r) {
                String s = words[i].substring(l, r);
                Integer j = map.get(new StringBuilder(s).reverse().toString());
                if (j != null && i != j && isPalindrome(words[i].substring(l == 0 ? r : 0, l == 0 ? words[i].length() : l))) {
                    List<Integer> item = new ArrayList<>();
                    if (l == 0) {
                        item.add(i);
                        item.add(j);
                    } else {
                        item.add(j);
                        item.add(i);
                    }
                    res.add(item);
                }
                if (r < words[i].length()) {
                    r++;
                } else {
                    l++;
                }
            }
        }
        return res;
    }
    
    private boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }
}