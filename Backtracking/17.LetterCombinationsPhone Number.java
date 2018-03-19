/*
	Letter Combinations of a Phone Number
	Given a digit string, return all possible letter combinations that the number could represent.
	A mapping of digit to letters (just like on the telephone buttons) is given below.

	Input:Digit string "23"
	Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

	Note:
	Although the above answer is in lexicographical order, your answer could be in any order you want.

	Tags: Backtracking， String
*/

/*
    Time complexity: 
    Assuming the average number of letters on every number is m,
    and the length of digits string is n, then the complexity is O(m的n次方).
*/

public class Solution {
    //Recursive
	public List<String> letterCombinations(String digits) {
        //new a ArrayList to store the result 
    	ArrayList<String> res = new ArrayList<String>();
        //if the digits is null or the length equals 0,and I return the  res
    	if (digits == null || digits.length() == 0)
    		return res;

        //new a String array , the value is like the phone's keyboard, notice that in front of two value is null
        //since we need to  let the index according to the keyboard
    	String[] keyboard = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        //new a item to stroe the every string combination
    	StringBuilder item = new StringBuilder();
        //use index to iterate the String digits
    	int index = 0;
        //use dfs to get the whole valid lettercombinations
    	backtracking_helper(digits, index, item, keyboard, res);
    	return res;
    }

    private void backtracking_helper(String digits, int index, StringBuilder item, String[] keyboard, ArrayList<String> res) {
    	
        if (item.length() == digits.length()) {
    		res.add(item.toString());
    		return ;
    	}
        //get the number character from the String digits.
    	int num = digits.charAt(index) - '0';              //取出字符串里的第index位数字,正好数字对应该数字所表示的字符数组的下标，2 == board[2] (a, b, c)
    	for (int i = 0; i < keyboard[num].length(); i++) { //在keyboard字符串数组里，23 的2 就代表在keyboard[2]里的字符
    		item.append(keyboard[num].charAt(i));
    		backtracking_helper(digits, index + 1, item, keyboard, res);
    		item.deleteCharAt(item.length() - 1);
    	}

    }

    //Iteration1
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
    
    //iteration2
    public ArrayList<String> letterCombinations(String digits) {
        ArrayList<String> res = new ArrayList<String>();
        if (digits.length() == 0 || digits == null) {
            return res;
        }
        //at first we use empty string initialize the res list 
        res.add("");
        //and new a keyboard String array
        String[] keyboard = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        //treave all the number of String digits
        for (int i = 0; i < digits.length(); i++) {
            //base on the digits to selce the according letters in keyborad
            String letters = keyboard[digits.charAt(i) - '0'];
            //new a temp ArrayList to restore the temp resut
            ArrayList<String> temp = new ArrayList<String>();
            //traver the result ArrayList
            for (int j = 0; j < res.size(); j++) {
                for (int k = 0; k < letters.length(); k++) {
                    temp.add(res.get(j) + Character.toString(letters.charAt(k)));
                }
            }
            res = temp;
        }
        return res;
    }
}