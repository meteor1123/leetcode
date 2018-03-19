/*
	Partition List
	Given a linked list and a value x, partition it such that all nodes less than x 
	come before nodes greater than or equal to x.
	You should preserve the original relative order of the nodes in each of the two partitions.

	For example,
	Given 1->4->3->2->5->2 and x = 3,
	return 1->2->2->4->3->5.
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
    给定一个x的值，小于x都放在大于等于x的前面,并且不改变链表之间node原始的相对位置。每次看这道题我老是绕晕，纠结为什么4在3的前面。。
    其实还是得理解题意，4->3->5都是大于等3的数，而且这保持了他们原来的相对位置 。
    所以，这道题是不需要任何排序操作的，题解方法很巧妙。
    new两个新链表，一个用来创建所有大于等于x的链表，一个用来创建所有小于x的链表。
    遍历整个链表时，当当前node的val小于x时，接在小链表上，反之，接在大链表上。这样就保证了相对顺序没有改变，而仅仅对链表做了与x的比较判断。
    最后，把小链表接在大链表上，别忘了把大链表的结尾赋成null。
*/
public class Solution {
    public ListNode partition(ListNode head, int x) {
    	if (head == null || head.next == null)
    		return head;
    	ListNode small = new ListNode(-1);
    	ListNode smallhead = small;
    	ListNode large = new ListNode(-1);
    	ListNode largehead = large;
    	while (head != null) {
    		if (head.val < x) {
    			small.next = head;
    			small = small.next;
    		} else {
    			large.next = head;
    			large = large.next;
    		}
    		head = head.next;
    	}
    	//最后别忘了 larg最后的指针要指向null
    	//small 最后的指针指向larg的头结点。
    	large.next = null;
    	small.next = largehead.next;
    	return smallhead.next;
    }
}