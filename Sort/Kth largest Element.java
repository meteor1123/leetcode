/*
	Find K-th largest element in an array.

	Note
	You can swap elements in the array

	Example
	In array [9,3,2,4,8], the 3rd largest element is 4

	In array [1,2,3,4,5], the 1st largest element is 5, 2nd largest element is 4, 3rd largest element is 3 and etc.

	Challenge
	O(n) time, O(1) space
*/


/*

	The default PriorityQueue is implemented with Min-Heap, that is the top element is the minimum one in the heap.
	1.minHeap
	public class MyComparator implements Comparator<Integer> {
	    public int compare(Integer x, Integer y) {
	        return x - y;
	    }
	}
	  	PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
		    @Override
		    //最小堆
		    public int compare(Integer arg0, Integer arg1) {
		        if (arg0 > arg1) {
		            return 1;
		        } else if (arg0 < arg1) {
		            return - 1;
		        } else {
		            return 0;
		        }
		    }
		});

	2.maxHeap
	In order to implement a max-heap, you can create your own Comparator:
	public class MyComparator implements Comparator<Integer> {
	    public int compare(Integer x, Integer y) {
	        return y - x;
	    }
	}
		PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
		    @Override
		    //最大堆
		    public int compare(Integer arg0, Integer arg1) {
		        if (arg1 > arg0) {
		            return 1;
		        } else if (arg1 < arg0) {
		            return -1;
		        } else {
		            return 0;
		        }
		    }
		});

	So, you can create a min-heap and max-heap in the following way:

	PriorityQueue minHeap = new PriorityQueue();
	PriorityQueue maxHeap = new PriorityQueue(size, new MyComparator());

	PriorityQueue<Integer> maxHeap = new PriorityQueue<>(1000, Collections.reverseOrder());
*/

	/*
	*  Array
	*
	*
	*/
public class Solution {
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
}
public class Solution {
	//1.Using heap
	//  O(N lg K) running time + O(k) memory
	public int findKthLargest(int[] nums, int k) {
		//默认是最小堆，也就是适合找第k大,
		//堆顶元素就是peek，堆顶元素是队列中最小的数字，维护一个size（） = k, 每次只要大于堆顶的元素，就将堆顶元素出列，堆顶元素变成当前队列最小
		
		//最大堆适合找第k小，堆顶元素是队列中最大的数字
		//优先队列中元素默认按自然顺序排列，也就是数字默认是小的在队列头，字符串则按字典序排列。
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for (int val : nums) {
			pq.offer(val);
			if (pq.size() > k) {
				pq.poll();
			}
		}
		return pq.peek();
	}
}

//O(N) best case / O(N^2) worst case running time + O(1) memory
public class Solution {
	//param k : description of k
	//param numbers : array of numbers
	//return: description of return
	public int kthLargestElement(int k, ArrayList<Integer> numbers) {
		if (k > numbers.size()) {
			return 0;
		}
		return helper(numbers.size() - k + 1, numbers, 0, numbers.size() - 1);
	}
	public int helper(int k, ArrayList<Integer> numbers, int start, int end) {
		int l = start;
		int r = end;
		int pivot = end;
		while (true) {
			//双路快排
			while (numbers.get(l) < numbers.get(pivot) && l < r) {
				l++;
			}
			while (numbers.get(r) >= numbers.get(pivot) && l < r) {
				r--;
			}
			if (l == r) {
				break;
			}
			swap(numbers, l, r);
		}
		swap(numbers, l, end); // l here is the first one which is bigger than pivot, swap it with the pivot
		if (l + 1 == k) {// Why l ?since i is the index of array, if we want to check the ith position, we need to plus 1
			return numbers.get(l);
		//if l + 1 < k , then we make the left side move to right
		} else if (l + 1 < k) {
			return helper(k, numbers, l + 1, end);
		//if l + 1 > k ,then we make the right side move to left 
		} else return helper(k, numbers, start , l - 1);
	}
	public void swap(ArrayList<Integer> numbers, int l, int r) {
		int temp = numbers.get(l);
		numbers.set(l, numbers.get(r).intValue());
		numbers.set(r, temp);
	}

}

//Best Solution O(N) guaranteed running time + O(1) space,
//Why ? T(n) = T(n/2) + O(n)(partition) , 满足Master Method case 3! so T(n) = O(n)
public class Solution {
	public int findKthLargest(int[] nums, int k) {
        shuffle(nums);
        k = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            final int j = partition(nums, lo, hi);
            if(j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                break;
            }
        }
        return nums[k];
	}
	private int partition(int[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while(true) {
            while(i < hi && less(a[++i], a[lo]));
            while(j > lo && less(a[lo], a[--j]));
            if(i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }
    private void exch(int[] a, int i, int j) {
        final int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    private boolean less(int v, int w) {
        return v < w;
    }
	private void shuffle(int a[]) {
        final Random random = new Random();
        for(int ind = 1; ind < a.length; ind++) {
            final int r = random.nextInt(ind + 1);
            exch(a, ind, r);
        }
	}
}



