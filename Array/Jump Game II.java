/*
	Jump Game II
	Given an array of non-negative integers, you are initially positioned at the first index of the array.

	Each element in the array represents your maximum jump length at that position.
	Your goal is to reach the last index in the minimum number of jumps.

	For example:
	Given array A = [2,3,1,1,4]

	The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
*/

public class Solution {
	//DP 
	//steps mean the minimum step from 0 to i
    public int jump(int[] A) {
        int[] steps = new int[A.length];
        steps[0] = 0;
        for (int i = 1; i < A.length; i++) {
            steps[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (steps[i] != Integer.MAX_VALUE && A[j] + j > i) {
                    steps[i] = steps[j] + 1;
                    break;
                }
            }
        }
        return steps[A.length - 1];
    }

    //Greedy
    /*
        Steps: 目前为止的jump数
        lastCover: 从A[0] 进行steps次jump后达到的最大范围
        maxCover: 从0 ~ i 这i + 1 个元素中能达到的最大范围
        when lastCover < i 说明steps次jump已经不足以覆盖当前第i个元素，因此需要增加一次
        jump，使其达到纪录的maxCover；
    */
    public int jump(int[] A) {
    	if (A == null || A.length == 0) {
    		return 0;
    	}
    	int maxCover = 0;
    	int steps = 0;
    	int lastCover = 0;
    	for (int i = 0; i < A.length && i <= maxCover; i++) {
    		if (i > lastCover) {
    			steps++;
    			lastCover = maxCover;
    		}

            //maxCover = Math.max(maxCover, A[i] + i);
    		if (A[i] + i > maxCover) {
    			maxCover = A[i] + i;
    		}
    	}
    	if (maxCover < A.length - 1) {
    		return 0;
    	}
    	return steps;
    }
}

//Solution3 greedy 
public class Solution {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxCover = 0;
        int steps = 0;
        int lastCover = 0;
        for (int i = 0; i < nums.length; i++) {
            if(i > lastCover) {
                steps++;
                lastCover = maxCover;
            }
            maxCover = Math.max(maxCover, nums[i] + i);
        }
        return steps;
    }
}