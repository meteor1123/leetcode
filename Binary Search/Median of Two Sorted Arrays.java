/*
	Median of Two Sorted Arrays
	There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
	Tags: Divide and Conquer ,Array, Binary Search
*/


/*
	Analysis:
	Assume that the number of elements in A and B are both larger than k/2, and if we compare the k/2-th smallest element in A(i.e. A[k/2-1]) 
	and the k-th smallest element in B(i.e. B[k/2 - 1]), there are three results:
	(Becasue k can be odd or even number, so we assume k is even number here for simplicy. The following is also true when k is an odd number.)
	A[k/2-1] = B[k/2-1]
	A[k/2-1] > B[k/2-1]
	A[k/2-1] < B[k/2-1]
	if A[k/2-1] < B[k/2-1], that means all the elements from A[0] to A[k/2-1](i.e. the k/2 smallest elements in A) are in the range of k smallest elements in the union of A and B. Or, 
	in the other word, A[k/2 - 1] can never be larger than the k-th smalleset element in the union of A and B.

	Why?
	We can use a proof by contradiction. 
	Since A[k/2 - 1] is larger than the k-th smallest element in the union of A and B, 
	then we assume it is the (k+1)-th smallest one. Since it is smaller than B[k/2 - 1], then B[k/2 - 1] should be at least the (k+2)-th smallest one. 
	So there are at most (k/2-1) elements smaller than A[k/2-1] in A, and at most (k/2 - 1) elements smaller than A[k/2-1] in B.So the total number is k/2+k/2-2, 
	which, no matter when k is odd or even, is surly smaller than k(since A[k/2-1] is the (k+1)-th smallest element).
	So A[k/2-1] can never larger than the k-th smallest element in the union of A and B if A[k/2-1]<B[k/2-1];
	Since there is such an important conclusion, we can safely drop the first k/2 element in A, which are definitaly smaller than k-th element in the union of A and B.
	This is also true for the A[k/2-1] > B[k/2-1] condition, which we should drop the elements in B.
	When A[k/2-1] = B[k/2-1], then we have found the k-th smallest element, that is the equal element, we can call it m. There are each (k/2-1) numbers smaller than m in A and B, so m must be the k-th smallest number. So we can call a function recursively, when A[k/2-1] < B[k/2-1], we drop the elements in A, else we drop the elements in B.


	We should also consider the edge case, that is, when should we stop?
	1. When A or B is empty, we return B[k-1]( or A[k-1]), respectively;
	2. When k is 1(when A and B are both not empty), we return the smaller one of A[0] and B[0]
	3. When A[k/2-1] = B[k/2-1], we should return one of them

	In the code, we check if m is larger than n to garentee that the we always know the smaller array, for coding simplicy.
*/
/*
	
	这个findKth()函数写的非常经典，思路如下：
	1. 保持A是短的那一个数组，B是长的
	2. 平分k, 一半在A，一半在B （如果A的长度不足K/2,那partA就指到最后一个）
	3. 如果partA的值 < partB的值，那证明第K个数肯定不会出现在partA之前，递归，把A数组partA之前的砍掉，同理递归砍B数组。
	4. 递归到 m == 0 （短的数组用完了） 就返回 B[k - 1], 或者k == 1（找第一个数）就返回min(A第一个数，B第一个数）
*/
public class Solution {

	//Solution0:
	public class Solution {
	    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
	        int m = nums1.length;
	        int n = nums2.length;
	        int total = m + n;
	        if (total % 2 != 0) {
	            return findMedianSortedArrays(nums1, 0, m - 1, nums2, 0, n - 1, total / 2 + 1);
	        } else {
	            double x = findMedianSortedArrays(nums1, 0, m - 1, nums2, 0, n - 1, total / 2);
	            double y = findMedianSortedArrays(nums1, 0, m - 1, nums2, 0, n - 1, total / 2 + 1);
	            return (double)(x + y) / 2;
	        }
	    }
	    
	    public static int findMedianSortedArrays(int[] nums1, int aStart, int aEnd, int[] nums2, int bStart, int bEnd, int k) {
	        int m = aEnd - aStart + 1;
	        int n = bEnd - bStart + 1;
	        
	        if (m > n) {
	            return findMedianSortedArrays(nums2, bStart, bEnd, nums1, aStart, aEnd, k);
	        }
	        if (m == 0) {
	            return nums2[k - 1];
	        }
	        if (k == 1) {
	            return Math.min(nums1[aStart], nums2[bStart]);
	        }
	        
	        int partA = Math.min(k / 2, m);
	        int partB = k - partA;
	        if (nums1[aStart + partA - 1] < nums2[bStart + partB - 1]) {
	            return findMedianSortedArrays(nums1, aStart + partA, aEnd, nums2, bStart, bEnd, partB);
	        } else if (nums1[aStart + partA - 1] > nums2[bStart + partB - 1]) {
	            return findMedianSortedArrays(nums1, aStart, aEnd, nums2, bStart + partB, bEnd, k - partB);
	        } else {
	            return nums1[aStart + partA - 1];
	        }
	    }
	}

// Solution 3
class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j-1] > A[i]){
            	iMin = iMin + 1; // i is too small
            } else if (i > iMin && A[i-1] > B[j]) {
              iMax = iMax - 1; // i is too big
            } else { // i is perfect
              int maxLeft = 0;
              if (i == 0) {
           			maxLeft = B[j-1];
              } else if (j == 0) { 
              	maxLeft = A[i-1];
              } else { 
              	maxLeft = Math.max(A[i-1], B[j-1]); 
              }
              if ((m + n) % 2 == 1) { 
              	return maxLeft; 
              }

              int minRight = 0;
              if (i == m) { 
              	minRight = B[j]; 
              } else if (j == n) { 
              	minRight = A[i]; 
              } else { 
              	minRight = Math.min(B[j], A[i]);
              }

              return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}
