/*
	Binary Search
*/




1. Search Problem
		1.1 Find The Duplicate Number
			/*
				Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), 
				prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

				Note:
				You must not modify the array (assume the array is read only).
				You must use only constant, O(1) extra space.
				Your runtime complexity should be less than O(n2).
				There is only one duplicate number in the array, but it could be repeated more than once.

			*/
			/*
				Solution:
				Let count be the number of elements in the range 1 .. mid, as in your solution.

				If count > mid, then there are more than mid elements in the range 1 .. mid and thus that range contains a duplicate.

				If count <= mid, then there are n+1-count elements in the range mid+1 .. n. 
				That is, at least n+1-mid elements in a range of size n-mid. Thus this range must contain a duplicate.
				不用管数组里的实际数字，用 n + 1 个数分布在1-n这个区间，直接用区间序号来计算
			*/
			public class Solution {
				 public int findDuplicate(int[] nums) {
			        int start = 1;
			        int end = nums.length - 1;
			        while (start < end) {
			            int mid = start + (end - start) / 2;
			            int count = noGreater(nums, mid);
			            if (count > mid) {
			                end = mid;
			            } else {
			                start = mid + 1;
			            }
			        }
			        return start;
			    }
			    public int noGreater(int[] nums, int target) {
			        int count = 0;
			        for (int i : nums) {
			            if (i <= target) {
			                count++;
			            }
			        }
			        return count;
			    }
			}
		1.2 Search Insert Position
			/*
				Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

				You may assume no duplicates in the array.

				Here are few examples.
				[1,3,5,6], 5 → 2
				[1,3,5,6], 2 → 1
				[1,3,5,6], 7 → 4
				[1,3,5,6], 0 → 0
			*/
			public class Solution {
			    public int searchInsert(int[] nums, int target) {
			        int start = 0;
			        int end = nums.length - 1;
			        while (start + 1 < end) {
			            int mid = start + (end - start) / 2;
			            if (nums[mid] > target) {
			                end = mid;
			            } else if (nums[mid] < target) {
			                start = mid;
			            } else {
			                return mid;
			            }
			        }
			        if (nums[start] >= target) {
			            return start;
			        } else if (nums[end] >= target) {
			            return end;
			        } else {
			            return end + 1;
			        }
			    }
			}
		1.3 Find Minimum In Rotated Sorted Array I
			/*
				Suppose a sorted array is rotated at some pivot unknown to you beforehand.
				(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
				Find the minimum element.

				No duplicate
			*/
			public class Solution {
			    public int findMin(int[] num) {
			        int start = 0;
			        int end = num.length - 1;
			        while (start + 1 < end) {
			            int mid = start + (end - start) / 2;
			            // the mid value large than end , means left side is sorted and rotated
			            if (num[mid] >= num[end]) {
			                start = mid;
			            } else {
			                end = mid;
			            }
			        }
			        //at last we just compare the num[]
			        if (num[start] < num[end]) {
			            return num[start];
			        } else {
			            return num[end];
			        }
			    }
			}
		1.4 Find Minimum In Rotated Sorted Array II
			/*
				Suppose a sorted array is rotated at some pivot unknown to you beforehand.
				(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
				Find the minimum element.

				Has duplicates
			*/
			public class Solution {
			    public int findMin(int[] nums) {
			        int start = 0;
			        int end = nums.length - 1;
			        while (start + 1 < end) {
			            int mid = start + (end - start) / 2;
			            if (nums[mid] < nums[end]) {
			                end = mid;
			            } else if (nums[mid] > nums[end]) {
			                start = mid;
			            } else {
			                end--;
			            }
			        }
			        if (nums[start] <= nums[end]) {
			            return nums[start];
			        } else {
			            return nums[end];
			        }
			    }
			}
		1.5 Search In Rotated Sorted Array
			/*
				Suppose a sorted array is rotated at some pivot unknown to you beforehand.
				(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

				You are given a target value to search. If found in the array return its index, otherwise return -1.
				You may assume no duplicate exists in the array.
			*/
			public class Solution {
			    public int search(int[] nums, int target) {
			        int start = 0;
			        int end = nums.length - 1;
			        while (start + 1 < end) {
			            int mid = start + (end - start) / 2;
			            if (nums[mid] == target) {
			                return mid;
			            }
			            if (nums[start] < nums[mid]) {
			                if (nums[start] <= target && target <= nums[mid]) {
			                    end = mid;
			                } else {
			                    start = mid;
			                }
			            } else {
			                if (nums[mid] <= target && target <= nums[end]) {
			                    start = mid;
			                } else {
			                    end = mid;
			                }
			            }
			        }
			        if (nums[start] == target) {
			            return start;
			        }
			        if (nums[end] == target) {
			            return end;
			        }
			        return -1;
			    }
			}
		1.6 Search In Rotated Sorted Array II
			//Only difference is has duplicate
			public class Solution {
			    public boolean search(int[] nums, int target) {
			        int start = 0;
			        int end = nums.length - 1;
			        while (start + 1 < end) {
			            int mid = start + (end - start) / 2;
			            if (nums[mid] == target) {
			                return true;
			            }
			            if (nums[start] < nums[mid]) {
			                if (nums[start] <= target && target <= nums[mid]) {
			                    end = mid;
			                } else {
			                    start = mid;
			                }
			            } else if (nums[start] > nums[mid]) {
			                if (nums[mid] <= target && target <= nums[end]) {
			                    start = mid;
			                } else {
			                    end = mid;
			                }
			            } else {
			                start++;
			            }
			        }
			        if (nums[start] == target || nums[end] == target) {
			            return true;
			        }
			        return false;
			    }
			}
		1.7 Search For A Range
			/*
				Given a sorted array of integers, find the starting and ending position of a given target value.

				Your algorithm's runtime complexity must be in the order of O(log n).

				If the target is not found in the array, return [-1, -1].

				For example,
				Given [5, 7, 7, 8, 8, 10] and target value 8,
				return [3, 4].
			*/
			/*
			    为什么我们用 start + 1 < end 而不用 start < end 或者 start <= end?
			    因为可以看到 我们需要找到target值出现的左右边界，如果start + 1 < end 跳出，则一定有 start + 1 > end ==> start > end - 1 ,--> start >= end
			*/
			public class Solution {
			    public int[] searchRange(int[] nums, int target) {
			        int[] res = new int[]{-1, -1};
			        int start = 0;
			        int end = nums.length - 1;
			        //find the left bound
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
			        } 
			        
			        start = 0;
			        end = nums.length - 1;
			        //find the right bound
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
			        } 
			        return res;
			    }
			}
		1.8 Find Peak Element
			/*
				A peak element is an element that is greater than its neighbors.

				Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

				The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

				You may imagine that num[-1] = num[n] = -∞.

				For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
			*/
			/*
			    This problem is similar to Local Minimum. And according to the given condition, 
			    num[i] != num[i+1], there must exist a O(logN) solution. 
			    So we use binary search for this problem.

			    If num[i-1] < num[i] > num[i+1], then num[i] is peak
			    If num[i-1] < num[i] < num[i+1], then num[i+1...n-1] must contains a peak
			    If num[i-1] > num[i] > num[i+1], then num[0...i-1] must contains a peak
			    If num[i-1] > num[i] < num[i+1], then both sides have peak (n is num.length)
			    Here is the code
			*/
			public class Solution {
			    public int findPeakElement(int[] nums) {
			        if (nums == null || nums.length == 0) {
			            return 0;
			        }
			        int start = 0;
			        int end = nums.length - 1;
			        while (start + 1 < end) {
			            int mid = start + (end - start) / 2;
			            if (nums[mid] > nums[mid - 1]) {
			                start = mid;
			            } else {
			                end = mid;
			            }
			        }
			        return nums[start] > nums[end] ? start : end;
			    }
			}
		1.9 Longest Increasing Subsequence
			/*
				Given an unsorted array of integers, find the length of longest increasing subsequence.
				For example,
				Given [10, 9, 2, 5, 3, 7, 101, 18],
				The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

				Your algorithm should run in O(n2) complexity.

				Follow up: Could you improve it to O(n log n) time complexity?
			*/
			
			/*
				Solution:
				O(n),binarySearch and Dp
			    The idea is that as you iterate the sequence, you keep track of the minimum value a subsequence of given length might end with, 
			    for all so far possible subsequence lengths. So dp[i] is the minimum value a subsequence of length i+1 might end with. Having this info,
			    for each new number we iterate to, we can determine the longest subsequence where it can be appended using binary search. 
			*/
			public class Solution {
			    public int lengthOfLIS(int[] nums) {
			        int[] dp = new int[nums.length];
			        int len = 0;
			        for (int num : nums) {
			            int i = binarySearch(dp, 0, len, num);
			            dp[i] = num;
			            if (i == len) {
			                len++;
			            }
			        }
			        return len;
			    }
			    private int binarySearch(int[] nums, int start, int end, int target) {
			        while (start < end) {
			            int mid = start + (end - start)/2;
			            if (nums[mid] >= target) {
			                end = mid;
			            } else {
			                start = mid + 1;
			            }
			        }
			        return end;
			    }
			}
		1.10 Smallest Rectangle Enclosing Black Pixels
			/*
				An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., 
				there is only one black region. Pixels are connected horizontally and vertically. 
				Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.
				For example, given the following image:

				[
				  "0010",
				  "0110",
				  "0100"
				]
				and x = 0, y = 2,
				Return 6.
			*/
				public class Solution {
					public int minArea(char[][] image, int x, int y) {
				        int m = image.length;
				        int n = image[0].length;
				        int colMin = binarySearch(image, true, 0, y, 0, m, true);
				        int colMax = binarySearch(image, true, y + 1, n, 0, m, false);
				        int rowMin = binarySearch(image, false, 0, x, colMin, colMax, true);
				        int rowMax = binarySearch(image, false, x + 1, m, colMin, colMax, false);
				        return (colMax - colMin) * (rowMax - rowMin);
				    }
				    public int binarySearch(char[][] image, boolean isHorizontal, int lower, int upper, int min, int max, boolean goLower) {
				        //以find colMin为例子，lower 就是 0列， upper就是y列，我们要在(lower ~ upper)之间找最小的col， y是所给坐标的列数
				        while (lower < upper) {
				            int mid = lower + (upper - lower) / 2;//从中间开始
				            boolean isOne = false;//标识是否找到“1”
				            for (int i = min; i < max; i++) {//由于是找列的最小最大值，所以需要遍历每一行，min是最小行，max是最大行
				                if ((isHorizontal ? image[i][mid] : image[mid][i]) == '1') {
				                    //image[i][mid]在某一行找列的1出现的最小最大范围，
				                    //image[mid][i]在某一列找行的1出现的最小最大范围
				                    isOne = true;
				                    break;
				                }
				            }
				            if (isOne == goLower) {
				                upper = mid;
				            } else {
				                lower = mid + 1;
				            }
				        }
				        return lower;
				    }
				}
