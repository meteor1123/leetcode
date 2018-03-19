/*
	Moving Average from Data Stream   

	Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

	For example,
	MovingAverage m = new MovingAverage(3);
	m.next(1) = 1
	m.next(10) = (1 + 10) / 2
	m.next(3) = (1 + 10 + 3) / 3
	m.next(5) = (10 + 3 + 5) / 3
*/

public class MovingAverage {

    /** Initialize your data structure here. */
    Queue<Integer> queue;
    int len;
    public MovingAverage(int size) {
        queue = new LinkedList<>();
        len = size;
    }
    
    public double next(int val) {
        queue.offer(val);
        double res = 0;
        if (queue.size() == len) {
            queue.poll();
        }
        for (int num : queue) {
            res += num;
        }
        return res / len;
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */