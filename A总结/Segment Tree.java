/*
	Segment Tree
*/

0. Introduction
	/*

		1. What is Segment Tree?
			The structure of Segment Tree is a binary tree which each node has two attributes start and end denote an segment / interval.
			
		2. What kinds of attributes segment tree have?
			start and end are both integers, they should be assigned in following rules:

			(1)The root's start and end is given by build method.
		    (2)The left child of node A has:
		    		start = A.left
		    		end   = (A.left + A.right) / 2
			(3)The right child of node A has:
					start = (A.left + A.right) / 2 + 1, 
					end   = A.right.
			(4)if start equals to end, there will be no children for this node.
		
		3. Example 
			Implement a build method with two parameters start and end, so that we can create a corresponding segment tree with every node has the correct start and end value, 
			return the root of this segment tree.

			Example
				Given start=0, end=3. The segment tree will be:

				               [0,  3]
				             /        \
				      [0,  1]           [2, 3]
				      /     \           /     \
				   [0, 0]  [1, 1]     [2, 2]  [3, 3]
				Given start=1, end=6. The segment tree will be:

				               [1,  6]
				             /        \
				      [1,  3]           [4,  6]
				      /     \           /     \
				   [1, 2]  [3,3]     [4, 5]   [6,6]
				   /    \           /     \
				[1,1]   [2,2]     [4,4]   [5,5]
		4. What kinds of problem segment tree can be used to solve?
			Segment Tree (a.k.a Interval Tree) is an advanced data structure which can support queries like:

			1. which of these intervals contain a given point
			2. which of these points are in a given interval

		5. Definition of SegmentTreeNode
			/**
			 * Definition of SegmentTreeNode:
			 * public class SegmentTreeNode {
			 *     public int start;end;
			 *     public SegmentTreeNode left, right;
			 *     public SegmentTreeNode(int start, int end) {
			 *         this.start = start, this.end = end;
			 *         this.left = this.right = null;
			 *     }
			 * }
			 */
	*/

1. Operation Of Segment Tree
		1.1 Segment Tree Build
			public class Solution {
				public SegmentTreeNode build(int start, int end) {
					if (start > end) {
						return null;
					}
					SegmentTreeNode root = new SegmentTreeNode(start, end);
					if (start != end) {
						int mid = (start + end) / 2;
						root.left = build(start, mid);
						root.right = build(mid + 1, end);
					}
					return root;
				}
			}
		1.2 Segment Tree Query I
			/*
				Segment Tree Query
				For an integer array (index from 0 to n-1, where n is the size of this array), 
				in the corresponding SegmentTree, each node stores an extra attribute max to denote the maximum number in the interval of the array (index from start to end).

				Design a query method with three parameters root, start and end, 
				find the maximum number in the interval [start, end] by the given root of segment tree.

				Example
					For array [1, 4, 2, 3], the corresponding Segment Tree is:

					                  [0, 3, max=4]
					                 /             \
					          [0,1,max=4]        [2,3,max=3]
					          /         \        /         \
					   [0,0,max=1] [1,1,max=4] [2,2,max=2], [3,3,max=3]

					query(root, 1, 1), return 4

					query(root, 1, 2), return 4

					query(root, 2, 3), return 3

					query(root, 0, 2), return 4
			*/
			//求范围内的最大值
			public class Solution {
				public int quert(SegmentTreeNode root, int start, int end) {
					if (start == root.start && end == root.end) {
						return root.max;
					}
					int mid = (root.start + root.end) / 2;
					int leftmax = Integer.MIN_VALUE;
					int rightmax = Integer.MIN_VALUE;
					if (start <= mid) {
						leftmax = query(root.left, start, Math.min(mid, end));
					}
					if (mid < end) {
						rightmax = query(root.right, Math.max(mid + 1, start), end);
					}
					return Math.max(leftmax, rightmax);
				}
			}
		1.3 Segment Tree Query II
			/*
				For an array, we can build a SegmentTree for it, each node stores an extra 
				attribute count to denote the number of elements in the the array which value is between interval start and end.
				(The array may not fully filled by elements)

				Design a query method with three parameters root, start and end, find the number of elements in the in array's interval [start, end] by the given root of value SegmentTree.

				Example
					For array [0, 2, 3], the corresponding value Segment Tree is:

					                     [0, 3, count=3]
					                     /             \
					          [0,1,count=1]             [2,3,count=2]
					          /         \               /            \
					   [0,0,count=1] [1,1,count=0] [2,2,count=1], [3,3,count=1]
				query(1, 1), return 0
				query(1, 2), return 1
				query(2, 3), return 2
				query(0, 2), return 2
			*/
			//求范围内的节点数,每个节点都有一个count，记录这个范围内节点的数量
			/*
				如果查询区间 包含节点表示区间（start <= root.start && root.end <= end） => 直接返回root.count
			    如果查询区间被节点表示的左/右区间包含 => 递归搜索左/右区间
			    如果查询区间和结点表示的区间相交不相等 => 分裂递归搜索左/右区间
			    典型的divide & conquer
			    最后返回 leftCount + rightCount
			*/
				public class Solution {
					public int query(SegmentTreeNode root, int start, int end) {
						if (start > end || root == null) {
							return 0;
						}
						if (start <= root.start && root.end <= end) {
							return root.count;
						}
						int mid = (root.start + root.end) / 2;
						int leftsum = 0, rightsum = 0;

						//left side
						if (start <= mid && root.left != null) {
							leftsum = query(root.left, start, Math.min(mid, end));
						}
						//right side
						if (mid < end && root.right != null) {
							rightsum = query(root.right, Math.max(start, mid + 1), end);
						}
						return leftsum + rightsum;
					}
				}
		1.2 Segment Tree Modify
			/*
				Segment Tree Modify
				For a Maximum Segment Tree, which each node has an extra value max to store the maximum value in this node's interval.

				Implement a modify function with three parameter root, 
				index and value to change the node's value with [start, end] = [index, index] to the new given value. 
				Make sure after this change, every node in segment tree still has the max attribute with the correct value.

				Example
				For segment tree:

				                      [1, 4, max=3]
				                    /                \
				        [1, 2, max=2]                [3, 4, max=3]
				       /              \             /             \
				[1, 1, max=2], [2, 2, max=1], [3, 3, max=0], [4, 4, max=3]
				
				if call modify(root, 2, 4), we can get:
				                      [1, 4, max=4]
				                    /                \
				        [1, 2, max=4]                [3, 4, max=3]
				       /              \             /             \
				[1, 1, max=2], [2, 2, max=4], [3, 3, max=0], [4, 4, max=3]
				
				or call modify(root, 4, 0), we can get:
				                      [1, 4, max=2]
				                    /                \
				        [1, 2, max=2]                [3, 4, max=0]
				       /              \             /             \
				[1, 1, max=2], [2, 2, max=1], [3, 3, max=0], [4, 4, max=0]

				Note
				We suggest you finish problem Segment Tree Build and Segment Tree Query first.

				Challenge
				Do it in O(h) time, h is the height of the segment tree.
			*/
				public class Solution {
    				public void modify(SegmentTreeNode root, int index, int value) {
    					if (root.start == index && root.end == index) {
    						root.max = value;
    						return;
    					}
    					int mid = (root.start + root.end) / 2;
    					if (root.start <= index && index <= mid) {
    						modify(root.left, index, value);
    					}
    					if (mid < index && index <= root.end) {
    						modify(root.right, index, value);
    					}
    					root.max = Math.max(root.left.max, root.right.max);
    				}
    			}




