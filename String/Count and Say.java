/*
	Count and Say
	The count-and-say sequence is the sequence of integers beginning as follows:
	1, 11, 21, 1211, 111221, ...

	1 is read off as "one 1" or 11.
	11 is read off as "two 1s" or 21.
	21 is read off as "one 2, then one 1" or 1211.
	Given an integer n, generate the nth sequence.

	Note: The sequence of integers will be represented as a string.
*/

/*
	n = 1时，打印一个1。
	n = 2时，看n=1那一行，念：1个1，所以打印：11。
	n = 3时，看n=2那一行，念：2个1，所以打印：21。
	n = 4时，看n=3那一行，念：一个2一个1，所以打印：1211。

	以此类推。(注意这里n是从1开始的）
	所以构建当前行的字符串要依据上一行的字符串。“小陷阱就是跑完循环之后记得把最后一个字符也加上，因为之前只是计数而已。”
*/

// Prefer
class Solution {
    public String countAndSay(int n) {
        if (n == 0) {
            return "";
        } 
        StringBuilder res = new StringBuilder("1");

        for (int i = 1; i < n; i++) {
            StringBuilder curRes = new StringBuilder();
            int count = 1;
            for (int j = 0; j <= res.length() - 1; j++) {
                if (res.charAt(j) == res.charAt(j + 1)) {
                    count++;
                } else {
                    curRes.append(count);
                    curRes.append(res.charAt(j));
                    count = 1;
                }
            }
            curRes.append(count);
            curRes.append(res.charAt(res.length() - 1));
            res = curRes;
        }
        return res.toString();
    }        
}


public class Solution {
    public String countAndSay(int n) {
        //When the n equals zero just return empty string
        if (n <= 0) {
            return "";
        }

        //initialize the  res to "1"
        String curRes = "1";

        //build the sequence from n = 1 
        int start = 1;
        
        while (start < n) {
            StringBuilder res = new StringBuilder();

            int count = 1;
            //check the res string ,and get the character from the left side,
            //every time we check the first and second character are equals or not
            //if equals,we just increase the count 
            //if no equals we append the count and the second character to the res String
            //and reset the count
            for (int j = 1; j < curRes.length(); j++) {
                if (curRes.charAt(j) == curRes.charAt(j - 1)) {
                    count++;
                } else {
                    res.append(count);
                    res.append(curRes.charAt(j - 1));
                    count = 1;
                }
            }
            //since at the last time of every end of for loop,we just count the number,
            //so we need to append the count and the last number
            res.append(count);
            res.append(curRes.charAt(curRes.length() - 1));
            curRes = res.toString();
            start++;
        }
        return curRes;
    }
}

