/*
	Add Binary 
	Given two binary strings, return their sum (also a binary string).
	For example,
		a = "11"
		b = "1"
		Return "100".
	Tags: Math, String
*/

public class Solution {
    //My self
    public String addBinary(String a, String b) {
        //add the number from the end to start
        int i = a.length() - 1;
        int j = b.length() - 1;
        //use carry to store the sum of two string
        int carry = 0;
        StringBuilder res = new StringBuilder();
        while (i >= 0 || j >= 0) {
            if (i >= 0) {
                carry += a.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                carry += b.charAt(j) - '0';
                j--;
            }
            res.insert(0, carry % 2);
            carry = carry / 2;
        }
        if (carry == 1) {
            res.insert(0, "1");
        }
        return res.toString();
    }
}