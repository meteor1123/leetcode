/*
	Merge Intervals
	Given a collection of intervals, merge all overlapping intervals.

	For example,
	Given [1,3],[2,6],[8,10],[15,18],
	return [1,6],[8,10],[15,18].
	Tags: Array, Sort
*/
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */


// lambda sort prefer
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList();
        
        if (intervals == null || intervals.size() == 0)
            return res;
        
        intervals.sort((Interval a, Interval b) -> {
            if (a.start == b.start) 
                return b.end - b.end;
            else 
                return a.start - b.start;
        });
  
        res.add(intervals.get(0));
        
        for (int i = 1; i < intervals.size(); i++) {
            Interval pre = res.get(res.size() - 1);
            Interval cur = intervals.get(i);
            if (pre.end >= cur.start) {
                pre.end = Math.max(pre.end, cur.end);
            } else {
                res.add(cur);
            }
        }
        
        return res;
    }
}


public class Solution {
    //Solution1
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) {
            return res;
        }
        
        //Overrid the compare, and make a comparator
        Collections.sort(intervals, new Comparator<Interval>(){
           @Override
           public int compare(Interval interval1, Interval interval2) {
               return interval1.start - interval2.start;
           }
        });
        
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        

        //遍历intervals
        //假如 interval.start <= end, 先不加入res, (start,  interval.start   Math.max(end, interval.end)), 取end最大值
        //和下一个比，如果interval.start > end, 说明 和上一个interval没有重合区域，直接将上一个interval加入res
        for (Interval interval : intervals) {
            if (interval.start <= end) {
                end = Math.max(end, interval.end);
            } else {
                res.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }
        
        res.add(new Interval(start, end));
        return res;
    }
}

//Solution2
public class Solution {
    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        ArrayList<Interval> res = new ArrayList<Interval>();
        if (intervals == null || intervals.size() <= 1)
            return intervals;
        //Comparator 实现排序，但是这里需要重写排序方法
        Comparator<Interval> comp = new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {// sort the Interval by start
                if(i1.start == i2.start) 
                    return i1.end - i2.end;
                return i1.start - i2.start;
            }
        };
        Collections.sort(intervals, comp);
        res.add(intervals.get(0));//加入排序后的第一个数
        for(int i = 1; i < intervals.size(); i++) {//从第二个数开始比较
            if(res.get(res.size() - 1).end >= intervals.get(i).start) {//始终从list的最后一个数的end和未加入的数的start进行比较
                res.get(res.size() - 1).end = Math.max(res.get(res.size() - 1).end, intervals.get(i).end);  
            } else 
                res.add(intervals.get(i));  
        }
        return res;
    }
}

//Solution3: prefer
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) {
            return res;
        }
        Collections.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });
        res.add(intervals.get(0));
        for (int i = 1; i < intervals.size(); i++) {
            Interval pre = res.get(res.size() - 1);
            Interval cur = intervals.get(i);
            if (pre.end >= cur.start) {
                pre.end = Math.max(cur.end, pre.end);
            } else {
                res.add(cur);
            }
        }
        return res;
    }
}