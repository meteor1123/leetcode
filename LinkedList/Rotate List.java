/*
	Rotate List 
	Given a list, rotate the list to the right by k places, where k is non-negative.

	For example:
	Given 1->2->3->4->5->NULL and k = 2,
	return 4->5->1->2->3->NULL.
	Tags: LinkedList Two Pointers
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

/*
    Solution:
        step1: create dummy, dummy.next = head;
               new ListNode fast and slow = dummy.
        step2: use fast pointer to travese the List count the length n;
        step3: use slow pointer move n - k position
        step4: 

*/
public class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if ( head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;//指向head的空指针
        ListNode fast = dummy;
        ListNode slow = dummy;
        
        int count = 0;
        //fast move n step
        while (faster.next != null) {
            faster = faster.next;
            count++;
        } // fast指针作用是遍历到初始链表尾，取得链表长度
       
        //slow move n - k step, avoid the large number, use mod
        for (int j = count - k % count; j > 0 ; j--){
            slow = slow.next;//slow指针作用是遍历到断点。并且 slow.next 等于头结点
        }

        /*
            1 -> 2 -> 3 -> 4 -> 5 -> NULL and k = 2,
         dummy.next   slow     fast
            4 -> 5 -> 1 -> 2 -> 3 -> NULL. 
         dummy.next      fast        slow
        */
        //fast is the end point , end point next to the dummy.next
        fast.next = dummy.next;//尾结点直接指向头结点
        //dummy.next to the slow.next, since slow.next still point to the 4
        dummy.next = slow.next;//空指针指向现在的头结点
        slow.next = null;//将slow指针的next置空，表示尾结点
        return dummy.next;
    }
}