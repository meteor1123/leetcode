/*
	Swap Nodes in Pairs
	Given a linked list, swap every two adjacent nodes and return its head.
	For example:
		Given 1->2->3->4, you should return the list as 2->1->4->3.
	Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
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


//为什么这里要用cur.next指向head，这样可以规避corner case， 
//假如直接cur = head,不仅需要判断cur.next是否为空 还需要判断cur.next.nxet是否为空，
//并且 即便cur.next.next为空，也是能交换的，所以不便
 public class Solution {
 	public ListNode swapPairs(ListNode head) {
 		LinkedList newhead = new LinkedList(0);//使用fakehead指向头结点保存指针头
 		newhead.next = head;
 		LinkedList cur = newhead;//用cur进行遍历
 		
 		/*
 		初始状态： 	newhead -> 1 -> 2 -> 3 -> 4 -> 5 -> 6
 					 |         |    |
 					cur  cur.next   cur.next.next
 		one time while loop：
 					newhead -> 2 -> 1 -> 3 -> 4 -> 5 -> 6
 									|
 								    cur	 
 		*/			
 		while (cur.next != null && cur.next.next != null) {
 			cur.next = swap(cur.next, cur.next.next);
 			cur = cur.next.next;
 		}
 		return newhead.next;
 	}

 	public ListNode swap(ListNode l1, ListNode l2) {
 		l1.next = l2.next;
 		l2.next = l1;
 		return l2;
 	}

 //Solution2: prefer
 	public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        
        while (cur.next != null && cur.next.next != null) {
            ListNode first = cur.next;
            ListNode second = cur.next.next;
            
            first.next = second.next;
            second.next = first;
            
            cur.next = second;
            cur = cur.next.next;
        }
        return dummy.next;
    }
 }