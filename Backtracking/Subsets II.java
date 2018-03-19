/*
	Subsets II
	Given a collection of integers that might contain duplicates, S, return all possible subsets.

	Note:
	Elements in a subset must be in non-descending order.
	The solution set must not contain duplicate subsets.
	For example,
	If S = [1,2,2], a solution is:

	[
	  [2],
	  [1],
	  [1,2,2],
	  [2,2],
	  [1,2],
	  []
	]
	Tags: Array, Backtracking
*/

public class Solution {
    //Recursive
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (num == null || num.length == 0) {
            return res;
        }
        ArrayList<Integer> item = new ArrayList<Integer>();
        res.add(new ArrayList<Integer>());
        Arrays.sort(num);
        boolean[] visited = new boolean[num.length];
        helper(num, 0, item, res, visited);
        return res;
        
    }
    
    public void helper(int[] num, int index, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res, boolean[] visited) {
       
       for (int i = index; i < num.length; i++) {
       		// this if statament is to skip the duplicate value
           if (i > 0 && num[i] == num[i - 1] && visited[i - 1] == false) {
               continue;
           }
           visited[i] = true;
           item.add(num[i]);
           res.add(new ArrayList<Integer>(item));
           helper(num, i + 1, item, res, visited);
           visited[i] = false;
           item.remove(item.size() - 1);
       } 
    }

  //iterative1
  public List<List<Integer>> subsetsWithDup(int[] num) {
      Arrays.sort(num);
      List<List<Integer>> ret = new ArrayList<>();
      ret.add(new ArrayList<Integer>());

      int size = 0, startIndex;
      for(int i = 0; i < num.length; i++) {
        //If we want to insert an element which is a dup, 
        //we can only insert it after the newly inserted elements from last step.
        startIndex = (i >= 1 && num[i] == num[i - 1]) ? size : 0;
        size = ret.size();
        for(int j = startIndex; j < size; j++) {
          List<Integer> temp = new ArrayList<>(ret.get(j));
          temp.add(num[i]);
          ret.add(temp);
        }
      }
      return ret;
  }

  //iterative 
  /*
    To solve this problem, it is helpful to first think how many subsets are there. 
    If there is no duplicate element, the answer is simply 2^n, where n is the number of elements. 
    This is because you have two choices for each element, either putting it into the subset or not.
     So all subsets for this no-duplicate set can be easily constructed: num of subset

    (1 to 2^0) empty set is the first subset
    (2^0+1 to 2^1) add the first element into subset from (1)
    (2^1+1 to 2^2) add the second element into subset (1 to 2^1)
    (2^2+1 to 2^3) add the third element into subset (1 to 2^2)
    ....
    (2^(n-1)+1 to 2^n) add the nth element into subset(1 to 2^(n-1))
    Then how many subsets are there if there are duplicate elements? 
    We can treat duplicate element as a spacial element. For example, 
    if we have duplicate elements (5, 5), instead of treating them as two elements that are duplicate, 
    we can treat it as one special element 5, but this element has more than two choices: 
    you can either NOT put it into the subset, or put ONE 5 into the subset, or put TWO 5s into the subset. 
    Therefore, we are given an array (a1, a2, a3, ..., an) with each of them appearing (k1, k2, k3, ..., kn) times, 
    the number of subset is (k1+1)(k2+1)...(kn+1). We can easily see how to write down all the subsets similar to the approach above.
  */
  public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> empty = new ArrayList<Integer>();
        result.add(empty);
        Arrays.sort(num);

        for (int i = 0; i < num.length; i++) {
            int dupCount = 0;
            while( ((i+1) < num.length) && num[i+1] == num[i]) {
                dupCount++;
                i++;
            }
            int prevNum = result.size();
            for (int j = 0; j < prevNum; j++) {
                List<Integer> element = new ArrayList<Integer>(result.get(j));
                for (int t = 0; t <= dupCount; t++) {
                    element.add(num[i]);
                    result.add(new ArrayList<Integer>(element));
                }
            }
        }
        return result;
    }
}
}