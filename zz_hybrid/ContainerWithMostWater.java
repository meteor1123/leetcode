package LeetCode;

public class ContainerWithMostWater {
	public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int curArea = 0;
        int result = 0;
        while (left < right) {
            curArea = Math.min(height[left], height[right]) * (right - left);
            result = Math.max(result, curArea);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return result;
    }
	public static void main(String[] args) {
		ContainerWithMostWater test = new ContainerWithMostWater();
		int[] height = {3,2,1,4,2};
		int res = test.maxArea(height);
		System.out.println(res);
	}
}
