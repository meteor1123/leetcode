/*
	Valid Sudoku 
	Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

	The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

	A partially filled sudoku which is valid.

	Note:
	A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
*/


//Solution: prefer
class Solution {
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            HashSet<Character> rows = new HashSet();
            HashSet<Character> cols = new HashSet();
            HashSet<Character> cube = new HashSet();
            
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.' && !rows.add(board[i][j]))
                    return false;
                if (board[j][i] != '.' && !cols.add(board[j][i]))
                    return false;
                int rowIndex = 3 * (i / 3);
                int colIndex = 3 * (i % 3);
                // 小的九宫格
                if (board[rowIndex + j / 3][colIndex + j % 3] != '.' && !cube.add(board[rowIndex + j / 3][colIndex + j % 3]))
                    return false;
            }
        }
        return true;
    }
}

//Solution1
public class Solution {
	//no use hash
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length == 0) {
            return true;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.' && !isValid(board, i, j)) {
                    return false;
                }
            }
        }
        return true;
        
    }
    public boolean isValid (char[][] board, int row, int col, char c) {
    	//check whole row
        for (int i = 0; i < 9; i++) {
            if (i != row && board[i][col] == c) {
                return false;
            }
        }
        //check whole col
        for (int i = 0; i < 9; i++) {
            if (i != col && board[row][i] == c) {
                return false;
            }
        }
        //check the block
        for (int i = (row / 3) * 3; i < (row / 3) * 3 + 3; i++) {
            for (int j = (col / 3) * 3; j < (col / 3) * 3 + 3; j++) {
                if ((i != row && j != col) && board[i][j] == c) {
                    return false;
                }
            }
        }
        return true;
    }
}

//Solution2
public class Solution {
    public boolean isValidSudoku(char[][] board) {
        for(int i = 0; i < 9; i++) {
            if(!isValid(board, i, i, 0, 8) || !isValid(board, 0, 8, i, i) || !isValid(board, i / 3 * 3, i / 3 * 3 + 2, i % 3 * 3, i % 3 * 3 + 2)) {
            	return false;         
            }
        }

        return true;  
    }

    public boolean isValid(char[][] board, int xStart, int xEnd, int yStart, int yEnd) {
        HashSet<Integer> set = new HashSet<Integer>();
        for(int x = xStart; x <= xEnd; x++) {
            for(int y = yStart; y <= yEnd; y++) {
                if(board[x][y] != '.' && !set.add(board[x][y] - '0')) return false;
            }
        }
        return true;
    }
}

    /*
    	solution by xiaoyingzi
	    	这道题利用的是HashSet的唯一性来帮助check。
			先按每行check，如果是'.'说明还没填字，是合法的，往下走，如果没在set中存过就加一下，
			如果便利过程中出现了在set中存在的key值，说明有重复的数字在一行，不合法，return false。
			再按照这个方法check列。最后按照这个方法check小方块。
			注意小方块的ij取法。对于当前这块板子来说，总共有9个小方格，按0~8从左到右依次编号。
			按编号求'/'就是求得当前小方格的第一行横坐标，因为每个小方格有3行，所以循环3次。
			按编号求'%'就是求得当前小方格的第一列纵坐标，因为每个小方格有3列，所以循环3次。 
			对9个小方格依次走一边，就完成了检查小方格的工作。
    */
    //use hashset
    public boolean isValidSudoku(char[][] board) {
    	HashSet<Character> set = new HashSet<Character>();
	    // Check for each row
	    for (int i = 0; i < 9; i++) {
	        for (int j = 0; j < 9; j++) {
	            if (board[i][j] == '.')
	                continue;
	            if (set.contains(board[i][j]))
	                return false;
	            set.add(board[i][j]);
	        }
	        set.clear();
	    }

	    // Check for each column
	    for (int j = 0; j < 9; j++) {
	        for (int i = 0; i < 9; i++) {
	            if (board[i][j] == '.')
	                continue;
	            if (set.contains(board[i][j]))
	                return false;
	            set.add(board[i][j]);
	        }
	        set.clear();
	    }

	    // Check for each sub-grid
	    for (int k = 0; k < 9; k++) {
	        for (int i = k/3*3; i < k/3*3+3; i++) {
	            for (int j = (k%3)*3; j < (k%3)*3+3; j++) {
	                if (board[i][j] == '.')
	                    continue;
	                if (set.contains(board[i][j]))
	                    return false;
	                set.add(board[i][j]);
	            }
	        }
	        set.clear();
	    }
	    
	    return true;
	}
}