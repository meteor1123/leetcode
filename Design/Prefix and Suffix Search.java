/*
	745. Prefix and Suffix Search

	Given many words, words[i] has weight i.

	Design a class WordFilter that supports one function, WordFilter.f(String prefix, String suffix). It will return the word with given prefix and suffix with maximum weight. If no word exists, return -1.

	Examples:
	Input:
	WordFilter(["apple"])
	WordFilter.f("a", "e") // returns 0
	WordFilter.f("b", "") // returns -1
	Note:
	words has length in range [1, 15000].
	For each test case, up to words.length queries WordFilter.f may be made.
	words[i] has length in range [1, 10].
	prefix, suffix have lengths in range [0, 10].
	words[i] and prefix, suffix queries consist of lowercase letters only.
*/


/* 
	Solution1: use HashMap to store all of the prefix AND suffix combination sets. Use space to speed up f function time
		
	Complexity analyse: N = words.lenth, L = the max length of each word
		WordFilter: Time = O(NL^2) 
		f: Time = O(1)
		Space: O(NL^2)
*/
class WordFilter {
    HashMap<String, Integer> map = new HashMap();
    public WordFilter(String[] words) {
        for (int i = 0; i < words.length; i++) {
            // because prefix, suffix have lengths in range [0, 10].
            for (int j = 0; j <= 10 && j <= words[i].length(); j++) {
                // because prefix, suffix have lengths in range [0, 10].
                for (int k = 0; k <= 10 && k <= words[i].length(); k++) {
                    map.put(words[i].substring(0, j) + "#" + words[i].substring(words[i].length() - k), i);
                }
            }
        }
    }
    
    public int f(String prefix, String suffix) {
        String key = prefix + "#" + suffix;
        return map.containsKey(key) ? map.get(key) : -1;
    }
}

/* 
	Solution2: 
		
	Complexity analyse: N = words.lenth, L = the max length of each word
		WordFilter: Time = O(NL)
		f: Time = O(N)
		Space = O(NL)
*/
class WordFilter {
	  HashMap<String, List<Integer>> prefixMap;
    HashMap<String, List<Integer>> suffixMap;
    public WordFilter(String[] words) {
        prefixMap = new HashMap();
        suffixMap = new HashMap();
        
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j <= 10 && j <= words[i].length(); j++) {
                String prefix = words[i].substring(0, j);
                if (!prefixMap.containsKey(prefix))
                    prefixMap.put(prefix, new ArrayList());
                prefixMap.get(prefix).add(i);
                
                String suffix = words[i].substring(words[i].length() - j);
                if (!suffixMap.containsKey(suffix))
                    suffixMap.put(suffix, new ArrayList());
                suffixMap.get(suffix).add(i);
            }
        }
    }
    
    public int f(String prefix, String suffix) {
        if (!prefixMap.containsKey(prefix) || !suffixMap.containsKey(suffix))
            return -1;
        List<Integer> p = prefixMap.get(prefix);
        List<Integer> s = suffixMap.get(suffix);
        // 因为把weight 按升序存到了arraylist里，所以从后往前搜索，存在的第一个同时有prefixhesuffix的 数组序号index即为所求
        int i = p.size() - 1;
        int j = s.size() - 1;
        
        while (i >= 0 && j >= 0) {
            if (p.get(i) < s.get(j))
                j--;
            else if (p.get(i) > s.get(j))
                i--;
            else
                return p.get(i);
        }
        return -1;
    }
}

/* 
	Solution3: use HashMap to store all of the prefix AND suffix combination sets. Use space to speed up f function time
		
	Complexity analyse: N = words.lenth, L = the max length of each word
		WordFilter: Time = O(1)
		f: Time = O(NL)
		Space = O(1)
*/

class WordFilter {
	  String[] input;
    public WordFilter(String[] words) {
        input = words;
    }
    
    public int f(String prefix, String suffix) {
        for (int i = input.length - 1; i >= 0; i--)
            if (input[i].startsWith(prefix) && input[i].endsWith(suffix))
                return i;
        return -1;
    }
}



/*
  Solution4: Trie

  Time Complexity: O(NK^2 + QK) where N is the number of words, K is the maximum length of a word, and Q is the number of queries.
	Space Complexity: O(NK^2), the size of the trie.
*/
class WordFilter {
    TrieNode trie;
    public WordFilter(String[] words) {
        trie = new TrieNode();
        for (int weight = 0; weight < words.length; weight++) {
            String word = words[weight] + "{";
            for (int i = 0; i < word.length(); i++) {
                TrieNode cur = trie;
                cur.weight = weight;
                // why from i to 2 * word.length() - 1 ? because we want to insert suffix + '{' + prefix which for example is `apple{apple`
                for (int j = i; j < 2 * word.length() - 1; j++) {
                    int k = word.charAt(j % word.length()) - 'a';
                    if (cur.children[k] == null)
                        cur.children[k] = new TrieNode();
                    cur = cur.children[k];
                    cur.weight = weight;
                }
            }
        }
    }
    public int f(String prefix, String suffix) {
        TrieNode cur = trie;
        char[] word = (suffix + '{' + prefix).toCharArray();
        for (char c : word) {
            if (cur.children[c - 'a'] == null)
                return -1;
            cur = cur.children[c - 'a'];
        }
        return cur.weight;
    }
}

class TrieNode {
    TrieNode[] children;
    int weight;
    public TrieNode() {
        children = new TrieNode[27];
        weight = 0;
    }
}