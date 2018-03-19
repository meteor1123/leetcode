/*
	Find Median from Data Stream
	Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

	Examples: 
	[2,3,4] , the median is 3

	[2,3], the median is (2 + 3) / 2 = 2.5

	Design a data structure that supports the following two operations:

	void addNum(int num) - Add a integer number from the data stream to the data structure.
	double findMedian() - Return the median of all elements so far.
	For example:

	add(1)
	add(2)
	findMedian() -> 1.5
	add(3) 
	findMedian() -> 2
	Tags: Heap, Design
*/

/*
	Max-heap small has the smaller half of the numbers.
	Min-heap large has the larger half of the numbers.
	This gives me direct access to the one or two middle values (they're the tops of the heaps), 
	so getting the median takes O(1) time. And adding a number takes O(log n) time.

	Supporting both min- and max-heap is more or less cumbersome, depending on the language, s
	o I simply negate the numbers in the heap in which I want the reverse of the default order. 
	To prevent this from causing a bug with -231 (which negated is itself, when using 32-bit ints), I use integer types larger than 32 bits.

	Using larger integer types also prevents an overflow error when taking the mean of the two middle numbers. 
	I think almost all solutions posted previously have that bug.
*/

/*
	思路:
		首先用large（最小堆）来存放num，之后large出列num，将-num存放到small中，因为small存放的是负数，因此可以成是一个最大堆
		根据large 和 small的 size来回倒腾，如果large的size 小于small.size 则将-small.peek()放入large中

		large和small的size之差不会超过1,

		形如small = (-1, -2, -3)
		large = (6, 5, 4)
		保证两个peek一定是median（size相等时,偶数长度）
		large的peek是median（size不等时，奇数长度）
*/

//Solution1 prefer
class MedianFinder {

    // Adds a number into the data structure.
    PriorityQueue<Integer> min = new PriorityQueue<>();
    PriorityQueue<Integer> max = new PriorityQueue<>(1000, Collections.reverseOrder());
    public void addNum(int num) {
        max.offer(num);
        min.offer(max.poll());
        if (max.size() < min.size()) {
            max.offer(min.poll());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (max.size() == min.size()) {
            return (max.peek() + min.peek()) / 2.0;
        } else {
            return max.peek();
        }
    }
};


//Solution2 stefan, store negative
class MedianFinder {
	 private Queue<Long> small = new PriorityQueue<Long>();
	 private Queue<Long> large = new PriorityQueue<Long>(); 
	 public void addNum(int num) {
	 	large.add((long)num);
	 	small.add(-large.poll());
	 	if (large.size() < small.size()) {
	 		large.offer(-small.poll());
	 	}
	 }
	 public double findMedian() {
	 	if (large.size() > small.size()) {
	 		return large.peek();
	 	} else {
	 		return (large.peek() - small.peek()) / 2.0;
	 	}
	 }
};





