/*
	Sort Colors
	Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

	Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

	Note:
	You are not suppose to use the library's sort function for this problem.
*/

public class Solution {
    //Counting sort1 easy to understand
    public void sortColors(int[] A) {
        if (A == null || A.length == 0) {
            return;
        }
        int[] colornum = new int[3];
        for (int elem : A) {
            if (elem == 0) {
                colornum[0]++;
            } 
            if (elem == 1) {
                colornum[1]++;
            }
            if (elem == 2) {
                colornum[2]++;
            }
        }
        for (int i = 0; i < colornum[0]; i++) {
            A[i] = 0;
        }
        for (int i = colornum[0]; i < colornum[0] + colornum[1]; i++) {
            A[i] = 1;
        }
        for (int i = colornum[0] + colornum[1]; i < A.length; i++) {
            A[i] = 2;
        }
    }

    //counting sort2
    public void sortColors(int[] A) {
        if (A == null || A.length ==0) {
            return;
        }
        //Store the sorted result
        int[] res = new int[A.length];
        //the length is the  maximum color(value) of array A
        int[] helper = new int[3];
        
        //count the every value's amount
        //helper[i] now contains the number of elements equal to i
        for (int i = 0; i < A.length; i++) {
            helper[A[i]] = helper[A[i]] + 1;
        }
        
        
        //after below statement, helper[i] now contains the number of elements less than or equal to i
        for (int i = 1; i < 3; i++) {
            helper[i] = helper[i] + helper[i - 1];
        }
        
        //base on the helper[i]'s value to set the result arry
        for (int i = A.length - 1; i >= 0; i--) {
            res[helper[A[i]] - 1] = A[i];
            helper[A[i]] = helper[A[i]] - 1;
        }
        //set the result to A array.
        for (int i = 0; i < A.length; i++) {
            A[i] = res[i];
        }
    }
	// public void sortColors(int[] A) {
    //     if (A == null || A.length <= 1) {
    //         return ;
    //     }
    //     sortColors(A, 0, A.length - 1);
    // }
    // public void sortColors(int[] A, int low, int high) {
    //     if (low < high) {
    //         int q = partition(A, low, high);
    //         sortColors(A, low, q - 1);
    //         sortColors(A, q + 1, high);
    //     }
    // }
    
    // public void swap(int[] A, int i ,int j) {
    //     int temp = A[i];
    //     A[i] = A[j];
    //     A[j] = temp;
    // }
    
    // public int partition(int[] A, int p, int r) {
    //     int x = A[r];
    //     int i = p - 1;
    //     for (int j = p; j < r; j++) {
    //         if (A[j] <= x) {
    //             i = i + 1;
    //             swap(A, i, j);
    //         }
    //     }
    //     swap(A, i + 1, r);
    //     return i + 1;
    // }
    
    //Quick Sort
    public void sortColors(int[] A) {
    	if (A.length <= 1 || A == null) {
    		return;
    	}

    	sortColors(A, 0, A.length - 1);
    }

    public void sortColors(int[] A, int low, int high) {
    	if (low < high) {
    		int pivot = partition(A, low, high);
    		sortColors(A, low, pivot - 1);
    		sortColors(A, pivot + 1, high);
    	}
    }

    public int partition(int[] A, int low, int high) {
    	int pivot = A[high];
    	int i = low - 1;
    	for (int j = low; j < high; j++) {
    		if (A[j] <= pivot) {
    			i++;
    			swap(A, i, j);
    		}
    	}
    	swap(A, i + 1, high);
    	return i + 1;
    }

    public void swap(int[] A, int i, int j) {
    	int temp = A[i];
    	A[i] = A[j];
    	A[j] = temp;
    }
    
    //Merge Sort
    public void sortColors(int[] A) {
        if (A == null || A.length <= 1) {
            return;
        }
        sortColors(A, 0, A.length - 1);
    }
    public static int[] sortColors(int[] A, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            sortColors(A, low, mid);
            sortColors(A, mid + 1, high);
            merge(A, low, mid, high);
        }
        return A;
    }
    
    public static void merge(int[] A, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        
        while (i <= mid && j <= high) {
            if (A[i] < A[j]) {
                temp[k++] = A[i++];
            } else {
                temp[k++] = A[j++];
            }
        }
        
        while (i <= mid) {
            temp[k++] = A[i++];
        }
        while (j <= high) {
            temp[k++] = A[j++];
        }
        
        for (int l = 0; l < temp.length; l++) {
            A[l + low] = temp[l];
        }
    }

    //double pointer
    public void sortColors(int[] A) {
    	if (A == null || A.lengtn <= 1) {
    		return ;
    	}
        //pl is point to the 0's position
    	int pl = 0;
        //pr is point to the 2's position
    	int pr = A.length - 1;

        //i is point ot the 1's position
    	int i = 0;
    	while (i <= pr) {
            //if find A[i] equals 0, replace to the left side ,and make pl++,i++
    		if (A[i] == 0) {
    			swap(A, i, pl);
    			pl++;
    			i++;
            //if find A[i] equals 1, just i++
    		} else if (A[i] == 1) {
    			i++;
            //if find A[i] equals 2, swap the A[i] pr, put the 2 to right side
    		} else {
    			swap(A, i, pr);
    			pr--;
    		}
    	}
    }


}