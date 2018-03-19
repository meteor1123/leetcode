/*
	Add and Search Word - Data structure design 
	Design a data structure that supports the following two operations:

	void addWord(word)
	bool search(word)
	search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

	For example:

	addWord("bad")
	addWord("dad")
	addWord("mad")
	search("pad") -> false
	search("bad") -> true
	search(".ad") -> true
	search("b..") -> true
	You may assume that all words are consist of lowercase letters a-z.
*/
class WordDictionary {
    class Trie {
        Trie[] children;
        String item;
        public Trie() {
            children = new Trie[26];
            item = "";
        }
    }
    private Trie dict;
    /** Initialize your data structure here. */
    public WordDictionary() {
        dict = new Trie();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        Trie cur = dict;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (cur.children[c - 'a'] == null) {
                cur.children[c - 'a'] = new Trie();
            }
            cur = cur.children[c - 'a'];
        }
        cur.item = word;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return match(word.toCharArray(), 0, dict);
    }
    
    public boolean match(char[] word, int index, Trie node) {
        if (index == word.length) {
            return !node.item.equals("");
        }
        
        if (word[index] != '.') {
            return node.children[word[index] - 'a'] != null && match(word, index + 1, node.children[word[index] - 'a']);
        } else {
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null) {
                    if (match(word, index + 1, node.children[i])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");