/*
	Array
*/




1. Matrix Problem
		1.1 Sparse Matrix Multiplication
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
		1.2 Spiral Matrix I
			/*
				Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
				For example,
				Given the following matrix:

				[
				 [ 1, 2, 3 ],
				 [ 4, 5, 6 ],
				 [ 7, 8, 9 ]
				]
				You should return [1,2,3,6,9,8,7,4,5].

				Show Company Tags
				Hide Tags
			*/
			public class Solution {
			    public List<Integer> spiralOrder(int[][] matrix) {
			        List<Integer> res = new ArrayList<>();
			        if (matrix == null || matrix.length == 0) {
			            return res;
			        }
			        int m = matrix.length;
			        int n = matrix[0].length;
			        int x = 0, y = 0;
			        while (m > 0 && n > 0) {
			            if (m == 1) {
			                for (int i = 0; i < n; i++) {
			                    res.add(matrix[x][y++]);
			                }
			                break;
			            } else if (n == 1) {
			                for (int i = 0; i < m; i++) {
			                    res.add(matrix[x++][y]);
			                }
			                break;
			            }
			            for (int i = 0; i < n - 1; i++) {
			                res.add(matrix[x][y++]);
			            }
			            for (int i = 0; i < m - 1; i++) {
			                res.add(matrix[x++][y]);
			            }
			            for (int i = 0; i < n - 1; i++) {
			                res.add(matrix[x][y--]);
			            }
			            for (int i = 0; i < m - 1; i++) {
			                res.add(matrix[x--][y]);
			            }
			            m = m - 2;
			            n = n - 2;
			            x++;
			            y++;
			        }
			        return res;
			    }
			}
		1.3 Spiral Matrix II
			/*
				Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
				For example,
				Given n = 3,

				You should return the following matrix:
				[
				 [ 1, 2, 3 ],
				 [ 8, 9, 4 ],
				 [ 7, 6, 5 ]
				]
			*/
			public class Solution {
			    public int[][] generateMatrix(int n) {
			        int[][] res = new int[n][n];
			        int x = 0;
			        int y = 0;
			        int k = n;
			        int val = 1;
			        while (k > 0) {
			            for (int i = 0; i < k - 1; i++) {
			                res[x][y++] = val++;
			            }
			            for (int i = 0; i < k - 1; i++) {
			                res[x++][y] = val++;
			            }
			            for (int i = 0; i < k - 1; i++) {
			                res[x][y--] = val++;
			            }
			            for (int i = 0; i < k - 1; i++) {
			                res[x--][y] = val++;
			            }
			            x++;
			            y++;
			            k = k - 2;
			        }
			        if (n % 2 != 0) {
			            res[n / 2][n / 2] = val;
			        }
			        return res;
			    }
			}
		1.3 Matrix Zigzag Traversal
			/*
				Matrix Zigzag Traversal
				Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in ZigZag-order.

				Have you met this question in a real interview? Yes
				Example
				Given a matrix:

				[
				  [1, 2,  3,  4],
				  [5, 6,  7,  8],
				  [9,10, 11, 12]
				]
				return [1, 2, 5, 9, 6, 3, 4, 7, 10, 11, 8, 12]
			 */

			 public class Solution {
			     public int[] printZMatrix(int[][] matrix) {
			         // write your code here
			         if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			             return null;
			         }
			         int m = matrix.length;
			         int n = matrix[0].length;
			         int count = m * n;
			         int[] res = new int[count];
			         int r = 0;//row
			         int c = 0;//col
			         int i = 1;
			         res[0] = matrix[0][0];
			         
			         while (i < count) {
			             //斜上走到顶
			             while (i < count && r - 1 >= 0 && c + 1 < n) {
			                 res[i++] = matrix[--r][++c];
			             }
			             
			             //横右走一步，不可横右走时竖下走一步
			             if (i < count && c + 1 < n) {
			                 res[i++] = matrix[r][++c];
			             } else if (i < count && r + 1 < m) {
			                 res[i++] = matrix[++r][c];
			             }
			             
			             //斜下走到底
			             while (i < count && r + 1 < m && c - 1 >= 0) {
			                 res[i++] = matrix[++r][--c];
			             }
			             
			             //竖下走一步，不可竖下走时横右走一步
			             if (i < count && r + 1 < m) {
			                 res[i++] = matrix[++r][c];
			             } else if (i < count && c + 1 < n) {
			                 res[i++] = matrix[r][++c];
			             }
			         }
			         return res;
			     }
			 }

		1.4 Pascal Triangle I
			/*
				Given numRows, generate the first numRows of Pascal's triangle.
				For example, given numRows = 5,
				Return

				[
				     [1],
				    [1,1],
				   [1,2,1],
				  [1,3,3,1],
				 [1,4,6,4,1]
				]
			*/
			//Solution1
			public class Solution {
			    public List<List<Integer>> generate(int numRows) {
			        List<List<Integer>> res = new ArrayList<>();
			        if (numRows == 0) {
			            return res;
			        }
			        List<Integer> rowRes = new ArrayList<>();
			        for (int i = 0; i < numRows; i++) {
			            rowRes.add(0, 1);
			            for (int j = 1; j < rowRes.size() - 1; j++) {
			                rowRes.set(j, rowRes.get(j) + rowRes.get(j + 1));
			            }
			            res.add(new ArrayList<>(rowRes));
			        }
			        return res;
			    }
			}
			//Solution2
			public class Solution {
			    public List<List<Integer>> generate(int numRows) {
			        List<List<Integer>> res = new ArrayList<>();
			        if (numRows == 0) {
			            return res;
			        }
			        List<Integer> initList = new ArrayList<>();
			        initList.add(1);
			        res.add(initList);
			        for (int i = 2; i <= numRows; i++) {
			            List<Integer> preRow = res.get(i - 2);
			            List<Integer> curRow = new ArrayList<>();
			            curRow.add(1);//第一个1
			            for (int j = 1; j <= i - 2; j++) {
			               curRow.add(preRow.get(j - 1) + preRow.get(j));
			            }
			            curRow.add(1);//最后一个1
			            res.add(curRow);
			        }
			        return res;
			    }
			}
		1.5 Pascal Triangle II
			/*
				Given an index k, return the kth row of the Pascal's triangle.

				For example, given k = 3,
				Return [1,3,3,1].

				Note:
				Could you optimize your algorithm to use only O(k) extra space?
			*/
			/*
				k = 0, [1]
				k = 1, [1, 1]
				k = 2, [1, 2, 1]
				k = 3, [1, 3, 3, 1]
			*/
			public class Solution {
			    public List<Integer> getRow(int numRows) {
			        List<Integer> rowRes = new ArrayList<>();
			    	for (int i = 0; i <= numRows; i++) {
			    		rowRes.add(0, 1);//这是指在首位insert 1 ！
			    		for (int j = 1; j < rowRes.size() - 1; j++) {//忽略首尾两个1
			    			rowRes.set(j, rowRes.get(j) + rowRes.get(j + 1));//规律  j 等于上一层的 j + (j + 1)
			    		}
			    	}
			    	return rowRes;
			    }
			}

		1.6 Search A 2D Matrix I
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
		1.7 Search A 2D Matrix II
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
			        return false;
			    }
			}
		1.8 Set Matrix Zeroes
			/*
				Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

				click to show follow up.

				Follow up:
				Did you use extra space?
				A straight forward solution using O(mn) space is probably a bad idea.
				A simple improvement uses O(m + n) space, but still not the best solution.
				Could you devise a constant space solution?
			*/
			public class Solution {
			    public void setZeroes(int[][] matrix) {
			        if (matrix == null || matrix.length == 0) {
			            return;
			        }
			        int m = matrix.length;
			        int n = matrix[0].length;
			        boolean hasZeroFirstRow = false;
			        boolean hasZeroFirstCol = false;
			        for (int i = 0; i < m; i++) {
			            if (matrix[i][0] == 0) {
			                hasZeroFirstCol = true;
			            }
			        }
			        for (int i = 0; i < n; i++) {
			            if (matrix[0][i] == 0) {
			                hasZeroFirstRow = true;
			            }
			        }
			        for (int i = 1; i < m; i++) {
			            for (int j = 1; j < n; j++) {
			                if (matrix[i][j] == 0) {
			                    matrix[0][j] = 0;
			                    matrix[i][0] = 0;
			                }
			            }
			        }
			        
			        for (int i = 1; i < m; i++) {
			            for (int j = 1; j < n; j++) {
			                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
			                    matrix[i][j] = 0;
			                }
			            }
			        }
			        
			        if (hasZeroFirstRow) {
			            for (int i = 0; i < n; i++) {
			                matrix[0][i] = 0;
			            }
			        }
			        if (hasZeroFirstCol) {
			            for (int i = 0; i < m; i++) {
			                matrix[i][0] = 0;
			            }
			        }
			    }
			}
		1.9 Rotate Image
			/*
				You are given an n x n 2D matrix representing an image.
				Rotate the image by 90 degrees (clockwise).

				Follow up:
				Could you do this in-place?
			*/
			/*
			    The idea was firstly transpose the matrix and then flip it symmetrically. For instance,

			    1  2  3
			    4  5  6
			    7  8  9
			    after transpose, it will be swap(matrix[i][j], matrix[j][i])

			    1  4  7
			    2  5  8
			    3  6  9
			    Then flip the matrix horizontally. (swap(matrix[i][j], matrix[i][matrix.length-1-j])

			    7  4  1
			    8  5  2
			    9  6  3
			*/
			public class Solution {
			    public void rotate(int[][] matrix) {
			    	if (matrix == null || matrix.length == 0) {
			    		return ;
			    	}
			    	int m = matrix.length;
			    	int n = matrix[0].length;
			    	for (int i = 0; i < m; i++) {
			    		for (int j = i; j < n; j++) {
			    			int temp = matrix[i][j];
			    			matrix[i][j] = matrix[j][i];
			    			matrix[j][i] = temp;
			    		}
			    	}

			    	for (int i = 0; i < m; i++) {
			    		for (int j = 0; j < n / 2; j++) {
			    			int temp = matrix[i][j];
			    			matrix[i][j] = matrix[i][n - j - 1];
			    			matrix[i][n - j - 1] = temp;
			    		}
			    	}
			    }
			}

		1.8 Game Of Life
			/*
				According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

				Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, 
				diagonal) using the following four rules (taken from the above Wikipedia article):

				Any live cell with fewer than two live neighbors dies, as if caused by under-population.
				Any live cell with two or three live neighbors lives on to the next generation.
				Any live cell with more than three live neighbors dies, as if by over-population..
				Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
				Write a function to compute the next state (after one update) of the board given its current state.

				Follow up: 
				Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
				In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?
			*/
			public class Solution {
			    public void gameOfLife(int[][] board) {
			        if (board.length == 0 || board == null) {
			            return;
			        }
			        int m = board.length;
			        int n = board[0].length;
			        for (int i = 0; i < m; i++) {
			            for (int j = 0; j < n; j++) {
			                int live = 0;
			                for (int k = 0; k < shiftX.length; k++) {
			                    int x = i + shiftX[k];
			                    int y = j + shiftY[k];
			                    if (x < 0 || y < 0 || x > m - 1 || y > n - 1) {
			                        continue;
			                    }
			                    if (board[x][y] == 1 || board[x][y] == 2) {
			                        live++;
			                    }
			                }
			                if (board[i][j] == 0 && live == 3) {
			                    board[i][j] = 3;
			                }
			                if (board[i][j] == 1 && (live < 2 || live > 3)) {
			                    board[i][j] = 2;
			                }
			            }
			        }
			        
			        for (int i = 0; i < m; i++) {
			            for (int j = 0; j < n; j++) {
			                board[i][j] %= 2;
			            }
			        }
			    }
			}




