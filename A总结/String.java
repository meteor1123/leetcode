/*
	String 
*/



1. Math, Alphabet, Game, Other
		1.1 Game Proble
			1.1.1 Flip Game I
				/*
					For example, given s = "++++", after one move, it may become one of the following states:
					[
					  "--++",
					  "+--+",
					  "++--"
					]
					T(N) = T(N-2) + T(N-3) + [T(2) + T(N-4)] + [T(3) + T(N-5)] + ... 
					        [T(N-5) + T(3)] + [T(N-4) + T(2)] + T(N-3) + T(N-2)
					     = 2 * sum(T[i])  (i = 3..N-2)
				*/
				//I
				public class Solution {
				    public List<String> generatePossibleNextMoves(String s) {
				        List<String> res = new ArrayList<>();
				        if (s == null || s.length() < 2) {
				            return res;
				        }
				        for (int i = 0; i < s.length(); i++) {
				            if (s.startsWith("++", i)) {
				                res.add(s.substring(0, i) + "--" + s.substring(i + 2));
				            }
				        }
				        return res;
				    }
				}
			1.1.2 Flip Game II
				public class Solution {
				    public boolean canWin(String s) {
				        if (s == null || s.length() < 2) {
				            return false;
				        }
				        for (int i = 0; i < s.length() - 1; i++) {
				            if (s.startsWith("++", i)) {
				                String item = s.substring(0, i) + "--" +s.substring(i + 2);
				                //If the opponent can't win, then we win,recursively check the result
				                if (!canWin(item)) {
				                    return true;
				                }
				            }
				        }
				        return false;
				    }
				}
		1.2 Alphabet Convert Problem
			1.2.1 Integer To Roman
				//Solution1:
				public class Solution {
				    //We notice that, once we meet 4 or 40 or 400, they are difficult to solve,
				    //like IIII is not roman four, so we need to convert to IV,
				    //like VIIII is not roman nine, so we need to convert to IX
				    //注意到，每逢4就不好处理，比如IIII不可行，需要转换成IV。9也不好处理VIIII所以要转成IX，以此类推
				    //40 90 400 900 也应该预先初始化，方便遍历
				    public String intToRoman(int num) {
				        String res = "";
				        
				        //数组应该从大到小顺序排列，因为要从最大的能表示的数开始减
				        int[] value = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
				        String[] symbol = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V","IV", "I"};
				        
				        for (int i = 0; num != 0; i++) {
				            //while number is larger than the value[i], we minus the maximum value that we can minus
				            while (num >= value[i]) {
				                num -= value[i];
				                //and add  the symbol[i] to the according symbol.
				                res += symbol[i];
				            }
				        }
				        return res;
				    }
				}
				//Solution2:
				public class Solution {
					public static String intToRoman(int num) {
						//				  1000, 2000, 3000
				        String M[] = {"", "M", "MM", "MMM"};

				        //                100,  200,  300,   400, 500,  600,  700,   800,   900
				        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};

				        //                10,   20,   30,    40,  50,   60,   70,    80,    90  
				        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};

				        //                1,    2,    3,     4,   5,    6,    7,     8,     9
				        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

				        return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
				    }
				}
			1.2.2 Roman To Integer
				public class Solution {
				    public int romanToInt(String s) {
				        if (s == null || s.length() == 0) {
				            return 0;
				        }
				        int res = 0;
				        for (int i = s.length() - 1; i >= 0; i--) {
				            char c = s.charAt(i);
				            if (c == 'I') {
				                if (res >= 5) {
				                    res -= 1;
				                } else {
				                    res += 1;
				                }
				            } else if (c == 'V') {
				                res += 5;
				            } else if (c == 'X') {
				                if (res >= 50) {
				                    res -= 10;
				                } else {
				                    res += 10;
				                }
				            } else if (c == 'L') {
				                res += 50;
				            } else if (c == 'C') {
				                if (res >= 500) {
				                    res -= 100;
				                } else {
				                    res += 100;
				                }
				            } else if (c == 'D') {
				                res += 500;
				            } else if (c == 'M') {
				                res += 1000;
				            }
				        }
				        return res;
				    }
				}
			1.2.3 Integer To English Words
				public class Solution {
				    private final String[] lessThan20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
				    private final String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
				    private final String[] thousands = {"", "Thousand", "Million", "Billion"};
				    public String numberToWords(int num) {
				        if (num == 0) {
				            return "Zero";
				        }
				        int i = 0;
				        String words = "";
				        while (num > 0) {
				            if (num % 1000 != 0) {
				                words = helper(num % 1000) + thousands[i] + " " + words;
				            }
				            num /= 1000;
				            i++;
				        }
				        return words.trim();
				    }
				    private String helper(int num) {
				        if (num == 0) {
				            return "";
				        } else if (num < 20) {
				            return lessThan20[num] + " ";
				        } else if (num < 100) {
				            return tens[num / 10] + " " + helper(num % 10);
				        } else {
				            return lessThan20[num / 100] + " Hundred " + helper(num % 100);
				        }
				    }
				}

		1.3 Math Problem
			1.3.1 Add Binary
				public class Solution {
				    public String addBinary(String a, String b) {
				        if (a == null || a.length() == 0) {
				            return b;
				        }
				        if (b == null || b.length() == 0) {
				            return a;
				        }
				        StringBuilder sb = new StringBuilder();
				        int flag = 0;
				        int i = a.length() - 1;
				        int j = b.length() - 1;
				        while (i >= 0 || j >= 0) {
				            if (i >= 0) {
				                flag += a.charAt(i) - '0';
				                i--;
				            }
				            if (j >= 0) {
				                flag += b.charAt(j) - '0';
				                j--;
				            }
				            sb.insert(0, flag % 2);
				            flag = flag / 2;
				        }
				        if (flag == 1) {
				            sb.insert(0, "1");
				        }
				        return sb.toString();
				    }
				}
			1.3.2 Multiply Strings
				public class Solution {
				    public String multiply(String num1, String num2) {
				        int len1 = num1.length();
				        int len2 = num2.length();
				        int[] products = new int[num1.length() + num2.length()];
				        for (int i = len1 - 1; i >= 0; i--) {
				            for (int j = len2 - 1; j >= 0; j--) {
				                int a = num1.charAt(i) - '0';
				                int b = num2.charAt(j) - '0';
				                products[i + j + 1] += a * b;
				            }
				        }
				        int carry = 0;
				        for (int i = products.length - 1; i >= 0; i--) {
				            int temp = (carry + products[i]) % 10;
				            carry = (carry + products[i]) / 10;
				            products[i] = temp;
				        }
				        StringBuilder sb = new StringBuilder();
				        for (int num : products) {
				            sb.append(num);
				        }
				        while (sb.length() != 0 && sb.charAt(0) == '0') {
				            sb.deleteCharAt(0);
				        }
				        return sb.length() == 0 ? "0" : sb.toString();
				    }
				}
		1.4 Compare Problem
			1.4.1 Compare Version Numbers
				public class Solution {
				    public int compareVersion(String version1, String version2) {
				        String[] v1 = version1.split("\\.");
				        String[] v2 = version2.split("\\.");
				        int len = Math.max(v1.length, v2.length);
				        for (int i = 0; i < len; i++) {
				            Integer i1 = i < v1.length ? Integer.parseInt(v1[i]) : 0; 
				            Integer i2 = i < v2.length ? Integer.parseInt(v2[i]) : 0;
				            if (i1 > i2) {
				                return 1;
				            } else if (i1 < i2) {
				                return -1;
				            } 
				        }
				        return 0;
				    }
				}
			1.4.2 Compare Strings
				/*
					Compare two strings A and B, determine whether A contains all of the characters in B.
					The characters in string A and B are all Upper Case letters.
					Example
						For A = "ABCD", B = "ACD", return true.
						For A = "ABCD", B = "AABC", return false.
					Note
						The characters of B in A are not necessary continuous or ordered.
				*/
				public class Solution {
				    public boolean compareStrings(String A, String B) {
				        int[] arrA = new int[256];
				        for (int i = 0; i < A.length(); i++) {
				            arrA[A.charAt(i) - '0']++;
				        }
				        int count = 0;
				        for (int i = 0; i < B.length(); i++) {
				            if (arrA[B.charAt(i) - '0'] > 0) {
				                arrA[B.charAt(i) - '0']--;
				                count++;
				            } 
				        }
				        return count == B.length();
				    }
				}
		1.5	Others 
			1.5.1 Count And Say
			/*
				The count-and-say sequence is the sequence of integers beginning as follows:
				1, 11, 21, 1211, 111221, ...

				1 is read off as "one 1" or 11.
				11 is read off as "two 1s" or 21.
				21 is read off as "one 2, then one 1" or 1211.
			*/
			public class Solution {
			    public String countAndSay(int n) {
			        if (n == 0) {
			            return "";
			        } 
			        String res = "1";
			        int count = 1;
			        for (int i = 1; i < n; i++) {
			            StringBuilder curRes = new StringBuilder();
			            for (int i = 1; j < res.length(); j++) {
			                if (res.charAt(i - 1) == res.charAt(i)) {
			                    count++;
			                } else {
			                    curRes.append(count);
			                    curRes.append(res.charAt(i - 1));
			                    count = 1;
			                }
			            }
			            curRes.append(count);
			            curRes.append(res.charAt(res.length() - 1));
			            count = 1;
			        }
			        return res;
			    }
			}

		


