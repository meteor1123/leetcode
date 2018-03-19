/*
	Remove Duplicates from Sorted List II
	Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

	For example,
	Given 1->2->3->3->4->4->5, return 1->2->5.
	Given 1->1->1->2->3, return 2->3.
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


 public class Solution {


    //Solution1
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //create a dummy node to avoid the coener case
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int val = cur.next.val;
                while (cur.next != null && cur.next.val == val) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }
 	//Solution1
 	public ListNode deleteDuplicates(ListNode head) {
 		if (head == null || head.next == null)
 			return head;
 		ListNode fakehead = new ListNode(-1);
 		fakehead.next = head;
 		ListNode pre = fakehead;
 		ListNode cur = pre.next;
 		ListNode next = cur.next;
 		boolean flag = false;
 		//关键变量，flag指示是否找到重复值，找到重复值置为true，因为不能立马将cur进位，还得判断cur和next.next是否相同
 		while (next != null) {
 		    //初始阶段判断cur 以及cur.next的值是否相等
 			if (cur.val == next.val) {
 				next = next.next;//相等，next前进
 				flag = true;//设重复标示位
 				if (next == null) //next假如为null，表明到队尾
 					pre.next = null;//直接指向null
 			} else {
 				if (flag == true) {// 因为需要保留一个重复的结点怼后结点继续判断，所以当找到第一个不与之前重复结点相同的点时，进入此循环，pre.next此时可以放心指向非重复结点，并将flag设为false
 					pre.next = next;
 					flag = false;
 				} else  //当彻底不是重复结点时，pre可以进位到cur
 					pre = cur;
 				cur = next;//cur进位
 			    next = next.next;//next进位
 			}
 		}
 		return fakehead.next;
 	}

 }