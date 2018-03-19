/*
	A linked list is given such that each node contains an additional random pointer
	which could point to any node in the list or null.

	Return a deep copy of the list.
*/

/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */

/*
	Solution :
	如果要copy一个带有random pointer的list，主要的问题就是有可能这个random指向的位置还没有被copy到。

	第一遍，对每个node进行复制，并插入其原始node的后面，新旧交替，变成重复链表。如：原始：1->2->3->null，复制后：1->1->2->2->3->3->null

 	第二遍，遍历每个旧node，把旧node的random的复制给新node的random，因为链表已经是新旧交替的。所以复制方法为：

          node.next.random = node.random.next

          前面是说旧node的next的random，就是新node的random，后面是旧node的random的next，正好是新node，是从旧random复制来的。

 	第三遍，则是把新旧两个表拆开，返回新的表即可。
*/

public class Solution {
    /*
        1. 遍历原有链表，在每个原来的节点后面增加一个拷贝节点。
        2. 根据原节点的随机指针设置拷贝节点的随机指针。
        3. 剥离出所有拷贝节点。
    */

    
    //standard name
        public RandomListNode copyRandomList(RandomListNode head) {
        // write your code here
        if (head == null) {
            return head;
        }
        
        RandomListNode oldNode = head;
        while (oldNode != null) {
            RandomListNode copyNode = new RandomListNode(oldNode.label);
            copyNode.next = oldNode.next;
            oldNode.next = copyNode;
            oldNode = copyNode.next;
        }
        
        oldNode = head;
        
        while (oldNode != null && oldNode.next != null) {
            if (oldNode.random != null) {
                oldNode.next.random = oldNode.random.next;
            }
            oldNode = oldNode.next.next;
        }
        
        oldNode = head;
        RandomListNode newhead = head.next;
        RandomListNode copyNode = head.next;
        
        while (copyNode != null) {
            oldNode.next = copyNode.next;
            if (copyNode.next != null) {
                copyNode.next = copyNode.next.next;
            }
            oldNode = oldNode.next;
            copyNode = copyNode.next;
        }
        return newhead ;
    }

    public RandomListNode copyRandomList(RandomListNode head) {
    	if (head == null)
    		return null;

    	//step1 
    	RandomListNode copy = head;
    	while (copy != null) {
    		RandomListNode node = new RandomListNode(copy.label);
    		node.next = copy.next;
    		copy.next = node;
    		copy = node.next;
    	}

    	//step2 
    	copy = head;
    	while (copy != null && copy.next != null) {
    		if (copy.random != null) 
    			copy.next.random = copy.random.next;
    		copy = copy.next.next;
    	}

    	//step3

    	copy = head;
    	//返回的新链表头结点
    	RandomListNode newhead = head.next;
    	//遍历新链表的结点
    	RandomListNode tmp = newhead;

    	//新旧结点都非空 就继续
    	while (copy != null && tmp != null) {
    		//旧链表结点连接原本的旧链表结点的next，因为之前在每个旧结点后面复制了一个新结点
    		copy.next = tmp.next;

    		//旧链表结点遍历到下一个旧链表结点
    		copy = copy.next;

    		//假如新链表的next非空（可能会是最后一个新链表结点，因此需要判断null）
    		if (tmp.next != null)
    			tmp.next = tmp.next.next;//非空就指向下一个新的链表结点
    		tmp = tmp.next;//新链表结点遍历到下一个新链表结点
    	}
    	return newhead;
    }
}



/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */


//Modified the variable name
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return head;
        }
        //copy every ListNode
        RandomListNode oldNode = head;
        while (oldNode != null) {
            RandomListNode copyNode = new RandomListNode(oldNode.label);
            copyNode.next = oldNode.next;
            oldNode.next = copyNode;
            oldNode = copyNode.next;
        }
        //set the random value
        oldNode = head;
        while (oldNode != null && oldNode.next != null) {
            if (oldNode.random != null) {
                oldNode.next.random =  oldNode.random.next;
            }
            oldNode = oldNode.next.next;
        }
        //divide the ListNode
        oldNode = head;
        RandomListNode newhead = head.next;
        RandomListNode copyList = newhead;
        while (copyList != null) {
            oldNode.next = copyList.next;
            oldNode = oldNode.next;
            if (copyList.next != null) {
                copyList.next = copyList.next.next;
            }
            copyList = copyList.next;
        }
        return newhead;
    }
}