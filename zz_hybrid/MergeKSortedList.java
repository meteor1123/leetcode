package LeetCode;

import java.util.Comparator;
import java.util.PriorityQueue;

import LaiOfferClass24.ListNode;

/*
 * Given k sorted LinkedList, merge
 */
public class MergeKSortedList {
	public ListNode mergeKLists(ListNode[] lists) {
		int k = lists.length;
		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		PriorityQueue<ListNode> pq = new PriorityQueue<>(k, new Comparator<ListNode>() {
			@Override
			public int compare(ListNode l1, ListNode l2) {
				if (l1.key == l2.key) {
					return 0;
				}
				return l1.key < l2.key? -1 : 1;
			}
		});
		for (ListNode n : lists) {
			pq.offer(n);
		}
		while (!pq.isEmpty()) {
			ListNode min = pq.poll();
			cur.next = min;
			cur = cur.next;
			if (cur.next != null) {
				pq.offer(cur.next);
			}
		}
		return dummy.next;
	}
	public static void main(String[] args) {
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		ListNode c = new ListNode(3);
		ListNode d = new ListNode(4);
		a.next = b;
		c.next = d;
		MergeKSortedList test = new MergeKSortedList();
		ListNode[] lists = {a, c};
		ListNode newhead = test.mergeKLists(lists);
		while (newhead != null) {
			System.out.print(newhead.key);
			newhead = newhead.next;
		}
	}
}
