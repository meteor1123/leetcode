/*
	Given a singly linked list L: L0→L1→…→Ln-1→Ln,
	reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

	You must do this in-place without altering the nodes' values.

	For example,
	Given {1,2,3,4}, reorder it to {1,4,2,3}.
*/

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

/*
题解：

    题目要重新按照 L0→Ln→L1→Ln-1→L2→Ln-2→…来排列，看例子1->2->3->4会变成1->4->2->3，
    拆开来看，是{1，2}和{4，3}的组合，而{4，3}是{3，4}的逆序。这样问题的解法就出来了。
    第一步，将链表分为两部分。
    第二步，将第二部分链表逆序。
    第三步，将链表重新组合。
*/

public class Solution {
    public void reorderList(ListNode head) {
    	if (head == null || head.next == null)
    		return ;
    	ListNode slow = head;
    	ListNode fast = head;
    	ListNode firsthalf = head;
    	//双指针找中点
    	while (fast.next != null && fast.next.next != null) {
    		slow = slow.next;
    		fast = fast.next.next;
    	}
    	//第二段的头结点就是slow.next
    	ListNode secondhalf = slow.next;
    	slow.next = null;//将第一段的尾结点指向null
		secondhalf = reverseList(secondhalf);
		mergeTwoList(firsthalf, secondhalf);
    }
    public  ListNode reverseList(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
    public void mergeTwoList(ListNode firsthalf, ListNode secondhalf) {
    	while (secondhalf != null) {
    		ListNode temp1 = firsthalf.next;
    		ListNode temp2 = secondhalf.next;

    		firsthalf.next = secondhalf;
    		secondhalf.next = temp1;

    		firsthalf = temp1;
    		secondhalf = temp2;
    	}
    }
}