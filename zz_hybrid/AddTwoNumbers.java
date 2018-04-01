package LeetCode;

public class AddTwoNumbers {
	static class ListNode {
		public ListNode next;
		public int val;
		public ListNode(int val) {
			this.val = val;
		}
	}
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		ListNode c1 = l1;
		ListNode c2 = l2;
		int carry = 0;
		while (c1 != null || c2 != null) {
			int v1 = c1 == null? 0 : c1.val;
			int v2 = c2 == null? 0 : c2.val;
			int sum = v1 + v2 + carry;
			carry = sum / 10;
			cur.next = new ListNode(sum % 10);
			cur = cur.next;
			if (c1 != null) {
				c1 = c1.next;
			}
			if (c2 != null) {
				c2 = c2.next;
			}
		}
		if (carry > 0) {
			cur.next = new ListNode(carry);
		}
		return dummy.next;
	}
}
