/*
	Container With Most Water
	Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
	n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines,
	which together with x-axis forms a container, such that the container contains the most water.
	Note: You may not slant the container.
*/


/*
    Here is the proof. Proved by contradiction:

    Suppose the returned result is not the optimal solution. Then there must exist an optimal solution, 
    say a container with aol and aor (left and right respectively), such that it has a greater volume than the one we got. 
    Since our algorithm stops only if the two pointers meet. So, we must have visited one of them but not the other. 
    WLOG, let's say we visited aol but not aor. When a pointer stops at a_ol, it won't move until

    The other pointer also points to aol. In this case, iteration ends. 
    But the other pointer must have visited aor on its way from right end to aol. 
    Contradiction to our assumption that we didn't visit aor.

    The other pointer arrives at a value, say arr, that is greater than aol before it reaches aor. 
    In this case, we does move aol. But notice that the volume of aol and arr is already greater than aol and aor (as it is wider and heigher), 
    which means that aol and aor is not the optimal solution -- Contradiction!
*/

/*
    这题目要求就是求任意两隔板之间的最大盛水量，也就是面积，以两隔板中小的那块作为最短高度，不用管中间有多少隔板
    算法逻辑
    1. 双指针，一个指向头部，一个指向尾部
    2. 两个指针哪个高度小就将小的那边往里面移。更新最大面积

    证明：
    1. 首先，我们假设找到了最大的容积S，即由ai，aj和x轴围成，那么我们想一下，
       此时如果i的左边有比ai大的数，那么此时最大面积就不可能是S了
      （因为x轴的长度增加了，而高度最少是保证很原来相等的，当增加的是较长的边不影响高度，这是由短板理论决定的。）
    2. 同理，j的右边也是如此。此时，我们得出了一个结论，当出现最大面积时，i的左边和j的右边的高度都比ai和aj小。
    3. 那么，现在考虑i和j的中间，当中间有比ai和aj大的数时，有可能出现比S更大的容积，因为尽管x轴减小了，当高度补齐了它的不足。
    4. 所以，我们从两头向中间靠拢，同时更新候选值；在收缩区间的时候优先从 x, y中较小的边开始收缩（这点很重要，若是从较大的开始收缩会越来越小）。
*/
public class Solution {
    //prefer
    public int maxArea(int[] height) {
        int maxWater = 0;
        int start = 0;
        int end = height.length - 1;
        while (start < end) {
            if (height[start] < height[end]) {
                maxWater = Math.max(maxWater, height[start] * (end - start));
                start++;
            } else {
                maxWater = Math.max(maxWater, height[end] * (end - start));
                end--;
            }
        }
        return maxWater;
    }
}

//
class Solution {
    public int maxArea(int[] height) {
        if (height == null || height.length == 0)
            return 0;
        int start = 0;
        int end = height.length - 1;
        int max = Integer.MIN_VALUE;
        while (start < end) {
            //短板原理，选两边高度最小的
            max = Math.max(max, Math.min(height[start], height[end]) * (end - start));
            if (height[start] < height[end]) {
                start++;
            } else {
                end--;
            }
        }
        
        return max;
    }
}