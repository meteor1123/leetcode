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

    public ListNode mergeTwoLists(ListNode leftlist, ListNode rightlist) {
    	if (leftlist == null)
    		return rightlist;
    	if (rightlist == null)
    		return leftlist;

    	ListNode newhead = new ListNode(-1);
    	ListNode ptr = newhead;

    	while (rightlist != null && leftlist != null) {
    		if (rightlist.val < leftlist.val) {
    			ptr.next = rightlist;
    			ptr = ptr.next;
    			rightlist = rightlist.next;
    		} else {
    			ptr.next = leftlist;
    			ptr = ptr.next;
    			leftlist = leftlist.next;
    		}
    	}

    	if (rightlist != null)
    		ptr.next = rightlist;
    	if (leftlist != null)
    		ptr.next = leftlist;
    	return newhead.next;
    }
}


// Bubble Sort O(n*n), Microsoft interview question
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        int len = 0;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy.next;
        while (cur != null) {
            cur = cur.next;
            len++;
        }
        
        for (int i = 0 ; i < len; i++) {
            cur = head;
            ListNode next = cur.next;
            for (int j = 0; j < len - 1; j++) {
                if (cur.val > next.val) {
                    int temp = cur.val;
                    cur.val = next.val;
                    next.val = temp;
                }
                cur = next;
                next = next.next;
            }
        }
        return dummy.next;
    }
}