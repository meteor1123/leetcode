/*
	Zenefits
*/


1. Binary Tree
		1.1 Verify Preorder Sequence In Binary Search Tree
			/*
				Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
				You may assume each number in the sequence is unique.

				Follow up:
				Could you do it using only constant space complexity?
			*/

			//Solution1: 
			public boolean verifyPreorder(int[] preorder) {
		        return verify(preorder, 0, preorder.length - 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
		    }
		    
		    public boolean verify(int[] preorder, int start, int end, int min, int max) {
		        if (start > end) {
		            return true;
		        }
		        int root = preorder[start];
		        if (root > max || root < min) {
		            return false;
		        }
		        int rightIndex = start;
		        while (rightIndex <= end && preorder[rightIndex] <= root) {
		            rightIndex++;
		        }
		        return verify(preorder, start + 1, rightIndex - 1, min, root) &&
		               verify(preorder, rightIndex + 1, end, root, max);
		    }

		    //Solution2: O(n) time, O(n) space
		    public class Solution {
			    public boolean verifyPreorder(int[] preorder) {
			        int low = Integer.MIN_VALUE;
			        Stack<Integer> stack = new Stack<>();
			        for (int val : preorder) {
			            if (val < low) {
			                return false;
			            }
			            while (!stack.isEmpty() && val > stack.peek()) {
			                low = stack.pop();
			            }
			            stack.push(val);
			        }
			        return true;
			    }
			}

			//Solution3: prefer, O(n) time, O(1) space
			public class Solution {
			    public boolean verifyPreorder(int[] preorder) {
			        int low = Integer.MIN_VALUE;
			        int i = -1;
			        for (int val : preorder) {
			            if (val < low)
			                return false;
			            while (i >= 0 && val > preorder[i])
			                low = preorder[i--];
			            preorder[++i] = val;
			        }
			        return true;
			    }
			}

		1.2 Validate Binary Search Tree
			//Recursive
			public class Solution {
			    public boolean isValidBST(TreeNode root) {
			        if (root == null) {
			            return true;
			        }
			        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
			    }
			    
			    public boolean isValid(TreeNode root, long min, long max) {
			        if (root == null) {
			            return true;
			        }
			        if (min < root.val && max > root.val) {
			            return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
			        } else {
			            return false;
			        }

			    }
			}
			//Iterative
			public class Solution {
			    public boolean isValidBST(TreeNode root) {
			        if (root == null) {
			            return true;
			        }
			        Stack<TreeNode> stack = new Stack<>();
			        TreeNode pre = null;
			        TreeNode cur = root;
			        while (!stack.isEmpty() || cur != null) {
			            if (cur != null) {
			                stack.push(cur);
			                cur = cur.left;
			            } else {
			                cur = stack.pop();
			                if (pre != null && pre.val >= cur.val) {
			                    return false;
			                }
			                pre = cur;
			                cur = cur.right;
			            }
			        }
			        return true;
			    }
			}
		1.3 A Pair With Given Sum In A Balanced BST
			public class findTwoElementsEqualsSumInBST {
				public int[] findPair(TreeNode root, int target) {
					int[] pair = {-1, -1};
					Stack<TreeNode> leftStack = new Stack<>();
					Stack<TreeNode> rightStack = new Stack<>();
					boolean searchLeft = true;
					boolean searchRight = true;
					TreeNode leftNode = root;
					TreeNode rightNode = root;
					int leftVal = 0, rightVal = 0;
					
					while (true) {
						while (searchLeft) {
							if (leftNode != null) {
								leftStack.push(leftNode);
								leftNode = leftNode.left;
							} else {
								searchLeft = false;
								if (!leftStack.isEmpty()) {
									leftNode = leftStack.pop();
									leftVal = leftNode.val;
									leftNode = leftNode.right;
								}
							}
						}
						while (searchRight) {
							if (rightNode != null) {
								rightStack.push(rightNode);
								rightNode = rightNode.right;
							} else {
								searchRight = false;
								if (!rightStack.isEmpty()) {
									rightNode = rightStack.pop();
									rightVal = rightNode.val;
									rightNode = rightNode.left;
								}
							}
						}
						if (leftVal >= rightVal) {
							return pair;
						}
						if (leftVal + rightVal == target) {
							pair[0] = leftVal;
							pair[1] = rightVal;
							return pair;
						} else if (leftVal + rightVal > target) {
							searchRight = true;
						} else {
							searchLeft = true;
						}
					}
				}
			}





2. Array Problem
		2.1 Two Pointer
			2.1.1 Majority Element
				/*
					Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
					You may assume that the array is non-empty and the majority element always exist in the array.
				*/	
				public class Solution {
				    public int majorityElement(int[] nums) {
				        int majority = nums[0];
				        int count = 1;
				        for (int i = 1; i < nums.length; i++) {
				            if (count == 0) {
				                majority = nums[i];
				                count++;
				            } else if (majority == nums[i]) {
				                count++;
				            } else {
				                count--;
				            }
				        }
				        return majority;
				    }
				}
		2.2 Flip 0 Or 1
		/*
			You are given an array a of sizeN. The elements of the array area[0], a[1], ... a[N - 1], where each a is either 0 or 1. You can perform one transformation on the array: choose any two integers L,and R, and flip all the elements between (and including) the Lth and Rth bits. In other words, Land R represent the left-most and the right-most index demarcating the boundaries of the segment whose bits you will decided to flip. ('Flipping' a bit means, that a 0 is transformed to a 1 and a 1 is transformed to a 0.)

			What is the maximum number of '1'-bits (indicated by S) which you can obtain in the final bit-string?


			Input Format:The first line has a single integerNThe next N lines contains the Nelements in the array,a[0], a[1], ... a[N - 1], one per line.

			Note: Feel free to re-use the input-output code stubs provided.


			Output format:Return a single integer that denotes the maximum number of 1-bits which can be obtained in the final bit string


			Constraints:. 1 ≤ N ≤ 100,000d can be either 0 or 1. It cannot be any other integer.0 ≤ L ≤ R < N
			Sample Input:
			8
			10010010
			Sample Output:
			6
			Explanation:We can get a maximum of 6 ones in the given binary array by performing either of the following operations:Flip [1, 5] ⇒ 1 1 1 0 1 1 1 0or
			Flip [1, 7] ⇒ 1 1 1 0 1 1 0 1
		 */
		public class Flip0or1 {
			public static int flip(int[] nums) {
				if (nums == null || nums.length == 0) {
					return 0;
				}
				int curSum = 0;
				int oneCount = 0;
				int minSum = Integer.MAX_VALUE;
				
				for (int num : nums) {
					if (num == 0) {
						curSum--;
					} else {
						curSum++;
						oneCount++;//每次遇到1时curSum都会++
					}
					
					if (curSum > 0) {
						curSum = 0;
					} else if (curSum < minSum) {
						minSum = curSum;
					}
				}
				return oneCount - minSum;//等于原始1的总数 + 最多翻转区间中的0个数 - 最多翻转区间中的1个数）
			}
		}

		2.3 Rotate Array
			/*
		        Solution for 前K: reverse the array,k mean the mostleft k elements
		                  1. reverse 全部数
		                  2. reverse 前面k个数，
		                  3. reverse 后面len - k个数
		    */ 
			public class Solution {
			    public void rotate(int[] nums, int k) {
			        k = k % nums.length;
			        reverse(nums, 0, nums.length - 1);
			        reverse(nums, 0, k - 1);
			        reverse(nums, k, nums.length - 1);
			    }
			    
			    public void swap(int[] nums, int i, int j) {
			        int temp = nums[i];
			        nums[i] = nums[j];
			        nums[j] = temp;
			    }
			    
			    public void reverse(int[] nums, int i, int j) {
			        while (i < j) {
			            swap(nums, i++, j--);
			        }
			    }
			}
		2.4 Rotate Image
			//核心思想，先将所有的matrix[i][j] swap matrix[j][i]
			//其次从左至右，将 [i][j] 和 [i][n - j - 1]对称交换，奇数的中间那一列不需要swap
			public class Solution {
			    public void rotate(int[][] matrix) {
			        int m = matrix.length;
			        int n = matrix[0].length;
			        for (int i = 0; i < m; i++) {
			            for (int j = i + 1; j < n; j++) {
			                int temp = matrix[i][j];
			                matrix[i][j] = matrix[j][i];
			                matrix[j][i] = temp;
			            }
			        }
			        
			        for (int i = 0; i < m; i++) {
			            for (int j = 0; j < n/2; j++) {
			                int temp = matrix[i][j] ;
			                matrix[i][j] = matrix[i][n - j - 1];
			                matrix[i][n - j - 1] = temp;
			            }
			        }
			    }
			}
		2.5 Trapping Rain Water
			/*
				基本思路是这样的，用两个指针从两端往中间扫，在当前窗口下，如果哪一侧的高度是小的，那么从这里开始继续扫，
				如果比它还小的，肯定装水的瓶颈就是它了，可以把装水量加入结果，如果遇到比它大的，立即停止，重新判断左右窗口的大小情况，重复上面的步骤。
				这里能作为停下来判断的窗口，说明肯定比前面的大了，所以目前肯定装不了水（不然前面会直接扫过去）。
				这样当左右窗口相遇时，就可以结束了，因为每个元素的装水量都已经记录过了
			*/
			public int trap(List<Integer> height) {
			    if (height == null || height.size() == 0) {
			        return 0;
			    }
			    int left = 0;
			    int right = height.size() - 1;
			    int res = 0;
			    while (left < right) {
			        int min = Math.min(height.get(left), height.get(right));
			        if (height.get(left) == min) {
			            left++;
			            while (left < right && height.get(left) < min) {
			                res += min - height.get(left);
			                left++;
			            }
			        } else {
			            right--;
			            while (left < right && height.get(right) < min) {
			                res += min - height.get(right);
			                right--;
			            }
			        }
			    }
			    return res;
			}


3. Divide Conquer
		3.1 Majority Element
			public class Solution {
			    //Divide and Conquer
			    public int majorityElement(int[] num) {
			        return majorityHelper(num, 0, num.length - 1);
			    }
			    public int majorityHelper(int[] num, int start, int end) {
			        // int n = end - start + 1;
			        if (start == end) {
			            return num[start];
			        }
			        int mid = (start + end) / 2;
			        int leftMajority = majorityHelper(num, start, mid);
			        int rightMajority = majorityHelper(num, mid + 1, end);
			        
			        int leftCount = 0;
			        int rightCount = 0;
			        for (int i = start; i <= end; i++) {
			            if (num[i] == leftMajority) {
			                leftCount++;
			            }
			            if (num[i] == rightMajority) {
			                rightCount++;
			            }
			        }
			        
			        if (leftCount > (end - start + 1) / 2) {
			            return leftMajority;
			        }
			        if (rightCount > (end - start + 1) / 2) {
			            return rightMajority;
			        }
			        return 0;
			    }
			}
		3.2 Majority Element II
			public class Solution {
			    public List<Integer> majorityElement(int[] nums) {
			        List<Integer> res = new ArrayList<>();
			        if (nums == null || nums.length == 0) {
			            return res;
			        }
			        int count1 = 0, count2 = 0;
			        int candidate1 = 0, candidate2 = 0;
			        for (int val : nums) {
			            if (candidate1 == val) {
			                count1++;
			            } else if (candidate2 == val) {
			                count2++;
			            } else if (count1 == 0) {
			                candidate1 = val;
			                count1++;
			            } else if (count2 == 0) {
			                candidate2 = val;
			                count2++;
			            } else {
			                count1--;
			                count2--;
			            }
			        }
			        
			        if (candidate1 == candidate2) {
			            res.add(candidate1);
			            return res;
			        }
			        count1 = count2 = 0;
			        for (int val : nums) {
			            if (val == candidate1) {
			                count1++;
			            }
			            if (val == candidate2) {
			                count2++;
			            }
			        }
			        
			        if (count1 > nums.length / 3) {
			            res.add(candidate1);
			        }
			        if (count2 > nums.length / 3) {
			            res.add(candidate2);
			        }
			        return res;
			    }
			}

4. Backtracking
		4.1 Generate Parentheses
			//Solution1: recursive
			public class Solution {
			    public List<String> generateParenthesis(int n) {
			        List<String> res = new ArrayList<>();
			        if (n <= 0) {
			            return res;
			        }
			        helper(res, "", n, n);
			        return res;
			    }
			    public void helper(List<String> res, String item, int left, int right) {
			        if (left == 0 && right == 0) {
			            res.add(item);
			        }
			        if (left > 0) {
			            helper(res, item + "(", left - 1, right);
			        }
			        if (left < right) {
			            helper(res, item + ")", left, right - 1);
			        }
			    }
			//Solution2: Iteraive
			/*
		        My method is DP. First consider how to get the result f(n) from previous result f(0)...f(n-1). Actually, the result f(n) will be put an extra () pair to f(n-1). Let the "(" always at the first position, to produce a valid result, we can only put ")" in a way that there will be i pairs () inside the extra () and n - 1 - i pairs () outside the extra pair.

		        Let us consider an example to get clear view:

		        f(0): ""
		        f(1): "("f(0)")"
		        f(2): "("f(0)")"f(1), "("f(1)")"
		        f(3): "("f(0)")"f(2), "("f(1)")"f(1), "("f(2)")"
		        So f(n) = "("f(0)")"f(n-1) , "("f(1)")"f(n-2) "("f(2)")"f(n-3) ... "("f(i)")"f(n-1-i) ... "(f(n-1)")"
		    */
			public class Solution {
			    public List<String> generateParenthesis(int n) {
			        List<List<String>> lists = new ArrayList<>();
			        List<String> initList = new ArrayList<>();
			        initList.add("");
			        lists.add(initList);
			        
			        for (int i = 1; i <= n; i++) {
			            List<String> list = new ArrayList<>();
			            for (int j = 0; j < i; j++) {
			                for (String first : lists.get(j)) {
			                    for (String second : lists.get(i - j - 1)) {
			                        list.add("(" + first + ")" + second);
			                    }
			                }
			            }
			            lists.add(list);
			        }
			        return lists.get(lists.size() - 1);
			    }
			}
		4.2 Find All Combinations With Length N In A String
			//Solution1:
			public static List<String> permutation(String s, int n) {
				List<String> res = new ArrayList<>();
				if (s == null || s.length() == 0 || s.length() < n ) {
					return res;
				}

				helper(s, "", 0, n, res);
				return res;
			}
			private static void helper(String s, String item, int index, int n,
				List<String> res) {
				if (item.length() == n) {
					res.add(item);
					return;
				}
			
				for (int i = index; i < s.length(); i++) {
					if (i != index && s.charAt(i) ==  s.charAt(i - 1)) {
						continue;
					}
					helper(s, item + s.charAt(i), i + 1, n, res);
				}
			}

			//Solution2:
			public static List<String> permutation(String s, int n) {
				List<String> res = new ArrayList<>();
				if (s == null || s.length() == 0 || s.length() < n ) {
					return res;
				}
				boolean[] visited = new boolean[s.length()];
				char[] word = s.toCharArray();
				Arrays.sort(word);
				helper(word, n, new StringBuilder(), res, visited);
				return res;
			}
			private static void helper(char[] word, int n, StringBuilder item,
					List<String> res, boolean[] visited) {
				// TODO Auto-generated method stub
				if (item.length() == n) {
					res.add(item.toString());
					return;
				}
				for (int i = 0; i < word.length; i++) {
					if (visited[i] || i > 0 && word[i] == word[i - 1] && !visited[i - 1]) {
						continue;
					}
					item.append(word[i]);
					visited[i] = true;
					helper(word, n, item, res, visited);
					item.deleteCharAt(item.length() - 1);
					visited[i] = false;
				}
			}

5. Design
		5.1 Flatten 2D Vector
		/*
			Implement an iterator to flatten a 2d vector.
			For example,
			Given 2d vector =

			[
			  [1,2],
			  [3],
			  [4,5,6]
			]
			By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].
		*/
		public class Vector2D {
			Iterator<List<Integer>> listIter;
			Iterator<Integer> numIter;
			public Vector2D(List<List<Integer>> vec2d) {
				listIter = vec2d.iterator();
			}
			public int next() {
				hasNext();
				return numIter.next();
			}
			public boolean hasNext() {
				while ((numIter == null || !numIter.hasNext()) && listIter.hasNext()) {
					numIter = listIter.next().iterator();
				}
				return numIter != null && numIter.hasNext();

			}
		}

		5.2 Min Stack
			/*
				Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
				push(x) -- Push element x onto stack.
				pop() -- Removes the element on top of the stack.
				top() -- Get the top element.
				getMin() -- Retrieve the minimum element in the stack.
			*/
			//Solution1: one stack
			public class MinStack {
			    int min;
			    Stack<Integer> stack;
			    public MinStack () {
			        stack = new Stack<>();
			    }
			    public void push(int x) {
			        if (stack.isEmpty()) {
			            stack.push(0);
			            min = x;
			        } else {
			            stack.push(x - min);
			            if (x < min) {
			                min = x;
			            }
			        }
			    }
			    public void pop() {
			        if (stack.isEmpty()) {
			            return;
			        }
			        int pop = stack.pop();
			        if (pop < 0) {
			            min = min - pop;
			        }
			    }
			    public int top() {
			        int top = stack.peek();
			        if (top > 0) {
			            return top + min;
			        } else {
			            return min;
			        }
			    }
			    public int getMin() {
			        return min;
			    }
			}

			//Solution2: two stack
			class MinStack {
			    Stack<Integer> stack = new Stack<>();
			    Stack<Integer> minStack = new Stack<>();
			    public void push(int x) {
			        if (stack.isEmpty()) {
			            stack.push(x);
			            minStack.push(x);
			        } else {
			            stack.push(x);
			            if (minStack.peek() >= x) {
			                minStack.push(x);
			            }
			        }
			    }

			    public void pop() {
			        if (stack.isEmpty()) {
			            return;
			        }
			        int pop = stack.pop();
			        if (pop == minStack.peek()) {
			            minStack.pop();
			        }
			    }

			    public int top() {
			        return stack.peek();
			    }

			    public int getMin() {
			        return minStack.peek();
			    }
			}
			//Solution3: do not use api 
			public class MinStack {
			    private ListNode head = null;
			    private ListNode min = null;
			     
			    public void push(int x) {
			        ListNode newNode = new ListNode(x);
			         
			        if (head == null) {
			            head = newNode;
			        } else {
			            head.next = newNode;
			            newNode.prev = head;
			            head = newNode;
			        }
			         
			        // Update the min value
			        if (min == null || newNode.val <= min.val) {
			            min = newNode;
			        }
			    }
			     
			    public int pop() {
			        ListNode node = head;
			         
			        head = head.prev;
			        // Be careful only one node in the list
			        if (head != null) {
			            head.next = null;
			        }
			         
			        // Find out the new min
			        if (min == node) {
			            ListNode p = head;
			            min = head;
			            while (p != null) {
			                if (p.val < min.val) {
			                    min = p;
			                }
			                p = p.prev;
			            }
			        }
			         
			        return node.val;
			    }
			     
			    public int getMin() {
			        return min.val;
			    }
			}





6. BFS && DFS Path, Matrix Problem
		6.1 Shortest Distance From All Buildings
			/*
				Shortest Distance from All Buildings
				You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You are given a 2D grid of values 0, 1 or 2, where:

				Each 0 marks an empty land which you can pass by freely.
				Each 1 marks a building which you cannot pass through.
				Each 2 marks an obstacle which you cannot pass through.
				The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

				For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):

				1 - 0 - 2 - 0 - 1
				|   |   |   |   |
				0 - 0 - 0 - 0 - 0
				|   |   |   |   |
				0 - 0 - 1 - 0 - 0
				The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.

				Note:
				There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
			*/

			/*
				Solution: 1. 设两个数组 reach 用来记录这个坐标已经被多少个坐标值为1进行了多少次bfs
									  dist 用来记录这个坐标对已经遍历过的坐标值为1的点的距离和，因为bfs 所以一定是这个点到所有1点的最近距离

						  2. 对所有坐标值为1的点进行bfs，记录所有0点对这个点的距离.
						  3. 遍历所有坐标值为0的点的reach 和dist值，找reach等于所有1的数量，并且dist的值

			*/

			public class Solution {
			    public int shortestDistance(int[][] grid) {
			        if (grid == null || grid.length == 0) {
			            return 0;
			        }
			        final int[] shift = new int[]{0, 1, 0, -1, 0};
			        int m = grid.length;
			        int n = grid[0].length;
			        int[][] dist = new int[m][n];
			        int[][] reach = new int[m][n];
			        int buildNum = 0;//记录1的个数

			        for (int i = 0; i < m; i++) {
			            for (int j = 0; j < n; j++) {
			                if (grid[i][j] == 1) {
			                    buildNum++;//发现1 ，数量++
			                    Queue<int[]> queue = new LinkedList<>();
			                    queue.offer(new int[]{i, j});//从这个building开始做bfs
			                    boolean[][] visited = new boolean[m][n];//记录遍历过的点避免重复
			                    int level = 1; //building附近的点level为1，再外层再+1.
			                    while (!queue.isEmpty()) {
			                        int size = queue.size();
			                        //层序遍历
			                        for (int q = 0; q < size; q++) {
			                            int[] point = queue.poll();
			                            for (int k = 0; k < 4; k++) {
			                                int nextRow = point[0] + shift[k];
			                                int nextCol = point[1] + shift[k + 1];
			                                if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n && !visited[nextRow][nextCol] && grid[nextRow][nextCol] == 0) {
			                                    queue.offer(new int[]{nextRow, nextCol});
			                                    dist[nextRow][nextCol] += level;//累加对所有点的距离
			                                    reach[nextRow][nextCol]++;//grid[nextRow][nextCol] 这个点目前遇到多少1
			                                    visited[nextRow][nextCol] = true;
			                                }
			                            }
			                        }
			                        level++;
			                    }
			                }
			            }
			        }
			        int minDist = Integer.MAX_VALUE;
			        for (int i = 0; i < m; i++) {
			            for (int j = 0; j < n; j++) {
			                if (grid[i][j] == 0 && reach[i][j] == buildNum) {
			                    minDist = Math.min(minDist, dist[i][j]);
			                }
			            }
			        }
			        return minDist == Integer.MAX_VALUE ? -1 : minDist;
			    }
			}

		6.2 Number Of Islands
			/*
				Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
				An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
				You may assume all four edges of the grid are all surrounded by water.
				Example 1:
				11110
				11010
				11000
				00000
				Answer: 1

				Example 2:
				11000
				11000
				00100
				00011
				Answer: 3
			*/
			//Solution1:
			public class Solution {
			    public int numIslands(char[][] grid) {
			        if (grid == null || grid.length == 0) {
			            return 0;
			        }
			        int m = grid.length;
			        int n = grid[0].length;
			        int count = 0;
			        boolean[][] visited = new boolean[m][n];
			        final int[] shift = new int[] {0, 1, 0, -1, 0};
			        for (int i = 0; i < m; i++) {
			            for (int j = 0; j < n; j++) {
			                if (grid[i][j] == '1') {
			                    count++;
			                    Queue<int[]> queue = new LinkedList<>();
			                    queue.offer(new int[]{i, j});
			                    while (!queue.isEmpty()) {
			                        int size = queue.size();
			                        for (int q = 0; q < size; q++) {
			                            int[] point = queue.poll();
			                            for (int k = 0; k < 4; k++) {
			                                int nextRow = point[0] + shift[k];
			                                int nextCol = point[1] + shift[k + 1];
			                                if (nextRow >= 0 && nextCol >= 0 && nextRow < m && nextCol < n && grid[nextRow][nextCol] == '1' && !visited[nextRow][nextCol]) {
			                                    queue.offer(new int[]{nextRow, nextCol});
			                                    grid[nextRow][nextCol] = '2';
			                                    visited[nextRow][nextCol] = true;
			                                }
			                            }
			                        }
			                    }
			                }
			            }
			        }
			        return count;
			    }
			}
			//Solution2: dfs
			public class Solution {
				 public int numIslands(char[][] grid) {
			        if (grid == null || grid.length == 0) {
			            return 0;
			        }
			        int m = grid.length;
			        int n = grid[0].length;
			        int count = 0;
			        boolean[][] visited = new boolean[m][n];
			        for (int i = 0; i < m; i++) {
			            for (int j = 0; j < n; j++) {
			                if (grid[i][j] == '1') {
			                    dfs(grid, visited, i, j);
			                    count++;
			                }
			            }
			        }
			        return count;
			    }	    
			    public void dfs(char[][] grid, boolean[][] visited, int row, int col) {
			        if (row < 0 || col < 0 || row > grid.length - 1 || col > grid[0].length - 1 || visited[row][col] || grid[row][col] != '1') {
			            return ;
			        }
			        grid[row][col] = '2';
			        visited[row][col] = true;
			        dfs(grid, visited, row + 1, col);
			        dfs(grid, visited, row - 1, col);
			        dfs(grid, visited, row, col + 1);
			        dfs(grid, visited, row, col - 1);
			    }				
			}
		6.4 Find Islands And Lakes
		/*
			湖的定义就是 group of 0s entirely surrounded by a single island。也就是说两个不同island包围的0不算一个湖，
			比如下面这个只有1个湖，就是两个连续0哪个是湖，边上那个被4个1包围的0不算湖

			Example1: // 1 1 1 0 1 1 
					  // 1 0 0 1 0 1
					  // 1 1 1 1 1 0 

			Example2:
					  // 1 1 1 1 1 0
					  // 1 0 1 0 0 1       i)  islands =>  2,  1是岛
					  // 1 0 1 0 0 1       ii) lakes =>  1，   0是湖
					  // 1 1 0 1 1 1

			// write a library/class that provides two functions / methods
			// i) count the number of 'islands' that the matrix has. 
			// ii) count the number of 'lakes' that the matrix has i.e. connected clump of zeros that is entirely surrounded by a single island.
		*/
		public class Solution {
			public static int findIslands(int[][] grid) {
				if (grid == null || grid.length == 0) {
					return 0;
				}
				int m = grid.length;
				int n = grid[0].length;
				int count = 0;
				boolean[][] visited = new boolean[m][n];
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						if (grid[i][j] == 1) {
							count++;
							dfs1(grid, visited, i, j, count + 1);
						}
					}
				}
				return count;
			}	
			public static int findLakes(int[][] grid) {
				findIslands(grid);
				int m = grid.length;
				int n = grid[0].length;
				int count = 0;
				for (int i = 1; i < m - 1; i++) {
					for (int j = 1; j < n - 1; j++) {
						if (grid[i][j] == 0) {
							int[] key = new int[1];
							key[0] = -1;
							if (dfs2(grid, i, j, key)) {
								count++;
							}
						}
					}
				}
				
				for (int i = 1; i < m - 1; i++) {
					for (int j = 1; j < n - 1; j++) {
						if (grid[i][j] == -1) {
							grid[i][j] = 0;
						}
					}
				}
				reset(grid);
				
				return count;	
			}
			
			public static void reset(int[][] grid) {
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						grid[i][j] = grid[i][j] == 0 ? 0 : 1;
					}
				}
			}
			
			public static boolean dfs2(int[][] grid, int i, int j, int[] key) {
				//假如是大于0，就是岛的点，
				if (grid[i][j] > 0) {
					//第一次碰上岛的点时
					if (key[0] == -1) {
						key[0] = grid[i][j];//取得岛的编号并返回true
						return true;
					} else if (key[0] == grid[i][j]) {
						//如果岛的编号和记录的编号相同则返回true
						return true;
					} else {
						//编号不同，返回false
						return false;
					}
				}
				
				if (grid[i][j] == -1) {
					//-1就是遇到邻接的湖
					return true;
				}
				grid[i][j] = -1;//每次进来就将0赋值为-1，
				if (i == 0 || j == 0 || i == grid.length - 1 || j == grid[0].length - 1) {//corn case
					return false;
				}
				return dfs2(grid, i - 1, j, key) && dfs2(grid, i + 1, j, key) && dfs2(grid, i, j + 1, key) &&  dfs2(grid, i, j - 1, key);
			}
			
			public static void dfs1(int[][] grid, boolean[][] visited, int i, int j, int index) {
				if (i < 0 || j < 0 || i > grid.length - 1 || j > grid[0].length - 1 || visited[i][j] || grid[i][j] != 1) {
					return;
				}
				grid[i][j] = index;
				visited[i][j] = true;
				dfs1(grid, visited, i + 1, j, index);
				dfs1(grid, visited, i - 1, j, index);
				dfs1(grid, visited, i, j + 1, index);
				dfs1(grid, visited, i, j - 1, index);
			}


