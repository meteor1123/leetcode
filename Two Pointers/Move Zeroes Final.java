/*
	Move Zeroes
	Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

	For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

	Note:
	You must do this in-place without making a copy of the array.
	Minimize the total number of operations.
*/



//Solution1 non-swap retain order, minimun operation
public class Solution {
	//non-swap
	public void moveZeroes (int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}
		int count = 0;
		//use a count to record the number of non-zero number
		//every time when the number do not equal zero,
		//we set the arr[i] value to the count index ,and add the count 
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != 0) {
				//count is used to record the amount of non-zero number
				arr[count++] = arr[i];
			}
		}
		while (count < arr.length) {
			arr[count++] = 0;
		}
	}
}


//Solution2: minimum swap time, unorder!
public class Solution {
    public int moveZeroes(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            if (nums[start] != 0) {
                start++;
            } else if (nums[end] == 0) {
                end--;
            } else {
                swap(nums, start++, end--);
            }
        }
        return start;
    }
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

//Solution3: reduce the assign
public class Solution {
	public void moveZeroes (int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}
		int count = 0;
		//use a count to record the number of non-zero number
		//every time when the number do not equal zero,
		//we set the arr[i] value to the count index ,and add the count 
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != 0) {
				//count is used to record the amount of non-zero number
				if (i == count) {
				    count++;
				    continue;
				}
				arr[count++] = arr[i];
			}
		}
		while (count < arr.length) {
			arr[count++] = 0;
		}
	}
}

//Solution4: swap and the part of non-zero is  ordered
public class Solution {
	public void moveZeroes (int[] arr) {
		int len = arr.length;
		//use two pointer,
		//
		for (int i = 0; int j = 0; j < len; j++) {
			if (arr[j] != 0) {
				if (i < j) {
					swap(arr, i, j);
				}
				i++
			}
		}
	}

	public void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}

