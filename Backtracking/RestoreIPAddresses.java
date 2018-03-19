/*
	Restore IP Addresses
	Given a string containing only digits, restore it by returning all possible valid IP address combinations.

	For example:
	Given "25525511135",

	return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
*/


/*
	利用循环递归解决子问题。对于每个段内数来说，最多3位最少1位，所以在每一层可以循环3次，来尝试填段。因为IP地址最多4个分段，当层数是3的时候说明已经尝试填过3个段了，那么把剩余没填的数段接到结尾即可。
	这个过程中要保证的是填的数是合法的，最后拼接的剩余的数也是合法的。
	注意开头如果是0的话要特殊处理，如果开头是0，判断整个串是不是0，不是的话该字符就是非法的。因为001，01都是不对的。
*/
public class Solution {
    //recursive
    public List<String> restoreIpAddresses(String s) {
    	ArrayList<String> res = new ArrayList<String>();
    	if (s == null || s.length() < 4 || s.length() > 12)
    		return res;
    	dfs(s, 0, item, res);
    	return res;
    }
    public void dfs(String s, int start, String item, ArrayList<String> res) {
    	if (start == 3 && isValid(s)) {
    		res.add(item + s);
    		return ;
    	}
    	for (int i = 0; i < 3 && i < s.length() - 1; i++) {
    		String substr = s.substring(0, i + 1);
    		if (isValid(substr))
    			dfs(s.substring(i + 1, s.length()), start + 1, item + substr + '.', res)
    	}
    }

    public boolean isValid(String s) {
    	if (s.charAt(0) == '0')
    		return s.equals("0");
    	int num = Integer.parseInt(s);
    	if (num <= 255 && num > 0)
    		return true;
    	else 
    		return false;
    }

    //Iterative
    /*
        3-loop divides the string s into 4 substring: s1, s2, s3, s4. 
        Check if each substring is valid. 
        In isValid, strings whose length greater than 3 or equals to 0 is not valid; 
        or if the string's length is longer than 1 and the first letter is '0' then it's invalid;
         or the string whose integer representation greater than 255 is invalid.


    */
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<String>();
        int len = s.length();
        //i means the first ip address block,
        for (int i = 1; i < 4 && i < len - 2; i++) { 
            //j is the second ip address block
            for (int j = i + 1; j < i + 4 && j < len - 1; j++) {
                //k is the 
                for (int k = j + 1; k < j + 4 && k < len; k++) {
                    String s1 = s.substring(0, i);
                    String s2 = s.substring(i, j);
                    String s3 = s.substring(j, k);
                    String s4 = s.substring(k, len);
                    if (isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4)) {
                        res.add(s1 + "." + s2 + "." + s3 + "." + s4);
                    }
                }
            }
        }
        return res;
    }
    
    public boolean isValid(String s) {
        if (s.length() > 3 || s.length() == 0 || (s.charAt(0) == '0' && s.length() > 1) 
            ||Integer.parseInt(s) > 255) {
                return false;
        }
        return true;
    }
}