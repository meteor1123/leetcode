/*
	Implement pow(x, n)
	Tag: Binary Search, Math
	Hints:we notice that x^n = x^(n/2) * x^(n/2) * x^(n % 2) 
*/

// recursive 
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

// Iterative logn
class Solution {
    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        
        double res = 1;
        double currentProduct = x;
        
        for (long i = N; i > 0; i /= 2) {
            if (i % 2 == 1) {
                res = res * currentProduct;
            }
            currentProduct = currentProduct * currentProduct;
        }
        return res;
    }

}