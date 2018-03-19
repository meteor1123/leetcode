/*
	Contains Duplicate III 
	Given an array of integers, find out whether there are two distinct indices i and j 
	in the array such that the difference between nums[i] and nums[j] is at most t and 
	the difference between i and j is at most k.
	Tags: Binary Search, TreeSet, SortedSet

*/

/*
    参考LeetCode Discuss：https://leetcode.com/discuss/38177/java-o-n-lg-k-solution

    TreeSet数据结构（Java）使用红黑树实现，是平衡二叉树的一种。

    该数据结构支持如下操作：

    1. floor()方法返set中≤给定元素的最大元素；如果不存在这样的元素，则返回 null。

    2. ceiling()方法返回set中≥给定元素的最小元素；如果不存在这样的元素，则返回 null。
*/
import java.util.SortedSet;

public class Solution {

    //Solution1
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        //input check
        if(k<1 || t<0 || nums==null || nums.length<2) return false;
        
        SortedSet<Long> set = new TreeSet<Long>();
        
        for(int j=0; j<nums.length; j++) {
            SortedSet<Long> subSet =  set.subSet((long)nums[j] - t, (long)nums[j] + t + 1);
            if(!subSet.isEmpty()) return true;
            
            if(j >= k) {
                set.remove((long)nums[j - k]);
            }
            set.add((long)nums[j]);
        }
        return false;
    }


    //Solution2
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 1 || t < 0) {
            return false;
        }
        TreeSet<Integer> set = new TreeSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            if ((set.floor(n) != null && n <= t + set.floor(n)) || (set.ceiling(n) != null && set.ceiling(n) <= t + n)) {
                return true;
            }
            set.add(n);
            if (i >= k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }


}
