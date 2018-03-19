/*
	Strobogrammatic Number III
	A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

	Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

	For example,
	Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.

	Note:
	Because the range might be a large number, the low and high numbers are represented as string.
*/


/*
    Solution：
        1. 沿用strobrogrammaticII 的方法生成长度为n的所有 number
        2. n由 low和high字符的length()确定， 比如low = “50" high = "150" length of n就是 from 2 to 3
        3. 将生成的所有number判断是否小于num
*/
public class Solution {
    public int strobogrammaticInRange(String low, String high) {
        int count = 0;
        List<String> res = new ArrayList<String>();
        for (int i = low.length(); i <= high.length(); i++) {
            res.addAll(helper(i, i));
        }
        
        for (String num : res) {
            // if ((num.length() == low.length() && num.compareTo(low) < 0) || (num.length() == high.length() && num.compareTo(high) > 0)) {
            //     continue;
            // }
            // count++;
            for (String num : res) {
                if (Long.valueOf(num) >= Long.valueOf(low) && Long.valueOf(num) <= Long.valueOf(high)) {
                    count++;
                }//只要num大于等于low，小于等于high就是合法的数字，count++
            }
        }
        return count;
    }
    
    public List<String> helper(int cur, int max) {
        if (cur == 0) {
            return new ArrayList<String>(Arrays.asList(""));
        }
        if (cur == 1) {
            return new ArrayList<String>(Arrays.asList("1", "8", "0"));
        }
        
        List<String> res = new ArrayList<String>();
        List<String> center = helper(cur - 2, max);
        
        for (int i = 0; i < center.size(); i++) {
            String tmp = center.get(i);
            if (cur != max) {
                res.add("0" + tmp + "0");
            }
            res.add("1" + tmp + "1");
            res.add("6" + tmp + "9");
            res.add("8" + tmp + "8");
            res.add("9" + tmp + "6");
        }
        return res;
    }
}