package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class SingleNumber {
	public int singleNumber(int[] nums) {
		if (nums.length < 2) return nums[nums.length - 1];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int n : nums) {
            Integer count = map.get(n);
            if (map.containsKey(n)) {
                map.put(n, count + 1);
            } else {
                map.put(n, 1);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) != 2) {
                return nums[i];
            }
        }
       return 0;
    }
	public static void main(String[] args) {
		SingleNumber test = new SingleNumber();
		int[] nums = {2,2,1};
		int res = test.singleNumber(nums);
		System.out.println(res);
	}
}
