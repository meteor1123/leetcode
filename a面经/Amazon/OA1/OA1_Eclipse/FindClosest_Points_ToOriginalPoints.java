package OA1;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class FindClosest_Points_ToOriginalPoints {
	public ArrayList<Point> findClosest(ArrayList<Point> points, int k, final Point target) {
		ArrayList<Point> res = new ArrayList<Point>();
		PriorityQueue<Point> pq = new PriorityQueue<Point>(k, new Comparator<Point>(){
			@Override
			public int compare(Point p1, Point p2) {
				double dist1 = Math.sqrt(Math.pow(p1.x - target.x, 2) + Math.pow(p1.y - target.y, 2));
				double dist2 = Math.sqrt(Math.pow(p2.x - target.x, 2) + Math.pow(p2.y - target.y, 2));
				return (int)(dist2 - dist1);
			}
		});
		for (Point p : points) {
			pq.offer(p);
			if (pq.size() == k + 1) {
				pq.poll();
			}
		}
		res.addAll(pq);
		return res;
	}
	public Point[] Solution(Point[] array, Point origin, int k) {
		Point[] res = new Point[k];
		int index = 0;
		PriorityQueue<Point> pq = new PriorityQueue<Point> (k, new Comparator<Point> () {
			@Override
			public int compare(Point a, Point b) {
				return (int) (getDistance(a, origin) - getDistance(b, origin));
			}
		});
		for (int i = 0; i < array.length; i++) {
			pq.offer(array[i]);
			if (pq.size() > k)
				pq.poll();
		}
		while (!pq.isEmpty())
			res[index++] = pq.poll();
		return res;
	}
	private double getDistance(Point a, Point b) {
		return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Point> points = new ArrayList<>();
		Point origin = new Point(0, 0);
        points.add(new Point(1, 0));
        points.add(new Point(2, 3));
        points.add(new Point(3, 2));
        points.add(new Point(4, 0));
        points.add(new Point(1, 1));
        points.add(new Point(2, 2));
        points.add(new Point(-1, 0));
        points.add(new Point(1, -1));
        points.add(new Point(1, 1));
        FindClosest_Points_ToOriginalPoints test = new FindClosest_Points_ToOriginalPoints();
        ArrayList<Point> res = test.findClosest(points, 2, origin);
        for (Point p : res) {
        	System.out.println(p.toString());
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