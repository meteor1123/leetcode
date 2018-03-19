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


public class WordDictionary {
    public class TrieNode {
        public TrieNode[] children = new TrieNode[26];
        public String item = "";
    }

    private TrieNode root = new TrieNode();
    
    
    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (cur.children[c - 'a'] == null) {
                cur.children[c - 'a'] = new TrieNode();
            }
            cur = cur.children[c - 'a'];
        }
        cur.item = word;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return match(word.toCharArray(), 0, root);
    }
    
    private boolean match(char[] word, int k, TrieNode node) {
    	//match to the last character, so k == word.length;
        if (k == word.length) {
            return !node.item.equals("");//in here, we don't need to compare the node.item with word.
            //since if k == word.length, which mean has right matched, just check the node.item whether is not
        }

        //if isn't '.', just recursive check the next character of word.
        if (word[k] != '.') {
            return node.children[word[k] - 'a'] != null && match(word, k + 1, node.children[word[k] - 'a']);
        } else {
        	//if is '.',  check every children with the node, since '.' match every single character.
            for (int i = 0; i < node.children.length; i++) {
            	//
                if (node.children[i] != null) {
                    if (match(word, k + 1, node.children[i])) {
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