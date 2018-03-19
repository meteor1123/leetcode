/*
	Implement int sqrt(int x).
	Compute and return the square root of x.
	Tags: Binary Search, Math
*/

//Solution2 prefer
class Solution {
    public int mySqrt(int x) {
        if (x == 1)
            return 1;
        
        long start = 1;
        long end = x;
        while (start + 1 < end) {
            long mid = start + (end - start) / 2;
            if (mid * mid <= x) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        /*
            若x无法开整数根号（在上述查找中没有找到），那么我们仍然可以利用之前对二分查找法总结的技巧，当target值不在数组中，
            low指向大于target的那个值，high指向小于target的那个值，由于我们需要向下取整的结果，
            所以我们返回high指向的值（这里high指向的值和high的值是同一个值），这个值就是所求得最接近起开根号结果的整数值。
        */
        if (end * end <= x) 
            return (int)end;
        return (int)start;
    }
}