2. Math Problem
		2.1 Sqrt(x)
			public class Solution {
			    public int mySqrt(int x) {
			        int start = 0;
			        int end = x;
			        while (start <= end) {
			            long mid = (long)(start + end) / 2;
			            if (mid * mid < x) {
			                start = (int)mid + 1;
			            } else if (mid * mid > x) {
			                end = (int)mid - 1;
			            } else {
			                return (int)mid;
			            }
			        }
			        return end;
			    }
			}
		2.2 Pow(x, n)
			public class Solution {
			    public double myPow(double x, int n) {
			        if (n >= 0) {
			            return pow(x, n);
			        } else {
			            return 1 / pow(x, -n);
			        }
			    }
			    public double pow(double x, int n) {
			        if (n == 0) {
			            return 1;
			        }
			        double res = pow(x, n/2);
			        if (n % 2 == 0) {
			            return res * res;
			        } else {
			            return res * res * x;
			        }
			    }
			}
		2.3  Divide Two Integers
			public class Solution {
			    public int divide(int dividend, int divisor) {
			        if (divisor == 0) {
			            return dividend >= 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
			        }
			        if (dividend == 0) {
			            return 0;
			        }
			        if (dividend == Integer.MIN_VALUE && divisor == -1) {
			            return Integer.MAX_VALUE;
			        }
			        boolean isNegative = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0);
			        long a = Math.abs((long)dividend);
			        long b = Math.abs((long)divisor);
			        
			        int res = 0;
			        
			        while (a >= b) {
			            int shift = 0;
			            while (a >= (b << shift)) {
			                shift++;
			            }
			            a -= b << (shift - 1);
			            res += 1 << (shift - 1);
			        }
			        return isNegative ? -res : res;
			    }
			}


