/*
归并操作(merge)，也叫归并算法，指的是将两个已经排序的序列合并成一个序列的操作。归并排序算法依赖归并操作。
归并操作的过程如下:
	1.申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
	2.设定两个指针，最初位置分别为两个已经排序序列的起始位置
	3.比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
	4.重复步骤3直到某一指针到达序列尾
	5.将另一序列剩下的所有元素直接复制到合并序列尾
*/

// public int[] mergeSort(int[] arr) {
// 	if (arr.length < 2 || arr == null)
// 		return ;
// 	MSort(arr, 0, arr.length - 1);
// }

// public int[] MSort(int[] arr, int low, int high) {
// 	if (low < high) {
// 		int mid = (low + high) / 2;
// 		int[] left = Msort(arr, left, mid);
// 		int[] right = Msort(arr, mid + 1, right);
// 		mergeTwoArray(left, right);
// 	} 
// }

// public int[] mergeTwoArray(int[] left, int[] right) {
// 	int[] mergeArr = new int[left.length + right.length];
// 	int k = 0;
// 	int i = 0;
// 	int j = 0;

// 	while (i < left.length && j < right.length) {
// 		if (left[i] < right[j]) 
// 			mergeArr[k++] = left[i++];
// 		else 
// 			mergeArr[k++] = right[j++];
// 	}

// 	while (i < left.length) 
// 		mergeArr[k++] = left[i++];
// 	while (j < right.length)
// 		mergeArr[k++] = right[i++];
// 	return mergeArr;
// }

// public int[] mergeSort(int[] arr) {
// 	if (arr.length < 2 || arr == null) {
// 		return arr;
// 	}
// 	return MSort(arr, 0, arr.length - 1);
// }

// public int[] MSort(int[] arr, int low, int high) {
// 	if (low < high) {
// 		int mid = (low + high) / 2;
// 		int[] left = MSort(arr, low, mid);
// 		int[] right = MSort(arr, mid + 1, high);
// 		return mergeTwoList(left, right);
// 	}
// }

// public int[] mergeTwoList(int[] A, int[] B) {
// 	int[] C = new int[A.length + B.length];
// 	int k = 0;
// 	int i = 0;
// 	int j = 0;
// 	while (i < A.length && j < B.length) {
// 		if (A[i] < B[j]) {
// 			C[k++] = A[i++];
// 		} else {
// 			C[k++] = B[j++];
// 		}
// 	}

// 	while (i < A.length) {
// 		C[k++] = A[i++];
// 	}
// 	while (j < B.length) {
// 		C[k++] = B[j++];
// 	}
// 	return C;
// }

public int[] mergeSort(int[] arr) {
	if (arr.length < 2 || arr == null) {
		return arr;
	}
	return MSort(arr, 0, arr.length - 1);
}


//Divide 
public int[] MSort(int[] arr, int low, int high) {
	if (low < high) {
		int mid = (low + high) / 2;
		int[] left = MSort(arr, low, mid);
		int[] right = MSort(arr, mid + 1, high);
		return mergeTwoList(left, right);
	}
	return arr;
}

//Conquer
public int[] mergeTwoList(int[] A, int[] B) {
	int[] C = new int[A.length + B.length];
	int k = 0;
	int i = 0;
	int j = 0;
	while (i < A.length && j < B.length) {
		if (A[i] < B[j]) {
			C[k++] = A[i++];
		} else {
			C[k++] = B[j++];
		}
	}

	while (i < A.length) {
		C[k++] = A[i++];
	}
	while (j < B.length) {
		C[k++] = B[j++];
	}
	return C;
}






