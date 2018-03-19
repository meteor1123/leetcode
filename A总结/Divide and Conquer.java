/*
	Divide and Conquer
*/





1. Array Problem
		1.1 Majority Element
			/*
				Given an array of size n, find the majority element. 
				The majority element is the element that appears more than ⌊ n/2 ⌋ times.
				You may assume that the array is non-empty and the majority element always exist in the array.
			*/
				public class Solution {
				    public int majorityElement(int[] nums) {
				        return findMajority(nums, 0, nums.length - 1);
				    }
				    public int findMajority(int[] nums, int start, int end) {
				        if (start == end) {
				            return nums[start];
				        }
				        int mid = (start + end) / 2;
				        int leftMajority = findMajority(nums, start, mid);
				        int rightMajority = findMajority(nums, mid + 1, end);
				        int leftCount = 0;
				        int rightCount = 0;
				        for (int i = start; i <= end; i++) {
				            if (nums[i] == leftMajority) {
				                leftCount++;
				            } 
				            if (nums[i] == rightMajority) {
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
		1.2 Maximum SubArray
			public class Solution {
			    public int maxSubArray(int[] nums) {
			        return findMaxSubArray(nums, 0, nums.length - 1);
			    }
			    public int findMaxSubArray(int[] nums, int start, int end) {
			        if (start == end) {
			            return nums[start];
			        }
			        if (start == end - 1) {
			            return Math.max(nums[start] + nums[end], Math.max(nums[start], nums[end]));
			        }
			        int mid = (start + end) / 2;
			        int leftMax = findMaxSubArray(nums, start, mid - 1);
			        int rightMax = findMaxSubArray(nums, mid + 1, end);

			        int midMax = nums[mid];
			        int temp = nums[mid];
			        
			        for (int i = mid - 1; i >= start; i--) {
			            temp = temp + nums[i];
			            if (midMax < temp) {
			                midMax = temp;
			            }
			        }
			        temp = midMax;
			        for (int i = mid + 1; i <= end; i++) {
			            temp = temp + nums[i];
			            if (midMax < temp) {
			                midMax = temp;
			            }
			        }
			        return Math.max(midMax, Math.max(leftMax, rightMax));
			    }
			}
		1.3 Maximum Product Subarray
			public class Solution {
			    public int maxProduct(int[] nums) {
			        if (nums == null || nums.length == 0) {
			            return 0;
			        }
			        int max = nums[0];
			        int min = nums[0];
			        int maxRes = nums[0];
			        for (int i = 1; i < nums.length; i++) {
			            int mx = max;
			            int mn = min;
			            max = Math.max(Math.max(nums[i], mx * nums[i]), mn * nums[i]);
			            min = Math.min(Math.min(nums[i], mx * nums[i]), mn * nums[i]);
			            maxRes = Math.max(max, maxRes);
			        }
			        return maxRes;
			    }
			}
		1.4 Kth Largest Element In An Array
			public class Solution {
			    public int findKthLargest(int[] nums, int k) {
			        return quickSelect(nums, nums.length - k + 1, 0, nums.length - 1);
			    }
			    public int quickSelect(int[] nums, int k, int start, int end) {
			        int l = start;
			        int r = end;
			        while (true) {
			            while (l < r && nums[l] < nums[end]) {
			                l++;
			            }
			            while (l < r && nums[r] >= nums[end]) {
			                r--;
			            }
			            if (l == r) {
			                break;
			            }
			            swap(nums, l, r);
			        }
			        swap(nums, l, end);
			        
			        if (l + 1 == k) {
			            return nums[l];
			        } else if (l + 1 < k) {
			            return quickSelect(nums, k, l + 1, end);
			        } else {
			            return quickSelect(nums, k, start, l - 1);
			        }
			    }
			    public void swap(int[] nums, int l, int r) {
			        int temp = nums[l];
			        nums[l] = nums[r];
			        nums[r] = temp;
			    }
			}
		1.5 Median Of Two Sorted Arrays
			/*
				There are two sorted arrays nums1 and nums2 of size m and n respectively. Find the median of the two sorted arrays. 
				The overall run time complexity should be O(log (m+n)).
			*/
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

					public static int findKth(int[] A, int aStart, int aEnd, int[] B, int bStart, int bEnd, int k) {
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

						//this statement is assgin the median index to A and B,
						int partA = Math.min(k / 2, m);
						int partB = k - partA;

						if (A[aStart + partA - 1] < B[bStart + partB - 1]) {
							return findKth(A, aStart + partA, aEnd, B, bStart, bEnd, k - partA);

						} else if (A[aStart + partA - 1] > B[bStart + partB - 1]) {

							return findKth(A, aStart, aEnd, B, bStart + partB, bEnd, k - partB);

						} else {
							
							return A[aStart + partA - 1];
						}
					}
				}

		1.6 Burst Balloons
			/*
				Example:
				Given [3, 1, 5, 8]

				Return 167
				    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
				   coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
			*/
			public class Solution {
			    public int maxCoins(int[] nums) {
			        int[] newNums = new int[nums.length + 2];
			        int n = 1;
			        for (int i : nums) {
			            if (i > 0) {
			                newNums[n++] = i;
			            }
			        }
			        newNums[0] = newNums[n++] = 1;
			        int[][] memo = new int[n][n];
			        return burst(memo, newNums, 0, n - 1);
			    }
			    
			    public int burst(int[][] memo, int[] nums, int left, int right) {
			        if (left + 1 == right) {
			            return 0;
			        }
			        if (memo[left][right] > 0) {
			            return memo[left][right];
			        }
			        int res = 0;
			        for (int i = left + 1; i < right; i++) {
			            res = Math.max(res, nums[left] * nums[i] * nums[right] + burst(memo, nums, left, i) + burst(memo, nums, i, right));
			        }
			        memo[left][right] = res;
			        return res;
			    }
			}
		1.6  Count Of Smaller Numbers After Self
		/*
			You are given an integer array nums and you have to return a new counts array. 
			The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
			Example:

			Given nums = [5, 2, 6, 1]

			To the right of 5 there are 2 smaller elements (2 and 1).
			To the right of 2 there is only 1 smaller element (1).
			To the right of 6 there is 1 smaller element (1).
			To the right of 1 there is 0 smaller element.
			Return the array [2, 1, 1, 0].
		*/
			public class Solution {
			    public List<Integer> countSmaller(int[] nums) {
			        List<Integer> sorted = new ArrayList<>();
			        Integer[] res = new Integer[nums.length];
			        for (int i = nums.length - 1; i >= 0; i--) {
			            res[i] = findIndex(sorted, nums[i]);
			            sorted.add(res[i], nums[i]);
			        }
			        return Arrays.asList(res);
			    }
			    public int findIndex(List<Integer> nums, int target) {
			        if (nums.size() == 0) {
			            return 0;
			        }
			        int start = 0;
			        int end = nums.size() - 1;
			        if (nums.get(end) < target) {
			            return end + 1;
			        }
			        if (nums.get(start) > target) {
			            return 0;
			        }
			        while (start + 1 < end) {
			            int mid = start + (end - start) / 2;
			            if (nums.get(mid) >= target) {
			               end = mid;
			            } else {
			               start = mid + 1;
			            } 
			        }
			        if (nums.get(start) >= target) {
			            return start; 
			        } else {
			            return end;
			        }
			    }
			}

2. String Problem
			2.1 Different Ways To Add Parentheses
			/*
				Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. 
				The valid operators are +, - and *.

				Example 1
					Input: "2-1-1".

					((2-1)-1) = 0
					(2-(1-1)) = 2
					Output: [0, 2]

				Example 2
					Input: "2*3-4*5"

					(2*(3-(4*5))) = -34
					((2*3)-(4*5)) = -14
					((2*(3-4))*5) = -10
					(2*((3-4)*5)) = -10
					(((2*3)-4)*5) = 10
					Output: [-34, -14, -10, -10, 10]
			*/
			public class Solution {
			    public List<Integer> diffWaysToCompute(String input) {
			        List<Integer> res = new ArrayList<>();
			        for (int i = 0; i < input.length(); i++) {
			            char c = input.charAt(i);
			            if (c == '-' || c == '+' || c == '*') {
			                List<Integer> part1 = diffWaysToCompute(input.substring(0, i));
			                List<Integer> part2 = diffWaysToCompute(input.substring(i + 1));
			                for (int num1 : part1) {
			                    for (int num2 : part2) {
			                        if (c == '+') {
			                           res.add(num1 + num2); 
			                        } else if (c == '-') {
			                            res.add(num1 - num2);
			                        } else if (c == '*') {
			                            res.add(num1 * num2);
			                        }
			                    }
			                }
			            }
			        }
			        if (res.size() == 0) {
			            res.add(Integer.valueOf(input));
			        }
			        return res;
			    }
			}

			2.2 Expression Add Operators
			//eval用来记录此次操作以后的总值，multed仅记录这步运算符后的值， 
    		//比如 2 + 3 * 5, eval = 2 + 3 * 5= 17, multed = 3 * 5=15
			public class Solution {
			    public List<String> addOperators(String num, int target) {
			        List<String> res = new ArrayList<>();
			        if (num == null || num.length() == 0) {
			            return res;
			        }
			        helper(res, "", num, target, 0, 0, 0);
			        return res;
			    }
			    public void helper(List<String> res, String path, String num, int target, int index, long item, long multed) {
			        if (index == num.length() && target == item) {
			            res.add(path);
			            return;
			        }
			        for (int i = index; i < num.length(); i++) {
			            if (i > index && num.charAt(index) == '0') { //如果首位为0，并且字符串长度还大于1，则舍去
			                break;
			            }
			            long cur = Long.parseLong(num.substring(index, i + 1));
			            if (index == 0) {
			                helper(res, path + cur, num, target, i + 1, cur, cur);
			            } else {
			                helper(res, path + "+" + cur, num, target, i + 1, item + cur, cur);
			                helper(res, path + "-" + cur, num, target, i + 1, item - cur, -cur);
			                helper(res, path + "*" + cur, num, target, i + 1, item - multed + multed * cur, multed * cur);
			            }
			        }
			    }
			}


