/*
	763. Partition Labels

	A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

	Example 1:
	Input: S = "ababcbacadefegdehijhklij"
	Output: [9,7,8]
	Explanation:
	The partition is "ababcbaca", "defegde", "hijhklij".
	This is a partition so that each letter appears in at most one part.
	A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
	Note:

	S will have length in range [1, 500].
	S will consist of lowercase letters ('a' to 'z') only.
*/

class Solution {
    public List<Integer> partitionLabels(String S) {
        if (S == null | S.length() == 0) {
            return null;
        }
        List<Integer> res = new ArrayList();
        int[] map = new int[26];
        
        for (int i = 0; i < S.length(); i++) {
            map[S.charAt(i) - 'a'] = i;
        }
        
        int maxEnd = 0;
        int start = 0;
        for (int i = 0; i < S.length(); i++) {
            int curEnd = map[S.charAt(i) - 'a']; // 当前char的范围
            if (maxEnd < i) { // 如果最大范围已经不足以cover i， 这就是一个partition
                res.add(i - start);
                start = i;
            }
            maxEnd = Math.max(curEnd, maxEnd);
        }
        res.add(S.length() - start);
        return res;
    }
}