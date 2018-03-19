/*
	Spiral Matrix II
	Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

	For example,
	Given n = 3,

	You should return the following matrix:
	[
	 [ 1, 2, 3 ],
	 [ 8, 9, 4 ],
	 [ 7, 6, 5 ]
	]
*/

public class Solution {
public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        //row index
        int x = 0;
        //column index
        int y = 0;
        //the matrix value
        int val = 1;
        
        int k = n;
        while (k > 0) {
            //top: left to right
            for (int i = 0; i < k - 1; i++) {
                matrix[x][y++] = val++;
            }
            //right: top to bottom
            for (int i = 0; i < k - 1; i++) {
                matrix[x++][y] = val++;
            }
            //bottom: right to left
            for (int i = 0; i < k - 1; i++) {
                matrix[x][y--] = val++;
            }
            //left: bottom to top
            for (int i = 0; i < k - 1; i++) {
                matrix[x--][y] = val++;
            }
            x++;
            y++;
            k =  k - 2;
        }
        //need to check the index is odd or even,
        //since if is odd  we need to set the center point.
        if (n % 2 != 0) {
            matrix[n / 2][n / 2] = val;
        }
        return matrix;
    }
}