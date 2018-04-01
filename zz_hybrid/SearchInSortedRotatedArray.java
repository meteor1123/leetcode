package LeetCode;

public class SearchInSortedRotatedArray {
	public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        	while (left + 1 < right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] >= nums[right]) {
                    if (target < nums[mid] && target >= nums[left]) {
                        right = mid;
                    } else {
                        left = mid;
                    }
                } else {
                    if (nums[mid] < target && target <= nums[right]) {
                        left = mid;
                    } else {
                        right = mid;
                    }
                }
            }
        if (nums[left] == target) {
            return left;
        } else if (nums[right] == target) {
            return right;
        } else {
        	return -1;
        }
    }
	public static void main(String[] args) {
		SearchInSortedRotatedArray test = new SearchInSortedRotatedArray();
		int[] nums = {1,3,5};
		int res = test.search(nums, 3);
		System.out.println(res);
	}
}
