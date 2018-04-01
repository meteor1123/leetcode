package LeetCode;

public class RemoveDuplicatesFromSortedArray {
	public int removeDuplicates(int[] nums) {
        if (nums.length == 1) return 1;
        int resIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                i++;
            }
            nums[resIndex] = nums[i];
            resIndex++;
        }
        return resIndex;
    }
	public static void main(String[] args) {
		RemoveDuplicatesFromSortedArray test = new RemoveDuplicatesFromSortedArray();
		int[] nums = {1,1,2,3,3,3,4,4,4,4,5};
		int res = test.removeDuplicates(nums);
		System.out.println(res);
	}
}
