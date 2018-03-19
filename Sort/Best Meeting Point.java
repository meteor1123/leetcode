/*
	Best Meeting Point
	A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. 
    The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

	For example, given three people living at (0,0), (0,4), and (2,2):

	1 - 0 - 0 - 0 - 1
	|   |   |   |   |
	0 - 0 - 0 - 0 - 0
	|   |   |   |   |
	0 - 0 - 1 - 0 - 0
	The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.

	Hint:

	Try to solve it in one dimension first. How can this solution apply to the two dimension case?
	Answer: find the median of these point
*/

/*
	Solution:
		1. 如何找到2D 图中各个点的 最短中间距离？find the median！
		2. 因为是二维坐标， 因此我们需要找到所有居民点在x轴上的median以及y轴上的median， 比如 (0, 0), (0, 4) , (2, 2)， X轴（0, 0, 2)， Y轴（0, 2 , 4)
			注意Y轴需要排序，因为原始的数据无序， medianX = 0， medianY = 2；
		3. 将每个坐标点和median进行res += Math.abs(pointX-  medianX) + Math.abs(pointY - medianY),就是最短距离，注意并不需要求出这个点，只需要求出距离！
		4. 小技巧，求一组数中所有数与median的差，
			正常方法: 先找出median = size() - 2(这里的median如果是偶数，中间两个数任意一个都可以),再对每个点对median做abs差值相加
			文艺方法: list(end--) - list(start++);
*/

public class Solution {
    public int minTotalDistance(int[][] grid) {
        List<Integer> I = new ArrayList<>();
        List<Integer> J = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    I.add(i);
                    J.add(j);
                }
            }
        }
        return minDistance(I) + minDistance(J);
    }
    

    //计算每个点对median 点的distance
    public int minDistance(List<Integer> list) {
        Collections.sort(list);
        int start = 0;
        int end = list.size() - 1;
        int sum = 0;
        while (start < end) {
            sum += list.get(end--) - list.get(start++); 
        }
        return sum;
    }
}