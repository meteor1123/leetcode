package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {
	public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        int fizz = 0;
        int buzz = 0;
       for (int i = 1; i <= n; i++) {
           fizz++;
           buzz++;
           if (fizz == 3 && buzz == 5) {
               res.add("FizzBuzz");
               fizz = 0;
               buzz = 0;
           } else if (fizz == 3) {
               res.add("Fizz");
               fizz = 0;
           } else if (buzz == 5) {
               res.add("Buzz");
               buzz = 0;
           } else {
               res.add(i + "");
           }
       }
        return res;
    }
	public static void main(String[] args) {
		FizzBuzz test = new FizzBuzz();
		List<String> res = test.fizzBuzz(15);
		System.out.println(res);
	}
}
