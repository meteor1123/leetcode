/*
	Pascal's Triangle II
	Given an index k, return the kth row of the Pascal's triangle.

	For example, given k = 3,
	Return [1,3,3,1].

	Note:
	Could you optimize your algorithm to use only O(k) extra space?
*/

/*
	k = 0, [1]
	k = 1, [1, 1]
	k = 2, [1, 2, 1]
	k = 3, [1, 3, 3, 1]
*/
public class Solution {
    public List<Integer> getRow(int numRows) {
        List<Integer> rowRes = new ArrayList<>();
    	for (int i = 0; i <= numRows; i++) {
    		rowRes.add(0, 1);//这是指在首位insert 1 ！
    		for (int j = 1; j < rowRes.size() - 1; j++) {//忽略首尾两个1
    			rowRes.set(j, rowRes.get(j) + rowRes.get(j + 1));//规律  j 等于上一层的 j + (j + 1)
    		}
    	}
    	return rowRes;
    }
}