2. Longest Problem
		2.1 Longest Substring With At Most Two Distinct Characters
			public class Solution {
			    public int lengthOfLongestSubstringTwoDistinct(String s) {
			        if (s == null || s.length() == 0) {
			            return 0;
			        }
			        HashMap<Character, Integer> map = new HashMap<>();
			        int maxLen = 0;
			        int start = 0;
			        for (int i = 0; i < s.length(); i++) {
			            char c = s.charAt(i);
			            if (!map.containsKey(c) && map.size() == 2) {//key point
			                int leftCharPos = s.length();
			                char leftChar = ' ';
			                for (char ch : map.keySet()) {
			                    if (map.get(ch) < leftCharPos) {
			                        leftCharPos = map.get(ch);
			                        leftChar = ch;
			                    }
			                }
			                start = leftCharPos + 1;
			                map.remove(leftChar);
			            }
			            map.put(c, i);
			            maxLen = Math.max(maxLen, i - start + 1);
			        }
			        return maxLen;
			    }
			}
		2.2 Longest Valid Parentheses
			/*
				1. 新建一个stack，用以保存括号char的序号，而不是保存内容，遍历String上的所有char， 遇到'('一律将index进栈.
			    2. 遇到')'，一律不进栈，此时判断stack是否为空如果为空
			       1） 为空说明之前已经将'('匹配完出栈，已经没有'('可以进行匹配，所以需要将start的位置 赋值为i+ 1，跳过这个')'
			       2） 如果栈非空， pop 一个'('和这个')'进行匹配， 匹配完成后判断栈是否为空，
			            2.1）如果空  maxLen = Math.max(maxLen, i - start + 1) 
			            2.2）如果栈非空  maxLen = Math.max(maxLen, i - stack.pop())
			*/
			public class Solution {
			    public int longestValidParentheses(String s) {
			        if (s == null || s.length() == 0) {
			            return 0;  
			        }
			        Stack<Integer> stack = new Stack<Integer>();
			        int start = -1;
			        int maxLen = 0;
			        //use a stack to store the unvalid parentheses
			        for (int i = 0; i < s.length(); i++) {
			            if (s.charAt(i) == '(') {
			                stack.push(i);
			            } else {
			                ////if stack is empty, it means that we already found a complete valid combo and pop to the stack
			                // or since we meet a right parenthesesm so we need to update the last index. to store the unmatched  position
			                if (stack.isEmpty()) {
			                    start = i;
			                } else {
			                    stack.pop();
			                    maxLen = stack.isEmpty() ? Math.max(maxLen, i - start) : Math.max(maxLen, i - stack.peek());
			                }
			            }
			        }
			        return maxLen;
			    }
			}
		2.3	Longest Substring Without Repeating Characters
			/*
				step1: 设定一个滑动窗口，窗口的左边界为left，右边界为right，维护一个全局最大距离变量 max = right - left + 1
				step2: 先不停将right往右边移动，并放入hashset，只要hashset中没有重复的字符，right一直++
				step3: 一旦遇到重复的字符，判断先前的 max 是否小于现在的right - left + 1，如果小于则更新max,
					   并且需要将right之前存在set里的字符remove，因为下一次要进行比较的窗口序列是从right开始，也就是 left = right
				step4: 每一次left前进只在找到重复字符时才++，
					   相反right只在没找到重复字符时才++.
			*/
			public class Solution {
			    public int lengthOfLongestSubstring(String s) {
			        if (s == null || s.length() == 0) {
			            return 0;
			        }
			        int left = 0;
			        int right = 0;
			        int maxLen = 0;
			        HashSet<Character> set = new HashSet<>();
			        while (right < s.length()) {
			            char c = s.charAt(right);
			            if (set.contains(c)) {
			                while (s.charAt(left) != c) {
			                    set.remove(s.charAt(left));
			                    left++;
			                }
			                left++;
			            } else {
			                set.add(c);
			                maxLen = Math.max(maxLen, right - left + 1);
			            }
			            right++;
			        }
			        maxLen = Math.max(maxLen, right - left);
			        return maxLen;
			    }
			}
		2.4 Longest Palindromic Substring
			/*
				中心回探法O（n*n)
			*/
			public class Solution {
			    private int low, maxLen;
			    public String longestPalindrome(String s) {
			        int len = s.length();
			        if (len < 2) {
			            return s;
			        }
			        
			        for (int i = 0; i < s.length(); i++) {
			            extendPalindrome(s, i, i);
			            extendPalindrome(s, i, i + 1);
			        }
			        return s.substring(low, low + maxLen);
			    }
			    public void extendPalindrome(String s, int i, int j) {
			        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
			            i--;
			            j++;
			        }
			        if (maxLen < j - i - 1) { 
			            low = i + 1;
			            maxLen = j - i - 1;
			        }
			    }
			}
			/*
				DP
			*/
			public String longestPalindrome(String s) {
		        if (s == null || s.length() == 0) {
		            return "";
		        }
		        //isPalin[i][j], i and j are two indices of the string, 
		        //denote whether substring from i to j is palindrome;
		        //isPalin[i][i] is always palindrome, since s.charAt(i) == s.charAt(i)
		        boolean[][] isPalind = new boolean[s.length()][s.length()];
		        String res = "";
		        int maxLen = 0;
		        for (int i = s.length() - 1; i >= 0; i--) {
		            for (int j = i; j < s.length(); j++) {
		                // j - i <= 2 --> aba or aa 肯定palindrome
		                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || isPalind[i + 1][j - 1])) {
		                    isPalind[i][j] = true;
		                    if (maxLen < j - i + 1) {
		                        maxLen = j - i + 1;
		                        res = s.substring(i, j + 1);
		                    }
		                }
		            }
		        }
		        return res;
		    }
		    /*
		    	Best manacher's Algorithm   O(n) O(n)
				http://www.felix021.com/blog/read.php?2040
				http://articles.leetcode.com/2011/11/longest-palindromic-substring-part-ii.html
			*/
			public class Solution {
			    public String longestPalindrome(String s) {
			        if (s == null || s.length() <= 1) {
			            return s;
			        }
			        s = preProcess(s);
			        int[] p = new int[s.length()];
			        int mid = 0;
			        int max = 0;
			        for (int i = 1; i < s.length() - 1; i++) {
			            p[i] = i < max ? Math.min(p[mid - (i - mid)], max - i) : 0; 
			            while (s.charAt(i - p[i] - 1) == s.charAt(i + p[i] + 1)) {
			                p[i]++;
			            }
			            if (i + p[i] > max) {
			                mid = i;
			                max = p[i];
			            }
			        }
			        int maxLen = 0;
			        mid = 0;
			        for (int i = 0; i < s.length(); i++) {
			            if (p[i] > maxLen) {
			                mid = i;
			                maxLen = p[i];
			            }
			        }
			        StringBuilder sb = new StringBuilder();
			        for (int i = mid - maxLen; i <= mid + maxLen; i++) {
			            if (s.charAt(i) != '#') {
			                sb.append(s.charAt(i));
			            }
			        }
			        return sb.toString();
			    }
			    public String preProcess(String s) {
			        StringBuilder sb = new StringBuilder();
			        sb.append('~');
			        for (int i = 0; i < s.length(); i++) {
			            sb.append('#');
			            sb.append(s.charAt(i));
			        }
			        sb.append("#$");
			        return sb.toString();
			    }
			}
		2.5 Longest Common Prefix
			// indexOf method
			public class Solution {
			    public String longestCommonPrefix(String[] strs) {
			        if (strs == null || strs.length == 0) {
			            return "";
			        }
			        String pre = strs[0];
			        for (int i = 1; i < strs.length; i++) {
			            while (strs[i].indexOf(pre) != 0) {
			                pre = pre.substring(0, pre.length() - 1);
			            }
			        }
			        return pre;
			    }
			}
			//no indexOf method
			public class Solution {
			    public String longestCommonPrefix(String[] strs) {
			        if (strs == null || strs.length == 0) {
			            return "";
			        }
			        for (int i = 0; i < strs[0].length(); i++) {
			            char c = strs[0].charAt(i);
			            for (int j = 1; j < strs.length; j++) {
			                if (i == strs[j].length() || strs[j].charAt(i) != c) {
			                    return strs[0].substring(0, i);
			                }
			            }
			        }
			        return strs[0];
			    }
			}
		2.6 Longest Consecutive Sequence
			/*
				We will use HashMap. The key thing is to keep track of the sequence length and store that in the boundary points of the sequence. 
				For example, as a result, for sequence {1, 2, 3, 4, 5}, map.get(1) and map.get(5) should both return 5.

				Whenever a new element n is inserted into the map, do two things:

				1) See if n - 1 and n + 1 exist in the map, and if so, it means there is an existing sequence next to n. 
					Variables left and right will be the length of those two sequences, while 0 means there is no sequence and n will be the boundary point later. 
					Store (left + right + 1) as the associated value to key n into the map.
				2) Use left and right to locate the other end of the sequences to the left and right of n respectively, and replace the value with the new length.
					Everything inside the for loop is O(1) so the total time is O(n). Please comment if you see something wrong. Thanks.
			*/
			public class Solution {
			    public int longestConsecutive(int[] nums) {
			        int res = 0;
			        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			        for (int i : nums) {
			            if (!map.containsKey(i)) {
			                int left = map.containsKey(i - 1) ? map.get(i - 1) : 0;
			                int right = map.containsKey(i + 1) ? map.get(i + 1) : 0;
			                
			                int sum = left + right + 1;
			                map.put(i, sum);
			                
			                res = Math.max(res, sum);
			                
			                map.put(i - left, sum);
			                map.put(i + right, sum);
			            } else {
			                continue;
			            }
			        }
			        return res;
			    }
			}
		2.7 Longest Increasing Subsequence
			//O(n*n) dp
			public class Solution {
			    public int lengthOfLIS(int[] nums) {
			        if (nums == null || nums.length == 0) {
			            return 0;
			        }
			        int[] dp = new int[nums.length];
			        int max = 0;
			        Arrays.fill(dp, 1);
			        for (int i = 0; i < nums.length; i++) {
			            for (int j = 0; j < i; j++) {
			                if (nums[i] > nums[j]) {
			                    dp[i] = Math.max(dp[j] + 1, dp[i]);
			                }
			            }
			            max = Math.max(max, dp[i]);
			        }
			        return max;
			    }
			}
			//O(n),binarySearch and Dp
			/*
				The idea is that as you iterate the sequence, you keep track of the minimum value a subsequence of given length might end with, f
				or all so far possible subsequence lengths. So dp[i] is the minimum value a subsequence of length i+1 might end with. Having this info,
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
		2.8 Longest Common Substring
			/*
				given two strings, find the longest common substring.
				Example
					Given A=“ABCD”, B=“CBCE”, return  2
				D[i][j] 定义为：两个string的前i个和前j个字符串，尾部连到最后的最长子串。
				D[i][j] = 
					1. i = 0 || j = 0 : 0
					2. s1.char[i - 1] = s2.char[j - 1] ? D[i-1][j-1] + 1 : 0;
			*/
			public class Solution {
				public int longestCommonSubstring(String A, String B) {
					int[][] dp = new int[A.length() + 1][B.length() + 1];
					int result = 0;
					for (int i = 0; i <= A.length(); i++) {
						for (int j = 0; j <= B.length(); j++) {
							if (i == 0 || j == 0) {
								res[i][j] = 0;
								continue;
							}
							if (A.charAt(i - 1) != B.charAt(j - 1)) {
								dp[i][j] = 0;
							} else {
								dp[i][j] = dp[i - 1][j - 1] + 1;
							}
							result = Math.max(result, res[i][j]);
						}
					}
					return result;
				}
			}
		2.9 Longest Subarray With Equal Number Of 0s And 1s
			/*
				Given an array containing only 0s and 1s, find the largest subarray which contain equal no of 0s and 1s. Expected time complexity is O(n).
				Examples:

				Input: arr[] = {1, 0, 1, 1, 1, 0, 0}
				Output: 1 to 6 (Starting and Ending indexes of output subarray)

				Input: arr[] = {1, 1, 1, 1}
				Output: No such subarray

				Input: arr[] = {0, 0, 1, 1, 0}
				Output: 0 to 3 Or 1 to 4
			*/
			/*
				Following is a solution that uses O(n) extra space and solves the problem in O(n) time complexity.
				Let input array be arr[] of size n and maxsize be the size of output subarray.
				1) Consider all 0 values as -1. The problem now reduces to find out the maximum length subarray with sum = 0.
				2) Create a temporary array sumleft[] of size n. Store the sum of all elements from arr[0] to arr[i] in sumleft[i]. This can be done in O(n) time.
				3) There are two cases, the output subarray may start from 0th index or may start from some other index. We will return the max of the values obtained by two cases.
				4) To find the maximum length subarray starting from 0th index, scan the sumleft[] and find the maximum i where sumleft[i] = 0.
				5) Now, we need to find the subarray where subarray sum is 0 and start index is not 0. This problem is equivalent to finding two indexes i & j in sumleft[] such that sumleft[i] = sumleft[j] and j-i is maximum. To solve this, we can create a hash table with size = max-min+1 where min is the minimum value in the sumleft[] and max is the maximum value in the sumleft[]. The idea is to hash the leftmost occurrences of all different values in sumleft[]. The size of hash is chosen as max-min+1 because there can be these many different possible values in sumleft[]. Initialize all values in hash as -1
				6) To fill and use hash[], traverse sumleft[] from 0 to n-1. If a value is not present in hash[], then store its index in hash. If the value is present, then calculate the difference of current index of sumleft[] and previously stored value in hash[]. If this difference is more than maxsize, then update the maxsize.
				7) To handle corner cases (all 1s and all 0s), we initialize maxsize as -1. If the maxsize remains -1, then print there is no such subarray.
			*/
			public class Solution {
				int findSubArray(int arr[], int n) {
				    int maxsize = -1, startindex;  // variables to store result values
				  
				    // Create an auxiliary array sunmleft[]. sumleft[i] will be sum of array 
				    // elements from arr[0] to arr[i]
				    int sumleft[n];
				    int min, max; // For min and max values in sumleft[]
				    int i;
				  
				    // Fill sumleft array and get min and max values in it. 
				    // Consider 0 values in arr[] as -1
				    sumleft[0] = ((arr[0] == 0)? -1: 1);
				    min = arr[0]; max = arr[0];
				    for (i=1; i<n; i++) {      
				        sumleft[i] = sumleft[i-1] + ((arr[i] == 0)? -1: 1);
				        if (sumleft[i] < min)
				            min = sumleft[i];
				        if (sumleft[i] > max)
				            max = sumleft[i];
				    }
				  
				    // Now calculate the max value of j - i such that sumleft[i] = sumleft[j].   
				    // The idea is to create a hash table to store indexes of all visited values.   
				    // If you see a value again, that it is a case of sumleft[i] = sumleft[j]. Check 
				    // if this j-i is more than maxsize. 
				    // The optimum size of hash will be max-min+1 as these many different values 
				    // of sumleft[i] are possible. Since we use optimum size, we need to shift
				    // all values in sumleft[] by min before using them as an index in hash[].
				    int hash[max-min+1];
				  
				    // Initialize hash table
				    for (i=0; i<max-min+1; i++)
				        hash[i] = -1;
				  
				    for (i=0; i<n; i++) {
				        // Case 1: when the subarray starts from index 0
				        if (sumleft[i] == 0) {
				           maxsize = i+1;
				           startindex = 0;
				        }
				  
				        // Case 2: fill hash table value. If already filled, then use it
				        if (hash[sumleft[i]-min] == -1) {
				            hash[sumleft[i]-min] = i;
				        } else {
				            if ( (i - hash[sumleft[i]-min]) > maxsize ) {
				                maxsize = i - hash[sumleft[i]-min];
				                startindex = hash[sumleft[i]-min] + 1;
				            }
				        }
				    }
				    if ( maxsize == -1 )
				        printf("No such subarray");
				    else
				        printf("%d to %d", startindex, startindex+maxsize-1);
				  
				    return maxsize;
				}
			}


