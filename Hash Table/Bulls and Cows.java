/*
	Bulls and Cows
	You are playing the following Bulls and Cows game with your friend: You write a 4-digit secret number and ask your friend to guess it, each time your friend guesses a number, you give a hint, the hint tells your friend how many digits are in the correct positions (called "bulls") and how many digits are in the wrong positions (called "cows"), your friend will use those hints to find out the secret number.

	For example:

	Secret number:  1807
	Friend's guess: 7810
	Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
	According to Wikipedia: "Bulls and Cows (also known as Cows and Bulls or Pigs and Bulls or Bulls and Cleots) is an old code-breaking mind or paper and pencil game for two or more players, predating the similar commercially marketed board game Mastermind. The numerical version of the game is usually played with 4 digits, but can also be played with 3 or any other number of digits."

	Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows, in the above example, your function should return 1A3B.

	You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
*/

public class Solution {

	//Solution1
	/*
		核心思想是只要匹配就same++，
		不匹配的并且val > 0 就diff++，
		但是如何解决diff占用后面same匹配的val呢？通过check val是否小于=0，减去diff
	*/
    public String getHint(String secret, String guess) {
        HashMap<Character, Integer> map = new HashMap<>();
        String res = "";
        int len = secret.length();
        for (char c : secret.toCharArray()) {
            map.put(c, map.get(c) == null ? 1 : map.get(c) + 1);
        }
        int sameCount = 0;
        int diffCount = 0;
        for (int i = 0; i < len; i++) {
            char c1 = secret.charAt(i);
            char c2 = guess.charAt(i);
            int val = map.get(c2) == null ? 0 : map.get(c2);
            if (c1 == c2) {
                sameCount++;
                if (val <= 0) {
                    diffCount--;
                }
            }  else if (val > 0) {
                diffCount++;
            }
            map.put(c2, val - 1);
        }
        
        return sameCount + "A" + diffCount + "B";
    }


    //Solution2
    public String getHint(String secret, String guess) {
	    int bulls = 0;
	    int cows = 0;
	    int[] numbers = new int[10];
	    for (int i = 0; i<secret.length(); i++) {
	        if (secret.charAt(i) == guess.charAt(i)) 
	        	bulls++;
	        else {
	            if (numbers[secret.charAt(i)-'0']++ < 0) 
	            	cows++;
	            if (numbers[guess.charAt(i)-'0']-- > 0) 
	            	cows++;
	        }
        }
        return bulls + "A" + cows + "B";
    }

    // my solution
    public String getHint(String secret, String guess) {
        HashMap<Character, Integer> map = new HashMap();
        for (int i = 0; i < secret.length(); i++) {
            map.put(secret.charAt(i), map.getOrDefault(secret.charAt(i), 0) + 1);
        }
        int bull = 0;
        int cows = 0;
        for (int i = 0; i < guess.length(); i++) {
            char c1 = secret.charAt(i);
            char c2 = guess.charAt(i);
            if (c1 == c2) {
                bull++;
                if (map.get(c1) == 0) {
                    cows--;
                } else {
                    map.put(c1, map.get(c1) - 1);
                }
            } else if (map.get(c2) != null && map.get(c2) > 0) {
                map.put(c2, map.get(c2) - 1);
                cows++;
            }
        }
        return bull + "A" + cows + "B";
    }
}