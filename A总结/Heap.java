/*
	Heap
*/




1. Array Problem
		1.1 Kth Largest Element In An Array
			public class Solution {
			    public int findKthLargest(int[] nums, int k) {
			        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
			        for (int i = 0; i < nums.length; i++) {
			            pq.offer(nums[i]);
			            if (pq.size() > k) {
			                pq.poll();
			            }
			        }
			        return pq.peek();
			    }
			}
		1.2 Meeting Rooms II
			/*
				Meeting Rooms II
				Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

				For example,
				Given [[0, 30],[5, 10],[15, 20]],
				return 2.
				Tags: Heap, Greedy, Sort
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
			public class Solution {
			    public int minMeetingRooms(Interval[] intervals) {
			        if (intervals == null || intervals.length == 0) {
			            return 0;
			        }
			        // Sort the intervals by start time
			        Comparator<Interval> comp = new Comparator<Interval>() {
			            @Override
			            public int compare(Interval interval1, Interval interval2) {
			                return interval1.start - interval2.start;
			            }
			        };
			        Arrays.sort(intervals, comp);
			        // Use a min heap to track the minimum end time of merged intervals
			        PriorityQueue<Interval> minHeap = new PriorityQueue<Interval>(intervals.length, new Comparator<Interval>() {
			            public int compare(Interval a, Interval b) {
			                return a.end - b.end;
			            }
			        });
			        // start with the first meeting, put it to a meeting room
			        minHeap.offer(intervals[0]);
			        for (int i = 1; i < intervals.length; i++) {
			            // get the meeting room that finishes earliest
			            Interval interval = minHeap.poll();
			            
			            // if the current meeting starts right after 
			            // there's no need for a new room, merge the interval
			            if (intervals[i].start >= interval.end) {
			                interval.end = intervals[i].end;
			            } else {
			                // otherwise, this meeting needs a new room
			                minHeap.offer(intervals[i]);
			            }
			            //don't forget to put the meeting room back
			            minHeap.offer(interval);
			        }
			        return minHeap.size();
			    }
			}

		1.3 Sliding Window Maximum
			//solution1: O(nlogk)
			public class Solution {
			    public int[] maxSlidingWindow(int[] nums, int k) {
			        int len = nums.length;
			        int[] res = new int[len - k + 1];
			        if (nums.length == 0) {
			            return new int[0];
			        }
			        //maxHeap
			        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
			            @Override
			            public int compare(Integer i1, Integer i2) {
			                return i2 - i1;
			            }
			        });
			        
			        for (int i = 0; i < k; i++) {
			            pq.add(nums[i]);
			        }
			        
			        res[0] = pq.peek();
			        for (int i = k; i < len; i++) {
			            pq.remove(nums[i - k]);
			            pq.add(nums[i]);
			            res[i - k + 1] = pq.peek();
			        }
			        return res;
			    }
			}
			//Solution2: O(n),best
			public class Solution {
				public int[] maxSlidingWindow(int[] nums, int k) {
					int n = nums.length;
					if (nums == null || k <= 0) {
						int[] res = new int[0];
						return res;
					}
					int[] res = new int[n - k + 1];
					int j = 0;

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
							res[j++] = nums[queue.peek()];
						}
					}
					return res;
				}
			}
		1.4 Find Median From Data Stream
			/*
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
			*/
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
		1.5 The Skyline Problem
			/*
				A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance.
				 Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).

				Buildings  Skyline Contour
				The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], 
				where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. 
				It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. 
				You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

				For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

				The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. 
				A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, a
				nd always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

				For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

				Notes:

				The number of buildings in any input list is guaranteed to be in the range [0, 10000].
				The input list is already sorted in ascending order by the left x position Li.
				The output list must be sorted by the x position.
				There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; 
				the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
			*/
			/*
			    1. 先按照左边界的x坐标进行排序，小的在前, x坐标相等y坐标小的在前。
			    2. 将边界存入heights数组，可以将左边界或者右边界的高设为负，方便确定边界，我们这里将左边界的高设为负
			    2. 设置一个priorityQueue, 最大堆， 堆顶每次都是最大高度
			    3. 遍历边界数组heights，
			        1）假如遇到该点的高为负，则将高度取正加入堆
			        2）假如遇到该点的高度为正，说明遇到右边界，这个范围已经遍历完，将该高度移出堆
			    4. 我们用pre和cur去验证之前的最高高度pre 和加入新点以后 现在正在遍历的点的高度是否一样，
			        如果不一样，有两种情况
			        1）遇到之前最高点的右边界，
			        2）遇到更高的点，
			*/
			public class Solution {
			    public List<int[]> getSkyline(int[][] buildings) {
			        List<int[]> res = new ArrayList<>();
			        List<int[]> height = new ArrayList<>();
			        
			        //构建顶点列表
			        for (int[] b : buildings) {
			            height.add(new int[]{b[0], -b[2]}); //用负的高度表示左边界点
			            height.add(new int[]{b[1], b[2]});//正高度表示右边界点
			        }
			        
			        Collections.sort(height, new Comparator<int[]>() {
			           @Override
			           public int compare(int[] a, int[] b) {
			               if (a[0] != b[0]) {
			                   return a[0] - b[0];
			               } else {
			                   return a[1] - b[1];
			               }
			           }
			        });
			        
			        Queue<Integer> pq = new PriorityQueue<Integer>(11, new Comparator<Integer>() {
			            public int compare(Integer i1, Integer i2) {
			                return i2 - i1;
			            }
			        });
			        pq.offer(0);
			        int preHeight = 0;//
			        for (int[] h : height) {
			            if (h[1] < 0) {//假如遇到左边界点，将该范围的高度进栈
			                pq.offer(-h[1]);
			            } else {
			                pq.remove(h[1]);//假如遇到右边界点，将该范围的高度出栈
			            }
			            int curHeight = pq.peek();//pq这里是max heap， 如果上面进栈点的高度是大于之前的pre，或者最高的高度点已经出栈
			                                      //则会出现preHeight != curHeight的情况，这时候我们需要更新新的高度范围，h[0](遍历到的新高度范围起点)，curHeight（新高度）
			            if (preHeight != curHeight) {
			                res.add(new int[]{h[0], curHeight});
			                preHeight = curHeight;
			            }
			        }
			        return res;
			    }
			}