3. Anagram Problem
		3.1 Valid Anagram
			public class Solution {
			    public boolean isAnagram(String s, String t) {
			        int[] arr = new int[26];
			        for (char c : s.toCharArray()) {
			            arr[c - 'a'] += 1;
			        }
			        for (char c : t.toCharArray()) {
			            arr[c - 'a'] -= 1;
			        }
			        for (int value : arr) {
			            if (value >= 1 || value < 0) {
			                return false;
			            }
			        }
			        return true;
			    }
			}
		3.2 Two Strings Are Anagrams
			/*
				Write a method anagram(s,t) to decide if two strings are anagrams or not.
				Example
					Given s="abcd", t="dcab", return true.

				Challenge
					O(n) time, O(1) extra space
			*/
			public class Solution {
			    public boolean anagram(String s, String t) {
			        int[] charArr = new int[256];
			        for (int i = 0; i < s.length(); i++) {
			            if (s.charAt(i) != ' ') {
			                charArr[s.charAt(i) - '0']++;
			            }
			            if (t.charAt(i) != ' ') {
			                charArr[t.charAt(i) - '0']--;
			            }
			        }
			        for (int i = 0; i < charArr.length; i++) {
			            if (charArr[i] != 0) {
			                return false;
			            }
			        }
			        return true;
			    }
			}
		3.3 Group Anagrams
			/*
				For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
				Return:
				[
				  ["ate", "eat","tea"],
				  ["nat","tan"],
				  ["bat"]
				]
			*/
			public class Solution {
			    public List<List<String>> groupAnagrams(String[] strs) {
			        List<List<String>> res = new ArrayList<>();
			        HashMap<String, List<String>> map = new HashMap<>();
			        for (String str : strs) {
			            char[] wordArr = str.toCharArray();
			            Arrays.sort(wordArr);
			            String anagram = String.valueOf(wordArr);
			            if (!map.containsKey(anagram)) {
			                map.put(anagram, new ArrayList<String>());
			            } 
			            map.get(anagram).add(str);
			        }
			        for (List<String> item : map.values()) {
			            Collections.sort(item);
			            res.add(item);
			        }
			        return res;
			    }
			}
		3.4 Anagrams LintCode
			/*
				Given an array of strings, return all groups of strings that are anagrams.
				Example
					Given ["lint", "intl", "inlt", "code"], return ["lint", "inlt", "intl"].
					Given ["ab", "ba", "cd", "dc", "e"], return ["ab", "ba", "cd", "dc"].
			*/
			public class Solution {
			    public List<String> anagrams(String[] strs) {
			        List<String> res = new ArrayList<>();
			        if (strs == null || strs.length == 0) {
			            return res;
			        }
			        HashMap<String, List<String>> map = new HashMap<>();
			        for (String s : strs) {
			            char[] arr = s.toCharArray();
			            Arrays.sort(arr);
			            String key = new String(arr);
			            if (!map.containsKey(key)) {
			                map.put(key, new ArrayList<String>());
			            }
			            map.get(key).add(s);
			        }
			        for (List<String> item : map.values()) {
			            if (item.size() > 1) {
			                res.addAll(item);
			            }
			        }
			        return res;
			    }
			}

