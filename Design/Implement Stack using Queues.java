/*
	Implement Stack using Queues 
	Implement the following operations of a stack using queues.

		push(x) -- Push element x onto stack.
		pop() -- Removes the element on top of the stack.
		top() -- Get the top element.
		empty() -- Return whether the stack is empty.
	Notes:
		You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
		Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
		You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).
	Update (2015-06-11):
		The class name of the Java function had been updated to MyStack instead of Stack.
*/
/*
	Keypoint: When we push the element, we need to poll the element 
			  and add to the end of the queue.
*/
//Solution1:  time push O(n), pop O(1)
class MyStack {
	Queue<Integer> queue;
	public MyStack() {
		this.queue = new LinkedList<Integer>();
	}

    // Push element x onto stack.
    public void push(int x) {
        queue.add(x);
        for (int i = 0; i < queue.size() - 1; i++) {
        	queue.add(queue.poll());
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

//Solution2:  time push O(1), pop O(n)
class MyStack {
    private Queue<Integer> q1 = new LinkedList<>();
    private Queue<Integer> q2 = new LinkedList<>();
    private int top;

    // Push element x onto stack.
    public void push(int x) {
        q1.add(x);
        top = x;
    }

    // Removes the element on top of the stack.
    public void pop() {
        while (q1.size() > 1) {
            top = q1.remove();
            q2.add(top);
        }
        q1.remove();
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    public int top() {
        return top;
    }

    public boolean empty() {
        return q1.isEmpty() && q2.isEmpty();
    }
}
//Solution3: implement Stack by using ListNode, no api
class MyStack {
    private ListNode head = null;
    public void push(int x) {
        ListNode newNode = new ListNode(x);
        if (head == null) {
            head = newNode;
        } else {
            head.next = newNode;
            newNode.pre = head;
            head = newNode;
        }
    }

    // Removes the element on top of the stack.
    public void pop() {
        ListNode node = head;
        if (head != null) {
            head.next = null;
            head = head.pre;
        }
    }

    // Get the top element.
    public int top() {
        return head.val;
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return head == null;
    }
}

class ListNode {
    int val;
    ListNode pre;
    ListNode next;
    public ListNode(int val) {
        this.val = val;
    }
}