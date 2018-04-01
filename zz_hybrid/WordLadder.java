package LeetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 */
public class WordLadder {
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		if (wordList.size() == 0) return -1;
		if (beginWord == endWord) return 0;
		Queue<String> q = new LinkedList<String>();
		Set<String> visited = new HashSet<String>();
		Set<String> wordSet = new HashSet<String>();
		for (String s : wordList) {
			wordSet.add(s);
		}
		q.offer(beginWord);
		visited.add(beginWord);
		int length = 1;
		while (!q.isEmpty()) {
			int size = q.size();
			length++;
			for (int i = 0; i < size; i++) {
				String cur = q.poll();
				for (String s : getNextWords(cur, wordSet)) {
					if (visited.contains(s)) {
						continue;
					}
					if (s.equals(endWord)) {
						return length;
					}
					q.offer(s);
					visited.add(s);
				}
			}
		}
		return 0;
	}
	private String replace(String word, int index, char c) {
		char[] ch = word.toCharArray();
		ch[index]= c;
		return new String(ch);
	}
	public List<String> getNextWords(String word, Set<String> wordSet) {
		List<String> res = new ArrayList<String>();
		char[] ch = word.toCharArray();
		for (int i = 0; i < word.length(); i++) {
			for (char c = 'a'; c <= 'z'; c++) {
				if (ch[i] == c) continue;
				String nextWord = replace(word, i, c);
				if (wordSet.contains(nextWord)) {
					res.add(nextWord);
				}
			}
		}
		return res;
	}
}
