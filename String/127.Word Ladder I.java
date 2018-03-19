/*
	Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:

	Only one letter can be changed at a time
	Each intermediate word must exist in the dictionary
	
	For example,
		Given:
		start = "hit"
		end = "cog"
		dict = ["hot","dot","dog","lot","log"]
	As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
	return its length 5.
*/

/*
	把每个单词作为一个node进行BFS。当取得当前字符串时，对他的每一位字符进行从a~z的替换，如果在字典里面，就入队，并将下层count++,并且为了避免环路，需把在字典里检测到的单词从字典里删除。
	这样对于当前字符串的每一位字符安装a~z替换后，在queue中的单词就作为下一层需要遍历的单词了。
	正因为BFS能够把一层所有可能性都遍历了，所以就保证了一旦找到一个单词equals（end），那么return的路径肯定是最短的。

	
*/


//By myself, prefer
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {  
        if (beginWord == null || beginWord.length() == 0 || endWord == null || endWord.length() == 0 || beginWord.equals(endWord))
            return 0;
        Set<String> set = new HashSet();
        Queue<String> queue = new LinkedList();
        queue.offer(beginWord);
        int curNum = 1;
        int nextNum = 0;
        int level = 1;
        Set<String> dict = new HashSet();
        for (String s : wordList) {
            dict.add(s);
        }
        while (!queue.isEmpty()) {
            String word = queue.poll();
            if (word.equals(endWord)) {
                return level;
            }
            curNum--;
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                for (char j = 'a'; j <= 'z'; j++) {
                    if (c != j) {
                        chars[i] = j;
                        String nextWord = new String(chars);
                        if (dict.contains(nextWord) && !set.contains(nextWord)) {
                            set.add(nextWord);
                            queue.offer(nextWord);
                            nextNum++;
                        }
                        chars[i] = c;
                    }
                } 
            }
            if (curNum == 0) {
                curNum = nextNum;
                nextNum = 0;
                level++;
            }
        }
        return 0;
    }
}