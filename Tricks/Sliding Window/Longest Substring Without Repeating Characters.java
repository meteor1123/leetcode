/*
	Longest Substring Without Repeating Characters
	Given a string, find the length of the longest substring without repeating characters. 
	For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. 
	For "bbbbb" the longest substring is "b", with the length of 1.
*/


/*
	Solution:
		step1: 设定一个滑动窗口，窗口的左边界为left，右边界为right，维护一个全局最大距离变量 max = right - left
		step2: 先不停将right往右边移动，并放入hashset，只要hashset中没有重复的字符，right一直++
		step3: 一旦遇到重复的字符，判断先前的 max 是否小于现在的right - left，如果小于则更新max,
			   并且需要将right之前存在set里的字符remove，因为下一次要进行比较的窗口序列是从right开始，也就是 left = right
		step4: 每一次left前进只在 找到重复字符时才++，
			   相反right只在没找到重复字符时才++.
*/
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0 || s == null)
            return 0;
        int max = 0;
        int left = 0;
        int right = 0;
        HashSet<Character> hs = new HashSet<Character>();
        while (right < s.length()) {
            if (hs.contains(s.charAt(right))) {
            	//当遇到重复的值的时候，才计算从left - right的距离，并与max进行比较
                if (max < right - left)
                    max = right - left;
                while (s.charAt(left) != s.charAt(right)) {
                    hs.remove(s.charAt(left));
                    left++;
                }
                left++;//因为上面的left 前进到和right相等的那个字符时就停止，也就是在right的左边，
                       //而下一次的查找left应该从right位置开始，所以这里要在外面left++
            } else {
                hs.add(s.charAt(right));
            }
            right++;
        }
        max = Math.max(max, right - left);
        return max;
    }
}

//Prefer
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Set<Character> set = new HashSet<>();
        int left = 0;
        int right = 0;
        int maxLen = 0;
        while (right < s.length()) {
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                maxLen = Math.max(maxLen, right - left + 1);
            } else {
                while (s.charAt(left) != s.charAt(right)) {
                    set.remove(s.charAt(left));
                    left++;
                }
                left++;
            }
            right++;
        }
        maxLen = Math.max(maxLen, right - 1 - left + 1);
        return maxLen;
    }
}