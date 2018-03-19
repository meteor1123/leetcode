/*
	Ugly Number II 

	Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

Note that 1 is typically treated as an ugly number.
*/


/*
	Solution: https://leetcode.com/discuss/52716/o-n-java-solution
*/
/*
    编写程序寻找第n个“丑陋数” ugly number
        丑陋数是指只包含质因子2, 3, 5的正整数。例如，1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前10个丑陋数。
        注意，数字1也被视为丑陋数

    提示：
        朴素的方法是对每一个数字重复调用isUgly方法，直到找到第n个为止。但是大部分数字都不是丑陋的。尝试寻找只生成丑陋数的方法
        丑陋数必须由一个较小的丑陋数乘以2,3或者5得到
        解决问题的关键是维护丑陋数的顺序。尝试一个类似于合并3个有序列表L1,L2与L3的方法
        假设你当前计算出了第k个丑陋数Uk。则Uk+1一定是Min(L1 * 2, L2 * 3, L3 * 5)
*/
public class Solution {

    //Solution1 O(n)
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int index2 = 0, index3 = 0, index5 = 0;
        int factor2 = 2, factor3 = 3, factor5 = 5;
        for (int i = 1; i < n; i++) {
            int min = Math.min(Math.min(factor2, factor3), factor5);
            ugly[i] = min;
            if (factor2 == min) {
                factor2 = 2 * ugly[++index2];
            }
            if (factor3 == min) {
                factor3 = 3 * ugly[++index3];
            }
            if (factor5 == min) {
                factor5 = 5 * ugly[++index5];
            }
        }
        return ugly[n - 1];
    }

    //Solution2 nlogn
    public int nthUglyNumber(int n) {
        if (n == 1) {
            return 1;
        }
        PriorityQueue<Long> pq = new PriorityQueue<>();
        pq.offer(1l);
        for (long i = 1; i < n; i++) {
            long temp = pq.poll();
            while (!pq.isEmpty() && pq.peek() == temp) {
                temp = pq.poll();
            }
            pq.add(temp * 2);
            pq.add(temp * 3);
            pq.add(temp * 5);
        }
        return pq.poll().intValue();
    }
}

