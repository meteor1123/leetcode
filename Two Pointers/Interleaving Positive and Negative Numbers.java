/*
	Interleaving Positive and Negative Numbers

	Given an array with positive and negative integers. Re-range it to interleaving with positive and negative integers.

	Have you met this question in a real interview? Yes
	Example
	Given [-1, -2, -3, 4, 5, 6], after re-range, it will be [-1, 5, -2, 4, -3, 6] or any other reasonable answer.

	Note
	You are not necessary to keep the original order of positive integers or negative integers.

	Challenge
	Do it in-place and without extra memory.
 */

class Solution {
    /**
     * @param A: An integer array.
     * @return: void
     */
    public void rerange(int[] A) {
        // write your code here
        if (A == null || A.length == 0) {
            return;
        }
        int posNum = 0;
        int negNum = 0;
        for (int num : A) {
            if (num < 0) {
                negNum++;
            } else {
                posNum++;
            }
        }
        
        int posIndex = 1;
        int negIndex = 0;
        if (posNum > negNum) {
            posIndex = 0;
            negIndex = 1;
        }
        
        while (posIndex < A.length && negIndex < A.length) {
            while (posIndex < A.length && A[posIndex] >= 0) {
                posIndex += 2;
            }
            while (negIndex < A.length && A[negIndex] < 0) {
                negIndex += 2;
            }
            if (posIndex < A.length && negIndex < A.length) {
                swap(A, posIndex, negIndex);
            }
        }
   }
   
   public void swap(int[] A, int i, int j) {
       int temp = A[i];
       A[i] = A[j];
       A[j] = temp;
   }
}