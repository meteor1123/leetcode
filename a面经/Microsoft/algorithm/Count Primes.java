/*
	Count Primes
	Description:
	Count the number of prime numbers less than a non-negative number, n
	Tags: HashTable Math
*/

public class Solution {

    /*
        这个做法是这样比如求n = 16;
            i = 2 :  start from 2 * 2 -> 4 6 8 10 12 14 
            i = 3 :  start from 3 * 3 -> 9 12(continue) 15
            i = 4 * 4 > n break，

            所有不在上面的数字就都是prime 2 3 5 7 11 13


    */
	//Solution1
	public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }
        for (int i = 2; i * i < n; i++) {
            if (!isPrime[i]) {
                continue;
            }
            for (int j = i * i; j < n; j = j + i) {
                isPrime[j] = false;
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }
        return count;
    }

    //Solution2
	public int countPrimes(int n) {
		int count=0;
	    boolean[] nums = new boolean[n];
	    for(int i = 2; i < nums.length; i++){
	    	if(!nums[i]){
	    		count++;
	            for(int j = 2 * i; j < nums.length; j = j + i){
	            	nums[j] = true;
	            }
	        }
	    }
	    return count;
	}
}