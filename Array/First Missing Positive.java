/*
	First Missing Positive
	Given an unsorted integer array, find the first missing positive integer.

	For example,
	Given [1,2,0] return 3,
	and [3,4,-1,1] return 2.

	Your algorithm should run in O(n) time and uses constant space.

	Tags: Array
*/

/*
    https://leetcode.com/discuss/8763/share-my-o-n-time-o-1-space-solution
	The basic idea is for any k positive numbers (duplicates allowed), 
    the first missing positive number must be within [1,k+1]. 
    The reason is like you put k balls into k+1 bins, there must be a bin empty,
     the empty bin can be viewed as the missing number.

1. Unfortunately, there are 0 and negative numbers in the array, 
   so firstly I think of using partition technique (used in quick sort) to put all positive numbers together in one side. 
   This can be finished in O(n) time, O(1) space.

2. After partition step, you get all the positive numbers lying within A[0,k-1]. 
   Now, According to the basic idea, I infer the first missing number must be within [1,k+1]. 
   I decide to use A[i] (0<=i<=k-1) to indicate whether the number (i+1) exists. 
   But here I still have to main the original information A[i] holds.
   Fortunately, A[i] are all positive numbers, so I can set them to negative to indicate the existence of (i+1) 
   and I can still use abs(A[i]) to get the original information A[i] holds.

3. After step 2, I can again scan all elements between A[0,k-1] to find the first positive element A[i], 
   that means (i+1) doesn't exist, which is what I want.
*/

public class Solution {
    //Solution1:  prefer
    public int firstMissingPositive(int[] A) {
        int len = A.length;
        for (int i = 0; i < len; i++) {
            while (A[i] > 0 && A[i] <= len && A[A[i] - 1] != A[i]) {
                swap (A, i, A[i] - 1);
            }
        }

        for (int i = 0; i < len; i++) {
            if (A[i] != i + 1) {
                return i + 1;
            }
        }
        return len + 1;
    }

    public void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    //Solution2: Using quick partition method, a little bit complicate
    public int firstMissingPositive(int[] A) {
        int n = A.length;
        if (n == 0) {
            return 1;
        }
        int k = partition(A) + 1;
        int temp = 0;
        int first_missing_Index = k;
        for (int i = 0; i < k; i++) {
            temp = Math.abs(A[i]);
            if (temp <= k) {// missing number must in 1 ~ k + 1
                A[temp - 1] = (A[temp - 1] < 0) ? A[temp - 1] : -A[temp - 1];
            }
        }
        
        for (int i = 0; i < k; i++) {
            if (A[i] > 0) {
                first_missing_Index = i;
                break;
            }
        }
        return first_missing_Index + 1;
    }
    
    public int partition(int[] A) {
        int i = - 1;
        for (int j = 0; j < A.length; j++) {
            if (A[j] > 0) {
                i = i + 1;
                swap(A, i, j);
            }
        }
        return  i;
    }
    
    public void swap(int[] A, int i, int j) {
        if (i != j) {
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
        }

    }
}