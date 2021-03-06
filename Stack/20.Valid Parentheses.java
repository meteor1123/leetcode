/*
    Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
    determine if the input string is valid.

    The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
*/

/*
    step1. Using stack to store the char array of string s
    step2. check whether if the pushing char is left bracket or right bracket
    step3. if left bracket,just push
           if right bracket, get the peek element of stack, compare the type of these two bracket,
           like () {} [] is correct(using switch statment),otherwise unmatch return false;

    tag:    Stack, String.
    running time : O(n)
*/

public class Solution {
    public boolean isValid(String s) {
        //Using stack to store the string
        Stack<Character> stack = new Stack<Character>();
        
        //convert the string ro char array
        char[] charArr = s.toCharArray();
        
        //traverse the char array
        for (int i = 0; i < charArr.length; i++) {
            //left bracket, push stack
            if (charArr[i] == '(' || charArr[i] == '{' || charArr[i] == '[')
                stack.push(charArr[i]);
            //right bracket, pop stack and check
            else if (charArr[i] == ')' || charArr[i] == '}' || charArr[i] == ']') {
                //if empty,return false
                if (stack.isEmpty())
                    return false;
                //temp store the peek of stack.
                char temp = stack.pop();
                
                //Using switch to check between the charArr[i] and temp
                switch(charArr[i]) {
                    case ')':
                        if (temp != '(')
                            return false;
                        break;
                    case '}':
                        if (temp != '{')
                            return false;
                        break;
                    case ']':
                        if (temp != '[')
                            return false;
                        break;
                }
            }
        }
        
        if (!stack.isEmpty())
            return false;
        return true;
    }
}

//Solution2 by myself
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

//Solution3: best
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }
}