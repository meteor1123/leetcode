/*
	 Intersection of Two Arrays II
	 Given two arrays, write a function to compute their intersection.

	Example:
	Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

	Note:
	Each element in the result should appear as many times as it shows in both arrays.
	The result can be in any order.
	Follow up:
	What if the given array is already sorted? How would you optimize your algorithm?
	What if nums1's size is small compared to nums2's size? Which algorithm is better?
	What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */
/*
Follow up:
	(1)What if the given array is already sorted? How would you optimize your algorithm?
     Using two pointer, start from two array respectively, if find equals just add result, else move the smaller pointer.

	(2)What if nums1's size is small compared to nums2's size? Which algorithm is better?
	   Suppose lengths of two arrays are N and M, the time complexity of my solution is O(N+M) and the space complexity if O(N) considering the hash. 
     So it’s better to use the smaller array to construct the counter hash.
     Well, as we are calculating the intersection of two arrays, the order of array doesn’t matter. We are totally free to swap to arrays.

	(3)What if elements of nums2 are stored on disk, and the memory is limited 
	   such that you cannot load all elements into the memory at once?
     
     If only nums2 cannot fit in memory, put all elements of nums1 into a HashMap, read chunks of array that fit into the memory, and record the intersections.
     If both nums1 and nums2 are so huge that neither fit into the memory, sort them individually (external sort), then read 2 elements from each array at a time in memory, record intersections.
     
     1. Store the two strings in distributed system(whether self designed or not), then using MapReduce technique to solve the problem;
     2. Processing the Strings by chunk, which fits the memory, then deal with each chunk of data at a time;
     3. Processing the Strings by streaming, then check.

 */
//Solution1: prefer, one HashMap O(n)
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0)
            return new int[]{};
        HashMap<Integer, Integer> map = new HashMap();
        List<Integer> item = new ArrayList();
        for (int num : nums2) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        for (int num : nums1) {
            if (map.get(num) != null && map.get(num) > 0) {
                item.add(num);
                map.put(num, map.get(num) - 1);
            }
        }
        
        int[] res = new int[item.size()];
        
        int index = 0;
        for (int num : item) {
            res[index++] = num;
        }
        return res;
    }
}

//Solution2: sort + two pointer O(nlogn)
public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
       Arrays.sort(nums1);
       Arrays.sort(nums2);
       List<Integer> resList = new ArrayList<>();
       int i = 0;
       int j = 0;
       while (i < nums1.length && j < nums2.length) {
           if (nums1[i] > nums2[j]) {
               j++;
           } else if (nums1[i] < nums2[j]) {
               i++;
           } else {
               resList.add(nums2[j]);
               i++;
               j++;
           }
       }
       int k = 0;
       int[] res = new int[resList.size()];
       for (int num : resList) {
           res[k++] = num;
       }
       return res;
    }
}