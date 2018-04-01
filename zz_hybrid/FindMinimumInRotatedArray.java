package LeetCode;

public class FindMinimumInRotatedArray {
	public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums[0] < nums[nums.length - 1]) return nums[0];
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            } else {
                if (nums[mid] > nums[left]) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
        }
        return nums[left] < nums[right]? nums[left] : nums[right];
    }
	public static void main(String[] args) {
		FindMinimumInRotatedArray test = new FindMinimumInRotatedArray();
		int nums[] = {3,5,7,9,12,15,1,2};
		int res = test.findMin(nums);
		System.out.println(res);
	}
}
