/*
	Word Search II
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

	Hint: 
		You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?
	If the current candidate does not exist in all words' prefix, you could stop backtracking immediately. What kind of data structure could answer such query efficiently? Does a hash table work? Why or why not? How about a Trie? If you would like to learn how to implement a basic trie, please work on this problem: Implement Trie (Prefix Tree) first.
	Tags: Backtracking, Trie
*/

//Solution 1
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
         if (root.startsWith(item)) {
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
     }
 }


 class TrieNode {
    // Initialize your data structure here.
    TrieNode[] children;
    String item;
    public TrieNode() {
        children = new TrieNode[26];
        item = "";
    }
}
public class Trie {
    private TrieNode root;
    public Trie() {
        root = new TrieNode();
    }
    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.children[c - 'a'] == null) {
                cur.children[c - 'a'] = new TrieNode();
            }
            cur = cur.children[c - 'a'];
        }
        cur.item = word;
    }
    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.children[c - 'a'] == null) {
                return false;
            }
            cur = cur.children[c - 'a'];
        }
        return cur.item.equals(word);
    }
    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c= prefix.charAt(i);
            if (cur.children[c - 'a'] == null) {
                return false;
            }
            cur = cur.children[c - 'a'];
        }
        return true;
    }
}



//Solution2
public class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        ArrayList<String> res = new ArrayList<String>();
        if (board == null || board.length == 0 || words.length == 0 || words == null) {
            return res;
        }
        HashSet<String> set = new HashSet<String>();
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
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || visited[row][col] == true) {
            return ;
        }
        item = item + board[row][col];
        if (root.startsWith(item)) {
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
    }
    
    private class Trie {
        private TrieNode root;
        Trie() {
            root = new TrieNode();
        }
        public void insert(String word) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                if (cur.getChild(c - 'a') == null) {
                    cur.setChild(c - 'a', new TrieNode());
                }
                cur = cur.getChild(c - 'a');
            }
                cur.setItem(word);
        }
        public boolean search(String word) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                if (cur.getChild(c - 'a') == null) {
                    return false;
                }
                cur = cur.getChild(c - 'a');
            }
            return cur.getItem().equals(word);
        }
        public boolean startsWith(String prefix) {
            TrieNode cur = root;
            for (char c : prefix.toCharArray()) {
                if(cur.getChild(c - 'a') == null) {
                    return false;
                }
                cur = cur.getChild(c - 'a');
            }
            return true;
        }
        private class TrieNode {
            private final int R = 26;
            private final TrieNode[] children;
            private String item;
            
            public TrieNode() {
                children = new TrieNode[R];
                item = "";
            }
            public String getItem() {
                return item;
            }
            public void setItem(String item) {
                this.item = item;
            }
            public TrieNode[] getChildren() {
                return children;
            }
            public TrieNode getChild(int i) {
                if (i >= 26 || i < 0) {
                    throw new IllegalArgumentException();
                }
                return children[i];
            }
            public void setChild(int i, TrieNode node) {
                children[i] = node;
            }
       }
    }
}


