/*
	Find Minimum in Rotated Sorted Array
	Suppose a sorted array is rotated at some pivot unknown to you beforehand.
	(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
	Find the minimum element.
	You may assume no duplicate exists in the array.
*/

public class Solution {
    public int findMin(int[] num) {
        int start = 0;
        int end = num.length - 1;
        int mid;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            // the mid value large than end , means left side is sorted and rotated
            if (num[mid] >= num[end]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        //at last we just compare the num[]
        if (num[start] < num[end]) {
            return num[start];
        } else {
            return num[end];
        }
    }
}