/*
	Remove Element
	Given an array and a value, remove all instances of that value in place and return the new length.
	The order of elements can be changed. It doesn't matter what you leave beyond the new length.
*/



public class Solution {

    /*
        这道题有几个关键字需要注意: in place, the order of elements can be changed.所以我们可以尝试用two pointers，
        一个记录remove过后的数组最后一个元素位置，一个用来遍历整个数组，判断条件就是比较该元素和target的值，
        如果不相等就把它copy给另一个pointer.因为题目只需要求最后的数组长度，所以返回最后记录位置就可以了。
        复杂度： 时间复杂度O(n)，空间复杂度O(1).
    */
    

    //Solution1 minimum swap time! best! no order
    public int removeElement(int[] nums, int val) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            if (nums[start] != val) {
                start++;
            } else if (nums[end] == val) {
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

    //Solution2 concise but not minimum swap time,order
    public int removeElement(int[] A, int elem) {
        if (A == null || A.length == 0) {
            return 0;
        }
        //index record the length after remove the elem
        int index = 0;
        //traverse the arry
        for (int i = 0; i < A.length; i++) {
            //if A[i] don't equals the elem, that we put this A[i] to A[index],and index ++
            if (A[i] != elem) {
                A[index] = A[i];
                index++;//
            }
        }
        return index;
    }
    
    //Solution3
    public int removeElement(int[] A, int elem) {
    	if (A == null || A.length == 0)
    		return 0;
    	int len = A.length - 1;
    	for (int i = 0; i < len; i++) {
    		while (A[i] == elem && i < len) {
    			A[i] = A[--len];
    		}
    	}
    	return len;
    }





}