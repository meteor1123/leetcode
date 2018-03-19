/*
	Shortest Word Distance
	Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

	For example,
	Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

	Given word1 = “coding”, word2 = “practice”, return 3.
	Given word1 = "makes", word2 = "coding", return 1.

	Note:
	You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.

	Tasg: Array
*/

/*
	很简单，只要设置两个pointer 一旦找到word1 或者word2 就更新index，只要标志这俩index的值都不为初始值-1，就计算距离并取最小
*/
public class Solution {
	public int shortestDistance(String[] words, String word1, String word2) {
		int index1 = -1;
		int index2 = -1;
		int minDist = Integer.MAX_VALUE;
		for (int i = 0; i < words.length; i++) {
			if (words[i] == word1) {
				index1 = i;
			} else if (words[i] == word2) {
				index2 = i;
			}
			if (index != -1 && index2 != -1) {
				minDist = Math.min(minDist, Math.abs(index1 - index2));
			}
		}
		return minDist;
	}
}


public class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        if (words == null || words.length == 0) {
            return 0;
        }
        int minLen = words.length;
        int indexOne = -1;
        int indexTwo = -1;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.equals(word1)) {
                indexOne = i;
            } else if (word.equals(word2)) {
                indexTwo = i;
            }
            
            if (indexOne != -1 && indexTwo != -1) {
                minLen = Math.min(Math.abs(indexOne - indexTwo), minLen);
            }
        }
        return minLen;
    }
}