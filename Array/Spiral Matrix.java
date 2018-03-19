/*
	Spiral Matrix 
	Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

	For example,
	Given the following matrix:

	[
	 [ 1, 2, 3 ],
	 [ 4, 5, 6 ],
	 [ 7, 8, 9 ]
	]
	You should return [1,2,3,6,9,8,7,4,5].
	Tags: array
*/


/*
	Solution: set a x and y ,initially set to zero;
			  use x and y to traverse the index of matrix, 
			  from top left to right, 
			  right top to bottom, 
			  bottom right to left, 
			  left bottom to top;
*/
public class Solution {
	public ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0) {
            return res;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        
        int x = 0;
        int y = 0;
        
        while (m > 0 && n > 0) {
            
            //if one row/column left, no circle can be formed
            //row == 1
            if (m == 1) {
                for (int i = 0; i < n; i++) {
                    res.add(matrix[x][y++]);
                }
                break;
            } else if (n == 1) { //column == 1, 
                for (int i = 0; i < m; i++) {
                    res.add(matrix[x++][y]);
                }
                break;
            }
            
            //process a circle
            
            //use for loop to controll the traverse

            //top - move right
            for (int i = 0; i < n - 1; i++) {
                res.add(matrix[x][y++]);
            }
            
            //right - move down
            for (int i = 0; i < m - 1; i++) {
                res.add(matrix[x++][y]);
            }
            
            //bottom - move left
            for (int i = 0; i < n - 1; i++) {
                res.add(matrix[x][y--]);
            }
            
            //left - move top
            for (int i = 0; i < m - 1; i++) {
                res.add(matrix[x--][y]);
            }
            
            //to next
            x++;
            y++;
            m = m - 2;
            n = n - 2;
        }
        return res;
    }
}