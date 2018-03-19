/*
	Lintcode: Sort Colors II
*/

//Counting sort O(KN)
class Solution {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    //use k times  partition of quick sort, 
    public void sortColors2(int[] colors, int k) {
        // write your code here
        if (colors == null || colors.length == 0 || k <= 1) {
            return;
        }
        int r = colors.length - 1;
        for (int i = 0; i < k - 1; ++i) {//为什么是 < k - 1 而不是< k?  因为K个数，至多只需要K-1次partition就能排好顺序
            r = partition(colors, 0, r, k - i - 1);//pivot从k - 1 到  1
        }
    }
    
    //Partition1
    public int partition(int[] colors, int low, int high, int pivot) {
        int i = low;
        int j = high;
        while (i <= j) {
            while (i < high && colors[i] <= pivot) {
                ++i;
            }
            while (j > 0 && colors[j] > pivot) {
                --j;
            }
            if (i <= j) {
                swap(colors , i, j);
                i++;
                j--;
            }
        }
        return i - 1;
    }

    //Partition2
    public int partition(int[] a, int low, int high, int pivot) {
       int i = low;
       int j = high;
       while (true) {
           while (i < j && a[i] <= pivot) {
               i++;
           }
           while (i < j && a[j] > pivot) {
               j--;
           }
           if (i == j) {
               break;
           }
           swap(a, i, j);
       }
       swap(a, high, i);
       return i - 1;
    }



    public void swap(int[] colors, int l, int r) {
        int temp = colors[l];
        colors[l] = colors[r];
        colors[r] = temp;
    }
}

//quick sort
class Solution {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors2(int[] colors, int k) {
        // write your code here
        if (colors==null || colors.length==0 || k<=1) {
            return;
        }
        quickSort(colors, 0, colors.length-1);
    }
    
    public void quickSort(int[] colors, int l, int r) {
        if (l >= r) {
            return;
        }
        int pivot = r;
        int pos = partition(colors, l, r, pivot);
        quickSort(colors, l, pos - 1);
        quickSort(colors, pos + 1, r);
    }
    
    public int partition(int[] colors, int start, int end, int pivot) {
        int l = start, r = end;
        while (true) {
            while (l < r && colors[l] < colors[pivot]) {
                l++;
            }
            while (l < r && colors[r] >= colors[pivot]) {
                r--;
            }
            if (l == r) break;
            swap(colors, l, r);
        }
        swap(colors, l, end);
        return l;
    }
    
    public void swap(int[] colors, int l, int r) {
        int temp = colors[l];
        colors[l] = colors[r];
        colors[r] = temp;
    }
}