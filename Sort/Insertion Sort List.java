/*
	Insertion Sort List 
	Sort a linked list using insertion sort.
*/



/*
	public static void insertionSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int insertValue = arr[i];
			int index = i - 1;
			while (index >= 0 && insertValue < arr[index]) {
				arr[index + 1] = arr[index];
				index--; 
			}
			arr[index + 1] = insertValue;
		}
	}

	Insertion Sort就是把一个一个元素往已排好序的list中插入的过程。
 	1. 初始时，sorted list是空，把一个元素插入sorted list中。然后，在每一次插入过程中，都是找到最合适位置进行插入。
 	   因为是链表的插入操作，需要维护pre，cur和next3个指针。
 	2. pre始终指向sorted list的newhead，cur指向当前需要被插入的元素，next指向下一个需要被插入的元素。

 	3. 当newhead为空以及pre.next所指向的元素比cur指向的元素值要大时，需要把cur元素插入到pre.next所指向元素之前。
 		否则，pre指针后移。最后返回newhead的next即可。


	 newhead (loop1: start)
 	  -1       4  -->   6  --> 3 --> null
 	  pre     cur     next

 	 newhead (loop1: end)
 	  -1 --> 4       6 -->  3 --> null
 	  		pre    cur,next    

 	 newhead (loop2: start)
 	 -1 --> 4      6 --> 3 --> null
	 pre	      cur    next

	 newhead (loop2: end)
	 -1 --> 4 --> 6   3 --> null
			pre	  	 cur,next

	 newhead (loop3: start)
	 -1 --> 4 --> 6   3 --> null
	 pre              cur    next

	 newhead (loop3: end)
	 -1 --> 3 --> 4 --> 6  null
	 pre                    cur,next; 

*/
public class Solution {
	public ListNode insertionSortList(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode newhead = new ListNode(0);//dummy head for the result sorted LinkedList
		ListNode cur = head;//cur is current node, the node to be plugged in the sorted list

		//cur代表当前要排序的点
		while (cur != null) {
			//next是cur的下一结点，先保存，后面交换后 cur 要往前走
			ListNode next = cur.next;//keep a record of the current node's next
			//每次循环都将pre设为头结点，每次从头开始遍历比较
			ListNode pre = newhead;//after one search, put pre back to its original place

			//每次pre从第一个结点开始比较，只要pre.next 非空 并且 pre.next的值小于cur就一直往前走
			//注意这里比较的是pre.next和cur的值
			// //use pre to traverse the sorted list to find the suitable place to plug in
			while (pre.next != null && pre.next.val < cur.val)
				pre = pre.next;
			
			//跳出while循环意味着，1.要不pre.next为空，或者 2.pre.next的val值 大于等于cur,应将cur插到pre后面，
			//因此需要重新将pre.next指向新的比较结点cur
			cur.next = pre.next; // plug in current node to the right place
			pre.next = cur;
			cur = next;//go on to deal with the next node in the unsorted list
		}
		return newhead.next;
	}
}