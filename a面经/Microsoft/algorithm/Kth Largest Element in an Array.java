/*
	Kth Largest Element in an Array
	Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

	For example,
	Given [3,2,1,5,6,4] and k = 2, return 5.

	Note: 
	You may assume k is always valid, 1 ≤ k ≤ array's length.
	Tags: Divide and Conquer, Heap
*/

/*
	https://leetcode.com/discuss/36966/solution-explained
*/
public class Solution {
	//1.Using heap
	//  O(N lg K) running time + O(k) memory
	public int findKthLargest(int[] nums, int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for (int val : nums) {
			pq.offer(val);
			if (pq.size() > k) {
				pq.poll();
			}
		}
		return pq.peek();
	}
	
	//2.quick select
	//O(N) best case / O(N^2) worst case running time + O(1) memory

	public int findKthLargest(int[] nums, int k) {
		k = nums.length - k;
		int low = 0;
		int high = nums.length - 1;
		while (low < high) {
			int j = partition(nums, low, high);
			if (j < k) {
				low = j + 1;
			} else if (j > k) {
				high = j - 1;
			} else {
				break;
			}
		}
		return nums[k];
	}

	// private int partition(int[] a, int low, int high) {
	// 	int i = low;
	// 	int j = high + 1;
	// 	while (true) {
	// 		while (i < high && a[++i] < a[low]);//以 A[low] 为pivot， i从左走， j从右走，假如A[i] 小于pivot 则一直往后走，会停到第一个比pivot大的位置
	// 		while (j > low && a[low] < a[--j]);//假如A[j] 大于pivot，则一直往前走，会停到第一个比pivot小的位置， 然后交换，则最后low 一定是 第j + 1小的元素
	// 		if (i >= j) {
	// 			break;
	// 		}
	// 		swap(a, i, j);
	// 	}
	// 	swap(a, low, j);
	// 	return j;
	// }

	//easy understand
	public int partition(int[] a, int low, int high) {
       int i = low;
       int j = high;
       while (true) {
           while (i < j && a[i] < a[high]) { //以 A[high] 为pivot， i从左走， j从右走，假如A[i] 小于pivot 则一直往后走，会停到第一个比pivot大的位置
               i++;
           }
           while (i < j && a[j] >= a[high]) {
               j--;
           }
           if (i == j) {
               break;
           }
           swap(a, i, j);
       }
       swap(a, high, i);
       return i;
    }
	private void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] =tmp;
	}
}


//3,O(N) guaranteed running time + O(1) space
public int findKthLargest(int[] nums, int k) {
        shuffle(nums);
        k = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            final int j = partition(nums, lo, hi);
            if(j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                break;
            }
        }
        return nums[k];
}

private void shuffle(int a[]) {

        final Random random = new Random();
        for(int ind = 1; ind < a.length; ind++) {
            final int r = random.nextInt(ind + 1);
            exch(a, ind, r);
        }
    }
