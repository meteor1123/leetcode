/*
	Largest Number 
	Given a list of non negative integers, arrange them such that they form the largest number.
	For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
	Note: The result may be very large, so you need to return a string instead of an integer.
*/

/*
	    //算法的关键在于比较两个子数字字符串的大小，如何比较呢？
        //如果[a在前b在后]组成的结果大于[b在前a在后]组成的结果，那么a>b。
        // change the int array to the String array.
        // sorting the num
        // concate all the String 
        // sort by ascending
        // 去掉数字开头的0，如输入[0,0]
*/

/*
    1. convert the int[] num to String[] numStringArr
    2. set up a Comparator to sort the String[], the most important part is how to make the sort rule?
            how to compare these two String number? if StrNum1 + StrNum2 > StrNum1 + StrNum2, which mean StrNum2 should be smaller than StrNum1
    3. sort it by using new comparator
    4. check the first string's first character, if equals 0, that must be a zero
    5. append the string ,concate to a new string and return, that is the largest number.
*/
public class Solution {
        public String largestNumber(int[] num) {
        // write your code here
        if (num == null || num.length == 0) {
            return "";
        }
        
        String[] stringArr = new String[num.length];
        
        for (int i = 0; i < num.length; i++) {
            stringArr[i] = num[i] + "";
        }
        
        Comparator<String> comp = new Comparator<String>(){
            @Override
            public int compare(String str1, String str2) {
                String s1 = str1 + str2;
                String s2 = str2 + str1;
                return s2.compareTo(s1);
            }
        };
        
        Arrays.sort(stringArr, comp);
        if (stringArr[0].charAt(0) == '0') {
            return "0";
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < stringArr.length; i++) {
            sb.append(stringArr[i]);
        }
        return sb.toString();
    }
    // public String largestNumber(int[] num) {
    // 	int n = num.length;
    // 	if (n < 1)
    // 		return "";
    // 	Comparator<String> comp = new Comparator<String>();
    // 	@Override
    // 	public int compare(String a, String b) {
    // 		String ab = a.concat(b);
    // 		String ba = b.concat(a);
    // 		return Integer.parseInt (ab) - Integer.parseInt(ba);
    // 	}

    // 	String[] strs = new String[n];
    // 	for (int i = 0; i < n; i++)
    // 		sts[i] = String.valueOf(num[i]);

    // 	Arrays.sort(strs, comp);

    // 	String ans = "";

    // 	for (int i = n - 1; i >= 0; i--)
    // 		ans += ans.concat(strs[i]);
    // 	int i = 0;
    // 	while (i < n && ans.charAt(i) == '0')
    // 		i++;
    // 	if (i == n)
    // 		return "0";
    // 	return ans.substring(i);
    // }
public  String largestNumber(int[] num) {
        if(num==null || num.length==0)
            return "";
        String[] Snum = new String[num.length];
        //将数字转化为字符串存入字符串数组
        for(int i=0;i<num.length;i++)
            Snum[i] = num[i]+"";
    
    	//重写comparator的compare方法
        Comparator<String> comp = new Comparator<String>(){
            @Override
            public int compare(String str1, String str2){
                String s1 = str1+str2;
                String s2 = str2+str1;
                return s1.compareTo(s2);
            }
        };

        //排序 从小到大
        Arrays.sort(Snum, comp);

        //假如最大的第一个字符 == 0.那说明肯定为0
        if(Snum[Snum.length-1].charAt(0)=='0')
            return "0";
    	
        StringBuilder sb = new StringBuilder();
    	
    	//insert(0, s)不停将大的往前插。
        for(String s: Snum)
            sb.insert(0, s);
        return sb.toString();
    }
}






