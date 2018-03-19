/*
	Find the Celebrity
	Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. 

    The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

	Now you want to find out who the celebrity is or verify that there is not one. 
    The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. 
    You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

	You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.

	Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.

	Tags: Array
*/

/*
    1. 遍历数组，找一个candidate， 
        这里用这句是关键
            if(!knows(i,candidate)) 
                candidate = i
            只要有个candidate，i不认识它，则i就被选为candidate，上一个candidate自然也可以去掉
            为什么一次这样的遍历可以得到这个candidate值？
            因为只要遍历到这个candidate：
                1） 一定会有 这个（celebrity）不认识前面的candidate，确保进入!knows(i,candidate)判断语句，并赋值为candidate
                2） 后面的i认识这个candidate，确保不会进入!knows(i,candidate)判断语句，因此candidate不会变
            但是有可能会出现不存在candidate的情况也能找到这样的一个candidate：
                i不认识前面的i - 1，后面的i + 1 认识i，但是i认识i - 2，这样就是错误的candidate，所以需要进入二层循环判断
*/
//Best Solution
public class Solution extends Relation {
    public int findCelebrity(int n) {
        if (n <= 1) {
            return n;
        }
        int candidate = 0;
        //一次判断找出candidate
        for (int i = 1; i < n; i++) {
            if (!knows(i, candidate)) {
                candidate = i;
            }
        }
        
        //二次判断这个是不是正确的candidate
        //有可能这个 1
        for (int j = 0; j < n; j++) {
            if (j == candidate) {
                continue;
            }
            if (knows(candidate, j) || !knows(j, candidate)) {
                return -1;
            } 
        }
        return candidate;
    }
} 


/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

      
//Solution by myself, O(n*n) time, O(n) space
public class Solution extends Relation {
    public int findCelebrity(int n) {
        if (n == 0) {
            return -1;
        }
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ( i != j && knows(i, j) && !knows(j, i)) {
                    if (!map.containsKey(j)) {
                        map.put(j, 1);
                    } else {
                        map.put(j, map.get(j) + 1);
                    }
                }
            }
        }

        for (int i : map.keySet()) {
            if (map.get(i) == n - 1) {
                return i;
            }
        }
        return -1;
    }
}