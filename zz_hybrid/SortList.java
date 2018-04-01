package LeetCode;

import LaiOfferClass21.ListNode;

public class SortList {
	public ListNode sortList(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode slow = head;
		ListNode fast = head;
		ListNode prev = null;
		while (fast != null && fast.next != null) {
			prev = slow;
			fast = fast.next.next;
			slow = slow.next;
		}
		prev.next = null;
		ListNode m = sortList(head);
		ListNode n = sortList(slow);
		return merge(m, n);
	}
	private ListNode merge(ListNode m, ListNode n) {
		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		while (m != null && n != null) {
			if (m.key < n.key) {
				cur.next = m;
				m = m.next;
			} else {
				cur.next = n;
				n = n.next;
			}
			cur = cur.next;
		}
		if (m != null) {
			cur.next = m;
		} 
		if (n != null) {
			cur.next = n;
		}
		return dummy.next;
	}
	public static void main(String[] args) {
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		ListNode c = new ListNode(3);
		ListNode d = new ListNode(4);
		a.next = b;
		b.next = d;
		d.next = c;
		c.next = null;
		SortList test = new SortList();
		ListNode newhead = test.sortList(a);
		while (newhead != null) {
			System.out.println(newhead.key);
			newhead = newhead.next;
		}
	}
}
