/*
	Math
*/






1. Number Problem
	1.1 Happy Number
		/*
			A happy number is a number defined by the following process: Starting with any positive integer, 
			replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), 
			or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
			Example: 19 is a happy number
				1^2 + 9^2 = 82
				8^2 + 2^2 = 68
				6^2 + 8^2 = 100
				1^2 + 0^2 + 0^2 = 1
		*/	
		public class Solution {
		    public boolean isHappy(int n) {
		        HashSet<Integer> set = new HashSet<>();
		        int sum = 0;
		        int remain = 0;
		        while (set.add(n)) {
		            sum = 0;
		            while (n > 0) {
		                remain = n % 10;
		                sum += remain * remain;
		                n = n / 10;
		            }
		            if (sum == 1) {
		                return true;
		            } else {
		                n = sum;
		            }
		        }
		        return false;
		    }
		}
	1.2 Ugly Number 
		1.2.1 Ugly Number I
			/*
				Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
			*/
			public class Solution {
			    public boolean isUgly(int num) {
			        if (num <= 0) {
			            return false;
			        }
			        for (int i = 2; i <= 5; i++) {
			            while (num % i == 0) {
			                num = num / i;
			            }
			        }
			        return num == 1;
			    }
			}
		1.2.2 Ugly Number II
			//Solution1 DP, O(n)
			public class Solution {
			    public int nthUglyNumber(int n) {
			        int[] ugly = new int[n];
			        ugly[0] = 1;
			        int index2 = 0, index3 = 0, index5 = 0;
			        int factor2 = 2, factor3 = 3, factor5 = 5;
			        for (int i = 1;i < n; i++) {
			            int min = Math.min(Math.min(factor2, factor3), factor5);
			            ugly[i] = min;
			            if (factor2 == min) {
			                factor2 = 2 * ugly[++index2];
			            }
			            if (factor3 == min) {
			                factor3 = 3 * ugly[++index3];
			            }
			            if (factor5 == min) {
			                factor5 = 5 * ugly[++index5];
			            }
			        }
			        return ugly[n - 1];
			    }
			}
			//Solution2 O(nlogk)
			public class Solution {
			    public int nthUglyNumber(int n) {
			        if (n == 1) {
			            return 1;
			        }
			        PriorityQueue<Long> pq = new PriorityQueue<>();
			        pq.offer(1l);
			        for (long i = 1; i < n; i++) {
			            long temp = pq.poll();
			            while (!pq.isEmpty() && pq.peek() == temp) {
			                pq.poll();//remove the duplicate
			            }
			            pq.add(temp * 2);
			            pq.add(temp * 3);
			            pq.add(temp * 5);
			        }
			        return pq.poll().intValue();
			    }
			}
		1.2.3 Super Ugly Number
			/*
				Write a program to find the nth super ugly number.
				Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k. 
				For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.

				Note:
				(1) 1 is a super ugly number for any given primes.
				(2) The given numbers in primes are in ascending order.
				(3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
			*/
			public class Solution {
			    public int nthSuperUglyNumber(int n, int[] primes) {
			        if (n == 1) {
			            return 1;
			        }
			        int[] res = new int[n];
			        res[0] = 1;
			        int[] index = new int[primes.length];
			        for (int i = 1; i < n; i++) {
			            res[i] = Integer.MAX_VALUE;
			            for (int j = 0; j < primes.length; j++) {
			                res[i] = Math.min(res[i], primes[j] * res[index[j]]);
			            }
			            for (int j = 0; j < primes.length; j++) {
			                if (res[i] == primes[j] * res[index[j]]) {
			                    index[j]++;
			                }
			            }
			        }
			        return res[n - 1];
			    }
			}
	1.3 Strobogrammatic Number 
		1.3.1 Strobogrammatic Number I
			/*
				A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
				Write a function to determine if a number is strobogrammatic. The number is represented as a string.
				For example, the numbers "69", "88", and "818" are all strobogrammatic.
			*/
				public class Solution {
				    public boolean isStrobogrammatic(String num) {
				        if (num == null || num.length() == 0) {
				            return false;
				        }
				        HashMap<Character, Character> map = new HashMap<>();
				        map.put('6', '9');
				        map.put('9', '6');
				        map.put('8', '8');
				        map.put('0', '0');
				        map.put('1', '1');
				        int start = 0;
				        int end = num.length() - 1;
				        while (start <= end) {
				            char c1 = num.charAt(start);
				            char c2 = num.charAt(end);
				            if (!map.containsKey(c1) || !map.containsKey(c2)) {
				                return false;
				            } else if (map.get(c1) == c2) {
				                start++;
				                end--;
				            } else {
				                return false;
				            }
				        }
				        return true;
				    }
				}
		1.3.2 Strobogrammatic Number II
				public class Solution {
				    public List<String> findStrobogrammatic(int n) {
				        return helper(n, n);
				    }
				    List<String> helper(int n, int m) {
				        if (n == 0) {
				            return new ArrayList<String>(Arrays.asList(""));
				        }
				        if (n == 1) {
				            return new ArrayList<String>(Arrays.asList("0", "1", "8"));//asList 的只能读，不能改
				        }
				        List<String> list = helper(n - 2, m);//why n - 2? since every time we add two number, one from head,the other from end
				        List<String> res = new ArrayList<String>();
				        for (int i = 0; i < list.size(); i++) {
				            String s = list.get(i);
				            // n == m, 就是最外层的时候，不能在最外围加上0， 会导致出现010这样的无效数字
				            if (n != m) {
				                res.add("0" + s + "0");
				            }
				            res.add("1" + s + "1");
				            res.add("6" + s + "9");
				            res.add("8" + s + "8");
				            res.add("9" + s + "6");
				        }
				        return res;
				    }
				}
		1.3.3 Strobogrammatic Number III
				public class Solution {
				    public int strobogrammaticInRange(String low, String high) {
				        int count = 0;
				        List<String> res = new ArrayList<>();
				        for (int i = low.length(); i <= high.length(); i++) {
				            res.addAll(helper(i, i));
				        }
				        for (String num : res) {
				            if  (
					            	(num.length() == low.length() && num.compareTo(low) < 0) || 
					            	(num.length() == high.length() && num.compareTo(high) > 0)
				            	) {
				                continue;
				            }
				            count++;
				        }
				        return count;
				    }
				    public List<String> helper(int cur, int max) {
				        if (cur == 0) {
				            return new ArrayList<String>(Arrays.asList(""));
				        }
				        if (cur == 1) {
				            return new ArrayList<String>(Arrays.asList("1", "8", "0"));
				        }
				        List<String> center = helper(cur - 2, max);
				        List<String> res = new ArrayList<String>();
				        for (int i = 0; i < center.size(); i++) {
				            String tmp = center.get(i);
				            if (cur != max) {
				                res.add("0" + tmp + "0");
				            }
				            res.add("1" + tmp + "1");
				            res.add("6" + tmp + "9");
				            res.add("8" + tmp + "8");
				            res.add("9" + tmp + "6");
				        }
				        return res;
				    }
				}
		1.4 Missing Number
			/*
				Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
				For example,
				Given nums = [0, 1, 3] return 2.

				Note:
				Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
			*/
			public class Solution {
				//这里的数和index是对应的， 除了missing number，将所有的数与其对应的index进行xor操作
			    public int missingNumber(int[] nums) {
			        int xor = 0;
			        int i = 0;
			        for (; i < nums.length; i++) {
			            xor = xor ^ i ^ nums[i];
			         // nums[0]=0 nums[1]=2  nums[2]=3    i=3
			        }// 形如 0 ，2 ， 3 = 0 ^ (0 ^ 0) ^ (1 ^ 2) ^ (2 ^ 3)   ^  3
			        return xor ^ i;
			    }
			}
		1.5 Palindrome Number
			public class Solution {
			    public boolean isPalindrome(int x) {
			        if (x < 0) {
			            return false;
			        }
			        int div = 1;
			        while (x / div >= 10) {
			            div *= 10;
			        }
			        while (x > 0) {
			            int left = x / div;
			            int right = x % 10;
			            if (left != right) {
			                return false;
			            }
			            x = (x % div) / 10;
			            div = div / 100;
			        }
			        return true;
			    }
			}
		1.6 Perfect Squares
			/*
				Perfect Squares
				Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

				For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
			*/
			/*
				动态规划（Dynamic Programming）
				时间复杂度：O(n * log n)
				初始化，令dp[y * y] = 1，其中y * y <= n

				状态转移方程：
					dp[x + y * y] = min(dp[x + y * y], dp[x] + 1);
			   	 	dp[i] = dp[i - j * j] + 1;
			*/
			public class Solution {
			    //Solution1 prefer
			    public int numSquares(int n) {
			        int[] dp = new int[n + 1];
			        dp[1] = 1;
			        for(int i = 2; i <= n; i++) {
			            dp[i] = Integer.MAX_VALUE;
			            for (int j = 1; j <= Math.sqrt(i); j++) {
			                dp[i] = Math.min(dp[i], dp[i - j*j] + 1);
			            }
			        }
			        return dp[n];
			    }

			}


