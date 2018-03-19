/*
	Intersection of Two Linked Lists
	Write a program to find the node at which the intersection of two singly linked lists begins.
	For example, the following two linked lists:
	A:       a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
	B:  b1 → b2 → b3
	begin to intersect at node c1.


	Notes:

		1. If the two linked lists have no intersection at all, return null.
		2. The linked lists must retain their original structure after the function returns.
		3. You may assume there are no cycles anywhere in the entire linked structure.
		4. Your code should preferably run in O(n) time and use only O(1) memory.
*/
public class Solution {
    //长的链表开始多走 （h1的数量 - h2的数量）步，然后和短链表同步往下走，遇到的第一个相同的节点就是最早的公共节点并返回
    //没有找到相同的，或者有一方走到null，则返回null.
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    	/*
            1.分别算出A 和 B链表的长度
            2. 比较A 和B 哪个长，哪个长就走 长 - 短的 路程
            3. 短的从开头开始走， 长的从长减短的位置开始走
            4. 此时遇到的
        */

        int lenA = 0;
    	int lenB = 0;
    	ListNode h1 = headA;
    	ListNode h2 = headB;
    	while (h1 != null) {
    		lenA++;
    		h1 = h1.next;
    	}

    	while (h2 != null) {
    		lenB++;
    		h2 = h2.next;
    	}
    	h1 = headA;
    	h2 = headB;
    	if (lenA > lenB) 
    		for (int i = 0; i < lenA - lenB; i++)
    			h1 = h1.next;
    	else 
    		for (int i = 0; i < lenB - lenA; i++)
    			h2 = h2.next;
    		
    	while (h1 != null && h2 != null) {
    		if (h1.val == h2.val) 
    			return h1;
    		else {
    			h1 = h1.next;
    			h2 = h2.next;
    		}

    	}
    	return null;
    }
}