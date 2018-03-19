/*
	Implement Queue by Stacks
	 As the title described, you should only use two stacks to implement a queue's actions.

	The queue should support push(element), pop() and top() where pop is pop the first(a.k.a front) element in the queue.

	Both pop and top methods should return the value of first element.

	Example
	For push(1), pop(), push(2), push(3), top(), pop(), you should return 1, 2 and 2

	Challenge
	implement it by two stacks, do not use any other data structure and push, pop and top should be O(1) by AVERAGE.
*/

/*
	两个栈，stack1用来存push进来的元素，stack2用来存准备要pop出去的元素。
	push没什么好说的，pop操作时，如果stack2里面有东西，直接pop就好了，没有的话，把stack1的所有元素全存进来，再pop
*/

public class StackToQueue {
	private Stack<Integer> stack1;
	private Stack<Integer> stack2;

	public StackToQueue() {
		stack1 = new Stack<Integer>();
		stack2 = new Stack<Integer>();
	}

	public void push(int element) {
		stack1.push(element);
	}

	public int pop() {
		if (stack2.isEmpty()) {
			while (!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
		}
		return stack2.pop();
	}

	public int top() {
		if (stack2.isEmpty()) {
			while (!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
		}
		return stack2.peek():
	}
}