4. Palindrome Problem
		4.1 Valid Palindrome
			/*
				Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
				For example,
				"A man, a plan, a canal: Panama" is a palindrome.
				"race a car" is not a palindrome.
			*/
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
			    public boPHolean isValid(char c) {
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

		4.2 Palindrome Permutation
			/*
				Given a string, determine if a permutation of the string could form a palindrome.
				For example,
				"code" -> False, "aab" -> True, "carerac" -> True.
			*/
			public class Solution {
			    public boolean canPermutePalindrome(String s) {
			        HashSet<Character> set = new HashSet<>();
			        for (char c : s.toCharArray()) {
			            if (set.contains(c)) {
			                set.remove(c);
			            } else {
			                set.add(c);
			            }
			        }
			        return set.size() <= 1;
			    }
			}
		4.3 Palindrome Partitioning I
			/*
				Given a string s, partition s such that every substring of the partition is a palindrome.
				Return all possible palindrome partitioning of s.
				For example, given s = "aab",
				Return
				  [
				    ["aa","b"],
				    ["a","a","b"]
				  ]
			*/
			public class Solution {
			    public List<List<String>> partition(String s) {
			        List<List<String>> res =new ArrayList<>();
			        if (s == null || s.length() == 0) {
			            return res;
			        }
			        List<String> item = new ArrayList<>();
			        helper(res, item, s, 0);
			        return res;
			    }
			    public void helper(List<List<String>> res, List<String> item, String s, int start) {
			        if (start == s.length()) {
			            res.add(new ArrayList<>(item));
			            return;
			        }
			        for (int i = start; i < s.length(); i++) {
			            String str = s.substring(start, i + 1);
			            if (isValid(str)) {
			                item.add(str);
			                helper(res, item, s, i + 1);
			                item.remove(item.size() - 1);
			            }
			        }
			    }
			    public boolean isValid(String s) {
			        int i = 0;
			        int j = s.length() - 1;
			        while (i < j) {
			            if (s.charAt(i++) != s.charAt(j--)) {
			                return false;
			            }
			        }
			        return true;
			    }
			}
		4.4 Palindrome Permutation II
			public class Solution {
			    public List<String> generatePalindromes(String s) {
			        List<String> res =new ArrayList<>();
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
			            odd += map.get(c) % 2 != 0 ? 1 : -1;
			        }
			        if (odd > 1) {
			            return res;
			        }
			        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			            char key = entry.getKey();
			            int val = entry.getValue();
			            
			            if (val %2 != 0) {
			                mid += key;
			            }
			            for (int i = 0; i < val / 2; i++) {
			                item.add(key);
			            }
			        }
			        dfs(res, item, mid, new boolean[item.size()], new StringBuilder());
			        return res;
			    }
			    public void dfs(List<String> res, List<Character> item, String mid, boolean[] visited, StringBuilder sb) {
			        if (sb.length() == item.size()) {
			            res.add(sb.toString() + mid + sb.reverse().toString());
			            sb.reverse();
			            return;
			        }
			        for (int i = 0; i < item.size(); i++) {
			            if (i != 0 && item.get(i) == item.get(i - 1) && !visited[i - 1]) {
			                continue;
			            }
			            if (!visited[i]) {
			                visited[i] = true;
			                sb.append(item.get(i));
			                dfs(res, item, mid, visited, sb);
			                visited[i] = false;
			                sb.deleteCharAt(sb.length() - 1);
			            }
			        }
			    }
			}
		4.5 Shortest Palindrome
			/*
				Given "aacecaaa", return "aaacecaaa".
				Given "abcd", return "dcbabcd".
			*/
			public class Solution {
			    public String shortestPalindrome(String s) {
			        if (s.length() <= 1) {
			            return s;
			        }
			        String cur = s + " " + new StringBuilder(s).reverse().toString();
			        int len = cur.length();
			        int[] next = new int[len];
			        int k = -1;
			        int j = 0;
			        next[0] = -1;
			        while (j < len - 1) {
			            if (k == -1 || cur.charAt(k) == cur.charAt(j)) {
			                next[++j] = ++k;
			            } else {
			                k = next[k];
			            }
			        }
			        return new StringBuilder(s.substring(next[cur.length() - 1] + 1)).reverse().toString() + s;
			    }
			}

