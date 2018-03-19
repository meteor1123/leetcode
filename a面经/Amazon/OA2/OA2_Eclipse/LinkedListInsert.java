package OA2;

public class LinkedListInsert {
	public static ListNode Solution(ListNode head, int val) {
		if (head == null) {
			ListNode res = new ListNode(val);
			res.next = res;
			return res;
		}
		ListNode cur = head;
		do {
			if (val <= cur.next.val && val >= cur.val) {
				break;
			}
			if (cur.val > cur.next.val && (val < cur.next.val || val > cur.val)) {
				break;
			}
			cur = cur.next;
		} while (cur != head);
		ListNode newNode = new ListNode(val);
		newNode.next = cur.next;
		cur.next = newNode;
		return newNode;
	}
	//Solution2
	public static ListNode Solution2(ListNode head, int val) {
		if (head == null) {
			ListNode res = new ListNode(val);
			res.next = res;
			return res;
		}
		ListNode cur = head;
		ListNode pre = null;
		do {
			pre = cur;
			cur = cur.next;
			if (val <= cur.val && val >= pre.val) {
				break;
			}
			if ((pre.val > cur.val) && (val < cur.val || val > pre.val)) {
				break;
			}
		} while (cur != head);
		ListNode newNode = new ListNode(val);
		newNode.next = cur;
		pre.next = newNode;
		return newNode;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(3);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(5);
		ListNode n5 = new ListNode(6);
		ListNode n6 = new ListNode(7);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = n1;
		ListNode temp = n1;
		while (temp != null) {
			System.out.print(temp.val + "->");
			temp = temp.next;
			if (temp == n1) {
				break;
			}
		}
		System.out.println();
		ListNode res = Solution2(n6, 0);
		ListNode cur = res;
		do {
			System.out.print(cur.val + "->");
			cur = cur.next;
		} while (cur != res);
//		System.out.println(cur.val);
	}

}
