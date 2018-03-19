/*
	378. Kth Smallest Element in a Sorted Matrix

	Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

	Note that it is the kth smallest element in the sorted order, not the kth distinct element.

	Example:

	matrix = [
	   [ 1,  5,  9],
	   [10, 11, 13],
	   [12, 13, 15]
	],
	k = 8,

	return 13.
	Note: 
	You may assume k is always valid, 1 ≤ k ≤ n2.
*/


// 因为最小的元素都在第一行，所以下一个最小的元素肯定在第一行的元素之后的下几行依次，同时建一个turple拥有位置和val属性入最小堆
// 求最小K需要poll k - 1个最小的元素，之后的peek就是所求元素
// time: klogk, space: k
class Solution {
    class Turple {
        int x;
        int y;
        int val;
        public Turple(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
    
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        PriorityQueue<Turple> pq = new PriorityQueue<Turple>((a, b) -> a.val - b.val);
        
        // 将最小的m个元素先入最小堆
        for (int i = 0; i < m; i++) {
            pq.offer(new Turple(0, i, matrix[0][i]));
        }
        
        for (int i = 0; i < k - 1; i++) {
            Turple turple = pq.poll();
            if (turple.x + 1 > m - 1) continue; //防止越界

            pq.offer(new Turple(turple.x + 1, turple.y, matrix[turple.x + 1][turple.y]));
        }
        return pq.peek().val;
    }
}


// Binary Search
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0)
            return 0;
        int n = matrix.length;
        int start = matrix[0][0];
        int end = matrix[n - 1][n - 1];
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int count = count(matrix, mid); //比mid小的数有多少
            if (count >= k) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (count(matrix, end) <= k - 1)
            return end;
        return start;
    }
    
    private int count(int[][] matrix, int target){
        int n = matrix.length;
        int i = n - 1;
        int j = 0;
        int res = 0;

        while (i >= 0 && j < n) { //从左下的点开始， 如果大于target就往下走，如果小于target就往右走
            if (matrix[i][j] < target) { 
                res += i + 1;
                j++;
            } else{
                i--;
            }
        }

        return res;
    }
}