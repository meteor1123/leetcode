/*
	The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
	Given an integer n, return all distinct solutions to the n-queens puzzle.
	Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
	For example,	There exist two distinct solutions to the 4-queens puzzle:

		[
		 [".Q..",  // Solution 1
		  "...Q",
		  "Q...",
		  "..Q."],

		 ["..Q.",  // Solution 2
		  "Q...",
		  "...Q",
		  ".Q.."]
		]
*/

 
//Solution0 prefer, time: O(n^n) space: O(n)
public class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        int[] matrix = new int[n];
        dfs(res, matrix, n, 0);
        return res;
    }
    
    public void dfs(List<List<String>> res, int[] matrix, int n, int row) {
        if (row == n) {
            List<String> item = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (j == matrix[i]) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                item.add(sb.toString());
            }
            res.add(item);
            return ;
        }
        for (int i = 0; i < n; i++) {
            matrix[row] = i;
            if (isValid(matrix, row, matrix[row])) {
                dfs(res, matrix, n, row + 1);
            }
        }
    }
    
    public boolean isValid(int[] matrix, int row, int colValue) {
        for (int i = 0; i < row; i++) {
            if (matrix[i] == matrix[row] || Math.abs(i - row) == Math.abs(matrix[i] - colValue) ) {
                return false;
            }
        }
        return true;
    }
}

//Solution2: dfs by row prefer
public class Solution {
        public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n < 0) {
            return res;
        }
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        dfs(n, 0, board, res);
        return res;
        
    }
    public void dfs(int n, int row, char[][] board, List<List<String>> res) {
        if (row == n) {
            List<String> item = new ArrayList<>();
            for (char[] rowRes : board) {
                item.add(new String(rowRes));
            }
            res.add(item);
            return;
        }
        
        for (int i = 0; i < n; i++) {
            if (isValid(board, row, i)) {
                 board[row][i] = 'Q';
                 dfs(n, row + 1, board, res);
                 board[row][i] = '.';
            }
        }
    }
    
    public boolean isValid(char[][] board, int row, int col) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 'Q' && (i == row || j == col || Math.abs(row - i) == Math.abs(col - j))) {
                    return false;
                }
            }
        }
        return true;
    }
}


//Solution3: dfs by col
class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n < 0) {
            return res;
        }
        char[][] board = new char[n][n];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = '.';
            }
        }
        dfs(n, res, 0, 0, board);
        return res;
    }
    
    public void dfs(int nQueens, List<List<String>> res, int row, int col, char[][] board) {
        if (col == nQueens) {
            List<String> item = new ArrayList<>();
            for (int i = 0; i < board.length; i++) {
                item.add(new String(board[i]));
            }
            res.add(item);
        }
        
        for (int i = row; i < nQueens; i++) {
            if (isValid(board, i, col)) {
                board[i][col] = 'Q';
                dfs(nQueens, res, row, col + 1, board);
                board[i][col] = '.';
            }
        }
    }
    
    public boolean isValid(char[][] board, int row, int col) {
        if(col>= board.length)
            return false;
        for(int i = 0;i < board.length; i++){
            for(int j = 0;j < col; j++){
                if(board[i][j] == 'Q'&& (i == row || row + col == i + j || row - col == i - j)) //Return false if a quuen is already present in another row for same col, or in any diagnal passing through board[row][col]
                    return false;
            }
        }
        return true;
    }
}