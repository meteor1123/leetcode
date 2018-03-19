/*
	Given a collection of intervals, merge all overlapping intervals.

	For example,
	Given [1,3],[2,6],[8,10],[15,18],
	return [1,6],[8,10],[15,18].
	Tags:
*/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { 
 *			start = 0; end = 0; }
 *     Interval(int s, int e) {
 *			 start = s; end = e; }
 * }
 */

 public class Solution {
     public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
    	ArrayList<Interval> res = new ArrayList<Interval>();
    	if (intervals == null || intervals.size() <= 1)
    		return intervals;
    	Comparator<Interval> comp = new Comparator<Interval>() {
    		@Override
    		public int compare(Interval l1, Interval l2) {
    			if (l1.start == l2.start)
    				return l1.end - l2.end;
    			else 
    				return l1.start - l2.start;
    		}
    	};

    	//implement the sort interface
    	Collections.sort(intervals, comp);
    	res.add(intervals.get(0));
    	for (int i = 1; i < intervals.size(); i++) {
    		//每次取res list里的最后一个数和intervals里的比
    		if (res.get(res.size() - 1).end >= intervals.get(i).start)
    			res.get(res.size() - 1).end = Math.max(res.get(res.size() - 1).end, intervals.get(i).end);
    		else 
    			res.add(intervals.get(i));
    	}
    	return res;
    }
 }