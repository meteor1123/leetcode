package com.alg1;

public class MAXHeap {
	public void MaxHeapIfy(int A[], int length, int i) {
		int left = i * 2; //the left children of point i
		int right = i * 2 + 1; //the right children of point j
		int largest = i; //parent point
		
		if (left <= length && A[largest] < A[left]) {
			largest = left;
		}
		
		if (right <= length && A[largest] < A[right]) {
			largest = right;
		}
		
		if (i != largest) { //the maximum value isn't parent node
			int temp = A[largest];
			A[largest] = A[i];
			A[i] = temp;
			
			MaxHeapIfy(A, length, largest);
		}
		
	}
	
	public void BuildMaxHeap(int A[], int length) { //建堆
		for (int i = (length - 1) / 2; i >= 0; i--) {
			MaxHeapIfy(A, length, i);
		}
	}
	
	public void heapSort(int A[], int length) {
		int temp;
		BuildMaxHeap(A, length);
		for (int i = 0; i <= length; i++) {
			System.out.print(A[i] + " ");
		}
		System.out.println("");
		for (int i = length; i >= 1;) { //最后一个肯定是最小
			temp = A[i];
			A[i] = A[0];
			A[0] = temp;
			i--;//堆大小减1
			MaxHeapIfy(A, i, 0);//调堆
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MAXHeap test = new MAXHeap();
		int A[] = {0, 4, 1, 3, 2, 16, 9, 10, 14, 8, 7};  
		test.heapSort(A, A.length - 1);
		
		for (int i : A) {
			System.out.print(i + " ");
		}
		
	}

}
