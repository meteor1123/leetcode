/*
	 Number of Airplanes in the Sky
	 Given an interval list which are flying and landing time of the flight. How many airplanes are on the sky at most?
	 Example
	 For interval list [[1,10],[2,3],[5,8],[4,7]], return 3

	 Note
	 If landing and flying happens at the same time, we consider landing should happen at first.
 */


/*
	Key Point: 1. 可以把所有的飞机的起始时间看成'(',结束时间看成')',问题就转化成了括号匹配的问题了
			   2. 创建一个Point class， 两个属性，time，flag， 
			   3. 把（start， end）转换为Point->(start, 1), (end, 0), 1 标识着起点，0标识着终点
 */
 public class Solution {
     public int countOfAirplanes(List<Interval> airplanes) { 
         // write your code here
         if (airplanes == null || airplanes.size() == 0) {
             return 0;
         }
         List<Point> list = new ArrayList<>();
         for (Interval i : airplanes) {
             list.add(new Point(i.start, 1));
             list.add(new Point(i.end, 0));
         }
         Collections.sort(list, new Comparator<Point>(){
             @Override
             public int compare(Point p1, Point p2) {
                if (p1.time == p2.time) {
                    return p1.flag - p2.flag;
                } else {
                    return p1.time - p2.time;
                }
             } 
         });
         int count = 0;
         int res = 0;
         for (Point p : list) {
             if (p.flag == 1) {
                 count++;
             } else {
                 count--;
             }
             res =  Math.max(count, res);
         }
         return res;
     }
}

class Point {
     int time;
     int flag;
     
     Point(int time, int flag) {
         this.time = time;
         this.flag = flag;
     }

 }