5. String And Word Modification Problem
		5.1 Reverse And Rotate 
				5.1.1 Reverse Word In A String I
					//Using split()
					public class Solution {
						//Using split(), Reverse Word In A String I
						public String reverseWords(String s) {
					        if (s == null || s.length() == 0) {
					            return "";
					        }
					        String[] strArr = s.split(" ");
					        if (strArr.length == 0) {
					            return "";
					        }
					        StringBuilder sb = new StringBuilder();
					        for (int i = strArr.length - 1; i >= 0; i--) {
					            if (!strArr[i].equals("")) {
					                sb.append(strArr[i]).append(" ");
					            }
					        }
					        return sb.toString().trim();
					    }
					    //No using split()
					    public String reverseWords(String s) {
					        StringBuilder res = new StringBuilder();
					        for (int start = s.length() - 1; start >= 0; start--) {
					            if (s.charAt(start) == ' ') {
					                continue;
					            }
					            int end = start;
					            while (start >= 0 && s.charAt(start) != ' ') {
					                start--;
					            }
					            res.append(s.substring(start + 1, end + 1)).append(' ');
					        }
					        return res.toString().trim();
					    }
					}
				5.1.2 Reverse Word In A String II
					//Reverse Word In A String II
					public class Solution {
						public class Solution {
						    public void reverseWords(char[] s) {
						        reverse(s, 0, s.length - 1);
						        for (int i = 0, j = 0; j < s.length; j++) {
						            if (s[j] == ' ') {
						                reverse(s, i, j - 1);
						                i = j + 1;
						            }
						            if (j == s.length - 1) {
						                reverse(s, i, j);
						            }
						        }
						    }
						    public void reverse(char[] s, int start, int end) {
						        while (start < end) {
						            swap(s, start++, end--);
						        }
						    }
						    public void swap(char[] s, int i, int j) {
						        char temp = s[i];
						        s[i] = s[j];
						        s[j] = temp;
						    }
						}
					}
				5.1.3 Rotate String / Rotate Array
					/*
						Given a string and an offset, rotate string by offset. (rotate from left to right)
						Example
							Given "abcdefg".
							offset=0 => "abcdefg"
							offset=1 => "gabcdef"
							offset=2 => "fgabcde"
							offset=3 => "efgabcd"
						Challenge
							Rotate in-place with O(1) extra memory.
					*/
					/*
						Key point: 	从转动的位置开始将两个部分反转，然后再将整个字符串反转即可
									(1) reverse(str, 0, str.length - offset - 1);
							        (2) reverse(str, str.length - offset, str.length - 1);
							        (3) reverse(str, 0, str.length - 1);
							        (4) finally we can get a array which was already moved from left to right with offset position
					*/
					public class Solution {
					    public void rotateString(char[] str, int offset) {
					        if (str == null || str.length == 0) {
					            return;
					        }
					        offset = offset % str.length;
					        reverse(str, 0, str.length - offset - 1);
					        reverse(str, str.length - offset, str.length - 1);
					        reverse(str, 0, str.length - 1);
					    }
					    public void reverse(char[] str, int i, int j) {
					        while (i < j) {
					            swap(str, i++, j--);
					        }
					    }
					    public void swap(char[] str, int i, int j) {
					        char temp = str[i];
					        str[i] = str[j];
					        str[j] = temp;
					    }
					}
				5.1.4 Scramble String
					/*
						We say that "rgeat" is a scrambled string of "great".
						We say that "rgtae" is a scrambled string of "great".
						Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
					*/
					//Solution1: Recursive
					public class Solution {
					    public boolean isScramble(String s1, String s2) {
					        if (s1.length() != s2.length()) {
					            return false;
					        }
					        if (s1.equals(s2)) {
					            return true;
					        }
					        int[] count = new int[256];
					        for (int i = 0; i < s1.length(); i++) {
					            count[s1.charAt(i) - '0']++;
					            count[s2.charAt(i) - '0']--;
					        }
					        for (int i = 0; i < count.length; i++) {
					            if (count[i] != 0) {
					                return false;
					            }
					        }
					        for (int i = 1; i < s1.length(); i++) {
					            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && 
					                isScramble(s1.substring(i), s2.substring(i))) {
					                return true;
					            }
					            if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i)) && 
					                isScramble(s1.substring(i), s2.substring(0, s2.length() - i))) {
					                return true;
					            }
					        }
					        return false;
					    }
					}
				5.1.5 Interleaving String
					/*
						Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
						For example,
						Given:
						s1 = "aabcc",
						s2 = "dbbca",
						When s3 = "aadbbcbcac", return true.
						When s3 = "aadbbbaccc", return false.
					*/
					public class Solution {
					    public boolean isInterleave(String s1, String s2, String s3) {
					        int m = s1.length();
					        int n = s2.length();
					        if (m + n != s3.length()) {
					            return false;
					        }
					        boolean[][] dp = new boolean[m + 1][n + 1];
					        dp[0][0] = true;
					        for (int i = 1; i <= m; i++) {
					            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
					        }
					        for (int i = 1; i <= n; i++) {
					            dp[0][i] = dp[0][i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1);
					        }
					        for (int i = 1; i <= m; i++) {
					            for (int j = 1; j <= n; j++) {
					                dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1) ||
					                           dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
					            }
					        }
					        return dp[m][n];
					    }
					}

		5.2 Length Of Last Word
			/*
				Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
				If the last word does not exist, return 0.
				Note: A word is defined as a character sequence consists of non-space characters only.
			*/
			public class Solution {
			    public int lengthOfLastWord(String s) {
			        int end  = s.length() - 1;
			        while (end >= 0 && s.charAt(end) == ' ') {
			            end--;
			        }
			        int start = end;
			        while (start >= 0 && s.charAt(start) != ' ') {
			            start--;
			        }
			        return end - start;
			    }
			}
		5.3 Encode And Decode Strings
			/*
				encode: len + '/' + s
				decode: 1. Using indexOf('/', i) to find the slash position
						2. slash 
			*/
			public class Codec {
			    // Encodes a list of strings to a single string.
			    public String encode(List<String> strs) {
			        StringBuilder sb = new StringBuilder();
			        for (String s : strs) {
			            sb.append(s.length()).append('/').append(s);
			        }
			        return sb.toString();
			    }
			    // Decodes a single string to a list of strings.
			    public List<String> decode(String s) {
			        List<String> res = new ArrayList<>();
			        int i = 0;
			        while (i < s.length()) {
			            int slash = s.indexOf('/', i);//这里的indexOf是return从下标i开始，第一个遇见的 slash的下标，并不是指固定位置上有没有
			            int len = Integer.valueOf(s.substring(i, slash));
			            res.add(s.substring(slash + 1, slash + len + 1));
			            i = slash + len + 1;
			        }
			        return res;
			    }
			}
		5.4 Decode Ways
			//DP
			public class Solution {
			    public int numDecodings(String s) {
			        if (s == null || s.length() == 0) {
			            return 0;
			        }
			        int[] dp = new int[s.length() + 1];
			        dp[0] = 1;
			        if (isValid(s.substring(0, 1))) {
			            dp[1] = 1;
			        } else {
			            dp[1] = 0;
			        }
			        for (int i = 2; i <= s.length(); i++) {
			            if (isValid(s.substring(i - 1, i))) {
			                dp[i] += dp[i - 1];
			            }
			            if (isValid(s.substring(i - 2, i))) {
			                dp[i] += dp[i - 2];
			            }
			        }
			        return dp[s.length()];
			    }
			    public boolean isValid(String s) {
			        if (s.charAt(0) == '0') {
			            return false;
			        }
			        int code = Integer.parseInt(s);
			        return code <= 26 && code >= 1;
			    }
			}
			//Recursive
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
			    
			    public void dfs(String s){
			        if (s.length() == 0) {
			            num++;
			        }
			        for(int i = 0; i <= 1 && i < s.length(); i++){
			            if (isValid(s.substring(0, i + 1))) {
			                dfs(s.substring(i + 1));
			            }
			        }
			    }
			    
			    public boolean isValid(String s){
			        if (s.charAt(0) == '0') {
			            return false;
			        }
			        int code = Integer.parseInt(s);
			        return code >= 1 && code <= 26;
			    }
			}
		5.5 Space Replacement
			/*
				Write a method to replace all spaces in a string with %20. The string is given in a characters array, 
				you can assume it has enough space for replacement and you are given the true length of the string.
				You code should also return the new length of the string after replacement.
				Example
					Given "Mr John Smith", length = 13.
					The string after replacement should be "Mr%20John%20Smith".
				Challenge
					Do it in-place.
			*/
			public class Solution {
			    public int replaceBlank(char[] string, int length) {
			        int realLen = length;
			        for (int i = 0; i < length; i++) {
			            if (string[i] == ' ') {
			                realLen += 2;
			            }
			        }
			        int index = realLen;
			        for (int i = length - 1; i>= 0; i--) {
			            if (string[i] == ' ') {
			                string[--index] = '0';
			                string[--index] = '2';
			                string[--index] = '%';
			            } else {
			                string[--index] = string[i];
			            }
			        }
			        return realLen;
			    }
			}
		5.6 ZigZag Conversion
			/*
				The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
				(you may want to display this pattern in a fixed font for better legibility)
					P   A   H   N
					A P L S I I G
					Y   I   R
				And then read line by line: "PAHNAPLSIIGYIR"
				Write the code that will take a string and make this conversion given a number of rows:
				
				string convert(string text, int nRows);
				convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
			*/
			public class Solution {
			    public String convert(String s, int numRows) {
			        char[] c = s.toCharArray();
			        int len = s.length();
			        StringBuilder[] sb = new StringBuilder[numRows];
			        for (int i = 0; i < sb.length; i++) {
			            sb[i] = new StringBuilder();
			        }
			        int i = 0;
			        while (i < len) {
			            for (int index = 0; index < numRows && i < len; index++) {
			                sb[index].append(c[i++]);
			            }
			            for (int index = numRows - 2; index >= 1 && i < len; index--) {
			                sb[index].append(c[i++]);
			            }
			        }
			        for (int index = 1; index < sb.length; index++) {
			            sb[0].append(sb[index]);
			        }
			        return sb[0].toString();
			    }
			}
		5.7 Remove Duplicate Letters
			/*
				Remove Duplicate Letters
				Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

				Example:
				Given "bcabc"
				Return "abc"

				Given "cbacdcbc"
				Return "acdb"

				Tags: Greedy
			*/
			/*
			    核心思想： 
			            对所有的char统计出现的次数，从左边开始遍历对遍历到的字符count--，pos记录排序最小的字符出现的位置如果相同取最左边的，
			             一旦某个字符的count == 0， 则break出来将pos位置的字符加入res，并将所有的s.charAt(pos)replace成“”，并从pos + 1 开始截取字符进行下一次遍历
			*/
			public class Solution {
			    public String removeDuplicateLetters(String s) {
			        int[] arr = new int[26];
			        int pos = 0;
			        for (int i = 0; i < s.length(); i++) {
			            arr[s.charAt(i) - 'a']++;
			        }
			        for (int i = 0; i < s.length(); i++) {
			            if (s.charAt(i) < s.charAt(pos)) {
			                pos = i;
			            }
			            if (--arr[s.charAt(i) - 'a'] == 0) {
			                break;
			            }
			        }
			        return s.length() == 0 ? "" : s.charAt(pos) + 
			                removeDuplicateLetters(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
			    }
			}
		5.8 Summary Ranges
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
		



6. File Read And Write
		6.1 Read N Characters Given Read4 I
			public class Solution extends Reader4 {
			    /**
			     * @param buf Destination buffer
			     * @param n   Maximum number of characters to read
			     * @return    The number of characters read
			     */
			    public int read(char[] buf, int n) {
			        char[] buffer = new char[4];
			        int readBytes = 0;
			        int bytes = 0;
			        boolean lessthan4 = false;
			        while (!lessthan4 && readBytes < n) {
			            int size = read4(buffer);
			            if (size < 4) {
			                lessthan4 = true;
			            }
			            bytes = Math.min(n - readBytes, size);
			            for (int i = 0; i < bytes; i++) {
			                buf[readBytes + i] = buffer[i];
			            }
			            readBytes += bytes;
			        }
			        return readBytes;
			    }
    		}
		6.2 Read N Characters Given Read4 II
			public class Solution extends Reader4 {
			    /**
			     * @param buf Destination buffer
			     * @param n   Maximum number of characters to read
			     * @return    The number of characters read
			     */
			    private char[] buffer = new char[4];
			    int offset = 0;
			    int bufsize = 0;
			    public int read(char[] buf, int n) {
			        int readBytes = 0;
			        boolean lessthan4 = false;
			        int bytes = 0;
			        while (!lessthan4 && readBytes < n) {
			            if (bufsize == 0) {
			                bufsize = read4(buffer);
			                if (bufsize < 4) {
			                	lessthan4 = true;
			                }
			            }
			            
			            bytes = Math.min(n - readBytes, bufsize);
			            for (int i = 0; i < bytes; i++) {
			                buf[readBytes + i] = buffer[offset + i];
			            }
			            offset = (offset + bytes) % 4;
			            bufsize -= bytes;
			            readBytes += bytes;
			        }
			        return readBytes;
			    }
			}
		6.3 Text Justification
			/*
				Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.
				You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

				Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

				For the last line of text, it should be left justified and no extra space is inserted between words.

				For example,
				words: ["This", "is", "an", "example", "of", "text", "justification."]
				L: 16.

				Return the formatted lines as:
				[
				   "This    is    an",
				   "example  of text",
				   "justification.  "
				]
				Note: Each word is guaranteed not to exceed L in length.

				click to show corner cases.

				Corner Cases:
				A line other than the last line might contain only one word. What should you do in this case?
				In this case, that line should be left-justified.
			*/
			public class Solution {
			    public List<String> fullJustify(String[] words, int maxWidth) {
			        List<String> res = new ArrayList<>();
			        for (int i = 0, w = 0; i < words.length; i = w) {
			            int len = -1;//之所以初始化为1，是因为后面算len的时候每个word都自动包括1个空格的长度，而最后一个字符在非最后一行的时候是不需要加空格，-1就是为了中和最后一个字符的+1.
			            for (w = i; w < words.length && len + words[w].length() + 1 <= maxWidth; w++) {
			                len += words[w].length() + 1;
			            }
			            StringBuilder sb = new StringBuilder(words[i]);
			            int space = 1;
			            int extra = 0;
			            if (w != i + 1 && w != words.length) {
			                //为什么这里space需要+1？因为我们在计算len的时候已经将每个有效范围内的单词都加上一个空格的长度
			                //所以(maxWidth - len) / (w - i - 1) + 1 = maxWidth - len + w - i - 1 / w - i - 1, 
			                //                                       = maxWidth - (len - (w - i - 1)) / w - i - 1,
			                //我们注意到w - i - 1 是可以放空格的间隔， (len - (w - i - 1))才是字符除去空格后的真实长度。
			                space = (maxWidth - len) / (w - i - 1) + 1;
			                extra = (maxWidth - len) % (w - i - 1);
			            }
			            for (int j = i + 1; j < w; j++) {
			                for (int s = space; s > 0; s--) {
			                    sb.append(' ');
			                }
			                if (extra > 0) {
			                    sb.append(' ');
			                    extra--;
			                }
			                sb.append(words[j]);
			            }
			            int strLen = maxWidth - sb.length();
			            while (strLen > 0) {
			                sb.append(' ');
			                strLen--;
			            }
			            res.add(sb.toString());
			        }
			        return res;
			    }
			}


7. Word Distance Problem
		7.1 Edit Distance
			/*
				Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. 
			*/
			//2D DP
			public class Solution {
			    public int minDistance(String word1, String word2) {
			        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
			        for (int i = 0; i <= word1.length(); i++) {
			            dp[i][0] = i;
			        }
			        for (int i = 0; i <= word2.length(); i++) {
			            dp[0][i] = i;
			        }
			        for (int i = 1; i <= word1.length(); i++) {
			            for (int j = 1; j <= word2.length(); j++) {
			                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
			                    int delete = dp[i][j - 1] + 1;
			                    int insert = dp[i - 1][j] + 1;
			                    int replace = dp[i - 1][j - 1] + 1;
			                    dp[i][j] = Math.min(insert, Math.min(delete, replace));
			                } else {
			                    dp[i][j] = dp[i - 1][j - 1];
			                }
			            }
			        }
			        return dp[word1.length()][word2.length()];
			    }
			}
			//1D DP
			public class Solution {
			    public int minDistance(String word1, String word2) {
			        int[] dp = new int[word2.length() + 1];
			        for (int i = 0; i <= word2.length(); ++i) {
			        	dp[i] = i;
			        }
			        for (int i = 1; i <= word1.length(); ++i) {
			            int pre = dp[0];
			            dp[0] = i;
			            for (int j = 1; j <= word2.length(); ++j) {
			                int temp = dp[j];
			                dp[j] = Math.min(dp[j - 1], dp[j]) + 1;
			                dp[j] = Math.min(dp[j], pre + (word1.charAt(i -1) == word2.charAt(j - 1) ? 0: 1));
			                pre = temp;
			            }
			        }
			        return dp[word2.length()];
			    }
			}
		7.2 One Distance
			/*
				Given two strings S and T, determine if they are both one edit distance apart.
			*/
			public class Solution {
			    public boolean isOneEditDistance(String s, String t) {
			        int m = s.length();
			        int n = t.length();
			        if (m > n) {
			            return isOneEditDistance(t, s);
			        }
			        if (m == n) {
			            int count = 0;
			            for (int i = 0; i < m; i++) {
			                if (s.charAt(i) != t.charAt(i)) {
			                    count++;
			                }
			            }
			            return count == 1;
			        }
			        if (m == n - 1) {
			            for (int i = 0; i < m; i++) {
			                if (s.charAt(i) != t.charAt(i)) {
			                    return s.substring(i).equals(t.substring(i + 1));
			                }
			            }
			            return true;
			        }
			        return false;
			    }
			}
		7.3 Shortest Word Distance 
			7.3.1 Shortest Word Distance I
				/*
					For example,
						Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

						Given word1 = “coding”, word2 = “practice”, return 3.
						Given word1 = "makes", word2 = "coding", return 1.
					Note:
					You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
				*/
				public class Solution {
				    public int shortestDistance(String[] words, String word1, String word2) {
				        if (words == null || words.length == 0) {
				            return 0;
				        }
				        int start = -1;
				        int end = -1;
				        int minLen = Integer.MAX_VALUE;
				        for (int i = 0; i < words.length; i++) {
				            if (words[i].equals(word1)) {
				                start = i;
				            } else if (words[i].equals(word2)) {
				                end = i;
				            }
				            if (start != -1 && end != -1) {
				                minLen = Math.min(minLen, Math.abs(end - start));
				            }
				        }
				        return minLen;
				    }
				}
			7.3.2 Shortest Word Distance II
				//需要design，以适应可以多次调用查询不同word之间的最短距离
				public class WordDistance {
				    private HashMap<String, ArrayList<Integer>> map;
				    public WordDistance(String[] words) {
				        map = new HashMap<String, ArrayList<Integer>>();
				        for (int i = 0; i < words.length; i++) {
				            if (!map.containsKey(words[i])) {
				                map.put(words[i], new ArrayList<>());
				            }
				            map.get(words[i]).add(i);
				        }
				    }
				    //从O(m * n) 缩减到O(m + n)的关键在于要意识到，两个list里面的下标都已经是排好序的了，类似merge sort的merge方法
				    public int shortest(String word1, String word2) {
				        List<Integer> list1 = map.get(word1);
				        List<Integer> list2 = map.get(word2);
				        int minLen = Integer.MAX_VALUE;
				        int i = 0;
				        int j = 0;
				        while (i < list1.size() && j < list2.size()) {
				            minLen = Math.min(minLen, Math.abs(list1.get(i) - list2.get(j)));
				            if (list1.get(i) > list2.get(j)) {
				                j++;
				            } else {
				                i++;
				            }
				        }
				        return minLen;
				    }
				}

			7.3.3 Shortest Word Distance III
				//word1 和 word2 可能相等
				public int shortestWordDistance(String[] words, String word1, String word2) {
				    long dist = Integer.MAX_VALUE;
				    long i1 = dist;
				    long i2 = -dist;
				    for (int i = 0; i < words.length; i++) {
				        if (words[i].equals(word1))
				            i1 = i;
				        if (words[i].equals(word2)) {
				            if (word1.equals(word2))
				                i1 = i2;
				            i2 = i;
				        }
				        dist = Math.min(dist, Math.abs(i1 - i2));
				    }
				    return (int) dist;
				}