2. Math Operation
		2.1 Divide Two Integers
			/*
				Divide two integers without using multiplication, division and mod operator.
				If it is overflow, return MAX_INT.
			*/
			/*
				Suppose we want to divide 15 by 3, so 15 is dividend and 3 is divisor. Well, 
				division simply requires us to find how many times we can subtract the divisor from the the dividend without making the dividend negative.

				Let's get started. We subtract 3 from 15 and we get 12, which is positive. Let's try to subtract more. 
				Well, we shift 3 to the left by 1 bit and we get 6. Subtracting 6 from 15 still gives a positive result.
				Well, we shift again and get 12. We subtract 12 from 15 and it is still positive. We shift again, obtaining 24 and we know we can at most subtract 12. 
				ell, since 12 is obtained by shifting 3 to left twice, we know it is 4 times of 3. 
				How do we obtain this 4? Well, we start from 1 and shift it to left twice at the same time. We add 4 to an answer (initialized to be 0). 
				In fact, the above process is like 15 = 3 * 4 + 3. We now get part of the quotient (4), with a remainder 3.
				
				Then we repeat the above process again. We subtract divisor = 3 from the remaining dividend = 3 and obtain 0. We know we are done. No shift happens, so we simply add 1 << 0 to the answer.

				Now we have the full algorithm to perform division.

				According to the problem statement, we need to handle some exceptions, such as overflow.

				Well, two cases may cause overflow:

				divisor = 0;
				dividend = INT_MIN and divisor = -1 (because abs(INT_MIN) = INT_MAX + 1).
			*/
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
		2.2 Sqrt
			/*
				Implement int sqrt(int x).

				Compute and return the square root of x.
			*/
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
		2.3 Pow
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
		2.3 Count Primes
			/*
				Description:
					Count the number of prime numbers less than a non-negative number, n.
			*/
				public class Solution {
				    public int countPrimes(int n) {
				        boolean[] notPrimes = new boolean[n];
				        int count = 0;
				        for (int i = 2; i < n; i++) {
				            if (!notPrimes[i]) {
				                count++;
				                for (int j = 2 * i; j < n; j = j + i) {
				                    notPrimes[j] = true;
				                }
				            }
				        }
				        return count;
				    }
				}
		2.4 Add Digits
			/*
				Add Digits 
				Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

			    For example:

			    Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

			    Follow up:
			    Could you do it without any loop/recursion in O(1) runtime?
			*/
			    //Solution1:prefer
			    public class Solution {
				    public int addDigits(int num) {
				        return 1 + (num - 1) % 9;
				    }
				}
				//Solution2:
				public int addDigits(int num) {
			        if (num <= 9 & num >= 0) {
			            return num;
			        }
			        int sum = 0;
			        while (num > 0) {
			            sum += num % 10;
			            num = num / 10;
			        }
			        return addDigits(sum);
			    }
		2.5 Power Of Two
			public class Solution {
			    /*
			        Power of 2 means only one bit of n is '1', so use the trick n&(n-1)==0 to judge whether that is the case
			    */
			    public boolean isPowerOfTwo(int n) {
			        return n > 0 && (n & (n - 1)) == 0;
			    }
			}
		2.6 Power Of Three
			//问一个数是不是三的幂
			//Iterative
			public class Solution {
				public boolean isPowerOfThree(int n) {
			        if (n > 1) {
			            while (n % 3 == 0) {
			                n = n / 3;
			            }
			        }
			        return n == 1;
			   }
			}
			//Recursive 
			public boolean isPowerOfThree(int n) {
				return n > 0 && (n == 1 || (n % 3 == 0 && isPowerOfThree(n/3)));
		    }
		    //Math,Best		 
		    public boolean isPowerOfThree(int n) {
			    return (Math.log10(n) / Math.log10(3)) % 1 == 0;
			}
		2.6 Plus One
			/*
				Given a non-negative number represented as an array of digits, plus one to the number.
				The digits are stored such that the most significant digit is at the head of the list.
			*/
			public class Solution {
			    public int[] plusOne(int[] digits) {
			        int len = digits.length;
			        for (int i = len - 1; i >= 0; i--) {
			            if (digits[i] == 9) {
			                digits[i] = 0;
			            } else {
			                digits[i]++;
			                return digits;
			            }
			        }
			        int[] res = new int[len + 1];
			        res[0] = 1;
			        return res;
			    }
			}
		2.7 Reverse Integer
			/*
				Reverse digits of an integer.
					Example1: x = 123, return 321
					Example2: x = -123, return -321
			*/
			public class Solution {
			    public int reverse(int x) {
			        long res = 0;
			        while (x != 0) {
			            res = res * 10 + x % 10;
			            if(res > Integer.MAX_VALUE || res < Integer.MIN_VALUE){
			                return 0;
			            } 
			            x = x / 10;
			        }
			        return (int)res;
			    }
			}
		2.7 Factorial Trailing Zeroes
			/*
				完全取决与 在阶乘中 含有5这个因子的数的个数！
			*/
			public class Solution {
			    public int trailingZeroes(int n) {
			        int res = 0;
			        while (n > 0) {
			            n = n / 5;
			            res += n;
			        }
			        return res;
			    }
			}
		2.8 Fraction To Recurring Decimal
			/*
				Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

				If the fractional part is repeating, enclose the repeating part in parentheses.

				For example,

				Given numerator = 1, denominator = 2, return "0.5".
				Given numerator = 2, denominator = 1, return "2".
				Given numerator = 2, denominator = 3, return "0.(6)".
			*/
			public class Solution {
			    public String fractionToDecimal(int numerator, int denominator) {
			        if (numerator == 0) {
			            return "0";
			        }
			        if (denominator == 0) {
			            return "";
			        }
			        String ans = "";
			        
			        if (((numerator < 0) && (denominator > 0)) || ((numerator > 0) && (denominator < 0)) ) {//按位异或
			            ans += "-";
			        }
			        
			        long num = numerator;
			        long den = denominator;
			        
			        num = Math.abs(num);
			        den = Math.abs(den);
			        
			        long tempRes = num / den;
			        res += String.valueOf(tempRes);
			        
			        long rem = (num % den) * 10;
			        if (rem == 0) {
			            return res;
			        }
			        
			        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
			        res += ".";
			        while (rem != 0) {
			            if (map.containsKey(rem)) {
			                int start = map.get(rem);
			                String part1 = res.substring(0, start);
			                String part2 = res.substring(start, res.length());
			                res = part1 + "(" + part2 + ")";
			                return res;
			            }
			            
			            map.put(rem, res.length());
			            tempRes = rem / den;
			            res += tempRes + "";
			            rem = (rem % den) * 10;
			        }
			        return res;
			    }
			}
		2.9 String To Integer (atoi)
			/*
			    1. null or empty string
			    2. white spaces
			    3. +/- sign
			    4. calculate real value
			    5. handle min & max
			 */
		    public int atoi(String str) {
		        //corner case
		        if (str == null || str.length() < 1)
		            return 0;
		        
		        //remove the leading and trailing white blank
		        str = str.trim();
		        
		        //determine the flag of '+' or '-'
		        char flag = '+';
		        int i = 0;
		        if (str.charAt(i) == '-') {
		            flag = '-';
		            i++;
		        } else if (str.charAt(i) == '+') {
		            i++;
		        }
		        
		        //Using double to store the result
		        double result = 0;
		        
		        //determine every char in str
		        //put a constraint '0' < str.charAt(i) <'9'
		        while (i < str.length() && str.charAt(i) <= '9' && str.charAt(i) >= '0') {
		            //example: 251 = 0*10 + 2-> 2*10 + 5-> 25*10 + 1 = 251
		            result = result * 10 +(str.charAt(i) - '0'); 
		            i++;
		        }
		        
		        //determine the flag
		        if (flag == '-')
		            result = -result;
		            
		        //corner case of overflow
		        if (result >= Integer.MAX_VALUE)
		            return Integer.MAX_VALUE;
		        if (result <= Integer.MIN_VALUE)
		            return Integer.MIN_VALUE;
		        
		        //dont forget to turn to Integer
		        return (int)result;
		    }

		2.10 Basic Calculator
			/*
				Implement a basic calculator to evaluate a simple expression string.
				The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

				You may assume that the given expression is always valid.

				Some examples:
				"1 + 1" = 2
				" 2-1 + 2 " = 3
				"(1+(4+5+2)-3)+(6+8)" = 23
			 */
			/*
			    Principle:
			        (Sign before '+'/'-') = (This context sign);
			        (Sign after '+'/'-') = (This context sign) * (1 or -1);
			    Algorithm:
			        Start from +1 sign and scan s from left to right;
			        if c == digit: This number = Last digit * 10 + This digit;
			        if c == '+': Add num to result before this sign; This sign = Last context sign * 1; clear num;
			        if c == '-': Add num to result before this sign; This sign = Last context sign * -1; clear num;
			        if c == '(': Push this context sign to stack;
			        if c == ')': Pop this context and we come back to last context;
			        Add the last num. This is because we only add number after '+' / '-'.
			*/
			public class Solution {
			    public int calculate(String s) {
			        if (s == null) {
			            return 0;
			        }
			        int res = 0;
			        int sign = 1;
			        int num = 0;
			        Stack<Integer> stack = new Stack<Integer>();
			        stack.push(sign);
			        for (int i = 0; i < s.length(); i++) {
			            char c = s.charAt(i);
			            if (c >= '0' && c <= '9') {
			                num = num * 10 + (c - '0');
			            } else if (c == '+' || c == '-') {
			                res += sign * num;
			                sign = stack.peek() * (c == '+' ? 1 : -1);
			                num = 0;
			            } else if (c == '(') {
			                stack.push(sign);
			            } else if (c == ')') {
			                stack.pop();
			            }
			        }
			        res += sign * num;
			        return res;
			    }
			}
		2.11 Add Two Numbers
			/*
				You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

				Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
				Output: 7 -> 0 -> 8
			 */
			 public class Solution {
			     public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
			         if (l1 == null) {
			             return l2;
			         }
			         if (l2 == null) {
			             return l1;
			         }
			         ListNode l3 = new ListNode(0);
			         ListNode newHead = l3;
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
			             l3 = l3.next;
			             sum = sum / 10;
			         }
			         if (sum == 1) {
			             l3.next = new ListNode(1);
			         }
			         return newHead.next;
			     }
			 }