7. Slide Window
		7.1 Sliding Window Maximum
		/*
			Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

			For example,
			Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

			Window position                Max
			---------------               -----
			[1  3  -1] -3  5  3  6  7       3
			 1 [3  -1  -3] 5  3  6  7       3
			 1  3 [-1  -3  5] 3  6  7       5
			 1  3  -1 [-3  5  3] 6  7       5
			 1  3  -1  -3 [5  3  6] 7       6
			 1  3  -1  -3  5 [3  6  7]      7
			Therefore, return the max sliding window as [3,3,5,5,6,7].

			Note: 
			You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

			Follow up:
			Could you solve it in linear time?
		*/
		//Solution1: O(n * logk), k is the size of window
		public class Solution {
		    public int[] maxSlidingWindow(int[] nums, int k) {
		        int[] res = new int[nums.length - k + 1];
		        if (nums == null || nums.length == 0) {
		            return new int[]{};
		        }
		        PriorityQueue<Integer> pq = new PriorityQueue<>(k, new Comparator<Integer>(){
		            @Override
		            public int compare(Integer i1, Integer i2) {
		                return i2 - i1;
		            }
		        });	        
		        for (int i = 0; i < k; i++) {
		            pq.offer(nums[i]);
		        }
		        res[0] = pq.peek();
		        for (int i = k; i < nums.length; i++) {
		            pq.remove(nums[i - k]);
		            pq.offer(nums[i]);
		            res[i - k + 1] = pq.peek();
		        }
		        return res;		        
		    }
		}
		//Solution2: O(n)
		/*
			We scan the array from 0 to n-1, keep "promising" elements in the deque. The algorithm is amortized O(n) as each element is put and polled once.

			At each i, we keep "promising" elements, which are potentially max number in window [i-(k-1),i] or any subsequent window. This means

			1. If an element in the deque and it is out of i-(k-1), we discard them. We just need to poll from the head, 
			   as we are using a deque and elements are ordered as the sequence in the array

			2. Now only those elements within [i-(k-1),i] are in the deque. We then discard elements smaller than a[i] from the tail. 
			   This is because if a[x] <a[i] and x<i, then a[x] has no chance to be the "max" in [i-(k-1),i], or any other subsequent window: a[i] would always be a better candidate.

			3. As a result elements in the deque are ordered in both sequence in array and their value. 
			   At each step the head of the deque is the max element in [i-(k-1),i]


		*/
		public class Solution {
		   	public int[] maxSlidingWindow(int[] nums, int k) {
		   		int n = nums.length;
		   		if (nums == null || k <= 0) {
		   			int[] res = new int[0];
		   			return res;
		   		}
		   		int[] res = new int[n - k + 1];
		   		Deque<Integer> queue = new ArrayDeque<>();
		   		for (int i = 0; i < nums.length; i++) {
		   			if (!queue.isEmpty() && queue.peek() < i - k + 1) {
		   				queue.poll();
		   			}

		   			while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
		   				queue.pollLast();
		   			}

		   			queue.offer(i);
		   			if (i >= k - 1) {
		   				res[i - k + 1] = nums[queue.peek()];
		   			}
		   		}
		   		return res;
		   	}
		}


