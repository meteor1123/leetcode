/*
	Wiggle Sort

	Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
	For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
*/


/*
	Solution:
	https://www.quora.com/What-is-an-efficient-method-to-wiggle-sort-an-array

	You can do this in linear time in a single pass. 
	The key is to notice that you only need to preserve comparison relations between immediate neighbours. Here's how:

	The first two elements are already wiggle-sorted, because their order doesn't matter.

	Let's say the first n positions of the array are already wiggle-sorted, and A[n-1] < A[n] (which implies if A[n-2] exists, then A[n-2] > A[n-1]).

	Let the sorted order of (A[n-1], A[n], A[n+1]) be (m1, m2, m3).
	I claim that if you assign A[n-1] = m1, A[n] = m3, A[n+1] = m2 the first n+1 elements will be wiggle-sorted.

	Proof:
	- The first n-2 elements are not changed, so from the assumption that they were wiggle-sorted, they are still wiggle-sorted.
	- We had A[n-2] > A[n-1] before, so we definitely have A[n-2] > m1.
	- We have m1 < m3, m3 > m2, so A[n-1], A[n], A[n+1] continue the wiggle pattern.

	If A[n-1] > A[n], you want to assign A[n-1]=m3, A[n]=m1, A[n+1]=m2, and the proof is the same. 

	Repeat this until all elements are wiggle-sorted.

	In your example:

	n= 2: 1, 2, 8, 9, 3, 5 (starting array)
	n= 3: 1, 8, 2, 9, 3, 5
	n = 4: 1, 9, 2, 8, 3, 5
	n = 5: 1, 9, 2, 8, 3, 5
	n = 6: 1, 9, 2, 8, 3, 5
*/


/*
	假设n-1是偶， n为奇，并且0...A[n - 1]满足wiggle sort
		
		if A[n-1] < A[n], 判断A[n + 1] 是否大于A[n], 
			1）if A[n + 1]大于A[n]，则将A[n+1]和[n] swap， 满足A[n-1] < A[n+1] > A[n]，wiggle sort
			2）if A[n + 1]小于A[n]， 则满足A[n - 1] < A[n] > A[n + 1]，一样是wiggle sort

		if A[n - 1] > A[n],如果 A[n - 2] 和 A[n - 1]满足 wiggle sort， 则一定有A[n - 2] > A[n - 1] > A[n] ,
			调换成A[n- 2] > A[n] < A[n - 1] 满足wiggle sort

	综上所示，每个点只需要处理邻居节点的关系，即可满足wiggle sort
*/

/*
	 我们注意到在数组index= 0, 和末尾的时候，A[index] 一定是大于A[-1]或者 A【length] 的，这两个地方可以看成负无穷
	 求中间的数可以简化成下面的逻辑
	 1） 当i为奇数时， nums[i] 要大于nums[i - 1]否则 swap
	 2） 当i为偶数时， nums[i] 要小于nums[i - 1]否则swap
*/
public class Solution {
    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
           int a = nums[i - 1];
           if ((i % 2 == 1) == (nums[i] < a)) {// i % 2 == 1 的作用是在奇数位置的时候需要判断，因为 a[i*2] <= a[i*2 + 1] >=a[i*2 + 2]
               nums[i - 1] = nums[i];
               nums[i] = a;
           }
        }
    }
}


//prefer
public class Solution {
    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (i % 2 == 1) {
                if (nums[i - 1] > nums[i]) {
                    swap(nums, i);
                } 
            } else if(nums[i - 1] < nums[i]) {
                swap(nums, i);
            }
        }
    }
    public void swap(int[] nums, int i) {
        int temp = nums[i];
        nums[i] = nums[i - 1];
        nums[i - 1] = temp;
    }
}