3. Geometry
		3.1 Rectangle Area
			/*
				Rectangle Area

				Find the total area covered by two rectilinear rectangles in a 2D plane.
				Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.

										  |
			 						______|________(C,D) : (3, 4)
									|	  |       |
									|     |       |
									|	  |_______|_____________ (G, H) : (9, 2)
									|	  |		  |			   |
						____________|_____|_______|____________|_________
					       (A, B):(-3, 0) |	O:(0,0)  		   |
										  |____________________|
										  |(E, F) :(0, -1)
										  |
										  |
										  |
										  |

				Assume that the total area is never beyond the maximum possible value of int.
			*/
			public class Solution {
			    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
			        int areaA = Math.abs(C - A) * Math.abs(D - B);//求出ABCD这个矩形面积
			        int areaB = Math.abs(G - E) * Math.abs(H - F);//求出EFGH这个矩形面积
			    
			        if (A >= G || B >= H || C <= E || D <= F) {
			            return areaA + areaB;//不重叠的情况
			        }
			        int length = Math.min(C, G) - Math.max(A, E);//C,G 决定右边界， A,E 决定左边界
			        int height = Math.min(D, H) - Math.max(B, F);//D,H 决定上边界， B,F 决定下边界
			        return areaA + areaB - length * height;
			    }
			}
		3.2 Maximal Rectangle
			/*
				Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
			*/
			public class Solution {
			    public int maximalRectangle(char[][] matrix) {
			        if (matrix.length == 0 || matrix[0].length == 0) {
			            return 0;
			        }
			        int[] height = new int[matrix[0].length];
			        int maxArea = 0;
			        for (int i = 0; i < matrix.length; i++) {
			            for (int j = 0; j < matrix[0].length; j++) {
			                if (matrix[i][j] == '1') {
			                    height[j]++;
			                } else if (matrix[i][j] == '0') {
			                    height[j] = 0;
			                }
			            }
			            maxArea = Math.max(maxArea, findMaxAreaInHistogram(height));
			        }
			        return maxArea;
			    }
			    
			    public int findMaxAreaInHistogram(int[] height) {
			        int[] h = new int[height.length + 1];
			        h = Arrays.copyOf(height, height.length + 1);
			        int maxArea = 0;
			        int i = 0;
			        Stack<Integer> stack = new Stack<>();
			        while (i < h.length) {
			            if (stack.isEmpty() || h[i] >= h[stack.peek()]) {
			                stack.push(i);
			                i++;
			            } else {
			                int top = stack.pop();
			                maxArea = Math.max(maxArea, h[top] * (stack.isEmpty() ? i : i - stack.peek() - 1));
			            }
			        }
			        return maxArea;
			    }
			}





