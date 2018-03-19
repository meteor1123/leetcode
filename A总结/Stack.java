/*
	Dynamic Programming
*/



1. Parentheses Problem
		1.1	Valid Parentheses
			/*
				Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
				determine if the input string is valid.The brackets must close in the correct order, 
				"()" and "()[]{}" are all valid but "(]" and "([)]" are not.
			*/
			public class Solution {
			    public boolean isValid(String s) {
			        if (s == null || s.length() == 0) {
			            return true;
			        }
			        Stack<Character> stack = new Stack<>();
			        for (int i = 0; i < s.length(); i++) {
			            char c = s.charAt(i);
			            if (c == '(' || c == '[' || c == '{') {
			                stack.push(c);
			            } else {
			                if (stack.isEmpty()) {
			                    return false;
			                }
			                char peek = stack.pop();
			                if (peek == '(' && c == ')') {
			                    continue;
			                } else if (peek == '[' && c == ']') {
			                    continue;
			                } else if (peek == '{' && c == '}') {
			                    continue;
			                } else {
			                    return false;
			                }
			            }
			        }
			        if (!stack.isEmpty()) {
			            return false;
			        }
			        return true;
			    }
			}

		1.2 Generate Parentheses
			//Recursive
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
		    //Iteration
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
		1.3	Longest Valid Parentheses
			/*
				Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
				For "(()", the longest valid parentheses substring is "()", which has length = 2.Another example is ")()())", 
				where the longest valid parentheses substring is "()()", which has length = 4.
			*/
			public class Solution {
			    public int longestValidParentheses(String s) {
			        if (s == null || s.length() == 0) {
			            return 0;  
			        }
			        Stack<Integer> stack = new Stack<Integer>();
			        int start = -1;
			        int maxLen = 0;
			    
			        for (int i = 0; i < s.length(); i++) {
			            if (s.charAt(i) == '(') {
			                stack.push(i);
			            } else {
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
		1.4 Balance Parentheses
			public class Solution  {
				public String balance(String s) {
					//use stack to store the position information of parentheses
					//at last , if the stack is not empty, 
					//that's mean these parentheses need to delete from tht orginial string
					Stack<Integer> stack = new Stack<>();
					StringBuilder sb = new StringBuilder(s);
					
					for (int i=0; i< s.length(); i++) {
						int c = s.charAt(i);
						//when the stack is empty and c equals '(', stack push the index of String
						if (stack.isEmpty() || c == '(') {
							stack.push(i);
						} else {
							int top = stack.peek();
							if (s.charAt(top) == ')') {
								stack.push(i);
							} else {
								stack.pop();
							}
						}
					}
					
					while (!stack.isEmpty()) {
						sb.deleteCharAt(stack.pop());
					}
					
					return sb.toString();
				}
			}
		1.5 Remove Invalid Parentheses
			/*
				"()())()" -> ["()()()", "(())()"]
				"(a)())()" -> ["(a)()()", "(a())()"]
				")(" -> [""]
			*/
			public class Solution {
			    public List<String> removeInvalidParentheses(String s) {
			        List<String> res = new ArrayList<>();
			        if (s == null) {
			            return res;
			        }
			        Set<String> visited = new HashSet<>();
			        Queue<String> queue = new LinkedList<>();
			        queue.add(s);
			        visited.add(s);
			        boolean found = false;
			        while (!queue.isEmpty()) {
			            s = queue.poll();
			            
			            if (isValid(s)) {
			                res.add(s);
			                found = true;
			            }
			            //一旦找到了有效的s，一定是最大长度的，因此只需要判断在同一层入队列的s，不需要再往下删除生成子字符串
			            if (found) {
			                continue;
			            }
			            //遍历字符串所有位置，删除'('或者')'之后压入队列
			            for (int i = 0; i < s.length(); i++) {
			                if (s.charAt(i) != '(' && s.charAt(i) != ')') {
			                    continue;
			                }
			                String subStr = s.substring(0, i) + s.substring(i + 1);
			                if (!visited.contains(subStr)) {
			                    queue.add(subStr);
			                    visited.add(subStr);
			                }
			            }
			        }
			        return res;
			    }
			    public boolean isValid(String s) {
			        int count = 0;
			        for (int i = 0; i < s.length(); i++) {
			            char c = s.charAt(i);
			            if (c == '(') {
			                count++;
			            } else if (c == ')' && count-- == 0) {
			                return false;
			            }
			        }
			        return count == 0;
			    }
			}





2. Math
		2.1	Basic Calculator I
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
		2.2 Basic Calculator II
    		/*
		    	Solution: 算法逻辑
		             1.运用stack， 遍历String上的所有character，如果遇到数字字符um = num * 10 + s.charAt(i) - '0'，为什么要这样，当遇见多位的数时，需要这样处理
		             2.接下来如果遇到以下两种情况就对栈进行进栈操作
		                1）  如果该字符非数字，且该字符不为空，根据sign，我们注意这个时候的sign是前次运算的sign，根据上次的sign来决定对前个num是采用何种操作。
		                     之后将sign赋值为当前字符，num设为0
		                2)   如果i遍历到字符串尾部。
		            3. 将stack的所有项相加，就是ressult
            */
			public class Solution {
			    public int calculate(String s) {
			        if (s == null || s.length() == 0) {
			            return 0;
			        }
			        Stack<Integer> stack = new Stack<Integer>();
			        int num = 0;
			        char sign = '+';
			        for (int i = 0; i < s.length(); i++) {
			            if (Character.isDigit(s.charAt(i))) {
			                num = num * 10 + s.charAt(i) - '0';
			            }
			            if ((!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ') || i == s.length() - 1) {
			                if (sign == '-') {
			                    stack.push(-num);
			                }
			                if (sign == '+') {
			                    stack.push(num);
			                }
			                if (sign == '*') {
			                    stack.push(stack.pop() * num);
			                }
			                if (sign == '/') {
			                    stack.push(stack.pop() / num);
			                }
			                sign = s.charAt(i);
			                num = 0;
			            }
			        }
			        int res = 0;
			        for (int i : stack) {
			            res += i;
			        }
			        return res;
			    }
			}
		2.3 Evaluate Reverse Polish Notation
			/*
				Evaluate the value of an arithmetic expression in Reverse Polish Notation.
				Valid operators are +, -, *, /. Each operand may be an integer or another expression.

				Some examples:
				  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
				  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
			*/
			public class Solution {
			    public int evalRPN(String[] tokens) {
			        Stack<String> stack = new Stack<>();
			        for (String s : tokens) {
			            if (!s.equals("+") && !s.equals("-") && !s.equals("*") && !s.equals("/")) {
			                stack.push(s);
			                continue;
			            } 
			            int num1 = Integer.valueOf(stack.pop());
			            int num2 = Integer.valueOf(stack.pop());
			            if (s.equals("+")) {
			                stack.push(String.valueOf(num2 + num1));
			            }
			            if (s.equals("-")) {
			                stack.push(String.valueOf(num2 - num1));
			            }
			            if (s.equals("*")) {
			                stack.push(String.valueOf(num2 * num1));
			            }
			            if (s.equals("/")) {
			                stack.push(String.valueOf(num2 / num1));
			            }
			        }
			        return Integer.valueOf(stack.pop());
			    }
			}

3. String Problem
		3.1 Simplify Path
		/*		
			当遇到“/../"则需要返回上级目录，需检查上级目录是否为空。
	        当遇到"/./"则表示是本级目录，无需做任何特殊操作。 
	        当遇到"//"则表示是本级目录，无需做任何操作。
	        当遇到其他字符则表示是文件夹名，无需简化。
	        当字符串是空或者遇到”/../”，则需要返回一个"/"。
	        当遇见"/a//b"，则需要简化为"/a/b"。
		*/
		public class Solution {
		    public String simplifyPath(String path) {
		        String res = "";
		        if (path == null || path.length() == 0) {
		            return res;
		        }
		        String[] pathArr = path.split("/");
		        LinkedList<String> stack = new LinkedList<String>();
		        for (String s : pathArr) {
		            if (s.length() == 0 || s.equals(".")) {
		                continue;
		            } else if (s.equals("..")) {
		                if (!stack.isEmpty()) {
		                    stack.pop();
		                }
		            } else {
		                stack.push(s);
		            }
		        }
		        if (stack.isEmpty()) {
		            return "/";
		        }
		        while (!stack.isEmpty()) {
		            res += "/" + stack.removeLast();
		        }
		        return res;
		    }
		}



4. Histogram Problem
		4.1 Largest Rectangle In Histogram
			/*
				For example,
					Given height = [2,1,5,6,2,3],
					return 10.
			*/
			public class Solution {
			    public int largestRectangleArea(int[] height) {
			    	Stack<Integer> stack = new Stack<Integer>();
			    	int i = 0 ;
			    	int maxArea = 0;
			    	int[] h = new int[height.length + 1];
			    	h = Arrays.copyOf(height, height.length + 1);
			    	while (i < h.length) {
			    		if (stack.isEmpty() || h[stack.peek()] <= h[i]) {
			    			stack.push(i);
			                i++;
			    		} else {
			    			int top = stack.pop();
			    			maxArea = Math.max(maxArea, h[top] * (stack.isEmpty() ? i: i - stack.peek() - 1));
			    		}
			    	}
			    	return maxArea;
			    }
			}


		4.2 Maximal Rectangle
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
			            //key point,要计算累计的matrix行的 最大值
			            maxArea = Math.max(maxArea, findMaxAreaInHistogram(height));
			        }
			        return maxArea;
			    }
    		//Use the Histogram method to find every 
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









