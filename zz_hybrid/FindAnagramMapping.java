package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class FindAnagramMapping {
	public int[] anagramMappings(int[] A, int[] B) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
        	map.put(B[i], i);
        }
        int[] ans = new int[A.length];
        for (int i = 0; i < A.length; i++) {
        	ans[i] = map.get(A[i]);
        }
        return ans;
    }
	public static void main(String[] args) {
		FindAnagramMapping test = new FindAnagramMapping();
		int[] A = {12,28,46,32,50};
		int[] B = {50,12,32,46,28};
		int[] res = test.anagramMappings(A, B);
		for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " "); 
        }
	}
}
