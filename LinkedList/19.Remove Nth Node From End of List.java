/*
	Remove Nth Node From End of List 
	Given a linked list, remove the nth node from the end of list and return its head.
	For example,

	Given linked list: 1->2->3->4->5, and n = 2.
	After removing the second node from the end, the linked list becomes 1->2->3->5.

	Note:
		Given n will always be valid.
		Try to do this in one pass.
*/

/*
	首先先让faster从起始点往后跑n步。然后再让slower和faster一起跑，直到faster==null时候，slower所指向的node就是需要删除的节点。
	注意，一般链表删除节点时候，需要维护一个prev指针，指向需要删除节点的上一个节点。
	为了方便起见，当让slower和faster同时一起跑时，就不让 faster跑到null了，让他停在上一步，
	faster.next==null时候，这样slower就正好指向要删除节点的上一个节点，充当了prev指针。这样一来，就很容易做删除操作了。
	slower.next = slower.next.next(类似于prev.next = prev.next.next)。
	同时，这里还要注意对删除头结点的单独处理，要删除头结点时，没办法帮他维护prev节点，所以当发现要删除的是头结点时，直接让head = head.next并return head就够了。
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
// prefer one pass
public class Solution {
	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode fast = head;
		ListNode slow = head;

		//先让fast移动n步，之后再让slow和fast 一起移动，如果fast.next == null
		//就意味着slow.next指向要移除的结点
		for (int i = 0; i < n; i++)
			fast = fast.next;

		//移除头结点的情况
		if (fast == null)
			head = head.next;
			return head;

		while (fast.next != null) {
			slow = slow.next;
			fast = fast.next;
		}

		slow.next = slow.next.next;
		return head;
	}
}

//Solution2: two pass
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || (head.next == null && n == 1))
            return null;
        int len = 1;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy.next;
        
        while (cur != null) {
            cur = cur.next;
            len++;
        }
        cur = dummy;
        
        for (int i = 1; i < len - n; i++) {
            cur = cur.next;
        }
        if (cur.next == null)
            return head;
        cur.next = cur.next.next;
        return dummy.next;
    }
}

