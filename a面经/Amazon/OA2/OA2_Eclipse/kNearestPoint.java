package OA2;

import java.util.PriorityQueue;
import java.util.Comparator;
public class kNearestPoint {
	public static Point[] Solution(Point[] array, int k) {
		Point[] rvalue = new Point[k];
		int index = 0;
		PriorityQueue<Point> pq = new PriorityQueue<Point> (k, new Comparator<Point>() {
			@Override
			public int compare(Point a, Point b) {
				if (getDistance(a) < getDistance(b)) {
					return 1;
				} else if (getDistance(a) > getDistance(b)) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		for (int i = 0; i < array.length; i++) {
			pq.offer(array[i]);
			if (pq.size() > k)
				pq.poll();
		}
		while (!pq.isEmpty()) {
			rvalue[rvalue.length - index - 1] = pq.poll();
			index++;
		}
		return rvalue;
	}
	private static double getDistance(Point a) {
		return Math.sqrt((a.x  * a.x) + (a.y * a.y));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point p1 = new Point(1, 1);
		Point p2 = new Point(1, 2);
		Point p3 = new Point(2, 2);
		Point p4 = new Point(1, 0);
		Point p5 = new Point(3, 0);
		Point p6 = new Point(3, 2);
//		Point p1 = new Point(1, 1);
		Point[] nums = {p1, p2, p3, p4, p5, p6};
		Point[] res = Solution(nums, 4);
		for (int i = 0; i < res.length; i++) {
			System.out.print("[" + res[i].x + "," + res[i].y + "]");
		}
	}

}

class Point {
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
