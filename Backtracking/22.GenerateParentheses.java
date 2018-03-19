/*
	Generate Parentheses
	Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
	For example, given n = 3, a solution set is:
		"((()))", "(()())", "(())()", "()(())", "()()()"
	Tags: Backtracking, String
*/



public class Solution {

    //Recursive
    public List<String> generateParenthesis(int n) {
        ArrayList<String> res = new ArrayList<String>();
        if (n <= 0)
            return res;
        dfs(n, n, "", res);
        return res;
    }
    public void dfs(int left, int right, String item, ArrayList<String> res){
        //终止的返回条件，left 和right都等于0，说明已经放置n个(括号 和 n个)括号
        if (left == 0 && right == 0) {
            res.add(item);
            return;
        }
        //递归状态，如果left > 0 则不断递归放入(符号
        if (left > 0)
            dfs(left - 1, right, item + '(', res);
        //因为只有当左括号数量大于右括号的数量时才能放入右括号
        if (left < right)
            dfs(left, right - 1, item + ')', res);
    }


    //Iterative stack
    //https://leetcode.com/discuss/6185/does-anyone-come-up-with-a-non-recursion-solution
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        Stack<Integer> validationStack = new Stack<>();
        stack.push("(");
        validationStack.push(0);
        while (!stack.isEmpty()) {
            String s = stack.pop();
            int v = validationStack.pop();
            if (s.length() == n * 2) {
                res.add(s);
                continue;
            }
            if (s.length() - v < n) {
                stack.push(s + "(");
                validationStack.push(v);
            }
            
            if (2 * v < s.length()) {
                stack.push(s + ")");
                validationStack.push(v + 1);
            }
        }
        return res;
    }

    //Iterative DP

    /*
        My method is DP. First consider how to get the result f(n) from previous result f(0)...f(n-1). Actually, the result f(n) will be put an extra () pair to f(n-1). Let the "(" always at the first position, to produce a valid result, we can only put ")" in a way that there will be i pairs () inside the extra () and n - 1 - i pairs () outside the extra pair.

        Let us consider an example to get clear view:

        f(0): ""
        f(1): "("f(0)")"
        f(2): "("f(0)")"f(1), "("f(1)")"
        f(3): "("f(0)")"f(2), "("f(1)")"f(1), "("f(2)")"
        So f(n) = "("f(0)")"f(n-1) , "("f(1)")"f(n-2) "("f(2)")"f(n-3) ... "("f(i)")"f(n-1-i) ... "(f(n-1)")"
    */
public class Solution {
    public List<String> generateParenthesis(int n) {
        List<List<String>> lists = new ArrayList<>();
        List<String> initList = new ArrayList<>();
        initList.add("");
        lists.add(initList);
        
        for (int i = 1; i <= n; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                for (String first : lists.get(j)) {
                    for (String second : lists.get(i - j - 1)) {
                        list.add("(" + first + ")" + second);
                    }
                }
            }
            lists.add(list);
        }
        return lists.get(lists.size() - 1);
    }
}