8. String Matching
		8.1 Basic Matching
			8.1.1 Implement StrStr()
				//Solution1: Brute Force
				public class Solution {
				    public int strStr(String haystack, String needle) {
				        if (needle == null || needle.length() == 0) {
				            return 0;
				        }
				        if (haystack == null || haystack.length() == 0) {
				            return -1;
				        }
				        int i, j;
				        for (i = 0; i <= haystack.length() - needle.length(); i++) {
				            for (j = 0; j < needle.length(); j++) {
				                if (haystack.charAt(i + j) != needle.charAt(j)) {
				                    break;
				                }
				                if (j == needle.length() - 1) {
				                    return i;
				                }
				            }
				        }
				        return -1;
				    }
				}
			8.1.2 Implement StrStr() By Kmp
				public class Solution {
					public int strStr(String haystack, String needle) {
				        if (needle == null || needle.length() == 0) {
				            return 0;
				        }
				        if (haystack == null || haystack.length() == 0) {
				            return -1;
				        }
				        int[] next = new int[needle.length()];
				        setNext(next, needle);
				        int i = 0;
				        int j = 0;
				        while (i < haystack.length()) {
				            if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
				                i++;
				                j++;
				            } else {
				                j = next[j];
				            }
				            if (j == needle.length()) {
				                return i - needle.length();
				            }
				        }
				        return -1;
				    }
				    public void setNext(int[] next, String pattern) {
				        char[] s = pattern.toCharArray();
				        int len = pattern.length();
				        int k = -1;
				        int j = 0;
				        next[0] = -1;
				        while (j < s.length - 1) {
				            if (k == -1 || s[k] == s[j]) {
				                next[++j] = ++k;
				            } else {
				                k = next[k];
				            }
				        }
				    } 
				}


