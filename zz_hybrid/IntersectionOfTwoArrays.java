package LeetCode;

public class IntersectionOfTwoArrays {
	public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<Integer>();
        Arrays.sort(nums2);
        for (int n : nums1) {
            set1.add(n);
        }
        int[] res = new int[set1.size()];
        for (int n : set1) {
            int i = 0;
            while (i < set1.size()) {
                if (found(nums2, n)) {
                    res[i] = n;
                }
                i++;
            }
        }
        return res;
    }
    private boolean found(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (nums[left] == target) {
            return true;
        } else if (nums[right] == target) {
            return true;
        }
        return false;
    }
}
