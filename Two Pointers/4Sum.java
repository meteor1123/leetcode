/*
    Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? 
    Find all unique quadruplets in the array which gives the sum of target.

    Note:
    Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
    The solution set must not contain duplicate quadruplets.

    For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
    A solution set is:
    (-1,  0, 0, 1)
    (-2, -1, 1, 2)
    (-2,  0, 0, 2)
*/
    
/*
    Solution: same as 3sum,but need more pointer to travers the whole array
    Time complexity: O(n3)
*/

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList();
        HashSet<List<Integer>> set = new HashSet();
        
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                int left = j + 1;
                int right = nums.length - 1;
                
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                   if (sum == target) {
                       List<Integer> item = new ArrayList();
                       item.add(nums[i]);
                       item.add(nums[j]);
                       item.add(nums[left]);
                       item.add(nums[right]);
                       if (!set.contains(item)) {
                          res.add(item);
                          set.add(item);
                       }
                       left++;
                       right--;
                    } else if (sum < target) {
                       left++;
                       // 剪枝加速
                       while (left < right && nums[left] == nums[left - 1]) {
                           left++;
                       }
                    } else {
                       right--;
                       // 剪枝加速
                       while (right > left && nums[right] == nums[right + 1]) {
                           right--;
                     }
                   }
                }
            }
        }
        return res;
    }
}