9. Slide Window Problem
		9.0 Minimum Size Subarray Sum
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
		9.1 Longest Substring With At Most K Distinct Characters
			//O (n * k)
			public class Solution {
			    public int lengthOfLongestSubstringTwoDistinct(String s) {
			        if (s == null || s.length() == 0) {
			            return 0;
			        }
			        HashMap<Character, Integer> map = new HashMap<>();
			        int maxLen = 0;
			        int start = 0;
			        for (int i = 0; i < s.length(); i++) {
			            char c = s.charAt(i);
			            if (!map.containsKey(c) && map.size() == K) {
			                int leftCharPos = s.length();
			                char leftChar = ' ';
			                for (char ch : map.keySet()) {
			                    if (map.get(ch) < leftCharPos) {
			                        leftCharPos = map.get(ch);
			                        leftChar = ch;
			                    }
			                }
			                start = leftCharPos + 1;
			                map.remove(leftChar);
			            }
			            map.put(c, i);
			            maxLen = Math.max(maxLen, i - start + 1);
			        }
			        return maxLen;
			    }
			}
		9.2 Longest Substring Without Repeating Characters
			//O(n)
			public class Solution {
			    public int lengthOfLongestSubstring(String s) {
			        if (s == null || s.length() == 0) {
			            return 0;
			        }
			        int left = 0;
			        int right = 0;
			        int maxLen = 0;
			        HashSet<Character> set = new HashSet<>();
			        while (right < s.length()) {
			            char c = s.charAt(right);
			            if (set.contains(c)) {
			                while (s.charAt(left) != c) {
			                    set.remove(s.charAt(left));
			                    left++;
			                }
			                left++;
			            } else {
			                set.add(c);
			                maxLen = Math.max(maxLen, right - left + 1);
			            }
			            right++;
			        }
			        return Math.max(maxLen, right - 1 - left + 1);
			    }
			}
		9.3 Substring With Concatenation Of All Words
			/*
				You are given a string, s, and a list of words, words, that are all of the same length. 
				Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

				For example, given:
					s: "barfoothefoobarman"
					words: ["foo", "bar"]

				You should return the indices: [0,9].
				(order does not matter).
			*/
			public class Solution {
			    public List<Integer> findSubstring(String s, String[] words) {
			        List<Integer> res = new ArrayList<Integer>();
			        if (s == null || s.length() == 0 || words == null || words.length == 0) {
			            return res;
			        }
			        int wordLen = words[0].length();
			        int arrLen = words.length;
			        HashMap<String, Integer> map = new HashMap<>();
			        for (String word : words) {
			            map.put(word, !map.containsKey(word) ? 1 : map.get(word) + 1);
			        }
			        for (int i = 0; i < wordLen; i++) {
			            HashMap<String, Integer> curMap = new HashMap<>();
			            int count = 0;
			            int left = i;
			            for (int j = i; j <= s.length() - wordLen; j += wordLen) {
			                String str = s.substring(j, j + wordLen);
			                if (map.containsKey(str)) {
			                    curMap.put(str, !curMap.containsKey(str) ? 1 : curMap.get(str) + 1);
			                    if (curMap.get(str) <= map.get(str)) {
			                        count++;
			                    } else {
			                        while (curMap.get(str) > map.get(str)) {
			                            String temp = s.substring(left, left + wordLen);
			                            if (curMap.containsKey(temp)) {
			                                curMap.put(temp, curMap.get(temp) - 1);
			                                if (curMap.get(temp) < map.get(temp)) {
			                                    count--;
			                                }
			                            }
			                            left += wordLen;
			                        }
			                    }
			                    if (count == words.length) {
			                        res.add(left);
			                        String temp = s.substring(left, left + wordLen);
			                        if (curMap.containsKey(temp)) {
			                            curMap.put(temp, curMap.get(temp) - 1);
			                        }
			                        count--;
			                        left += wordLen;
			                    }
			                } else {
			                    curMap.clear();
			                    count = 0;
			                    left = j + wordLen;
			                }
			            }
			        }
			        return res;
			    }
			}
		9.4	Minimum Window Substring
			/*
				Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
				For example,
				S = "ADOBECODEBANC"
				T = "ABC"
				Minimum window is "BANC".

				Note:
				If there is no such window in S that covers all characters in T, return the empty string "".
				If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
			*/
			public class Solution {
			    public String minWindow(String s, String t) {
			        String res = "";
			        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
			            return res;
			        }
			        HashMap<Character, Integer> map = new HashMap<>();
			        for (char c : t.toCharArray()) {
			            map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
			        }
			        int minStart = 0;
			        int minLen = s.length() + 1;
			        int count = 0;
			        int left = 0;
			        for (int right = 0; right < s.length(); right++) {
			            char rightChar = s.charAt(right);
			            if (map.containsKey(rightChar)) {
			                map.put(rightChar, map.get(rightChar) - 1);
			                if (map.get(rightChar) >= 0) {
			                    count++;
			                }
			                while (count == t.length()) {
			                    if (right - left + 1 < minLen) {
			                        minLen = right - left + 1;
			                        minStart = left;
			                    }
			                    char leftChar = s.charAt(left);
			                    if (map.containsKey(leftChar)) {
			                        map.put(leftChar, map.get(leftChar) + 1);
			                        if (map.get(leftChar) > 0) {
			                            count--;
			                        }
			                    }
			                    left++;
			                }
			            }
			        }
			        if (minLen > s.length()) {
			            return "";
			        }
			        return s.substring(minStart, minStart + minLen);
			    }
			}