2. Problem
		2.1 Range Sum Query - Mutable
			/*
				Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

				The update(i, val) function modifies nums by updating the element at index i to val.
				Example:
				Given nums = [1, 3, 5]

				sumRange(0, 2) -> 9
				update(1, 2)
				sumRange(0, 2) -> 8
				Note:
				The array is only modifiable by the update function.
				You may assume the number of calls to update and sumRange function is distributed evenly.
			*/
			public class NumArray {
			    class SegmentTreeNode {
			        int start;
			        int end;
			        int sum;
			        SegmentTreeNode left;
			        SegmentTreeNode right;
			     
			        public SegmentTreeNode (int start, int end) {
			            this.start = start;
			            this.end = end;
			            this.left = null;
			            this.right = null;
			            this.sum = 0;
			        }
			    }
			    SegmentTreeNode root = null;
			    public NumArray(int[] nums) {
			        root = buildTree(nums, 0, nums.length - 1);
			    }
			    private SegmentTreeNode buildTree(int[] nums, int start, int end) {
			        if (start > end) {
			            return null;
			        } 
			        SegmentTreeNode res = new SegmentTreeNode(start, end);
			        if (start == end) {
			            res.sum = nums[start];
			        } else {
			            int mid = start + (end - start) / 2;
			            res.left = buildTree(nums, start, mid);
			            res.right = buildTree(nums, mid + 1, end);
			            res.sum = res.left.sum + res.right.sum;
			        }
			        return res;
			    }
			    void update(int i, int val) {
			        update(root, i, val);
			    }
			    
			    void update(SegmentTreeNode root, int pos, int val) {
			        if (root.start == root.end) {
			            root.sum = val;
			        } else {
			            int mid = root.start + (root.end - root.start) / 2;
			            if (pos <= mid) {
			                update(root.left, pos, val);
			            } else {
			                update(root.right, pos, val);
			            }
			            root.sum = root.left.sum + root.right.sum;
			        }
			    }

			    public int sumRange(int i, int j) {
			        return sumRange(root, i, j);
			    }
			    
			    public int sumRange(SegmentTreeNode root, int start, int end) {
			        if (root.end == end && root.start == start) {
			            return root.sum;
			        } else {
			            int mid = root.start + (root.end - root.start) / 2;
			            if (end <= mid) { 
			                return sumRange(root.left, start, end);
			            } else if (start >= mid + 1) {
			                return sumRange(root.right, start, end);
			            } else {
			                return sumRange(root.right, mid + 1, end) + sumRange(root.left, start, mid);
			            }
			        }
			    }
			}



