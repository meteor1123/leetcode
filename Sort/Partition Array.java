/*
	Partition Array
    Given an array "nums" of integers and an int "k", Partition the array (i.e move the elements in "nums") such that,

    * All elements < k are moved to the left

    * All elements >= k are moved to the right

    Return the partitioning Index, i.e the first index "i" nums[i] >= k.

    Note
    You should do really partition in array "nums" instead of just counting the numbers of integers smaller than k.

    If all elements in "nums" are smaller than k, then return "nums.length"

    Example
    If nums=[3,2,2,1] and k=2, a valid answer is 1.

    Challenge
    Can you partition the array in-place and in O(n)?
*/

/*
    Quick Sort 一样的做法，只是有两种情况特殊处理：我第一次做的时候没有考虑到

1. all elements in nums are greater than or equal to k, l pointer never shift， should return r

2. all elements in nums are smaller than k, r pointer never shift, shoud return r+1
*/
public class Solution {
	/** 
     *@param nums: The integer array you should partition
     *@param k: As description
     *return: The index after partition
     */

    
    public int partitionArray(ArrayList<Integer> nums, int k) {
          //write your code here
        if (nums==null || nums.size()==0) return 0;
        int l=0, r=nums.size()-1;
        while (true) {
             while (l<r && nums.get(r)>=k) {
                 r--;
             }
             while (l<r && nums.get(l)<k) {
                 l++;
             }
             if (l == r) break;
             swap(l, r, nums);
         }
         if (l==0 && nums.get(l)>=k) return r;
         if (r==nums.size()-1 && nums.get(l)<k) return r+1;
         return r+1;
    }
     
    public void swap(int l, int r, ArrayList<Integer> nums) {
         int temp = nums.get(l);
         nums.set(l, nums.get(r).intValue());
         nums.set(r, temp);
    }
}

