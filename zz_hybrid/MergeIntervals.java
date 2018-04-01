package LeetCode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Comparator;

public class MergeIntervals {
	public List<Interval> merge(List<Interval> intervals) {
		LinkedList<Interval> helper = new LinkedList<>();
		Collections.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval e1, Interval e2) {
				if (e1.start == e2.start) {
					return 0;
				}
				return e1.start < e2.start? -1 : 1;
			}
		});
		for (Interval e : intervals) {
			if (helper.isEmpty() || e.start > helper.getLast().end) {
				helper.add(e);
			} else {
				helper.getLast().end = Math.max(helper.getLast().end, e.end);
			}
		}
		return helper;
	}
	
	class Interval {
		int start;
		int end;
		Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
/*	
	public static void main(String[] args) {
		MergeIntervals test = new MergeIntervals();
		Interval a = new Interval(1,3);
		Interval b = new Interval(2,6);
		Interval c = new Interval(8,10);
		Interval d = new Interval(15,18);
		Interval e = new Interval(16,21);
		Interval f = new Interval(4,9);
		List<Interval> res = new LinkedList<>(); 
	}
*/
}
