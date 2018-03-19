/*
	767. Reorganize String

	Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

	If possible, output any possible result.  If not possible, return the empty string.

	Example 1:

	Input: S = "aab"
	Output: "aba"
	Example 2:

	Input: S = "aaab"
	Output: ""
	Note:

	S will consist of lowercase letters and have length in range [1, 500].
*/


/*
	Solution: 每次取最大堆的堆顶元素，并且取完以后不马上入heap，等下一次循环的时候再进，有效防止重复取count最大的数
*/

class Solution {
    public String reorganizeString(String S) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        int[] m = new int[26];
        
        for (int i = 0; i < S.length(); i++)
            m[S.charAt(i) - 'a']++;
        
        for (int i = 0; i < 26; i++) {
            if (m[i] != 0)
                pq.offer(new int[]{i, m[i]});
        }
        
        StringBuilder sb = new StringBuilder();
        int[] pre = new int[] {-1,0};
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (pre[1] > 0) 
                pq.offer(pre); // add back last used character
            sb.append((char)(cur[0] + 'a')); // append current character
            cur[1]--; // decrease count of current char since it's used
            pre = cur; // set this character as previous used
            if (pq.isEmpty() && pre[1] > 0) // if we left with anything return ""
                return "";
        }
        return sb.toString();
    }
}