/*
	Single Number II 
	Given an array of integers, every element appears three times except for one. Find that single one.

	Note:
	Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
*/

public class Solution {
	//Solution1
	public int singleNumber(int A[], int n) {
	    int count[32] = {0};
	    int result = 0;
	    for (int i = 0; i < 32; i++) {
	        for (int j = 0; j < n; j++) {
	            if ((A[j] >> i) & 1) {
	                count[i]++;
	            }
	        }
	        result |= ((count[i] % 3) << i);
	    }
	    return result;
	}

	//Solution2: Prefer
	/*
		Solution: 
			1. 核心思想，一个int有32位bit，计算每个bit上1出现的次数，因为除了一个数出现一次，其他所有数都出现3次
					   因此 将所有数组里的数的 每个bit 分别相加，一定是3的倍数，不是3的倍数的位数就是那个只出现一次的数的1的位数！
			2. bits[i] += ((A[j] >> i) & 1);  这句话的意思是，将A[j] 右移 i位 再与1 进行单个与，
											  即可以获得A[j]第i + 1 位上的值.
			3. result |= (bits[i] << i); 当我们获得了不同位数上的1时， 比如3 = 11，  则10 | 01 即可还原成3
										 bits[i] << i 就是将这个1 左移i位，再或res，逐步获得singler umber
	*/
	public int singleNumber(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int result = 0;
        int[] bits = new int[32];
        for (int i = 0; i < 32; i++) {
            for(int j = 0; j < A.length; j++) {
            	//check the i th bit position is 1 or 0,

            	//A[j]的二进制中，从右往左数，取第i+1位上的值
                bits[i] += ((A[j] >> i) & 1); //把输入数字的第i位加起来。这里的1是按位与， 只有第1位才有1！
                //and plus the previous sum of i th bit position, 
                //if divide three and the remainder equals 0, means, has 3 or 3's  multiple bits,
                  
            }
			bits[i] %= 3; //然后求它们除以3的余数。
            //after every bit position's traversal, we use or operation to save the i th bits position's value
            //and use left shift to stroe the value.
            result |= (bits[i] << i);//把二进制表示的结果转化为十进制表示的结果  
        }
        return result;
    }
}


