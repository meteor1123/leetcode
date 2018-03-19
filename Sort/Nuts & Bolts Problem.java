/*
	Nuts & Bolts Problem
	Given a set of n nuts of different sizes and n bolts of different sizes. There is a one-one mapping between nuts and bolts. Comparison of a nut to another nut or a bolt to another bolt is not allowed. It means nut can only be compared with bolt and bolt can only be compared with nut to see which one is bigger/smaller.

	We will give you a compare function to compare nut with bolt.

	Example
	Given nuts = ['ab','bc','dd','gg'], bolts = ['AB','GG', 'DD', 'BC'].

	Your code should find the matching bolts and nuts.

	one of the possible return:

	nuts = ['ab','bc','dd','gg'], bolts = ['AB','BC','DD','GG'].

	we will tell you the match compare function. If we give you another compare function.

	the possible return is the following:

	nuts = ['ab','bc','dd','gg'], bolts = ['BC','AA','DD','GG'].

	So you must use the compare function that we give to do the sorting.

	The order of the nuts or bolts does not matter. You just need to find the matching bolt for each nut.
 */


/**
* public class NBCompare {
*     public int cmp(String a, String b);
* }
* You can use compare.cmp(a, b) to compare nuts "a" and bolts "b",
* if "a" is bigger than "b", it will return 1, else if they are equal,
* it will return 0, else if "a" is smaller than "b", it will return -1.
* When "a" is not a nut or "b" is not a bolt, it will return 2, which is not valid.
*/

/*
	首先结合例子读懂题意，本题为 nuts 和 bolts 的配对问题，但是需要根据题目所提供的比较函数，且 nuts 与 nuts 之间的元素无法直接比较，compare 仅能在 nuts 与 bolts 之间进行。首先我们考虑若没有比较函数的限制，那么我们可以分别对 nuts 和 bolts 进行排序，由于是一一配对，故排完序后即完成配对。那么在只能通过比较对方元素得知相对大小时怎么完成排序呢？

	我们容易通过以一组元素作为参考进行遍历获得两两相等的元素，这样一来在最坏情况下时间复杂度为 O(n2)O(n^2)O(n2), 相当于冒泡排序。根据排序算法理论可知基于比较的排序算法最好的时间复杂度为 O(nlogn)O(n \log n)O(nlogn), 也就是说这道题应该是可以进一步优化。回忆一些基于比较的排序算法，能达到 O(nlogn)O(n \log n)O(nlogn) 时间复杂度的有堆排、归并排序和快速排序，由于这里只能通过比较得到相对大小的关系，故可以联想到快速排序。

	快速排序的核心即为定基准，划分区间。由于这里只能以对方的元素作为基准，故一趟划分区间后仅能得到某一方基准元素排序后的位置，那通过引入 O(n)O(n)O(n) 的额外空间来对已处理的基准元素进行标记如何呢？这种方法实现起来较为困难，因为只能对一方的元素划分区间，而对方的元素无法划分区间进而导致递归无法正常进行。

	山穷水尽疑无路，柳暗花明又一村。由于只能通过对方进行比较，故需要相互配合进行 partition 操作(这个点确实难以想到)。核心在于：首先使用 nuts 中的某一个元素作为基准对 bolts 进行 partition 操作，随后将 bolts 中得到的基准元素作为基准对 nuts 进行 partition 操作。
	
	nuts相当于螺母，bolts相当于螺帽
	步骤如下：
	1.拿一个螺帽 

	2. 把剩下的螺母分为2堆，一堆比第一步螺帽大，一堆比第一步螺帽小

	3. 找出与第一步相对的螺母

	4.把剩下的螺帽分为2堆，一堆比第3步的螺母大，一堆比第3步的螺母小

	难以理解的可能在partition部分，不仅需要使用compare.cmp(alist[i], pivot), 同时也需要使用compare.cmp(pivot, alist[i]), 否则答案有误。
	第二个在于alist[i] == pivot时，需要首先将其和alist[l]交换，因为i是从l+1开始处理的，将alist[l]换过来后可继续和 pivot 进行比较。
	在 while 循环退出后在将当前遍历到的小于 pivot 的元素 alist[m] 和 alist[l] 交换，此时基准元素正确归位。对这一块不是很清楚的举个例子就明白了。
 */


//Solution1: Brute force
public class Solution {
    public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {
        // write your code here
         for(int i = 0; i < nuts.length; i++){
              for(int j = i;j < bolts.length; j++){
                   if(compare.cmp(nuts[i], bolts[j]) == 0){
                        String tmp = bolts[i];
                        bolts[i] = bolts[j];
                        bolts[j] = tmp;
                        break;
                   }
              }
         }
    }
}

//Solution2:Best
public class Solution {
    public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {
        // write your code here
        if (nuts == null || bolts == null || nuts.length != bolts.length) {
            return;
        }   
        quicksort(nuts, bolts, 0, nuts.length - 1, compare);
    }
    
    public void quicksort(String[] nuts, String[] bolts, int start, int end, NBComparator compare) {
        if (start >= end) {
            return;
        }
        int pivot = partition(nuts, start, end, bolts[end], compare);
        partition(bolts, start, end, nuts[pivot], compare);
        quicksort(nuts, bolts, start, pivot - 1, compare);
        quicksort(nuts, bolts, pivot + 1, end, compare);
    }
    
    public int partition(String[] strs, int start, int end, String pivot, NBComparator compare) {
        int j = start - 1;
        for (int i = start; i < end; i++) {
            if (compare.cmp(strs[i], pivot) == -1 || compare.cmp(pivot, strs[i]) == 1) {
                j++;
                swap(strs, i, j);
            } else if (compare.cmp(strs[i], pivot) == 0) {
                swap(strs, i, end);
                i--;
            }
        }
        j++;
        swap(strs, j, end);
        return j;
    }
    
    public void swap(String[] s, int i, int j) {
        String temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
};


//Solution3:
public class Solution {
    public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {
        // write your code here
        if (nuts == null || bolts == null || nuts.length != bolts.length) {
            return;
        }   
        quicksort(nuts, bolts, 0, nuts.length - 1, compare);
    }
    
    public void quicksort(String[] nuts, String[] bolts, int start, int end, NBComparator compare) {
        if (start >= end) {
            return;
        }
        int pivot = sortBolts(nuts[start], bolts, start, end, compare);
        sortNuts(nuts, bolts[pivot], start, end, compare);
        quicksort(nuts, bolts, start, pivot - 1, compare);
        quicksort(nuts, bolts, pivot + 1, end, compare);
    }
    
    public int sortBolts(String nut, String[] bolts, int start, int end, NBComparator compare) {
        int left = start;
        int right = end;
        while (left < right) {
            while (left < right && compare.cmp(nut, bolts[left]) < 0) {
                left++;
            }
            while (left < right && compare.cmp(nut, bolts[right]) > 0) {
                right--;
            }
            
            if (left < right) {
                swap(bolts, left, right);
            }
        }
        return left;
    }
    
    private void sortNuts(String[] nuts, String bolt, int start, int end, NBComparator compare){
        int left = start;
        int right = end;
        
        while(left < right){
            while(left < right && compare.cmp(nuts[left], bolt) > 0){
                left++;
            }
            while(left < right && compare.cmp(nuts[right], bolt) < 0){
                right--;
            }
            if(left < right) {
                swap(nuts, left, right);
            }
        }
    }
    public void swap(String[] s, int i, int j) {
        String temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
};
