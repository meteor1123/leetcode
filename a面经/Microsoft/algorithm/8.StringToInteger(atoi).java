/*
Implement atoi to convert a string to an integer.
Hint: Carefully consider all possible input cases. 
If you want a challenge, please do not see below and ask yourself what are the possible input cases.
Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.

*/

 /*
        1. The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. 
        2. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, 
           and interprets them as a numerical value.
        3. The string can contain additional characters after those that form the integral number, 
           which are ignored and have no effect on the behavior of this function.
        4. If the first sequence of non-whitespace characters in str is not a valid integral number,
           or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
        5. If no valid conversion could be performed, a zero value is returned. 
           If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
    
*/

/*
    1. null or empty string
    2. white spaces
    3. +/- sign
    4. calculate real value
    5. handle min & max
 */
    public int atoi(String str) {
        //corner case
        if (str == null || str.length() < 1)
            return 0;
        
        //remove the leading and trailing white blank
        str = str.trim();
        
        //determine the flag of '+' or '-'
        char flag = '+';
        int i = 0;
        if (str.charAt(i) == '-') {
            flag = '-';
            i++;
        } else if (str.charAt(i) == '+') {
            i++;
        }
        
        //Using double to store the result
        double result = 0;
        
        //determine every char in str
        //put a constraint '0' < str.charAt(i) <'9'
        while (i < str.length() && str.charAt(i) <= '9' && str.charAt(i) >= '0') {
            //example: 251 = 0*10 + 2-> 2*10 + 5-> 25*10 + 1 = 251
            result = result * 10 +(str.charAt(i) - '0'); 
            i++;
        }
        
        //determine the flag
        if (flag == '-')
            result = -result;
            
        //corner case of overflow
        if (result >= Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        if (result <= Integer.MIN_VALUE)
            return Integer.MIN_VALUE;
        
        //dont forget to turn to Integer
        return (int)result;
    }