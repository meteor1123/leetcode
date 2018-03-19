/*
	Sort List 
	Sort a linked list in O(n log n) time using constant space complexity.
*/

public class Solution {
    //由于要求用O（nlogn）的时间复杂度和constant space complexity
    //因此采用merge sort
    public ListNode sortList(ListNode head) {
    	if (head == null || head.next == null)
    		return head;
    	ListNode slow = head;
    	ListNode fast = head;
    	ListNode firsthalf = head;

    	//merge 需要计算中点 
    	while (fast.next != null && fast.next.next != null) {
    		slow = slow.next;
    		fast = fast.next.next;
    	}

    	ListNode secondhalf = slow.next;
    	slow.next = null;
    	ListNode leftlist = sortList(firsthalf);
    	ListNode rightlist = sortList(secondhalf);

    	return mergeTwoLists(leftlist, rightlist);
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode newhead = new ListNode(-1);
        ListNode l3 = newhead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                l3.next = l1;
                l1 = l1.next;
            } else {
                l3.next = l2;
                l2 = l2.next;
            }
            l3 = l3.next;
        }
        if (l1 != null) {
            l3.next = l1;
        }
        if (l2 != null) {
            l3.next = l2;
        }
        return newhead.next;
    }
}