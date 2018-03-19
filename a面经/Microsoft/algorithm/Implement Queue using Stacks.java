/*
	Implement Queue using Stacks
	Implement the following operations of a queue using stacks.

	push(x) -- Push element x to the back of queue.
	pop() -- Removes the element from in front of queue.
	peek() -- Get the front element.
	empty() -- Return whether the queue is empty.
	Notes:
	You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
	Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
	You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
*/

/*
    O(1) amortized for each operation
    Each element only ever gets moved like that once, though, and only after we already spent time pushing it, 
    so the overall amortized cost for each operation is O(1).
*/
//Solution1: two stack implement
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

//Solution2:
class MyQueue {
    // Push element x to the back of queue.
    ListNode head;
    ListNode end;
    public void push(int x) {
        if (head == null) {
            head = new ListNode(x);
            end = head;
        } else {
            ListNode newNode = new ListNode(x);
            newNode.next = end;
            end.pre = newNode;
            end = newNode;
        }
    }

    // Removes the element from in front of queue.
    public void pop() {
        if (head != null) {
            ListNode temp = head.pre;
            head = null;
            head = temp;
        } 
    }

    // Get the front element.
    public int peek() {
        return head.val;
    }

    // Return whether the queue is empty.
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