/*
	Design 
*/



/*
	Java中的Iterator功能比较简单，并且只能单向移动：
　　(1) 使用方法iterator()要求容器返回一个Iterator。第一次调用Iterator的next()方法时，它返回序列的第一个元素。注意：iterator()方法是java.lang.Iterable接口,被Collection继承。
　　(2) 使用next()获得序列中的下一个元素。
　　(3) 使用hasNext()检查序列中是否还有元素。
　　(4) 使用remove()将迭代器新返回的元素删除。

	
	list l = new ArrayList();
	l.add("aa");
	l.add("bb");
	l.add("cc");
	for (Iterator iter = l.iterator(); iter.hasNext();) {
	  	String str = (String)iter.next();
	 	System.out.println(str);
	}
	/*迭代器用于while循环
	Iterator iter = l.iterator();
	while(iter.hasNext()){
		String str = (String) iter.next();
	 	System.out.println(str);
	}
*/

1. Iterator Problem
		1.1 List Iterator
			1.1.1 Zigzag Iterator
				/*
					For example, given two 1d vectors:
					v1 = [1, 2]
					v2 = [3, 4, 5, 6]
					By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].

					The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example, given the following input:

					[1,2,3]
					[4,5,6,7]
					[8,9]
					It should return [1,4,8,2,5,9,3,6,7].
				*/
				/**
				 * Your ZigzagIterator object will be instantiated and called as such:
				 * ZigzagIterator i = new ZigzagIterator(v1, v2);
				 * while (i.hasNext()) v[f()] = i.next();
				 */
				//Simple Version
				public class ZigzagIterator {
				    private Iterator<Integer> i1, i2, temp;
				    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
				        i1 = v1.iterator();
				        i2 = v2.iterator();
				    }
				    public int next() {
				        if (i1.hasNext()) {
				            temp = i1;
				            i1 = i2;
				            i2 = temp;
				        }
				        return i2.next();
				    }
				    public boolean hasNext() {
				        return i1.hasNext() || i2.hasNext();
				    }
				}

				//Can be used to k-D vector
				public class ZigzagIterator {
				    private LinkedList<Iterator> queue;
				    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
				        queue = new LinkedList<Iterator>();
				        if (!v1.isEmpty()) {
				            queue.offer(v1.iterator());
				        }
				        if (!v2.isEmpty()) {
				            queue.offer(v2.iterator());
				        }
				    }
				    public int next() {
				        Iterator head = queue.poll();// using queue, every time poll the head iterator and get the next() value, if hasNext(),and offer to the queue again
				        int res = (Integer)head.next();
				        if (head.hasNext()) {
				            queue.offer(head);
				        }
				        return res;
				    }
				    public boolean hasNext() {
				        return !queue.isEmpty();
				    }
				}
			1.1.2 Flatten 2D Vector
				/*
					Given 2d vector =
					[
					  [1,2],
					  [3],
					  [4,5,6]
					]
					By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].
				*/
				/*
					思路： 1. 
				*/
				public class Vector2D {
				    Iterator<List<Integer>> listIter;
				    Iterator<Integer> numIter;
				    public Vector2D(List<List<Integer>> vec2d) {
				        listIter = vec2d.iterator();
				    }
				    public int next() {
				        hasNext();
				        return numIter.next();
				    }
				    public boolean hasNext() {
				        while ((numIter == null || !numIter.hasNext()) && listIter.hasNext()) {
				            numIter = listIter.next().iterator();
				        }
				        return numIter != null && numIter.hasNext();
				    }
				}
		1.2 Iterator Interface
				// Java Iterator interface reference:
				// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
				class PeekingIterator implements Iterator<Integer> {
				    Integer nextVal;
				    Iterator<Integer> iter ;
					public PeekingIterator(Iterator<Integer> iterator) {
					    // initialize any member here.
					    iter = iterator;
					    if (iter.hasNext()) {
					         nextVal = iter.next();
					    }
					}
				    // Returns the next element in the iteration without advancing the iterator.
					public Integer peek() {
				        return nextVal;
					}
					// hasNext() and next() should behave the same as in the Iterator interface.
					// Override them if needed.
					@Override
					public Integer next() {
					    Integer res = nextVal;
					    nextVal = iter.hasNext() ? iter.next() : null;
					    return res;
					}
					@Override
					public boolean hasNext() {
					    return nextVal != null;
					}
				}
		1.3 Binary Search Tree Iterator
			/*
				Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
			*/
			public class BSTIterator {
				private Stack<TreeNode> stack = new Stack<TreeNode>();
				public BSTIterator(TreeNode root) {
					pushAll(root);
				}
				public boolean hasNext() {
					return !stack.isEmpty();
				}
				public int next() {
					TreeNode curNode = stack.pop();
					pushAll(curNode.right);
					return curNode.val;
				}
				private void pushAll(TreeNode node) {
					while (node != null) {
						stack.push(node);
						node = node.left;
					}
				}
			}


