/*
	Combination Sum 
	Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
	The same repeated number may be chosen from C unlimited number of times.

	Note:
		All numbers (including target) will be positive integers.
		Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
		The solution set must not contain duplicate combinations.

	For example, given candidate set 2,3,6,7 and target 7, 
		A solution set is: 
		[7] 
		[2, 2, 3] 
	Tags:Array Backtracking

	和II的 区别在于数组里的数可以无限的试探使用

    This algorithm has time complexity O((n+k)!) 
        where n is the size of candidates, 
        and k is the max repeated times for each candidates
    Space complexity O(m) where m is the size of array for the solution.
*/


public class Solution {

    //DFS1 prefer
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList();
        if (candidates == null || candidates.length == 0)
            return res;
        Arrays.sort(candidates);
        List<Integer> item = new ArrayList();
        dfs(res, item, candidates, target, 0);
        return res;
    }
    
    public void dfs(List<List<Integer>> res, List<Integer> item, int[] candidates, int sum, int index) {
        if (sum < 0)
            return;
        if (sum == 0) {
            res.add(new ArrayList(item));
            return;
        }
        
        for (int i = index; i < candidates.length; i++) {
            int newSum = sum - candidates[i];
            item.add(candidates[i]);
            dfs(res, item, candidates, newSum, i);
            item.remove(item.size() - 1);
        }
    }

	//DFS2递归
	public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> item = new ArrayList<Integer>();
		if (candidates.length == 0 || candidates == null)
			return res;
		Arrays.sort(candidates);
		dfs(candidates, target, 0, item, res);
		return res;
	}

	private void dfs(int[] candidates, int target, int start, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res) {
		if (target < 0)
			return ;

		//dfs终点条件，target值不停在变
		if (target == 0) {
			res.add(new ArrayList<Integer>(item));
			return;
		}

		for (int i = start; i < candidates.length; i++) {
			// The same repeated number may be chosen from C unlimited number of times.
			if (i > 0 && candidates[i] == candidates[i - 1])//假如candidates[i]和 [i- 1]相同，则之前已经遍历过该数字的所有情况，因此跳出选下个数字
				continue;//deal with dupicate
				
			item.add(candidates[i]);
			int newTarget = target - candidates[i];//关键
			dfs(candidates, newTarget, i, item, res);
			//之所以不传i+1，是因为The same repeated number may be chosen from C
			//unlimited number of times. num[i]可以任意使用n多次
			item.remove(item.size() - 1);
		}
	}

	//非递归
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		 // sort candidates to try them in asc order
        Arrays.sort(candidates);
        List<List<List<Integer>>> dp = new ArrayList<List<List<Integer>>>();

        for (int i = 1; i <= target; i++) {// run through all targets from 1 to t
            List<List<Integer>> list_i = new ArrayList<List<Integer>>();
            // run through all candidates <= i
            for (int j = 0; j < candidates.length && candidates[j] <= i; j++) {
            	// special case when curr target is equal to curr candidate
                if (i == candidates[j])
                	// if current candidate is less than the target use prev results
                    list_i.add(Arrays.asList(candidates[j]));
                else {
                    for (List<Integer> l : dp.get(i - 1 - candidates[j])) {
                        if (candidates[j] <= l.get(0)) {
                            List<Integer> tmp = new ArrayList<Integer>();
                            tmp.add(candidates[j]);
                            tmp.addAll(l);
                            if (!list_i.contains(tmp))
                                list_i.add(tmp);
                        }
                    }
                }  
            }
            dp.add(list_i);
        }
        return dp.get(target - 1);
    }

    //stack solution
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        int i = 0;
        int size = candidates.length;
        int sum = 0;
        Stack<Integer> combi = new Stack<>();//存储要放入结果里的candidates
        Stack<Integer> indices = new Stack<>();//和combi对应的每个candidates的 index
        List<List<Integer>> result = new ArrayList<>();
        while (i < size) {
            if (sum + candidates[i]>= target) {
                if (sum + candidates[i] == target) {
                    combi.push(candidates[i]);
                    result.add(new ArrayList<>(combi));
                    combi.pop();
                }
                // indices stack and combination stack should have the same size all the time
                if (!indices.empty()){
                    sum -= combi.pop();
                    i = indices.pop();
                    //如果已经遍历到最后一个candidates，则不停的出栈直到空，将最后一个candidate入栈计算
                    while (i == size-1 && !indices.empty()) {
                        i = indices.pop();
                        sum -= combi.pop();

                    }
                }
                i++;
            } else {
                combi.push(candidates[i]);
                sum +=candidates[i];
                indices.push(i);
            }
        }
        return result;
    }
}