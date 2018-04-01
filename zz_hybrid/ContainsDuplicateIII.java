package LeetCode;

public class ContainsDuplicateIII {
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length < 2) return false;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < k; j++) {
                if (i + j < nums.length && Math.abs(nums[i] - nums[j]) <= t) {
                    return true;
                }
            }
        }
        return false;
    }
	public static void main(String[] args) {
		ContainsDuplicateIII test = new ContainsDuplicateIII();
		int[] nums = {2,2};
		boolean res = test.containsNearbyAlmostDuplicate(nums, 3, 0);
		System.out.println(res);
	}
}