10. Word Ladder Problem
		10.1 Word Ladder I
			/*
				Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

				Only one letter can be changed at a time
				Each intermediate word must exist in the word list
				For example,

				Given:
				beginWord = "hit"
				endWord = "cog"
				wordList = ["hot","dot","dog","lot","log"]
				As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
				return its length 5.

				Note:
				Return 0 if there is no such transformation sequence.
				All words have the same length.
				All words contain only lowercase alphabetic characters.
			*/
			public class Solution {
			    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
			        Queue<String> queue = new LinkedList<>();
			        queue.offer(beginWord);
			        wordList.remove(beginWord);
			        int curNum = 1;
			        int nextNum = 0;
			        int level = 1;
			        while (!queue.isEmpty()) {
			            String word = queue.poll();
			            curNum--;
			            for (int i = 0; i < word.length(); i++) {
			                char[] wordArr = word.toCharArray();
			                for (char c = 'a'; c <= 'z'; c++) {
			                    wordArr[i] = c;
			                    String newWord = new String(wordArr);
			                    if (newWord.equals(endWord)) {
			                        return level + 1;
			                    }
			                    if (wordList.contains(newWord)) {
			                        queue.offer(newWord);
			                        nextNum++;
			                        wordList.remove(newWord);
			                    }
			                }
			            }
			            if (curNum == 0) {
			                curNum = nextNum;
			                nextNum = 0;
			                level++;
			            }
			        }
			        return 0;
			    }
			}
		10.2 Word Ladder II
			/*
			    Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:

			    Only one letter can be changed at a time
			    Each intermediate word must exist in the dictionary
			    For example,

			    Given:
			        start = "hit"
			        end = "cog"
			        dict = ["hot","dot","dog","lot","log"]
			    Return:
			        [
			            ["hit","hot","dot","dog","cog"],
			            ["hit","hot","lot","log","cog"]
			        ]
			    Note:
			        All words have the same length.
			        All words contain only lowercase alphabetic characters.
			    Tags:Array, Backtracking, BFS, String
			*/


			/*
			    和wordladderI 的不同之处在于，需要返回所有可能的结果
			    为什么搜索的时候保存前驱结点容易，而保存后继结点比较困难？
			    answer：因为当我们到达当前节点是，我们总是知道前驱的，但是后继却还不知道，因此后继结点的维护是比较有难度
			*/

			//Solution1  prefer!
			public class Solution {
			    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
			        List<List<String>> res = new ArrayList<>();
			        List<String> item = new ArrayList<String>();
			        Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
			        if (wordList.size() == 0) {
			            return res;
			        }
			        Queue<String> queue = new LinkedList<String>();
			        Set<String> unvisited = new HashSet<String>(wordList);//保存所有未访问过的结点
			        Set<String> visited = new HashSet<String>();//每一层上访问过的结点，每访问一层就清空
			        queue.add(beginWord);
			        unvisited.add(endWord);
			        unvisited.remove(beginWord);
			        int curNum = 1, nextNum = 0;
			        while (!queue.isEmpty()) {
			            String word = queue.poll();
			            curNum--;
			            for (int i = 0; i < word.length(); i++) {
			                StringBuilder sb = new StringBuilder(word);
			                for (char c = 'a'; c <= 'z'; c++) {
			                    sb.setCharAt(i, c);
			                    String newWord = sb.toString();
			                    if (unvisited.contains(newWord)) {
			                        //假如未访问过该结点，则该节点入队列
			                        if (visited.add(newWord)) {
			                            nextNum++;
			                            queue.add(newWord);
			                        }
			                        if (map.containsKey(newWord)) {
			                            map.get(newWord).add(word);
			                        } else {
			                            ArrayList<String> temp = new ArrayList<String>();
			                            temp.add(word);
			                            map.put(newWord, temp);
			                        }
			                    }
			                }
			            }
			            if (curNum == 0) {
			                curNum = nextNum;
			                nextNum = 0;
			                unvisited.removeAll(visited);//将所有一层上访问过的结点都从未访问结点中清空
			                visited.clear();
			            }
			        }
			        helper(endWord, beginWord, res, item, map);
			        return res;
			    }
			    //DFS 遍历hashmap，从尾部到头部开始构建最短path，from endWord -> startWord
			    public void helper(String word, String start, List<List<String>> res, List<String> item, Map<String, ArrayList<String>> map) {
			        //终止返回条件， word == start， 
			        if (word.equals(start)) {
			            item.add(0, start);
			            res.add(new ArrayList<String>(item));
			            item.remove(0);//回溯
			            return;
			        }
			        item.add(0, word);//每个单词都从头插入，因为HashMap中 Key是后继词， Value是前驱。 hit-->hot, hit就是前驱，hot是后继
			        if (map.get(word) != null) {//只要前驱不为空，递归遍历前驱的前驱的前驱。。。。
			            for (String s : map.get(word)) {
			                helper(s, start, res, item, map);
			            }
			        }
			        item.remove(0);//作用在于回溯
			    }
			}
		10.3 Longest Word Chain
			/*
				给定一个词典， 对于里面单词删掉任何一个字母，如果新单词还在词典里，就形成一个 chain：old word -> new word, 求最长长
				比如给List<String> dict = {a,ba,bca,bda,bdca} 最长是4：bdca->bda->ba->a；. more info on 1point3acres.com
			*/
			/*
				Solution2: 我用的map<Integer (length)，set<String>> 先字典里的词放到map里，然后从最长的词的set里开始recursive call，直到搜到长度是1的里面或者找不到了，int变量记录最长结果。
			*/
			public class Solution {
				public static int longestWordChain(String[] dict) {
					 if (dict == null || dict.length == 0) {
						 return 0;
					 }
					 Arrays.sort(dict, new Comparator<String>() {
						@Override
						public int compare(String o1, String o2) {
							// TODO Auto-generated method stub
							return o2.length() - o1.length();
						}
					 });
					 HashSet<String> set = new HashSet<>();
					 for (String word : dict) {
						 set.add(word);
					 }
					 HashSet<String> visited = new HashSet<>();
					 Queue<String> queue = new LinkedList<>();
					 int maxLen = 0;
					 for (String str : dict) {
						 if (maxLen >= str.length()) {
							 break;
						 }
						 if (visited.contains(str)) {
							continue; 
						 }
						 queue.offer(str);
						 int curNum = 1;
						 int nextNum = 0;
						 int level = 1;
						 while (!queue.isEmpty()) {
							 String word = queue.poll();
							 curNum--;
							 if (visited.contains(word)) {
								 continue;
							 }
							 visited.add(word);
							 HashSet<String> levelSet = new HashSet<>();
							 for (int i = 0; i < word.length(); i++) {
								 StringBuilder sb = new StringBuilder(word);
								 String temp = sb.deleteCharAt(i).toString();	
								 if (temp.equals("")){
									 break;
								 }
								 if (set.contains(temp) && !visited.contains(temp) && !levelSet.contains(temp)){				 
									 queue.offer(temp);
									 levelSet.add(temp);
									 nextNum++;
								 }
							 }
							 if (curNum == 0 && nextNum != 0) {
								 curNum = nextNum;
								 level++;
								 nextNum = 0;			  
							 }
							 maxLen = Math.max(level, maxLen);
						 } 	 
						 
					 }
					 return maxLen;
				 }
			}
