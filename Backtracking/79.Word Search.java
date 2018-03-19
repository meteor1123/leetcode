/*
	Word Search 
	Given a 2D board and a word, find if the word exists in the grid.

	The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

	For example,
	Given board = [
	  ["ABCE"],
	  ["SFCS"],
	  ["ADEE"]
	]
	word = "ABCCED", -> returns true,
	word = "SEE", -> returns true,
	word = "ABCB", -> returns false.
	Show Tags:Array , Backtracking.

*/
/*
    这道题分析看，就是一个词，在一行出现也是true，一列出现也是true，一行往下拐弯也是true，一行往上拐弯也是true，一列往左拐弯        也是true，一列往右拐弯也是true。所以是要考虑到所有可能性，基本思路是使用DFS来对一个起点字母上下左右搜索，看是不是含有给定  的Word。还要维护一个visited数组，表示从当前这个元素是否已经被访问过了，过了这一轮visited要回false，因为对于下一个元素，当前这个元  素也应该是可以被访问的。
*/

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, 0, i, j, visited))
                    return true;
            }
        }
        return false;
    }
    
    public boolean dfs(char[][] board, String word, int index, int rowIndex, int colIndex, boolean[][] visited) {
        //index 等于 word的lenggth，说明找到匹配
        if (index == word.length())
            return true;
        //剪枝，各种不符合项
        if (rowIndex < 0 ||colIndex < 0 || rowIndex >= board.length || colIndex >= board[0].length)
            return false;
        //每次只要访问过board[i][j]就标记visited[i][j]为false，不能重复访问
        if (visited[rowIndex][colIndex] == true)
            return false;
        //关键，只要board[i][j]不符合，就返回false，回溯
        if (board[rowIndex][colIndex] != word.charAt(index))
            return false;

        visited[rowIndex][colIndex] = true;
        boolean res = dfs(board, word, index + 1, rowIndex + 1, colIndex, visited) || // upward
                      dfs(board, word, index + 1, rowIndex - 1, colIndex, visited) || // downward
                      dfs(board, word, index + 1, rowIndex, colIndex + 1, visited) || // towards the right
                      dfs(board, word, index + 1, rowIndex, colIndex - 1, visited); // towards the left
        //回溯完标记为false ，方便下次从不同点开始时尅访问
        visited[rowIndex][colIndex] = false;
        return res;
    }