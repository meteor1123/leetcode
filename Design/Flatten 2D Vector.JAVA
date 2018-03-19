/*
	Flatten 2D Vector
	Flatten 2D Vector My Submissions Question Solution 
	Total Accepted: 1639 Total Submissions: 5875 Difficulty: Medium
	Implement an iterator to flatten a 2d vector.

	For example,
	Given 2d vector =

	[
	  [1,2],
	  [3],
	  [4,5,6]
	]
	By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].

	Hint:

		How many variables do you need to keep track?
		Two variables is all you need. Try with x and y.
		Beware of empty rows. It could be the first few rows.
		To write correct code, think about the invariant to maintain. What is it?
		The invariant is x and y must always point to a valid point in the 2d vector. Should you maintain your invariant ahead of time or right when you need it?
		Not sure? Think about how you would implement hasNext(). Which is more complex?
		Common logic in two different places should be refactored into a common method.
		Follow up:
		As an added challenge, try to code it using only iterators in C++ or iterators in Java.
*/


public class Vector2D {
    private Iterator<List<Integer>> i;
    private Iterator<Integer> j;
    public Vector2D(List<List<Integer>> vec2d) {
        i = vec2d.iterator();
    }

    public int next() {
        hasNext();
        return j.next();
    }

    public boolean hasNext() {
        while ((j == null || !j.hasNext()) && i.hasNext()) {
            j = i.next().iterator();
        }
        return j != null && j.hasNext();
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */


public class Vector2D {
	//分两个Iterator，一个用来遍历vec2d，一个用来遍历子 List<Integer>中的元素
    private Iterator<List<Integer>> vec2d;
    private Iterator<Integer> tempList;
    public Vector2D(List<List<Integer>> vec2d) {
        this.vec2d = vec2d.iterator();
    }

    public int next() {
        hasNext();
        return tempList.next();
    }

    public boolean hasNext() {
        while ((tempList == null || !tempList.hasNext()) && vec2d.hasNext()) {
            tempList = vec2d.next().iterator();
        }
        return tempList != null && tempList.hasNext();
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
