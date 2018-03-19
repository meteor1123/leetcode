/*
	Search in Rotated Sorted Array 
	Suppose a sorted array is rotated at some pivot unknown to you beforehand.
	(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
	You are given a target value to search. If found in the array return its index, otherwise return -1.
	You may assume no duplicate exists in the array.
*/


/*
	Solution:
*/
public class Solution {
	//solution 1
	public int search(int[] A, int target) {
		int start = 0;
		int end = A.length - 1;

		while (start <= end) {
			int mid = (start + end) / 2;
			if (A[mid] == target)
				return mid;
			if (A[start] < A[mid]) {
                // if the first half is in-order, 
				if (A[start] <= target && target < A[mid])
					end = mid - 1;// if target is in the range of the first half
				else 
					start = mid + 1;
			} else {
                // if the second half is in order
				if (A[mid] < target && target <= A[end]) // target is in the range of the second half
					start = mid + 1;
				else 
					end = mid - 1;
			}
		}
	}

	//solution 2
	public int search(int[] A, int target) {
		int start = 0;
        int end = A.length - 1;
        int mid;

        //为什么用这种方式？满足binary search的第一个规则，要可以逐步逼近边界跳出。
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            //denote we find the target
            if (A[mid] == target) {
                return mid;
            }
            if (A[start] < A[mid]) {
            	// if the first half is in-order, 
                if (A[start] <= target && target <= A[mid]) {
                    end = mid; // if target is in the range of the first half
                } else {
                    start = mid;
                }
            //this situation means the array had already rotated
            } else {
            	// if the second half is in order
                if (A[mid] <= target && target <= A[end]) { // target is in the range of the second half
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }
        if (A[start] == target) 
            return start;
        if (A[end] == target) 
            return end;
        return -1;
	}

}

//Solution 3
public class Solution {
    	public int search(int[] A, int target) {
		int start = 0;
        int end = A.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (A[mid] == target) {
                return mid;
            }
            if (A[start] <= A[mid]) {
                // if the first half is in-order, <= since mid may be equal to left when there are only two elements
                if (A[start] <= target && target < A[mid]) {
                    end = mid - 1;// if target is in the range of the first half
                } else {
                    start = mid + 1;
                }
            } else {// if the second half is in order
                if (A[mid] < target && target <= A[end]) {// target is in the range of the second half
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
	}
}