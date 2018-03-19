/*
	nextPermutation
	Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

	If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

	The replacement must be in-place, do not allocate extra memory.

	Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
	1,2,3 → 1,3,2
	3,2,1 → 1,2,3
	1,1,5 → 1,5,1
	Tags: Array
*/


/*
    For this problem, coding is not a big deal. Algorithm is!
    Now let's pick a number, for example, 24387651.
    what is the next permutation? 24513678.
        
    First step: find the first ascending digit from the back of the number. 
    3 < 8 > 7 > 6 > 5 > 1. Then 3 is the digit. 
    Second step: swap that digit with the next big digit in following digits. 
    Which one is the next big digit in 87651? 5!
    So swap them. Now the number becomes 24587631.
    Third step: sort 87631 into 13678. The final answer is 24513678.

*/
/*
	1. find the first one which make A[i] < A[i + 1] from the end to start of the array,
	2. find the smallest one which make A[j] > A[i] , from the index i + 1 to A.length - 1;
	3. swap them, and reverse the array from i + 1 to A.length - 1;

    1. 从后往前找，找到第一个nums[i] < nums[i + 1], 返回i的位置
    2. 判断i 是不是大于等于0， 如果i小于0， 这个数字一定是形如 321 这样的 逆序排列，下一个数字是 123， 因此只要直接reverse并返回。
    3. 所以我们这里面的操作是基于 i >= 0, 从  j = i + 1这个位置开始，从前往后找到最小的使得nums[j] > nums[i]，的j，返回j的位置
       注意到，i + 1 之后是逆序排列，所以最后大于nums[i]那个就是最小的j.
    4. swao i和j， 并reverse i+1 到 nums.length - 1 位置的数.
*/
/*

*/
public class Solution {
	public void nextPermutation(int[] num) {
        if (num == null || num.length <= 1) {
            return;
        }
        //find the first element which A[i] < A[i + 1],so the index start from num.length - 2;
        int i = num.length - 2;
        while (i >= 0 && num[i + 1] <= num[i]) {
            i--;
        }

        //if find that number, the idnex must be no less than 0;
        if (i >= 0) {
        	//the adjacent number of j
            int j = i + 1;
            //Why we don't need to compare the value?cause from j to length - 1, that is the descending array so the last large one is smallest one
            while (j < num.length && num[j] > num[i]) {// because the last sequence of array is deascending array, so the last
                                                      //one which bigger than num[i],is the smallest number which is larger than                                                        //num[i];
                j++;
            }
            
            j--;//j minus 1 is the position,smallest large num[i] one;
            swap(num, i, j);
        }
        reverse(num, i + 1, num.length - 1);
    }
    public void swap (int[] num, int i, int j) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }  
    public void reverse(int[] num, int i, int j) {
        while (i < j) {
            swap(num, i++, j--);
        }   
    }
}




