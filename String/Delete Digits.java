/*
	Delete Digits

	Given string A representative a positive integer which has N digits, remove any k digits of the number, the remaining digits are arranged according to the original order to become a new positive integer.

	Find the smallest integer after remove k digits.

	N <= 240 and k <= N,

	Have you met this question in a real interview? Yes
	Example
	Given an integer A = "178542", k = 4

	return a string "12"


 */


//Solution1: O(nk)
public class Solution {
    /**
     *@param A: A positive integer which has N digits, A is a string.
     *@param k: Remove k digits.
     *@return: A string
     */
    public String DeleteDigits(String A, int k) {
        // write your code here
        if (A.length() == k) {
            return "";
        }
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < A.length(); j++) {
                if (j == A.length() - 1 || A.charAt(j) > A.charAt(j + 1)) {
                    A = remove(A, j);
                    break;
                }
            }
        }
        int i = 0;
        while (i < A.length() - 1 && A.charAt(i) == '0') {
            i++;
        }
        return A.substring(i, A.length());
    }
    
    private String remove(String A, int pos) {
        return A.substring(0, pos) + A.substring(pos + 1);
    }
}

//Solution2: O(n)
public class Solution {
    /**
     *@param A: A positive integer which has N digits, A is a string.
     *@param k: Remove k digits.
     *@return: A string
     */
    public String DeleteDigits(String A, int k) {
        Stack<Integer> stack = new Stack<>();
        int popCount = 0;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < A.length(); i++) {
            int num = (int)(A.charAt(i) - '0');
            if (stack.isEmpty()) {
                stack.push(num);
            } else if (num >= stack.peek()) {
                stack.push(num);
            } else {
                if (popCount < k) {
                    stack.pop();
                    i--;
                    popCount++;
                } else {
                    stack.push(num);
                }
            }
        }
        while (popCount < k) {
            stack.pop();
            popCount++;
        }
        while (!stack.isEmpty()) {
            res.insert(0, stack.pop());
        }
        while (res.length() > 1 && res.charAt(0) == '0') {
            res.deleteCharAt(0);
        }
        return res.toString();
    }
}