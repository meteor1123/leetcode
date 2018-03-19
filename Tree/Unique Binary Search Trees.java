/*
	Unique Binary Search Trees
	Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

	For example,
	Given n = 3, there are a total of 5 unique BST's.
*/

/*
	“这道题要求可行的二叉查找树的数量，其实二叉查找树可以任意取根，只要满足中序遍历有序的要求就可以。
	从处理子问题的角度来看，选取一个结点为根，就把结点
	切成左右子树，以这个结点为根的可行二叉树数量就是左右子树可行二叉树数量的乘积，
	所以总的数量是将以所有结点为根的可行结果累加起来。写成表达式如下：

	C0 = 1 and Cn+1 = 求和（CiCn - i）， i从0~n 对于n >= 0;

	熟悉卡特兰数的朋友可能已经发现了，这正是卡特兰数的一种定义方式，是一个典型的动态规划的定义方式（根据其实条件和递推式求解结果）。
	所以思路也很明确了，维护量res[i]表示含有i个结点的二叉查找树的数量。根据上述递推式依次求出1到n的的结果即可。
	时间上每次求解i个结点的二叉查找树数量的需要一个i步的循环，总体要求n次，所以总时间复杂度是O(1+2+...+n)=O(n^2)。
	空间上需要一个数组来维护，并且需要前i个的所有信息，所以是O(n)。"



	 这题想了好久才想清楚。其实如果把上例的顺序改一下，就可以看出规律了。 

      1         3     3      2      1
       \       /     /      / \      \
        3     2     1      1   3      2
       /     /       \                 \
      2     1         2                 3
 

   比如，以1为根的树有几个，完全取决于有二个元素的子树有几种。同理，2为根的子树取决于一个元素的子树有几个。以3为根的情况，则与1相同。

    定义Count[i] 为以[0,i]能产生的Unique Binary Tree的数目，

    如果数组为空，毫无疑问，只有一种BST，即空树，
    Count[0] =1

    如果数组仅有一个元素{1}，只有一种BST，单个节点
    Count[1] = 1

    如果数组有两个元素{1,2}， 那么有如下两种可能
    1                       2
     \                    /
       2                1
    Count[2] = Count[0] * Count[1]   (1为根的情况)
                  + Count[1] * Count[0]  (2为根的情况。

    再看一遍三个元素的数组，可以发现BST的取值方式如下：
    Count[3] = Count[0]*Count[2]  (1为根的情况)
                  + Count[1]*Count[1]  (2为根的情况)
                  + Count[2]*Count[0]  (3为根的情况)

    所以，由此观察，可以得出Count的递推公式为
    Count[i] = ∑ Count[0...k] * [ k+1....i]     0<=k<i-1
    问题至此划归为一维动态规划。

*/
/*
  https://leetcode.com/discuss/24282/dp-solution-in-6-lines-with-explanation-f-i-n-g-i-1-g-n-i
  非常好的讲解！！！
*/
// prefer
class Solution {
    public int numTrees(int n) {
        int[] res = new int[n + 1];
        res[0] = 1;
        // f(n) == f(0)*f(n - 1) + f(1)*f(n - 2) + ... + f(n - 2)*f(1) + f(n - 1)*f(0)
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                res[i] += res[j] * res[i - j - 1];  //总共有i个节点，res[j]是左节点， res[i - j - 1]是右节点， 因为root占用1个节点
            }
        }
        return res[n];
    }
}