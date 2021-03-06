/*
	Nested List Weight Sum

	Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

	Each element is either an integer, or a list -- whose elements may also be integers or other lists.

	Example 1:
	Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)

	Example 2:
	Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)
 */

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

// DFS The algorithm takes O(N) time, where N is the total number of nested elements in the input list.
class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        return helper(nestedList, 1);
    }
    
    public int helper(List<NestedInteger> nestedList, int level) {
        int res = 0;
        for (NestedInteger num : nestedList) {
            res += num.isInteger() ? num.getInteger() * level : helper(num.getList(), level + 1);
        }
        return res;
    }
}


// BFS
class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0)
            return 0;
        int res = 0;
        int level = 1;
        Queue<NestedInteger> queue = new ArrayDeque(nestedList);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                NestedInteger num = queue.poll();
                if (num.isInteger()) {
                    res += num.getInteger() * level;
                } else {
                    queue.addAll(num.getList());
                }
            }
            level++;
        }
        return res;
    }
}