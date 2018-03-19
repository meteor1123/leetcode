/*
	Intersection of Two Arrays

	Given two arrays, write a function to compute their intersection.

	Example:
	Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

	Note:
	Each element in the result must be unique.
	The result can be in any order.
 */



//Solution1: HashSet O(n) time, O(n) space
public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> intersect = new HashSet<>();
        
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i])) {
                intersect.add(nums2[i]);
            }
        }
        int[] res = new int[intersect.size()];
        int i = 0;
        for (Integer num : intersect) {
            res[i++] = num;
        }
        return res;
    }
}

//Solution2: HashSet O(nlogn) time
public int[] intersection(int[] nums1, int[] nums2) {
    Arrays.sort(nums1);
    Arrays.sort(nums2);
    Set<Integer> set = new HashSet<>();
    int i = 0;
    int j = 0;
    while (i < nums1.length && j < nums2.length) {
        if (nums1[i] > nums2[j]) {
            j++;
        } else if (nums1[i] < nums2[j]) {
            i++;
        } else {
            set.add(nums1[i]);
            i++;
            j++;
        }
    }
    int[] res = new int[set.size()];
    int k = 0;
    for (int num : set) {
        res[k++] = num;
    }
    return res;
}

