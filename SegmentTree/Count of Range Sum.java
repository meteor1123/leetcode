/*
	Count of Range Sum
	Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
	Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.

	Note:
	A naive algorithm of O(n2) is trivial. You MUST do better than that.

	Example:
	Given nums = [-2, 5, -1], lower = -2, upper = 2,
	Return 3.
	The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.
 */

//Solution1: native method, O(n*n) timeout
/*
	Java - Naive Solution(这个做法为了让所有区间都能表示成一个区间减另一个区间，size额外增加了1，
	sums[i]定义为前i个元素之和，这样连只包含第一个元素的区间的和都可以表示为sums[1]-sums[0],这样写不用再分类讨论，挺好的)
 */
public class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] sum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (sum[j] - sum[i] >= lower && sum[j] - sum[i] <= upper) {
                    res++;
                }
            }
        }
        return res;
    }
}


//Solution2:mergesort, O(NlogN) running time best, but hardly understand
public class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] sums = new long[n + 1];
        for (int i = 0; i < n; ++i)
            sums[i + 1] = sums[i] + nums[i];
        return countWhileMergeSort(sums, 0, n + 1, lower, upper);
    }
    
    private int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
        if (end - start <= 1) return 0;
        int mid = (start + end) / 2;
        int count = countWhileMergeSort(sums, start, mid, lower, upper) 
                  + countWhileMergeSort(sums, mid, end, lower, upper);
        int j = mid, k = mid, t = mid, r = 0;
        long[] cache = new long[end - start];
        for (int i = start; i < mid; ++i, ++r) {
            while (k < end && sums[k] - sums[i] < lower) k++;
            while (j < end && sums[j] - sums[i] <= upper) j++;
            while (t < end && sums[t] < sums[i]) cache[r++] = sums[t++]; //start merging
            cache[r] = sums[i];
            count += j - k;
        }
        System.arraycopy(cache, 0, sums, start, r);
        return count;
    }
}


//Solution3: construct BST(Segment Tree)
/*
	默认方法：construct BST (好理解很多) ， Time: O(NlogN)

	这个做法是建立BST，把prefix sum作为TreeNode.val存进去，为了避免重复的TreeNode.val处理麻烦，设置一个count记录多少个重复TreeNode.val, 
	维护leftSize, 记录比该节点value小的节点个数，rightSize同理

	由于RangeSum S(i,j)在[lower,upper]之间的条件是lower<=sums[j+1]-sums[i]<=upper, 所以我们每次insert一个新的PrefixSum sums[k]进这个BST之前，
	先寻找一下（rangeSize）该BST内已经有多少个PrefixSum（叫它sums[t]吧）满足lower<=sums[k]-sums[t]<=upper, 即寻找有多少个sums[t]满足： 

	sums[k]-upper<=sums[t]<=sums[k]-lower

	BST提供了countSmaller和countLarger的功能，计算比sums[k]-upper小的RangeSum数目和比sums[k]-lower大的数目，再从总数里面减去，就是所求
 */


public class Solution {
    private class TreeNode {
        long val = 0;
        int count = 1;
        int leftSize = 0;
        int rightSize = 0;
        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(long v) {
            this.val = v;
            this.count = 1;
            this.leftSize = 0;
            this.rightSize = 0;
        }
    }

    private TreeNode insert(TreeNode root, long val) {
        if(root == null) {
            return new TreeNode(val);
        } else if(root.val == val) {
            root.count++;
        } else if(val < root.val) {
            root.leftSize++;
            root.left = insert(root.left, val);
        } else if(val > root.val) {
            root.rightSize++;
            root.right = insert(root.right, val);
        }
        return root;
    }

    private int countSmaller(TreeNode root, long val) {
        if(root == null) {
            return 0;
        } else if(root.val == val) {
            return root.leftSize;
        } else if(root.val > val) {
            return countSmaller(root.left, val);
        } else {
            return root.leftSize + root.count + countSmaller(root.right, val);
        }
    }

    private int countLarger(TreeNode root, long val) {
        if(root == null) {
            return 0;
        } else if(root.val == val) {
            return root.rightSize;
        } else if(root.val < val) {
            return countLarger(root.right, val);
        } else {
            return countLarger(root.left, val) + root.count + root.rightSize;
        }
    }

    private int rangeSize(TreeNode root, long lower, long upper) {
        int total = root.count + root.leftSize + root.rightSize;
        int smaller = countSmaller(root, lower);    // Exclude everything smaller than lower
        int larger = countLarger(root, upper);      // Exclude everything larger than upper
        return total - smaller - larger;
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        if(nums.length == 0) {
            return 0;
        }
        long[] sums = new long[nums.length + 1];
        for(int i = 0; i < nums.length; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
        TreeNode root = new TreeNode(sums[0]);
        int output = 0;
        for(int i = 1; i < sums.length; i++) {
            output += rangeSize(root, sums[i] - upper, sums[i] - lower);
            insert(root, sums[i]);
        }
        return output;
    }
}