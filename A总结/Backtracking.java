1.First Part: Introduction Of Backtracking Algorithm
	1.0 Overview
		Backtracking is a systematic way to iterate through all the possible configurations of a search space. 
		These configurations may represent as:
			Permutations（排列）: all possible arrangements of objects.
			Subsets（子集）: all possible ways of building a collection of them
			Combinations(组合）: a combination is a way of selecting items from a collection, such that the order of selection does not matter.
		Other situations may demand enumerating all spanning trees of a graph, all paths between two vertices, or all possible ways to partition vertices into color classes.
		What these problems have in common is that we must generate each one possible configuration exactly once.


		Backtrack-DFS(A, k)//Pseudocode
			if A = (a1, a2, ..., ak) is a solution, report it. 
			else
				k = k + 1 
				compute Sk 
				while Sk != ∅ do
					ak = an element in Sk 
					Sk = Sk − ak 
					Backtrack-DFS(A, k)
    1.1 Q & A
		1.1.1 Why use DFS for Backtracking problem ?
				Although a breadth-first search could also be used to enumerate solutions, a depth-first search is greatly preferred because it uses much less space. 
				The current state of a search is completely represented by the path from the root to the current search depth-first node. 
				This requires space proportional to the height of the tree.

		1.1.2 Why we dont use BFS for Backtracking problem ?
				In breadth-first search, the queue stores all the nodes at the current level, which is proportional to the width of the search tree. 
				For most interesting problems, the width of the tree grows exponentially in its height.

		1.1.3 Why Backtracking works and how?
				Backtracking ensures correctness by enumerating all possibilities. 
			   It ensures efficiency by never visiting a state more than once.

	1.2 Implementation(From Skiena The Algorithm Desigen Manual):
		//The honest working backtrack code is given below:

		public class Solution {
			private boolean finished = false;/* found all solutions yet? */

			public void backtrack(int a[], int k, data input) {
				int c[MAXCANDIDATES]; /* candidates for next position */
				int ncandidates; /* next position candidate count */
				int i; /* counter */
				
				if (is_a_solution(a, k, input)) {
					process_solution(a, k, input);
				} else {
					k = k + 1;
					construct_candidates(a, k, input, c, &ncandidates);
					for (int i = 0; i < ncandidates; i++) {
						a[k] = c[i];
						make_move(a, k, input);
						backtrack(a, k, input);
						unmake_move(a, k, input);
						if (finished) {
							return ;
						}/* terminate early */
					}
				}
			}
		}
		1.2.1 Backtracking Method Explain
			1.2.1 is_a_solution(a,k,input)
				This Boolean function tests whether the first k elements of vector a from a complete solution for the given problem. 
				The last argument, input, allows us to pass general information into the routine. We can use it to specify n---the size of a target solution. 
				This makes sense when constructing permutations or subsets of n elements, but other data may be relevant when constructing variable-sized objects such as sequences of moves in a game.
			1.2.2 construct_candidates(a, k, input, c, ncandidates) 
				This routine fills an array c with the complete set of possible candidates for the kth position of a, given the contents of the first k − 1 positions. 
				The number of candidates returned in this array is denoted by ncandidates. Again, input may be used to pass auxiliary information.
			1.2.3 process solution(a,k,input)
				This routine prints, counts, or however processes a complete solution once it is constructed.
			1.2.4 make_move(a, k, input) and unmake_move(a, k, input)
				able us to modify a data structure in response to the latest move, as well as clean up this data structure if we decide to take back the move. 
				Such a data structure could be rebuilt from scratch from the solution vector a as needed, but this is inefficient when each move involves incremental changes that can easily be undone.






2. Second Part: Problems Set
		1. Permutations 排列 //[1,2,3] have the following permutations:[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
			1.1 Permutations I, II //Permutations II 只需要加入下面2行代码
				//Recursive, the key is boolean[] array!
				public class Solution {
				    public List<List<Integer>> permute(int[] nums) {
				        List<List<Integer>> res = new ArrayList<>();
				        if (nums == null || nums.length == 0) {
				            return res;
				        }
				        //Arrays.sort(nums); Permutations II
				        List<Integer> item = new ArrayList<>();
				        boolean[] visited = new boolean[nums.length];
				        helper(nums, res, item, visited);
				        return res;
				    }
				    public void helper(int[] nums, List<List<Integer>> res, List<Integer> item, boolean[] visited) {
				        if (item.size() == nums.length) {
				            res.add(new ArrayList<>(item));
				            return;
				        }
				        for (int i = 0; i < nums.length; i++) {
				        	//if (i != 0 && nums[i] == nums[i - 1] && nums[i - 1] == false) Permutations II
				            if (visited[i] == false) {
				                visited[i] = true;
				                item.add(nums[i]);
				                helper(nums, res, item, visited);
				                item.remove(item.size() - 1);
				                visited[i] = false;
				            }
				           
				        }
				    }
				}
				//Iterative
				public class Solution {
					public List<List<Integer>> permute(int[] nums) {
				        List<List<Integer>> res = new ArrayList<List<Integer>>();
				        if (nums.length == 0 || nums == null) {
				            return res;
				        }
				        // Collections.sort(nums) //Permutations II
				        List<Integer> initList = new ArrayList<>();
				        initList.add(nums[0]);
				        res.add(initList);				   
				        /*	
				    		if nums[] = {1, 2, 3}
				    		At first 			  : ans = { (1) }
				    		After second for loop : ans = { (2, 1), (1, 2)}
				    		Finaly:    			  :	ans = { (3,2,1) (3,1,2) (2,3,1) (1,3,2) (2,1,3) (1,2,3)}
				    	*/
				        for (int i = 1; i < nums.length; i++) { //遍历待插入元素
				            List<List<Integer>> tempRes = new ArrayList<>();
				            for (int j = 0; j <= i; j++) { //遍历插入位置
				                for (List<Integer> list : res) {//遍历res里的 list，取出来插入
				                    List<Integer> newList = new ArrayList<>(list);//这样是copy的用法，如果直接把newList = list,将会导致错误
				                    newList.add(j, nums[i]);
				                    // if (!tempRes.contains) {  //Permutations II 
				                    // 	tempRes.add(newList);
				                    // }
				                    tempRes.add(newList);
				                }
				            }
				            res = tempRes;
				        }
				        return res;
				    }
				}
			1.2 Permutation Sequence 
				/*
					The logic is as follows: 
						1. For n numbers, permutations can be divided into n groups with (n - 1)! elements in each group. 
						2. Thus, k / (n - 1)! is the group index among the current n (to be) sorted groups
						3. and k % (n - 1)! is the sequence number k for next iteration.    
				*/
				/*
					Example:
						1. n = 4, k = 13;
						2. 将1~4加入list中， fact = 1 * 2 * 3 * 4 = 24;
						   fact /= n 是因为，在由1，2，3，4 组成的permutation如下所示：
								   				1 + (permutations of 2, 3, 4) 
												2 + (permutations of 1, 3, 4) 
												3 + (permutations of 1, 2, 4) 
												4 + (permutations of 1, 2, 3)，
												后面三位组成的permutation数量= fact / n = 24 / 4 = 6 ....A33;
												我们只需要将 k / (fact / n) ,就可以得到第一位数字的序号。
												13 / (24 / 4) = 13 / 6 = 2, 也就是取3.
						3. k--,是因为数组下标从0开始，方便计算。
						4. k %= fact 是因为， k = 13 % 6 = 1, 
						    fact = 6 / 3 = 2;
						    k / (fact / n) : 1 / (6 / 3) = 0, 我们要在剩余的3位permutation数字中确定首位的序号，首位取0，
						    3，1
						5. 如此循环直到n == 0;
				*/
				public class Solution {
					public String getPermutation(int n, int k) {
					    LinkedList<Integer> list = new LinkedList<>();
					    for (int i = 1; i <= n; i++) {
					    	list.add(i);
					    }
					    int fact = 1;
					    for (int i = 2; i <= n; i++) {
					    	fact *= i; // factorial
					    }
					    StringBuilder sb = new StringBuilder();
					    k--;
					    while(n > 0) {
					        fact /= n;
					        sb.append(list.remove(k / fact));
					        k %= fact;
					        n--;
					    }
				    	return sb.toString();
					}
				}
			1.3 Next Permutation
				/*
					1. find the first element which A[i] <= A[i + 1], the index i start from num.length - 2;
					2. find the smallest one which make A[j] > A[i] , j start from the index i + 1 to A.length - 1;
						(notice, i + 1 ~ A.length - 1,must be a descending order, since the step 1)
					3. swap(i, j)
					4. reverse from i + 1 to nums.length - 1;
				*/
				public class Solution {
					public void nextPermutation(int[] num) {
				        if (num == null || num.length <= 1) {
				            return;
				        }
				        int i = num.length - 2;
				        while (i >= 0 && num[i + 1] <= num[i]) {
				            i--;
				        }
				        if (i >= 0) {
				        	//the adjacent number of j
				            int j = i + 1;
				            //Why we don't need to compare the value?cause from j to length - 1, that is the descending array so the last large one is smallest one
				            while (j < num.length && num[j] > num[i]) {
				                j++;
				            }
				            j--;
				            swap(num, i, j);
				        }
				        reverse(num, i + 1, num.length - 1);
				    } 
				    public void reverse(int[] num, int i, int j) {
				        while (i < j) {
				            swap(num, i++, j--);
				        }   
				    }
				}
			1.4 Previous Permutation
				/*
					思路：和next permutation完全一致的思路。
					1. 从右往左扫，找到的第一个增长pivot，这个大的数字就是即将被换掉的位置i
					2. 从这个数字往右必然是一个递增序列，往右扫，直到找到一个数比这个pivot小而且是所有右边数字中最大的（递增序列，因此最后一个比pivot大的就是）
					3. 然后交换pivot和该数字。
					4. reverse from i + 1 ~ nums.len - 1;
				*/
				public class Solution {
					public void previousPermutation(int[] num) {
				        if (num == null || num.length <= 1) {
				            return;
				        }
				        int i = num.length - 2;
				        while (i >= 0 && num[i + 1] >= num[i]) {//只有两处区别，第一处
				            i--;
				        }
				        if (i >= 0) {
				        	//the adjacent number of j
				            int j = i + 1;
				            //Why we don't need to compare the value?cause from j to length - 1, that is the descending array so the last large one is smallest one
				            while (j < num.length && num[j] < num[i]) {//第二处
				                j++;
				            }
				            j--;
				            swap(num, i, j);
				        }
				        reverse(num, i + 1, num.length - 1);
				    }   
				}
			1.5 Palindrome Permutation II
				/*
					Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.
					For example:
						Given s = "aabb", return ["abba", "baab"].
						Given s = "abc", return [].
				*/
				/*
					关键部分： 1. 判断这个String 是否能够palindrome，这里用一个hashmap，value存每一个字符出现的次数
								字符数量为奇数的字符最多只允许一个，用odd去记录奇数字符的个数，大于1就返回空list。（
							 2. 用一个list去保存hashmap里的字符，记住只需要保存一半即可，
							 	比如 aaaa bb ccc dddd,只需要保存 aabcdd, 再对这个一半的字符串进行backtracking
							 	aabcdd + c + ddcbaa.....
				*/
				public class Solution {
				    public List<String> generatePalindromes(String s) {
				        List<String> res = new ArrayList<>();
				        if (s == null || s.length() == 0) {
				            return res;
				        }
				        int odd = 0;
				        String mid = "";
				        List<Character> item = new ArrayList<>();
				        HashMap<Character, Integer> map = new HashMap<>();
				        for (int i = 0; i < s.length(); i++) {
				            char c = s.charAt(i);
				            if (map.containsKey(c)) {
				                map.put(c, map.get(c) + 1);
				            } else {
				                map.put(c, 1);
				            }
				            odd += map.get(c) % 2 != 0 ? 1 : -1;//char的数量只要是偶数就会抵消
				        }
				        if (odd > 1) {
				            return res;
				        }
				        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
				            char key = entry.getKey();
				            int val = entry.getValue();
				            
				            if (val % 2 != 0) {
				                mid += key;
				            }
				            //only need to find the first half
				            for (int i = 0; i < val / 2; i++) {
				                item.add(key);
				            }
				        }
				        helper(item, mid, new boolean[item.size()], new StringBuilder(), res);
				        return res;
				    }
				    public void helper(List<Character> item, String mid, boolean[] visited, StringBuilder sb, List<String> res) {
				        //only need to find the first half
				        if (sb.length() == item.size()) {
				            res.add(sb.toString() + mid + sb.reverse().toString());
				            sb.reverse();
				            return;
				        }
				        for (int i = 0; i < item.size(); i++) {
				            if (i != 0 && item.get(i) == item.get(i - 1) && !visited[i - 1]) {//去重
				                continue;
				            }
				            //Backtracking
				            if (!visited[i]) {
				                visited[i] = true;
				                sb.append(item.get(i));
				                helper(item, mid, visited, sb, res);
				                visited[i] = false;
				                sb.deleteCharAt(sb.length() - 1);
				            }
				        }
				    }
				}
		2. Combination
			2.1 Combinations
				/*
					Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
					For example,
					If n = 4 and k = 2, a solution is:
					[
					  [2,4],
					  [3,4],
					  [2,3],
					  [1,2],
					  [1,3],
					  [1,4],
					]
				*/
				//Recursive
				public class Solution {
				    public List<List<Integer>> combine(int n, int k) {
				        List<List<Integer>> res = new ArrayList<>();
				        if (n == 0 || k == 0 || n < k) {
				            return res;
				        }
				        List<Integer> item = new ArrayList<>();
				        helper(res, item, n, k, 1);
				        return res;
				    }
				    public void helper(List<List<Integer>> res, List<Integer> item, int n, int k, int start) {
				        if (item.size() == k) {
				            res.add(new ArrayList<>(item));
				            return;
				        }
				        for (int i = start; i <= n; i++) {
				            item.add(i);
				            helper(res, item, n, k, i + 1);
				            item.remove(item.size() - 1);
				        }
				    }
				}
				//Iterative
				public class Solution {
					public List<List<Integer>> combine(int n, int k) {
				        List<List<Integer>> res = new ArrayList<>();
				        if (k == 0 || n == 0 || k > n) {
				            return res;
				        }
				        for (int i = 1; i <= n; i++) {
				            res.add(Arrays.asList(i));
				        }
				        for (int i = 2; i <= k; i++) {
				            List<List<Integer>> tempRes = new ArrayList<>();
				            for (int j = i; j <= n; j++) {
				                for (List<Integer> item : res) {
				                    if (item.get(item.size() - 1) < j) {
				                        List<Integer> newItem = new ArrayList<>(item);
				                        newItem.add(j);
				                        tempRes.add(newItem);
				                    } 
				                }
				            }
				            res = tempRes;
				        }
				        return res;
				    }
				}
			2.2 Combination Sum I & II
				/*
					I ：candidates没有重复的数字，但是每个数字可以重复使用，因此递归下一层的时候，还是传递本身的位置
						O(k * 2^n) time.
					II：candidates有重复的数字，每个数字仅能使用一次，因此递归下一层的时候，数字从下一个开始
						O(k * 2^n) time.
				*/
				//Recursive
				public class Solution {
				    public List<List<Integer>> combinationSum(int[] candidates, int target) {
				        List<List<Integer>> res = new ArrayList<>();
				        if (candidates == null || candidates.length == 0) {
				            return res;
				        }
				        Arrays.sort(candidates);
				        List<Integer> item = new ArrayList<>();
				        helper(candidates, res, item, target, 0);
				        return res;
				    }
				    public void helper(int[] candidates, List<List<Integer>> res, List<Integer> item, int target, int start) {
				        if (target < 0) {
				            return;
				        }
				        if (target == 0) {
				            res.add(new ArrayList<>(item));
				            return;
				        }
				        for (int i = start; i < candidates.length; i++) {
				        	// if (i > start && candidates[i - 1] == candidates[i]) { ---Combination sum II
				         //        continue;
				         //    }
				            int newTarget = target - candidates[i];
				            item.add(candidates[i]);
				            helper(candidates, res, item, newTarget, i);//helper(candidates, res, item, newTarget, i + 1); ---Combination sum II
				            item.remove(item.size() - 1);
				        }
				    }
				}
				//Iterative
				/*
                    Example :{2, 3, 6, 7}
                    dp.get(i) = {candidates[j], dp.get(i - candidates[j] - 1)}
                    dp.get(0) = {[]};
                    dp.get(1) = {[2]};
                    dp.get(2) = {[3]};
                    dp.get(3) = {[2, 2]};
                    dp.get(4) = {[2, 3]};
                    dp.get(5) = {[2, 2, 2], [3, 3], [6]};
                    
                    dp.get(5) = {2 + dp.get(3)}, {3 + dp.get(2)}. {6 + dp.get(0));

                    ->  for 循环所有candidates[j], 并且使得candidates[j]要满足 candidates[j] <= item里面的第一个元素

                        dp.get(i) = {candidates[j] + dp.get(i - candidates[j] - 1) }
                */
			    public List<List<Integer>> combinationSum(int[] candidates, int target) {
			         Arrays.sort(candidates);
			         List<List<List<Integer>>> dp = new ArrayList<>();
			         for (int i = 1; i <= target; i++) {// run through all targets from 1 to target
			             List<List<Integer>> tempRes = new ArrayList<>();
			             // run through all candidates <= i
			             for (int j = 0; j < candidates.length && candidates[j] <= i; j++) {
			                 // special case when cur target is equal to cur candidate
			                 if (i == candidates[j]) {
			                     tempRes.add(Arrays.asList(candidates[j]));
			                 } else {
			                     for (List<Integer> item : dp.get(i - candidates[j] - 1)) {
			                          // if current candidate is less than the target use prev results, since we already get the result
			                         if (candidates[j] <= item.get(0)) {
			                             List<Integer> newList = new ArrayList<>();
			                             newList.add(candidates[j]);
			                             newList.addAll(item);
			                             tempRes.add(newList);
			                         }
			                     }
			                 }
			             }
			             dp.add(tempRes);
			         }
			         return dp.get(target - 1);
			    }
			2.3 Combination Sum III
				/*
					Find all possible combinations of k numbers that add up to a number n, 
					given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
						Input: 	 k = 3, n = 9
						Output:
								[[1,2,6], [1,3,5], [2,3,4]]
				*/
				public class Solution {
				    public List<List<Integer>> combinationSum3(int k, int n) {
				        List<List<Integer>> res = new ArrayList<>();
				        List<Integer> item = new ArrayList<>();
				        helper(res, item, k, n, 1);
				        return res;
				    }
				    public void helper(List<List<Integer>> res, List<Integer> item, int k, int target, int start) {
				        if (item.size() == k && target == 0) {
				            res.add(new ArrayList<>(item));
				            return;
				        }
				        for (int i = start; i <= 9; i++) {
				            int newTarget = target - i;
				            item.add(i);
				            helper(res, item, k, newTarget, i + 1);
				            item.remove(item.size() - 1);
				        }
				    }
				}

			2.4 Letter Combinations Of A Phone Number
				/*
					O(n * 4^n) OR O(n * 3^p*4^q) time
				    Explanation:
				    O(n * num of sols) time
				    n is the toString() API time complexity which is the same length as input
				  
				    num of sols:
				    O(4^n)
				    n is the length of input digits
				    
				    OR O(3^p*4^q) solutions
				    p is the number of digits which has 3 corresponding letters
				    q is the number of digits which has 4 corresponding letters
			    */
				//Recursive
			    public class Solution {
				    public List<String> letterCombinations(String digits) {
				        List<String> res = new ArrayList<>();
				        if (digits == null || digits.length() == 0) {
				            return res;
				        }
				        String[] keyboard = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
				        StringBuilder sb = new StringBuilder();
				        helper(res, digits, keyboard, sb, 0);
				        return res;
				    }
				    public void helper(List<String> res, String digits, String[] keyboard, StringBuilder sb, int index) {
				        if (sb.length() == digits.length()) {
				            res.add(sb.toString());
				            return;
				        }
				        int number = digits.charAt(index) - '0';
				        for (int i = 0; i < keyboard[number].length(); i++) {
				            sb.append(keyboard[number].charAt(i));
				            helper(res, digits, keyboard, sb, index + 1);
				            sb.deleteCharAt(sb.length() - 1);
				        }
				    }
				}
				//Iterative
				public List<String> letterCombinations(String digits) {
			        List<String> res = new LinkedList<>();
			        if (digits == null || digits.length() == 0) {
			            return res;
			        }
			        String[] keyboard = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
			        res.add("");
			        for (int i = 0; i < digits.length(); i++) {
			            String letters = keyboard[digits.charAt(i) - '2'];
			            int size = res.size();
			            for (int j = 0; j < size; j++) {
			                String item = res.remove(0);
			                for (int k = 0; k < letters.length(); k++) {
			                    res.add(item + letters.charAt(k));
			                }
			            }
			        }
			        return res;
			    }
			2.5 Factor Combinations
				/*
					Each combination's factors must be sorted ascending, for example: The factors of 2 and 6 is [2, 6], not [6, 2].
					You may assume that n is always positive.
					Factors should be greater than 1 and less than n.
					input: 32
					output:
					[
					  [2, 16],
					  [2, 2, 8],
					  [2, 2, 2, 4],
					  [2, 2, 2, 2, 2],
					  [2, 4, 4],
					  [4, 8]
					]
				*/
				//The Key Point is using n / i to recursive, unitl n / i == 1
				public class Solution {
				    public List<List<Integer>> getFactors(int n) {
				        List<List<Integer>> res = new ArrayList<>();
				        if (n == 0) {
				            return res;
				        }
				        List<Integer> item = new ArrayList<>();
				        helper(res, item, n, 2);
				        return res;
				    }
				    public void helper(List<List<Integer>> res, List<Integer> item, int n, int start) {
				        if (n < 1) {
				            return;
				        }
				        if (n == 1 && item.size() > 1) {
				            res.add(new ArrayList<>(item));
				            return;
				        }
				        for (int i = start; i <= n; i++) {
				            if (n % i == 0) {
				                item.add(i);
				                helper(res, item, n / i, i);
				                item.remove(item.size() - 1);
				            }
				        }
				    }
				}
			2.6 Find All Combinations With Length N In A String
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

		3. Subsets（子集）
			3.1 SubsetsI no duplicates
				//Recursive
				public class Solution {
				    public List<List<Integer>> subsets(int[] nums) {
				        List<List<Integer>> res = new ArrayList<>();
				        if (nums == null || nums.length == 0) {
				            return res;
				        }
				        List<Integer> item = new ArrayList<>();
				        Arrays.sort(nums);
				        res.add(new ArrayList<Integer>());
				        helper(nums, res, item, 0);
				        return res;
				    }
				    public void helper(int[] nums, List<List<Integer>> res, List<Integer> item, int start) {
				        for (int i = start; i < nums.length; i++) {
				            item.add(nums[i]);
				            res.add(new ArrayList<Integer>(item));
				            helper(nums, res, item, i + 1);
				            item.remove(item.size() - 1);
				        }
				    }
				}
				//Iteration
				/*
					res = { {} }
                    res = { {} } + { {1} }
                    res = { {}, {1} } + {{2} ,{1, 2} } = {{}, {1}, {2}, {1, 2}};
                    res = {{}, {1}, {2}, {1, 2}} + {{3}, {1,3}, {2, 3}, {1, 2, 3}};
				*/
                public class Solution {
					public List<List<Integer>> subsets(int[] nums) {
				        List<List<Integer>> res = new ArrayList<>();
				        res.add(new ArrayList<>());
				        Arrays.sort(nums);
				        int size = 0;
					    for (int i = 0; i < nums.length; i++) {
				            size = res.size();
				            for (int j = 0; j < size; j++) {
				                List<Integer> item = new ArrayList<>(res.get(j));
				                item.add(nums[i]);
				                res.add(item);
				            }
				        }
				        return res;
				    }
				}
			3.2 SubsetsII has duplicates
				//Recursive
				public class Solution {
				    public List<List<Integer>> subsetsWithDup(int[] nums) {
				        List<List<Integer>> res = new ArrayList<>();
				        if (nums == null || nums.length == 0) {
				            return res;
				        }
				        Arrays.sort(nums);
				        boolean[] visited = new boolean[nums.length];
				        List<Integer> item = new ArrayList<>();
				        res.add(new ArrayList<Integer>());
				        helper(nums, res, item, visited, 0);
				        return res;
				    }
				    public void helper(int[] nums, List<List<Integer>> res, List<Integer> item, boolean[] visited, int start) {
				        for (int i = start; i < nums.length; i++) {
				            if (i != 0 && nums[i - 1] == nums[i] && !visited[i - 1]) {
				                continue;
				            }
				            if (!visited[i]) {
				                item.add(nums[i]);
				                visited[i] = true;
				                res.add(new ArrayList<>(item));
				                helper(nums, res, item, visited, i + 1);
				                visited[i] = false;
				                item.remove(item.size() - 1);
				            }
				        }
				    }
				}
				//Iteration
				public class Solution {
					public List<List<Integer>> subsetsWithDup(int[] nums) {
				        List<List<Integer>> res = new ArrayList<>();
				        res.add(new ArrayList<>());
				        Arrays.sort(nums);
				        int size = 0;
				        int startIndex = 0;
				        for (int i = 0; i < nums.length; i++) {
				            if (i != 0 && nums[i] == nums[i - 1]) {
				                startIndex = size;
				            } else {
				                startIndex = 0;
				            }
				            size = res.size();
				            for (int j = startIndex; j < size; j++) {
				                List<Integer> item = new ArrayList<>(res.get(j));
				                item.add(nums[i]);
				                res.add(item);
				            }
				        }
				        return res;
				    }
				}




		4. Bits, Math, Others,Backtracking
			4.1 Gray Code 
				/*
					 n = 0: 0
					 n = 1: 0 -> 1
					 n = 2: 0 -> 1 -> 3 -> 2
					 n = 3: 0 -> 1 -> 3 -> 2 -> 6 -> 7 -> 5 -> 4 , 规律，每次从res中从后往前取 + 1 << (n - 1)
				*/
				//Recursive
				//O(2^n) time, O(1) space OR O(n) space if consider recursion stack
				public List<Integer> grayCode(int n) {
			        if (n == 0) {
			            List<Integer> res = new ArrayList<>();
			            res.add(0);
			            return res;
			        }
			        List<Integer> res = grayCode(n - 1);
			        int inc = 1 << n - 1;
			        for (int i = res.size() - 1; i >= 0; i--) {
			            res.add(res.get(i) + inc);
			        }
			        return res;
			    }
			    //Iteration
	    		// O(2^n) time, O(1) space
			    public List<Integer> grayCode(int n) {
			        List<Integer> res = new ArrayList<>();
			        res.add(0);
			        for (int i = 0; i < n; i++) {
			            int inc = 1 << i;
			            for (int j = res.size() - 1; j >= 0; j--) {
			                res.add(res.get(j) + inc);
			            }
			        }
			        return res;
			    }
			4.2 Generate Parentheses
				//Recursive
				/*
					(n)*h(n) time:
				    Explanation:
				    	此题时间复杂度应该是解的个数乘以每个解的长度, 解的个数对应卡特兰数h(n)的通项公式: 
					    h(n) = 2n!/(n!*n+1!) = C(2*n, n)/(n+1)
					    解的长度是2n, 也就是O(n)

				    O(n) space:
				    path的最大长度也是recursion stack的最高高度, 为2n.
				*/ 
				public class Solution {
				    public List<String> generateParenthesis(int n) {
				        List<String> res = new ArrayList<>();
				        if (n < 1) {
				            return res;
				        }
				        helper(res, "", n, n);
				        return res;
				    }
				    public void helper(List<String> res, String item, int left, int right) {
				        if (left == 0 && right == 0) {
				            res.add(item);
				            return;
				        }
				        if (left > 0) {
				            helper(res, item + '(', left - 1, right);
				        } 
				        if (left < right) {
				            helper(res, item + ')', left, right - 1);
				        }
				    }
				}
				//Iteration
				/*
					Let the "(" always at the first position, to produce a valid result, we can only put ")" in a way that there will be 
					i pairs () inside the extra () and n - 1 - i pairs () outside the extra pair.

					Let us consider an example to get clear view:
					f(0): ""
					f(1): "("f(0)")"
					f(2): "("f(0)")"f(1), "("f(1)")"
					f(3): "("f(0)")"f(2), "("f(1)")"f(1), "("f(2)")"
					So f(n) = "("f(0)")"f(n-1) , "("f(1)")"f(n-2) "("f(2)")"f(n-3) ... "("f(i)")"f(n-1-i) ... "(f(n-1)")"
				*/
				public List<String> generateParenthesis(int n) {
			        List<List<String>> lists = new ArrayList<>();
			        List<String> initList = new ArrayList<>();
			        initList.add("");
			        lists.add(initList);
			        for (int i = 1; i <= n; i++) {
			            List<String> res = new ArrayList<>();
			            for (int j = 0; j < i; j++) {
			                for (String first : lists.get(j)) {
			                    for (String second : lists.get(i - 1 - j)) {
			                        res.add("(" + first + ")" + second);
			                    }
			                }
			            }
			            lists.add(res);
			        }
			        return lists.get(lists.size() - 1);
			    }
			4.3 Restore IP Addresses
				//Recursive
				public class Solution {
				    public List<String> restoreIpAddresses(String s) {
				        List<String> res = new ArrayList<>();
				        if (s == null || s.length() == 0 || s.length() > 12) {
				            return res;
				        }
				        dfs(s, "", res, 0);
				        return res;
				    }
				    public void dfs(String s, String item, List<String> res, int start) {
				        if (start == 3 && isValid(s)) {
				            res.add(item + s);
				            return;
				        }
				        for (int i = 0; i < 3 && i < s.length(); i++) {
				            String subStr = s.substring(0, i + 1);
				            if (isValid(subStr)) {
				                dfs(s.substring(i + 1), item + subStr + '.', res, start + 1);
				            }
				        }
				    }
				    public boolean isValid(String s) {
				        if (s == null || s.length() == 0) {
				            return false;
				        }
				        if (s.charAt(0) == '0' && !s.equals("0")) {
				            return false;
				        }
				        if (Integer.valueOf(s) <= 255 && Integer.valueOf(s) >= 0) {
				            return true;
				        }
				        return false;
				    }
				}
				//Iterative
				public class Solution {
					public List<String> restoreIpAddresses(String s) {
				        List<String> res = new ArrayList<String>();
				        int len = s.length();
				        for (int i = 1; i < 4 && i < len - 2; i++) {
				            for (int j = i + 1; j < i + 4 && j < len - 1; j++) { 
				                for (int k = j + 1; k < j + 4 && k < len; k++) {
				                    String s1 = s.substring(0, i);
				                    String s2 = s.substring(i, j);
				                    String s3 = s.substring(j, k);
				                    String s4 = s.substring(k, len);
				                    if (isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4)) {
				                        res.add(s1 + "." + s2 + "." + s3 + "." + s4);
				                    }
				                }
				            }
				        }
				        return res;
				    }
				}

			4.4 Decode Ways
				/*
					Decode Ways 
					A message containing letters from A-Z is being encoded to numbers using the following mapping:
					'A' -> 1
					'B' -> 2
					...
					'Z' -> 26

					Given an encoded message containing digits, determine the total number of ways to decode it.

					For example,
					Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
					The number of ways decoding "12" is 2.
					Tags:Dp, String
				*/
				public class Solution {
					int num;
				    public int numDecodings(String s) {
				        if (s.length() == 0) {
				            return 0;
				        }
				        num = 0;
				        dfs(s);
				        return num;
				    }
				    
				    public void dfs(String s) {
				        if (s.length() == 0) {
				            num++;
				        }
				        for (int i = 0; i <= 1 && i < s.length(); i++) {
				            if (isValid(s.substring(0, i + 1))) {
				                dfs(s.substring(i + 1));
				            }
				        }
				    }
				    
				    public boolean isValid(String s) {
				        if (s.charAt(0) == '0') {
				            return false;
				        }
				        return Integer.valueOf(s) >= 1 && Integer.valueOf(s) <= 26;
				    }
				}
			4.5 Generalized Abbreviation
				/*
					Write a function to generate the generalized abbreviations of a word.
					Example:
					Given word = "word", return the following list (order does not matter):
					["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
				*/
				public class Solution {
				    public List<String> generateAbbreviations(String word){
				        List<String> res = new ArrayList<String>();
				        backtracking(res, word, 0, "", 0);

				        return res;
				    }

				    private void backtracking(List<String> res, String word, int pos, String item, int count){
				       if (pos == word.length()) {
				           if (count > 0) {
				               item += count;
				           }
				           res.add(item);
				       } else {
				           backtracking(res, word, pos + 1, item, count + 1);
				           backtracking(res, word, pos + 1, item + (count > 0 ? count : "") + word.charAt(pos), 0);
				       }
				    }
				}

		5. N-Queen, Sudo, Flip Game I & II
			5.1 N-Queens I
				//O(n) space, use matrix[i] to denote i row, matrix[i] col.
				public class Solution {
				    public List<List<String>> solveNQueens(int n) {
				        List<List<String>> res = new ArrayList<>();
				        if (n <= 0) {
				            return res;
				        }
				        List<String> item = new ArrayList<>();
				        int[] matrix = new int[n];
				        dfs(res, 0, n, matrix);
				        return res;
				    }
				    public void dfs(List<List<String>> res, int row, int n, int[] matrix) {
				        if (row == n) {
				            List<String> item = new ArrayList<>();
				            for (int i = 0; i < n; i++) {
				                StringBuilder sb = new StringBuilder();
				                for (int j = 0; j < n; j++) {
				                    if (j == matrix[i]) {
				                        sb.append('Q');
				                    } else {
				                        sb.append('.');
				                    }
				                }
				                item.add(sb.toString());
				            }
				            res.add(item);
				            return;
				        }
				        for (int i = 0; i < n; i++) {
				            matrix[row] = i;
				            if (isValid(matrix, row)) {
				                dfs(res, row + 1, n, matrix);
				            }
				        }
				    }
				    public boolean isValid(int[] matrix, int row) {
				        for (int i = 0; i < row; i++) {
				            if (matrix[i] == matrix[row] || Math.abs(matrix[i] - matrix[row]) == row - i) {
				                return false;
				            }
				        }
				        return true;
				    }
				}
			5.2 N-Queens II			
				public class Solution {
				    int count;
				    public int totalNQueens(int n) {
				        count = 0;
				        if (n == 0) {
				            return count;
				        }
				        int[] matrix = new int[n];
				        dfs(matrix, 0, n);
				        return count;
				    }
				    
				    public void dfs(int[] matrix, int row, int n) {
				        if (row == n) {
				            count++;
				            return;
				        }
				        for (int i = 0 ; i < n; i++) {
				            matrix[row] = i;
				            if (isValid(matrix, row)){
				                dfs(matrix, row + 1, n);
				            }
				        }
				    }
				    public boolean isValid(int[] matrix, int row) {
				        for (int i = 0; i < row; i++) {
				            if (matrix[i] == matrix[row] || Math.abs(matrix[i] - matrix[row]) == row - i) {
				                return false;
				            }
				        }
				        return true;
				    }
				}
			5.3 Sudo Solver
				public class Solution {
				    public void solveSudoku(char[][] board) {
				        if (board == null || board.length == 0 || board[0].length == 0) {
				            return ;
				        }
				        dfs(board);
				    }
				    public boolean dfs(char[][] board) {
				        for (int i = 0; i < board.length; i++) {
				            for (int j = 0; j < board[0].length; j++) {
				                if (board[i][j] == '.') {
				                    for (char c = '1'; c <= '9'; c++) {
				                        if (isValid(board, i, j, c)) {
				                            board[i][j] = c;
				                            if (dfs(board)) {
				                                return true;
				                            } else {
				                            	//Backtracking
				                                board[i][j] = '.';
				                            }
				                        }
				                    }
				                    return false;
				                }
				            }
				        }
				        return true;
				    }
				    public boolean isValid(char[][] board, int row, int col, char c) {
				        for (int i = 0; i < board.length; i++) {
				            if (board[i][col] == c) {
				                return false;
				            }
				        }
				        for (int i = 0; i < board.length; i++) {
				            if (board[row][i] == c) {
				                return false;
				            }
				        }
				        //Key Point, use row / 3  + 0 ~ 2  and col / 3 + 0 ~ 2, to verify the small 3 * 3 matrix
				        for (int i = (row / 3) * 3; i < (row / 3) * 3 + 3; i++) {
				            for (int j = (col / 3) * 3; j < (col / 3) * 3 + 3; j++) {
				                if (board[i][j] == c) {
				                    return false;
				                }
				            }
				        }
				        return true;
				    }
				}
			5.4 Valid Sudo
				public class Solution {
				    public boolean isValidSudoku(char[][] board) {
				        if (board == null || board.length == 0) {
				            return false;
				        }
				        return dfs(board);
				    }
				    public boolean dfs(char[][] board) {
				        for (int i = 0; i < 9; i++) {
				            for (int j = 0; j < 9; j++) {
				               if (board[i][j] != '.') {
				                   if (!isValid(board, i, j, board[i][j])){
				                       return false;
				                   }
				               }
				            }
				        }
				        return true;
				    }
				    public boolean isValid (char[][] board, int row, int col, char c) {
				    	//check whole row
				        for (int i = 0; i < 9; i++) {
				            if (i != row && board[i][col] == c) {
				                return false;
				            }
				        }
				        //check whole col
				        for (int i = 0; i < 9; i++) {
				            if (i != col && board[row][i] == c) {
				                return false;
				            }
				        }
				        //check the block
				        for (int i = (row / 3) * 3; i < (row / 3) * 3 + 3; i++) {
				            for (int j = (col / 3) * 3; j < (col / 3) * 3 + 3; j++) {
				                if ((i != row && j != col) && board[i][j] == c) {
				                    return false;
				                }
				            }
				        }
				        return true;
				    }
				}
			5.5 Flip Game I & II
				/*
				    For the time complexity, here is what I thought, let's say the length of the input string s is n, 
				    there are at most n - 1 ways to replace "++" to "--" (imagine s is all "+++..."), once we replace one "++", 
				    there are at most (n - 2) - 1 ways to do the replacement, it's a little bit like solving the N-Queens problem, 

				    the time complexity is (n - 1) x (n - 3) x (n - 5) x ..., so it's O(n!!)
				*/
				public class Solution {
				    public List<String> generatePossibleNextMoves(String s) {
				        List<String> res = new ArrayList<>();
				        if (s == null || s.length() < 2) {
				            return res;
				        }     
				        for (int i = 0; i < s.length() - 1; i++) {
				            if (s.startsWith("++", i)) {
				                res.add(s.substring(0, i) + "--" + s.substring(i + 2, s.length()));
				            }
				        }
				        // Flip Game II 用这句替换上面
				        // for (int i = 0; i < s.length() - 1; i++) {
				        //     if (s.startsWith("++", i)) {
				        //         String item = s.substring(0, i) + "--" +s.substring(i + 2);
				        //         if (!canWin(item)) {
				        //             return true;
				        //         }
				        //     }
				        // }
				        return res;
				    }
				}

			5.6 Additive Number
				/*
					Additive number is a string whose digits can form additive sequence.

					A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.

					For example:
					"112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.

					1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
					"199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
					1 + 99 = 100, 99 + 100 = 199
					Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

					Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.

					Follow up:
					How would you handle overflow for very large input integers?
				*/

				public class Solution {
				    public boolean isAdditiveNumber(String num) {
				        int n = num.length();
				        for (int i = 1; i < n; i++) {
				            for (int j = i + 1; j < n; j++) {
				                long a = parse(num.substring(0, i));
				                long b = parse(num.substring(i, j));
				                if (a == -1 || b == -1) {
				                    continue;
				                }
				                if (dfs(num.substring(j), a, b)) {
				                    return true;
				                }
				            }
				        }
				        return false;
				    }
				    public boolean dfs(String s, long a, long b) {
				        if (s.length() == 0) {
				            return true;
				        }
				        for (int i = 1; i <= s.length(); i++) {
				            long c = parse(s.substring(0, i));
				            if (c == -1) {
				                return false;
				            }
				            if (c - a == b && dfs(s.substring(i), b, c)) {
				                return true;
				            }
				        }
				        return false;
				    }
				    
				    public long parse(String s) {
				        if (!s.equals("0") && s.startsWith("0")) {
				            return -1;
				        }
				        long res = Long.parseLong(s);
				        return res;
				    }
				}

		6. Word Backtracking
			6.1 Word Search I
				public class Solution {
				    public boolean exist(char[][] board, String word) {
				        if (board == null || board.length == 0) {
				            return false;
				        }
				        if (word == null || word.length() == 0) {
				            return true;
				        }
				        boolean[][] visited = new boolean[board.length][board[0].length];
				        for (int i = 0; i < board.length; i++) {
				            for (int j = 0; j < board[0].length; j++) {
				                if (dfs(i, j, board, word, 0, visited)) {
				                    return true;
				                }
				            }
				        }
				        return false;
				    }
				    public boolean dfs(int row, int col, char[][] board, String word, int index, boolean[][] visited) {
				        if (index == word.length()) {
				            return true;
				        }
				        if (row < 0 || col < 0 || row > board.length - 1 || col > board[0].length - 1 || word.charAt(index) != board[row][col] || visited[row][col]) {
				            return false;
				        }
				        visited[row][col] = true;
				        boolean res = dfs(row - 1, col, board, word, index + 1, visited) ||
				               dfs(row + 1, col, board, word, index + 1, visited) ||
				               dfs(row, col + 1, board, word, index + 1, visited) ||
				               dfs(row, col - 1, board, word, index + 1, visited);
				        visited[row][col] = false;
				        return res;
				    }
				}
			6.2 Word Search II
				public class Solution {
				    public List<String> findWords(char[][] board, String[] words) {
				         ArrayList<String> res = new ArrayList<String>();
				        if (board == null || board.length == 0 || board[0].length == 0 || words == null) {
				            return res;
				        }
				        // HashSet<String> set = new HashSet<>();
				        Trie root = new Trie();
				        for (int i = 0; i < words.length; i++) {
				            root.insert(words[i]);
				        }
				        int m = board.length;
				        int n = board[0].length;
				        boolean[][] visited = new boolean[m][n];
				        for (int i = 0; i < m; i++) {
				            for (int j = 0; j < n; j++) {
				                dfs(res, board, i, j, visited, root, "");
				            }
				        }
				        return res;
				     }
				     public void dfs(ArrayList<String> res, char[][] board, int row, int col, boolean[][] visited, Trie root, String item) {
				         if (row < 0 || col < 0 || row > board.length - 1 || col > board[0].length - 1 || visited[row][col]) {
				             return ;
				         }
				         item = item + board[row][col];
				         if (!root.startsWith(item)) {
				             return;
				         }
				         visited[row][col] = true;
				         if (root.search(item) && !res.contains(item)) {
				             res.add(item);
				         }
				         dfs(res, board, row + 1, col, visited, root, item);
				         dfs(res, board, row - 1, col, visited, root, item);
				         dfs(res, board, row, col + 1, visited, root, item);
				         dfs(res, board, row, col - 1, visited, root, item);
				         visited[row][col] = false;
				     }
				}
			6.3 Word Break II
				/*
					s = "catsanddog", dict = ["cat", "cats", "and", "sand", "dog"].
					A solution is ["cats and dog", "cat sand dog"].
				*/
				public class Solution {
				    public List<String> wordBreak(String s, Set<String> wordDict) {
				        List<String> res = new ArrayList<>();
				        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0 || !isWordBreak(s, wordDict)) {
				            return res;
				        }
				        helper(s, wordDict, 0, "", res);
				        return res;
				    }
				    public void helper(String s, Set<String> wordDict, int start, String item, List<String> res) {
				        if (start == s.length()) {
				            res.add(item);
				            return;
				        }
				        //Key point
				        StringBuilder sb = new StringBuilder();
				        for (int i = start; i < s.length(); i++) {
				            sb.append(s.charAt(i));
				            if (wordDict.contains(sb.toString())) {
				                String newItem = item.length() > 0 ? (item + " " + sb.toString()) : sb.toString();
				                helper(s, wordDict, i + 1, newItem, res);
				            }
				        }
				    }
				}
			6.4 Regular Matching
				/*
					'.' Matches any single character.
					'*' Matches zero or more of the preceding element.
				*/
				public boolean isMatch(String s, String p) {
			        if (p.isEmpty()) {
			            return s.isEmpty();
			        }
			        //check the p.length() == 1 ,or the charAt(1) != '*', easy to do just recursive find the substring(1)
			        if (p.length() == 1 || p.charAt(1) != '*') {
			            if (s.isEmpty() || (p.charAt(0) != '.' && p.charAt(0) != s.charAt(0))) {
			                return false;
			            } else {
			                return isMatch(s.substring(1), p.substring(1));
			            }
			        }
			        //P.length() >=2 and p.charAt(1) must be equals = '*', notice the last if statement
			        while (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
			            if (isMatch(s, p.substring(2))) {
			                return true;
			            }
			            s = s.substring(1);
			        }
			        return isMatch(s, p.substring(2));
			    }
			6.5 Wildcard Matching Greedy
				/*
					'?' Matches any single character.
					'*' Matches any sequence of characters (including the empty sequence).

					主要是*的匹配问题。p每遇到一个*，就保留住当前*的坐标和s的坐标，然后s从前往后扫描，如果不成功，则s++,重新扫描。
			        1. *s==*p or *p == ? ：匹配，s++ p++。
			        2. p=='*'：也是一个匹配，但可能有0个或者多个字符用于匹配，
			        	 所以将'*'所在位置保存(star)，并且将s的位置也保存下来(ss)。
			        3. 如果不匹配，查看之前是否有'*'。没有则返回false，有则对ss的下一个位置和start之后的重新匹配。
				*/
				public class Solution {
				    public boolean isMatch(String str, String pattern) {
				        int s = 0;
				        int p = 0;
				        int match = 0;
				        int starIndex = -1;
				        while (s < str.length()) {
				            if (p < pattern.length() && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))) {
				                s++;
				                p++;
				            } else if (p < pattern.length() && pattern.charAt(p) == '*') {
				                starIndex = p;
				                match = s;
				                p++;
				            } else if (starIndex != -1) {
				                p = starIndex + 1;
				                match++;
				                s = match;
				            } else {
				                return false;
				            }
				        }
				        while (p < pattern.length() && pattern.charAt(p) == '*') {
				            p++;
				        }
				        return p == pattern.length();
				    }
				}
			6.6 Word Pattern II
				/*
					Given a pattern and a string str, find if str follows the same pattern.

					Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

					Examples:
					pattern = "abab", str = "redblueredblue" should return true.
					pattern = "aaaa", str = "asdasdasdasd" should return true.
					pattern = "aabb", str = "xyzabcxzyabc" should return false.
					Notes:
					You may assume both pattern and str contains only lowercase letters.
				*/
				public class Solution {
				    public boolean wordPatternMatch(String pattern, String str) {
				        Map<Character, String> map = new HashMap();
				        Set<String> set = new HashSet<>();
				        return isMatch(str, 0, pattern, 0, map, set);
				    }
				    public boolean isMatch(String str, int i, String pat, int j, Map<Character, String> map, Set<String> set) {
				        if (i == str.length() && j == pat.length()) {
				            return true;
				        }
				        if (i == str.length() || j == pat.length()) {
				            return false;
				        }
				        char c = pat.charAt(j);
				        if (map.containsKey(c)) {
				            String s = map.get(c);
				            if (!str.startsWith(s, i)) {
				                return false;
				            }
				            return isMatch(str, i + s.length(), pat, j + 1, map, set);
				        }
				        for (int k = i; k < str.length(); k++) {
				            String subStr = str.substring(i, k + 1);
				            if (set.contains(subStr)) {
				                continue;//subStr之前已经被用过了 这里不能再用这个匹配新的char c
				            }
				            map.put(c, subStr);
				            set.add(subStr);
				            if (isMatch(str, k + 1, pat, j + 1, map, set)) {
				                return true;
				            }
				            set.remove(map.get(c));
				            map.remove(c);
				        }
				        return false;
				    }
				}















