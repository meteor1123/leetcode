/*
	Reverse a linked list.
	Example
	For linked list 1->2->3, the reversed linked list is 3->2->1

	Challenge
	Reverse it in-place and in one-pass
*/

/*
	(head) -> [ ] -> [ ] -> [ ] -> (tail)
     head      1      2      3      null

	 step1 new a ListNode pre, pre = 
     step2 new a ListNode nex, nex = head.next;

     (head) -> [ ]
				
*/


public class Solution {
    //Iterative
    public ListNode reverse(ListNode head) {

        ListNode newhead = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = newhead;
            newhead = head;
            head = next;
        }
        return newhead;
    }

    //Recursive
    public ListNode reverseList(ListNode head) {
        return reverse(head, null);
    }
    public ListNode reverse(ListNode head, ListNode newHead) {
        if (head == null) {
            return newHead;
        }
        ListNode next = head.next;
        head.next = newHead;
        return reverse(next, head);
    }
}