3. Binary Search Tree
		3.1 Contains Duplicate III
			/*
				TreeSet数据结构（Java）使用红黑树实现，是平衡二叉树的一种。
	    		该数据结构支持如下操作：
			    1. floor()方法返set中不大于给定元素的最大元素；如果不存在这样的元素，则返回 null。
			    2. ceiling()方法返回set中不小于给定元素的最小元素；如果不存在这样的元素，则返回 null。
			*/
			public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		        if (k < 1 || t < 0) {
		            return false;
		        }
		        TreeSet<Integer> set = new TreeSet<Integer>();
		        for (int i = 0; i < nums.length; i++) {
		            if ((set.floor(nums[i]) != null && nums[i] <= t + set.floor(nums[i])) ||
		             (set.ceiling(nums[i]) != null && set.ceiling(nums[i]) <= t + nums[i])){
		                 return true;
		             }
		            set.add(nums[i]);
		            if (i >= k) {
		                set.remove(nums[i - k]);
		            }
		        }
		        return false;
		    }
		3.2 Kth Smallest Element In A BST My Submissions Question
			//Solution1: binary search, worst case O(n*n), avg:O(nlogn)
			public class Solution {
			    public int kthSmallest(TreeNode root, int k) {
			        int leftNum = countNode(root.left);
			        if (leftNum + 1 == k) {
			            return root.val;
			        } else if (leftNum + 1 < k) {
			            return kthSmallest(root.right, k - leftNum - 1);
			        } else {
			            return kthSmallest(root.left, k);
			        }
			    }
			    
			    public int countNode(TreeNode root) {
			        if (root == null) {
			            return 0;
			        }
			        return countNode(root.left) + countNode(root.right) + 1;
			    }
			}
			//Solution2: inorder iterative, worst case O(n), avg: O(logn)
			public class Solution {
			    int count = 0;
			    int res = Integer.MIN_VALUE;
			    public int kthSmallest(TreeNode root, int k) {
			        inorder(root, k);
			        return res;
			    }
			    public void inorder(TreeNode root, int k) {
			        if (root == null) {
			            return;
			        }
			        inorder(root.left, k);
			        count++;
			        if (count == k) {
			            res = root.val;
			            return;
			        }
			        inorder(root.right, k);
			    }
			}
		3.3 Closest Binary Search Tree Value
			/*
				Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

				Note:
				Given target value is a floating point.
				You are guaranteed to have only one unique value in the BST that is closest to the target.
			*/
			//Solution1: iteratibe
			public class Solution {
			    public int closestValue(TreeNode root, double target) {
			        int closet = root.val;
			        while (root != null) {
			            if (Math.abs(closet - target) >= Math.abs(root.val - target)) {
			                closet = root.val;
			            } 
			            root = target < root.val ? root.left : root.right;
			        }
			        return closet;
			    }
			}
			//Solution2: recursive
			public class Solution {   
			    public int closestValue(TreeNode root, double target) {
			        TreeNode child = target < root.val ? root.left : root.right;
			        if (child == null) {
			            return root.val;
			        }
			        int closetVal = closestValue(child, target);
			        return Math.abs(closetVal - target) < Math.abs(root.val - target) ? closetVal : root.val;
			    }
			}
		3.4  Count Complete Tree Nodes
			/*
				Given a complete binary tree, count the number of nodes.

				Definition of a complete binary tree from Wikipedia:
				In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. 
				It can have between 1 and 2h nodes inclusive at the last level h.
			*/
			public class Solution {
			    public int countNodes(TreeNode root) {
			        int rootHeight = height(root);
			        if (rootHeight == 0) {
			            return 0;
			        } 
			        
			        if (height(root.right) == rootHeight - 1) {
			            return (1 << (rootHeight - 1)) - 1 + countNodes(root.right) + 1;
			        }  else {
			            return (1 << (rootHeight - 2)) - 1 + countNodes(root.left) + 1;
			        }
			    }
			    
			    public int height(TreeNode root) {
			        if (root == null) {
			            return 0;
			        }
			        return height(root.left) + 1;
			    }
			}