2. Stack And Queue Design
		2.1 Implement Queue Using Stacks
			/*
			    O(1) amortized for each operation
			    Each element only ever gets moved like that once, though, and only after we already spent time pushing it, 
			    so the overall amortized cost for each operation is O(1).
			*/
			class MyQueue {
			    Stack<Integer> input = new Stack();
			    Stack<Integer> output = new Stack();
			    // Push element x to the back of queue.
			    public void push(int x) {
			        input.push(x);
			    }

			    // Removes the element from in front of queue.
			    public void pop() {
			        peek();
			        output.pop();
			    }

			    // Get the front element.
			    public int peek() {
			        if (output.empty()) {
			            while (!input.empty()) {
			                output.push(input.pop());
			            }
			        }
			        return output.peek();
			    }
			    // Return whether the queue is empty.
			    public boolean empty() {
			        return input.isEmpty() && output.isEmpty();
			    }
			}
		2.2 Implement Stack Using Queues
			class MyStack {
			    // Push element x onto stack.
			    Queue<Integer> queue;
			    public MyStack() {
			        queue = new LinkedList<>();
			    }
			    public void push(int x) {
			        queue.offer(x);
			        for (int i = 0; i < queue.size() - 1; i++) {
			            queue.offer(queue.poll());
			        }
			    }
			    // Removes the element on top of the stack.
			    public void pop() {
			        queue.poll();
			    }
			    // Get the top element.
			    public int top() {
			        return queue.peek();
			    }
			    // Return whether the stack is empty.
			    public boolean empty() {
			        return queue.isEmpty();
			    }
			}
		2.3 Min Stack
			//Solution1: Two Stack
				class MinStack {
				    Stack<Integer> stack = new Stack<>();
				    Stack<Integer> minStack = new Stack<>();
				    public void push(int x) {
				        stack.push(x);
				        if (minStack.isEmpty() || minStack.peek() >= x) {
				            minStack.push(x);
				        }
				    }
				    public void pop() {
				        int peek = stack.pop();
				        if (minStack.peek() == peek) {
				            minStack.pop();
				        }
				    }
				    public int top() {
				        return stack.peek();
				    }
				    public int getMin() {
				        return minStack.peek();
				    }
				}
			//Solution2: One Stack, Store the value and the min's diff
				class MinStack {
				    int min;
				    Stack<Integer> stack;
				    public MinStack () {
				        stack = new Stack<>();
				    }
				    public void push(int x) {
				        if (stack.isEmpty()) {
				            stack.push(0);
				            min = x;
				        } else {
				            stack.push(x - min);
				            if (x < min) {
				                min = x;
				            }
				        }
				    }
				    public void pop() {
				        if (stack.isEmpty()) {
				            return;
				        }
				        int pop = stack.pop();
				        if (pop < 0) {
				            min = min - pop;
				        }
				    }
				    public int top() {
				        int top = stack.peek();
				        if (top > 0) {
				            return top + min;
				        } else {
				            return min;
				        }
				    }
				    public int getMin() {
				        return min;
				    }
				}
			//Solution3: Without use api
			class MinStack {
			    private ListNode head = null;
			    private ListNode min = null;
			    public void push(int x) {
			        ListNode newNode = new ListNode(x);
			        
			        if (head == null) {
			            head = newNode;
			        } else {
			            head.next = newNode;
			            newNode.pre = head;
			            head = newNode;
			        }
			        
			        if (min == null || newNode.val <= min.val) {
			            min = newNode;
			        }
			    }

			    public void pop() {
			         ListNode node = head;
			         head = head.pre;
			         if (head != null) {
			             head.next = null;
			         }
			         
			         if (min == node) {
			             ListNode p = head;
			             min = head;
			             while (p != null) {
			                 if (p.val < min.val) {
			                     min = p;
			                 }
			                 p = p.pre;
			             }
			         }
			         
			    }

			    public int top() {
			        return head.val;
			    }

			    public int getMin() {
			        return min.val;
			    }
			    
			    class ListNode {
			        int val;
			        ListNode pre;
			        ListNode next;
			        public ListNode(int val) {
			            this.val = val;
			        }
			    }
			}




