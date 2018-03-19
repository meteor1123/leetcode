/*
	Summary Ranges 
	Given a sorted integer array without duplicates, return the summary of its ranges.

	For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
*/

//Concise best
public class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            while (i + 1 < nums.length && nums[i + 1] - nums[i] == 1) {
                i++;
            }
            if (val != nums[i]) {
                res.add(val + "->" + nums[i]);
            } else {
                res.add(val + "");
            }
        }
        return res;
    }
}

//My self
public class Solution {
	public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<String>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        if (nums.length == 1) {
            res.add("" + nums[0]);
            return res;
        }
        int left = nums[0];
        int right = 0;
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            right = nums[i];
            if (nums[i] > nums[i - 1] + 1) {
                if (count == 0) {
                    res.add("" + left); 
                } else {
                    res.add("" + left + "->" + (left + count));
                    count = 0;
                }
                left = right;
                if (i + 1 == nums.length) {
                    res.add("" + right);
                }
            } else {
                count++;
            }
        }
        //if count
        if (count != 0) {
            res.add("" + left + "->" + (left + count));
        }
        return res;
    }
}

