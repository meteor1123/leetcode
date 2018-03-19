/*
	Expression Add Operators
	Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

	Examples: 
	"123", 6 -> ["1+2+3", "1*2*3"] 
	"232", 8 -> ["2*3+2", "2+3*2"]
	"105", 5 -> ["1*0+5","10-5"]
	"00", 0 -> ["0+0", "0-0", "0*0"]
	"3456237490", 9191 -> []
*/

/*
	Solution:
    	这道题给了我们一个只由数字组成的字符串，让我们再其中添加+,-或*号来形成一个表达式，
    	该表达式的计算和为给定了target值，让我们找出所有符合要求的表达式来。题目中给的几个例子其实并不好，
    	很容易让人误以为是必须拆成个位数字，其实不是的，比如"123", 15能返回"12+3"，说明连着的数字也可以。
    	如果非要在过往的题中找一道相似的题，我觉得跟Combination Sum II 组合之和之二很类似。
    	不过这道题要更复杂麻烦一些。还是用递归来解题，我们需要两个变量diff和curNum，一个用来记录将要变化的值，另一个是当前运算后的值，
    	而且它们都需要用long long型的，因为字符串转为int型很容易溢出，所以我们用长整型。
    	对于加和减，diff就是即将要加上的数和即将要减去的数的负值，
    	而对于乘来说稍有些复杂，此时的diff应该是上一次的变化的diff乘以即将要乘上的数，有点不好理解，
    	那我们来举个例子，比如2+3*2，即将要运算到乘以2的时候，上次循环的curNum = 5, diff = 3, 而如果我们要算这个乘2的时候，新的变化值diff应为3*2=6，
    	而我们要把之前+3操作的结果去掉，再加上新的diff，即(5-3)+6=8，即为新表达式2+3*2的值，有点难理解，大家自己一步一步推算吧。

    	还有一点需要注意的是，如果输入为"000",0的话，容易出现以下的错误：

    	Wrong:["0+0+0","0+0-0","0+0*0","0-0+0","0-0-0","0-0*0","0*0+0","0*0-0","0*0*0","0+00","0-00","0*00","00+0","00-0",

    	"00*0","000"]

    	Correct：["0*0*0","0*0+0","0*0-0","0+0*0","0+0+0","0+0-0","0-0*0","0-0+0","0-0-0"]

    	我们可以看到错误的结果中有0开头的字符串出现，明显这不是数字，所以我们要去掉这些情况，过滤方法也很简单，
    	我们只要判断长度大于1且首字符是‘0’的字符串，将其滤去即可，参见代码如下：
*/

/*
    This problem has a lot of edge cases to be considered:

    1. overflow: we use a long type once it is larger than Integer.MAX_VALUE or minimum, we get over it.
    2. 0 sequence: because we can’t have numbers with multiple digits started with zero, we have to deal with it too.
    3. a little trick is that we should save the value that is to be multiplied in the next recursion.
*/
public class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if (num == null || num.length() == 0) {
            return res;
        }
        helper(res, "", num, target, 0, 0, 0);
        return res;
    }
    
    //eval用来记录此次操作以后的总值，multed仅记录这步运算符后的值， 
    //比如 2 + 3 * 5, sum = 2 + 3 * 5= 17, multed = 3 * 5=15
    public void helper(List<String> res, String item, String num, int target, int index, long sum, long multed) {
        if (index == num.length()) {
            if (target == sum) {
                res.add(item);
            }
            return;
        }
        
        for (int i = index; i < num.length(); i++) {
            if (i != index && num.charAt(index) == '0') {//滤掉有0开头的字符串，我们只要判断长度大于1且首字符是‘0’的字符串，将其滤去即可
                break;
            }
            long cur = Long.valueOf(num.substring(index, i + 1));
            if (index == 0) {
                helper(res, item + cur, num, target, i + 1, cur, cur);//处理首位数字
            } else {
                helper(res, item + "+" + cur, num, target, i + 1, sum + cur, cur);
                helper(res, item + "-" + cur, num, target, i + 1, sum - cur, -cur);

                helper(res, item + "*" + cur, num, target, i + 1, sum - multed + multed * cur, multed * cur);
                //比如 2 + 5 * 3的时候，要将原来的eval = 2 + 5 = 7 减去5， 再乘以3，
            }
        }
         //不需要backtracking，因为每一步都分三种情况递进
    }
}