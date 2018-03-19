/*
	Trie
*/





1. Basic
		1.1 Implement Trie (Prefix Tree)
			/*
				Implement a trie with insert, search, and startsWith methods.
				Note:
				You may assume that all inputs are consist of lowercase letters a-z.
			*/
			class TrieNode {
			    // Initialize your data structure here.
			    TrieNode[] children;
			    String word;
			    public TrieNode() {
			        children = new TrieNode[26];
			        word = "";
			    }
			}

			public class Trie {
			    private TrieNode root;
			    public Trie() {
			        root = new TrieNode();
			    }

			    // Inserts a word into the trie.
			    public void insert(String word) {
			        TrieNode node = root;
			        for (int i = 0; i < word.length(); i++) {
			            char c = word.charAt(i);
			            if (node.children[c - 'a'] == null) {
			                node.children[c - 'a'] = new TrieNode();
			            }
			            node = node.children[c - 'a'];
			        }
			        node.word = word;
			    }

			    // Returns if the word is in the trie.
			    public boolean search(String word) {
			        TrieNode node = root;
			        for (int i = 0; i < word.length(); i++) {
			            char c = word.charAt(i);
			            if (node.children[c - 'a'] != null) {
			                node = node.children[c - 'a'];
			            } else {
			                return false;
			            }
			        }
			        return node.word.equals(word);
			    }

			    // Returns if there is any word in the trie
			    // that starts with the given prefix.
			    public boolean startsWith(String prefix) {
			        TrieNode node = root;
			        for (int i = 0; i < prefix.length(); i++) {
			            char c = prefix.charAt(i);
			            if (node.children[c - 'a'] != null) {
			                node = node.children[c - 'a'];
			            } else {
			                return false;
			            }
			        }
			        return true;
			    }
			}

		1.2 Add And Search Word - Data Structure Design
			/*
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
				Note:
				You may assume that all words are consist of lowercase letters a-z.
			*/
			public class WordDictionary {
			    public class TrieNode {
			        public TrieNode[] children = new TrieNode[26];
			        public String item = "";
			    }
			    public TrieNode root = new TrieNode();
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
			    public boolean match(char[] words, int k, TrieNode node) {
			        if (k == words.length) {
			            return !node.item.equals("");
			        }
			        if (words[k] != '.') {
			            return node.children[words[k] - 'a'] != null && match(words, k + 1, node.children[words[k] - 'a']);
			        } else {
			            for (int i = 0; i < node.children.length; i++) {
			                if (node.children[i] != null) {
			                    if (match(words, k + 1, node.children[i])) {
			                        return true;
			                    }
			                }
			            }
			        }
			        return false;
			    }
			}



2. Word Search Problem
		2.1 Word Search II
			/*
				Given a 2D board and a list of words from the dictionary, find all words in the board.

				Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

				For example,
				Given words = ["oath","pea","eat","rain"] and board =

				[
				  ['o','a','a','n'],
				  ['e','t','a','e'],
				  ['i','h','k','r'],
				  ['i','f','l','v']
				]
				Return ["eat","oath"].
				Note:
				You may assume that all inputs are consist of lowercase letters a-z.
			*/
			public class Solution {
			    public List<String> findWords(char[][] board, String[] words) {
			         ArrayList<String> res = new ArrayList<String>();
			        if (board == null || board.length == 0 || board[0].length == 0 || words == null) {
			            return res;
			        }
			        // HashSet<String> set = new HashSet<>();
			        Trie root = new Trie();
			        for (int i = 0; i < words.length; i++) {
			            root.insert(words[i]);
			        }
			        int m = board.length;
			        int n = board[0].length;
			        boolean[][] visited = new boolean[m][n];
			        for (int i = 0; i < m; i++) {
			            for (int j = 0; j < n; j++) {
			                dfs(res, board, i, j, visited, root, "");
			            }
			        }
			        return res;
			     }
			     public void dfs(ArrayList<String> res, char[][] board, int row, int col, boolean[][] visited, Trie root, String item) {
			         if (row < 0 || col < 0 || row > board.length - 1 || col > board[0].length - 1 || visited[row][col]) {
			             return ;
			         }
			         item = item + board[row][col];
			         if (!root.startsWith(item)) {
			             return;
			         }
			         visited[row][col] = true;
			         if (root.search(item) && !res.contains(item)) {
			             res.add(item);
			         }
			         dfs(res, board, row + 1, col, visited, root, item);
			         dfs(res, board, row - 1, col, visited, root, item);
			         dfs(res, board, row, col + 1, visited, root, item);
			         dfs(res, board, row, col - 1, visited, root, item);
			         visited[row][col] = false;
			     }
			     public class Trie {
			         class TrieNode {
			             public char val;
			            //  public boolean isWord;
			             public String item;
			             public TrieNode[] children = new TrieNode[26];
			             public TrieNode() {}
			             TrieNode(char c) {
			                 TrieNode node = new TrieNode();
			                 node.val = ' ';
			                 item = "";
			             }
			         }
			         private TrieNode root;
			         public Trie() {
			             root = new TrieNode();
			             root.val = ' ';
			         }
			         public void insert(String word) {
			             TrieNode node = root;
			             for (int i = 0; i < word.length(); i++) {
			                 char c = word.charAt(i);
			                 if (node.children[c - 'a'] == null) {
			                     node.children[c - 'a'] = new TrieNode(c);
			                 }
			                 node = node.children[c - 'a'];
			             }
			             node.item = word;
			         }
			         public boolean search(String word) {
			             TrieNode node = root;
			             for (int i = 0; i < word.length(); i++) {
			                 char c = word.charAt(i);
			                 if (node.children[c - 'a'] == null) {
			                     return false;
			                 }
			                 node = node.children[c - 'a'];
			             }
			             return node.item.equals(word);
			         }
			         public boolean startsWith(String word) {
			             TrieNode node = root;
			             for (int i = 0; i < word.length(); i++) {
			                 char c = word.charAt(i);
			                 if (node.children[c -'a'] == null) {
			                     return false;
			                 }
			                 node = node.children[c - 'a'];
			             }
			             return true;
			         }
			     }
			 }