2. Array Problem
		2.1 Majority Element
			/*
				More than [n / 2]
			*/
			public class Solution {
			    public int majorityElement(int[] nums) {
			        int count = 1;
			        int majorityNum = nums[0];
			        for (int i = 1; i < nums.length; i++) {
			            if (nums[i] == majorityNum) {
			                count++;
			            } else if (count == 0) {
			                count++;
			                majorityNum = nums[i];
			            } else {
			                count--;
			            }
			        }
			        return majorityNum;
			    }
			}
		2.2 Majority Element II
			/*
				More than  [n / 3];
			*/
			public class Solution {
			    public List<Integer> majorityElement(int[] nums) {
			        List<Integer> res = new ArrayList<>();
			        if (nums == null || nums.length == 0) {
			            return res;
			        }
			        int count1 = 0, count2 = 0;
			        int candidate1 = 0, candidate2 = 1;
			        for (int num : nums) {
			            if (num == candidate1) {
			                count1++;
			            } else if (num == candidate2) {
			                count2++;
			            } else if (count1 == 0) {
			                candidate1 = num;
			                count1 = 1;
			            } else if (count2 == 0) {
			                candidate2 = num;
			                count2 = 1;
			            } else {
			                count1--;
			                count2--;
			            }
			        }
			        count1 = count2 = 0;
			        for (int num : nums) {
			            if (num == candidate1) {
			                count1++;
			            }
			            if (num == candidate2) {
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
		2.3 Maximum Subarray
		/*
			Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
			For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
			the contiguous subarray [4,−1,2,1] has the largest sum = 6.
		*/
			public class Solution {
			    public int maxSubArray(int[] nums) {
			        int localMax = nums[0];
			        int globalMax = nums[0];
			        for (int i = 1; i < nums.length; i++) {
			            localMax = Math.max(nums[i], localMax + nums[i]);
			            globalMax = Math.max(localMax, globalMax);
			        }
			        return globalMax;
			    }
			}
		2.3.1 Continuous Subarray Sum
			/*
				Continuous Subarray Sum
				Given an integer array, find a continuous subarray where the sum of numbers is the biggest.
				 Your code should return the index of the first number and the index of the last number. (If their are duplicate answer, return anyone)

				Example
				Give [-3, 1, 3, -3, 4], return [1,4].
			 */


			public class Solution {
				public ArrayList<Integer> continuousSubarraySum(int[] A) {
			        // Write your code here
			        ArrayList<Integer> res = new ArrayList<>();
			        if (A == null || A.length == 0) {
			            return res;
			        }
			        int curSum = 0;
			        int maxSum = Integer.MIN_VALUE;
			        int start = 0;
			        res.add(0);
			        res.add(0);
			        for (int i = 0; i < A.length; i++) {
			            curSum += A[i];
			            if (maxSum < curSum) {
			                res.set(0, start);
			                res.set(1, i);
			                maxSum = curSum;
			            }
			            if (curSum < 0) {
			                curSum = 0;
			                start = i + 1;
			            }
			            
			        }
			        
			        return res;
			    }
			}

		2.3.2 Maximum Size Subarray Sum Equals K
			/*
				Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
				Example 1:
				Given nums = [1, -1, 5, -2, 3], k = 3,
				return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)

				Example 2:
				Given nums = [-2, -1, 2, 1], k = 1,
				return 2. (because the subarray [-1, 2] sums to 1 and is the longest)

				Follow Up:
				Can you do it in O(n) time?
			*/
			public class Solution {
			    public int maxSubArrayLen(int[] nums, int k) {
			        HashMap<Integer, Integer> map = new HashMap<>();
			        int sum = 0;
			        int maxLen = 0;
			        for (int i = 0; i < nums.length; i++) {
			            sum += nums[i];
			            if (sum == k) {
			                maxLen = i + 1;
			            } else if (map.containsKey(sum - k)) {
			                maxLen = Math.max(maxLen, i - map.get(sum - k));
			            }
			            if (!map.containsKey(sum)) {
			                map.put(sum, i);
			            }
			            
			        }
			        return maxLen;
			    }
			}
		2.4 Shortest Word Distance III
		/*
			For example,
				Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

				Given word1 = “makes”, word2 = “coding”, return 1.
				Given word1 = "makes", word2 = "makes", return 3.

			Note:
				You may assume word1 and word2 are both in the list.
				The word may be duplicate
		*/
			public class Solution {
			    public int shortestWordDistance(String[] words, String word1, String word2) {
			        int dist = words.length;
			        int index1 = words.length;
			        int index2 = -words.length;
			        for (int i = 0; i < words.length; i++) {
			            if (words[i].equals(word1)) {
			                index1 = i;
			            } 
			            if (words[i].equals(word2)) {
			                if (word1.equals(word2)) {
			                    index1 = index2;
			                }
			                index2 = i;
			            }
			            dist = Math.min(dist, Math.abs(index2 - index1));
			        }
			        return dist;
			    }
			}

		2.5 First Missing Positive
		/*
			Given an unsorted integer array, find the first missing positive integer.

			For example,
			Given [1,2,0] return 3,
			and [3,4,-1,1] return 2.

			Your algorithm should run in O(n) time and uses constant space.
		*/
		public class Solution {
		    public int firstMissingPositive(int[] nums) {
		        int len = nums.length;
		        for (int i = 0; i < len; i++) {
		            while (nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i]) {
		                swap(nums, nums[i] - 1, i);
		            }
		        }
		        for (int i = 0; i < len; i++) {
		            if (nums[i] != i + 1) {
		                return i + 1;
		            }
		        }
		        return len + 1;
		    }
		    
		    public void swap(int[] nums, int i, int j) {
		        int temp = nums[i];
		        nums[i] = nums[j];
		        nums[j] = temp;
		    }
		}
		2.6 Summary Ranges
			/*
				Given a sorted integer array without duplicates, return the summary of its ranges.
				For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
			*/
			public class Solution {
			    public List<String> summaryRanges(int[] nums) {
			        List<String> res = new ArrayList<>();
			        for (int i = 0; i < nums.length; i++) {
			            int val = nums[i];
			            while (i + 1 < nums.length && nums[i + 1] - nums[i] == 1) {
			                i++;
			            }
			            if (val != nums[i]) {
			                res.add(val + "->" + nums[i]);
			            } else {
			                res.add(val + "");
			            }
			        }
			        return res;
			    }
			}
		2.7 Missing Ranges
			/*
				Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.
				For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
			*/
			//Solution:
			public class Solution {
			    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
			        List<String> res = new ArrayList<>();
			        //通过判断pre 和 after的关系，检查mssing的 rang
			        int pre = lower - 1;
			        int after = 0;
			        for (int i = 0; i <= nums.length; i++) {
			            if (i == nums.length) {
			                after = upper + 1;
			            } else {
			                after = nums[i];
			            }
			            //pre + 1 = after, 则没有缺失， 如果 pre + 2 = after，则只缺失1个, pre + 1
			            if (pre + 2 == after) {
			                res.add(String.valueOf(pre + 1));
			            //pre + 2 < after, 则缺失的范围大于1 pre  pre + 1->after - 1  after
			            } else if (pre + 2 < after) {
			                res.add(String.valueOf(pre + 1) + "->" + String.valueOf(after - 1));
			            }
			            pre = after;
			        }
			        return res;
			    }
			}
		2.8 Find The Celebrity
			/*
				Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

				Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. 
				You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

				You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.

				Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.
			*/
			/* The knows API is defined in the parent class Relation.
      			boolean knows(int a, int b); 
      		*/
      		/*
      			最关键的部分，celebrity一定不认识其他人，这样一定可以找出一个候选人
      			if (!knows(i, celebrity)) {
	                celebrity = i;
	            }
      		*/
			public class Solution extends Relation {
			    public int findCelebrity(int n) {
			        if (n <= 1) {
			            return n;
			        }
			        int celebrity = 0;
			        for (int i = 1; i < n; i++) {
			            if (!knows(i, celebrity)) {
			                celebrity = i;
			            }
			        }
			        for (int i = 0; i < n; i++) {
			            if (i != celebrity) {
			                if (!knows(i, celebrity) || knows(celebrity, i)) {
			                    return -1;
			                }
			            }
			        }
			        return celebrity;
			    }
			}

		2.9 Increasing Triplet Subsequence
			/*
				Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

				Formally the function should:
				Return true if there exists i, j, k 
				such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
				Your algorithm should run in O(n) time complexity and O(1) space complexity.

				Examples:
				Given [1, 2, 3, 4, 5],
				return true.

				Given [5, 4, 3, 2, 1],
				return false.
			 */
			/*
				核心思想：
					用三个pointer，min就是第一个，max是第二个，i是第三个，
					如果当前的i比max还大，那就是已经找到满足符合三个条件的数返回true，
					每一次只要nums[i] < min, 就jiang
			 */
			public class Solution {
			    public boolean increasingTriplet(int[] nums) {
			        if (nums == null || nums.length < 3) {
			            return false;
			        }
			        int min = Integer.MAX_VALUE; 
			        int max = Integer.MAX_VALUE;
			        int i = 0;
			        while (i < nums.length) {
			            if (nums[i] > max) {
			                return true;
			            } else if (nums[i] > min) {
			                max = nums[i];
			            } else {
			                min = nums[i];
			            }
			            i++;
			        }
			        return false;
			    }
			}

2. Two Pointer Problem

3. K Sum Problem
		

4. A


