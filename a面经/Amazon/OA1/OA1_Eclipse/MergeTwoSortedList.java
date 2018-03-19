package OA1;

public class MergeTwoSortedList {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        
        //res.next store the head pointer of the sorted ListNode
        ListNode res = new ListNode(0);
        ListNode head = res;
        
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                head.next = l1;
                l1 = l1.next;
            } else {
                head.next = l2;
                l2 = l2.next;
            }
            head = head.next;
        }
        
        if (l1 != null)
            head.next = l1;
        if (l2 != null)
            head.next = l2;
        return res.next;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}

//class ListNode {
//    int val;
//    ListNode next;
//    ListNode(int x) {
//        val = x;
//        next = null;
//    }
//}