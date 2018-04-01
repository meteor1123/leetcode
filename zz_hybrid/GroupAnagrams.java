package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Maintain a hashmap mapping the current sorted string. (K = sorted string, V = the current position in res)
 */
public class GroupAnagrams {
	public List<List<String>> groupAnagrams(String[] strs) {
		Map<String, Integer> map = new HashMap<>();
		List<List<String>> res = new ArrayList<List<String>>();
		for (String str : strs) {
			char[] ch = str.toCharArray();
			Arrays.sort(ch);
			String s = new String(ch);
			if (!map.containsKey(s)) {
				List<String> cur = new ArrayList<>();
				cur.add(str);
				map.put(s, res.size());
				res.add(cur);
			} else {
				List<String> cur = res.get(map.get(s));
				cur.add(str);
			}
		}
		return res;
	}
	public static void main(String[] args) {
		GroupAnagrams test = new GroupAnagrams();
		String[] strs = {"eat", "tea", "nat", "bat", "nat", "ate"}; 
		List<List<String>> res = test.groupAnagrams(strs);
		System.out.println(res);
	}
}