8. Graph
		8.1 Graph Valid Tree
			/*
				Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
				For example:

				Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
				Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

				Hint:
					Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
					According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
					Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
			*/
			//Solution1: BFS
				public class Solution {
				    public boolean validTree(int n, int[][] edges) {
				        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
				        for (int i = 0; i < n; i++) {
				            graph.put(i, new HashSet<Integer>());
				        }
				        
				        for (int[] edge : edges) {
				            graph.get(edge[0]).add(edge[1]);
				            graph.get(edge[1]).add(edge[0]);
				        }
				        
				        Queue<Integer> queue = new LinkedList<>();
				        boolean[] visited = new boolean[n];
				        queue.offer(0);
				        
				        while (!queue.isEmpty()) {
				            int node = queue.poll();
				            if (visited[node] == true) {
				                return false;
				            }
				            visited[node] = true;
				            for (int neighbor : graph.get(node)) {
				                graph.get(neighbor).remove(node);
				                queue.offer(neighbor);
				            }
				        }
				        
				        for (boolean visit : visited) {
				            if (visit == false) {
				                return false;
				            }
				        }
				        return true;
				    }
				}
			//Solution2: Union-Find
				/*
					http://www.geeksforgeeks.org/union-find/
					Find: Determine which subset a particular element is in. This can be used for determining if two elements are in the same subset.
					Union: Join two subsets into a single subset.

					Solution1 : Union Find, Union the point set
					1)	Initially, all slots of parent array are initialized to -1 (means there is only one item in every subset).
						0   1   2
						-1 -1  -1
					2)  Now process all edges one by one.
					    Edge 0-1: Find the subsets in which vertices 0 and 1 are. Since they are in different subsets, we take the union of them. 
					    For taking the union, either make node 0 as parent of node 1 or vice-versa.
					    0   1   2    <----- 1 is made parent of 0 (1 is now representative of subset {0, 1})
						1  -1  -1
						Edge 1-2: 1 is in subset 1 and 2 is in subset 2. So, take union.
						0   1   2    <----- 2 is made parent of 1 (2 is now representative of subset {0, 1, 2})
						1   2  -1
					3)  Edge 0-2: 0 is in subset 2 and 2 is also in subset 2. Hence, including this edge forms a cycle.
						How subset of 0 is same as 2?
						0->1->2 // 1 is parent of 0 and 2 is parent of 1					
					// A utility function to find the subset of an element i
					int find(int parent[], int i){
					    if (parent[i] == -1)
					        return i;
					    return find(parent, parent[i]);
					} 
					// A utility function to do union of two subsets 
					void Union(int parent[], int x, int y){
						int xset = find(parent, x);
					    int yset = find(parent, y);
					    parent[xset] = yset;
					}
				*/
			    public class Solution {
			    	public boolean validTree(int n, int[][] edges) {
				        int[] parent = new int[n];
				        Arrays.fill(parent, -1);
				        for (int[] edge : edges) {
				            int x = find(parent, edge[0]);
				            int y = find(parent, edge[1]);
				        
				            if (x == y) {
				                return false;
				            }
				            parent[x] = y;
				        }
				        return edges.length == n - 1;
				    }
				    
				    public int find(int[] parent, int i) {
				        if (parent[i] == -1) {
				            return i;
				        } else {
				            return find(parent, parent[i]);
				        }
				    }
			    }
		8.2 Course Schedule I
			/*
				There are a total of n courses you have to take, labeled from 0 to n - 1.
				Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
				Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

				For example:
				2, [[1,0]]
				There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

				2, [[1,0],[0,1]]
				There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
				Note:
				The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
			*/
			//bfs + topological sort, 
			/*
				0. Construct the graph, the key is start point, the value if the end point
				1. The indegree array is key point,
				2. Let the node which the indegree equals zero offer to the queue,
				3. and substract 1 to the neighbour indegree of the node, if after remove the indegree of neighbour equals 0, we offer this node to the queue.
			*/
			public class Solution {
			    public boolean canFinish(int numCourses, int[][] prerequisites) {
			        HashMap<Integer, List<Integer>> graph = new HashMap<>();
			        int[] indegree = new int[numCourses];
			        int count = 0;
			        for (int i = 0; i < numCourses; i++) {
			            graph.put(i, new ArrayList<Integer>());
			        }
			        for (int[] course : prerequisites) {
			            graph.get(course[1]).add(course[0]);
			            indegree[course[0]]++;
			        }
			        Queue<Integer> queue = new LinkedList<>();
			        for (int i = 0; i < indegree.length; i++) {
			            if (indegree[i] == 0) {
			                queue.offer(i);
			            }
			        }
			        while (!queue.isEmpty()) {
			            int preCourse = queue.poll();
			            for (int curCourse : graph.get(preCourse)) {
			                if (--indegree[curCourse] == 0) {
			                    queue.offer(curCourse);
			                }
			            }
			            count++;
			        }
			        return count == numCourses;
			    }


			//Detect the Cycle by using DFS
			public class Solution {
			    public boolean canFinish(int numCourses, int[][] prerequisites) {
			        int[][] graph = new int[numCourses][numCourses];
			        for (int i = 0; i < prerequisites.length; i++) {
			            graph[prerequisites[i][1]][prerequisites[i][0]] = 1;
			        }
			        
			        //Visited array has 3 different number
			        //0: not visited
			        //1: visiting
			        //2: visited
			        int[] visited = new int[numCourses];
			        for (int node = 0; node < numCourses; node++) {
			            if (visited[node] == 0) {
			                if (dfs(node, graph, visited)) {
			                    continue;
			                } else {
			                    return false;
			                }
			            }
			        }
			        return true;
			    }
			    public boolean dfs(int node, int[][] graph, int[] visited) {
			        visited[node] = 1;
			        for (int i = 0; i < graph.length; i++) {
			            if (grap[node][i] == 1) {
			                if (visited[i] == 1) {
			                    return false;
			                }
			                if (visited[i] == 0 && !dfs(i, graph, visited)) {
			                    return false;
			                }
			            }
			        }
			        visited[node] = 2;
			        return true;
			    }
			}
		8.3 Course Schedule II
			//BFS
			public class Solution {
			    public int[] findOrder(int numCourses, int[][] prerequisites) {
			        int[] res = new int[numCourses];
			        int count = 0;
			        HashMap<Integer, List<Integer>> graph = new HashMap<>();
			        int[] indegree = new int[numCourses];
			        for (int i = 0; i < numCourses; i++) {
			            graph.put(i, new ArrayList<Integer>());
			        }
			        for (int[] course : prerequisites) {
			            graph.get(course[1]).add(course[0]);
			            indegree[course[0]]++;
			        }
			        Queue<Integer> queue = new LinkedList<>();
			        for (int i = 0; i < indegree.length; i++) {
			            if (indegree[i] == 0) {
			                queue.offer(i);
			            }
			        }
			        while (!queue.isEmpty()) {
			            int course = queue.poll();
			            res[count++] = course;
			            for (int i : graph.get(course)) {
			                if (--indegree[i] == 0) {
			                    queue.offer(i);
			                }
			            }
			        }
			        return count == numCourses ? res : new int[0];
			    }
			}

			//DFS
			public class Solution {
			    public int[] findOrder(int numCourses, int[][] prerequisites) {
			        List<List<Integer>> adj = new ArrayList<>(numCourses);
			        for (int i = 0; i < numCourses; i++) adj.add(i, new ArrayList<>());
			        for (int i = 0; i < prerequisites.length; i++) adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
			        boolean[] visited = new boolean[numCourses];
			        Stack<Integer> stack = new Stack<>();
			        for (int i = 0; i < numCourses; i++) {
			            if (!topologicalSort(adj, i, stack, visited, new boolean[numCourses])) return new int[0];
			        }
			        int i = 0;
			        int[] result = new int[numCourses];
			        while (!stack.isEmpty()) {
			            result[i++] = stack.pop();
			        }
			        return result;
			    }

			    private boolean topologicalSort(List<List<Integer>> adj, int v, Stack<Integer> stack, boolean[] visited, boolean[] isLoop) {
			        if (visited[v]) return true;
			        if (isLoop[v]) return false;
			        isLoop[v] = true;
			        for (Integer u : adj.get(v)) {
			            if (!topologicalSort(adj, u, stack, visited, isLoop)) return false;
			        }
			        visited[v] = true;
			        stack.push(v);
			        return true;
			    }
			}

9. String
		9.1 Valid Palindrome
			/*
				Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
				For example,
				"A man, a plan, a canal: Panama" is a palindrome.
				"race a car" is not a palindrome.

				Note:
				Have you consider that the string might be empty? This is a good question to ask during an interview.

				For the purpose of this problem, we define empty string as valid palindrome.
			*/
				//Solution1
				public class Solution {
				    public boolean isPalindrome(String s) {
				        int start = 0;
				        int end = s.length() - 1;
				        while (start < end) {
				            if(isValid(s.charAt(start)) && isValid(s.charAt(end))) {
				                if (isSame(s.charAt(start), s.charAt(end))) {
				                    start++;
				                    end--;
				                    continue;
				                } else {
				                    return false;
				                }
				            } 
				            if (!isValid(s.charAt(start))) {
				                start++;
				            }
				            if (!isValid(s.charAt(end))) {
				                end--;
				            }
				        }
				        return true;
				    }
				    public boolean isValid(char c) {
				        if (c != ' ' && ((c <= 'z' && c >= 'a') || (c <= '9' && c >= '0') || (c <= 'Z' && c >= 'A'))) {
				            return true;
				        }
				        return false;
				    }
				    public boolean isSame(char c1, char c2) {
				        if (c1 >= 'A' && c1 <= 'Z') {
				            c1 = (char) (c1 - 'A' + 'a');
				        }
				        if (c2 >= 'A' && c2 <= 'Z') {
				            c2 = (char) (c2 - 'A' + 'a');
				        }
				        return c1 == c2;
				    }
				}

				//Solution2
				public class Solution {
				    public boolean isPalindrome(String s) {
				        if (s.isEmpty()) {
				            return true;
				        }
				        int start = 0;
				        int end = s.length() - 1;
				        char c1, c2;
				        while (start <= end) {
				            c1 = s.charAt(start);
				            c2 = s.charAt(end);
				            if (!Character.isLetterOrDigit(c1)) {
				                start++;
				            } else if (!Character.isLetterOrDigit(c2)) {
				                end--;
				            } else {
				                if (Character.toLowerCase(c1) != Character.toLowerCase(c2)) {
				                    return false;
				                }
				                start++;
				                end--;
				            }
				        }
				        return true;
				    }
				}
		9.2 Random Shuffle String Except First And Last
			public class shuffleString {
				public static String shuffle(String s) {
					String[] words = s.split(" ");
					StringBuilder res = new StringBuilder();
					for (int i = 0; i < words.length; i++) {
						res.append(scramble(words[i]) + " ");
					}
					return res.toString();
				}
				public static String scramble(char first, char last, String word) {
					String res = "" + first;
					while (word.length() != 0) {
						int index = (int)Math.floor(Math.random() * word.length());
						char c = word.charAt(index);
						word = word.substring(0, index) + word.substring(index + 1);
						res += c;
					}
					return res + last;
				}
				public static String scramble(String word) {
					if (word.length() < 3) {
						return word;
					}
					String middle = word.substring(1,  word.length() - 1);
					return scramble(word.charAt(0), word.charAt(word.length() - 1), middle);
				}
			}


10. Math
		10.1 Excel Sheet Column Title , Number And Alphabat Convert Problem
			public class Solution {
			    public String convertToTitle(int n) {
			        StringBuilder sb = new StringBuilder();
			        while (n > 0) {
			            n--;
			            char c = (char)(n % 26 + 'A');
			            sb.insert(0, c);
			            n = n / 26;
			        }
			        return sb.toString();
			    }
			}
		10.1.2 Excel Sheet Column Number
			/*
			   A -> 1
			   B -> 2
			   C -> 3
			   ...
			   Z -> 26
			   AA -> 27
			   AB -> 28 
			 */
			public class Solution {
			    public int titleToNumber(String s) {
			        int res = 0;
			        for (int i = 0; i < s.length(); i++) {
			            res = res * 26 + (s.charAt(i) - 'A' + 1);
			        }
			        return res;
			    }
			}
		10.2 Sqrt
			public class Solution {
				public int sqrt(int x) {
					int start = 0;
					int end = x;
					while (start <= end) {
						long mid = (long)(low + high) / 2;
						if (mid * mid > x) {
							end = (long)mid - 1;
						} else if (mid * mid < x) {
							start = (long)mid + 1;
						} else {
							return (int)mid;
						}
					}
					return end
				}
			}
		10.3 Multiply Strings
			public class Solution {
			    public String multiply(String num1, String num2) {
			        int m = num1.length();
			        int n = num2.length();
			        int[] products = new int[m + n];
			        for (int i = m - 1; i >= 0; i--) {
			            for (int j = n - 1; j >= 0; j--) {
			                int a = num1.charAt(i) - '0';
			                int b = num2.charAt(j) - '0';
			                products[i + j + 1] += a * b;
			            }
			        }
			        StringBuilder sb = new StringBuilder();
			        int carry = 0;
			        for (int i = products.length - 1; i >= 0; i--) {
			            int temp = products[i];
			            products[i] = (carry + products[i]) % 10;
			            carry = (temp + carry) / 10;
			        }
			        for (int digit : products) {
			            sb.append(digit);
			        }
			        while (sb.length() != 0 && sb.charAt(0) == '0') {
			            sb.deleteCharAt(0);
			        }
			        return sb.length() == 0 ? "0" : sb.toString();
			    }
			}
		10.4 Sparse Matrix Multiplication
			/*
				Given two sparse matrices A and B, return the result of AB.

				You may assume that A's column number is equal to B's row number.

				Example:

				A = [
				  [ 1, 0, 0],
				  [-1, 0, 3]
				]

				B = [
				  [ 7, 0, 0 ],
				  [ 0, 0, 0 ],
				  [ 0, 0, 1 ]
				]


				     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
				AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
				                  | 0 0 1 |
			 */
			public class Solution {
			    public int[][] multiply(int[][] A, int[][] B) {
			        int m = A.length;
			        int n = A[0].length;
			        int nB = B[0].length;
			        int[][] C = new int[m][nB];
			        for (int i = 0; i < m; i++) {
			            for (int k = 0; k < n; k++) {
			                if (A[i][k] != 0) {
			                    for (int j = 0; j < nB; j++) {
			                        C[i][j] += A[i][k] * B[k][j];
			                        
			                    }
			                }
			            }
			        }
			        return C;
			    }
			}

12. Binary Search
		12.1 Median Of Two Sorted Arrays
			public class Solution {
			    public double findMedianSortedArrays(int A[], int B[]) {
					int m = A.length;
					int n = B.length;
					int total = m + n;
					if (total % 2 != 0) {
					    return findKth(A, 0, m - 1, B, 0, n - 1, total / 2 + 1);
					} else {
					    double x = findKth(A, 0, m - 1, B, 0, n - 1, total / 2);
					    double y = findKth(A, 0, m - 1, B, 0, n - 1, total / 2 + 1);
					    return (double) (x + y) / 2;
					}
				}
				public int findKth(int[] A, int aStart, int aEnd, int[] B, int bStart, int bEnd, int k) {
				    int m = aEnd - aStart + 1;
				    int n = bEnd - bStart + 1;
				    if (m > n) {
				        return findKth(B, bStart, bEnd, A, aStart, aEnd, k);
				    }
				    if (m == 0) {
				        return B[k - 1];
				    }
				    if (k == 1) {
				        return Math.min(A[aStart], B[bStart]);
				    }
				    int partA = Math.min(k / 2, m);
				    int partB = k - partA;
				    if (A[aStart + partA - 1] > B[bStart + partB - 1]) {
				        return findKth(A, aStart, aEnd, B, bStart + partB, bEnd, k - partB);
				    } else if (A[aStart + partA - 1] < B[bStart + partB - 1]) {
				        return findKth(A, aStart + partA, aEnd, B, bStart, bEnd, k - partA);
				    } else {
				        return A[aStart + partA - 1];
				    }
				}
			}
		12.2 Search For The Range
			public class Solution {
			    public int[] searchRange(int[] nums, int target) {
			        if (nums == null || nums.length == 0) {
			            return new int[]{};
			        }
			        int[] res = new int[2];
			        int start = 0;
			        int end = nums.length - 1;
			        
			        //Find the left bound
			        while (start + 1 < end) {
			            int mid = start + (end - start) / 2;
			            if (nums[mid] > target) {
			                end = mid;
			            } else if (nums[mid] < target) {
			                start = mid;
			            } else {
			                end = mid;
			            }
			        }
			        
			        if (nums[start] == target) {
			            res[0] = start;
			        } else if (nums[end] == target) {
			            res[0] = end;
			        } else {
			            res[0] = -1;
			        }
			        start = 0;
			        end = nums.length - 1;
			        //Find the right bound
			        while (start + 1 < end) {
			            int mid = start + (end - start) / 2;
			            if (nums[mid] > target) {
			                end = mid;
			            } else if (nums[mid] < target) {
			                start = mid;
			            } else {
			                start = mid;
			            }
			        }
			        
			        if (nums[end] == target) {
			            res[1] = end;
			        } else if (nums[start] == target) {
			            res[1] = start;
			        } else {
			            res[1] = -1;
			        }
			        
			        return res;
			    }
			}

13. Two Pointers
		13.1 Trapping Rain Water
			/*
			    1. 两个指针从两端往中间扫， 在当前窗口中，如果哪一侧高度小，则从该侧开始扫描
			    2. 如果在扫描的过程中发现有比它还小的，就可以把leftHeight - curHeight 加入结果，为什么呢？因为另外一边的高度肯定大于左边开始的高度，
			       所以这个装水量一定会满足
			    3. 当左右窗口相遇结束
			*/
			public class Solution {
			    public int trap(int[] height) {
			        if (height == null || height.length == 0) {
			            return 0;
			        }
			        int left = 0;
			        int right = height.length - 1;
			        int res = 0;
			        
			        while (left < right) {
			            int minHeight = Math.min(height[left], height[right]);
			            if (height[left] == minHeight) {
			                left++;
			                while (left < right && height[left] < minHeight) {
			                    res += minHeight - height[left];
			                    left++;
			                }
			            } else {
			                right--;
			                while (left < right && height[right] < minHeight) {
			                    res += minHeight - height[right];
			                    right--;
			                }
			            }
			        }
			        return res;
			    }
			}
		13.2 3 Sum Smaller
			public class Solution {
			    public int threeSumSmaller(int[] nums, int target) {
			        if (nums == null || nums.length == 0) {
			            return 0;
			        }
			        Arrays.sort(nums);
			        int len = nums.length;
			        int count = 0;
			        for (int left = 0; left < len - 2; left++) {
			            int mid = left + 1;
			            int right = len - 1;
			            while (mid < right) {
			                int sum = nums[left] + nums[mid] + nums[right];
			                if (sum < target) {
			                    count += right - mid;
			                    mid++;
			                } else {
			                    right--;
			                }
			            }
			        }
			        return count;
			    }
			}

14. HashTable	
		14.1 Count Same Prefixes And Suffixes
			/*
				arr = [1,2, 3, 1, 2]
				Prefixes of the array: [1], [1, 2], [1,2, 3], [1,2,3,1], [1,2,3,1,2]
				Suffixes of the array: [2], [1, 2], [3,1,2], [2,3,1,2], [1,2,3,1,2]

				prefix [1,2, 3, 1] == {1, 2, 3}
				suffix [1, 2, 3, 1, 2] == {1,2,3}

				[1, 2, 3, 1] == [1, 2, 3, 1, 2]

				Q.How many prefix-suffix pairs (P, S) are there such that set(P) == set(S)?
			*/
			public class Solution {
				public static int findSamePrefixAndSuffix(int[] nums) {
					HashMap<Set<Integer>, Integer> map1 = new HashMap<>();
					HashMap<Set<Integer>, Integer> map2 = new HashMap<>();
					int count = 0;
					for (int i = 1; i < nums.length; i++) {
						Set<Integer> pre = generatePrefix(nums, i);
						Set<Integer> suf = generateSuffix(nums, i);
						if (!map1.containsKey(pre)) {
							map1.put(pre, 1);
						} else {
							map1.put(pre, map1.get(pre) + 1);
						}
						
						if (!map2.containsKey(suf)) {
							map2.put(suf, 1);
						} else {
							map2.put(suf, map2.get(suf) + 1);
						}
					}
					
					System.out.println(map1);
					System.out.println(map2);
					for (Set<Integer> preSet : map1.keySet()) {
						if (map2.containsKey(preSet)) {
							count += map1.get(preSet) * map2.get(preSet);
						}
					}
					return count;
				}
				
				public static Set<Integer> generatePrefix(int[] prefixes, int k) {
					Set<Integer> res = new TreeSet<>();
					for (int i = 0; i < k; i++) {
						res.add(prefixes[i]);
					}
					return res;
				}
				
				public static Set<Integer> generateSuffix(int[] suffixes, int k) {
					Set<Integer> res = new TreeSet<>();
					int len = suffixes.length;
					for (int i = len - 1; i >= len - k; i--) {
						res.add(suffixes[i]);
					}
					return res;
				}
			}

		14.2 TWo Differences
		/*
			(2)Given N unique positive integers, we want to count the total pairs of numbers whose difference is K. The solution should minimize computational time complexity to the best of your ability
			Input Format:
			1st line contains N and K, which are integers separated by a space.. 2nd line contains N integers that form the set.
			Constraints 0 ≤ N ≤ 105,
			         All N numbers are distinct and can be represented by 32 bit signed integer. 0 <K <= 109
			Output Format:
			One integer, the number of pairs of numbers that have differenceK.. Sample Input #1:
			5215342
			Sample Output #1: 3
			Explanation:
			The possible pairs are (5,3), (4,2) and (3,1).
			Sample Input #2:
			10 1363374326 364147530 61825163 1073065718 1281246024 1399469912 428047635 491595254 879792181 1069262793
			Sample Output #2:
			0
			Explanation:
			There are no pairs with a difference of 1.
		 */
			//Solution1: just return count
			public class Solution {
				public static int findDiff(int[] nums, int k) {
					HashMap<Integer, Integer> map = new HashMap<>();
					int count = 0;
					for (int num : nums) {
						if (!map.containsKey(num)) {
							map.put(num, 1);
						} else {
							map.put(num, map.get(num) + 1);
						}
						if (map.containsKey(num - k) && map.get(num) <= 1) {
							count++;
						}
						if (map.containsKey(num + k) && map.get(num) <= 1) {
							count++;
						}
					}
					return count;
				}
			}
			//Solution2: return all pairs
			public class Solution{
				public static HashMap findDiffWithIndex(int[] nums, int k) {
					HashMap<Integer, Integer> map = new HashMap<>();
					HashSet<Integer> set = new HashSet<>();
					int count = 0;
					HashMap<Integer, Integer> res = new HashMap<>();
					for (int i = 0; i < nums.length; i++) {
						if (!map.containsKey(nums[i])) {
							map.put(nums[i], i);
						} 
						if (map.containsKey(nums[i] - k) && !set.contains(nums[i])) {
							res.put(map.get(nums[i] - k), i);
						}
						if (map.containsKey(nums[i] + k) && !set.contains(nums[i])) {
							res.put(map.get(nums[i] + k), i);
						}
						set.add(nums[i]);
					}
					return res;
				}
			}
15. Trie
		15.1 Implement Trie
			class TrieNode {
			    // Initialize your data structure here.
			    TrieNode[] children;
			    String word;
			    public TrieNode() {
			        children = new TrieNode[26];
			        word = "";
			    }
			}

			public class Trie {
			    private TrieNode root;
			    public Trie() {
			        root = new TrieNode();
			    }

			    // Inserts a word into the trie.
			    public void insert(String word) {
			        TrieNode node = root;
			        for (int i = 0; i < word.length(); i++) {
			            char c = word.charAt(i);
			            if (node.children[c - 'a'] == null) {
			                node.children[c - 'a'] = new TrieNode();
			            }
			            node = node.children[c - 'a'];
			        }
			        node.word = word;
			    }

			    // Returns if the word is in the trie.
			    public boolean search(String word) {
			        TrieNode node = root;
			        for (int i = 0; i < word.length(); i++) {
			            char c = word.charAt(i);
			            if (node.children[c - 'a'] != null) {
			                node = node.children[c - 'a'];
			            } else {
			                return false;
			            }
			        }
			        return node.word.equals(word);
			    }

			    // Returns if there is any word in the trie
			    // that starts with the given prefix.
			    public boolean startsWith(String prefix) {
			        TrieNode node = root;
			        for (int i = 0; i < prefix.length(); i++) {
			            char c = prefix.charAt(i);
			            if (node.children[c - 'a'] != null) {
			                node = node.children[c - 'a'];
			            } else {
			                return false;
			            }
			        }
			        return true;
			    }
			}