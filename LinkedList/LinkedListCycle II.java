/*
	Linked List Cycle II
	Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

	Follow up:
	Can you solve it without using extra space?
*/


/* 
	solution:
		step1: fast走两步，slow走一步，
		step2: 第一次相遇时slow走过的距离：a+b，fast走过的距离：a+b+c+b
				a:头结点到环入口的距离
				b:slow在环里走的距离
				c:环总距离减去b
		step3: 2(a + b) = a + b + c + b   ===> a = c，
		step4: 因此slow和fast相遇后，将slow指向头结点，slow 和fast 分别继续走，第二次相遇时的点就是环入口


*/

public class Solution {
	 ListNode detectCycle(ListNode head) {  
	 	ListNode slow = head;
	 	ListNode fast = head;
	 	while (fast != null && fast.next != null) {
	 		fast = fast.next.next;
	 		slow = slow.next;
	 		// 相遇 有环
	 		if (fast == slow) {
	 			slow = head;
	 			while (fast != slow) {
	 				fast = fast.next;
	 				slow = slow.next;
	 			}
	 			return slow;
	 		}
	 	}
	 	return null;
	 }
}