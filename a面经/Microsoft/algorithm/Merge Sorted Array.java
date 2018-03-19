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

    //ArrayList method
    public ArrayList<Integer> mergeSortedArray(ArrayList<Integer> A, ArrayList<Integer> B) {
        // write your code here
        if (A==null || A.size()==0) {
            return B;
        }
        if (B==null || B.size()==0) {
            return A;
        }
        ArrayList<Integer> res = new ArrayList<Integer>();
        int i = 0, j = 0;
        for (int k = 0; k < A.size() + B.size(); k++) {
            if (i < A.size() && j < B.size() && A.get(i) < B.get(j)) {
                res.add(A.get(i));
                i++;
            }
            else if (i < A.size() && j < B.size() && A.get(i) >= B.get(j)){
                res.add(B.get(j));
                j++;
            }
            else if (i < A.size()) {
                res.add(A.get(i));
                i++;
            }
            else {
                res.add(B.get(j));
                j++;
            }
        }
        return res;
    }
}