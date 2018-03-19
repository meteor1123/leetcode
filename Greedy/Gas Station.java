/*
	Gas Station
	There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
	You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

	Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

	Note:
	The solution is guaranteed to be unique.
	Tags: Greedy
*/


/*
	gas[i] 表示 加油站i的储油量
	cost[i] 表示从加油站i到加油站i+1所需的油量，因此gas[i] - cost[i] > 0时 意味着以这个为起点，i 一定能到i + 1
	贪心策略： tank表示油箱的变化，如果从点i 到 点j 的油量diff大于0 则一直往前走，tank +=gas[i] - cost[i],如果小于0，则将点j设为起点,tank清空，从该点开始重新计算继续走，
			 total 会一直记录所有的gas - cost的差值，不会清空。
			 最后判断total是否小于0，小于0表示没有这样的一个点，大于等于0则返回点j

	Analysis:
	Theorem: if the total gas is greater than the total cost, i.e., gas(1) + gas(2) + … + gas(n) > cost(1) + cost(2) + … + cost(n), 
	there must be a way to travel around the circuit once.


	Proof:

	1）If there is only one gas station, it’s true.
	
	2）If there are two gas stations a and b, and gas(a) cannot afford cost(a), i.e., gas(a) < cost(a), then gas(b) must be greater than cost(b), 
	   i.e., gas(b) > cost(b), since gas(a) + gas(b) > cost(a) + cost(b); so there must be a way too.
	3.1）If there are three gas stations a, b, and c, where gas(a) < cost(a), i.e., we cannot travel from a to b directly, then:
	   either if gas(b) < cost(b), i.e., we cannot travel from b to c directly, then cost(c) > cost(c), 
	   so we can start at c and travel to a; since gas(b) < cost(b), gas(c) + gas(a) must be greater than cost(c) + cost(a), 
	   so we can continue traveling from a to b. Key Point: this can be considered as there is one station at c’ with gas(c’) = gas(c) + gas(a) 
	   and the cost from c’ to b is cost(c’) = cost(c) + cost(a), and the problem reduces to a problem with two stations. 
	   This in turn becomes the problem with two stations above.
	3.2）or if gas(b) >= cost(b), we can travel from b to c directly. Similar to the case above, 
	    this problem can reduce to a problem with two stations b’ and a, where gas(b’) = gas(b) + gas(c) and cost(b’) = cost(b) + cost(c). 
	    Since gas(a) < cost(a), gas(b’) must be greater than cost(b’), so it’s solved too.

	4)For problems with more stations, we can reduce them in a similar way. In fact, as seen above for the example of three stations, 
	the problem of two stations can also reduce to the initial problem with one station.


*/
public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int tank = 0;
        int total = 0;
        int start = 0;
        for(int i = 0; i < gas.length; i++) {
            tank += gas[i] - cost[i];
            total += gas[i] - cost[i];
            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }
        if (total < 0) {
            return -1;
        }
        return start;
    }
}