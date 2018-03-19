/*
	Two Pointers
*/





1. Array Problem
		1.1 Remove Series
			1.1.1 Remove Element
				//Solution1
				public int removeElement(int[] nums, int val) {
			        int index = 0;
			        for (int i = 0; i < nums.length; i++) {
			            if (nums[i] != val) {
			                nums[index++] = nums[i];
			            }
			        }
			        return index;
			    }
			    //Solution2
			    public int removeElement(int[] nums, int val) {
			        int start = 0;
			        int end = nums.length - 1;
			        while (start <= end) {
			            if (nums[start] != val) {
			                start++;
			            } else if (nums[end] == val) {
			                end--;
			            } else {
			                swap(nums, start++, end--);
			            }
			        }
			        return start;
			    }
			1.1.2 Move Zeroes
				//Solution1: Reduce the assign
				public class Solution {
					public void moveZeroes (int[] arr) {
						if (arr == null || arr.length == 0) {
							return;
						}
						int count = 0;
						//use a count to record the number of non-zero number
						//every time when the number do not equal zero,
						//we set the arr[i] value to the count index ,and add the count 
						for (int i = 0; i < arr.length; i++) {
							if (arr[i] != 0) {
								//count is used to record the amount of non-zero number
								if (i == count) {//减少赋值的次数
								    count++;
								    continue;
								}
								arr[count++] = arr[i];
							}
						}
						while (count < arr.length) {
							arr[count++] = 0;
						}
					}
				}
				//Solution2: minimum swap time, unorder
				public class Solution {
				    public int moveZeroes(int[] nums) {
				        int start = 0;
				        int end = nums.length - 1;
				        while (start <= end) {
				            if (nums[start] != 0) {
				                start++;
				            } else if (nums[end] == 0) {
				                end--;
				            } else {
				                swap(nums, start++, end--);
				            }
				        }
				        return start;
				    }
				    public void swap(int[] nums, int i, int j) {
				        int temp = nums[i];
				        nums[i] = nums[j];
				        nums[j] = temp;
				    }
				}

			1.1.3 Remove Duplicates From Sorted Array
				public class Solution {
				    public int removeDuplicates(int[] nums) {
				        if (nums.length <= 1) {
				            return nums.length;
				        }
				        int index = 1;
				        for (int i = 1; i < nums.length; i++) {
				            if (nums[i] != nums[i - 1]) {
				                nums[index++] = nums[i];
				            }
				        }
				        return index;
				    }
				}
			1.1.4 Remove Duplicates From Sorted Array II
				public class Solution {
				    public int removeDuplicates(int[] nums) {
				        if (nums.length <= 2) {
				            return nums.length;
				        }
				        int index = 2;
				        for (int i = 2; i < nums.length; i++) {
				        	//Reduce the assign times
				        	if (nums[index] == nums[i]) {
			                    index++;
			                    continue;
			                }
				            if (nums[i] > nums[index - 2]) {
				                nums[index++] = nums[i];
				            }
				        }
				        return index;
				    }
				}


		1.2 Find The Duplicate Number
			/*
				Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

				Note:
				You must not modify the array (assume the array is read only).
				You must use only constant, O(1) extra space.
				Your runtime complexity should be less than O(n2).
				There is only one duplicate number in the array, but it could be repeated more than once.
			*/
			//Solution1:
			public int findDuplicate(int[] nums) {
			    int slow = 0, fast = 0;
			    do{
			        slow = nums[slow];
			        fast = nums[nums[fast]];
			    }while(slow != fast);
			    slow = 0;
			    while(slow != fast){
			        slow = nums[slow];
			        fast = nums[fast];
			    }
			    return slow;
			}

			//Solution2:
			public class Solution {
			    public int findDuplicate(int[] nums) {
			        int start = 1;
			        int end = nums.length - 1;
			        while (start < end) {
			            int mid = start + (end - start) / 2;
			            int count = noGreater(nums, mid);
			            if (count <= mid) {
			                start = mid + 1;
			            } else {
			                end = mid;
			            }
			        }
			        return start;//我们注意到 因为 start 最后会等于end，所以返回start或end都行
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
		1.3 Sort Color I(II Using QuickSlect)
			public class Solution {
			    public void sortColors(int[] nums) {
			        if (nums == null || nums.length <= 1) {
			            return;
			        }
			        int index0 = 0;//指示0
			        int index2 = nums.length - 1;//指示2
			        
			        int i = 0;//遍历
			        while (i <= index2) {
			            if (nums[i] == 0) {
			                swap(nums, i++, index0++);
			            } else if (nums[i] == 1) {
			                i++;
			            } else {
			                swap(nums, i, index2--);
			            }
			        }
			    }
			}
		1.4 Container With Most Water
			/*
				Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
				n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
				Note: You may not slant the container.
			*/
			public class Solution {
			    public int maxArea(int[] height) {
			        int left = 0;
			        int right = height.length - 1;
			        int max = 0;
			        while (left < right) {
			            if (height[left] < height[right]) {
			                max = Math.max(max, height[left] * (right - left));
			                left++;
			            } else {
			                max = Math.max(max, height[right] * (right - left));
			                right--;
			            }
			        }
			        return max;
			    }
			}
		1.5 Trapping Rain Water
			/*
				Given n non-negative integers representing an elevation map where the width of each bar is 1, 
				compute how much water it is able to trap after raining.
				For example, 
					Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
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
		1.6 Product Of Array Except Self
				/*
					Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
					Solve it without division and in O(n).

					For example, given [1,2,3,4], return [24,12,8,6].
					Follow up:
					Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
				*/
				/*
					Algorithm:
						1. 算nums数组里的每一个数的 右边的数乘积，
						2. 再算每个数的左边的数的乘积，
						3. 两个相乘就是除了这个数以外的乘积
				*/
				public class Solution {
				    public int[] productExceptSelf(int[] nums) {
				        int[] res = new int[nums.length];
				        res[0] = 1;
				        for (int i = 1; i < nums.length; i++) {
				            res[i] = nums[i - 1] * res[i - 1];
				        }
				        int right = 1;
				        for (int i = nums.length - 1; i >= 0; i--) {
				            res[i] = res[i] * right;
				            right = right * nums[i];
				        }
				        return res;
				    }
				}
		1.7 Minimum Size Subarray Sum
			/*
				Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.
				For example, given the array [2,3,1,2,4,3] and s = 7,
				the subarray [4,3] has the minimal length under the problem constraint.
			*/
			public class Solution {
			    public int minSubArrayLen(int s, int[] nums) {
			        int minLen = Integer.MAX_VALUE;
			        int left = 0;
			        int right = 0;
			        int sum = 0;
			        while (right < nums.length) {
			            sum += nums[right];
			            while (sum >= s) {
			                minLen = Math.min(minLen, right - left + 1);
			                sum -= nums[left];
			                left++;
			            }
			            right++;
			        }
			        return minLen == Integer.MAX_VALUE ? 0 : minLen;
			    }
			}
		1.8 Merge Sorted Array
			/*
				Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
				Note:
				You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.
			*/
			public class Solution {
			    public void merge(int[] nums1, int m, int[] nums2, int n) {
			        int i = m - 1;
			        int j = n - 1;
			        int index = m + n - 1;
			        while (i >= 0 && j >= 0) {
			            if (nums1[i] > nums2[j]) {
			                nums1[index--] = nums1[i--];
			            } else {
			                nums1[index--] = nums2[j--];
			            }
			        }
			        
			        //只需要检测nums2，因为nums1已经排好位置
			        while (j >= 0) {
			            nums1[index--] = nums2[j--];
			        }
			    }
			}





2. N Sum Problem
		2.0 Two Sum
			//Solution1: Hash
			public class Solution {
			    public int[] twoSum(int[] nums, int target) {
			        int[] res = new int[2];
			        HashMap<Integer, Integer> map = new HashMap<>();
			        for (int i = 0; i < nums.length; i++) {
			            if (map.containsKey(target - nums[i])) {
			                res[0] = map.get(target - nums[i]) + 1;
			                res[1] = i + 1;
			                return res;
			            } else {
			                map.put(nums[i], i);
			            }
			        }
			        return res;
			    }
			}
			//Solution2: input is sorted array
			public int[] twoSum(int[] numbers, int target) {
			    int[] res = new int[2];
			    if(numbers==null || numbers.length<2)
			        return null;
			    int left = 0;
			    int right = numbers.length-1;
			    while(left < right)
			    {
			        if(numbers[left] + numbers[right] == target)
			        {
			            res[0] = number[left];
			            res[1] = number[right];
			            return res;
			        }
			        else if(numbers[left] + numbers[right] > target)
			        {
			            right--;
			        }
			        else
			        {
			            left++;
			        }
			    }
			    return null;
			}
			//brute force unsorted
			public int[] twoSum(int[] numbers, int target) {
				int[] res = new int[2];
				if (number == null || numbers.length < 2) {
					return null;
				}
				for (int i = 0; i < numbers.length - 1; i++) {
					for (int j = i + 1; j < numbers.length; j++) {
						if (numbers[i] + numbers[j] == target) {
							res[0] = i + 1;
							res[1] = j + 1;
							return res;
						} 
					}
				}
				res[0] = -1;
				res[1] = -1;
				return res;
			}
		2.1 Two Sum II - Input Array Is Sorted
			/*
				Input: numbers={2, 7, 11, 15}, target=9
				Output: index1=1, index2=2
			*/
			public class Solution {
			    public int[] twoSum(int[] numbers, int target) {
			        int[] res = new int[2];
			        int left = 0;
			        int right = numbers.length - 1;
			        while (left < right) {
			            int sum = numbers[left] + numbers[right];
			            if (sum > target) {
			                right--;
			            } else if (sum < target) {
			                left++;
			            } else {
			                res[0] = left + 1;
			                res[1] = right + 1;
			                return res;
			            }
			        }
			        return res;
			    }
			}
		2.2 Two Sum III - Data Structure Design
			/*
				Design and implement a TwoSum class. It should support the following operations: add and find.
				add - Add the number to an internal data structure.
				find - Find if there exists any pair of numbers which sum is equal to the value.

				For example,
				add(1); add(3); add(5);
				find(4) -> true
				find(7) -> false
			*/
			public class TwoSum {
			    // Add the number to an internal data structure.
			    HashMap<Integer, Integer> map = new HashMap<>();
				public void add(int number) {
				    map.put(number, map.containsKey(number) ? map.get(number) + 1 : 1);
				}
			    // Find if there exists any pair of numbers which sum is equal to the value.
				public boolean find(int value) {
				    for (int key : map.keySet()) {
				        if (map.containsKey(value - key)) {
				            if (map.get(value - key) >= 2 || (map.get(value - key) == 1 && key != value - key)) {
				                return true;
				            }
				        }
				    }
				    return false;
				}
			}
		2.3 3Sum
			/*
				Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

				Note:
				Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
				The solution set must not contain duplicate triplets.
				    For example, given array S = {-1 0 1 2 -1 -4},

				    A solution set is:
				    (-1, 0, 1)
				    (-1, -1, 2)
			*/
			public class Solution {
			    public List<List<Integer>> threeSum(int[] nums) {
			        List<List<Integer>> res = new ArrayList<>();
			        HashSet<List<Integer>> set = new HashSet<>();
			        Arrays.sort(nums);
			        for (int left = 0; left < nums.length - 2; left++) {
			            int mid = left + 1;
			            int right = nums.length - 1;
			            while (mid < right) {
			                int sum = nums[left] + nums[mid] + nums[right];
			                if (sum == 0) {
			                    List<Integer> item = new ArrayList<>();
			                    item.add(nums[left]);
			                    item.add(nums[mid]);
			                    item.add(nums[right]);
			                    if (!set.contains(item)) {
			                        set.add(item);
			                        res.add(item);
			                    }
			                    mid++;
			                    right--;
			                } else if (sum > 0) {
			                    right--;
			                } else {
			                    mid++;
			                }
			            }
			        }
			        return res;
			    }
			}
		2.4 3Sum Smaller
			/*
				Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that 
				satisfy the condition nums[i] + nums[j] + nums[k] < target.
				For example, given nums = [-2, 0, 1, 3], and target = 2.

				Return 2. Because there are two triplets which sums are less than 2:

				[-2, 0, 1]
				[-2, 0, 3]
				Follow up:
				Could you solve it in O(n2) runtime?
			*/
			public class Solution {
			    public int threeSumSmaller(int[] nums, int target) {
			        if (nums == null || nums.length == 0) {
			            return 0;
			        }
			        int left = 0;
			        int count = 0;
			        Arrays.sort(nums);
			        while (left < nums.length - 2) {
			            int mid = left + 1;
			            int right = nums.length - 1;
			            while (mid < right) {
			                int sum = nums[left] + nums[mid] + nums[right];
			                if (sum >= target) {
			                    right--;
			                } else {
			                    count += right - mid;
			                    mid++;
			                }
			            }
			            left++;
			        }
			        return count;
			    }
			}
		2.5 3Sum Closest
			/*
				Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. 
				Return the sum of the three integers. You may assume that each input would have exactly one solution.
			    For example, given array S = {-1 2 1 -4}, and target = 1.

			    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
			*/
			public class Solution {
			    public int threeSumClosest(int[] nums, int target) {
			        Arrays.sort(nums);
			        int closestValue = Integer.MAX_VALUE;
			        int minSum = 0;
			        for (int left = 0; left < nums.length - 2; left++) {
			            int mid = left + 1;
			            int right = nums.length - 1;
			            while (mid < right) {
			                int sum = nums[left] + nums[mid] + nums[right];
			                if (Math.abs(sum - target) < closestValue) {
			                    closestValue = Math.abs(sum - target);
			                    minSum = sum;
			                } else if (sum > target) {
			                    right--;
			                } else {
			                    mid++;
			                }
			            }
			        }
			        return minSum;
			    }
			}
		2.6 4Sum
			/*
				Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? 
				Find all unique quadruplets in the array which gives the sum of target.
				Note:
				Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
				The solution set must not contain duplicate quadruplets.
				    For example, given array S = {1 0 -1 0 -2 2}, and target = 0.

				    A solution set is:
				    (-1,  0, 0, 1)
				    (-2, -1, 1, 2)
				    (-2,  0, 0, 2)
			*/
			public class Solution {
			    public List<List<Integer>> fourSum(int[] nums, int target) {
			        List<List<Integer>> res = new ArrayList<>();
			        HashSet<List<Integer>> set = new HashSet<>();
			        Arrays.sort(nums);
			        for (int left = 0; left < nums.length - 3; left++) {
			            for (int mid = left + 1; mid < nums.length - 2; mid++) {
			                int i = mid + 1;
			                int right = nums.length - 1;
			                while (i < right) {
			                    int sum = nums[left] + nums[mid] + nums[i] + nums[right];
			                    if (sum == target) {
			                        List<Integer> item = new ArrayList<>();
			                        item.add(nums[left]);
			                        item.add(nums[mid]);
			                        item.add(nums[i]);
			                        item.add(nums[right]);
			                        if (!set.contains(item)) {
			                            set.add(item);
			                            res.add(item);
			                        }
			                        i++;
			                        right--;
			                    } else if (sum > target) {
			                        right--;
			                    } else {
			                        i++;
			                    }
			                }
			            }
			        }
			        return res;
			    }
			}
		2.7 K Sum
			/*
				k Sum
				Given n distinct positive integers, integer k (k <= n) and a number target.

				Find k numbers where sum is target. Calculate how many solutions there are?

				Have you met this question in a real interview? Yes
				Example
				Given [1,2,3,4], k = 2, target = 5.

				There are 2 solutions: [1,4] and [2,3].

				Return 2.

				Tags: Dynamic Programming
			 */

			//Solution1: O(m*n*k)
			public class Solution {
			    /**
			     * @param A: an integer array.
			     * @param k: a positive integer (k <= length(A))
			     * @param target: a integer
			     * @return an integer
			     */
			    public int kSum(int A[], int k, int target) {
			        // write your code here
			        if (target < 0) {
			            return 0;
			        }
			        int len = A.length;
			        int[][][] dp = new int[len + 1][k + 1][target + 1];
			        dp[0][0][0] = 1;
			        
			        for (int i = 0; i <= len; i++) {
			            for (int j = 0; j <= k; j++) {
			                for (int t = 0; t <= target; t++) {
			                    if (j == 0 && t == 0) {
			                        dp[i][j][t] = 1;
			                    } else if (!(i == 0 || j == 0 || t == 0)) {
			                        dp[i][j][t] = dp[i - 1][j][t];
			                        if (t - A[i - 1] >= 0) {
			                            dp[i][j][t] += dp[i - 1][j - 1][t - A[i - 1]];
			                        }
			                    }
			                }
			            }
			        }
			        return dp[len][k][target];
			    }
			}
		2.7 K Sum II
			/*
				Given n unique integers, number k (1<=k<=n) and target.
				Find all possible k integers where their sum is target.
				Example
				Given [1,2,3,4], k = 2, target = 5. Return:
				[
				  [1,4],
				  [2,3]
				]
			 */
			
			public class Solution {
			    public ArrayList<ArrayList<Integer>> kSumII(int[] A, int k, int target) {
			        // write your code here
			        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
			        ArrayList<Integer> item = new ArrayList<>();
			        Arrays.sort(A);
			        helper(A, 0, k, target, res, item);
			        return res;
			    }
			    
			    public void helper(int[] A,int start, int k, int target, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> item) {
			        if (target < 0) {
			            return;
			        }
			        if (target == 0 && item.size() == k) {
			            res.add(new ArrayList<>(item));
			            return;
			        }
			        for (int i = start; i < A.length; i++) {
			            item.add(A[i]);
			            helper(A, i + 1, k, target - A[i], res, item);
			            item.remove(item.size() - 1);
			        }
			    }
			}










