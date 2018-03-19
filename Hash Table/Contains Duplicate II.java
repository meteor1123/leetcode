/*
	Contains Duplicate II 
	Given an array of integers and an integer k, find out whether there there are two 
	distinct indices i and j in the array such that 
	nums[i] = nums[j] and the difference between i and j is at most k.
*/

public class Solution {
    //Solution1
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0;i < nums.length; i++) {
            if (i > k) 
            set.remove(nums[i - k - 1]);//Because the distance between i and i - k - 1 is large than k, so just let the nums[n-k-1] out
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }
}

//Solution2 by myself
public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (i - map.get(nums[i]) <= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;
    }
}

