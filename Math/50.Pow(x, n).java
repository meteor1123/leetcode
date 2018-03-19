/*
	Implement pow(x, n)
	Tag: Binary Search, Math
	Hints:we notice that x^n = x^(n/2) * x^(n/2) * x^(n % 2) 
*/

public class Solution {
    //Help Method
	public double power(double x, int n) {
        if (n == 0)
            return 1;
        double res = power(x, n/2);
        if (n % 2 == 0)
            return res * res;
        else 
            return res * res * x;
    }
    

    public double pow(double x, int n) {
    	//check the n is '+'' or '-'s'
        if (n < 0) {
            return 1 / power(x, -n);
        } else 
            return power(x, n);
    }
}



    public double power(double x, int n) {
        if (n > 0) {
            return powHelper(x, n);
        } else {
            return 1 / powHelper(x, -n);
        }
    }
    public double powHelper(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double res = powHelper(x, n / 2);
        if (n % 2 == 0) {
            return res * res;
        } else {
            return res * res * x;
        }
        
    }
