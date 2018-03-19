/*
	Maximum Product of Word Lengths
	Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

	Example 1:
	Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
	Return 16
	The two words can be "abcw", "xtfn".

	Example 2:
	Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
	Return 4
	The two words can be "ab", "cd".

	Example 3:
	Given ["a", "aa", "aaa", "aaaa"]
	Return 0
	No such pair of words.
*/

public class Solution {
    public int maxProduct(String[] words) {
        if (words == null || words.length <= 1) {
            return 0;
        }
        //Pre-processing
        int[] preprocess = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            preprocess[i] = 0;
            for (int j = 0; j < words[i].length(); j++) {
                //核心思想，因为一个单词就26个字母，我们将一个单词出现的字母将它出现的bit位置1。
                //下次只需要比较两个字母的bit &是否等于0，如果等于0就是没有重叠的字符
                preprocess[i] |= (1 << words[i].charAt(j) - 'a');
            }
        }
        
        int maxProduct = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((preprocess[i] & preprocess[j]) == 0) {
                    maxProduct = Math.max(maxProduct, words[i].length() * words[j].length());
                }
            }
        }
        return maxProduct;
    }
}