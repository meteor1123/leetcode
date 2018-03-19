/*
	Longest Substring with At Most Two Distinct Characters
	Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

	For example, Given s = “eceba”,

	T is "ece" which its length is 3.
*/

public class Solution {
	//Solution1
	/*
		这题用hashmap的话，最关键在于map 的value 记录每一个字符的最新位置，相同的话更新为最前面的位置，只有这样才能追踪到size == 2时，最左边的字符的位置

	*/
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int start = 0;//用来标识左边字符的起始位置
        int maxLen = 0;//longest sustring长度
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //加入 map size==2， 再加进来的字符之前不存在，则需要将最左边的字符出列
            if (map.size() == 2 && !map.containsKey(c)) {
                char charEndsMostLeft = ' ';//最左边要被淘汰的字符；
                int minLastPosition = s.length();//记录最左边的要被淘汰的字符的位置
                for (char ch : map.keySet()) {
                    int last = map.get(ch);
                    if (last < minLastPosition) {
                        minLastPosition = last;
                        charEndsMostLeft = ch;
                    }
                }
                start = minLastPosition + 1;//从被淘汰字符的下一位开始记录start
                map.remove(charEndsMostLeft);
            }
            map.put(c, i);
            maxLen = Math.max(maxLen, i - start + 1);
        }
        return maxLen;
    }

	//Solution2
	/*
		brute force的解法就是构造出来所有substring然后线性扫描一遍，复杂度为O(n^3)。可以使用Set来判断是不是有重复的字符。
		如果假定是在26个英文字母之间的话，更容易了，直接上个array就好。但这个假定一般都不会成立的。

		最优的解法应该是维护一个sliding window，指针变量i指向sliding window的起始位置，
		j指向另个一个字符在sliding window的最后一个，用于定位i的下一个跳转位置。内部逻辑就是
		1）如果当前字符跟前一个字符是一样的，直接继续。
		2）如果不一样，则需要判断当前字符跟j是不是一样的
			a）一样的话sliding window左边不变，右边继续增加，但是j的位置需要调整到k-1。
			b）不一样的话，sliding window的左侧变为j的下一个字符（也就是去掉包含j指向的字符的区间），j的位置也需要调整到k-1。

		在对i进行调整的时候（1.a），需要更新maxLen。

		[注意事项]
		1）在最后返回的时候，注意考虑s.length()-i这种情况，也就是字符串读取到最后而没有触发（1.a）
		2）讲解清楚sliding window的更新
		3）该题目有个follow-up，就是如果是k个distinct characters怎么办。这样的话就只能对所有可能的字符用一个数组去做counting，
		   而且只能假设ASIC字符集256。Unicode太大了。
	*/
	/*
		uses i and j to track the last indices of two characters. i is tracking the first character, j is tracking the second character. 
	 */
	public int lengthOfLongestSubstringTwoDistinct(String s) {
		int i = 0;
		int j = -1;
		int maxLen = 0;
		for (int k = 1; k < s.length(); k++) {
			if (s.charAt(k) == s.charAt(k - 1)) {
				continue;
			} 
			if (j >= 0 && s.charAt(j) != s.charAt(k)) {
				maxLen = Math.max(k - i, maxLen);
				i = j + 1;
			}
			j = k - 1;
		}
		return Math.max(s.length() - i, maxLen);
	}
}