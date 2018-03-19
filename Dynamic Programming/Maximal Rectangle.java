/*
	Maximal Rectangle
	Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
*/



public class Solution {
    //Solution2:
    public int maximalRectangle(char[][] matrix) {
        //对每一行的每一列高度进行计算，如果列=0，则高度为0
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
            int m = matrix.length;
            int n = matrix[0].length;
            int max = 0;
            int[] height = new int[n];//每一行与上一行的数字相加，上一行的1+这一行的0等于0，上一行的0加上这一行的1等于1.
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(matrix[i][j] == '0') //
                        height[j] = 0;
                    else 
                        height[j] += 1;
                }
                max = Math.max(largestRectangleArea(height), max);
            }
            return max;
    }
    public int largestRectangleArea(int[] heights) {
        Stack < Integer > stack = new Stack < > ();
        stack.push(-1);
        int res = 0;
        for (int i = 0; i < heights.length; ++i) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i])
                res = Math.max(res, heights[stack.pop()] * (i - stack.peek() - 1));
            stack.push(i);
        }
        // if we reach the end of the array, we pop all the elements of the stack and at every pop
        while (stack.peek() != -1)
            res = Math.max(res, heights[stack.pop()] * (heights.length - stack.peek() -1));
        return res;
    }
}



//Solution2
public class Solution {
    /*
        This solution is so clever that I think so hard to understand it.
        height counts the number of successive '1's above (plus the current one).

        The value of left & right means the boundaries of the rectangle
        which contains the current point with a height of value height.
        

        one pointer, left, to note the starting position of 1s.
        one pointer, right, to note the ending position of 1s.
        one pointer, height, to note the height.
                 0 1 2 3 4 5 6

            0    0 0 0 1 0 0 0
            1    0 0 1 1 1 0 0
            2    0 1 1 1 1 1 0

        0th row: 0 0 0 1 0 0 0

        height:  0 0 0 1 0 0 0
        left:    0 0 0 3 0 0 0
        right    7 7 7 4 7 7 7

        1st row: 0 0 1 1 1 0 0

        height:  0 0 1 2 1 0 0
        left:    0 0 2 3 2 0 0
        right:   7 7 5 4 5 7 7

        2nd row: 0 1 1 1 1 1 0
        height:  0 1 2 3 2 1 0
        left:    0 1 2 3 2 1 0
        right:   7 6 5 4 5 6 7
    */



	//Solution1:
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        
        int[] left = new int[n];//记录一行点的左边界，等于包含这个点的矩形的最左边的点的数组序号，
        int[] right = new int[n];//记录一行点的右边界. 右边界等于最右边的点的数组序号+1，为什么+1？ 假如left = 2, right = 4, 4 - 2 = 2,但实际应该是3个跨度，所以令右边界+ 1
        int[] height = new int[n];//记录每一行点的高度，和上一行相关
        
        for (int i = 0; i < right.length; i++) {
            right[i] = n;
        }
        
        int maxA = 0;
        
        for (int i = 0; i < m; i++) {
            int cur_left = 0, cur_right = n;

            //设置高度
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    height[j] += 1;
                } else {
                    height[j] = 0;
                }
            }
            
            //等于本行最左边的点的以及上一行的最左点左边的最大值，
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], cur_left);
                } else {
                    //如果点不等于1，则该处出现缺口，更新 cur_left
                    left[j] = 0;
                    cur_left = j + 1;
                }
            }
            
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], cur_right);
                } else {
                    right[j] = n;
                    cur_right = j;
                }
            }
            
            for (int j = 0; j < n; j++) {
                maxA = Math.max(maxA, (right[j] - left[j]) * height[j]);
            }
        }
        return maxA;
    }
}