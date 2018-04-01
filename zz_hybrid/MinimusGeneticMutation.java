package LeetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
 * start: "AAAAACCC"
 * end:   "AACCCCCC"
 * bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
 * return: 3
 */

public class MinimusGeneticMutation {
	public int minMutation(String start, String end, String[] bank) {
		if (start.equals(end)) return 0;
		if (bank == null || bank.length == 0) return -1;
		Queue<String> q = new LinkedList<String>();
		Set<String> bankSet = new HashSet<String>();
		for (String b : bank) {
			bankSet.add(b);
		}
		Set<String> visited = new HashSet<String>();
		q.offer(start);
		visited.add(start);
		int length = 0;
		while (!q.isEmpty()) {
			length++;
			int size = q.size();
			for (int i = 0; i < size; i++) {
				String cur = q.poll();
				for (String s : nextGene(cur, bankSet)) {
					if (visited.contains(s)) {
						continue;
					}
					if (s.equals(end)) {
						return length;
					}
					q.offer(s);
					visited.add(s);
				}
			}
		}
		return length;
	}
	private String replace(String gene, int index, char c) {
		char[] ch = gene.toCharArray();
		ch[index] = c;
		return new String(ch);
	}
	private List<String> nextGene(String gene, Set<String> bankSet) {
		List<String> nextGene = new ArrayList<String>();
		char[] ch = gene.toCharArray();
		char[] elements = new char[] {'A','C','T','G'};
		for (int i = 0; i < gene.length(); i++) {
			for (char e : elements) {
				if (ch[i] == e) continue;
				String next = replace(gene, i, e);
				if (bankSet.contains(next)) {
					nextGene.add(next);
				}
			}
		}
		return nextGene;
	}
	public static void main(String[] args) {
		MinimusGeneticMutation test = new MinimusGeneticMutation();
		String[] bank = {"AAAACCCC", "AAACCCCC", "AACCCCCC"};
		int res = test.minMutation("AAAAACCC", "AACCCCCC", bank);
		System.out.println(res);
	}
}
