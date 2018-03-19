1. Reverse Linked List
	1.1	最普通的reverse，给你一个ListNode，然后reverse从这个ListNode 开始的所有节点
		public ListNode reverse(ListNode head) {
			ListNode pre = null;
			while (head != null) {
				ListNode next = head.next;
				head.next = pre;
				pre = head;
				head = next;
			}
		}

	1.2	反转从链表的第m个结点至第n个结点，1 <= m <= n <= length of list
		for (int i = 0; i < n; i++) {
			if (i < m - 1) {//先找到m-1这个点
				premNode = premNode.next;
			} else if (i == m - 1) {//这样才方便后续reverse 
									//initial: 1 ->  2  -> 3 ->  4 -> 5 -> NULL, m = 2 and n = 4， final：1->4->3->2->5->NULL.
									        //preM mNode nNode
				mNode = premNode.next;
				nNode = mNode.next;
			} else {
				mNode.next = nNode.next;
				nNode.next = premNode.next;
				premNode.next = nNode;
				nNode = mNode.next;
			}
		}

	
	1.3 按照一组k个翻转结点， 比如 1 2 3 4 5 6 7 8 9 ,k = 3 就是每3个节点一组reverse， 3 2 1 6 5 4 9 8 7
		public ListNode reverseKGroup(ListNode head, int k) {
	        if (head == null || k == 1) {
	            return head;
	        }
	        ListNode newhead = new ListNode(0);
	        newhead.next = head;
	        ListNode cur = head;
	        ListNode pre = newhead;
	        int count = 0;
	        while (cur != null) {
	            count++;
	            ListNode next = cur.next;
	            if (count == k) {
	                pre = reverse(pre, next);
	                count = 0;
	            }
	            cur = next;
	        }
	        return newhead.next;
	    }
	    //关键部分，pre是要开始reverse的点的前一个点， next是范围内最后一个点的next点，last是翻转以后最后面的点
	    public ListNode reverse(ListNode pre, ListNode next) {
	    	//
	        ListNode last = pre.next;
	        ListNode cur = last.next;
	        while (cur != next) {
	            last.next = cur.next;
	            cur.next = pre.next;
	            pre.next = cur;
	            cur = last.next;
	        }
	        return last;//正好 返回给下一次reverse的 rpe
	    }
	1.4 将所有的结点两两swap，比如 1-2-3-4 返回 4-3-2-1
		public class Solution {
		    public ListNode swapPairs(ListNode head) {
		        ListNode newhead = new ListNode(0);
		        newhead.next = head;
		        ListNode cur = newhead;
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
	1.5 反转第二段链表
		public class solution {
			public static ListNode reverseSecondHalfList(ListNode head) {
				if (head == null || head.next == null)	
					return head;
				ListNode fast = head;
				ListNode slow = head;
				while (fast.next != null && fast.next.next != null) {
					fast = fast.next.next;
					slow = slow.next;
				}
				ListNode pre = slow.next;
				ListNode cur = pre.next;
				while (cur != null) {
					pre.next = cur.next;
					cur.next = slow.next;
					slow.next = cur;
					cur = pre.next;
				}
				return head;
			}
		}
	}
	1.6 Rotate List //Given a list, rotate the list to the right by k places, where k is non-negative.
		// For example:
		// 	Given  1->2->3->4->5->NULL and k = 2,
		// 	return 4->5->1->2->3->NULL.
		public class Solution {
		    public ListNode rotateRight(ListNode head, int k) {
		        if (head == null || head.next == null) {
		            return head;
		        }
		        ListNode dummy = new ListNode(0);
		        dummy.next = head;
		        ListNode fast = dummy;
		        ListNode slow = dummy;
		        int len = 0;
		        while (fast.next != null) {
		            fast = fast.next;
		            len++;
		        }
		        for (int i = 0; i < len - k % len; i++) {
		            slow = slow.next;
		        }
		        fast.next = dummy.next;
		        dummy.next = slow.next;
		        slow.next = null;
		        return dummy.next;
		    }
		}

2. Double Pointer Method
	2.1 Find Median
	   	ListNode slow = head;
	   	ListNode fast = head;
	   	while (fast.next != null && fast.next.next != null) {
		   	fast = fast.next.next;
		   	slow = slow.next;
	    }
	    链表为奇数时slow就是median，链表为偶数时,
	2.2 Find The Last Nth Nodepublic ListNode 
		public ListNode removeNthFromEnd(ListNode head, int n) {
			ListNode fast = head;
			ListNode slow = head;
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
	2.3 Linked List CycleI
		设置slow = slow.next，fast = fast.next.next，只要相遇就是有cycle否则没有
		public class FindLoopsInLinkedList {
			public boolean hasCycle(ListNode head) {
				if (head == null) {
					return false;
				}
				ListNode slow = head;
				ListNode fast = head;
				while (fast != null && fast.next != null) {
					slow = slow.next;
					fast = fast.next.next;
					if (slow == fast) {
						return true;
					}
				}
				return false;
			}	
		}

	2.4 Linked List CycleII
		step1: fast走两步，slow走一步，
		step2: 第一次相遇时slow走过的距离：a+b，fast走过的距离：a+b+c+b
				a:头结点到环入口的距离
				b:slow在环里走的距离
				c:环总距离减去b
		step3: 2(a + b) = a + b + c + b   ===> a = c，
		step4: 因此slow和fast相遇后，将slow指向头结点，slow 和fast 分别继续走，第二次相遇时的点就是环入口
		public ListNode detectCycleStartPosition(ListNode head) {  
		 	ListNode slow = head;
		 	ListNode fast = head;
		 	while (fast != null && fast.next != null) {
		 		fast = fast.next.next;
		 		slow = slow.next;
		 		if (fast == slow) {
		 			slow = head;
		 			while (fast != slow) {
		 				fast = fast.next;
		 				slow = slow.next;
		 			}
		 			return slow;
		 		}
		 	}
		 	return null;
		}

    2.5 Reorder List  //Given {1,2,3,4}, reorder it to {1,4,2,3}.
		public class Solution {
		    public void reorderList(ListNode head) {
		        if (head == null || head.next == null) {
		            return;
		        }
		        ListNode slow = head;
		        ListNode fast = head;
		        while (fast.next != null && fast.next.next != null) {
		            fast = fast.next.next;
		            slow = slow.next;
		        }
		        ListNode secondHalf = slow.next;
		        slow.next = null;
		        secondHalf = reverse(secondHalf);// Using 1.1 reverse
		        merge(head, secondHalf);//Using 3.1 merge Method
		    }
		}

	2.6 Palindrome Linked List
		public class Solution {
		    public boolean isPalindrome(ListNode head) {
		        if (head == null || head.next == null) {
		            return true;
		        }
		        ListNode fast = head;
		        ListNode slow = head;
		        while (fast.next != null && fast.next.next != null) {
		            slow = slow.next;
		            fast = fast.next.next;
		        }
		        ListNode secondList = slow.next;
		        slow.next = null;
		        ListNode partTwo = reverse(secondList);//1.1 普通Reverse 
		        ListNode partOne = head;
		        while (partTwo != null) {
		            if (partOne.val != partTwo.val) {
		                return false;
		            }
		            partOne = partOne.next;
		            partTwo = partTwo.next;
		        }
		        return true;
		    }
		}

3. Merge Two List
	3.1 Just Merge (without order)
		public void merge(ListNode l1, ListNode l2) {
			while (l1 != null && l2 != null) {
				ListNode temp1 = l1.next;
				ListNode temp2 = l2.next;
				l1.next = l2;
				l2.next = temp1;
				l1 = temp1;
				l2 = temp2;
			}
		}

	3.2 Merge With Order
		public ListNode merge(ListNode l1, ListNode l2) {
	        if (l1 == null) {
	            return l2;
	        }
	        if (l2 == null) {
	            return l1;
	        }
	        ListNode dummy = new ListNode(0);
	        ListNode l3 = dummy;
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
	        if (l1 != null) {
	            l3.next = l1;
	        }
	        if (l2 != null) {
	            l3.next = l2;
	        }
	        return dummy.next;
	    }
4. Find Length Of Linked List
	4.1	int len = 0; 
		ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
		while (fast.next != null) {
		 	fast = fast.next;
		 	len++;
		}//上面的这种方法求出来 pointer是指向最后一个结点，因此我们还可以借由fast.next 执行一些比如反转的操作

    4.2 while (fast != null) {
    		fast = fast.next;
    		len++
    	}//这种方法求出来 fast最后会是最后一个结点的next，也就是null，所以fast结点就废了，没有用处

5. LinkedList Sort
	5.1 Sort One LinkedList(Merge Sort)
		public ListNode sortList(ListNode head) {
			//How it works?
			/*
				1. Find the median so that we can separate the List to two part
				2. recursively seperate the list and merge then
			*/
	        if (head == null || head.next == null) {
	            return head;
	        }
	        ListNode slow = head;
	        ListNode fast = head;
	        ListNode firsthalf = head;
	        //find median method
	        while (fast.next != null && fast.next.next != null) {
	            slow = slow.next;
	            fast = fast.next.next;
	        }
	        ListNode secondhalf = slow.next;
	        slow.next = null;
	        ListNode leftlists = sortList(firsthalf);
	        ListNode rightlists = sortList(secondhalf);
	        return merger(leftlists, rightlists);
	    }
	5.2 Sort K Sorted LinkedlList(Merge Sort)
	    public ListNode mergeSort(ListNode[] lists, int start, int end) {
	        if (start >= end) {
	            return lists[start];
	        }
	        int mid = start + (end - start) / 2;
	        ListNode leftlist = mergeSort(lists, start, mid);
	        ListNode rightlist = mergeSort(lists, mid + 1, end);
	        return merge(leftlist, rightlist);
    	}

    5.3 Insertion Sort
    	public ListNode insertionSortList(ListNode head) {
	        if (head == null || head.next == null) {
	            return head;
	        }
	        //一定纳闷为什么这里dummy没有 dummy.next = head吧？
	        //因为在下面会将dummy赋值给pre，并且后面还有pre.next = cur， 因此在第一次while循环时就已将将dummy.next = cur!
	        ListNode dummy = new ListNode(-1);
	        ListNode cur = head;
	        while (cur != null) {
	            ListNode next = cur.next;
	            ListNode pre = dummy;
	            while (pre.next != null && pre.next.val < cur.val) { // 将cur 插入到 pre 以及 pre.next 之间， pre < cur < pre.next
	                pre = pre.next;
	            }
	            cur.next = pre.next;
	            pre.next = cur;
	            cur = next;
	        }
        	return dummy.next;
    	}
    5.4 Partition List  //Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
			  			//Given 1->4->3->2->5->2 and x = 3,
					    //return 1->2->2->4->3->5.
		public class Solution {
		    public ListNode partition(ListNode head, int x) {
		        if (head == null || head.next == null) {
		            return head;
		        }
		        ListNode small = new ListNode(-1);
		        ListNode smallHead = small;
		        ListNode larger = new ListNode(-1);
		        ListNode largerHead = larger;
		        while (head != null) {
		            if (head.val < x) {
		                small.next = head;
		                small = small.next;
		            } else {
		                larger.next = head;
		                larger = larger.next;
		            }
		            head = head.next;
		        }
		        larger.next = null;
		        small.next = largerHead.next;
		        return smallHead.next;
		    }
		}
	5.5 Odd Even Linked List
		/*
			Given a singly linked list, group all odd nodes together followed by the even nodes. 
			Please note here we are talking about the node number and not the value in the nodes.
			You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

			Example:
			Given 1->2->3->4->5->NULL,
			return 1->3->5->2->4->NULL.

			Note:
			The relative order inside both the even and odd groups should remain as it was in the input. 
			The first node is considered odd, the second node even and so on ...
		 */
		public class Solution {
		    public ListNode oddEvenList(ListNode head) {
		        if (head == null || head.next == null) {
		            return head;
		        }
		        ListNode odd = head;
		        ListNode even = odd.next;
		        ListNode evenHead = even;
		        while (even != null && even.next != null) {
		            odd.next = odd.next.next;
		            even.next = even.next.next;
		            odd = odd.next;
		            even = even.next;
		            
		        }
		        odd.next = evenHead;
		        return head;
		    }
		}


6. Remove Elements
	6.1  在有序的链表中移除重复结点使得每个结点只出现一次
	public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    6.2 在有序的链表中移除所有的重复结点（全部清空）
    public static ListNode deleteDuplicates(ListNode head) {
        // write your code here
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if(cur.next.val == cur.next.next.val) {
                int val = cur.next.val;
                while (cur.next != null && cur.next.val == val) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    6.3 删除所有node.val == val的结点
    public ListNode removeElements(ListNode head, int val) {
        // Write your code here
        if (head == null) {
            return head;
        }
        ListNode newhead = new ListNode(0);
        newhead.next = head;
        ListNode cur = newhead;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return newhead.next;
    }

    6.4 移除从后数起第n个结点
    	public ListNode removeNthFromEnd(ListNode head, int n) {
			ListNode fast = head;
			ListNode slow = head;
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

7. LinkedList Insert, Delete Operation
	7.1 Insert Node In A Loop Linked List
		public class LinkedListInsert {
			public ListNode Solution(ListNode head, int val) {
				if (head == null) {
					ListNode rvalue = new ListNode(val);
					rvalue.next = rvalue;
					return rvalue;
				}
				ListNode cur = head;
				do {
					if (val <= cur.next.val && val >= cur.val)	break;
					if (cur.val > cur.next.val && (val < cur.next.val || val > cur.val))	break;
					cur = cur.next;
				} while (cur != head);
				ListNode newNode = new ListNode(val);
				newNode.next = cur.next;
				cur.next = newNode;
				return newNode;
			}
		}
	7.2 Delete Node In A Linked List
	public class Solution {
	    public void deleteNode(ListNode node) {
	        if (node == null || node.next == null) {
	            return;
	        }
	        node.val = node.next.val;
	        node.next = node.next.next;
	    }
	}

8. Math Operation In LinkedList
	8.1 Add Two Numbers
		Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
		Output: 7 -> 0 -> 8
		public class Solution {
		    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		        if (l1 == null) {
		            return l2;
		        }
		        if (l2 == null) {
		            return l1;
		        }
		        ListNode newhead = new ListNode(-1);
		        ListNode l3 = newhead;
		        int sum = 0;
		        while (l1 != null || l2 != null) {
		            if (l1 != null) {
		                sum += l1.val;
		                l1 = l1.next;
		            }
		            if (l2 != null) {
		                sum += l2.val;
		                l2 = l2.next;
		            }
		            l3.next = new ListNode(sum % 10);
		            sum = sum / 10;
		            l3 = l3.next;
		        }
		        if (sum == 1) {
		            l3.next = new ListNode(1);
		        }
		        return newhead.next;
		    }
		}

9. Two LinkedList Operation
	9.1 Intersection Of Two Linked Lists
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
	    	if (lenA > lenB) {
	    		for (int i = 0; i < lenA - lenB; i++) {
	    			h1 = h1.next;
	    		}
	    	} else { 
	    		for (int i = 0; i < lenB - lenA; i++) {
	    			h2 = h2.next;
	    		}
	    	}
	    	while (h1 != null && h2 != null) {
	    		if (h1.val == h2.val) {
	    			return h1;
	    		} else {
	    			h1 = h1.next;
	    			h2 = h2.next;
	    		}

	    	}
	    	return null;
	    }
	9.2 Copy List With Random Pointer
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