2. LinkedList
		2.1 Merge K Sorted Lists
			public ListNode mergeKLists(ListNode[] lists) {  
		        // write your code here
		        if (lists == null || lists.length == 0) {
		            return null;
		        }
		        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(new Comparator<ListNode>(){
		            @Override
		            public int compare(ListNode l1, ListNode l2) {
		                return l1.val - l2.val;
		            }
		        });
		        ListNode dummy = new ListNode(0);
		        ListNode tail = dummy;
		        for (ListNode node : lists) {
		            if (node != null) {
		                queue.offer(node);
		            }
		        }
		        
		        while (!queue.isEmpty()) {
		            tail.next = queue.poll();
		            tail = tail.next;
		            if (tail.next != null) {
		                queue.add(tail.next);
		            }
		        }
		        return dummy.next;
		    }


3. Number, Math
		3.1 Ugly Number II
		/*
			Write a program to find the n-th ugly number.
			Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
			For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
			Note that 1 is typically treated as an ugly number.
		*/
			public class Solution {
			    public int nthUglyNumber(int n) {
			        if (n == 1) {
			            return 1;
			        }
			        PriorityQueue<Long> pq = new PriorityQueue<>();
			        pq.offer(1l);
			        for (long i = 1; i < n; i++) {
			            long temp = pq.poll();
			            while (!pq.isEmpty() && pq.peek() == temp) {
			                pq.poll();//remove the duplicate
			            }
			            pq.add(temp * 2);
			            pq.add(temp * 3);
			            pq.add(temp * 5);
			        }
			        return pq.poll().intValue();
			    }
			}