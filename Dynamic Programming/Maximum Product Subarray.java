/*
    Maximum Product Subarray 

    Find the contiguous subarray within an array (containing at least one number) which has the largest product.

    For example, given the array [2,3,-2,4],
    the contiguous subarray [2,3] has the largest product = 6.
    Tags: Array Dynamic Programming.
*/

public class Solution {
    /*
        Example Questions Candidate Might Ask:
        Q: Could the subarray be empty?
        A: No, the subarray must contain at least one number.
    */
    /*
        Solution:
        Solution:
            This problem is very similar to Question [45. Maximum Sum Subarray]. There is a slight twist though. 
            1. Besides keeping track of the largest product, we also need to keep track of the smallest product. 
            2. Why? The smallest product, which is the largest in the negative sense could become the maximum when being 
               multiplied by a negative number.
            
            Let us denote that:
        f(k) = Largest product subarray, from index 0 up to k. Similarly,
        g(k) = Smallest product subarray, from index 0 up to k. Then,
        
        f(k) = max( f(k-1) * A[k], A[k], g(k-1) * A[k] )
        g(k) = min( g(k-1) * A[k], A[k], f(k-1) * A[k] )

        There we have a dynamic programming formula. Using two arrays of size n, we could deduce the final answer as f(n-1). 
        Since we only need to access its previous elements at each step, two variables are sufficient.
    */
    public int maxProduct(int[] A) {
        int max = A[0];
        int min = A[0];
        int maxAns = A[0];
        for (int i = 1; i < A.length; i++) {
            int mx = max;
            int mn = min;
            max = Math.max(Math.max(A[i], mx * A[i]), mn * A[i]); 
            min = Math.min(Math.min(A[i], mx * A[i]), mn * A[i]);
            maxAns = Math.max(max, maxAns);
        }
        return maxAns;
    }
}