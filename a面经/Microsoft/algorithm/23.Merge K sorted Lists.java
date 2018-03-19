/** Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
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
 
/*
    注意到输入采用一个ArrayList存放各个链表的头结点，因此可以采用ArrayList的get(index)取出排序好的链表,形如数组下标进行
*/
public class Solution {
	/*
		我们来分析一下上述算法的时间复杂度。假设总共有k个list，每个list的最大长度是n，
		那么运行时间满足递推式T(k) = 2T(k/2)+O(n*k)。
		根据主定理，可以算出算法的总复杂度是O(nklogk)。
		空间复杂度的话是递归栈的大小O(logk)。
	*/
	//Merge sort
 	public ListNode mergeKLists(ArrayList<ListNode> lists) {
 		if (lists == null || lists.size() == 0)
 			return null;
 		return MSort(lists, 0, lists.size() - 1);
 	}
    
    //算法的关键部分；
 	public ListNode MSort(ArrayList<ListNode> lists, int low, int high) {
 	    //将K个链表的头结点存入一个ArrayList中
 		if (low >= high)
 			return lists.get(low);//递归终止条件
 		int mid = (low + high) / 2;//递归逼近语句
 		ListNode leftlist = MSort(lists, low, mid);
 		ListNode rightlist = MSort(lists, mid + 1, high);
 		return mergeTwoLists(leftlist, rightlist);
 	}
    
    //直接采用mergeTwoSortedLists的方法
 	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
 		if (l1 == null)
 			return l2;
 		if (l2 == null)
 			return l1;

 		ListNode newhead = new ListNode(-1);
 		ListNode l3 = newhead;
 		while (l1 != null && l2 != null) {
 			if (l1.val < l2.val) {
 				l3.next = l1;
 				l1 = l1.next;
 			} else {
 				l3.next = l2;
 				l2 = l2.next;
 			}
 			l3 = l3.next;
 		}

 		if (l1 != null) 
 			l3.next = l1;
 		if (l2 != null)
 			l3.next = l2;
 		return newhead.next;
 	}

    // recursive 方法
    public ListNode mergeTwoList(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        
        if (l1.val <= l2.val) {
            l1.next = mergeTwoList(l1.next, l2);
            return l1;
        }
        l2.next = mergeTwoList(l1, l2.next);
        return l2;
    }



   /*
	*  接下来我们来看第二种方法。这种方法用到了堆的数据结构，思路比较难想到，但是其实原理比较简单。
	*  维护一个大小为k的堆，每次取堆顶的最小元素放到结果中，然后读取该元素的下一个元素放入堆中，重新维护好。
	*  因为每个链表是有序的，每次又是去当前k个元素中最小的，所以当所有链表都读完时结束，这个时候所有元素按从小到大放在结果链表中。
	*  这个算法每个元素要读取一次，即是k*n次，然后每次读取元素要把新元素插入堆中要logk的复杂度，
	*  所以总时间复杂度是O(nklogk)。空间复杂度是堆的大小，即为O(k)。
 	*
 	*/
    //Solution2 prefer
    public ListNode mergeKLists(ListNode[] lists) {  
        // write your code here
        if (lists == null || lists.length == 0) {
            return null;
        }
        //最小堆，默认就是最小堆，但是要给ListNode写comparator
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(new Comparator<ListNode>(){
            @Override
            public int compare(ListNode l1, ListNode l2) {
                return l1.val - l2.val;
            }
        });
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        //先把所有的头结点加入堆中，
        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(node);
            }
        }
        
        while (!queue.isEmpty()) {
            //Poll出来的肯定是最小的，接上
            tail.next = queue.poll();
            //如果tail.next 非空，加入queue
            tail = tail.next;
            //判断tail.next
            if (tail.next != null) {
                queue.add(tail.next);
            }
        }
        return dummy.next;
    }

    //Solution3 
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.size() == 0){
        	return null;
        } 
        PriorityQueue<ListNode> queue= new PriorityQueue<ListNode>(lists.size(),new Comparator<ListNode>(){
            @Override
            public int compare(ListNode o1,ListNode o2){
                if (o1.val < o2.val)
                    return -1;
                else if (o1.val == o2.val)
                    return 0;
                else 
                    return 1;
            }
        });

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for (ListNode node : lists)
            if (node != null)
                queue.add(node);

        while (!queue.isEmpty()){
            tail.next = queue.poll();
            tail = tail.next;

            if (tail.next!=null)
                queue.add(tail.next);
        }
        return dummy.next;
    }
}