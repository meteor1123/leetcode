/*
	Flip Game
	You are playing the following Flip Game with your friend: Given a string that contains
	 only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". 
	 The game ends when a person can no longer make a move and therefore the other person will be the winner.

	Write a function to compute all possible states of the string after one valid move.

	For example, given s = "++++", after one move, it may become one of the following states:

	[
	  "--++",
	  "+--+",
	  "++--"
	]
	If there is no valid move, return an empty list [].
*/

/*
	题意：给一段字符串，游戏目的在于将连续的两个“+” 也就是“++” 翻转 成为“--”
		 如果没有连续的两个“++”，则no valid
*/
//Solution1

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
        return res;
    }
}

//Solution2
public class Solution {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> res = new ArrayList<>();
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '+' && s.charAt(i - 1) == '+') {
                res.add(s.substring(0, i - 1) + "--" + s.substring(i + 1));
            }
        }
        return res;
    }
}