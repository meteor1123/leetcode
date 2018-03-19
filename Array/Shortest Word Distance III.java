/*
	Shortest Word Distance III
	This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.

	Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

	word1 and word2 may be the same and they represent two individual words in the list.

	For example,
	Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

	Given word1 = “makes”, word2 = “coding”, return 1.
	Given word1 = "makes", word2 = "makes", return 3.
*/


//Solution1 prefer
public class Solution {
	public int shortestWordDistance(String[] words, String word1, String word2) {
        long dist = Integer.MAX_VALUE, index1 = dist, index2 = -dist;
        boolean same = word1.equals(word2);
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                if (same) {
                    index1 = index2;
                    index2 = i;
                } else {
                    index1 = i;
                }
            } else if (words[i].equals(word2)) {
                index2 = i;
            }
            dist = Math.min(dist, Math.abs(index1 - index2));
        }
        return (int)dist;
    }
}

//Solution2 by Myself
public class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        if (words == null || words.length == 0) {
            return 0;
        }
        int index1 = -1;
        int index2 = -1;
        int minLen = words.length;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (!word1.equals(word2)) {
                if (word.equals(word1)) {
                    index1 = i;
                } else if (word.equals(word2)) {
                    index2 = i;
                }
                if (index1 != -1 && index2 != -1) {
                    minLen = Math.min(Math.abs(index1 - index2), minLen);
                }
            } else {
                if (word.equals(word1)) {
                    if (index1 != -1) {
                        minLen = Math.min(i - index1, minLen);
                    }
                    index1 = i;
                }
            }
        }
        return minLen;
    }
}

//Solution short solution
public int shortestWordDistance(String[] words, String word1, String word2) {
    long dist = Integer.MAX_VALUE;
    long i1 = dist;
    long i2 = -dist;
    for (int i = 0; i < words.length; i++) {
        if (words[i].equals(word1))
            i1 = i;
        if (words[i].equals(word2)) {
            if (word1.equals(word2))
                i1 = i2;
            i2 = i;
        }
        dist = Math.min(dist, Math.abs(i1 - i2));
    }
    return (int) dist;
}