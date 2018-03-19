/*
	Swap Two Nodes in Linked List

	Given a linked list and two values v1 and v2. Swap the two nodes in the linked list with values v1 and v2. It's guaranteed there is no duplicate values in the linked list. If v1 or v2 does not exist in the given linked list, do nothing.

	Notice

	You should swap the two nodes with values v1 and v2. Do not directly swap the values of the two nodes.

	Example
		Given 1->2->3->4->null and v1 = 2, v2 = 4.

		Return 1->4->3->2->null.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    /**
     * @param head a ListNode
     * @oaram v1 an integer
     * @param v2 an integer
     * @return a new head of singly-linked list
     */
    public ListNode swapNodes(ListNode head, int v1, int v2) {
        // Write your code here
        ListNode newhead = new ListNode(-1);
        newhead.next = head;
        ListNode cur = newhead;
       
        ListNode node1Pre = null;
        ListNode node2Pre = null;
        
        while (cur.next != null) {
            if (cur.next.val == v1) {
                node1Pre = cur;
            } else if (cur.next.val == v2) {
                node2Pre = cur;
            }
            cur = cur.next;
        }
       
        //任意一个pre节点为空 说明没找到相应的值，直接返回
        if (node1Pre == null || node2Pre == null) {
            return head;
        }
        
        if (node2Pre.next == node1Pre) {
            // make sure node1Pre is before node2Pre
            ListNode temp = node1Pre;
            node1Pre = node2Pre;
            node2Pre = temp;
        }
        
        ListNode node1 = node1Pre.next;
        ListNode node2 = node2Pre.next;
        ListNode node2Next = node2.next;
        
        //处理两个节点相邻的情况
        if (node1Pre.next == node2Pre) {
            node1Pre.next = node2;
            node2.next = node1;
            node1.next = node2Next;
        } else {
            //神奇的处理 竟然不用考虑头结点和尾结点的情况
            node1Pre.next = node2;
            node2.next = node1.next;
            
            node2Pre.next = node1;
            node1.next = node2Next;
        }
        
        return newhead.next;
    }
    
}