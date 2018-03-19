/*
	Reverse Linked List II 
	Reverse a linked list from position m to n. Do it in-place and in one-pass.

	For example:
	Given 1->2->3->4->5->NULL, m = 2 and n = 4,

	return 1->4->3->2->5->NULL.

	Note:
	Given m, n satisfy the following condition:
	1 ≤ m ≤ n ≤ length of list.
	Tags: LinkedList
*/
/*
    step1: new four ListNode, newhead, premNode, mNode, nNode,
           newhead is a dummy node
           premNode is the pre node of m Node;
           mNode is the m node;
           nNode is the n node;
    
    step2: for loop to traverse n

    step3: when i < m - 1; just need to move the premNode
    step4: if i equals m - 1, means we find the m th node, and we set nNode equals mNode.next
    step5: use these three nodes to reverse the node from m to n;
           we notice that the mNode doesn't change, always is the original m node;

           mNode.next = nNode.next;
           nNode.next = premNode.next;
           premNode.next = nNode;
           nNode = mNode.next;
*/
public class Solution {

    //Solution 3
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode newhead = new ListNode(-1);
        newhead.next = head;
        
        if (head == null || head.next == null) {
            return newhead.next;
        }
        
        ListNode premNode = newhead;
        ListNode mNode = null;//需要reverse 到后面去的节点
        ListNode nNode = null;//需要reverse 刀前面去的节点
        
        for (int i = 0; i < n; i++) {
            if (i < m - 1) {
                premNode = premNode.next; //找真正的startpoint
            } else if (i == m - 1) { //开始第一轮
                mNode = premNode.next;
                nNode = mNode.next;
            } else {
                mNode.next = nNode.next; // mNode.next 指向nNode的下一个结点
                nNode.next = premNode.next; //nNode交换到最开始
                premNode.next = nNode;  //nNode作为新的店
                nNode = mNode.next;     //nNode回归到mNode
            }
        }
        return newhead.next;
    }
	//Solution1: Chapter9 
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m >= n || head == null) {
        	return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;

        for (int i = 1; i < m; i++) {
        	if (head == null) {
        		return null;
        	}
        	head = head.next;
        }

        ListNode premNode = head;
        ListNode mNode = head.next;
        ListNode nNode = mNode;
        ListNode postnNode = mNode.next;

        for (int i = m; i < n; i++) {
        	if (postnNode == null) {
        		return null;
        	}

        	ListNode nex = postnNode.next;
        	postnNode.next = nNode;
        	nNode = postnNode;
        	postnNode = nex;
        }
        mNode.next = postnNode;
        premNode = nNode;
        return dummy.next;
    }

    //Solution2:  Chapter 9 
    public ListNode reverseBetween(ListNode head, int m, int n) {
	    if (m >= n || head == null) {
	        	return head;
	        }
	        ListNode dummy = new ListNode(0);
	        dummy.next = head;
	        head = dummy;

	        for (int i = 1; i < m; i++) {
	        	if (head == null) {
	        		return null;
	        	}
	        	head = head.next;
	    }

	    ListNode premNode = head;
	    ListNode mNode = head.next;
	    ListNode pre = mNode;
	    ListNode cur = mNode.next;

	    for (int i = m; i < n; i++) {
	    	if (cur == null) {
	    		return null;
	        }
	        ListNode nex = cur.next;
	        cur.next = pre;
	        pre = cur;
	        cur = nex;
	    }
	    mNode.next = cur;
	    premNode.next = pre;
	    return dummy.next;
    }

}






