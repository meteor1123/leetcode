/*
	H-Index
	Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

	According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, 
    and the other N − h papers have no more than h citations each."

	For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them 
    had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations each and 
    the remaining two with no more than 3 citations each, his h-index is 3.

	Note: If there are several possible values for h, the maximum one is taken as the h-index.
*/

/*

    有N篇文章至少引用N次， 求最大的N

    给定一个代表一位研究人员论文的引用次数的数组，计算对应的H-Index。
    H-Index的定义可以抽象地描述成这样：一个数组中有N个元素，如果其中至少有H个数的值不小于H，
    剩下N-H个数不大于H，那么这个数组的H-Index的值就是H了。H的值可能有多个，题目要求找出其中最大的作为H-Index。
    
	Solution:
	 H-Index的核心计算方法如下：
        1. 将某作者的所有文章的引用频次按照从大到小的位置排列
        2. 从前到后，找到最后一个满足条件的位置，其条件为：此位置是数组的第x个，其值为y，必须满足 y >= x;
        3. 至此，思路已经形成。即先排序，然后从前向后遍历即可。(从大到小， 所以x = len - i)
*/

public class Solution {
    //1
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        Arrays.sort(citations);
        int h = 0;
        int len = citations.length;
        for (int i = len - 1; i >= 0; i--) {
            if (citations[i] >= len - i) {
                h = len - i;
            }
        }
        return h;
    }

    //2: prefer
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        Arrays.sort(citations);
        int len = citations.length;
        for (int i = 0; i < len; i++) {
            if (citations[i] >= len - i) {
                return len - i;
            }
        }
        return 0;
    }
}
/*
    思路：类似bucket sort， 
    建立一个记录。记录的是这个被引用文章出现的次数。因为最大的H值就是文章数。对于引用次数超过文章数的文章按照引用次数为文章数记录。 
    当遍历到某一个H值的时候，对应的sums数组里面存储的就是超过这个引用次数的文章数。如果这个h满足题意。那么就返回
*/

public class Solution {
    public int hIndex(int[] citations) {
        int len = citations.length;
        int[] count = new int[len + 1];
        for (int c : citations) {
            if (c > len) {
                count[len]++;
            } else {
                count[c]++;
            }
        }
        
        int sum = 0;
        for (int i = len; i >= 0; i--) {
            // sum记录大于count[i]的次数
            sum += count[i];
            if (sum >= i) {
                return i;
            }
        }
        return 0;
    }
}