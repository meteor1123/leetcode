/*
	Add Two Numbers 
	You are given two linked lists representing two non-negative numbers. 
    The digits are stored in reverse order and each of their nodes contain a single digit. 
    Add the two numbers and return it as a linked list
	
	Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
	Output: 7 -> 0 -> 8

	Tags:LinkedList, Math
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
    // 因为7-0-3 + 3-5 = 307 + 53，所以头结点实际上是整数的个位数，因此可以不用考虑长度，直接相加，进位直接往前进位
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	if (l1 == null)
    		return l2;
    	if (l2 == null)
    		return l1;
        //new two ListNode, newhead is fakehead, and it's next point to the head node.easy to return
    	ListNode newhead = new ListNode(-1);
    	ListNode l3 = newhead;
    	//flag 有两个作用，在一次while循环中，累加保存l1 + l2的值，
    	//用取余来表示该位的两数相加结果在该位上的数。
    	//用除以10，来表示该位是否需要进位，如需进位则flag 再最后会等于1，在下一个while循环时将会 等于进位+1
    	int flag = 0;
    	while (l1 !=null || l2 != null) {
    		if (l1 != null) {
    			flag += l1.val;
    			l1 = l1.next;
    		}
    		if (l2 != null) {
    			flag += l2.val;
    			l2 = l2.next;
    		}

            //除10的余数，小于10的和取啥就是啥，大于10一律1，个位数相加不可能余数大于=2的情况
    		l3.next = new ListNode(flag % 10);//每次需new一个node
            //表示进位，最大只能为1
    		flag = flag / 10;
    		l3 = l3.next;
    	}
        //假如flag == 1代表最后一位还需进位+1
    	if (flag == 1)
    		l3.next = new ListNode(1);
    	return newhead.next;
    }
}