package OA1;

public class FindLoopsInLinkedList {
	public boolean hasCycle(ListNode head) {
		if (head == null) {
			return false;
		}
		ListNode slow = head;
		ListNode fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				return true;
			}
		}
		return false;
	}	
//	 public ListNode detectCycleStartPosition(ListNode head) {  
//		 	ListNode slow = head;
//		 	ListNode fast = head;
//		 	while (fast != null && fast.next != null) {
//		 		fast = fast.next.next;
//		 		slow = slow.next;
//		 		if (fast == slow) {
//		 			slow = head;
//		 			while (fast != slow) {
//		 				fast = fast.next;
//		 				slow = slow.next;
//		 			}
//		 			return slow;
//		 		}
//		 	}
//		 	return null;
//	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
