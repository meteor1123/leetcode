/*	
	Space Replacement
	
	Write a method to replace all spaces in a string with %20. The string is given in a characters array, 
	you can assume it has enough space for replacement and you are given the true length of the string.
	You code should also return the new length of the string after replacement.
	Example
		Given "Mr John Smith", length = 13.
		The string after replacement should be "Mr%20John%20Smith".
	Challenge
		Do it in-place.
*/
public class Solution {
    public int replaceBlank(char[] string, int length) {
        int realLen = length;
        for (int i = 0; i < length; i++) {
            if (string[i] == ' ') {
                realLen += 2;
            }
        }
        int index = realLen;
        for (int i = length - 1; i>= 0; i--) {
            if (string[i] == ' ') {
                string[--index] = '0';
                string[--index] = '2';
                string[--index] = '%';
            } else {
                string[--index] = string[i];
            }
        }
        return realLen;
    }
}
