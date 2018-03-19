/*
	Permutations 
	Given a collection of numbers, return all possible permutations.

	For example,
	[1,2,3] have the following permutations:
	[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
	Tags:BackTracking.
*/


/*
	solution: key idea using boolean[]  to check the element in num arry,if is visited,dont put into the res
			  and remove 
*/

public class Solution {
	//Recursive
	public ArrayList<ArrayList<Integer>> permute(int[] num) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> item = new ArrayList<Integer>();
		if (num.length == 0 || num == null)
			return res;
		//为了不重复，visited数组是关键
		boolean[] visited = new boolean[num.length];
		backtracking(num, item, res, visited);
		return res;
	}

	public void backtracking(int[] num, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res, boolean[] visited) {
		if (item.size() == num.length) {
			res.add(new ArrayList<Integer>(item));
			return ;
		}

		for (int i = 0; i < num.length; i++) {
			/* 
				if the num[i] equals num[i - 1] ,and the visited[i - 1] is true,
    			mean that we meet the duplicate situation,
    			so we jump to this for loop, and to next number

    			下面这句if判断是 假如数组里有重复元素的 permutationII 的情况
				if (i > 0 && num[i - 1] == num[i] && !visited[i - 1]) {
                	continue;
            	}
            */
			if (!visited[i]) {
				item.add(num[i]);
				visited[i] = true;
				backtracking(num, item, res, visited);
				//去掉最后一个元素，回溯
				item.remove(item.size() - 1);
				//元素出列以后，标记为false，可以继续遍历
				visited[i] = false;
			}
		}
	}
	/*
		The basic idea is, to permute n numbers, we can add the nth number into the 
		resulting List<List<Integer>> from the n-1 numbers, in every possible position.
		For example, if the input num[] is {1,2,3}: First, add 1 into the initial List<List<Integer>> 
		(let's call it "answer").
		Then, 2 can be added in front or after 1. So we have to copy the List in answer (it's just {1}), 
		add 2 in position 0 of {1}, then copy the original {1} again, and add 2 in position 1. 
		Now we have an answer of {{2,1},{1,2}}. There are 2 lists in the current answer.

		Then we have to add 3. first copy {2,1} and {1,2}, add 3 in position 0; then copy {2,1} and {1,2}, 
		and add 3 into position 1, then do the same thing for position 3.
		 Finally we have 2*3=6 lists in answer, which is what we want.
	*/

    //iterative prefer!
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums.length == 0 || nums == null) {
            return res;
        }
        //tempList只是用来初始化，把nums[0]加入tempList，放入res
        List<Integer> initList = new ArrayList<>();
        initList.add(nums[0]);
        res.add(initList);
        

        /*	
    		if nums[] = {1, 2, 3}
    		at first , ans = { (1) }
    					after second for loop : ans = { (2, 1), (1, 2)}
    					
    					finaly { (3,2,1) (3,1,2) (2,3,1) (1,3,2) (2,1,3) (1,2,3)}
    	*/
        //i是nums数组里需要bianli
        for (int i = 1; i < nums.length; i++) {//遍历待插入元素
            //tempRes用来存放一层的结果，加入一个数字以后 就new一个空的存放下一层
            List<List<Integer>> tempRes = new ArrayList<>();
            //用j来遍历之前数字可以放的位置， 比如上一层有3个数字（1, 2, 3) i= 3
            //下一层要放入的数字4 可以放在下标0， 1， 2， 3
            for (int j = 0; j <= i; j++) {//遍历插入位置
                //新加入的数字 组成的新list，放入新res
                for (List<Integer> list : res) {//遍历res里的 list，取出来插入
                    List<Integer> newList = new ArrayList<>(list);//这样是copy的用法，如果直接把newList = list,将会导致错误
                    newList.add(j, nums[i]);
                    tempRes.add(newList);
                }
            }
            res = tempRes;
        }
        return res;
    }

	//iteration1
	public List<List<Integer>> permute(int[] num) {
    	LinkedList<List<Integer>> res = new LinkedList<List<Integer>>();
    	res.add(new ArrayList<Integer>());
	    for (int n : num) {
	        int size = res.size();
	        for (; size > 0; size--) {
	            List<Integer> r = res.pollFirst();
	            for (int i = 0; i <= r.size(); i++) {
	                List<Integer> t = new ArrayList<Integer>(r);
	                t.add(i, n);
	                res.add(t);
	            }
	        }
	    }
    	return res;
	}
	//iteration2
    public List<List<Integer>> permute(int[] num) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        List<List<Integer>> preList = null;
        List<Integer> subList = new LinkedList<Integer>();
        if(num.length == 0) {
        	return res;
        }
        subList.add(num[0]);
        res.add(subList);
        preList = res;
        for(int i = 1; i < num.length; i++) {
            res = new LinkedList<List<Integer>>();
            for(int j = 0; j < preList.size(); j++) {
                for(int k = 0; k <= preList.get(j).size(); k++) {
                    subList = new LinkedList<Integer>(preList.get(j));
                    subList.add(k, num[i]);
                    res.add(subList);
                }
            }
            preList = res;
        }
        return res;
    }
}