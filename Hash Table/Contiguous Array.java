/*
	525. Contiguous Array

	Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

	Example 1:
	Input: [0,1]
	Output: 2
	Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
	Example 2:
	Input: [0,1,0]
	Output: 2
	Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
	Note: The length of the given binary array will not exceed 50,000.
*/

/*
	对于求子数组的问题，一定要想到求累积和。在这里怎么将子数组的和跟0和1的个数之间产生联系呢？
	我们需要用到一个trick，遇到1就加1，遇到0，就减1，这样如果某个子数组和为0，就说明0和1的个数相等。
	知道了这一点，我们用一个哈希表建立子数组之和跟结尾位置的坐标之间的映射。如果某个子数组之和在哈希表里存在了 -> 说明当前子数组减去哈希表中存的那个子数字，得到的结果是中间一段子数组之和，必然为0，说明0和1的个数相等，我们更新结果res
*/
class Solution {
    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length <= 1)
            return 0;
        HashMap<Integer, Integer> map = new HashMap();
        map.put(0, -1);
        int maxLen = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i] == 1 ? 1 : -1;
            if (map.containsKey(count)) {
                maxLen = Math.max(maxLen, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }
        
        return maxLen;
    }
}