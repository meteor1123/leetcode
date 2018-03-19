/*
	Flip Game II
	You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

	Write a function to determine if the starting player can guarantee a win.

	For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".

	Follow up:
	Derive your algorithm's runtime complexity.

	Tags: backtracking
*/

public class Solution {
    public boolean canWin(String s) {
        // if (s == null || s.length() < 2) {
        //     return false;
        // }
        for (int i = 0; i < s.length(); i++) {
            if (s.startsWith("++", i)) {
            	//按照每一段 符合的 “++”，替换为“--”以后，
            	//用这段字符recursive的去调用canWin，如果返回false，说明对手输，则自己赢
                String t = s.substring(0, i) + "--" + s.substring(i + 2);
                if(!canWin(t)) {
                    return true;
                }
            }
        }
        return false;
    }
}

