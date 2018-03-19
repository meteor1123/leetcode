/*
	Majority Element II
	Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. The algorithm should run in linear time and in O(1) space. 
*/

/*
	n1, n2 are 2 distinct numbers. c1, c2 are the count of the 2 numbers. 
	Whenever encounter 3 different numbers, cancel them by decreasing the count. 
	And the remaining 2 numbers (or 1 or 0) are candidates. 
	Scan the array one more time to determine the result.
*/


//Prefer
public class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int count1 = 0, count2 = 0;
        int candidate1 = 0, candidate2 = 0;
        for (int val : nums) {
            if (candidate1 == val) {
                count1++;
            } else if (candidate2 == val) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = val;
                count1++;
            } else if (count2 == 0) {
                candidate2 = val;
                count2++;
            } else {
                count1--;
                count2--;
            }
        }
        
        if (candidate1 == candidate2) {
            res.add(candidate1);
            return res;
        }
        count1 = count2 = 0;
        for (int val : nums) {
            if (val == candidate1) {
                count1++;
            }
            if (val == candidate2) {
                count2++;
            }
        }
        
        if (count1 > nums.length / 3) {
            res.add(candidate1);
        }
        if (count2 > nums.length / 3) {
            res.add(candidate2);
        }
        return res;
    }
}