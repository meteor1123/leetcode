/*
	Sudoku Solver

	Write a program to solve a Sudoku puzzle by filling the empty cells.

	Empty cells are indicated by the character '.'.

	You may assume that there will be only one unique solution.
	https://leetcode.com/problems/sudoku-solver/
	Tags: Backtracking, HashTable;
*/



public class Solution {
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        dfs(board);
    }
    
    public boolean dfs(char[][] board) {
    	//traverse the board array
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
            	//if the array value equals '.' ,means we can set the value 
                if (board[i][j] == '.') {
                	//the value range is from 1 to 9
                    for (char c = '1'; c <= '9'; c++) {
                    	//we  check the value is valid or not,but we dont't update the array value
                        if (isValid(board, i, j, c)) {
                        	//if is valid, we update the value of array
                            board[i][j] = c;
                            //and recursive check the remainder matrix is true or false
                            if (dfs(board)) {
                                return true;
                            } else {
                            	//backtracking
                                board[i][j] = '.';
                            }
                        }
                    }
                    return false;// 1 ~ 9 都试了 结果还不能return true， 则出错，返回false
                }
            }
        }
        return true;
    }
    
    public boolean isValid(char[][] board, int row, int col, char c) {
    	//check the whole row
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == c) {
                return false;
            }
        }
        //check the whole col
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == c) {
                return false;
            }
        }
        //check the block
        for (int i = (row / 3) * 3; i < (row / 3) * 3 + 3; i++) {
            for (int j = (col / 3) * 3;  j < (col / 3) * 3 + 3; j++) {
                if (board[i][j] == c) {
                    return false;
                }
            }
        }
        return true;
    }
}