4. Others
		4.1 First Bad Version
			/*
				You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check.
				Since each version is developed based on the previous version, all the versions after a bad version are also bad.
				Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

				You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. 
				You should minimize the number of calls to the API.
			*/
			/*  The isBadVersion API is defined in the parent class VersionControl.
	      		boolean isBadVersion(int version); 
	      	*/

			public class Solution extends VersionControl {
			    public int firstBadVersion(int n) {
			        int start = 1;
			        int end = n;
			        while (start + 1 < end) {
			            int mid = start + (end - start) / 2;
			            if (isBadVersion(mid)) {
			                end = mid;
			            } else {
			                start = mid;
			            }
			        }
			        
			        if (isBadVersion(start)) {
			            return start;
			        } else {
			            return end;
			        }
			    }
			}
		4.2 Matrix Search Problem
			4.2.1 Search A 2D Matrix
				/*
					Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
					Integers in each row are sorted from left to right.
					The first integer of each row is greater than the last integer of the previous row.
					For example,

					Consider the following matrix:

					[
					  [1,   3,  5,  7],
					  [10, 11, 16, 20],
					  [23, 30, 34, 50]
					]
					Given target = 3, return true.
				*/
				public class Solution {
				    public boolean searchMatrix(int[][] matrix, int target) {
				        if (matrix == null || matrix.length == 0) {
				            return false;
				        }
				        int m = matrix.length;
				        int n = matrix[0].length;
				        int start = 0;
				        int end = m * n - 1;
				        while (start + 1 < end) {
				            int mid = start + (end - start) / 2;
				            int row = mid / n;
				            int col = mid % n;
				            if (matrix[row][col] > target) {
				                end = mid;
				            } else if (matrix[row][col] < target) {
				                start = mid;
				            } else {
				                return true;
				            }
				        }
				        if (matrix[start / n][start % n] == target || matrix[end / n][end % n] == target) {
				            return true;
				        }
				        return false;
				    }
				}
			4.2.2 Search A 2D Matrix II
				/*
					Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

					Integers in each row are sorted in ascending from left to right.
					Integers in each column are sorted in ascending from top to bottom.
					For example,

					Consider the following matrix:

					[
					  [1,   4,  7, 11, 15],
					  [2,   5,  8, 12, 19],
					  [3,   6,  9, 16, 22],
					  [10, 13, 14, 17, 24],
					  [18, 21, 23, 26, 30]
					]
					Given target = 5, return true.

					Given target = 20, return false.
				*/
				public class Solution {
				    public boolean searchMatrix(int[][] matrix, int target) {
				        if (matrix == null || matrix.length == 0) {
				            return false;
				        }
				        int m = matrix.length;
				        int n = matrix[0].length;
				        int row = 0;
				        int col = n - 1;
				        while (row < m && col >= 0) {
				            if (matrix[row][col] == target) {
				                return true;
				            } else if (matrix[row][col] > target) {
				                col--;
				            } else {
				                row++;
				            }
				        }
				        // Follow Up: count the same target
				        // while (row < rowLen && col >= 0) {
						// 	if (matrix[row][col] == target) {
						// 		count++;
						// 		row++;
						// 		col--;
						// 	} else if (matrix[row][col] > target) {
						// 		col--;
						// 	} else {
						// 		row++;
						// 	}
						// }
						// return count;
				        return false;
				    }
				}
			4.3 H-Index II
				/*
					Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

					According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

					For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3.

					Note: If there are several possible values for h, the maximum one is taken as the h-index.

					Hint:

					An easy approach is to sort the array first.
					What are the possible values of h-index?
					A faster approach is to use extra space.
				*/
				//The citations array is sorted
				public class Solution {
				    public int hIndex(int[] citations) {
				        if (citations == null || citations.length == 0) {
				            return 0;
				        }
				        int len = citations.length;
				        int start = 0;
				        int end = len - 1;
				        while (start + 1 < end) {
				            int mid = start + (end - start) / 2;
				            if (citations[mid] >= len - mid) {
				                end = mid;
				            } else {
				                start = mid;
				            }
				        }
				        if (citations[start] >= len - start) {
				            return len - start;
				        } else if (citations[end] >= len - end) {
				            return len - end;
				        } else {
				            return 0;
				        }
				    }
				}




