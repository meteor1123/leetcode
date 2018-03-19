/*
	Sliding Window Maximum
	Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

	For example,
	Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

	Window position                Max
	---------------               -----
	[1  3  -1] -3  5  3  6  7       3
	 1 [3  -1  -3] 5  3  6  7       3
	 1  3 [-1  -3  5] 3  6  7       5
	 1  3  -1 [-3  5  3] 6  7       5
	 1  3  -1  -3 [5  3  6] 7       6
	 1  3  -1  -3  5 [3  6  7]      7
	Therefore, return the max sliding window as [3,3,5,5,6,7].

	Note: 
	You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

	Follow up:
	Could you solve it in linear time?
*/

//Solution 0 pre, myself:  sliding window + priorityQueue, 
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length)
            return new int[]{};
        int m = nums.length;
        int[] res = new int[m - k + 1];
        
        // max heap
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> b - a);
        
        int start = 0;
        int end = 0;
        
        while (end < nums.length) {
            pq.offer(nums[end]);
            if (pq.size() == k) {
                res[start] = pq.peek();
                pq.remove(nums[start]);
                start++;
            } 
            end++;
        }
        
        return res;
    }
}
//Solution1
public class Solution {
	public int[] maxSlidingWindow(int[] nums, int k) {
		int n = nums.length;
		if (nums == null || k <= 0) {
			int[] res = new int[0];
			return res;
		}
		int[] res = new int[n - k + 1];
		Deque<Integer> queue = new ArrayDeque<>();
		for (int i = 0; i < nums.length; i++) {
			if (!queue.isEmpty() && queue.peek() < i - k + 1) {
				queue.poll();
			}

			while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
				queue.pollLast();
			}

			queue.offer(i);
			if (i >= k - 1) {
				res[i - k + 1] = nums[queue.peek()];
			}
		}
		return res;
	}
}


//Solution return use ArrayList
public class Solution {
    /**
     * @param nums: A list of integers.
     * @return: The maximum number inside the window at each moving.
     */
    public ArrayList<Integer> maxSlidingWindow(int[] nums, int k) {
        // write your code here
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (nums.length == 0) {
            return res;
        }
        
        Queue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>(){
            @Override
            public int compare(Integer i1, Integer i2){
                return Integer.compare(i2, i1);
            }
        });
        
        for (int i = 0; i < k; i++) {
            pq.add(nums[i]);
        }
        
        res.add(pq.peek());
        for (int i = k; i < nums.length; i++) {
            pq.remove(res.get(i - k));
            pq.add(res.get(i));
            res.add(pq.peek());
        }
        return res;
    }
}


