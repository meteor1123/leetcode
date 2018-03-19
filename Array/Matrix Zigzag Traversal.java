/*
	Matrix Zigzag Traversal
	Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in ZigZag-order.

	Have you met this question in a real interview? Yes
	Example
	Given a matrix:

	[
	  [1, 2,  3,  4],
	  [5, 6,  7,  8],
	  [9,10, 11, 12]
	]
	return [1, 2, 5, 9, 6, 3, 4, 7, 10, 11, 8, 12]
 */

 public class Solution {
     /**
      * @param matrix: a matrix of integers
      * @return: an array of integers
      */ 
     public int[] printZMatrix(int[][] matrix) {
         // write your code here
         if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
             return null;
         }
         int m = matrix.length;
         int n = matrix[0].length;
         int count = m * n;
         int[] res = new int[count];
         int r = 0;//row
         int c = 0;//col
         int i = 1;
         res[0] = matrix[0][0];
         
         while (i < count) {
             //斜上走到顶
             while (i < count && r - 1 >= 0 && c + 1 < n) {
                 res[i++] = matrix[--r][++c];
             }
             
             //横右走一步，不可横右走时竖下走一步
             if (i < count && c + 1 < n) {
                 res[i++] = matrix[r][++c];
             } else if (i < count && r + 1 < m) {
                 res[i++] = matrix[++r][c];
             }
             
             //斜下走到底
             while (i < count && r + 1 < m && c - 1 >= 0) {
                 res[i++] = matrix[++r][--c];
             }
             
             //竖下走一步，不可竖下走时横右走一步
             if (i < count && r + 1 < m) {
                 res[i++] = matrix[++r][c];
             } else if (i < count && c + 1 < n) {
                 res[i++] = matrix[r][++c];
             }
         }
         return res;
     }
 }
