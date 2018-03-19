/*
  Follow up for N-Queens problem.

  Now, instead outputting board configurations, return the total number of distinct solutions.
*/

// time: O(n^n) space: O(n)
public class Solution {
    int res = 0;
    public int totalNQueens(int n) {
        if (n <= 0) {
            return res;
        }
        //use one dimension array to store the  position information
        // columnVal[n] mean  in the position the row n, and the columnVal[n] column put a queens
        int[] columnVal = new int[n];
        totalNQueensHelper(0, columnVal, n);
        return res;
    }
    
    public void totalNQueensHelper(int curRow, int[] columnVal, int n) {
        if (curRow == n) {
            res++;
            return;
        }
        
        for (int i = 0; i < n; i++) {
          //只要curRow
            columnVal[curRow] = i; 
            if (isValid(curRow, columnVal)) {
                totalNQueensHelper(curRow + 1, columnVal, n);
            } 
        }
    }
    
    
    public boolean isValid(int curRow, int[] columnVal) {
        for (int i = 0; i < curRow; i++) {
            if (columnVal[curRow] == columnVal[i] || Math.abs(columnVal[curRow] - columnVal[i]) == (curRow - i)) {
                return false;
            }
        }
        return true;
    }
}


//Solution2 
public class Solution {
    int count;
    public int totalNQueens(int n) {
        if (n <= 1) {
            return n;
        }
        count = 0;
        int[] arr = new int[n];
        dfs(arr, 0, n);
        return count;
    }
    
    public void dfs(int[] arr, int row, int n) {
        if (row == n) {
            count++;
            return;
        }
        for (int i = 0; i < n; i++) {
            arr[row] = i;
            if (isValid(arr, row)) {
                dfs(arr, row + 1, n);
            }
        }
    }
    
    public boolean isValid(int[] arr, int row) {
        for (int i = 0; i < row; i++) {
            if (arr[row] == arr[i] || Math.abs(arr[row] - arr[i]) == (row - i)) {
                return false;
            }
        }
        return true;
    }
}


//Solution3 time: O(n^n) space: O(n)
Start row by row, and loop through columns. At each decision point, skip unsafe positions by using three boolean arrays.
Start going back when we reach row n.
If using HashSet, running time will be at least 3 times slower!

public class Solution {
    int count = 0;
    public int totalNQueens(int n) {
        boolean[] cols = new boolean[n];     // columns   |
        boolean[] d1 = new boolean[2 * n];   // diagonals \
        boolean[] d2 = new boolean[2 * n];   // diagonals /
        backtracking(0, cols, d1, d2, n);
        return count;
    }
    
    public void backtracking(int row, boolean[] cols, boolean[] d1, boolean []d2, int n) {
        if(row == n) count++;

        for(int col = 0; col < n; col++) {
            int id1 = col - row + n;
            int id2 = col + row;
            if(cols[col] || d1[id1] || d2[id2]) continue;
            
            cols[col] = true; d1[id1] = true; d2[id2] = true;
            backtracking(row + 1, cols, d1, d2, n);
            cols[col] = false; d1[id1] = false; d2[id2] = false;
        }
    }
}