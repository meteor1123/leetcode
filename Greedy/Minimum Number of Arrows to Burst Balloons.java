/*
	Minimum Number of Arrows to Burst Balloons

	There are a number of spherical balloons spread in two-dimensional space. For each balloon, provided input is the start and end coordinates of the horizontal diameter. Since it's horizontal, y-coordinates don't matter and hence the x-coordinates of start and end of the diameter suffice. Start is always smaller than end. There will be at most 104 balloons.

	An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend. There is no limit to the number of arrows that can be shot. An arrow once shot keeps travelling up infinitely. The problem is to find the minimum number of arrows that must be shot to burst all balloons.

	Example:

	Input:
	[[10,16], [2,8], [1,6], [7,12]]

	Output:
	2

	Explanation:
	One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 (bursting the other two balloons).
*/

/*

	题意解析
	在二维空间中有许多的圆形气球。对于每个气球，给出了该气球的在横坐标上的起始位置和终止位置，而且起始位置始终小于终止位置。
	现在开始射箭，箭可以在横坐标不同点上垂直向上发射。如果箭的起始点为x，如果x在气球的起始位置和终止位置之间，那么气球就会爆炸。
	现在有不限数量的箭可以发射。假设一直箭每次可以向上发射到无穷远处。现在的问题是，求出可以让所有气球爆炸的最少箭数。

	解法分析
	这道题目是活动选择问题(Activity-Selection Problem)的变形。活动选择问题是《算法导论》里面关于贪心算法的第一个问题。
	这个问题是这样的。有一组活动，每个活动都有一个开始时间S和结束时间F，假设一个人在同一时间只能参加一个活动，找出出一个人可以参加的最多的活动数量。例如。

	假设现在有6个活动，下面是6个活动的起始和结束时间。 
	     start[]  =  {1, 3, 0, 5, 8, 5};
	     finish[] =  {2, 4, 6, 7, 9, 9};
				一个人一天最多能参见的活动为
				{0, 1, 3, 4}
	关于活动选择问题就不在详细解说了。这道题非常算是变形。将所有的气球按照终止位置排序，开始从前向后扫描。以第一个气球的终止位置为准，只要出现的气球起始位置小于这个气球的终止位置，代表可以一箭使这些气球全部爆炸；
	当出现一个气球的起始位置大于第一个气球的终止位置时再以这个气球的终止位置为准，找出所有可以再一箭爆炸的所有气球；以此类推。。。

*/
class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0)
            return 0;
        Arrays.sort(points, (a, b) -> a[1] - b[1]);
        
        long lastEnd = Long.MIN_VALUE;
        int res = 0;
        
        for (int[] point : points) {
            if (lastEnd < point[0]) {
                lastEnd = point[1];
                res++;
            }
        }
        return res;
    }
}
