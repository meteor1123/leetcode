/*
	Brainteaser
*/


1. Nim Game
	/*
		You are playing the following Nim Game with your friend: There is a heap of stones on the table, each time one of you take turns to remove 1 to 3 stones. The one who removes the last stone will be the winner. You will take the first turn to remove the stones.

		Both of you are very clever and have optimal strategies for the game. Write a function to determine whether you can win the game given the number of stones in the heap.

		For example, if there are 4 stones in the heap, then you will never win the game: no matter 1, 2, or 3 stones you remove, the last stone will always be removed by your friend.

		Hint:

		If there are 5 stones in the heap, could you figure out a way to remove the stones such that you will always be the winner?
	*/
	//Solution1:
	public class Solution {
	    public boolean canWinNim(int n) {
	        return n % 4 != 0;
	    }
	}
	//Solution2: easy to understand
	/*
		 If you take 1 item, there will be (i - 1) for other player. If these (i - 1) item for the other play means "win", 
	     that means "lose" for you, which in code is that if res[i - 1] is true, you take 1 item and then you lose; otherwise, you win. 
	     Same reason for 2 items and 3 items. 
	*/
	public boolean canWinNim(int n) {
	    if(n <= 0)
	        throw new IllegalArgumentException();
	    if(n < 4)
	        return true;
	    boolean[] res = new boolean[n + 1];
	    res[0] = true;
	    res[1] = true;
	    res[2] = true;
	    res[3] = true;
	    for(int i = 4 ; i <= n ; i++)
	        res[i] = !(res[i - 1] && res[i - 2] && res[i - 3]);
	    return res[n];
	}








