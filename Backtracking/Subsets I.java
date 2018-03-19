nr/*
	Subsets
	Given a set of distinct integers, S, return all possible subsets.

	Note:
	Elements in a subset must be in non-descending order.
	The solution set must not contain duplicate subsets.
	For example,
	If S = [1,2,3], a solution is:

	[
	  [3],
	  [1],
	  [2],
	  [1,2,3],
	  [1,3],
	  [2,3],
	  [1,2],
	  []
	]
*/

/*
	套用combination的方法，其实combination那道题就是在求不同n下的subset，这里其实是要求一个集合罢了。
 例如k=3，n=1，用combination那道题的方法求得集合是[[1], [2], [3]]；
    k=3, n=2, 用combination那道题的方法求得集合是[[1, 2], [1, 3], [2, 3]]
    k=3, n=3, 用combination那道题的方法求得集合是[[1,2,3]]
所以上述3个集合外加一个空集不就是
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
么？
只需要在combination的外面加个循环即可。
*/

/*
    Time complexity is O(2^n), 
    where n is the cardinality of the set. 
    Space complexity is O(n).
*/
public class Solution {

    //concise solution
    public ArrayList<ArrayList<Integer>> subsets(int[] S) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (S == null || S.length == 0) {
            return res;
        }
        ArrayList<Integer> item = new ArrayList<Integer>();
        Arrays.sort(S);
        res.add(new ArrayList<Integer>());
        subsetsHelper(0, item, res, S);
        return res;
    }
    
    public void subsetsHelper(int start, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res, int[] S) {
        for (int i = start; i < S.length; i++) {
            item.add(S[i]);
            res.add(new ArrayList<Integer>(item));
            subsetsHelper(i + 1, item, res, S);
            item.remove(item.size() - 1);
        }
    }

    //Iterative
    public ArrayList<ArrayList<Integer>> subsets(int[] S) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        //at first the res only has a null elment
        res.add(new ArrayList<Integer>());
        //and we sort the array S
        Arrays.sort(S);
        //get the number from sort S
        for (int i : S) {
            /*
                like at first is res = { {} }
                                res = { {} ,{1} }
                                res = { {}, {1} } + {{2} ,{1, 2} } = {{}, {1}, {2}, {1, 2}};
                                res = {{3}, {1,3}, {2, 3}, {1, 2, 3}} + {{}, {1}, {2}, {1, 2}}
                                    = [
                                          [3],
                                          [1],
                                          [2],
                                          [1,2,3],
                                          [1,3],
                                          [2,3],
                                          [1,2],
                                          []
                                        ]
            */
            List<List<Integer>> tmp = new ArrayList<>();
            for (List<Integer> sub : res) {
                List<Integer> a = new ArrayList<>(sub);
                a.add(i);
                tmp.add(a);
            }
            res.addAll(tmp);
        }
        return res;
    }



  //Solution1: combination 加个循环
	public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        List<Integer> item = new ArrayList<>();
        Arrays.sort(nums);
        //迭代不同长度下dfs
        for (int i = 1; i <= nums.length; i++) {
            helper(res, nums, item, i, 0);
        }
        //别忘了加空集
        res.add(new ArrayList<>());
        return res;
        
    }
    
    public void helper(List<List<Integer>> res, int[] nums, List<Integer> item, int len, int start) {
        if(item.size() == len) {
            res.add(new ArrayList<Integer>(item));
            return;
        }
        
        for (int i = start; i < nums.length; i++) {
            item.add(nums[i]);
            helper(res, nums, item, len, i + 1);
            item.remove(item.size() - 1);
        }
    }
}