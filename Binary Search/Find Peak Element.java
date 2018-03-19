/*
	Find Peak Element 
	A peak element is an element that is greater than its neighbors.
	Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
	The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
	You may imagine that num[-1] = num[n] = -∞.
	For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
*/

public class Solution {
    //有很多peek， 任意返回一个即可
    public int findPeakElement(int[] num) {
    	int left = 0;
        int right = num.length - 1;  
        while (left <= right) {  
            if (left == right)  
                return left;  
            int mid = (left + right) / 2;  
            if (num[mid] < num[mid + 1])  
                left = mid + 1;  
            else  
                right = mid;  
        } 
        return -1;
    }
}


/*
    This problem is similar to Local Minimum. And according to the given condition, 
    num[i] != num[i+1], there must exist a O(logN) solution. 
    So we use binary search for this problem.

    If num[i-1] < num[i] > num[i+1], then num[i] is peak
    If num[i-1] < num[i] < num[i+1], then num[i+1...n-1] must contains a peak
    If num[i-1] > num[i] > num[i+1], then num[0...i-1] must contains a peak
    If num[i-1] > num[i] < num[i+1], then both sides have peak (n is num.length)
    Here is the code
*/
public class Solution {
    public int findPeakElement(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[mid - 1]) {
                start = mid;
            } else {
                end = mid;
            } 
        }
        return nums[start] > nums[end] ? start : end;
    }



// O(n) linear scan
public class Solution {
    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1])
                return i;
        }
        return nums.length - 1;
    }
}