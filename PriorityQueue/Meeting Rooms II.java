/*
	Meeting Rooms II
	Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

	For example,
	Given [[0, 30],[5, 10],[15, 20]],
	return 2.
	Tags: Heap, Greedy, Sort
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

/*
    Solution：
        1. 先按照interval.start对interval进行排序
        2. 建一个PriorityQueue，按照interval的end建最小堆，也就是最小的end在前面
        3. 将排序后的intervals数组取出逐一和最小堆的堆顶interval进行比对，
            为什么用最小堆，因为此时取出的堆顶元素的pre.start绝对比now.start要小（排序后的数组），
            我们只需要比较now.start he pre.end
            3.1 如果now.start比pre.end要大，意味着可以共用一个会议室，merge这个时间段再放入最小堆
            3.2 如果now.start比pre.end要小，意味着时间重叠，两个都要加入最小堆
            注意最小堆每次取出的end都是堆顶最小，所以不可能出现 pre（10, 20)  now(22,25) (after20,23  这样忽略的时间段
*/
class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;
        Arrays.sort(intervals, (a, b) -> a.start - b.start);
        
        PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.end - b.end);
        
        pq.offer(intervals[0]);
        
        for (int i = 1; i < intervals.length; i++) {
            Interval pre = pq.poll();
            if (intervals[i].start >= pre.end) {
                pre.end = intervals[i].end;
            } else {
                pq.offer(intervals[i]);
            }
            pq.offer(pre);
        }
        return pq.size();
    }
}