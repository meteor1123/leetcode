/*
	784. Letter Case Permutation

	Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  Return a list of all possible strings we could create.

	Examples:
	Input: S = "a1b2"
	Output: ["a1b2", "a1B2", "A1b2", "A1B2"]

	Input: S = "3z4"
	Output: ["3z4", "3Z4"]

	Input: S = "12345"
	Output: ["12345"]
	Note:

	S will be a string with length at most 12.
	S will consist only of letters or digits.

	S will be a string with length at most 12.
  S will consist only of letters or digits.
*/


class Solution {
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList();
        helper(res, S.toCharArray(), 0);
        return res;
    }
    
    public void helper(List<String> res, char[] chars, int index) {
        if (index == chars.length) {
            res.add(new String(chars));
            return; 
        }
        
        if (Character.isLetter(chars[index])) {
            chars[index] = Character.toLowerCase(chars[index]);
            helper(res, chars, index + 1);
            chars[index] = Character.toUpperCase(chars[index]);
        }
        // put out side the isLettee check. due to: if current element is numeric we can still go through next level
        helper(res, chars, index + 1);
    }
}