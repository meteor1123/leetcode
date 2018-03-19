/*
    Missing Ranges
    Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.

    For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
    Tags: Array
*/ 

/*
    这道题最关键的在于临界条件，
    需要判断lower 和 nums[0], upper 和nums[nums.length - 1]的关系. 我自己的傻办法设置了好多if判断。。
    
*/

//Solution1: elegance solution,
public class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        //通过判断pre 和 after的关系，检查mssing的 rang
        int pre = lower - 1;
        int after = 0;
        for (int i = 0; i <= nums.length; i++) {
            if (i == nums.length) {
                after = upper + 1;
            } else {
                after = nums[i];
            }
            //pre + 1 = after, 则没有缺失， 如果 pre + 2 = after，则只缺失1个, pre + 1
            if (pre + 2 == after) {
                res.add(String.valueOf(pre + 1));
            //pre + 2 < after, 则缺失的范围大于1 pre  pre + 1->after - 1  after
            } else if (pre + 2 < after) {
                res.add(String.valueOf(pre + 1) + "->" + String.valueOf(after - 1));
            }
            pre = after;
        }
        return res;
    }
}

//Solution2: My solution
public class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            if (lower == upper) {
                String s = String.valueOf(lower);
                res.add(s);
            } else {
                String s = String.valueOf(lower) + "->" + String.valueOf(upper);
                res.add(s);
            }
            return res;
            
        }
        if (nums[0] - lower == 1) {
            String s = String.valueOf(lower);
            res.add(s);
        } else if (nums[0] - lower > 1) {
            String s = String.valueOf(lower) + "->" + String.valueOf(nums[0] - 1);
            res.add(s);
        }
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == 2 ) {
                String s = String.valueOf(nums[i] - 1);
                res.add(s);
            } else if (nums[i] - nums[i - 1] > 2) {
                String s = String.valueOf(nums[i - 1] + 1) + "->" + String.valueOf(nums[i] - 1);
                res.add(s);
            }
        }
        int last = nums[nums.length - 1];
        if (upper - last == 1) {
            String s = String.valueOf(upper);
            res.add(s);
        } else if (upper - last > 1) {
            String s = String.valueOf(last + 1) + "->" + String.valueOf(upper);
            res.add(s);
        }
        return res;
    }
}

