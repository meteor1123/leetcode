/*
	Smallest Rectangle Enclosing Black Pixels
	An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

	For example, given the following image:

	[
	  "0010",
	  "0110",
	  "0100"
	]
	and x = 0, y = 2,
	Return 6.
*/

/*
	Suppose we have a 2D array

	"000000111000000"
	"000000101000000"
	"000000101100000"
	"000001100100000"
	Imagine we project the 2D array to the bottom axis with the rule "if a column has any black pixel it's projection is black otherwise white". 
	The projected 1D array is

	"000001111100000"
	Theorem
		If there are only one black pixel region, then in a projected 1D array all the black pixels are connected.
	Proof by contradiction
		Assume to the contrary that there are disconnected black pixels at i and j where i < j in the 1D projection array. 
		Thus there exists one column k, k in (i, j) and and the column k in the 2D array has no black pixel. 
		Therefore in the 2D array there exists at least 2 black pixel regions separated by column k which contradicting the condition of "only one black pixel region".
		Therefore we conclude that all the black pixels in the 1D projection array is connected.
		This means we can do a binary search in each half to find the boundaries, if we know one black pixel's position. And we do know that.

	To find the left boundary, do the binary search in the [0,x) range and find the first column vector who has any black pixel.

	To determine if a column vector has a black pixel is O(m) so the search in total is O(m log n)

	We can do the same for the other boundaries. The area is then calculated by the boundaries. Thus the algorithm runs in O(m * log n + n * log m)
*/
//Solution1 Binary Search
public class Solution {
    public int minArea(char[][] image, int x, int y) {
        int m = image.length;
        int n = image[0].length;
        int colMin = binarySearch(image, true, 0, y, 0, m, true);
        int colMax = binarySearch(image, true, y + 1, n, 0, m, false);
        int rowMin = binarySearch(image, false, 0, x, colMin, colMax, true);
        int rowMax = binarySearch(image, false, x + 1, m, colMin, colMax, false);
        return (colMax - colMin) * (rowMax - rowMin);
    }
    public int binarySearch(char[][] image, boolean isHorizontal, int lower, int upper, int min, int max, boolean goLower) {
        //以find colMin为例子，lower 就是 0列， upper就是y列，我们要在(lower ~ upper)之间找最小的col， y是所给坐标的列数
        while (lower < upper) {
            int mid = lower + (upper - lower) / 2;//从中间开始
            boolean isOne = false;//标识是否找到“1”
            for (int i = min; i < max; i++) {//由于是找列的最小最大值，所以需要遍历每一行，min是最小行，max是最大行
                if ((isHorizontal ? image[i][mid] : image[mid][i]) == '1') {
                    //image[i][mid]在某一行找列的1出现的最小最大范围，
                    //image[mid][i]在某一列找行的1出现的最小最大范围
                    isOne = true;
                    break;
                }
            }
            if (isOne == goLower) {
                upper = mid;
            } else {
                lower = mid + 1;
            }
        }
        return lower;
    }
}
//Solution2 DFS
public class Solution {
    private int minX = Integer.MAX_VALUE;
    private int maxX = 0;
    private int minY = Integer.MAX_VALUE;
    private int maxY = 0;
    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0].length == 0) {
            return 0;
        }
        dfs(image, x, y);
        return (maxX - minX + 1) * (maxY - minY + 1);
    }
    
    public void dfs(char[][] image, int x, int y) {
        if (x < 0 || x > image.length - 1 || y < 0 || y > image[0].length - 1 || image[x][y] == '0') {
            return;
        }
        image[x][y] = '0';
        minX = Math.min(minX, x);
        maxX = Math.max(maxX, x);
        minY = Math.min(minY, y);
        maxY = Math.max(maxY, y);
        dfs(image, x + 1, y);
        dfs(image, x - 1, y);
        dfs(image, x, y + 1);
        dfs(image, x, y - 1);
    }
}