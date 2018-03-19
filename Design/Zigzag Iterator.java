/*
	Zigzag Iterator
	Given two 1d vectors, implement an iterator to return their elements alternately.

	For example, given two 1d vectors:

	v1 = [1, 2]
	v2 = [3, 4, 5, 6]

	By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].

	Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

	Clarification for the follow up question - Update (2015-09-18):
	The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example, given the following input:

	[1,2,3]
	[4,5,6,7]
	[8,9]
	It should return [1,4,8,2,5,9,3,6,7].
*/
//Solution1
public class ZigzagIterator {
    //给List v1, v2 添加iterator
    private Iterator<Integer> i, j, temp;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        i = v1.iterator();
        j = v2.iterator();
        
    }
    //这里的if语句实现zigzag的功能，交替的返回v1 以及v2的值
    public int next() {
        if (i.hasNext()) {
            temp = i;
            i = j;
            j = temp;
        }
        return j.next();
    }
    public boolean hasNext() {
        return i.hasNext() || j.hasNext();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */

//Solution2
public class ZigzagIterator {
    Iterator<Integer>[] its;
    int pos;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        its = new Iterator[]{v1.iterator(), v2.iterator()};
        pos = 0;
    }
    public int next() {
        int next = its[pos].next();
        pos = (pos == its.length - 1) ? 0 : pos + 1;
        return next;
    }
    public boolean hasNext() {
        if (its[pos].hasNext()) {
            return true;
        }
        for (int i = pos + 1; i < its.length; i++) {
            if (its[i].hasNext()) {
                pos = i;
                return true;
            }
        }
        
        for (int i = 0; i < pos; i++) {
            if (its[i].hasNext()) {
                pos = i;
                return true;
            }
        }
        return false;
    }
}
