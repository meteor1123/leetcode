/*
	Merge Sorted Array
	Given two sorted integer arrays A and B, merge B into A as one sorted array.

	Note:
	You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements from B. 
    The number of elements initialized in A and B are m and n respectively.
*/

/*
    /*
归并操作(merge)，也叫归并算法，指的是将两个已经排序的序列合并成一个序列的操作。归并排序算法依赖归并操作。
归并操作的过程如下:
    1.申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
    2.设定两个指针，最初位置分别为两个已经排序序列的起始位置
    3.比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
    4.重复步骤3直到某一指针到达序列尾
    5.将另一序列剩下的所有元素直接复制到合并序列尾
*/

public class Solution {
    //in place 的方法
    public void merge(int A[], int m, int B[], int n) {
        while (m > 0 && n > 0) {
            if (A[m - 1] > B[n - 1]) {
                A[m + n - 1] = A[m - 1];
                m--;
            } else {
                A[m + n - 1] = B[n - 1];
                n--;
            }
        }
        

        //数组A为空,数组B非空的情况
        while (n > 0) {
            A[m + n - 1] = B[n - 1];
            n--;
        }
    }
}


// index from 0 ~ m - 1, 0 ~ n - 1
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        while (i >= 0 && j >= 0) {   
            nums1[i + j + 1] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        }
        
        while (j >= 0) {
            nums1[i + j + 1] = nums2[j]; // 如果j >= 0存在， i 一定= -1, i + j + 1就可以达到0
            j--;
        }
    }
}