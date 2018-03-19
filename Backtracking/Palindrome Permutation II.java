/*
	Palindrome Permutation II
	Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

	For example:

	Given s = "aabb", return ["abba", "baab"].

	Given s = "abc", return [].

	Hint:

	If a palindromic permutation exists, we just need to generate the first half of the string.
	To generate all distinct permutations of a (half of) string, use a similar approach from: Permutations II or Next Permutation.

	Tags: Backtracking
*/


/*
    Solution: 1. 用一个HashMap 来存储 字符串s对应的每个字符的数量
              2. 假如字符数量为奇数的字符，超过一个，则返回false，两个及以上不同的字符存在，无法构造Palindrome。
                 这里用一个int变量odd来记录这个奇数字符的数量，非常巧妙，如果字符c的数量 % 2 == 0，则等于原来的数量 -1；
                                                                如果字符c的数量 % 2 != 0, 则等于原来的数量 +1;
                                                                因此原来是奇数加了是偶数，原来是偶数，加了会和-1 中和为0， 
                                                                没有影响 还是偶数
              3. 遍历HashMap的每一个entry条目，假如val %2  != 0,说明是奇数，要取该字符为mid
                 将每个字符的 val/2数量加入 list中
              4. dfs这个list中的所有字符，形如{a, b) 构造出它们的 pemutation， 也就是 ab, ba， 
                 然后再用sb.toString() + mid + sb.reverse().toString() 的方法构造palindrome permutation！
             
*/
public class Solution {
    public List<String> generatePalindromes(String s) {
        int odd = 0;
        String mid = "";
        List<String> res = new ArrayList<>();
        List<Character> list = new ArrayList<>();
        HashMap<Character, Integer> map = new HashMap<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
            odd += map.get(c) % 2 != 0 ? 1 : -1;
        }
        
        if (odd > 1) {
            return res;
        }
        
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char key = entry.getKey();
            int val = entry.getValue();
            
            if (val % 2 != 0) {
                mid += key;
            }
            
            for (int i = 0; i < val / 2; i++) {
                list.add(key);
            }
        }
        
        helper(list, mid, new boolean[list.size()], new StringBuilder(), res);
        return res;
    }
    
    public void helper(List<Character> list, String mid, boolean[] used, StringBuilder sb, List<String> res) {
        if (sb.length() == list.size()) {
            res.add(sb.toString() + mid + sb.reverse().toString());
            sb.reverse();
            return;
        }
        
        for (int i = 0; i < list.size(); i++) {
            if (i > 0 && list.get(i) == list.get(i - 1) && !used[i - 1]) {
                continue;
            }
            if (!used[i]) {
                used[i] = true;
                sb.append(list.get(i));
                helper(list, mid, used, sb, res);
                used[i] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}