/*
	Pascal's Triangle
	Given numRows, generate the first numRows of Pascal's triangle.

	For example, given numRows = 5,
	Return

	[
	     [1],
	    [1,1],
	   [1,2,1],
	  [1,3,3,1],
	 [1,4,6,4,1]
	]
*/

/*
	算法逻辑： 1）首先设置一个ArrayList item, 每次进入外层循环时，就往里面+1
				根据这个item构造Pascal's Triangle， 根据这个item.set(j, item.get(j) + item.get(j + 1))
				j从 1 ~ item.size() - 1
				比如 第一层时 [1] 下一层时在首位+1 == [1, 1]

			 2  每层设置的范围在  1 <= j <= item.size() - 2, 跳过前后两个1， 每次设置的j = 上一层的j + (j + 1)
*/


//Solution1: concise
public class Solution {
    public List<List<Integer>> generate(int numRows) {
    	List<List<Integer>> res = new ArrayList<>();
    	if (numRows == 0) {
    		return res;
    	}
    	List<Integer> rowRes = new ArrayList<>();
    	for (int i = 0; i < numRows; i++) {
    		rowRes.add(0, 1);//这是指在首位insert 1 ！
    		for (int j = 1; j < rowRes.size() - 1; j++) {//忽略首尾两个1
    			rowRes.set(j, rowRes.get(j) + rowRes.get(j + 1));//规律  j 等于上一层的 j + (j + 1)
    		}
    		res.add(new ArrayList<>(rowRes));
    	}
    	return res;
    }
}

//Solution2: mysolution
public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) {
            return res;
        }
        List<Integer> initList = new ArrayList<>();
        initList.add(1);
        res.add(initList);
        for (int i = 2; i <= numRows; i++) {
            List<Integer> preRow = res.get(i - 2);
            List<Integer> curRow = new ArrayList<>();
            curRow.add(1);
            for (int j = 1; j <= i - 2; j++) {
               curRow.add(preRow.get(j - 1) + preRow.get(j));
            }
            curRow.add(1);
            res.add(curRow);
        }
        return res;
    }
}