/*
	Remove Invalid Parentheses
	Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
	Note: The input string may contain letters other than the parentheses ( and ).

	Examples:
		"()())()" -> ["()()()", "(())()"]
		"(a)())()" -> ["(a)()()", "(a())()"]
		")(" -> [""]
*/


//BFS
public class Solution {
    public List<String> removeInvalidParentheses(String s) {
      List<String> res = new ArrayList<>();

      // sanity check
      if (s == null) return res;

      Set<String> visited = new HashSet<>();
      Queue<String> queue = new LinkedList<>();

      // initialize
      queue.add(s);
      visited.add(s);

      boolean found = false;

      while (!queue.isEmpty()) {
        s = queue.poll();

        if (isValid(s)) {
          // found an answer, add to the result
          res.add(s);
          found = true;
        }

        if (found) continue;

        // generate all possible states
        for (int i = 0; i < s.length(); i++) {
          // we only try to remove left or right paren
          if (s.charAt(i) != '(' && s.charAt(i) != ')') continue;

          String t = s.substring(0, i) + s.substring(i + 1);

          if (!visited.contains(t)) {
            // for each state, if it's not visited, add it to the queue
            queue.add(t);
            visited.add(t);
          }
        }
      }

      return res;
    }

    // helper function checks if string s contains valid parantheses
    boolean isValid(String s) {
      int count = 0;

      for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (c == '(') count++;
        if (c == ')' && count-- == 0) return false;
      }

      return count == 0;
    }
}




//DFS
public class Solution {
    int maxLen;
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            res.add("");
            return res;
        }
        maxLen = 0;
        Set<String> set = new HashSet<>();
        helper(s, 0, 0, 0, "", set);
        for (String cur : set) {
            res.add(cur);
        }
        return res;
    }
    public void helper(String s, int pos, int left, int right, String item, Set<String> res) {
        if (left < right) {
            return;
        }
        if (pos >= s.length()) {
            if (left != right) {
                return;
            }
            if (res.size() == 0 || maxLen == item.length()) {
                res.add(item);
                maxLen = item.length();
            } else if (maxLen < item.length()) {
                res = new HashSet<>();
                res.add(item);
                maxLen = item.length();
            }
            return;
        }
        if (s.charAt(pos) == '(') {
            helper(s, pos + 1, left + 1, right, item + "(", res);
            helper(s, pos + 1, left, right, item, res);
        } else if (s.charAt(pos) == ')') {
            helper(s, pos + 1, left, right + 1, item + ")", res);
            helper(s, pos + 1, left, right, item , res);
        } else {
            helper(s, pos + 1, left, right, item + s.charAt(pos), res);
        }
    }
}