/*
	Wiggle Sort II
	Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

	Example:
	(1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6]. 
	(2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].

	Note:
	You may assume all input has valid answer.

	Follow Up:
	Can you do it in O(n) time and/or in-place with O(1) extra space?
 */


//Solution1: O(nlogn) time, O(n) space
public class Solution {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int[] copy = Arrays.copyOf(nums, nums.length);
        int smallIndex = (nums.length + 1) / 2 - 1;
        int largeIndex = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                nums[i] = copy[smallIndex - i / 2];
            } else {
                nums[i] = copy[largeIndex - i / 2];
            }
        }
    }
}



//Solution3: O(n) time, O(1) space
//https://leetcode.com/problems/wiggle-sort-ii/discuss/77682/Step-by-step-explanation-of-index-mapping-in-Java
// 大于中位数， 左 - 右， 奇
// 小于中位数， 右 - 左， 偶
class Solution {
   public void wiggleSort(int[] nums) {
        int median = findKthLargest(nums, (nums.length + 1) / 2);
        int n = nums.length;

        int left = 0, i = 0, right = n - 1;

        while (i <= right) {

            if (nums[newIndex(i,n)] > median) {
                swap(nums, newIndex(left++, n), newIndex(i++, n));
            }
            else if (nums[newIndex(i,n)] < median) {
                swap(nums, newIndex(right--, n), newIndex(i, n));
            }
            else {
                i++;
            }
        }


    }

    private int newIndex(int index, int n) {
        return (1 + 2*index) % (n | 1);
    }
}



//Solution2: O(n) time
import java.util.Random;
public class Solution {
    public void wiggleSort(int[] nums) {
       if (nums == null || nums.length == 0) {
           return;
       }
       int n = nums.length;
       randomShuffle(nums);
       double median = findMedian(nums);
       int firstHalfLen, secondHalfLen;
       if (n % 2 == 0) {
           firstHalfLen = n / 2;
       } else {
           firstHalfLen = n / 2 + 1;
       }
       secondHalfLen = n / 2;
       List<Integer> firstHalf = new ArrayList<>();
       List<Integer> secondHalf = new ArrayList<>();
       
       for (int i = 0; i < nums.length; i++) {
           if ((double)nums[i] < median) {
               firstHalf.add(nums[i]);
           } else if ((double)nums[i] > median) {
               secondHalf.add(nums[i]);
           }
       }
       
       while (firstHalf.size() < firstHalfLen) {
           firstHalf.add((int)median);
       }
       while (secondHalf.size() < secondHalfLen) {
           secondHalf.add((int)median);
       }
       
       for (int i = 0; i < firstHalf.size(); i++) {
           nums[i * 2] = firstHalf.get(firstHalf.size() - 1 - i)
           ;//从后往前加，因为在smaller list中median add在后面，large list中median也在后面，要避免这俩个median的冲突，因此要把smaller要从后遍历，large从前遍历，保证large一定大于smaller，
       }
       
       for (int i = 0; i < secondHalf.size(); i++) {
           nums[i * 2 + 1] = secondHalf.get(i);
       }
    }
    
    private double findMedian(int[] nums) {
        if(nums.length % 2 == 1) {
            return (double) findKth(nums, 0, nums.length-1, nums.length/2);
        } else {
            return ( 
                     (double) findKth(nums, 0, nums.length-1, nums.length/2 - 1) +
                     (double) findKth(nums, 0, nums.length-1, nums.length/2) 
                   ) / 2;
        }
    }
    private int findKth(int[] nums, int low, int high, int k) {
        int l = low;
        int r = high;
        int pivot = high;
        while (true) {
            while(l < r && nums[l] < nums[pivot]) {
                l++;
            }
            while(l < r && nums[r] >= nums[pivot]) {
                r--;
            }
            if (l == r) {
                break;
            }
            swap(nums, l, r);
        }
        swap(nums, l, high);
        if (l == k) {
            return nums[l];
        } else if (l < k) {
            return findKth(nums, l + 1, high, k);
        } else {
            return findKth(nums, low, l - 1, k);
        }
    }
    private void randomShuffle(int[] nums) {
        Random rm = new Random();
        for (int i = 0; i < nums.length - 1; i++) {
            int j = i + rm.nextInt(nums.length - i);
            swap(nums, i, j);
        }
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
