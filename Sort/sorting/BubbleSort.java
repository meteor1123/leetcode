/*
	每一趟依次比较相邻的两个数据元素，将较小的数放在左边，循环进行同样的操作，直到全部待排序的数据元素排完。
	1> 每一趟过程中，就是依次比较两个相邻的数，若a[i]>a[i+1]，则交换两数，否则不换；
    2> 每一趟就是一重循环，而由于要经过多趟过程，即外面还有一重循环，所以就存在双重循环。
*/
public class BubbleSort {
	public static void sort(int[] A) {
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length - i - 1; j++) {
				if (A[j] > A[j + 1]) {
					int temp = A[j];
					A[j] = A[j + 1];
					A[j + 1] = temp;
				}
			}
		}
	}
}


// Bubble Sort O(n*n), Microsoft interview question
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        int len = 0;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy.next;
        while (cur != null) {
            cur = cur.next;
            len++;
        }
        
        for (int i = 0 ; i < len; i++) {
            cur = head;
            ListNode next = cur.next;
            for (int j = 0; j < len - 1; j++) {
                if (cur.val > next.val) {
                    int temp = cur.val;
                    cur.val = next.val;
                    next.val = temp;
                }
                cur = next;
                next = next.next;
            }
        }
        return dummy.next;
    }
}