5. Others
		5.1 Excel Sheet Column Title
			/*
				char to int --> 
					1.直接和int进行运算
					2. c - '0' ,比如'9' - '0'
					
				int to char --> 
					1.(char)
					2. + 'a' 就是对应的1 ： a 
			*/
			/*
				Given a positive integer, return its corresponding column title as appear in an Excel sheet.
				For example:

				    1 -> A
				    2 -> B
				    3 -> C
				    ...
				    26 -> Z
				    27 -> AA
				    28 -> AB 
			*/
			public class Solution {
			    public String convertToTitle(int n) {
			        StringBuilder sb = new StringBuilder();
			        while (n > 0) {
			            n--;
			            sb.insert(0, (char)(n % 26 + 'A'));
			            n = n / 26;
			        }
			        return sb.toString();
			    }
			}
		5.2 Excel Sheet Column Number
			/*
				Given a column title as appear in an Excel sheet, return its corresponding column number.
				For example:

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
			            char c = s.charAt(i);
			            res = 26 * res + c % 64;
			        }
			        return res;
			    }
			}
		5.3 Max Points On A Line 
			/*
				Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
			 */
			public class Solution {
			    public int maxPoints(Point[] points) {
			        if (points == null || points.length == 0) {
			            return 0;
			        }
			        
			        int max = 1;
			        for (int i = 0; i < points.length; i++) {
			            HashMap<Float, Integer> map = new HashMap<>();
			            int samePoint = 0;
			            int localMax = 1;
			            for (int j = 0; j < points.length; j++) {
			                if (i == j) {
			                    continue;
			                }
			                if (points[j].x == points[i].x && points[j].y == points[i].y) {
			                    samePoint++;
			                    continue;
			                }
			                float slope = ((float)(points[i].y - points[j].y) / (points[i].x - points[j].x));
			                if (map.containsKey(slope)) {
			                    map.put(slope, map.get(slope) + 1);
			                } else {
			                    map.put(slope, 2);
			                }
			            }
			            
			            for (int count : map.values()) {
			                localMax = Math.max(localMax, count);
			            }
			            localMax += samePoint;
			            max = Math.max(localMax, max);
			        }
			        return max;
			    }
			}
