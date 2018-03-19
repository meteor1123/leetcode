/*
	Count of Smaller Numbers After Self
	You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

	Example:

	Given nums = [5, 2, 6, 1]

	To the right of 5 there are 2 smaller elements (2 and 1).
	To the right of 2 there is only 1 smaller element (1).
	To the right of 6 there is 1 smaller element (1).
	To the right of 1 there is 0 smaller element.
	Return the array [2, 1, 1, 0].

	Tags: Divide and Conquer Binary Indexed Tree Segment Tree Binary Search Tree

*/

//Solution1: Binary Search
/*
    Solution: 1. 从最右边开始遍历，每次将元素插入到顺序数组，将这个插入位置返回，就是该元素的smaller number
*/
public class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> sorted = new ArrayList<>();
        Integer[] indexArr = new Integer[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            //有序的插入位置就是要找的smaller number
            indexArr[i] = findIndex(sorted, nums[i]);
            sorted.add(indexArr[i], nums[i]);
        }
        return Arrays.asList(indexArr);
    }
    
    //Binary Search 找插入位置
    private int findIndex(List<Integer> nums, int target) {
        if (nums.size() == 0) {
            return 0;
        }
        int start = 0;
        int end = nums.size() - 1;
        if (nums.get(end) < target) {
            return end + 1;
        }
        if (nums.get(start) > target) {
            return 0;
        }
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums.get(mid) < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        if (nums.get(start) >= target) {
            return start;
        } else {
            return end;
        }
    }
}


//Solution2 构建BST
/*
    Traverse from nums[len - 1] to nums[0], and build a binary search tree, which stores:

    val: value of nums[i]
    count: if val == root.val, there will be count number of smaller numbers on the right
    Run time is 10ms. Hope it helps!
*/
public class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        TreeNode root = new TreeNode(nums[nums.length - 1]);
        res.add(0);
        for(int i = nums.length - 2; i >= 0; i--) {
            int count = insertNode(root, nums[i]);
            res.add(count);
        }
        Collections.reverse(res);
        return res;
    }

    public int insertNode(TreeNode root, int val) {
        int thisCount = 0;
        while(true) {
            if(val <= root.val) {
                root.count++;
                if(root.left == null) {
                    root.left = new TreeNode(val); break;
                } else {
                    root = root.left;
                }
            } else {
                thisCount += root.count;
                if(root.right == null) {
                    root.right = new TreeNode(val); break;
                } else {
                    root = root.right;
                }
            }
        }
        return thisCount;
    }
//