3. String, Word, Other Data Structure Design
		3.1 Shortest Word Distance II 
			/*
				Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
				Given word1 = “coding”, word2 = “practice”, return 3.
				Given word1 = "makes", word2 = "coding", return 1.
				Note:
				You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
			*/
			public class WordDistance {
			    private HashMap<String, ArrayList<Integer>> map;
			    public WordDistance(String[] words) {
			        map = new HashMap<String, ArrayList<Integer>>();
			        for (int i = 0; i < words.length; i++) {
			            if (!map.containsKey(words[i])) {
			                map.put(words[i], new ArrayList<>());
			            }
			            map.get(words[i]).add(i);
			        }
			    }
			    public int shortest(String word1, String word2) {
			        List<Integer> list1 = map.get(word1);
			        List<Integer> list2 = map.get(word2);
			        int minLen = Integer.MAX_VALUE;
			        int i = 0;
			        int j = 0;
			        while (i < list1.size() && j < list2.size()) {
			            minLen = Math.min(minLen, Math.abs(list1.get(i) - list2.get(j)));
			            if (list1.get(i) > list2.get(j)) {
			                j++;
			            } else {
			                i++;
			            }
			        }
			        return minLen;
			    }
			}

		3.2 Two Sum III - Data Structure Design
			public class TwoSum {
			    // Add the number to an internal data structure.
			    HashMap<Integer, Integer> map = new HashMap<>();
				public void add(int number) {
				    map.put(number, map.containsKey(number) ? map.get(number) + 1 : 1);
				}
			    // Find if there exists any pair of numbers which sum is equal to the value.
				public boolean find(int value) {
				    for (int key : map.keySet()) {
				        if (map.containsKey(value - key)) {
				            if (map.get(value - key) >= 2 || (map.get(value - key) == 1 && key != value - key)) {
				                return true;
				            }
				        }
				    }
				    return false;
				}
			}
		3.3 Find Median From Data Stream
			class MedianFinder {
			    // Adds a number into the data structure.
			    // 最小堆里的最大值， 最大堆里的最小值
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

4. New Data Structure
		4.1 LRU Cache
			public class LRUCache {
			    private HashMap<Integer, DoubleLinkedListNode> map;
			    private DoubleLinkedListNode head;
			    private DoubleLinkedListNode end;
			    private int capacity;
			    private int len;
			    public LRUCache(int capacity) {
			        map = new HashMap<>();
			        this.capacity = capacity;
			        this.len = 0;
			    }
			    
			    public int get(int key) {
			        if (map.containsKey(key)) {
			            DoubleLinkedListNode latest = map.get(key);
			            removeNode(latest);
			            setHead(latest);
			            return latest.val;
			        } else {
			            return -1;
			        }
			    }
			    
			    public void set(int key, int value) {
			        if (map.containsKey(key)) {
			            DoubleLinkedListNode oldNode = map.get(key);
			            removeNode(oldNode);
			            oldNode.val = value;
			            setHead(oldNode);
			        } else {
			            DoubleLinkedListNode newNode = new DoubleLinkedListNode(key, value);
			            if (len >=  capacity) {
			                map.remove(end.key);
			                end = end.pre;
			                if (end != null) {
			                    end.next = null;
			                }
			            } else {
			                len++;
			            }
			            map.put(key, newNode);
			            setHead(newNode);
			        }
			    }
			    
			    public void removeNode(DoubleLinkedListNode node) {
			        DoubleLinkedListNode cur = node;
			        DoubleLinkedListNode pre = cur.pre;
			        DoubleLinkedListNode post = cur.next;
			        
			        if (pre != null) {
			            pre.next = post;//pre不空，pre指向post
			        } else {
			            head = post;//pre为空，next就是头结点
			        }
			        
			        if (post != null) {//post不空, post前指针指向pre
			            post.pre = pre;
			        } else {
			            end = pre; //post为空， pre是尾结点
			        }
			    }
			    
			    public void setHead(DoubleLinkedListNode node) {
			        node.next = head;//node指向头结点
			        node.pre = null;//node已经是头结点，pre所以为空
			        if (head != null) {//如果head非空，原先head的pre指向新head（node）
			            head.pre = node;
			        }
			        head = node;//新的head就是node
			        if (end == null) {//如果end为空，意味着之前就是空链表，将头结点node同时设为end
			            end = node;
			        }
			        
			    }
			    
			    class DoubleLinkedListNode {
			        int key;
			        int val;
			        DoubleLinkedListNode pre;
			        DoubleLinkedListNode next;
			        public DoubleLinkedListNode(int key, int val) {
			            this.key = key;
			            this.val = val;
			        }
			    }
			}
				