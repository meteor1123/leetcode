/*
	Meeting Rooms
	Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

	For example,
	Given [[0, 30],[5, 10],[15, 20]],
	return false.
	Tags:Sort
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
public class Solution {

    //Solution1
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return true;
        }
        
        Comparator<Interval> comp = new Comparator<Interval>(){
            @Override
            public int compare(Interval interval1, Interval interval2) {
                return interval1.end - interval2.end;//这样的排序结果是 end 小的在数组前面，同时会自动排序start，小的也在前面
            }
        };
        Arrays.sort(intervals, comp);
        
        Interval pre = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < pre.end) {
                return false;
            }
            pre = intervals[i];
        }
        return true;
    }

    //Solution2
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return true;
        }
        
        Comparator<Interval> comp = new Comparator<Interval>(){
            @Override
            public int compare(Interval interval1, Interval interval2) {
                return interval1.start - interval2.start;//根据开始时间排序
            }
        };
        Arrays.sort(intervals, comp);
        
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i - 1].end > intervals[i].start) { //前面会议结束得比后面会议开始得晚，false
                return false;
            }
        }
        return true;
    }
}