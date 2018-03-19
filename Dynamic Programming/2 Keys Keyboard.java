/*
    2 Keys Keyboard

    Initially on a notepad only one character 'A' is present. You can perform two operations on this notepad for each step:

    Copy All: You can copy all the characters present on the notepad (partial copy is not allowed).
    Paste: You can paste the characters which are copied last time.
    Given a number n. You have to get exactly n 'A' on the notepad by performing the minimum number of steps permitted. Output the minimum number of steps to get n 'A'.

    Example 1:
    Input: 3
    Output: 3
    Explanation:
    Intitally, we have one character 'A'.
    In step 1, we use Copy All operation.
    In step 2, we use Paste operation to get 'AA'.
    In step 3, we use Paste operation to get 'AAA'.
    Note:
    The n will be in the range [1, 1000].
*/
/*
    Solution:

    当n = 1时，已经有一个A了，我们不需要其他操作，返回0

    当n = 2时，我们需要复制一次，粘贴一次，返回2

    当n = 3时，我们需要复制一次，粘贴两次，返回3

    当n = 4时，这就有两种做法，一种是我们需要复制一次，粘贴三次，共4步，另一种是先复制一次，粘贴一次，得到AA，然后再复制一次，粘贴一次，得到AAAA，两种方法都是返回4

    当n = 5时，我们需要复制一次，粘贴四次，返回5

    当n = 6时，我们需要复制一次，粘贴两次，得到AAA，再复制一次，粘贴一次，得到AAAAAA，共5步，返回5

    通过分析上面这6个简单的例子，我想我们已经可以总结出一些规律了，首先对于任意一个n(除了1以外)，我们最差的情况就是用n步，不会再多于n步，但是有可能是会小于n步的，
    比如n=6时，就只用了5步，仔细分析一下，发现时先拼成了AAA，再复制粘贴成了AAAAAA。那么什么情况下可以利用这种方法来减少步骤呢，分析发现，小模块的长度必须要能整除n，这样才能拆分。
    对于n=6，我们其实还可先拼出AA，然后再复制一次，粘贴两次，得到的还是5。分析到这里，我想解题的思路应该比较清晰了，我们要找出n的所有因子，然后这个因子可以当作模块的个数，我们再算出模块的长度n/i，调用递归，加上模块的个数i来更新结果res即可
*/

// 对于素数来说 需要本身值的次数, 而其他的数找因子中的最小
class Solution {
    public int minSteps(int n) {      
        int[] dp = new int[n + 1];
        
        for (int i = 2; i <= n; i++) {
             // sub-problem dp[i] := minSteps(i)
            for (int j = 2; j <= i; j++) {
                // j := use j steps to create j copies
                if (i % j == 0) {
                    dp[i] = j + dp[i / j]; //从前面开始一旦找到，就一定是次数最小的
                    break;
                }
            }
        }
        return dp[n];
    }
}

// O(1) space solution:
class Solution {
    public int minSteps(int n) {      
        int res = 0;
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                res += i;
                n /= i;
            }
        }
        return res;
    }
}

// 递归
class Solution {
    public int minSteps(int n) {      
        if (n == 1)
            return 0;
        int res = n;
        for (int i = 2; i <= n; i++) {
            if (n % i == 0) {
                res = Math.min(minSteps(n / i) + i, res);
            }
        }
        return res;
    }
}