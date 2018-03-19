/* 	
	Maximum Subarray 
	Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

	For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
	the contiguous subarray [4,−1,2,1] has the largest sum = 6.

	More practice:
		If you have figured out the O(n) solution, 
		try coding another solution using the divide and conquer approach, which is more subtle.
	Tags: Dp, Array, Divide and Conquer
*/

/*
	Solution:
	这道题要求 求连续的数组值，加和最大。

	 试想一下，如果我们从头遍历这个数组。对于数组中的其中一个元素，它只有两个选择：

	 1. 要么加入之前的数组加和之中（跟别人一组）

	 2. 要么自己单立一个数组（自己单开一组）

	 所以对于这个元素应该如何选择，就看他能对哪个组的贡献大。如果跟别人一组，能让总加和变大，还是跟别人一组好了；
	 如果自己起个头一组，自己的值比之前加和的值还要大，那么还是自己单开一组好了。

	所以利用local记录每一轮sum的最大值，然后维护一个全局最大值global，也就是答案。
*/
public class Solution {
	//solution1  维护一个局部最大，和全局最大值，局部最大值每次 用 local 和 local + A[i]相比
	public int maxSubArray(int[] A) {
		// use a local to store a local maximum,
		// and use a global to store a global maximum.
		int local = A[0];
		int global = A[0]
		for (int i = 1; i < A.length; i++) {
			local = Math.max(local, A[i] + local);
			global = Math.max(local, global);
		}
		return max;
	}

	//solution2 Kadane算法
	public int maxSubArray(int[] A) {
		int maxEndHere = 0;
		int maxSofar = Integer.MIN_VALUE;

		for (int i = 0; i < A.length; i++) {
			if (maxEndHere < 0)
				maxEndHere = 0;
			maxEndHere = maxEndHere + A[i];
			maxSofar = Math.max(maxSofar, maxEndHere);
		}
		return maxSofar;
	}

	//solution3 Divide and Conquer
	public int maxSubArray(int[] A) {
		return divideConquer(0, A.length - 1, A);
	}

	public int divideConquer(int low, int high, int[] A) {
		// 递归终止条件1：下标相等 直接返回该下标的数
		if (low == high)
			return A[low];
		// 递归终止条件2：只有2个元素，最大值 肯定只能是 low， high，low + high ，3者中的最大
		if (low == high - 1)
			return Math.max(A[low] + A[high], Math.max(A[low], A[high]));
		int mid = (low + high) / 2;
		//左边最大
		int leftSubMax = divideConquer(low, mid - 1, A);
		//右边最大
		int rightSubMax = divideConquer(mid + 1, high, A);
		//跨越中点的连续最大
		int midMax = A[mid];

		int temp = midMax;
		//从mid到low一直加，寻找最大
		for (int i = mid - 1; i >= low; i--) {
			temp = temp + A[i];
			if (temp > midMax)
				midMax = temp;
		}
		
		temp = midMax;
		//从mid到high 一直加，寻找最大
		for (int i = mid + 1; i <= high; i++) {
			temp = temp + A[i];
			if (temp > midMax)
				midMax = temp;
		}

		return Math.max(midMax, Math.max(leftSubMax, rightSubMax));
	}

}