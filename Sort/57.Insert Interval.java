/*
    Insert Interval 
    Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
    You may assume that the intervals were initially sorted according to their start times.

    Example 1:
    Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

    Example 2:
    Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

    This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
    Tag: Array, Sort.
*/

/*
    Solution: 1.We divide this problem to two part,which can easily solve.
        
        part1: old and new element are non-overlapping:
                [          ]  [         ]   or [         ]  [         ]
                    old           new               new          old

        part2: old and new element are overlapping,either old in the front or new:
                [        [ overlapping ]        ]   or     [        [ overlapping ]        ]
                    old                   new                  new                   old
 */


public class Solution {
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> res = new ArrayList<Interval>();


        for (Interval old : intervals) {
            //part1 old in the front
            if (old.end < newInterval.start) {
                res.add(old);
            //part1 new in the front
            } else if (newInterval.end < old.start) {
                res.add(newInterval);
                newInterval = old;
            //paert2 has overlapping
            } else {
                Interval insertval = new Interval();
                insertval.start = Math.min(old.start, newInterval.start);
                insertval.end = Math.max(old.end, newInterval.end);
                newInterval = insertval; //create a new Interval, but no hurry to insert to the ArrayList,just replace it to a newInterval;
            }
        }
        res.add(newInterval);
        return res;
    }
}