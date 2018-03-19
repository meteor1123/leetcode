/*
	Text Justification
	Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

	You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

	Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, 
    the empty slots on the left will be assigned more spaces than the slots on the right.

	For the last line of text, it should be left justified and no extra space is inserted between words.
	For example,
	words: ["This", "is", "an", "example", "of", "text", "justification."]
	L: 16.

	Return the formatted lines as:
	[
	   "This    is    an",
	   "example  of text",
	   "justification.  "
	]
	Note: Each word is guaranteed not to exceed L in length.

	Corner Cases:
	A line other than the last line might contain only one word. What should you do in this case?
	In this case, that line should be left-justified.

	Tags: String
*/

    /*
    这道题属于纯粹的字符串操作，要把一串单词安排成多行限定长度的字符串。主要难点在于空格的安排，
思路1：
    1.首先每个单词之间必须有空格隔开，而当当前行放不下更多的单词并且字符又不能填满长度L时，我们要把空格均匀的填充在单词之间。
    2.如果剩余的空格量刚好是间隔倍数那么就均匀分配即可，否则还必须把多的一个空格放到前面的间隔里面。
    3.实现中我们维护一个count计数记录当前长度，超过之后我们计算共同的空格量以及多出一个的空格量，然后将当行字符串构造出来。
    4.最后一个细节就是最后一行不需要均匀分配空格，句尾留空就可以，所以要单独处理一下。
    5.时间上我们需要扫描单词一遍，然后在找到行尾的时候在扫描一遍当前行的单词，不过总体每个单词不会被访问超过两遍，所以总体时间复       杂度是O(n)。
    6.而空间复杂度则是结果的大小（跟单词数量和长度有关，不能准确定义，如果知道最后行数r，则是O(r*L)
    
思路2：
    1. 首先要能判断多少个word组成一行：
        这里统计读入的所有words的总长curLen，并需要计算空格的长度。假如已经读入words[0]~words[i]。
        当curLen + i <=L 且 curLen + 1 + word[i+1].size() > L时，一行结束。

    2. 知道一行的所有n个words，以及总长curLen之后要决定空格分配：
        L      = 每行长度
        curLen = 一行中所有word的长度之和
        n      = 一行中word的数量

    平均空格数                             ：  k = (L - curLen) / (n-1)
    在同一行的前m个单词每个单词应该有的空格数k+1 ：m = (L - curLen) % (n-1)

    例子：L = 21，curLen = 14，n = 4
          k = (21 - 14) / (4-1) = 2
          m = (21 - 14) % (4-1)  = 1
          A---B--C--D

    3. 特殊情况：
        (a) 最后一行：当读入到第i = words.size()-1 个word时为最后一行。该行k = 1，m = 0
        (b) 一行只有一个word：此时n-1 = 0，计算(L - curLen)/(n-1)会出错。该行k = L-curLen, m = 0
        (c) 当word[i].size() == L时。

    */

//   0   1  2    3
// "This is an example..."
//  i=0, j=3, width=8, space=(16-8)/(3-0-1)=4, extra=0
// ------------------------------------------------------
//   3      4   5        6
// "example of text justification."
//  i=3, j=6, width=13, space=(16-13)/(3-0-1)=1, extra=1
// ------------------------------------------------------
// "justification."
//  i=6, j=7, space=1, extra=0

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        for (int i = 0, j; i < words.length; ) {
            int width = 0;                                  // width of words without space
            for (j = i; j < words.length && width + words[j].length() + (j - i) <= maxWidth; j++)
                width += words[j].length();                 /* j is the next word */
            
            int space = 1, extra = 0;                       // for last line, space=1
            if (j - i != 1 && j != words.length) {          // not 1 word (div-by-zero) and not last line
                space = (maxWidth - width) / (j - i - 1);   // minus 1 to exclude skip last word
                extra = (maxWidth - width) % (j - i - 1);
            }
            
            StringBuilder line = new StringBuilder(words[i]);
            for (i = i + 1; i < j; i++) {                   // move i to j finally
                for (int s = space; s > 0; s--) line.append(" ");
                if (extra-- > 0) line.append(" ");
                line.append(words[i]);
            }
            for (int s = maxWidth - line.length(); s > 0; s--) line.append(" ");
            result.add(line.toString());
        }
        return result;
    }
}









