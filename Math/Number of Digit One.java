/*
	Number of Digit One 
	Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

	For example:
		Given n = 13,
		Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
*/

/*
    题目理解：

    题目的意思是给定一个数n，计算[0, n]的区间中1出现的次数。

    具体思路：

    在面试过程中如果被问到类似的题目，一定不要着急，因为这肯定是一道找规律的题目。最好的办法是在白纸上写一个差不多大的数，然后分析如何计算每一位1出现的次数。

    以24071为例，如果我们想计算百位上1出现的次数，具体思路如下：

    获取百位的高位为24，获取百位的低位为71，当前百位数字为0。
    从高位出发来分析，百位出现的次数有：00100~00190,01100~01190,02100~02190,…,23100~23190。总共有(24*100)个1出现。
    这是百位为0的情况，如果百位为1的时候，那除了上述的(24*100)个1之外，还包括：24100～24171，共(71 + 1)个1，即总共：24 * 100 + 71 + 1。

    如果百位大于1例如为6的时候，那除了最开始的(24*100)个1之外，还包括了：24100~24199，共100个1，即总共：24 * 100 + （99  + 1）。

    总结规律

    其实通过上面的分析，我们就可以归纳出一个规律如下。

    要求第i位1出现的次数，并且i的高位数为highN，低位数为lowN，当前第i位的数字为cdigit，则当前i位1出现的次数分三种情况：

    cdigit == 0, count = highN * factor.
    cdigit == 1, count = highN * factor + lowN + 1.
    cdigit > 1, count = (highN + 1) * factor.
    其中，factor为当前的乘积因子，例如百位的factor为100，十位的乘积因子为10。
*/
    
/*
    intuitive: 每10个数, 有一个个位是1, 每100个数, 有10个十位是1, 每1000个数, 有100个百位是1.  做一个循环, 每次计算单个位上1得总个数(个位,十位, 百位).  
    例子:
    以算百位上1为例子:   假设百位上是0, 1, 和 >=2 三种情况: 
        case 1: n=3141092, a= 31410, b=92. 计算百位上1的个数应该为 3141 *100 次.
                百位出现的次数有 0000100 ~ 0000199(100个) 0001100 ~ 0001199（100）  3140100 ~ 3140199 （100） 一共3141 * 100

        case 2: n=3141192, a= 31411, b=92. 计算百位上1的个数应该为 3141 *100 + (92+1) 次. 多了从3141100 ~ 3141192 93个
        case 3: n=3141592, a= 31415, b=92. 计算百位上1的个数应该为 (3141+1) *100 次.  3141592 多了 从 3141100 ~ 3141199，100个
    以上三种情况可以用 一个公式概括:
    (a + 8) / 10 * m + (a % 10 == 1) * (b + 1);
*/

//http://www.cnblogs.com/EdwardLiu/p/4274497.html
public class Solution {
    public int countDigitOne(int n) {
        int res = 0;
        //每一次循环计算的是在 m位上1出现的总数，m = 1 个位 m = 10 十位 如此
        for (long m = 1; m <= n; m *= 10) {
            res += (n/m + 8) / 10 * m + (n/m % 10 == 1 ? n%m + 1 : 0);
        }
        return res;
    }
}


//Solution2
public class Solution {
    /*
        The idea is to calculate occurrence of 1 on every digit. There are 3 scenarios, for example

        if n = xyzdabc
        and we are considering the occurrence of one on thousand, it should be:
        
        (1) xyz * 1000                     if d == 0
        (2) xyz * 1000 + abc + 1           if d == 1
        (3) xyz * 1000 + 1000              if d > 1
    */
    public int countDigitOne(int n) {
        if (n <= 0) {
            return 0;
        }
        int q = n;
        int x = 1;
        int res = 0;
        do {
            int digit = q % 10;
            q /= 10;
            res += q * x;
            if (digit == 1) {
                res += n % x + 1;
            }
            if (digit > 1) {
                res += x;
            }
            x *= 10;
        } while (q > 0);
        return res;
    }
}