
When does the worst case of Quicksort occur ?

The answer depends on strategy for choosing pivot. 
In early versions of Quick Sort where leftmost (or rightmost) element is chosen as pivot, the worst occurs in following cases.

1) Array is already sorted in same order.
2) Array is already sorted in reverse order.
3) All elements are same (special case of case 1 and 2)

//Standard quick sort
public void quickSort(int[] A) {
	quickSort(A, 1, A.length);
}
public void quickSort(int[] A, int p, int r) {
	if (p < r) {
		int q = partition(A, p, r);
		quickSort(A, p, q - 1);
		quickSort(A, q + 1, r);
	}
}

public int partition(int[] A, int p, int r) {
	// A[r]是pivot
	int x = A[r];
	int i = p - 1;
	for (int j = p; j < r; j++) {
		if (A[j] <= x) {
			i = i + 1;
			swap(A, i, j);
		}
	}

	swap(A, i + 1, r);
	return i + 1;
}

public void swap(int[] A, int i, int j) {
	int temp = A[i];
	A[i] = A[j];
	A[j] = temp;
}


//double way quick sort prefer!
    public void quicksort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        if (start < end) {
            int pivot = partition(nums, start, end);
            quicksort(nums, start, pivot - 1);
            quicksort(nums, pivot + 1, end);
        }
    }
    public int partition(int[] nums, int start, int end) {
        int l = start;
        int r = end;
        int pivot = nums[end];
        while (true) {
            while (l < r && nums[l] < pivot) {
                l++;
            }
            while (l < r && nums[r] >= pivot) {
                r--;
            }
            if (l == r) {
                break;
            }
            swap(nums, l, r);
        }
        swap(nums, l, end);
        return l;
    }
    public void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }

//quickselec 找出数组中第K小元素，k代表第K小，不是数组序号 prefer!
public int quickselect(int[] a, int k, int low, int high) {
    int i = low;
    int j = high;
    while (true) {
        while (i < j && a[i] < a[high]) {
           i++;
        }
        while (i < j && a[j] >= a[high]) {
           j--;
        }
        if (i == j) {
           break;
        }
        swap(a, i, j);
    }
    swap(a, i, high);
    
   
    if (i + 1 == k) {
        return a[i];
    } else if (i + 1 < k) {
        return quickselect(a, k, i + 1, high);
    } else {
        return quickselect(a, k, low, i - 1);
    }
}
//quick selcet O(n) time complexity
public int select(int a[], int k, int l, int r)//找出数组中第K小元素  
{  	//数组中只有一个数 
    if (l == r) {
    	return a[l];
    } 
    int q = partition(a, l, r);  
    int t = q - l + 1;//左边数的个数  
    if (k == t) { //k==t那么a[q]就是答案  
    	return a[q];
    } else if (k < t) {//在左边进行查找 
    	return select(a, k, l, q - 1);
   	} else { //在右边进行查找，注意这里就需要查找第k-t小数了  
    	return select(a, k - t, q + 1, r);
    }
}


//double ways QuickSort
public void sortColors2(int[] colors, int k) {
	if (colors == null || colors.length == 0 || k <= 1) {
		return;
	}
	quickSort(colors, 0, colors.length - 1);
}

public void quickSort(int[] arr, int l, int r) {
	if (l >= r) {
		return;
	}
	int pos = partition(arr, l, r);
	quickSort(arr, l, pos - 1);
	quickSort(arr, pos + 1, r);
}

public int partition(int[] a, int low, int high) {
       int i = low;
       int j = high;
       while (true) {
           while (i < j && a[i] < a[high]) {
               i++;
           }
           while (i < j && a[j] >= a[high]) {
               j--;
           }
           if (i == j) {
               break;
           }
           swap(a, i, j);
       }
       swap(a, high, j);
       return j;
}

public void swap(int[] arr, int l, int r) {
	int temp = arr[l];
	arr[l] = arr[r];
	arr[r] = temp;
}

//find Kth largest element
	public int kthLargestElement(int k , int[] arr) {
		if (k > arr.length) {
			return -1;
		}
		return helper(arr.length - k + 1, arr, 0, arr.length - 1);
		//find the Kth larest which means find len - k small 
		//due to the arr index is from 0 to 9, so need to plus 1;
	}

	public int helper(int k, int[] arr, int start, int end) {
		int l = start;
		int r = end;
		int pivot = end;
		while (true) {
			while (l < r && arr[l] < arr[pivot]) {
				l++;
			}
			while (l < r && arr[r] >= arr[pivot]) {
				r--;
			}
			if (l == k) {
				break;
			}
			swap(arr, l, r);
		}
		swap(arr, l , end);
		if (l + 1 == k) {
			return arr[l];
		} else if (l + 1 < k) {
			helper(k, arr, l + 1, end);
		} else {
			helper(k, arr, start, l - 1);
		}
	}
	public void swap(int[] arr, int l, int r) {
		int temp = arr[l];
		arr[l] = arr[r];
		arr[r] = temp;
	}