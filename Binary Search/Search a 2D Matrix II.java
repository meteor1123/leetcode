/*
	Search a 2D Matrix II
	Write an efficient algorithm that searches for a value in an m x n matrix, return the occurrence of it.

	This matrix has the following properties:

    * Integers in each row are sorted from left to right.

    * Integers in each column are sorted from up to bottom.

    * No duplicate integers in each row or column.

    Example
	Consider the following matrix:

	[
    	[1, 3, 5, 7],

    	[2, 4, 7, 8],

    	[3, 5, 9, 10]
	]

	Given target = 3, return 2.

	Challenge
	O(m+n) time and O(1) extra space
*/

/*
	Solution: 这道题让我们在一个二维数组中快速的搜索的一个数字，这个二维数组各行各列都是按递增顺序排列的，
	是之前那道Search a 2D Matrix 搜索一个二维矩阵的延伸，
	那道题的不同在于每行的第一个数字比上一行的最后一个数字大，是一个整体蛇形递增的数组。
	所以那道题可以将二维数组展开成一个一位数组用一次二查搜索
	。而这道题没法那么做，这道题有它自己的特点。如果我们观察题目中给的那个例子，我们可以发现有两个位置的数字很有特点，
	左下角和右上角的数。左下角的18，往上所有的数变小，往右所有数增加，
	那么我们就可以和目标数相比较，如果目标数大，就往右搜，如果目标数小，就往左搜
	。这样就可以判断目标数是否存在。当然我们也可以把起始数放在右上角，往左和下搜，停止条件设置正确就行。代码如下：
*/


//单纯判断有没有target 存在
//我们可以从左下或者右上开始search,
// O(m + n) time, and 0(1) extra space;
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        
        int i = 0;
        int j = colLen - 1;
        
        while (i < rowLen && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }
}


//return 有多少个相同的target
public int searchMatrix(int[][] matrix, int target) {
        // write your code here
        	int rowLen = matrix.length;
	if (rowLen == 0)
		return 0;
	int colLen = matrix[0].length;
	if (colLen == 0)
		return 0;

	int count = 0;
	int i = 0;
	int j = colLen - 1;

	while (i < rowLen && j >= 0) {
		if (matrix[i][j] == target) {
			count++;
			i++;
			j--;
		} else if (matrix[i][j] > target) {
			j--;
		} else {
			i++;
		}
	}
	return count;
}