/*
    Merge two sorted linked lists and return it as a new list. 
    The new list should be made by splicing together the nodes of the first two lists.
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

// Iterative
public class Solution {
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
}

// recursion
public class Solution {
    public ListNode mergeTwoList(ListNode l1, ListNode l2) {
        if (l1 == null) 
            return l2;
        if (l2 == null) 
            return l1;
        
        if (l1.val <= l2.val) {
            l1.next = mergeTwoList(l1.next, l2);
            return l1;
        }
        l2.next = mergeTwoList(l1, l2.next);
        return l2;
    }
}