package OA1;

public class ReverseSecondHalfofLinkedList {
	public static ListNode reverseSecondHalfList(ListNode head) {
		if (head == null || head.next == null)	return head;
		ListNode fast = head;
		ListNode slow = head;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		ListNode pre = slow.next;
		ListNode cur = pre.next;
		while (cur != null) {
			pre.next = cur.next;
			cur.next = slow.next;
			slow.next = cur;
			cur = pre.next;
		}
		return head;
	}
	
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		ListNode node1 = new ListNode(2);
		ListNode node2 = new ListNode(3);
		ListNode node3 = new ListNode(4);
		ListNode node4 = new ListNode(5);
		ListNode node5 = new ListNode(6);
		head.next = node1;
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		ListNode rvalue = reverseSecondHalfList(head);
		while (rvalue != null) {
			System.out.println(rvalue.val);
			rvalue = rvalue.next;
		}
	}
}
class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}
