
public class LinkedListInsert {
	public ListNode Solution(ListNode head, int val) {
		if (head == null) {
			ListNode rvalue = new ListNode(val);
			rvalue.next = rvalue;
			return rvalue;
		}
		
		ListNode cur = head;
		
		do {
			if (val <= cur.next.val && val >= cur.val)	break;
			if (cur.val > cur.next.val && (val < cur.next.val || val > cur.val))	break;
			cur = cur.next;
		} while (cur != head);
		
		ListNode newNode = new ListNode(val);
		newNode.next = cur.next;
		cur.next = newNode;
		return newNode;
	}
}
